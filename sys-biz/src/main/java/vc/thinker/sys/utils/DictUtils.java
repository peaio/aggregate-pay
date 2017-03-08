/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package vc.thinker.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.model.DicBase;
import com.sinco.dic.client.model.DicParentBase;
import com.sinco.dic.client.model.LangDicBase;
import com.sinco.dic.client.model.LangDicParentBase;

import vc.thinker.utils.SpringContextHolder;

/**
 * 系统字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DicContent dicContent = SpringContextHolder.getBean(DicContent.class);

	/***
	 * 将所有级别上的名称组合返回
	 * @param value
	 * @param type
	 * @param mergeChar
	 * @return
	 */
	public static String getDictFullLabel(String value, String type,String mergeChar){
		StringBuilder reslut=new StringBuilder("");
		if(mergeChar == null){
			mergeChar="";
		}
		String code=value;
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			@SuppressWarnings("rawtypes")
			DicParentBase dict=null;
			while ((dict=dicContent.getDic(type,code)) != null) {
				reslut.insert(0,mergeChar).insert(0,dict.getName());
				code=dict.getParentCode();
				if(code == null){
					break;
				}
			}
		}
		return StringUtils.stripStart(reslut.toString(), mergeChar);
	}
	/***
	 * 将所有级别上的名称组合返回
	 * @param value
	 * @param type
	 * @param mergeChar
	 * @return
	 */
	public static String getLangDictFullLabel(String value, String type,String mergeChar,String local){
		StringBuilder reslut=new StringBuilder("");
		if(mergeChar == null){
			mergeChar="";
		}
		String code=value;
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			@SuppressWarnings("rawtypes")
			LangDicParentBase dict=null;
			while ((dict=dicContent.getDic(type,code)) != null) {
				reslut.insert(0,mergeChar).insert(0,dict.getName(local));
				code=dict.getParentCode();
				if(code == null){
					break;
				}
			}
		}
		return StringUtils.stripStart(reslut.toString(), mergeChar);
	}
	
	/**
	 * 查找名称
	 * @param value
	 * @param type
	 * @param defaultValue
	 * @return
	 */
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			DicBase dict=dicContent.getDic(type, value);
			if(dict == null){
				return defaultValue;
			}else{
				return dict.getName();
			}
		}
		return defaultValue;
	}
	
	/**
	 * 查找名称
	 * @param value
	 * @param type
	 * @param defaultValue
	 * @return
	 */
	public static String getLangDictLabel(String value, String type,String local,String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			LangDicBase dict=dicContent.getDic(type, value);
			if(dict == null){
				return defaultValue;
			}else{
				return dict.getName(local);
			}
		}
		return defaultValue;
	}

	/**
	 * 查找值
	 * @param label
	 * @param type
	 * @param defaultLabel
	 * @return
	 */
	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (DicBase dict : getDictList(type)){
				if (label.equals(dict.getName())){
					return dict.getCode();
				}
			}
		}
		return defaultLabel;
	}
	
	/**
	 * 根据父得到子list
	 * @param type
	 * @param parentCode
	 * @return
	 */
	public static List<DicParentBase> getDictListByParent(String type,String parentCode){
		List<DicParentBase> list=dicContent.getDicsByParentCode(type, parentCode);
		if(list ==null){
			list=new ArrayList<DicParentBase>();
		}
		return list;
	}

	public static List<DicBase> getDictList(String type){
		return dicContent.getDics(type);
	}
	
	/**
	 * 国际化搜索
	 * @param type
	 * @param local
	 * @return
	 */
	public static List<DicBase> getLangDictList(String type,String local){
		
		List<DicBase> dicList= dicContent.getDics(type);
		
		List<DicBase> result=new ArrayList<>();
		for (DicBase dic : dicList) {
			result.add(new DefaultDictModel(((LangDicBase)dic).getName(local),dic.getCode()));
		}
		return result;
	}
	
}
