package vc.thinker.core.web;

public class ResultJson 
{
	public static final String STATUCODE_1 = "1";
	public static final String STATUCODE_2 = "2";
	public static final String STATUCODE_3 = "3";
	//1(成功), 2（异常）, 3（权限异常，自动跳出登录框层）
	private String statusCode;
	//提示文本
	private String message;
	//跳转url,可选
	private String forwardUrl;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
}
