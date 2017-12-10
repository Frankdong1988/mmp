package com.frank.mmp.system.dao;

import java.util.List;

import javax.annotation.Resource;

import com.frank.mmp.system.bean.MenuPackageBean;
import com.frank.mmp.system.bean.UserBean;

/**
* @author 耶律齐
* @version 创建时间：2017年11月1日 下午1:38:44
* 系统数据接口层
*/
@Resource
public interface SystemDao {

	/**
	 * 查询系统用户表
	 * @param user
	 * @return
	 */
	List<UserBean> findSystemUser(UserBean user);

	/**
	 * 更新系统用户表数据
	 * @param user
	 */
	void updateSystemUserByUserId(UserBean user);

	/**
	 * 添加系统用户记录(返回自增主键id)
	 * @param userBean
	 */
	void addSystemUser(UserBean userBean);

	/**
	 * 根据用户id查询菜单表数据
	 * @param userId
	 * @return
	 */
	List<MenuPackageBean> findMenuSourceByUserId(String userId);

	
}
