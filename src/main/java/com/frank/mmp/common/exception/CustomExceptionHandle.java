package com.frank.mmp.common.exception;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

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
		System.out.println("异常对象："+JSONObject.fromObject(ex).toString());
		Class ec = ex.getClass();
		System.out.println(ec.getName());
		Field[] ecfs = ec.getDeclaredFields();
		boolean objCheck = false;
		for(int i=0; i<ecfs.length;i++){
			if(ecfs[i].getName().endsWith("Enum")){
				objCheck = true;
			}
		}
		Map<String, Object> map = null;
		if(objCheck){
			
		} else {
			log.error("系统异常啦：",e);
			e = new CustomException(ExceptionEnum.UNKONW_EXCEPTION);
			map = EnumExceptionUtil.getEnumExceptionMsg(e.getExceptionEnum(), PROPERTIES);
		}
		mav.addObject(map);
		return mav;
	}

}
