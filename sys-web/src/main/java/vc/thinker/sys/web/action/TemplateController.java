/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.web.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.sinco.common.utils.IPUtil;
import com.sinco.common.utils.StringUtils;

import vc.thinker.core.dal.MyPage;
import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.bo.TemplateBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.Template;
import vc.thinker.sys.model.User;
import vc.thinker.sys.service.TemplateService;
import vc.thinker.sys.vo.TemplateVO;
import vc.thinker.web.utils.UserUtils;

/**
 * 通知模板管理控制器，用来管理系统各类通知模板，包括站内短信通知、邮件通知、手机短信通知Controller
 * @author ThinkGem
 * @version 2013-6-2
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/template")
public class TemplateController extends BaseController {

	@Autowired
	private TemplateService templateService;
	
	@Value("${adminPath}")
	private String adminPath;
	
	@ModelAttribute("template")
	public TemplateBO get(@RequestParam(required=false) Long id) {
		if (id != null){
			return (TemplateBO) templateService.getObjById(id);
		}else{
			return new TemplateBO();
		}
	}
	
	@RequiresPermissions("sys:template:view")
	@RequestMapping(value = {"list", ""})
	@SecurityMapping(name="模板列表",permGroup="模板管理",userType=SysUserContant.USER_TYPE_1)
	public String list(TemplateVO vo, HttpServletRequest request, HttpServletResponse response, Model model) {
        MyPage<TemplateBO> page = templateService.selectListByVO(new MyPage<TemplateBO>(request, response), vo); 
        model.addAttribute("page", page);
		return "modules/sys/templateList";
	}

	@RequiresPermissions("sys:template:view")
	@RequestMapping("form")
	public String form(@ModelAttribute("template") TemplateBO template, Model model) {
		return "modules/sys/templateForm";
	}
	
	@RequiresPermissions("sys:template:edit")
	@SecurityMapping(name="模板添加/修改",permGroup="模板管理",userType=SysUserContant.USER_TYPE_1)
	@RequestMapping("save")
	public String save(TemplateBO template, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		
		if(template != null && template.getId() != null){
			this.templateService.update(template);
		}else{
			this.templateService.save(template);
		}
		addMessage(redirectAttributes, "保存模板成功");
		return "redirect:" +adminPath + "/sys/template/?repage";
	}
	
	@ResponseBody
	@RequestMapping("checkMark")
	public boolean checkMark(String id,String mark) {
		if(StringUtils.isNotEmpty(mark)){
			mark=mark.replace("," ,"");
		}
		List<TemplateBO> list = this.templateService.verifyMark(StringUtils.isNotEmpty(id)?Long.valueOf(id):null, mark);
		if (null != list && list.size() > 0){
			return false;
		}
		return true;
	}
}
