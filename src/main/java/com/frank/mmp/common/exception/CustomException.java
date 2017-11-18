package com.frank.mmp.common.exception;

import com.frank.mmp.common.enums.ExceptionEnum;

/**
 * 系统自定义异常类
 * @author Administrator
 *
 */
public class CustomException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 异常枚举对象 **/
	private ExceptionEnum exceptionEnum;
	
	public CustomException(ExceptionEnum exceptionEnum){
		this.exceptionEnum = exceptionEnum;
	}
	public ExceptionEnum getExceptionEnum() {
		return exceptionEnum;
	}
	public void setExceptionEnum(ExceptionEnum exceptionEnum) {
		this.exceptionEnum = exceptionEnum;
	}
	
}
