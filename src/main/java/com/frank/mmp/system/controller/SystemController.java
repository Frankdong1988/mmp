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

import com.frank.mmp.common.controller.BaseController;
import com.frank.mmp.system.service.SystemService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("system")
public class SystemController extends BaseController implements Serializable{
	
	private static final long serialVersionUID = 8186612966671004825L;
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("testMapper")
	public void testMapper(HttpServletRequest request,HttpServletResponse resp,Model model){
		System.out.println("=======================");
		System.out.println("testMapper 已执行");
		System.out.println("systemService实例："+systemService);
		Map<String, String> map = new HashMap<String, String>();
		map.put("code", "0");
		map.put("msg", "ok");
		model.addAttribute(map);
		super.outWriteJsonData(resp, JSONObject.fromObject(map).toString());
	}
	
	@RequestMapping("testVoid")
	public void testVoid(HttpServletRequest request,HttpServletResponse resp,ModelAndView mav){
		System.out.println("=======================");
		System.out.println("testVoid 已执行");
		System.out.println("systemService实例："+systemService);
		request.setAttribute("data", mav);
	}
	
	@RequestMapping("testStr")
	public String testStr(HttpServletRequest request,HttpServletResponse resp,ModelAndView mav){
		System.out.println("=======================");
		System.out.println("testStr 已执行");
		System.out.println("systemService实例："+systemService);
		return null;
	}
	
	@RequestMapping("testPage")
	public String testPage(HttpServletRequest request,HttpServletResponse resp,ModelAndView mav){
		System.out.println("=======================");
		System.out.println("testPage 已执行");
		System.out.println("systemService实例："+systemService);
		return "main";
	}
	
}
