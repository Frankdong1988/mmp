package com.frank.mmp.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.mmp.common.utils.MD5Util;
import com.frank.mmp.system.bean.MenuPackageBean;
import com.frank.mmp.system.bean.UserBean;
import com.frank.mmp.system.dao.SystemDao;
import com.frank.mmp.system.enums.SystemExceptionEnum;
import com.frank.mmp.system.exception.SystemException;

/**
* @author 耶律齐
* @version 创建时间：2017年11月1日 上午10:21:12
* 系统service实现层
*/
@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao systemDao;

	@Override
	public UserBean findUserByUserId(String account, String psw) {
		if(null == account || "".equals(account.trim()) || null == psw || "".equals(psw.trim())){
			throw new SystemException(SystemExceptionEnum.SYSTEM_ACCOUNT_PSW_IS_NULL);
		}
		psw = MD5Util.encrypt(psw);
		UserBean user = new UserBean();
		user.setuAccount(account);
		user.setuPsw(psw);
		List<UserBean> userList = systemDao.findSystemUser(user);
		if(null == userList || userList.isEmpty()){
			return null;
		}
		return userList.get(0);
	}

	@Override
	public void unSubscribe(String account, String psw) {
		// 用户注销规则：在用户的account账户后面加'*'
		if(null == account || "".equals(account.trim()) || null == psw || "".equals(psw.trim())){
			throw new SystemException(SystemExceptionEnum.SYSTEM_ACCOUNT_PSW_IS_NULL);
		}
		psw = MD5Util.encrypt(psw);
		UserBean user = new UserBean();
		user.setuAccount(account);
		user.setuPsw(psw);
		List<UserBean> userList = systemDao.findSystemUser(user);
		if(null == userList || userList.isEmpty()){
			throw new SystemException(SystemExceptionEnum.SYSTEM_NO_FIND_DATA);
		}
		if(userList.size() > 1){
			throw new SystemException(SystemExceptionEnum.SYSTEM_DATA_REPEAT_EXCEPTION);
		}
		user.setuId(userList.get(0).getuId());
		user.setuAccount(userList.get(0).getuAccount()+"*");
		systemDao.updateSystemUserByUserId(user);
	}

	@Override
	public UserBean subscribe(String account, String psw, UserBean userBean) {
		if(null == account || "".equals(account.trim()) || null == psw || "".equals(psw.trim())){
			throw new SystemException(SystemExceptionEnum.SYSTEM_ACCOUNT_PSW_IS_NULL);
		}
		UserBean user = new UserBean();
		user.setuAccount(account);
		List<UserBean> userList = systemDao.findSystemUser(user);
		if(null != userList && !userList.isEmpty()){
			throw new SystemException(SystemExceptionEnum.SYSTEM_ACCOUNT_ALREADY_EXIST);
		}
		if(null == userBean){
			userBean = user;
		}
		psw = MD5Util.encrypt(psw);
		userBean.setuAccount(account);
		userBean.setuPsw(psw);
		// 添加用户操作要求返回自动生成的主键
		systemDao.addSystemUser(userBean);
		return userBean;
	}

	@Override
	public List<MenuPackageBean> findMenuSourceByUserId(String userId) {
		List<MenuPackageBean> list = systemDao.findMenuSourceByUserId(userId);
		List<MenuPackageBean> menuTree = MenuPackageBean.formatTree(list);
		return menuTree;
	}


}
