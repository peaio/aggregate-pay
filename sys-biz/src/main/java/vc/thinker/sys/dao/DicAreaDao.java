package vc.thinker.sys.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicLoadData;
import com.sinco.dic.client.model.DicBase;

import vc.thinker.sys.bo.DicAreaBO;
import vc.thinker.sys.mapper.DicAreaMapper;
import vc.thinker.sys.model.DicArea;
import vc.thinker.sys.model.DicAreaExample;

/**
 * 
 * 数据访问接口
 *
 */
@Repository
@Lazy(false)
public class DicAreaDao {
	

	@Autowired
	private DicAreaMapper mapper;
	
	@Autowired
	private DicContent dicContent;
	
	@PostConstruct
	public void init(){
		dicContent.setDicCache(DicAreaBO.class, new DicLoadData() {
			@Override
			public List<DicBase> loadDate() {
				List<DicAreaBO> areas=findToCacheData();
				return Lists.newArrayList(areas.toArray(new DicBase[areas.size()])) ;
			}
		});
	}

	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int insert(DicArea record) {
		return mapper.insert(record);
	}

	public DicArea findById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	public int updateById(DicArea record) {
		return mapper.updateByPrimaryKey(record);
	}
	
	public List<DicAreaBO> findAll(){
		DicAreaExample example=new DicAreaExample();
		return mapper.selectByExample(example);
	}
	
	public List<DicAreaBO> findToCacheData() {
		return findAll();
	}
}
