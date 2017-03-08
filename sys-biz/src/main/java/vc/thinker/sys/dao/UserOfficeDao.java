package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.sys.mapper.UserOfficeMapper;
import vc.thinker.sys.model.UserOffice;
import vc.thinker.sys.bo.UserOfficeBO;
import vc.thinker.sys.model.UserOfficeExample;

@Repository
public class UserOfficeDao {

	@Autowired
	private UserOfficeMapper mapper;


   /** generate code begin**/
	public List<UserOfficeBO> findAll(){
		UserOfficeExample example=new UserOfficeExample();
		return mapper.selectByExample(example);
	}

//	List<UserOfficeBO> findAll(Iterable<Long> ids){
//		UserOfficeExample example=new UserOfficeExample();
//		example.createCriteria().andIdIn(Lists.newArrayList(ids));
//		return mapper.selectByExample(example);
//	}
	
	public long count(){
		UserOfficeExample example=new UserOfficeExample();
		return mapper.countByExample(example);
	}

	public List<UserOffice> save(Iterable<UserOffice> entities){
		List<UserOffice> list=new ArrayList<UserOffice>();
		for (UserOffice UserOffice : entities) {
			list.add(save(UserOffice));
		}
		return list;
	}
	
	public UserOffice save(UserOffice record){
		
		mapper.insertSelective(record);
		
		return record;
	}
	

	public List<UserOfficeBO> findByOfficeId(java.lang.Long officeId){
		UserOfficeExample example=new UserOfficeExample();
		example.createCriteria().andOfficeIdEqualTo(officeId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByOfficeId(java.lang.Long officeId){
		UserOfficeExample example=new UserOfficeExample();
		example.createCriteria().andOfficeIdEqualTo(officeId);
		return mapper.deleteByExample(example);
	}
	public List<UserOfficeBO> findByUserId(java.lang.Long userId){
		UserOfficeExample example=new UserOfficeExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByUserId(java.lang.Long userId){
		UserOfficeExample example=new UserOfficeExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return mapper.deleteByExample(example);
	}
	/** generate code end**/
}
