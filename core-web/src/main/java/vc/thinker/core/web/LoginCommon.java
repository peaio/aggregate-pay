package vc.thinker.core.web;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Maps;

public class LoginCommon {
	
	public static final String VALIDATE_CODE = "validateCode";
	
	public static final String LOGIN_FAIL_MAP="loginFailMap";
	
	/**
	 * 是否是验证码登录
	 * @param useruame 用户名
	 * @param isFail 计数加1
	 * @param clean 计数清零
	 * @return
	 */
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean){
		Subject subject = SecurityUtils.getSubject();
		
		Map<String, Integer> loginFailMap = (Map<String, Integer>) subject.getSession().getAttribute(LOGIN_FAIL_MAP);
		if (loginFailMap==null){
			loginFailMap = Maps.newHashMap();
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum==null){
			loginFailNum = 0;
		}
		if (isFail){
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean){
			loginFailMap.remove(useruame);
		}
		subject.getSession().setAttribute(LOGIN_FAIL_MAP, loginFailMap);
		return loginFailNum >= 3;
	}
	
	
}
