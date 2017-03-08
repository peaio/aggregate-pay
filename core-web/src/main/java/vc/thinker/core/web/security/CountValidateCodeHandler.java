package vc.thinker.core.web.security;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.InitializingBean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;

import redis.clients.jedis.JedisPool;
import vc.thinker.core.web.ValidateCodeHandler;
import vc.thinker.utils.RedisCacheUtils;
import vc.thinker.utils.SpringContextHolder;

/**
 * 计数的验证码处理
 * @author james
 *
 */
public class CountValidateCodeHandler implements ValidateCodeHandler,InitializingBean{
	
	public static final String VALIDATE_CODE = "validateCode";
	
	public static final String LOGIN_FAIL="loginFail";
	
	/**
	 * 失败次数
	 */
	private int failCount=3;
	
	/**
	 * 有效时间 
	 */
	private int effectiveTime=300;
	
	private RedisCacheUtils cacheUtils;
	
	private JedisPool pool;
	
	@Override
	public boolean isValidateCode(String loginName) {
		String key=makeKey(loginName);
		String loginFailNum =cacheUtils.get(key);
		if(StringUtils.isBlank(loginFailNum)){
			return false;
		}
		
		return Integer.parseInt(loginFailNum) >= failCount;
	}

	@Override
	public void loginSuccessHandler(String loginName) {
		// TODO Auto-generated method stub
		String key=makeKey(loginName);
		cacheUtils.del(key);
	}

	@Override
	public void loginFailHandler(String loginName) {
		// TODO Auto-generated method stub
		String key=makeKey(loginName);
		cacheUtils.incr(key);
		cacheUtils.expire(key, effectiveTime);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(pool == null){
			throw new NullPointerException("CountValidateCodeHandler init error,JedisPool is null");
		}
		cacheUtils=new RedisCacheUtils("", pool);
	}
	
	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	
	private String makeKey(String loginName){
		return new StringBuilder().append(LOGIN_FAIL).append(":").append(loginName).toString();
	}
	
}
