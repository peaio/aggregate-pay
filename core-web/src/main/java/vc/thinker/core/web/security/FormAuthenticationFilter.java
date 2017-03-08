/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.core.web.security;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;

import com.sinco.common.utils.IPUtil;

import vc.thinker.core.security.BasePrincipal;
import vc.thinker.core.web.ValidateCodeHandler;

/**
 * 表单验证（包含验证码）过滤类
 * 
 * @author ThinkGem
 * @version 2013-5-19
 */
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	
	private static final Logger log=org.slf4j.LoggerFactory.getLogger(FormAuthenticationFilter.class);

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	private String kickoutUrl; // 踢出后到的地址

	// kickoutUrl session attribute key
	public final static String KICKOUT_URL_SESSION_ATTRIBUTE_KEY = "kickout_url_session_attribute_key";

	public final static String BOUNDED_HASH_KEY_PREFIX = "cabbage:session:idlist:";

	private boolean kickoutAfter = false; // false 踢出之前登录的/ true 之后登录的用户
											// 默认踢出之前登录的用户

	private Integer maxSession; // 同一个帐号最大会话数 默认空不做限制

	private RedisTemplate<String, Deque<String>> redisTemplate;

	private SessionRepository<ExpiringSession> sessionRepository;
	
	private ValidateCodeHandler validateCodeHandler;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	@Override
	protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		super.redirectToLogin(request, response);
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = IPUtil.getIpAddr(request);
		String captcha = getCaptcha(request);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
		BasePrincipal principal = (BasePrincipal) subject.getPrincipal();
		
		// 登录成功后，验证码计算器清零
		if(validateCodeHandler != null){
			validateCodeHandler.loginSuccessHandler(principal.getLoginName());
		}
		
		// 判断是否验证 session 数量
		if (maxSession != null) {

			Session session = subject.getSession();
			
			String userId = principal.getId();
			String sessionId = (String) session.getId();

			String cacheKey = getUserSessionIdListKey(userId);
			// TODO 同步控制
			Deque<String> deque = redisTemplate.boundValueOps(cacheKey).get();
			if (deque == null) {
				deque = new LinkedList<String>();
			}

			// 如果队列里没有此sessionId，且用户没有被踢出；放入队列
			if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
				deque.push(sessionId);
			}

			// 如果队列里的sessionId数超出最大会话数，开始踢人
			while (deque.size() > maxSession) {
				String kickoutSessionId = null;
				if (kickoutAfter) { // 如果踢出后者
					kickoutSessionId = deque.removeFirst();
				} else { // 否则踢出前者
					kickoutSessionId = deque.removeLast();
				}
				ExpiringSession kickoutSession = sessionRepository.getSession(kickoutSessionId);
				if (kickoutSession != null) {
					// 设置会话的kickout属性表示踢出了
					kickoutSession.setAttribute(KICKOUT_URL_SESSION_ATTRIBUTE_KEY, kickoutUrl);
					sessionRepository.save(kickoutSession);
				}
			}
			redisTemplate.boundValueOps(cacheKey).set(deque);
		}
		return super.onLoginSuccess(token, subject, request, response);
	}	
	
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
            ServletRequest request, ServletResponse response) {
    	
//    	if(e instanceof IncorrectCredentialsException && ) 目前不确认是否只在密码错误的时候触发，先注释
		// 登录失败
		if( validateCodeHandler != null){
			validateCodeHandler.loginFailHandler(((UsernamePasswordToken) token).getUsername());
		}
		log.info("onLoginFailure：",e);
		return super.onLoginFailure(token, e, request, response);
	}

	/**
	 * 得到sesison id list key
	 * 
	 * @param userId
	 * @return
	 */
	private String getUserSessionIdListKey(String userId) {
		return BOUNDED_HASH_KEY_PREFIX + userId;
	}

	public String getKickoutUrl() {
		return kickoutUrl;
	}

	public void setKickoutUrl(String kickoutUrl) {
		this.kickoutUrl = kickoutUrl;
	}

	public boolean isKickoutAfter() {
		return kickoutAfter;
	}

	public void setKickoutAfter(boolean kickoutAfter) {
		this.kickoutAfter = kickoutAfter;
	}

	public int getMaxSession() {
		return maxSession;
	}

	public void setMaxSession(int maxSession) {
		this.maxSession = maxSession;
	}

	public ValidateCodeHandler getValidateCodeHandler() {
		return validateCodeHandler;
	}

	public void setValidateCodeHandler(ValidateCodeHandler validateCodeHandler) {
		this.validateCodeHandler = validateCodeHandler;
	}

	public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		redisTemplate = new RedisTemplate<String, Deque<String>>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.afterPropertiesSet();
	}

	public void setSessionRepository(SessionRepository<ExpiringSession> sessionRepository) {
		this.sessionRepository = sessionRepository;
	}
}