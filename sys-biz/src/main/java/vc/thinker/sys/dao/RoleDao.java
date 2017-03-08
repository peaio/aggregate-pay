/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.mapper.RoleMapper;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.RoleExample;

/**
 * 角色DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class RoleDao{
	
	@Autowired
	private RoleMapper mapper;
	

	/**
	 * 查找用户角色
	 * @param userId
	 * @return
	 */
    public List<RoleBO> findUserRole(Long userId, Integer userType){
    	return mapper.findUserRole(userId, userType);
    }
    
    /**
     * 查找权限查看
     * @return
     */
    public List<RoleBO> findByDataScope(Integer userType){
    	return mapper.findByDataScope(userType);
    }
	
	public RoleBO findByName(String name){
		RoleExample example=new RoleExample();
		example.createCriteria().andNameEqualTo(name);
		List<RoleBO> list=mapper.selectByExample(example);
		return list.size() > 0 ?list.get(0):null;
	}
	
	public int countByName(String name,Long id){
		RoleExample example=new RoleExample();
		RoleExample.Criteria c=example.createCriteria();
		c.andNameEqualTo(name);
		if(id != null){
			c.andIdNotEqualTo(id);
		}
		return mapper.countByExample(example);
	}
	
	
	public List<Role> findAll() {
		RoleExample example=new RoleExample();
		
		List<Role> list=new ArrayList<Role>();
		List<RoleBO> boList=mapper.selectByExample(example);
		for (Role menu : boList) {
			list.add(menu);
		}
		return list;
	}

	
	public RoleBO get(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public RoleBO getBO(Long id) {
		return mapper.selectByPrimaryKey( id);
	}

	
	public void save(Role entity) {
		if(entity.getId() != null){
			update(entity);
		}else{
			insert(entity);
		}
	}

	
	public void insert(Role entity) {
		mapper.insert(entity);
	}

	
	public void update(Role entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	
	public void save(List<Role> entityList) {
		for (Role role : entityList) {
			save(role);
		}
	}

	public int deleteById(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

//	@Query("from Role where delFlag='" + Role.DEL_FLAG_NORMAL + "' order by name")
//	public List<Role> findAllList();
//
//	@Query("select distinct r from Role r, User u where r in elements (u.roleList) and r.delFlag='" + Role.DEL_FLAG_NORMAL +
//			"' and u.delFlag='" + User.DEL_FLAG_NORMAL + "' and u.id=?1 or (r.user.id=?1 and r.delFlag='" + Role.DEL_FLAG_NORMAL +
//			"') order by r.name")
//	public List<Role> findByUserId(Long userId);

}
