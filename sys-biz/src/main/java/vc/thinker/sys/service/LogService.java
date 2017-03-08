/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.swing.Spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.utils.DateUtils;

import vc.thinker.core.dal.MyPage;
import vc.thinker.lbs.LbsBiz;
import vc.thinker.sys.bo.LogBO;
import vc.thinker.sys.bo.LoginLogBO;
import vc.thinker.sys.dao.LogDao;
import vc.thinker.sys.dao.LoginLogDao;
import vc.thinker.sys.model.Log;
import vc.thinker.sys.model.LoginLog;
import vc.thinker.utils.SpringContextHolder;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2013-6-2
 */
@Service
@Transactional(readOnly = true)
public class LogService {

	Logger log = LoggerFactory.getLogger(LogService.class);
			
	@Autowired
	private LogDao logDao;
	@Autowired
	private LoginLogDao loginLogDao;
	
	public Log get(String id) {
		return logDao.get(id);
	}
	
	@Transactional(readOnly = false)
	public void insertLoginLog(LoginLog loginLog)
	{
			try {
				LbsBiz lbsBiz=SpringContextHolder.getBean(LbsBiz.class);
				if(lbsBiz != null){
					loginLog.setLoginArea(lbsBiz.findByIP(loginLog.getLoginIp()).getFormattedAddress());
				}
			} catch (Exception e) {
				log.error(loginLog.getLoginIp() + "; 调用百度接口失败！", e.getMessage());
			}
		loginLogDao.insert(loginLog);
	}	
	
	public MyPage<LogBO> find(MyPage<LogBO> page, Map<String, Object> paramMap) {

		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null){
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null){
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		
		logDao.findByPage(page, paramMap);
		 
		return page;
	}
	public MyPage<LoginLogBO> findLoginList(MyPage<LoginLogBO> page, Map<String, Object> paramMap) {
		
		Date beginDate = DateUtils.parseDate(paramMap.get("beginDate"));
		if (beginDate == null){
			beginDate = DateUtils.setDays(new Date(), 1);
			paramMap.put("beginDate", DateUtils.formatDate(beginDate, "yyyy-MM-dd"));
		}
		Date endDate = DateUtils.parseDate(paramMap.get("endDate"));
		if (endDate == null){
			endDate = DateUtils.addDays(DateUtils.addMonths(beginDate, 1), -1);
			paramMap.put("endDate", DateUtils.formatDate(endDate, "yyyy-MM-dd"));
		}
		
		loginLogDao.findByPage(page, paramMap);
		
		return page;
	}
}
