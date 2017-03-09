/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.web.utils;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.dao.OfficeDao;
import vc.thinker.sys.dao.PermissionDao;
import vc.thinker.sys.dao.RoleDao;
import vc.thinker.sys.dao.UserDao;
import vc.thinker.sys.model.User;
import vc.thinker.utils.SpringContextHolder;
import vc.thinker.web.security.SysPrincipal;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class UserUtils{

	private static final Logger log=LoggerFactory.getLogger(UserUtils.class);
	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
//	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);
	
	public static final String CACHE_USER = "user";
	
	/**
	 * 得到用户缓存
	 * @param isRefresh 是否刷新
	 * @return
	 */
	public static User getUser(boolean isRefresh){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			SysPrincipal principal = (SysPrincipal)subject.getPrincipal();
			if(isRefresh){
				UserBO user=userDao.findOne(Long.parseLong(principal.getId()));
				principal.setUser(user);
				//刷新缓存
				subject.getSession(false).touch();
			}
			return principal.getUser();
		}
		return null;
	}
	
	/**
	 * 得到用户缓存
	 * @return
	 */
	public static User getUser(){
		return getUser(false);
	}
	
	/**
	 * 得到用户缓存
	 * @return
	 */
	public static Long getUserId(){
		return getUser(false).getId();
	}
	
	/**
	 * 
	 * @return
	 */
	public static SysPrincipal getPrincipal(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			SysPrincipal principal = (SysPrincipal)subject.getPrincipal();
			return principal;
		}
		return null;
	}

	public static void removeCache(String key){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.getSession().removeAttribute(key);
		}
	}
	
	public static void clearCacheMap(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
			subject.getSession().removeAttribute(UserUtils.CACHE_USER);
		}
	}
	
	/**
	 * 检查ip
	 * @param ips
	 * @param requestId
	 * @return
	 */
	private static boolean ckeckIP(String [] ips,String requestId){
		for (String ip : ips) {
			if(ip.equals(requestId)){
				return true;
			}
		}
		return false;
	}
	
}
