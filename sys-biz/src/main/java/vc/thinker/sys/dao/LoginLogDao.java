package vc.thinker.sys.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sinco.data.core.Page;

import vc.thinker.sys.bo.LoginLogBO;
import vc.thinker.sys.mapper.LoginLogMapper;
import vc.thinker.sys.model.LoginLog;
import vc.thinker.sys.model.LoginLogExample;
import vc.thinker.sys.vo.LoginLogVO;

/**
 * 
 * 数据访问接口
 *
 */
@Repository
public class LoginLogDao {
	
	@Autowired
	private LoginLogMapper mapper;

	
	public int deleteById(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	
	public int insert(LoginLog record) {
		return mapper.insertSelective(record);
	}

	
	public LoginLog findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	
	public int updateById(LoginLog record) {
		return mapper.updateByPrimaryKey(record);
	}

	
	public List<LoginLog> findByVO(LoginLogVO vo) {
		LoginLogExample example=new LoginLogExample();
		return mapper.selectByExample(example);
	}
	
	public List<LoginLogBO> findByPage(Page<LoginLogBO> page,Map<String, Object> param){
		   List<LoginLogBO> list= mapper.findByPage(page, param);
		   page.setContent(list);
		   return list;
	}
}
