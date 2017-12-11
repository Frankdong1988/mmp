package com.frank.mmp.common.enums;

import com.frank.mmp.common.constant.MenuMessageCodeConstant;

public enum ExceptionEnum {
	SUCCESS(MenuMessageCodeConstant.SUCCESS,"ok"),
	USER_UNLOGIN_OR_LOGIN_OUTTIME(MenuMessageCodeConstant.USER_UNLOGIN_OR_LOGIN_OUTTIME,"用户未登录或登录超时"),
	UNKONW_EXCEPTION(MenuMessageCodeConstant.UNKONW_EXCEPTION,"系统未知异常，请联系管理员");
	
	
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
