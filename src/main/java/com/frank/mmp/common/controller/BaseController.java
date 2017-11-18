package com.frank.mmp.common.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController implements Serializable {
	private static final long serialVersionUID = 4873282907611331773L;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	protected void outWriteJsonData(HttpServletResponse res,String jsonStr){
		try {
			res.setCharacterEncoding("utf-8");
			res.setContentType("application/json;charset=UTF-8");
			res.setHeader("Pragma", "No-cache");
			res.setHeader("Cache-Control", "no-cache");
			res.setDateHeader("Expires", 0);
			res.getWriter().write(jsonStr);
			res.getWriter().flush();
		} catch (IOException e) {
			log.error("输出 JSON 格式数据到客户端异常：",e);
		}
	}
}
