package com.frank.mmp.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * MD5
 * 
 * @author gyh
 * 
 */
public class MD5Util {
	private static Logger log = Logger.getLogger(MD5Util.class);
	public static MD5Util md5Util;

	public static String code(String str) {
		String hs = "";
		try {
			MessageDigest alga;
			String myinfo = str;
			alga = MessageDigest.getInstance("MD5");
			alga.update(myinfo.getBytes());
			byte[] digesta = alga.digest();
			String stmp = "";
			for (int n = 0; n < digesta.length; n++) {
				stmp = (java.lang.Integer.toHexString(digesta[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
		} catch (Exception e) {
			log.error("code error", e);
			throw new RuntimeException(e);
		}
		return hs.toUpperCase();
	}

	/**
	 * 重要信息进行部分加密
	 * @param msg 要处理的字符串
	 * @param encryptLength加密字符串长度
	 * @param type
	 *            begin从开头加密<br>
	 *            end从结尾加密<br>
	 *            其他从中间加密
	 * @return
	 */
	public String encryptImportMsg(String msg, int encryptLength, String type) {
		String newChar = "*";
		String result = "";
		if (null != msg && !"".equals(msg.trim()) && encryptLength > 0) {
			int length = msg.length();
			if ("begin".equals(type)) {
				if (encryptLength > length) {
					for (int i = 0; i < length; i++) {
						result += newChar;
					}
				} else {
					for (int i = 0; i < encryptLength; i++) {
						result += newChar;
					}
					for (int i = encryptLength; i < length; i++) {
						result += msg.substring(i, i + 1);
					}
				}
			} else if ("end".equals(type)) {
				if (encryptLength > length) {
					for (int i = 0; i < length; i++) {
						result += newChar;
					}
				} else {
					for (int i = 0; i < encryptLength; i++) {
						result += newChar;
					}
					for (int i = (length - encryptLength) - 1; i >= 0; i--) {
						result = msg.substring(i, i + 1) + result;
					}
				}
			} else {
				if (encryptLength >= length) {
					for (int i = 0; i < length; i++) {
						result += newChar;
					}
				} else {
					int i = 0;
					int inter = length - encryptLength;
					int multiple = inter / 2;
					int residue = inter % 2;
					int beginIndex = 0;
					int endIndex = 0;
					if (length < encryptLength + 2) {
						while (i < length) {
							if (i == 0) {
								result = msg.substring(0, 1);
							} else {
								result += newChar;
							}
							i++;
						}
					} else {
						if (residue == 0) {
							beginIndex = multiple;
							endIndex = encryptLength + multiple - 1;
						} else {
							beginIndex = multiple + residue;
							endIndex = encryptLength + beginIndex - 1;
						}
						while (i < length) {
							if (i < beginIndex || i > endIndex) {
								result += msg.substring(i, i + 1);
							} else {
								result += newChar;
							}
							i++;
						}
					}
				}
			}
		} else {
			result = msg;
		}
		return result;
	}

	public static MD5Util getInstance() {
		if (md5Util == null) {
			md5Util = new MD5Util();
		}
		return md5Util;
	}

	/**
	 * md5,32位小写
	 */
	public static String encrypt(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("utf-8");

			byte[] md5Bytes = md5.digest(byteArray);

			StringBuffer hexValue = new StringBuffer();

			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}

			return hexValue.toString();
		} catch (Exception e) {
			log.error("MD5加密异常",e);
			throw new RuntimeException("MD5加密异常");
		}
	}

	public static void main(String[] args) throws ParseException,
			NoSuchAlgorithmException {
	}
}
