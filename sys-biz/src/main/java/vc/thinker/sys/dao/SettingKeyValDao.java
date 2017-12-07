package vc.thinker.sys.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.dic.client.util.ReflectionUtils;

import vc.thinker.sys.bo.SettingKeyValBO;
import vc.thinker.sys.mapper.SettingKeyValMapper;
import vc.thinker.sys.model.SettingKeyVal;
import vc.thinker.sys.model.SettingKeyValExample;
import vc.thinker.sys.utils.BeanRefUtil;

@Repository
public class SettingKeyValDao {

	@Autowired
	private SettingKeyValMapper mapper;

	/**
	 * 根据 type 查找设置
	 * @param c
	 * @param type
	 * @return
	 */
	public <T> T findByType(Class<T> c,String type){
		SettingKeyValExample example=new SettingKeyValExample();
		example.createCriteria().andTypeEqualTo(type);
		List<SettingKeyValBO> list=mapper.selectByExample(example);
		
		T result=null;
		try {
			result=c.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		for (SettingKeyValBO settingKeyValBO : list) {
			String fieldName=settingKeyValBO.getKey();
			Field field=ReflectionUtils.findField(c, fieldName);
			if(field == null){
				throw new IllegalArgumentException("Class["+c+"] not find "+fieldName);
			}
			ReflectionUtils.makeAccessible(field);
			
			Class<?> fieldType=field.getType();
			if(fieldType == Long.class){
				Long val=Long.parseLong(settingKeyValBO.getValue());
				ReflectionUtils.setField(field, result, val);
			}else if(fieldType == String.class){
				ReflectionUtils.setField(field, result, settingKeyValBO.getValue());
			}else if(fieldType == Integer.class){
				Integer val=Integer.parseInt(settingKeyValBO.getValue());
				ReflectionUtils.setField(field, result, val);
			}else if(fieldType == BigDecimal.class){
				BigDecimal val=new BigDecimal(settingKeyValBO.getValue());
				ReflectionUtils.setField(field, result, val);
			}else if(fieldType == Boolean.class){
				Boolean val=Boolean.parseBoolean(settingKeyValBO.getValue());
				ReflectionUtils.setField(field, result, val);
			}
		}
		return result;
	}
	
	public <T> void saveByType(Object obj,String type){
		Map<String, String> keyValMap=BeanRefUtil.getFieldValueMap(obj);
		
		List<SettingKeyVal> modelList=Lists.newArrayList();
		keyValMap.forEach((k,v)->{
			SettingKeyVal kv=new SettingKeyVal();
			kv.setKey(k);
			kv.setValue(v);
			modelList.add(kv);
		});
		
		if(modelList.isEmpty()){
			return;
		}
		
		modelList.forEach(e -> {
			mapper.insertUpdate(e);
		});
		
	}
	
	public int delByKeysAndType(Set<String> keys,String type){
		SettingKeyValExample example=new SettingKeyValExample();
		example.createCriteria().andKeyIn(new ArrayList<>(keys)).andTypeEqualTo(type);
		return mapper.deleteByExample(example);
	}


   /** generate code begin**/
	public List<SettingKeyValBO> findAll(){
		SettingKeyValExample example=new SettingKeyValExample();
		return mapper.selectByExample(example);
	}
	
	public long count(){
		SettingKeyValExample example=new SettingKeyValExample();
		return mapper.countByExample(example);
	}

	public List<SettingKeyVal> save(Iterable<SettingKeyVal> entities){
		List<SettingKeyVal> list=new ArrayList<SettingKeyVal>();
		for (SettingKeyVal SettingKeyVal : entities) {
			list.add(save(SettingKeyVal));
		}
		return list;
	}
	
	public SettingKeyVal save(SettingKeyVal record){
		
		mapper.insertSelective(record);
		
		return record;
	}
	

	public List<SettingKeyValBO> findByKey(java.lang.String key){
		SettingKeyValExample example=new SettingKeyValExample();
		example.createCriteria().andKeyEqualTo(key);
		return mapper.selectByExample(example);
	}
	
	public int deleteByKey(java.lang.String key){
		SettingKeyValExample example=new SettingKeyValExample();
		example.createCriteria().andKeyEqualTo(key);
		return mapper.deleteByExample(example);
	}
	public List<SettingKeyValBO> findByType(java.lang.String type){
		SettingKeyValExample example=new SettingKeyValExample();
		example.createCriteria().andTypeEqualTo(type);
		return mapper.selectByExample(example);
	}
	
	public int deleteByType(java.lang.String type){
		SettingKeyValExample example=new SettingKeyValExample();
		example.createCriteria().andTypeEqualTo(type);
		return mapper.deleteByExample(example);
	}

	public int delete( java.lang.String key, java.lang.String type){
		return mapper.deleteByPrimaryKey(key,type);
	}
	public SettingKeyValBO findOne( java.lang.String key, java.lang.String type){
		return mapper.selectByPrimaryKey(key,type);
	}
	/** generate code end**/
}
