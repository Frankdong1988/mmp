package com.frank.mmp.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.mmp.system.bean.MenuPackageBean;
import com.frank.mmp.system.bean.UserBean;
import com.frank.mmp.system.dao.SystemDao;

/**
* @author 耶律齐
* @version 创建时间：2017年11月1日 上午10:21:12
* 系统service实现层
*/
@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao systemDao;

	/* (non-Javadoc)
	 * @see com.frank.mmp.system.service.SystemService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public UserBean login(String account, String psw) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.frank.mmp.system.service.SystemService#unSubscribe(java.lang.String, java.lang.String)
	 */
	@Override
	public UserBean unSubscribe(String account, String psw) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.frank.mmp.system.service.SystemService#subscribe(java.lang.String, java.lang.String, com.frank.mmp.system.bean.UserBean)
	 */
	@Override
	public UserBean subscribe(String account, String psw, UserBean userBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuPackageBean> findMenuSourceByUserId(String userId) {
//		List<MenuPackageBean> list = systemDao.findMenuSourceByUserId(userId);
//		setMenuToTreeType(list,null,null);
		return null;
	}


}
