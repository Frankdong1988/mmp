package com.frank.mmp.system.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.frank.mmp.common.constant.CommonConstant;
import com.frank.mmp.common.enums.ExceptionEnum;
import com.frank.mmp.common.utils.EnumExceptionUtil;

import net.sf.json.JSONObject;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 判断当前请求的 URL 是否需要登录校验
		@SuppressWarnings("unchecked")
		Map<String, String> noLoginUrlMap = (Map<String, String>) request.getSession().getServletContext().getAttribute(CommonConstant.APPLICATION_NOLOGIN_URL_KEY);
		// 当前请求的url路径
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "").trim();
		if(null != noLoginUrlMap.get(requestUrl)){
			//如果当前的url属于不需要登录的范围
			return true;
		}
		// 权限拦截
		@SuppressWarnings("unchecked")
		Map<String, String> authorityMap = (Map<String, String>) request.getSession().getAttribute(CommonConstant.USER_ALLOWABLE_RESOURCE_SESSION_KEY);
		if(null != authorityMap && null != authorityMap.get(requestUrl)){
			return true;
		}
		Map<String, Object> map = EnumExceptionUtil.getEnumExceptionMsg(ExceptionEnum.USER_UNLOGIN_OR_LOGIN_OUTTIME, CommonConstant.EXCEPTION_ENUM_PROPERTY_LIST);
		if(null == request.getHeader("X-Requested-With") || !"XMLHttpRequest".equals(request.getHeader("X-Requested-With").trim())){
			// 判断不是为ajax异步请求,转发到登录页面
			request.setAttribute("map", map);
			request.getRequestDispatcher(CommonConstant.SYSTEM_LOGING_PAGE).forward(request, response);
		} else {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONObject.fromObject(map).toString());
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}
