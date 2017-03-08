package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.sys.mapper.UserRoleMapper;
import vc.thinker.sys.model.UserRole;
import vc.thinker.sys.bo.UserRoleBO;
import vc.thinker.sys.model.UserRoleExample;

@Repository
public class UserRoleDao {

	@Autowired
	private UserRoleMapper mapper;
	/**
	 * 查找是否存在该记录
	 * @param uid
	 * @param rid
	 * @return
	 */
	public long count(Long uid,Long rid){
		UserRoleExample example=new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(uid).andRoleIdEqualTo(rid);
		return mapper.countByExample(example);
	}

	
   /** generate code begin**/
	public List<UserRoleBO> findAll(){
		UserRoleExample example=new UserRoleExample();
		return mapper.selectByExample(example);
	}
	
	public List<UserRole> save(Iterable<UserRole> entities){
		List<UserRole> list=new ArrayList<UserRole>();
		for (UserRole UserRole : entities) {
			list.add(save(UserRole));
		}
		return list;
	}
	
	public UserRole save(UserRole record){
		
		mapper.insertSelective(record);
		
		return record;
	}
	

	public List<UserRoleBO> findByRoleId(java.lang.Long roleId){
		UserRoleExample example=new UserRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByRoleId(java.lang.Long roleId){
		UserRoleExample example=new UserRoleExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		return mapper.deleteByExample(example);
	}
	public List<UserRoleBO> findByUserId(java.lang.Long userId){
		UserRoleExample example=new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByUserId(java.lang.Long userId){
		UserRoleExample example=new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return mapper.deleteByExample(example);
	}

	public int delete( java.lang.Long roleId, java.lang.Long userId){
		return mapper.deleteByPrimaryKey(roleId,userId);
	}
	/** generate code end**/
}
