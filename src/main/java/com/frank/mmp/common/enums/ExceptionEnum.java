package com.frank.mmp.common.enums;

import com.frank.mmp.common.constant.MenuMessageCodeConstant;

public enum ExceptionEnum {
	/** ok/成功 **/
	SUCCESS(MenuMessageCodeConstant.SUCCESS,"ok"),
	/** 用户未登录或登录超时 **/
	USER_UNLOGIN_OR_LOGIN_OUTTIME(MenuMessageCodeConstant.USER_UNLOGIN_OR_LOGIN_OUTTIME,"用户未登录或登录超时"),
	/** 系统未知异常，请联系管理员 **/
	UNKONW_EXCEPTION(MenuMessageCodeConstant.UNKONW_EXCEPTION,"系统未知异常，请联系管理员"),
	/** 未查询到相关数据 **/
	SYSTEM_NO_FIND_DATA(MenuMessageCodeConstant.SYSTEM_NO_FIND_DATA,"未查询到相关数据"),
	/** 数据重复 **/
	SYSTEM_DATA_REPEAT_EXCEPTION(MenuMessageCodeConstant.SYSTEM_DATA_REPEAT_EXCEPTION,"数据重复"),
	/** 账户已存在 **/
	SYSTEM_ACCOUNT_ALREADY_EXIST(MenuMessageCodeConstant.SYSTEM_ACCOUNT_ALREADY_EXIST,"账户已存在"),
	/** 帐号或密码错误 **/
	SYSTEM_ACCOUNT_PSW_ERROR(MenuMessageCodeConstant.SYSTEM_ACCOUNT_PSW_ERROR,"帐号或密码错误"),
	/** 帐号或密码为空 **/
	SYSTEM_ACCOUNT_PSW_IS_NULL(MenuMessageCodeConstant.SYSTEM_ACCOUNT_PSW_IS_NULL,"帐号或密码为空");
	
	
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
