package vc.thinker.sys.bo;


import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;
import com.sinco.dic.client.annotation.DicMappingModel;
import com.sinco.dic.client.model.DicParentBase;

import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.User;
/**
 * 
 * BO 用于返回数据
 *
 */
@DicMappingModel
public class OfficeBO extends Office implements DicParentBase<OfficeBO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1567216449364782403L;

	private List<User> userList = Lists.newArrayList();   // 拥有用户列表
	
	private Collection<OfficeBO> childList;
	
	private OfficeBO parent;	// 父级编号
	
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public Collection<OfficeBO> getChildList() {
		return childList;
	}

	public void setChildList(Collection<OfficeBO> childList) {
		this.childList = childList;
	}

	@NotNull
	public Office getParent() {
		return parent;
	}

	public void setParent(OfficeBO parent) {
		this.parent = parent;
		if(parent != null){
			setParentId(parent.getId());
		}
	}
	
	@Override
	public String getParentCode() {
		return this.getParentId() != null ? this.getParentId().toString():null;
	}

	@Override
	public Collection<OfficeBO> getChildren() {
		return this.childList;
	}

	@Override
	public void setChildren(Collection<OfficeBO> children) {
		this.childList=children;
	}
	
}