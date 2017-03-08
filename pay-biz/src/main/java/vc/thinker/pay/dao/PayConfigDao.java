package vc.thinker.pay.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicLoadData;
import com.sinco.dic.client.model.DicBase;

import vc.thinker.pay.bo.PayConfigBO;
import vc.thinker.pay.mapper.PayConfigMapper;
import vc.thinker.pay.model.PayConfig;
import vc.thinker.pay.model.PayConfigExample;

@Repository
public class PayConfigDao{

	@Autowired
	private PayConfigMapper mapper;
	
	public PayConfigBO findByMark(String mark){
		PayConfigExample example=new PayConfigExample();
		example.createCriteria().andMarkEqualTo(mark);
		List<PayConfigBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public PayConfigBO findByMark(String mark,Boolean install){
		PayConfigExample example=new PayConfigExample();
		PayConfigExample.Criteria c=example.createCriteria();
		c.andMarkEqualTo(mark);
		if(install != null){
			c.andInstallEqualTo(install);
		}
		List<PayConfigBO> list=mapper.selectByExample(example);
		return list.size() > 0 ? list.get(0):null;
	}
	
	public List<PayConfigBO> findByMark(String [] notMarks,Boolean install){
		PayConfigExample example=new PayConfigExample();
		PayConfigExample.Criteria c=example.createCriteria();
		c.andMarkNotIn(Lists.newArrayList(notMarks));
		if(install != null){
			c.andInstallEqualTo(install);
		}
		return mapper.selectByExample(example);
	}

	public List<PayConfigBO> findInstalledAll(){
		PayConfigExample example=new PayConfigExample();
		example.createCriteria().andInstallEqualTo(true);
		return mapper.selectByExample(example);
	}

   /** generate code begin**/
	public List<PayConfigBO> findAll(){
		PayConfigExample example=new PayConfigExample();
		return mapper.selectByExample(example);
	}
	List<PayConfigBO> findAll(Iterable<Long> ids){
		PayConfigExample example=new PayConfigExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PayConfigExample example=new PayConfigExample();
		return mapper.countByExample(example);
	}

	public List<PayConfig> save(Iterable<PayConfig> entities){
		List<PayConfig> list=new ArrayList<PayConfig>();
		for (PayConfig PayConfig : entities) {
			list.add(save(PayConfig));
		}
		return list;
	}
	
	public PayConfig save(PayConfig record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PayConfig record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PayConfigBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		PayConfigExample example=new PayConfigExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PayConfig entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PayConfig> entities){
		List<Long> ids=Lists.newArrayList();
		for (PayConfig  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		PayConfigExample example=new PayConfigExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PayConfigExample example=new PayConfigExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
