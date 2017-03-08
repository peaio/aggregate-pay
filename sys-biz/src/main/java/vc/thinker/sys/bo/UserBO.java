package vc.thinker.sys.bo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sinco.common.utils.Collections3;

import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.Role;
import vc.thinker.sys.model.User;

/**
 * 
 * BO 用于返回数据
 *
 */
public class UserBO extends User {

	private Office company;	// 归属公司
	
	private Office office;	// 归属部门

	private List<OfficeBO> officeList = Lists.newArrayList(); // 按明细设置数据范围

	private String loginName;

	private List<RoleBO> roleList = Lists.newArrayList(); // 拥有角色列表

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public List<OfficeBO> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<OfficeBO> officeList) {
		this.officeList = officeList;
	}

	public void setRoleList(List<RoleBO> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<Long> getRoleIdList() {
		List<Long> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	@JsonIgnore
	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			RoleBO role = new RoleBO();
			role.setId(Long.parseLong(roleId));
			roleList.add(role);
		}
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	@JsonIgnore
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}

	@JsonIgnore
	public List<Long> getOfficeIdList() {
		List<Long> officeIdList = Lists.newArrayList();
		for (Office office : officeList) {
			officeIdList.add(office.getId());
		}
		return officeIdList;
	}

	@JsonIgnore
	public void setOfficeIdList(List<Long> officeIdList) {
		officeList = Lists.newArrayList();
		for (Long officeId : officeIdList) {
			OfficeBO office = new OfficeBO();
			office.setId(officeId);
			officeList.add(office);
		}
	}

	@JsonIgnore
	public String getOfficeIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (Office office : officeList) {
			nameIdList.add(office.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	@JsonIgnore
	public void setOfficeIds(String officeIds) {
		officeList = Lists.newArrayList();
		if (officeIds != null) {
			String[] ids = StringUtils.split(officeIds, ",");
			for (String officeId : ids) {
				OfficeBO office = new OfficeBO();
				office.setId(Long.parseLong(officeId));
				officeList.add(office);
			}
		}
	}

	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	public List<RoleBO> getRoleList() {
		return roleList;
	}
}