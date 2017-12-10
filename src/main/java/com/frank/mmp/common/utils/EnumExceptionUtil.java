package com.frank.mmp.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frank.mmp.common.enums.ExceptionEnum;


public class EnumExceptionUtil {
	private static Logger log = LoggerFactory.getLogger(EnumExceptionUtil.class);
	
	/**
	 * 获取枚举异常对象信息
	 * @param e
	 * @param properties
	 * @return
	 */
	public static Map<String, Object> getEnumExceptionMsg(Enum e,List<String> properties){
		Map<String, Object> map = new HashMap<>();
		Class enuClass = e.getClass();
		Method[] ms = enuClass.getMethods();
		for(int i=0; i<ms.length; i++){
			String name = ms[i].getName();
			if(name.startsWith("get") && properties.contains(StringUtil.charToLowerCase(name.replace("get", ""), 0))){
				try {
					Object val = ms[i].invoke(e);
					map.put(StringUtil.charToLowerCase(name.replace("get", ""), 0),val);
				} catch (Exception e1) {
					log.error("获取异常枚举对象异常：",e);
					return null;
				}
			}
		}
		return map;
	}
	
	public static void main(String[] args) {
		List<String> properties = new ArrayList<>();
		properties.add("code");
		properties.add("message");
		getEnumExceptionMsg(ExceptionEnum.UNKONW_EXCEPTION, properties);
	}
	
}
