package com.frank.mmp.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements  HandlerInterceptor{
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("================Request=============================");
		System.out.println("getContentType()："+request.getContentType());
		System.out.println("getHeaderNames()："+request.getHeaderNames());
		System.out.println("getQueryString()："+request.getQueryString());
		System.out.println("getRequestURI()："+request.getRequestURI());
		System.out.println("getRequestURL()："+request.getRequestURL());
		System.out.println("getServletPath()："+request.getServletPath());
		System.out.println("getRemoteAddr()："+request.getRemoteAddr());
		System.out.println("getRemoteHost()："+request.getRemoteHost());
		System.out.println("getContentType()："+request.getContentType());
		System.out.println("getScheme()："+request.getScheme());
		System.out.println("getServerPort()："+request.getServerPort());
		System.out.println("getHeader(\"X-Requested-With\");："+request.getHeader("X-Requested-With"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		System.out.println("==============postHandle 执行"+mav.toString());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("================Request=============================");
		System.out.println("getContentType()："+request.getContentType());
		System.out.println("getHeaderNames()："+request.getHeaderNames());
		System.out.println("getQueryString()："+request.getQueryString());
		System.out.println("getRequestURI()："+request.getRequestURI());
		System.out.println("getRequestURL()："+request.getRequestURL());
		System.out.println("getServletPath()："+request.getServletPath());
		System.out.println("getRemoteAddr()："+request.getRemoteAddr());
		System.out.println("getRemoteHost()："+request.getRemoteHost());
		System.out.println("getContentType()："+request.getContentType());
		System.out.println("getScheme()："+request.getScheme());
		System.out.println("getServerPort()："+request.getServerPort());
		System.out.println("getHeader(\"X-Requested-With\");："+request.getHeader("X-Requested-With"));
		System.out.println("================Response=============================");
		System.out.println("getContentType()："+response.getContentType());
		System.out.println("getBufferSize()："+response.getBufferSize());
		System.out.println("getCharacterEncoding()："+response.getCharacterEncoding());
		System.out.println("getLocale()："+response.getLocale());
		System.out.println("getWriter()："+response.getWriter().toString());
		
	}

}