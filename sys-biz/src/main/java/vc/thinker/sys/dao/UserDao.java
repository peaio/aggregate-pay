package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.sys.mapper.UserMapper;
import vc.thinker.sys.model.User;
import vc.thinker.sys.bo.UserBO;
import vc.thinker.sys.model.UserExample;

@Repository
public class UserDao {

	@Autowired
	private UserMapper mapper;
	
	public int selectUserCountByTime(Date time){
		UserExample example=new UserExample();
		example.createCriteria().andCreateTimeGreaterThanOrEqualTo(time);
		return mapper.countByExample(example);
	}
	
	/**
	 * 根据角色查找
	 * @param rid
	 * @param accountType
	 * @return
	 */
	public List<UserBO> findUserByRole(Long rid, String accountType){
		return mapper.findUserByRole(rid, accountType);
	}
	
	/**
	 * 根据部门查找
	 * @param officeId
	 * @param accountType
	 * @return
	 */
	public List<UserBO> findByOffice(Long officeId, String accountType){
		return mapper.findByOffice(officeId, accountType);
	}


   /** generate code begin**/
	public List<UserBO> findAll(){
		UserExample example=new UserExample();
		return mapper.selectByExample(example);
	}

	List<UserBO> findAll(Iterable<Long> ids){
		UserExample example=new UserExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserExample example=new UserExample();
		return mapper.countByExample(example);
	}

	public List<User> save(Iterable<User> entities){
		List<User> list=new ArrayList<User>();
		for (User User : entities) {
			list.add(save(User));
		}
		return list;
	}
	
	public User save(User record){
		if(record != null && record.getId() != null){
			mapper.updateByPrimaryKeySelective(record);
		}else{
			mapper.insertSelective(record);
		}
		return record;
	}
	

	public void update(User record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public UserBO findById(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		UserExample example=new UserExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(User entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<User> entities){
		List<Long> ids=Lists.newArrayList();
		for (User  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		UserExample example=new UserExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserExample example=new UserExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
