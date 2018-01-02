package com.frank.mmp.system.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frank.mmp.common.constant.CommonConstant;
import com.frank.mmp.common.controller.BaseController;
import com.frank.mmp.common.enums.ExceptionEnum;
import com.frank.mmp.common.exception.CustomException;
import com.frank.mmp.system.bean.UserBean;
import com.frank.mmp.system.constant.SysConstant;
import com.frank.mmp.system.service.SystemService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController implements Serializable{
	
	private static final long serialVersionUID = 8186612966671004825L;
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("userSubscribe")
	public String userSubscribe(HttpServletRequest req,HttpServletResponse res){
		
		return null;
	}
	
	@RequestMapping("userUnSubscribe")
	public String userUnSubscribe(){
		
		return null;
	}
	
	@RequestMapping("userLogin")
	public String userLogin(HttpServletRequest req,HttpServletResponse res,String account,String psw)throws Exception{
		UserBean user = systemService.findUserByUserId(account, psw);
		if(null == user){
			throw new CustomException(ExceptionEnum.SYSTEM_ACCOUNT_PSW_ERROR);
		}
		req.getSession().setAttribute(SysConstant.SYSTEM_SESSION_USER_KEY, user);
		return CommonConstant.SYSTEM_MAIN_PAGE;
	}
	
	@RequestMapping("userLoginOut")
	public String userLoginOut(HttpServletRequest req,HttpServletResponse res){
		req.getSession().removeAttribute(SysConstant.SYSTEM_SESSION_USER_KEY);
		return CommonConstant.SYSTEM_LOGING_PAGE;
	}
	
}
