package com.frank.mmp.common.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.frank.mmp.common.constant.CommonConstant;
import com.frank.mmp.common.enums.ExceptionEnum;
import com.frank.mmp.common.utils.EnumExceptionUtil;

import net.sf.json.JSONObject;

public class CustomExceptionHandle implements HandlerExceptionResolver{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static List<String> PROPERTIES = null;
	static{
		PROPERTIES = new ArrayList<>();
		PROPERTIES.add("code");
		PROPERTIES.add("message");
	}

	/**
	 * 系统统一异常处理
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse res, Object obj,Exception ex) {
		ModelAndView mav = new ModelAndView();
		CustomException e = null;
		if(ex instanceof CustomException){
			e = (CustomException) ex;
		} else {
			log.error("系统异常啦：",ex);
			e = new CustomException(ExceptionEnum.UNKONW_EXCEPTION);
		}
		Map<String, Object> map = EnumExceptionUtil.getEnumExceptionMsg(e.getExceptionEnum(), PROPERTIES);
		mav.addObject("map",map);
		if(null == req.getHeader("X-Requested-With") || !"XMLHttpRequest".equals(req.getHeader("X-Requested-With").trim())){
			// 判断不是为ajax异步请求,转发到登录页面
			mav.setViewName(CommonConstant.SYSTEM_LOGING_PAGE);
		} else {
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html;charset=UTF-8");
			try {
				res.getWriter().write(JSONObject.fromObject(map).toString());
			} catch (IOException e1) {
				log.error("respones设置参数异常：",e1);
			}
		}
		return mav;
	}

}
