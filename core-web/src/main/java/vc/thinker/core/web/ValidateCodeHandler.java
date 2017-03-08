package vc.thinker.core.web;

public interface ValidateCodeHandler {

	/**
	 * 是否执行验证码验证
	 * @param loginName
	 * @return
	 */
	public boolean isValidateCode(String loginName);
	
	/**
	 * 登陆成功的处理
	 * @param loginName
	 */
	public void loginSuccessHandler(String loginName);
	
	/**
	 * 登陆失败的处理
	 * @param loginName
	 */
	public void loginFailHandler(String loginName);
}
