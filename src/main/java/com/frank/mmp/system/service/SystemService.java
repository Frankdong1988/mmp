package com.frank.mmp.system.service;

import java.util.List;

import com.frank.mmp.system.bean.MenuPackageBean;
import com.frank.mmp.system.bean.UserBean;

/**
 * 
 * @author Admin
 *
 */
public interface SystemService {
	
	/**
	 * 登录
	 * @param account
	 * @param psw
	 * @return
	 */
	public UserBean login(String account,String psw);
	/**
	 * 注销账户
	 * @param account
	 * @param psw
	 * @return
	 */
	public UserBean unSubscribe(String account,String psw);
	/**
	 * 注册账户
	 * @param account
	 * @param psw
	 * @param userBean
	 * @return
	 */
	public UserBean subscribe(String account,String psw,UserBean userBean);
	/**
	 * 根据用户id查询用户可操作的菜单
	 * @param userId
	 * @return
	 */
	public List<MenuPackageBean> findMenuSourceByUserId(String userId);
}
