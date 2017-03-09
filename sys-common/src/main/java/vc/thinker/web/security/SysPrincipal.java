package vc.thinker.web.security;

import vc.thinker.core.security.BasePrincipal;
import vc.thinker.sys.model.User;

public class SysPrincipal implements BasePrincipal{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 基本user
	 */
	private User user;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 用户登入账户
	 */
	private String loginName;
	
	/**
	 * 用户登入ip
	 */
	private String host;

	public SysPrincipal(User user,String loginName,String userName,String host) {
		this.loginName = loginName;
		this.userName=userName;
		this.host=host;
		this.user=user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String getLoginName() {
		return loginName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String getId() {
		return this.getUser().getId().toString();
	}
	
}
