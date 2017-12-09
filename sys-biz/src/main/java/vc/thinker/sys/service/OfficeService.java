/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.dao.OfficeDao;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.User;
import vc.thinker.sys.utils.AdminUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class OfficeService{

	@Autowired
	private OfficeDao officeDao;
	
	public List<OfficeBO> findByRole(String roleId){
    	return officeDao.findByRole(roleId);
    }
	
	public OfficeBO get(Long id) {
		return officeDao.get(id);
	}
	
	/**
	 * 查找用户权限内的机构
	 * @param id
	 * @return
	 */
	public List<OfficeBO> findByScopeFilter(Long uid){
		return officeDao.findByScopeFilter();
	}
	
	
	public List<OfficeBO> findAll(User user){
		
		List<OfficeBO> officeList = new ArrayList<OfficeBO>(); 
		if (AdminUtils.isAdmin(user.getId())){
			officeList = officeDao.findAll();
		}else{
			if( user.getOfficeId() != null){
				officeList = officeDao.findAllChild(user.getOfficeId() , "%,"+user.getOfficeId()+",%");
			}
		}
		return officeList;
	}
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		Office parentObj=this.get(office.getParentId());
		
		String oldParentIds = "";
		if(office.getId() != null){
			OfficeBO oldOffice=this.get(office.getId());
			oldParentIds = oldOffice.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		}
		
		office.setParentIds(parentObj.getParentIds()+office.getParentId()+",");
		officeDao.save(office);
		// 更新子节点 parentIds
		List<OfficeBO> list = officeDao.findByParentIdsLike("%,"+office.getId()+",%");
		
		for (Office e : list){
			e.setParentIds(e.getParentIds().replace(oldParentIds, office.getParentIds()));
		}
		officeDao.save( Lists.newArrayList(list.toArray(new Office[list.size()])));
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		officeDao.deleteById(id, "%,"+id+",%");
	}
	
}
