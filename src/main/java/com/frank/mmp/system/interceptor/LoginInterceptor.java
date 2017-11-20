package com.frank.mmp.system.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.frank.mmp.common.context.ApplicationCommon;
import com.frank.mmp.common.enums.ExceptionEnum;
import com.frank.mmp.common.utils.EnumExceptionUtil;

import net.sf.json.JSONObject;

public class LoginInterceptor implements  HandlerInterceptor{
	@SuppressWarnings("unused")
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object obj = request.getSession().getAttribute(ApplicationCommon.USER_SESSION_KEY);
		if(null != obj){
			// 用户已登录，或该请求在非登录要求的名单中，拦截放行
			return true;
		}
		// 判断当前请求的 URL 是否需要登录校验
		@SuppressWarnings("unchecked")
		Map<String, String> noLoginUrlMap = (Map<String, String>) request.getSession().getServletContext().getAttribute(ApplicationCommon.APPLICATION_NOLOGIN_URL_KEY);
		// 请求的url路径
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "").trim();
		if(null != noLoginUrlMap.get(requestUrl)){
			//如果当前的url属于不需要登录的范围
			return true;
		}
		Map<String, Object> map = EnumExceptionUtil.getEnumExceptionMsg(ExceptionEnum.USER_UNLOGIN_OR_LOGIN_OUTTIME, ApplicationCommon.EXCEPTION_ENUM_PROPERTY_LIST);
		if(null == request.getHeader("X-Requested-With") || !"XMLHttpRequest".equals(request.getHeader("X-Requested-With").trim())){
			// 判断不是为ajax异步请求,转发到登录页面
			request.setAttribute("map", map);
			request.getRequestDispatcher(ApplicationCommon.SYSTEM_LOGING_PAGE).forward(request, response);
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