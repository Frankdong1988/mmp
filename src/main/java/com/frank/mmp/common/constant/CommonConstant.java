/**
 * 
 */
package com.frank.mmp.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 公共静态常量类
 */
public class CommonConstant {
	/** 用户被允许的资源session key **/
	public static final String USER_ALLOWABLE_RESOURCE_SESSION_KEY = "USER_ALLOWABLE_SESSION_RESOURCE_KEY";
	/** 系统无需登录的url 全局容器key **/
	public static final String APPLICATION_NOLOGIN_URL_KEY = "APPLICATION_NOLOGIN_URL_KEY";
	/** system模块 登录页面地址 **/
	public static final String SYSTEM_LOGING_PAGE = "/system/login";
	/** system模块 登录成功跳转页面 **/
	public static final String SYSTEM_MAIN_PAGE = "/system/main";
	/** 异常枚举属性值 **/
	public static List<String> EXCEPTION_ENUM_PROPERTY_LIST = null;
	static {
		EXCEPTION_ENUM_PROPERTY_LIST = new ArrayList<>();
		EXCEPTION_ENUM_PROPERTY_LIST.add("code");
		EXCEPTION_ENUM_PROPERTY_LIST.add("message");
	}
}
