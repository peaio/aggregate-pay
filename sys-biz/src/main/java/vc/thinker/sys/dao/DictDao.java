/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicLoadData;
import com.sinco.dic.client.model.DicBase;

import vc.thinker.sys.mapper.DictMapper;
import vc.thinker.sys.model.Dict;
import vc.thinker.sys.model.DictExample;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class DictDao  {
	
	@Autowired
	private DictMapper mapper;
	
	@Autowired
	private DicContent dicContent;
	
	@PostConstruct
	public void init(){
		List<String> types=findTypeList();
		for (String type : types) {
			addCacheLoad(type);
		}
	}
	
	/**
	 * 添加缓存加载器
	 * @param type
	 */
	private void addCacheLoad(final String type){
		dicContent.setDicCache(type, new DicLoadData() {
			@Override
			public List<DicBase> loadDate() {
				List<Dict> list=findByType(type);
				return Lists.newArrayList(list.toArray(new DicBase[list.size()])) ;
			}
		});
	}
	
	public List<Dict> findAllList(){
		DictExample example=new DictExample();
		example.createCriteria().andDelFlagEqualTo(Dict.DEL_FLAG_NORMAL);
		example.setOrderByClause("sort");
		return mapper.selectByExample(example);
	}

	public List<String> findTypeList(){
		return mapper.findTypeList(Dict.DEL_FLAG_NORMAL);
	}
	
	public List<Dict> findByType(String type){
		DictExample example=new DictExample();
		example.createCriteria().andDelFlagEqualTo(Dict.DEL_FLAG_NORMAL).andTypeEqualTo(type);
		example.setOrderByClause("sort");
		return mapper.selectByExample(example);
	}

	
	public List<Dict> findAll() {
		DictExample example=new DictExample();
		return mapper.selectByExample(example);
	}

	
	public Dict get(Serializable id) {
		return mapper.selectByPrimaryKey((String)id);
	}

	
	public void save(Dict entity) {
		if(entity.getId() != null){
			update(entity);
		}else{
			insert(entity);
		}
	}

	
	public void save(List<Dict> entityList) {
		for (Dict dict : entityList) {
			save(dict);
		}
	}
	
	public List<Dict> findByPage(Page<Dict> page,String type,String description ){
		List<Dict> list=mapper.findByPage(page, type, description);
		page.setContent(list);
		return list;
	}

	
	public int deleteById(Serializable id) {
		Dict d=new Dict();
		d.setId(Long.valueOf(String.valueOf(id)));
		d.setDelFlag(Dict.DEL_FLAG_DELETE);
		return mapper.updateByPrimaryKeySelective(d);
	}

	
	public void insert(Dict entity) {
		 mapper.insert(entity);
	}

	
	public void update(Dict entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}
}
