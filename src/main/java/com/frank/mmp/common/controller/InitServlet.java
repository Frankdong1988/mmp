package com.frank.mmp.common.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.frank.mmp.common.utils.AppContext;
import com.frank.mmp.system.service.SystemService;

/**
 * @author dgf
 * 项目初始化类
 */
public class InitServlet extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 8860980594697046057L;
	@Autowired
	private SystemService systemService;
	
	@Override
	public void init() throws ServletException {
		// TODO 获取spring实例化的Dao对象 或者写Jdbcutil 连接数据库获取menu菜单信息初始化到ServletContext容器中
		ServletContext sc = this.getServletContext();
		ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		systemService = (SystemService) AppContext.getBean("systemService");
		log.info("===================InitServlet 初始化运行,systemService实例："+systemService);
	}
	@Override
	public void destroy(){
		
	} 
}
