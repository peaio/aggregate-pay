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
 * 系统字典默认类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DefaultDictModel implements DicBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7485492869593317079L;
	
	private String name;
	
	private String code;
	
	
	public DefaultDictModel(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
