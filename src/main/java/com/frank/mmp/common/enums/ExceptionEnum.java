package com.frank.mmp.common.enums;

public enum ExceptionEnum {
	SYSTEM_UNKONW_EXCEPTION("406","系统错误，请联系管理员"),
	USER_UNLOGIN_OR_LOGIN_OUTTIME("301","用户未登录或登录超时"),
	SUCCESS("0","ok");
	
	private String code;
	private String message;
	
	private ExceptionEnum(String code,String message){
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
