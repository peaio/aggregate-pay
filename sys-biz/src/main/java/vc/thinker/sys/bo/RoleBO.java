package vc.thinker.sys.bo;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.User;
/**
 * 
 * BO 用于返回数据
 *
 */
public class RoleBO extends Role{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<UserBO> userList = Lists.newArrayList(); // 拥有用户列表
	
	private List<PermissionBO> permissions;
	
	private String permissionIds;
	
	private Office office;	// 归属机构
	
	private String officeName;
	
	public List<UserBO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBO> userList) {
		this.userList = userList;
	}
	
	@JsonIgnore
	public static List<Long> getPermissionIdList(List<PermissionBO> permissions) {
		List<Long> nameIdList = Lists.newArrayList();
		for (PermissionBO perm : permissions) {
			nameIdList.add(perm.getId());
		}
		return nameIdList;
	}
	public static List<Long> getPermissionIdList(String permissions) {
		List<Long> nameIdList = Lists.newArrayList();
		for (String perm : permissions.split(",")) {
			if(StringUtils.isNotBlank(perm)){
				nameIdList.add(Long.parseLong(perm));
			}
		}
		return nameIdList;
	}
	
	public String getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String permissionIds) {
		this.permissionIds = permissionIds;
	}

	@JsonIgnore
	public List<Long> getUserIdList() {
		List<Long> nameIdList = Lists.newArrayList();
		for (User user : userList) {
			nameIdList.add(user.getId());
		}
		return nameIdList;
	}

	@JsonIgnore
	public String getUserIds() {
		List<String> nameIdList = Lists.newArrayList();
		for (User user : userList) {
			nameIdList.add(user.getId().toString());
		}
		return StringUtils.join(nameIdList, ",");
	}
	
	public List<PermissionBO> getPermissions() {
		return permissions;
	}
	@JsonIgnore
	public void setPermissions(List<PermissionBO> permissions) {
		this.permissions = permissions;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
	
}