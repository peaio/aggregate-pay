package vc.thinker.sys.bo;


import vc.thinker.sys.model.LoginLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class LoginLogBO extends LoginLog{

	private String userName;
	
	private String no;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
}