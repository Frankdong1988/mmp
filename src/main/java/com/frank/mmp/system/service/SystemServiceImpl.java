package com.frank.mmp.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	 * @see com.frank.mmp.system.service.SystemService#testMapper()
	 */
	@Override
	public void testMapper() {
		
	}

	
}
