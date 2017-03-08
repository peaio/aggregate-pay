package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vc.thinker.core.dal.MyPage;
import vc.thinker.sys.bo.TemplateBO;
import vc.thinker.sys.mapper.TemplateMapper;
import vc.thinker.sys.model.Template;
import vc.thinker.sys.model.TemplateExample;
import vc.thinker.sys.vo.TemplateVO;

import com.google.common.collect.Lists;

@Repository
public class TemplateDao {

	@Autowired
	private TemplateMapper mapper;
	
	/**
	 * 
	 * @param mark
	 * @return
	 */
	public TemplateBO findByMark(String mark){
		TemplateExample example=new TemplateExample();
		example.createCriteria().andMarkEqualTo(mark);
		List<TemplateBO> list=mapper.selectByExample(example);
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


   /** generate code begin**/
	public List<TemplateBO> findAll(){
		TemplateExample example=new TemplateExample();
		return mapper.selectByExample(example);
	}
	
	public long count(){
		TemplateExample example=new TemplateExample();
		return mapper.countByExample(example);
	}
	
	public List<TemplateBO> verifyMark(Long id,String mark){
		TemplateExample example=new TemplateExample();
		if(null != id){
			example.createCriteria().andDeleteStatusEqualTo(0)
			.andIdNotEqualTo(id).andMarkEqualTo(mark);
		}else{
			example.createCriteria().andDeleteStatusEqualTo(0)
			.andMarkEqualTo(mark);
		}
		
		return mapper.selectByExample(example);
	}

	public List<Template> save(Iterable<Template> entities){
		List<Template> list=new ArrayList<Template>();
		for (Template Template : entities) {
			list.add(save(Template));
		}
		return list;
	}
	
	public Template save(Template record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Template record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public TemplateBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public TemplateBO get(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}


	public boolean exists(java.lang.Long id){
		TemplateExample example=new TemplateExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}


	List<TemplateBO> findAll(Iterable<Long> ids){
		TemplateExample example=new TemplateExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Template entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Template> entities){
		List<Long> ids=Lists.newArrayList();
		for (Template  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		TemplateExample example=new TemplateExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		TemplateExample example=new TemplateExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	
	public List<TemplateBO> selectListByVO(MyPage<TemplateBO> page,TemplateVO vo){
		return this.mapper.selectListByVO(page,vo);
	}
}
