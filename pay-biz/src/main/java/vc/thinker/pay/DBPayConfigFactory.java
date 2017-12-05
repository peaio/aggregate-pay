package vc.thinker.pay;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import vc.thinker.pay.alipay.AlipayConfig;
import vc.thinker.pay.service.PayConfigService;
import vc.thinker.pay.weixin.WeixinConfig;

/**
 * 基本数据库的支付配制工厂
 * @author james
 *
 */
public class DBPayConfigFactory implements PayConfigFactory{
	
	@Autowired
	private PayConfigService payConfigService;

	@Override
	public PayConfig getPayConfig(String mark) {
		
		vc.thinker.pay.model.PayConfig payConfig=payConfigService.findCacheByMark(mark);
		if(StringUtils.isBlank(payConfig.getPayChannel())){
			throw new RuntimeException("pay config [" +mark+"] payChannel null");
		}
		
		PayChannel channel=PayChannel.forName(payConfig.getPayChannel());
		
		PayConfig result=null;
		switch (channel) {
		case ALIPAY:
			AlipayConfig alipayConfig=new AlipayConfig();
			alipayConfig.setAppPrivateKey(payConfig.getAppPrivateKey());
			alipayConfig.setAppPublicKey(payConfig.getAppPublicKey());
			alipayConfig.setAlipayPublicKey(payConfig.getAlipayPublicKey());
			alipayConfig.setPartner(payConfig.getPartner());
			alipayConfig.setAppId(payConfig.getAlipayAppId());
			alipayConfig.setSafeKey(payConfig.getSafeKey());
			alipayConfig.setSellerEmail(payConfig.getSellerEmail());
			alipayConfig.setSignType(payConfig.getSignType());
			result=alipayConfig;
			break;
		case WEIXIN:
			WeixinConfig weixinConfig=new WeixinConfig();
			weixinConfig.setTenpayPartner(payConfig.getTenpayPartner());
			weixinConfig.setWxAppId(payConfig.getWxAppid());
			weixinConfig.setWxPaysignkey(payConfig.getWxPaysignkey());
			weixinConfig.setCertLocalPath(payConfig.getWxCertLocalPath());
			weixinConfig.setPayBankPublicKey(payConfig.getWxRsaPublic());
			result=weixinConfig;
			break;
		}
		result.setChannel(channel);
		result.setMark(mark);
		
		return result;
	}

	@Override
	public PayChannel getPayChannel(String mark) {
		PayConfig config=getPayConfig(mark);
		return config.getChannel();
	}
}
