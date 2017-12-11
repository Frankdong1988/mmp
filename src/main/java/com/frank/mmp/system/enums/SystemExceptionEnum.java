package com.frank.mmp.system.enums;

import com.frank.mmp.common.constant.MenuMessageCodeConstant;

/**
 * @author Administrator
 *
 */
public enum SystemExceptionEnum {
	SYSTEM_USER_UNLOGIN_OR_LOGIN_OUTTIME(MenuMessageCodeConstant.SYSTEM_USER_UNLOGIN_OR_LOGIN_OUTTIME,"用户未登录或登录超时"),
	SYSTEM_NO_FIND_DATA(MenuMessageCodeConstant.SYSTEM_NO_FIND_DATA,"未查询到数据"),
	SYSTEM_ACCOUNT_ALREADY_EXIST(MenuMessageCodeConstant.SYSTEM_ACCOUNT_ALREADY_EXIST,"账户名重复"),
	SYSTEM_DATA_REPEAT_EXCEPTION(MenuMessageCodeConstant.SYSTEM_DATA_REPEAT_EXCEPTION,"数据重复异常"),
	SYSTEM_ACCOUNT_PSW_IS_NULL(MenuMessageCodeConstant.SYSTEM_ACCOUNT_PSW_IS_NULL,"帐号或密码为空");
	
	
	private String code;
	private String message;
	
	private SystemExceptionEnum(String code,String message){
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
