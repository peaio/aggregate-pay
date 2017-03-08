/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.dic.client.DicContent;

import vc.thinker.core.dal.MyPage;
import vc.thinker.sys.dao.DictDao;
import vc.thinker.sys.model.Dict;
import vc.thinker.sys.utils.DictUtils;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2013-5-29
 */
@Service
@Transactional(readOnly = true)
public class DictService{

	@Autowired
	private DictDao dictDao;
	
	@Autowired
	private DicContent dicContent;
	
//	@Autowired
//	private MyBatisDictDao myBatisDictDao;
	
	public Dict get(String id) {
		// MyBatis 查询
//		return myBatisDictDao.get(id);
		// Hibernate 查询
		return dictDao.get(id);
	}
	
	public MyPage<Dict> find(MyPage<Dict> page, Dict dict) {
		// MyBatis 查询
//		dict.setPage(page);
//		page.setList(myBatisDictDao.find(dict));
//		return page;
		// Hibernate 查询
//		DetachedCriteria dc = dictDao.createDetachedCriteria();
//		if (StringUtils.isNotEmpty(dict.getType())){
//			dc.add(Restrictions.eq("type", dict.getType()));
//		}
//		if (StringUtils.isNotEmpty(dict.getDescription())){
//			dc.add(Restrictions.like("description", "%"+dict.getDescription()+"%"));
//		}
//		dc.add(Restrictions.eq(Dict.FIELD_DEL_FLAG, Dict.DEL_FLAG_NORMAL));
//		dc.addOrder(Order.asc("type")).addOrder(Order.asc("sort")).addOrder(Order.desc("id"));
//		
		 dictDao.findByPage(page,  dict.getType(), dict.getDescription());
		 return page;
	}
	
	public List<String> findTypeList(){
		return dictDao.findTypeList();
	}
	
	@Transactional(readOnly = false)
	public void save(Dict dict) {
		dictDao.save(dict);
		dicContent.cleanDic(dict.getType());
	}
	
	@Transactional(readOnly = false)
	public void delete(String id) {
		Dict dict=dictDao.get(id);
		if(dict != null){
			dictDao.deleteById(id);
			dicContent.cleanDic(dict.getType());
		}
	}
	
}
