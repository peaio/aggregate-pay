/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sinco.common.security.PasswordUtil;

import vc.thinker.core.security.SecurityMappingModel;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.bo.RoleBO;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.contants.SysUserContant;
import vc.thinker.sys.dao.PermissionDao;
import vc.thinker.sys.dao.RoleDao;
import vc.thinker.sys.dao.RolePermissionDao;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.dao.UserDao;
import vc.thinker.sys.dao.UserRoleDao;
import vc.thinker.sys.model.Permission;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.RolePermission;
import vc.thinker.sys.model.User;
import vc.thinker.sys.model.UserAccount;
import vc.thinker.sys.model.UserRole;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 * @author ThinkGem
 * @version 2013-5-15
 */
@Service
@Transactional(readOnly = true)
public class SystemService{
	
	private static final Logger log=LoggerFactory.getLogger(SystemService.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private UserAccountDao accountDao;

	/**
	 * 根据角色查找用户
	 * @param rid
	 * @param accountType
	 * @return
	 */
	public List<UserBO> findUserByRole(Long rid, String accountType){
		return userDao.findUserByRole(rid, accountType);
	}
	
	/**
	 * 根据部门查找
	 * @param officeId
	 * @param accountType
	 * @return
	 */
	public List<UserBO> findByOffice(Long officeId, String accountType){
		return userDao.findByOffice(officeId, accountType);
	}
	
	public UserBO getUser(Long id) {
		return userDao.findOne(id);
	}

	/**
	 * 创建用户
	 * @param userType
	 * @param accountType
	 * @param loginName
	 * @param password
	 * @param requestIp
	 * @return
	 */
	@Transactional(readOnly = false)
	public Long createUser(String accountType,Long companyId,Long officeId,String dataScope, String loginName, String password, String requestIp,User currentUser) {
		User user = new User();
		user.setIsDeleted(false);
		user.setRegistIp(requestIp);
		user.setCompanyId(companyId);
		user.setOfficeId(officeId);
		user.setDataScope(dataScope);
		if(currentUser != null){
			user.setCurrentUserId(currentUser.getId().toString());
		}
		userDao.save(user);

		UserAccount account = new UserAccount();
		account.setLoginName(loginName);
		account.setPassword(PasswordUtil.entryptPassword(password));
		account.setAccountType(accountType);
		account.setIsDeleted(false);
		account.setUid(user.getId());
		if(currentUser != null){
			account.setCurrentUserId(currentUser.getId().toString());
		}
		accountDao.save(account);

		return user.getId();
	}
	/**
	 * 创建用户
	 * @param userType
	 * @param accountType
	 * @param loginName
	 * @param password
	 * @param requestIp
	 * @return
	 */
	@Transactional(readOnly = false)
	public Long createUser(String accountType, String loginName, String password, String requestIp,User currentUser) {
		return createUser(accountType, null, null, null, loginName, password, requestIp, currentUser);
	}
	
	/**
	 * 删除用户，逻辑删除
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteUser(Long id) {
		userDao.delete(id);
	}
	
	//-- Role Service --//
	
	public RoleBO getRole(Long id) {
		return roleDao.getBO(id);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Role findRoleByName(String name) {
		return roleDao.findByName(name);
	}
	
	/**
	 * 角色名称是否存在
	 * @param name
	 * @param id
	 * @return
	 */
	public boolean isRoleNameExist(String name ,Long id){
		return roleDao.countByName(name, id) > 0;
	}
	
	/**
	 * 得到用户角色
	 * @return
	 */
	public List<RoleBO> findRoleList(Long uid){
		User user=userDao.findOne(uid);
		List<RoleBO> list = null;
		if(user != null){
			if(user.isAdmin()){
				list = roleDao.findByDataScope(SysUserContant.USER_TYPE_1);
			}else{
				list = roleDao.findUserRole(user.getId(), SysUserContant.USER_TYPE_1);
			}
		}
		return list;
	}
	public List<RoleBO> findRoleList(Long uid, Integer userType){
		User user=userDao.findOne(uid);
		List<RoleBO> list = null;
		if(user != null){
			if(user.isAdmin()){
				list = roleDao.findByDataScope(userType);
			}else{
				list = roleDao.findUserRole(user.getId(), userType);
			}
		}
		return list;
	}

	
	/**
	 * 查找用户是否有该权限
	 * @param uid
	 * @param rid
	 * @return
	 */
	public boolean isUserHaveRole(Long uid,Long rid){
		return userRoleDao.count(uid, rid) > 0;
	}
	
	/**
	 * 查找用户角色
	 * @param userId
	 * @return
	 */
	public List<RoleBO> findRoleByUser(Long userId){
		return roleDao.findUserRole(userId, SysUserContant.USER_TYPE_1);
	}
	
	/**
	 * 初始化系统权限
	 * @param smList 权限 
	 * @param userType 用户类型
	 */
	@Transactional(readOnly = false)
	public void initSysPermissions(List<SecurityMappingModel> smList,Integer userType){
		List<String> vals=Lists.newArrayList();
		for (SecurityMappingModel sm : smList) {
			if(sm.getUserType().equals(userType)){
				Permission p=new Permission();
				p.setCreateTime(new Date());
				p.setGroupName(sm.getPermGroup());
				p.setName(sm.getName());
				p.setValue(sm.getValue());
				p.setUserType(sm.getUserType());
				p.setType(SysUserContant.PERMISSION_TYPE_FLAG);
				try {
					permissionDao.save(p);
				} catch (DuplicateKeyException e) {
					log.info("用户类型{}，权限 {} 已经存在",userType,p.getValue());
				}
				vals.add(p.getValue());
			}
		}
		
		List<PermissionBO> perms=permissionDao.findByNotValues(vals, userType);
		
		if(!perms.isEmpty()){
			//删除多余权限
			permissionDao.deleteByNotValues(vals, userType);
			//删除多余的角色权限关系
			for (PermissionBO perm : perms) {
				rolePermissionDao.deleteByPermissionId(perm.getId());
			}
		}
		
	}
	
	/**
	 * 修改最后登录时间
	 * @param loginName
	 * @param ip
	 */
	@Transactional(readOnly = false)
	public void updateUserLoginInfo(String loginName,String ip){
		// 修改最后登录时间
		UserAccount updateAccount = new UserAccount();
		updateAccount.setLastLoginIp(ip);
		updateAccount.setLastLoginTime(new Date());
		accountDao.updateByLoginName(loginName, updateAccount);
	}
	
	/**
	 * 保存角色信息
	 * @param role
	 * @param permissions
	 * @param offices
	 */
	@Transactional(readOnly = false)
	public void saveRole(Role role,List<Long> permissions,User operateUser) {
		
		if(role.getId() != null){
			rolePermissionDao.deleteByRoleId(role.getId());
//			roleOfficeDao.deleteByRoleId(role.getId().toString());
		}
		
		roleDao.save(role);
		
		//如果用户添加角色，给用户自动加上该角色
		if(userRoleDao.count(operateUser.getId(), role.getId()) <= 0){
			UserRole ur=new UserRole();
			ur.setRoleId(role.getId());
			ur.setUserId(operateUser.getId());
			userRoleDao.save(ur);
		}
		
		//修改菜单
		if(permissions != null && permissions.size() > 0){
			Set<Long> permissionSets=new HashSet<>(permissions);
			for (Long perId : permissionSets) {
				if(permissionDao.exists(perId)){
					RolePermission rp=new RolePermission();
					rp.setPermissionId(perId);
					rp.setRoleId(role.getId());
					rolePermissionDao.save(rp);
				}
			}
		}
	}

	/**
	 * 删除角色，物理
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void deleteRole(Long rid) {
		//删除角色
		roleDao.deleteById(rid);
		//删除角色权限
		rolePermissionDao.deleteByRoleId(rid);
		//删除用户角色
		userRoleDao.deleteByRoleId(rid);
		// 同步到Activiti
	}
	
	/**
	 * 得到用户权限分组
	 * @return
	 */
	public Set<String> getPermissionGroupNameList(List<PermissionBO> list){
		Set<String> groupNameList=Sets.newTreeSet();
		for (PermissionBO perm : list) {
			groupNameList.add(perm.getGroupName());
		}
		return groupNameList;
	}
	
	/**
	 * 根据用户查找权限
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findPermByUid(User user,Integer userType){
		List<PermissionBO> list = null;
		//查找管理员权限 
		if(user.isAdmin()){
			list=permissionDao.findPermByUserType(userType);
		}else{
			list=permissionDao.findByUid(user.getId(),userType);
		}
		return list;
	}
	/**
	 * 根据用户查找权限
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findCustomerPermByUid(User user,Integer userType){
		return permissionDao.findPermByUserType(userType);
	}
	
	/**
	 * 根据角色查找权限
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findPermByRid(Long rid){
		return permissionDao.findByRid(rid);
	}
	/**
	 * 根据用户查找权限
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findPermByUserType(Integer userType){
		return permissionDao.findPermByUserType(userType);
	}
	
	/**
	 * 移动用户权限
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public Boolean outUserInRole(Long roleId, Long userId) {
		UserBO user = userDao.findOne(userId);
		
		user.setRoleList(roleDao.findUserRole(userId, SysUserContant.USER_TYPE_1));
		
		List<Long> roleIds = user.getRoleIdList();
		// 
		if (roleIds.contains(roleId)) {
			userRoleDao.delete(roleId,userId);
			
			return true;
		}
		return false;
	}
	
	/**
	 * 添加用户权限
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean assignUserToRole(Long roleId, Long userId) {
		if (isUserHaveRole(userId,roleId)) {
			return false;
		}
		
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRoleDao.save(userRole);
		return true;
	}
}
