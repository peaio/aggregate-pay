package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.sys.mapper.RolePermissionMapper;
import vc.thinker.sys.model.RolePermission;
import vc.thinker.sys.bo.RolePermissionBO;
import vc.thinker.sys.model.RolePermissionExample;

@Repository
public class RolePermissionDao {

	@Autowired
	private RolePermissionMapper mapper;


   /** generate code begin**/
	public List<RolePermissionBO> findAll(){
		RolePermissionExample example=new RolePermissionExample();
		return mapper.selectByExample(example);
	}
	public long count(){
		RolePermissionExample example=new RolePermissionExample();
		return mapper.countByExample(example);
	}

	public List<RolePermission> save(Iterable<RolePermission> entities){
		List<RolePermission> list=new ArrayList<RolePermission>();
		for (RolePermission RolePermission : entities) {
			list.add(save(RolePermission));
		}
		return list;
	}
	
	public RolePermission save(RolePermission record){
		
		mapper.insertSelective(record);
		
		return record;
	}
	

	public List<RolePermissionBO> findByPermissionId(java.lang.Long permissionId){
		RolePermissionExample example=new RolePermissionExample();
		example.createCriteria().andPermissionIdEqualTo(permissionId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByPermissionId(java.lang.Long permissionId){
		RolePermissionExample example=new RolePermissionExample();
		example.createCriteria().andPermissionIdEqualTo(permissionId);
		return mapper.deleteByExample(example);
	}
	public List<RolePermissionBO> findByRoleId(java.lang.Long roleId){
		RolePermissionExample example=new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByRoleId(java.lang.Long roleId){
		RolePermissionExample example=new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return mapper.deleteByExample(example);
	}
	/** generate code end**/
}
