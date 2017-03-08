package vc.thinker.core.security;

/**
 * 用于传递权限配制值
 * @author james
 *
 */
public class SecurityMappingModel {
	
	private String name;// 权限名称

	private String value;// 权限值
	
	private Integer sequence;// 角色编码序号

	private String permGroup;// 权限分组

	private Integer userType;// 用户类型

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPermGroup() {
		return permGroup;
	}

	public void setPermGroup(String permGroup) {
		this.permGroup = permGroup;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
