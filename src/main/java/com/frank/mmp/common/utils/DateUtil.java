/**
 * 
 */
package com.xjzx.tools.phoneSplit.utils;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 耶律齐
* 类说明
* @version 创建时间：2017年11月23日 上午9:35:22 
*/
public class DateUtil {
	private static Logger log = LoggerFactory.getLogger(DateUtil.class);
	/**
	 * 计算当前时间加减相关时间后的时间
	 * @param date2 传入的时间
	 * @param year 加减年
	 * @param day 加减天
	 * @param hour 加减小时
	 * @param minute 加减分钟
	 * @param second 加减秒
	 * @return
	 */
	private static Date timeAdd(Date date2,Integer year,Integer month,Integer day,Integer hour,Integer minute,Integer second){
		if(null == date2){
			return null;
		}
		try {
			Calendar date = Calendar.getInstance();
			date.setTime(date2);
			if(null != year && 0 != year){
				date.set(Calendar.YEAR, date.get(Calendar.YEAR) + year);
			}
			if(null != month && 0 != month){
				date.set(Calendar.MONTH, date.get(Calendar.MONTH) + month);
			}
			if(null != day && 0 != day){
				date.set(Calendar.DATE, date.get(Calendar.DATE) + day);
			}
			if(null != hour && 0 != hour){
				date.set(Calendar.HOUR_OF_DAY, date.get(Calendar.HOUR_OF_DAY) + hour);
			}
			if(null != minute && 0 != minute){
				date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) + minute);
			}
			if(null != second && 0 !=  second){
				date.set(Calendar.SECOND, date.get(Calendar.SECOND) + second);
			}
			return date.getTime();
		} catch (Exception e) {
			log.info("参数："+date2+" 计算异常："+e.getMessage());
			return null;
		}
	}
	/**
	 * 计算传入时间加减年后的时间(减法传负数)
	 * @param date
	 * @param year
	 * @return
	 */
	public static Date timeAddYear(Date date,Integer year){
		return timeAdd(date, year,null, null, null, null, null);
	}
	/**
	 * 计算传入时间加减月后的时间(减法传负数)
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date timeAddMonth(Date date,Integer month){
		return timeAdd(date, null,month, null, null, null, null);
	}
	/**
	 * 计算传入时间加减天后的时间(减法传负数)
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date timeAddDay(Date date,Integer day){
		return timeAdd(date, null,null, day, null, null, null);
	}
	/**
	 * 计算传入时间加减小时后的时间(减法传负数)
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date timeAddHour(Date date,Integer hour){
		return timeAdd(date, null,null, null, hour, null, null);
	}
	/**
	 * 计算传入时间加减分钟后的时间(减法传负数)
	 * @param date 传入的时间
	 * @param minute 加减的分钟
	 * @return
	 */
	public static Date timeAddMinute(Date date,Integer minute){
		return timeAdd(date, null,null, null, null, minute, null);
	}
	/**
	 * 计算传入时间加减秒钟后的时间(减法传负数)
	 * @param date 传入的时间
	 * @param second 加减的秒钟
	 * @return
	 */
	public static Date timeAddSecond(Date date,Integer second){
		return timeAdd(date, null,null, null, null, null, second);
	}
	
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("计算前："+date);
//		date = timeAdd(date);
		System.out.println("计算后："+date);
	}
}
