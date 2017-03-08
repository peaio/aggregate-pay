package vc.thinker.sys.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 
 * <p>
 * Title: PopupAuthenticator.java
 * </p>
 * 
 * <p>
 * Description:邮件发送权限管理类
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * 
 * <p>
 * Company: 深圳市新科聚合网络技术有限公司 www.thinker.vc
 * </p>
 * 
 * @author thinker
 * 
 * @date 2015-5-20
 * 
 * @version 1.0.1 
 */
public class PopupAuthenticator extends Authenticator {
	private String username;
	private String password;

	public PopupAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}

}
