package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.sys.mapper.PermissionMapper;
import vc.thinker.sys.model.Permission;
import vc.thinker.sys.bo.PermissionBO;
import vc.thinker.sys.model.PermissionExample;

@Repository
public class PermissionDao {

	@Autowired
	private PermissionMapper mapper;

	/**
	 * 根据用户查找
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findByUid(Long uid,Integer userType){
		return mapper.findByUid(uid,userType);
	}
	/**
	 * 根据角色查找
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findByRid(Long rid){
		return mapper.findByRid(rid);
	}
	/**
	 * 根据用户类型查找
	 * @param uid
	 * @return
	 */
	public List<PermissionBO> findPermByUserType(Integer userType){
		PermissionExample example=new PermissionExample();
		example.createCriteria().andUserTypeEqualTo(userType);
		return mapper.selectByExample(example);
	}
	
	public List<PermissionBO> findByNotValues(List<String> vals,Integer userType){
		PermissionExample example=new PermissionExample();
		PermissionExample.Criteria c=example.createCriteria();
		c.andUserTypeEqualTo(userType);
		if(!vals.isEmpty()){
			c.andValueNotIn(vals);
		}
		return mapper.selectByExample(example);
	}
	
	public int deleteByNotValues(List<String> vals,Integer userType){
		PermissionExample example=new PermissionExample();
		PermissionExample.Criteria c=example.createCriteria();
		c.andUserTypeEqualTo(userType);
		if(!vals.isEmpty()){
			c.andValueNotIn(vals);
		}
		return mapper.deleteByExample(example);
	}
	
   /** generate code begin**/
	public List<PermissionBO> findAll(){
		PermissionExample example=new PermissionExample();
		return mapper.selectByExample(example);
	}

	List<PermissionBO> findAll(Iterable<Long> ids){
		PermissionExample example=new PermissionExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PermissionExample example=new PermissionExample();
		return mapper.countByExample(example);
	}

	public List<Permission> save(Iterable<Permission> entities){
		List<Permission> list=new ArrayList<Permission>();
		for (Permission Permission : entities) {
			list.add(save(Permission));
		}
		return list;
	}
	
	public Permission save(Permission record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Permission record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PermissionBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		PermissionExample example=new PermissionExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Permission entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Permission> entities){
		List<Long> ids=Lists.newArrayList();
		for (Permission  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		PermissionExample example=new PermissionExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PermissionExample example=new PermissionExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
