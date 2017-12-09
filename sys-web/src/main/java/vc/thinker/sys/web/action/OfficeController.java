/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.web.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
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
import com.google.common.collect.Maps;

import vc.thinker.core.security.SecurityMapping;
import vc.thinker.core.web.BaseController;
import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.User;
import vc.thinker.sys.service.OfficeService;
import vc.thinker.sys.utils.AdminUtils;
import vc.thinker.web.utils.UserUtils;

/**
 * 机构Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;
	@Value("${adminPath}")
	private String adminPath;
	
	@ModelAttribute("office")
	public OfficeBO get(@RequestParam(required = false) Long id) {
		if (id != null) {
			OfficeBO of= officeService.get(id);
			return of;
		} else {
			return new OfficeBO();
		}
	}

	@SecurityMapping(name="机构列表",permGroup="机构管理",userType=SysUserContant.USER_TYPE_1)
	@RequiresPermissions("sys:office:view")
	@RequestMapping({"officeIndex", ""})
	public String officeIndex() {
		return "modules/sys/officeIndex";
	}
	
	@SecurityMapping(name="机构列表",permGroup="机构管理",userType=SysUserContant.USER_TYPE_1)
	@RequiresPermissions("sys:office:view")
	@RequestMapping({"list"})
	public String list(Office office, Model model) {
		User user = UserUtils.getUser();
		if(AdminUtils.isAdmin(user.getId())){
			office.setId(1L);
		}else{
			office.setId(user.getOfficeId());
		}
		model.addAttribute("office", office);
		
		List<OfficeBO> list = Lists.newArrayList();
		List<OfficeBO> sourcelist = officeService.findAll(user);
		Office.sortList(list, sourcelist, office.getId());
        model.addAttribute("list", list);
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping("form")
	public String form(@ModelAttribute("office") OfficeBO office, Model model) {
		User user = UserUtils.getUser();
		
		if (office.getParentId() == null) {
			office.setParent(officeService.get(user.getOfficeId()));
		}else{
			office.setParent(officeService.get(office.getParentId()));
		}
		if (office.getAreaId() == null && office.getParent() != null) {
			office.setAreaId(office.getParent().getAreaId());
		}
		
		model.addAttribute("office", office);
		
		return "modules/sys/officeForm";
	}
	
	@SecurityMapping(name="机构修改",permGroup="机构管理",userType=SysUserContant.USER_TYPE_1)
	@RequiresPermissions("sys:office:edit")
	@RequestMapping("save")
	public String save(OfficeBO office, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, office)) {
			return form(office, model);
		}
		officeService.save(office);
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		return "redirect:" + adminPath+ "/sys/office/list";
	}
	
	@RequiresPermissions("sys:office:edit")
	@RequestMapping("delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		if (Office.isRoot(id)) {
			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
		} else {
			officeService.delete(id);
			addMessage(redirectAttributes, "删除机构成功");
		}
		return "redirect:" + adminPath+ "/sys/office/list";
	}

	@RequiresUser
	@ResponseBody
	@RequestMapping("treeData")
	public List<Map<String, Object>> treeData(HttpServletResponse response,
			@RequestParam(required = false) Long extId,
			@RequestParam(required = false) Long type,
			@RequestParam(required = false) Long grade) {
		
		response.setContentType("application/json; charset=UTF-8");
		
		User user = UserUtils.getUser();
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
//		User user = UserUtils.getUser();
		List<OfficeBO> list = officeService.findAll(user);
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			
			if ((extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && Integer.parseInt(e.getType()) <= type.intValue()))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))){
				
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
//				map.put("pId", !user.isAdmin() && e.getId().equals(user.getOffice().getId())?0:e.getParent()!=null?e.getParent().getId():0);
				map.put("pId", e.getParentId()!= null ? e.getParentId() : 0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
