package vc.thinker.sys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.mapper.UserAccountMapper;
import vc.thinker.sys.model.User;
import vc.thinker.sys.model.UserAccount;
import vc.thinker.sys.model.UserAccountExample;

@Repository
public class UserAccountDao {

	@Autowired
	private UserAccountMapper mapper;

	/**
	 * 得到最后登入的账户
	 * 
	 * @param uid
	 * @return
	 */
	public UserAccountBO findLastLogin(Long uid) {
		return mapper.findLastLogin(uid);
	}

	public UserAccountBO findByLoginName(String loginName) {
		return findByLoginName(loginName, null, null);
	}

	public UserAccountBO findByLoginName(String loginName, Long uid, String accountType) {
		UserAccountExample example = new UserAccountExample();
		UserAccountExample.Criteria c = example.createCriteria();
		c.andLoginNameEqualTo(loginName).andIsDeletedEqualTo(false);
		if (uid != null) {
			c.andUidNotEqualTo(uid);
		}
		if (accountType != null) {
			c.andAccountTypeEqualTo(accountType);
		}
		List<UserAccountBO> list = mapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}
	
	public UserAccountBO checkLoginName(String loginName, Long uid) {
		UserAccountExample example = new UserAccountExample();
		UserAccountExample.Criteria c = example.createCriteria();
		c.andIsDeletedEqualTo(false);
		if (uid != null) {
			c.andUidNotEqualTo(uid).andLoginNameEqualTo(loginName);
		}else{
			c.andLoginNameEqualTo(loginName);
		}
		
		List<UserAccountBO> list = mapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	public UserAccountBO findByLoginName(String loginName, String accountType) {
		return findByLoginName(loginName, null, accountType);
	}

	public UserAccountBO findByUid(Long userId, String accountType) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidEqualTo(userId).andAccountTypeEqualTo(accountType);
		List<UserAccountBO> list = mapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public List<UserAccountBO> findByUid(Long userId) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidEqualTo(userId);
		return mapper.selectByExample(example);
	}

	public int updateByUidAndType(UserAccount account) 
	{
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidEqualTo(account.getUid()).andAccountTypeEqualTo(account.getAccountType());
		return mapper.updateByExampleSelective(account, example);
	}
	public int updateByUid(UserAccount account) 
	{
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidEqualTo(account.getUid());
		return mapper.updateByExampleSelective(account, example);
	}
	public int passwordUpdate(UserAccount account) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidEqualTo(account.getUid());
		return mapper.updateByExampleSelective(account, example);
	}

	public int updateByLoginName(String loginName, UserAccount account) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andLoginNameEqualTo(loginName);
		return mapper.updateByExampleSelective(account, example);
	}
	
	public int deleteByUid(java.lang.Long uid) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidEqualTo(uid);
		return mapper.deleteByExample(example);
	}

	/** generate code begin **/
	public List<UserAccountBO> findAll() {
		UserAccountExample example = new UserAccountExample();
		return mapper.selectByExample(example);
	}

	List<UserAccountBO> findAll(Iterable<Long> ids) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}

	public long count() {
		UserAccountExample example = new UserAccountExample();
		return mapper.countByExample(example);
	}

	public List<UserAccount> save(Iterable<UserAccount> entities) {
		List<UserAccount> list = new ArrayList<UserAccount>();
		for (UserAccount UserAccount : entities) {
			list.add(save(UserAccount));
		}
		return list;
	}

	public UserAccount save(UserAccount record) {
		if (record.getId() == null) {
			mapper.insertSelective(record);
		} else {
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}

	public void update(UserAccount record) {
		mapper.updateByPrimaryKeySelective(record);
	}

	public UserAccountBO findOne(java.lang.Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}

	public void delete(java.lang.Long id) {
		mapper.deleteByPrimaryKey(id);
	}
	

	public void remove(java.lang.Long id) {
		mapper.deleteByPrimaryKey(id);
	}

	public void delete(UserAccount entity) {
		mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserAccount> entities) {
		List<Long> ids = Lists.newArrayList();
		for (UserAccount entity : entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}

	public void deleteByIds(Iterable<Long> ids) {
		UserAccountExample example = new UserAccountExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		mapper.deleteByExample(example);
	}

	public void deleteAll() {
		UserAccountExample example = new UserAccountExample();
		mapper.deleteByExample(example);
	}
	/** generate code end **/
}
