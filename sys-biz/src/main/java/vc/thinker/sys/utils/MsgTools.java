package vc.thinker.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Async;

import com.sinco.messager.MessageHandler;
import com.sinco.messager.sms.AlidayuMessage;
import com.sinco.messager.sms.AlidayuNewMessage;
import com.sinco.messager.sms.SMSConstants;
import com.sinco.messager.sms.SMSMessageAlidayuHandler;
import com.sinco.messager.sms.SMSMessageNewAlidayuHandler;
import com.sinco.messager.sms.SMSResult;

import redis.clients.jedis.Protocol;
import vc.thinker.sys.dao.TemplateDao;
import vc.thinker.sys.model.Template;
import vc.thinker.utils.RedisCacheUtils;


/**
 * 
 * <p>
 * Title: MsgTools.java<／p>
 * 
 * <p>
 * Description: 系统手机短信、邮件发送工具类，手机短信发送需要运营商购买短信平台提供的相关接口信息，邮件发送需要正确配置邮件服务器，
 * 运营商管理后台均有相关配置及发送测试（erikzhang） <／p>
 * <p>
 * 发送短信邮件工具类 参数json数据 buyer_id:如果有买家，则买家user.id seller_id:如果有卖家,卖家的user.id
 * sender_id:发送者的user.id receiver_id:接收者的user.id order_id:如果有订单 订单order.id
 * childorder_id：如果有子订单id goods_id:商品的id self_goods: 如果是自营商品 则在邮件或者短信显示 平台名称
 * SysConfig.title,（jinxinzhe）
 * </p>
 * 
 * 
 * <p>
 * Copyright: Copyright (c) 2015<／p>
 * 
 * <p>
 * Company: 新科聚合 thinker.vc<／p>
 * 
 * @author thinker，jinxinzhe，hezeng
 * 
 * @date 2015-5-20
 * 
 * @version 1.0.1
 */
public class MsgTools implements InitializingBean,ApplicationContextAware{
	private static final Logger logger=LoggerFactory.getLogger(MsgTools.class);
	@Autowired
	private TemplateDao templateDao;
	
	private RedisCacheUtils cacheUtils;
	
	private String redisHost;
	
	private int redisPort=Protocol.DEFAULT_PORT;
	
	private ApplicationContext applicationContext;
	
	private MessageHandler smsHandler;
	
	private static final String MSG_CACHE_KEY="sms:";

	/**
	 * 免费短信发送方法，系统给用户发送的短信工具，
	 * 
	 * @param request
	 * @param mark    短信模板代码
	 * @param mobile  手机号
	 * @param param   模板需要的参数
	 */
	@Async
	public void sendSms(String mark, String mobile,String ... params) {
		sendSms(mark, mobile,null, params);
	}
	
	/**
	 * 免费短信发送方法，系统给用户发送的短信工具，
	 * 
	 * @param request
	 * @param mark    短信模板代码
	 * @param mobile  手机号
	 * @param param   模板需要的参数
	 */
	@Async
	public void sendSms(String mark, String mobile,String ip,String [] params) {
		if(smsHandler == null){
			throw new RuntimeException("未配制 smsMessageHandler ，无法进行短信发送");
		}
		
		try {
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			logger.info("短信发送日期："+date+",mark："+mark+",mobile："+mobile+",param:"+params);
			Template template =  this.templateDao.findByMark(mark);
			if (template != null && template.getOpen()) {
				
				//目前限制实现会出现超出的情况，具体超出多少要看第三方接口超时时间和超时后是否依然会发送
				if(template.getTimes() != null && template.getTimes() != 0){
					if(!vaildateLimitCount(mark, mobile, template.getTimes())){
						logger.error("号码{}发送{}模版超出次数限制",mobile,mark);
						return;
					}
//					if(StringUtils.isNotBlank(ip) && !vaildateLimitCount(mark, ip, template.getTimes())){
//						logger.error("IP{}发送{}模版超出次数限制",mobile,ip);
//						return;
//					}
				}
				
				if (StringUtils.isNotEmpty(mobile)) {
					String content = template.getContent();
					if(StringUtils.isNotEmpty(content)){
						if(params != null){
							for(int i=0;i<params.length;i++){
								content=content.replace("$"+(i+1), params[i]);
							}
						}
						logger.info("短信发送日期："+date+",手机号码："+mobile+",短信内容："+content);
						boolean ret = this.smsHandler.sendMessage(mobile, content);
						logger.info("短信发送日期："+date+",短信发送结果："+ret);
						//添加手机限制次数
						addDaySendCount(mark, mobile);
						if(StringUtils.isNotBlank(ip)){
							//添加IP限制次数,先定6小时
							//addSendCount(mark, ip,60*60*6,false);
						}
						if (ret) {
							logger.info("发送短信成功");
						} else {
							logger.info("发送短信失败");
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("短信发送失败："+e);
		}
	}
	/**
	 * 
	 * @Title: sendTemplateSms 
	 * @Description: TODO(短信发送) 
	 * @param @param mark －－模版表示
	 * @param @param mobile －－手机号码
	 * @param @param ip －ip地址
	 * @param @param params 参数json格式；例：{code:"123456"}  
	 * @param @param smsFreeSignName  短信签名，需要在ali大鱼后台配置
	 * @return void    返回类型 
	 * @author Tang
	 * @throws
	 */
	@Async
	public void sendTemplateSms(String mark, String mobile,String ip, String params, String smsFreeSignName) {
		if(smsHandler == null){
			throw new RuntimeException("未配制 smsMessageHandler ，无法进行短信发送");
		}
		
		try {
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			logger.info("短信发送日期："+date+",mark："+mark+",mobile："+mobile+",param:"+params);
			Template template =  this.templateDao.findByMark(mark);
			if (template != null && template.getOpen()) {
				
				//目前限制实现会出现超出的情况，具体超出多少要看第三方接口超时时间和超时后是否依然会发送
				if(template.getTimes() != null && template.getTimes() != 0){
					if(!vaildateLimitCount(mark, mobile, template.getTimes())){
						logger.error("号码{}发送{}模版超出次数限制",mobile,mark);
						return;
					}
				}
				
				if (StringUtils.isNotEmpty(mobile)) {
					String thirdTemplateId = template.getThirdTemplateId();
					if(StringUtils.isNotEmpty(thirdTemplateId)){
						logger.info("短信参数："+params);
						logger.info("短信发送日期："+date+",手机号码："+mobile+",短信内容："+template.getContent());
						
						boolean ret =false;
						if(this.smsHandler instanceof SMSMessageAlidayuHandler){
							SMSMessageAlidayuHandler aliMsg = (SMSMessageAlidayuHandler) this.smsHandler;
							AlidayuMessage message=new AlidayuMessage(smsFreeSignName, thirdTemplateId, params);
							ret = aliMsg.sendMessage(mobile, message);
						}else if(this.smsHandler instanceof SMSMessageNewAlidayuHandler){
							SMSMessageNewAlidayuHandler aliMsg = (SMSMessageNewAlidayuHandler) this.smsHandler;
							AlidayuNewMessage message=new AlidayuNewMessage(smsFreeSignName, thirdTemplateId, params);
							ret = aliMsg.sendMessage(mobile, message);
						}else {
							logger.info("暂不支持的发送方式[{}]",this.smsHandler);
						}
						logger.info("短信发送日期："+date+",短信发送结果："+ret);
						//添加手机限制次数
						addDaySendCount(mark, mobile);
						if(StringUtils.isNotBlank(ip)){
							//添加IP限制次数,先定6小时
							//addSendCount(mark, ip,60*60*6,false);
						}
						if (ret) {
							logger.info("发送短信成功");
						} else {
							logger.info("发送短信失败");
						}
					}
					else
					{
						logger.info("发送短信失败，第三方模版id为空");
					}
				}
			}
		} catch (Exception e) {
			logger.error("短信发送失败："+e);
		}
	}
	
	/**
	 * 
	 * @Title: sendTemplateSms 
	 * @Description: TODO(短信发送) 
	 * @param @param mark －－模版表示
	 * @param @param mobile －－手机号码
	 * @param @param ip －ip地址
	 * @param @param params 参数json格式；例：{code:"123456"}  
	 * @param @param smsFreeSignName  短信签名，需要在ali大鱼后台配置
	 * @return void    返回类型 
	 * @author Tang
	 * @throws
	 */
	public SMSResult sendTemplateSms(String mobile,String ip, String params, String smsFreeSignName, String thirdTemplateId,String accountSid,String accountToken) {
		SMSResult smsResult=null;
		if(smsHandler == null){
			smsResult=new SMSResult(SMSConstants.SMS_CODE_SYSTEM_ERROR, SMSConstants.SMS_CODE_SYSTEM_ERROR_DESC);
			logger.error(this.getClass().getName() + "错误码：" + SMSConstants.SMS_CODE_SYSTEM_ERROR+ "错误描述：" +SMSConstants.SMS_CODE_SYSTEM_ERROR_DESC);
			logger.error("未配制 smsMessageHandler ，无法进行短信发送");
			return smsResult;
		}
		
		try {
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			logger.info("短信发送日期："+date+",mobile："+mobile+",ip："+ip+",param:"+params+",smsFreeSignName:"+smsFreeSignName+",thirdTemplateId:"+thirdTemplateId+",accountSid:"+accountSid+",accountToken:"+accountToken);
			
			SMSMessageAlidayuHandler aliMsg = (SMSMessageAlidayuHandler) this.smsHandler;
			
			AlidayuMessage message=new AlidayuMessage(smsFreeSignName, thirdTemplateId, params, accountSid, accountToken);
			smsResult = aliMsg.sendTemplateMessage(mobile, message);
			logger.info("短信发送日期："+date+",短信发送结果："+smsResult.toString());
			
		} catch (Exception e) {
			smsResult=new SMSResult(SMSConstants.SMS_CODE_SYSTEM_ERROR, SMSConstants.SMS_CODE_SYSTEM_ERROR_DESC);
			logger.error(this.getClass().getName() + "错误码：" + SMSConstants.SMS_CODE_SYSTEM_ERROR+ "错误描述：" +SMSConstants.SMS_CODE_SYSTEM_ERROR_DESC);
			logger.error("短信发送失败："+e);
			return smsResult;
		}
		
		return smsResult;
	}
	
	/**
	 * 验证限制次数
	 * @return true 正常， false 超出
	 */
	private boolean vaildateLimitCount(String mark,String mobile,Integer limitCount){
		StringBuilder key=new StringBuilder(MSG_CACHE_KEY);
		key.append(":").append(mobile).append(":").append(mark);
		String sendCount= cacheUtils.get(key.toString());
		if(StringUtils.isNotBlank(sendCount)){
			if(limitCount <= Integer.parseInt(sendCount)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 添加发送次数,设置每天过期
	 */
	private Long addDaySendCount(String mark,String mobile){
		
		Calendar date= Calendar.getInstance();
		Long currentTime=date.getTime().getTime();
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		
		return addSendCount(mark, mobile,
				new Long((date.getTime().getTime() - currentTime)/1000).intValue(), true);
	}

	/**
	 * 添加发送次数
	 * @param mark
	 * @param mobile
	 * @param expire 过期时间，秒
	 * @param isAddFlush 添加是否刷新过期时间
	 * @return
	 */
	private Long addSendCount(String mark,String mobile,int expire,boolean isAddFlush){
		StringBuilder key=new StringBuilder(MSG_CACHE_KEY);
		key.append(":").append(mobile).append(":").append(mark);
		Long sendCount=cacheUtils.incr(key.toString());
		
		if(isAddFlush ){
			//设置过期
			cacheUtils.expire(key.toString(),expire);
		}else{
			//只第一次设置过期
			if(sendCount == 1){
				cacheUtils.expire(key.toString(),expire);
			}
		}
		return sendCount;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(applicationContext.containsBean("smsMessageHandler")){
			this.smsHandler=(MessageHandler) applicationContext.getBean("smsMessageHandler");
		}
		if(this.smsHandler == null){
			logger.error("未配制 smsMessageHandler ，无法进行短信发送");
		}
		if(cacheUtils == null && (redisHost != null)){
			cacheUtils=new RedisCacheUtils("sms", redisHost,redisPort);
		}
		if(cacheUtils == null){
			throw new NullPointerException("cacheUtils 是空，请配制 reids host and port,或者cacheUtils本身引用");
		}
	}

	public RedisCacheUtils getCacheUtils() {
		return cacheUtils;
	}

	public void setCacheUtils(RedisCacheUtils cacheUtils) {
		this.cacheUtils = cacheUtils;
	}

	public MessageHandler getSmsHandler() {
		return smsHandler;
	}

	public void setSmsHandler(MessageHandler smsHandler) {
		this.smsHandler = smsHandler;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	
	/**
	 * 
	 * @Title: sendEmail 
	 * @throws
	 */
	@Async
	public void sendEmail(String mark, String [] params,String toMailAddress, String title, String emailUserName,String emailPws,String emialHost,String fromEmailUser) {
		try {
			String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			logger.info("邮件发送日期："+date+",mark："+mark+",title："+title+",param:"+params+",toMailAddress:"+toMailAddress+",fromEmailUser:"+fromEmailUser);
			Template template =  this.templateDao.findByMark(mark);
			if (template != null && template.getOpen()) {
				String content = template.getContent();
				if(StringUtils.isNotEmpty(content)){
					if(params != null){
						for(int i=0;i<params.length;i++){
							content=content.replace("$"+(i+1), params[i]);
						}
					}
				}
				logger.info("邮件发送日期："+date+",mark："+mark+",title："+title+",content："+content+",param:"+params+",toMailAddress:"+toMailAddress+",fromEmailUser:"+fromEmailUser);
				
				boolean ret = this.sendEmail(toMailAddress, title, content, emailUserName, emailPws, emialHost, fromEmailUser);
				logger.info("邮件发送日期："+date+",邮件发送结果："+ret);
				if (ret) {
					logger.info("发送邮件成功");
				} else {
					logger.info("发送邮件失败");
				}
			}
		} catch (Exception e) {
			logger.error("邮件发送失败："+e);
		}
	}
	
	/**
	 * 发送邮件底层工具
	 * 
	 * @return
	 */
	public boolean sendEmail(String toMailAddress, String title, String content,String emailUserName,String emailPws,String emialHost,String fromEmailUser) {
		boolean ret = true;
		if (emailUserName != null && emailPws != null && !emailUserName.equals("") && !emailPws.equals("") 
				&& emialHost != null&& !emialHost.equals("") && toMailAddress != null && !toMailAddress.trim().equals("")) {
			Authenticator auth = new PopupAuthenticator(emailUserName, emailPws);
			Properties mailProps = new Properties();
			mailProps.put("mail.smtp.auth", "true");
			mailProps.put("username", emailUserName);
			mailProps.put("password", emailPws);
			mailProps.put("mail.smtp.host", emialHost);//存储发送邮件服务器的信息 
			Session mailSession = Session.getInstance(mailProps, auth);
			MimeMessage message = new MimeMessage(mailSession);
			try {
				message.setFrom(new InternetAddress(fromEmailUser));//设置发件人的地址  
				message.setRecipient(Message.RecipientType.TO,new InternetAddress(toMailAddress));//设置收件人,并设置其接收类型为TO  
				message.setSubject(title);//设置标题  
				message.setSentDate(new Date());//设置发信时间  
				MimeMultipart multi = new MimeMultipart("related");
				BodyPart bodyPart = new MimeBodyPart();
				bodyPart.setDataHandler(new DataHandler(content,"text/html;charset=UTF-8"));// 网页格式
				multi.addBodyPart(bodyPart);
				message.setContent(multi);
				Transport.send(message);
				ret = true;
				logger.info("邮件发送成功==========");
			} catch (AddressException e) {
				ret = false;
				e.printStackTrace();
				logger.error("邮件发送失败："+e);
				logger.error("邮件发送失败==========");
			} catch (MessagingException e) {
				ret = false;
				e.printStackTrace();
				logger.error("邮件发送失败："+e);
				logger.error("邮件发送失败==========");
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("邮件发送失败："+e);
				logger.error("邮件发送失败==========");
			}
		} else {
			logger.error("邮件发送参数缺失==========");
			ret = false;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		MsgTools msgTools=new MsgTools();
//		msgTools.sendEmail("526889700@qq.com", "测试邮件标题", "测试邮件内容", "kevinzhoucs@163.com", "kevin123456", "smtp.163.com", "kevinzhoucs@163.com");
		msgTools.sendTemplateSms("sms_validate_code", "13212345678", "127.0.0.1", "{code:123456, product:粤港专车}", "身份验证");
	}
}
