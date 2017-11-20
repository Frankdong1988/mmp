package com.frank.mmp.common.utils;

import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author chenml Aug 1, 2011 create 提供统一的spring容器访问方式 和一些sevlet容器的获取
 */

public abstract class AppContext {
	private static final ThreadLocal curUsers = new ThreadLocal();
	private static final ThreadLocal authorKey = new ThreadLocal();
	private static final Log logger = LogFactory.getLog(AppContext.class);
	private static ApplicationContext ctx = null;
	private static ServletContext context = null;

	public static ApplicationContext getApplicationContext() {
		if (ctx == null) {
			synchronized (AppContext.class) {
				if (ctx == null) {
					logger.info("spring 容器开始加载！");
					ctx = WebApplicationContextUtils
							.getWebApplicationContext(getContext());
				}
			}
		}
		return ctx;
	}

	public static <T> T getBean(String beanName) {
		return (T) getApplicationContext().getBean(beanName);
	}

	public static ServletContext getContext() {
		return context;
	}

	public static void setContext(ServletContext context) {
		AppContext.context = context;
	}

	public static void removeCurUser() {
		curUsers.remove();
	}

	public static boolean getAuthorKey() {
		return (Boolean) authorKey.get();
	}

	public static void setAuthorKey(boolean key) {
		authorKey.set(key);
	}
}
