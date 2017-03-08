/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinco.data.core.Page;

import vc.thinker.sys.bo.LogBO;
import vc.thinker.sys.mapper.LogMapper;
import vc.thinker.sys.model.Log;
import vc.thinker.sys.model.LogExample;

/**
 * 日志DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class LogDao {

	
	@Autowired
	private LogMapper mapper;
	
	
	public List<Log> findAll() {
		LogExample example=new LogExample();
		return mapper.selectByExample(example);
	}

	
	public Log get(Serializable id) {
		return mapper.selectByPrimaryKey((String) id);
	}

	
	public void save(Log entity) {
		if(StringUtils.isNotBlank(entity.getId())){
			update(entity);
		}else{
			insert(entity);
		}
	}

	
	public void insert(Log entity) {
		mapper.insert(entity);
	}

	
	public void update(Log entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	
	public void save(List<Log> entityList) {
		for (Log log : entityList) {
			save(log);
		}
	}

	
	public int deleteById(Serializable id) {
		return mapper.deleteByPrimaryKey((String) id);
	}
	
	
    public List<LogBO> findByPage(Page<LogBO> page,Map<String, Object> param){
	   List<LogBO> list= mapper.findByPage(page, param);
	   page.setContent(list);
	   return list;
    }

}
