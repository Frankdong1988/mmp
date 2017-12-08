package com.frank.mmp.common.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frank.mmp.common.constant.CommonConstant;
import com.frank.mmp.common.utils.JdbcUtil;
import com.frank.mmp.system.bean.MenuBean;

/**
 * @author dgf
 * 项目初始化类
 */
public class InitServlet extends HttpServlet {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final long serialVersionUID = 8860980594697046057L;
	
	@Override
	public void init() throws ServletException {
		try{
			// 初始化系统资源信息
			MenuBean menuBean = new MenuBean();
			String sql = "SELECT * FROM sys_menu WHERE menu_login_check = 1";
			List<MenuBean> list = JdbcUtil.select(sql, menuBean);
			Map<String, MenuBean> map = new HashMap<>();
			while(!list.isEmpty()){
				MenuBean menu = list.remove(0);
				if(null == menu.getMenuUrl() || "".equals(menu.getMenuUrl())){
					continue;
				}
				map.put(menu.getMenuUrl(), menu);
			}
			// 将资源放入全局容器中
			this.getServletContext().setAttribute(CommonConstant.APPLICATION_NOLOGIN_URL_KEY, map);
		}catch(Exception e){
			log.error("初始化系统菜单资源异常",e);
		}
		log.info("系统资源初始化完成");
	}
	@Override
	public void destroy(){
		
	} 
}
