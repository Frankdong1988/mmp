package com.frank.mmp.common.utils;

public class StringUtil {
	/**
	 * 指定字符大写
	 * @param name
	 * @param i 指定字符的下标，从0开始
	 * @return 如果指定位置的字母本身为大写，将直接返回原字符串
	 */
	public static String charToUpperCase(String name,int i){
		char[] cs=name.toCharArray();
		if(i>=cs.length){
			throw new RuntimeException("下标越界");
		}
		if(!String.valueOf(cs[i]).matches("^[a-zA-Z]$")){
			throw new RuntimeException("指定字符不属于英文字母范围");
		}
		if(((int)cs[i]) > 64 && ((int)cs[i]) < 91){
			return name;
		}
        cs[i]-=32;
        return String.valueOf(cs);
	}
	/**
	 * 指定字符小写
	 * @param name
	 * @param i 指定字符的下标，从0开始
	 * @return 如果指定位置的字母本身为小写，将直接返回原字符串
	 */
	public static String charToLowerCase(String name,int i){
		char[] cs=name.toCharArray();
		if(i>=cs.length){
			throw new RuntimeException("下标越界");
		}
		if(!String.valueOf(cs[i]).matches("^[a-zA-Z]$")){
			throw new RuntimeException("指定字符不属于英文字母范围");
		}
		if(((int)cs[i]) > 96 && ((int)cs[i]) < 123){
			return name;
		}
		cs[i]+=32;
		return String.valueOf(cs);
	}
	
	
	public static void main(String[] args) {
		System.out.println(charToLowerCase("A2cDEFS",2));
	}
}
