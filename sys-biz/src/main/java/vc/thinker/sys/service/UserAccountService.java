package vc.thinker.sys.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinco.common.security.PasswordUtil;

import vc.thinker.sys.bo.UserAccountBO;
import vc.thinker.sys.dao.UserAccountDao;
import vc.thinker.sys.model.UserAccount;

@Service
@Transactional(readOnly = true)
public class UserAccountService {

	private Logger logger = LoggerFactory.getLogger(UserAccountService.class);

	@Autowired
	private UserAccountDao userAccountDao;
	
	public UserAccountBO findLastLogin(Long uid){
		return userAccountDao.findLastLogin(uid);
	}

	public UserAccountBO findByLoginName(String loginName) {
		return userAccountDao.findByLoginName(loginName);
	}
	
	public UserAccountBO findByLoginName(String loginName,Long uid) {
		return userAccountDao.findByLoginName(loginName,uid,null);
	}

	public UserAccountBO findByLoginName(String loginName, String accountType) {
		return userAccountDao.findByLoginName(loginName, accountType);
	}

	public UserAccountBO findByUid(Long uid,String acountType) {
		return userAccountDao.findByUid(uid,acountType);
	}
	
	public List<UserAccountBO> findByUid(Long userId) {
		return userAccountDao.findByUid(userId);
	}
	
	public UserAccountBO findOne(Long uid) {
		return userAccountDao.findOne(uid);
	}
	
	public UserAccountBO checkLoginName(String loginName, Long uid) {
		return userAccountDao.checkLoginName(loginName, uid);
	}
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public int passwordUpdate(Long userId, String password) {
		return updatePassword(userId, password);
	}
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updatePassword(Long userId, String password) {
		UserAccount account = new UserAccount();
		account.setPassword(PasswordUtil.entryptPassword(password));
		account.setUpdateTime(new Date());
		account.setUid(userId);
		return userAccountDao.passwordUpdate(account);
	}
	
	/**
	 * 
	 * @Title: updateLoginName 
	 * @Description: TODO(修改用户名) 
	 * @param @param userId
	 * @param @param password
	 * @param @param accountType
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @author Tang
	 * @throws
	 */
	@Transactional(readOnly = false)
	public int updateLoginName(Long userId, String loginName, String accountType) {
		UserAccount account = new UserAccount();
		account.setLoginName(loginName);;
		account.setUpdateTime(new Date());
		account.setUid(userId);
		account.setAccountType(accountType);
		account.setIsDeleted(false);
		return userAccountDao.updateByUidAndType(account);
	}

	public UserAccount setUserAccount(Long userId, String mobile, String password, String accountType, boolean sDeleted) {
		UserAccount account = new UserAccount();
		account.setUid(userId);
		account.setLoginName(mobile);
		account.setPassword(PasswordUtil.entryptPassword(password));
		account.setAccountType(accountType);
		account.setIsDeleted(sDeleted);
		account.setCreateTime(new Date());
		userAccountDao.save(account);
		return account;
	}

}
