/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicLoadData;
import com.sinco.dic.client.DicNameMappingHandle;
import com.sinco.dic.client.model.DicBase;

import vc.thinker.sys.bo.OfficeBO;
import vc.thinker.sys.mapper.OfficeMapper;
import vc.thinker.sys.model.Dict;
import vc.thinker.sys.model.Office;
import vc.thinker.sys.model.OfficeExample;

/**
 * 机构DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class OfficeDao{
	
	@Autowired
	private OfficeMapper mapper;
	
	@Autowired
	private DicContent dicContent;
	
	@Autowired
	private DicNameMappingHandle mappingHandle;
	
	@PostConstruct
	public void init(){
		dicContent.setDicCache(OfficeBO.class, new DicLoadData() {
			@Override
			public List<DicBase> loadDate() {
				List<OfficeBO> list=findAll();
				return Lists.newArrayList(list.toArray(new DicBase[list.size()])) ;
			}
		});
	}
	
	public List<OfficeBO> findDataScopeOfficeByUid(Long uid) {
		return mapper.findDataScopeOfficeByUid(uid);
	}
	
	public List<OfficeBO> findByRole(String roleId){
    	return mapper.findByRole(roleId);
    }
	
	public List<OfficeBO> findByParentIdsLike(String parentIds){
		OfficeExample example=new OfficeExample();
		example.createCriteria().andParentIdsLike(parentIds);
		return mapper.selectByExample(example);
	}

	
	public List<OfficeBO> findAll() {
		OfficeExample example=new OfficeExample();
		example.createCriteria().andDelFlagEqualTo("0");
		return mappingHandle.mappinHandle(mapper.selectByExample(example));
	}
	
	public OfficeBO get(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	
	public void save(Office entity) {
		if(null != entity.getId()){
			update(entity);
		}else{
			insert(entity);
		}
	}

	
	public void insert(Office entity) {
		mapper.insert(entity);
	}

	
	public void update(Office entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	
	public void save(List<Office> entityList) {
		for (Office office : entityList) {
			save(office);
		}
	}

	public int deleteById(Long id) {
		Office d=new Office();
		d.setId(id);
		d.setDelFlag(Dict.DEL_FLAG_DELETE);
		return mapper.updateByPrimaryKeySelective(d);
	}
	
	public List<OfficeBO> findByScopeFilter(){
		return mapper.findByScopeFilter();
	}
	
	public int deleteById(Long id, String likeParentIds){
		int result=deleteById(id);
		
		Office area=new Office();
		area.setDelFlag(Dict.DEL_FLAG_DELETE);
		
		OfficeExample example=new OfficeExample();
		example.createCriteria().andParentIdsLike(likeParentIds);
		result+=mapper.updateByExampleSelective(area, example);
		return result;
	}
	
	public List<OfficeBO> findAllChild(Long parentId, String likeParentIds)
	{
		return mappingHandle.mappinHandle(mapper.findAllChild(parentId, likeParentIds));
	}
	
}
