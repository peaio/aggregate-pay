/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vc.thinker.core.dal.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.bo.LogBO;
import vc.thinker.sys.bo.LoginLogBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.service.LogService;

/**
 * 日志Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController extends BaseController {

	@Autowired
	private LogService logService;
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = {"list", ""})
	@SecurityMapping(name="日志列表",permGroup="日志管理",userType=SysUserContant.USER_TYPE_1)
	public String list(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, Model model) {
        MyPage<LogBO> page = logService.find(new MyPage<LogBO>(request, response), paramMap); 
        model.addAttribute("page", page);
        model.addAllAttributes(paramMap);
		return "modules/sys/logList";
	}
	@RequiresPermissions("sys:log:loginlist")
	@RequestMapping(value = {"loginList"})
	@SecurityMapping(name="登录日志列表",permGroup="日志管理",userType=SysUserContant.USER_TYPE_1)
	public String loginList(@RequestParam Map<String, Object> paramMap, HttpServletRequest request, HttpServletResponse response, Model model) {
		MyPage<LoginLogBO> page = logService.findLoginList(new MyPage<LoginLogBO>(request, response), paramMap); 
		model.addAttribute("page", page);
		model.addAllAttributes(paramMap);
		return "modules/sys/loginLogList";
	}

}
