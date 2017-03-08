/**
* Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.core.web.security;

import java.util.Deque;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *  session 系统安全认证实现类
 *   
 *   AuthorizingRealm 数据缓存在 session
 * @author ThinkGem
 * @version 2013-5-29
 */
public abstract class SessionAuthorizingRealm extends AuthorizingRealm {
	
	 private static final Logger log = LoggerFactory.getLogger(SessionAuthorizingRealm.class);
	 
	 public static final String CACHED_TO_STRING="SESSION_AUTHORIZING_REALM";
	 
	 static final String BOUNDED_HASH_KEY_PREFIX = "spring:session:sessions:";
	 
	 static final String SESSION_ATTR_PREFIX = "sessionAttr:";
	 
	private RedisTemplate<String, Deque<String>> redisTemplate;
	
	public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
		redisTemplate = new RedisTemplate<String, Deque<String>>();
    	redisTemplate.setKeySerializer(new StringRedisSerializer());
    	redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    	redisTemplate.setConnectionFactory(connectionFactory);
    	redisTemplate.afterPropertiesSet();
	}
	 
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return CACHED_TO_STRING;
	}
	 
	 
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {

        if (principals == null) {
            return null;
        }

        Session session = SecurityUtils.getSubject().getSession(false);
    	 if (session == null) {
            return null;
        }
    	 if (log.isTraceEnabled()) {
    		 log.trace("Retrieving AuthorizationInfo for principals [" + principals + "]");
    	 }
        
        AuthorizationInfo info = (AuthorizationInfo) session.getAttribute(CACHED_TO_STRING);

        if (info == null) {
            log.trace("No AuthorizationInfo found in cache for principals [" + principals + "]");
        } else {
            log.trace("AuthorizationInfo found in cache for principals [" + principals + "]");
        }

        if (info == null) {
            // Call template method if the info was not found in a cache
            info = doGetAuthorizationInfo(principals);
            // If the info is not null and the cache has been created, then cache the authorization info.
            if (info != null) {
            	 session.setAttribute(CACHED_TO_STRING,info);
            }
        }
        return info;
    }
	

	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		//回头实现
	}
	
	/**
	 * 清空用户关联权限认证，待下次使用时重新加载
	 */
	public void clearCachedAuthorizationInfo() { 
        Session session = SecurityUtils.getSubject().getSession(false);
    	 if (session == null) {
            return;
        }
    	session.removeAttribute(CACHED_TO_STRING);
	}

	/**
	 * 清空所有关联认证
	 */
	public void clearAllCachedAuthorizationInfo() {
		if(redisTemplate != null){
			Set<String> keys= redisTemplate.keys(BOUNDED_HASH_KEY_PREFIX+"*");
			for (String key : keys) {
				redisTemplate.boundHashOps(key).delete(SESSION_ATTR_PREFIX+CACHED_TO_STRING);
			}
		}
	}
}
