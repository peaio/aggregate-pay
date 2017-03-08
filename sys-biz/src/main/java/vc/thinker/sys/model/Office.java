package vc.thinker.sys.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vc.thinker.dal.common.persistence.mybatis.DataEntity;
import vc.thinker.sys.bo.OfficeBO;

public class Office extends DataEntity<Office> {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long parentId;	// 父级编号
	
	private String parentIds; // 所有父级编号
	
	private String areaId;
	
	private String code; 	// 机构编码
	private String name; 	// 机构名称
	private String type; 	// 机构类型（1：公司；2：部门；3：小组）
	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; // 联系地址
	private String zipCode; // 邮政编码
	private String master; 	// 负责人
	private String phone; 	// 电话
	private String fax; 	// 传真
	private String email; 	// 邮箱
	
	public Office(){
		super();
	}
	
	public Office(Long id){
		this();
		this.id = id;
	}

	@Length(min=1, max=255)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=1, max=1)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Length(min=0, max=255)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min=0, max=100)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Length(min=0, max=100)
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@Length(min=0, max=200)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Length(min=0, max=200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min=0, max=100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public static void sortList(List<OfficeBO> list, List<OfficeBO> sourcelist, Long parentId){
		for (int i=0; i<sourcelist.size(); i++){
			Office e = sourcelist.get(i);
			if ( e.getParentId()!=null
					&& e.getParentId().equals(parentId)){
				OfficeBO bo = new OfficeBO();
				BeanUtils.copyProperties(e, bo);
				list.add(bo);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					Office child = sourcelist.get(j);
					if ( child.getParentId()!=null
							&& child.getParentId().equals(e.getId())){
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}

	@JsonIgnore
	public boolean isRoot(){
		return isRoot(this.id);
	}
	@JsonIgnore
	public static boolean isRoot(Long id){
		return id != null && id.equals("1");
	}
	
}