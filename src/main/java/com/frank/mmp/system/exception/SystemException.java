package com.frank.mmp.system.exception;

import com.frank.mmp.system.enums.SystemExceptionEnum;

/**
 * 系统自定义异常类
 * @author Administrator
 *
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/** 异常枚举对象 **/
	private SystemExceptionEnum exceptionEnum;
	
	public SystemException(SystemExceptionEnum exceptionEnum){
		this.exceptionEnum = exceptionEnum;
	}
	public SystemExceptionEnum getExceptionEnum() {
		return exceptionEnum;
	}
	public void setExceptionEnum(SystemExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}
	
}
