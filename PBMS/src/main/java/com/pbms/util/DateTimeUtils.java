package com.pbms.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年4月6日
 * @说明：日期时间格式处理工具
 */
public class DateTimeUtils {
    /**
     * @author：He.hp
     * @date：2017年3月8日
     * @function：TODO 字符串转Timestamp格式方法1（格式：yyyy-MM-dd）
     * @param date
     * @return
     */
    public static Timestamp stringToTimestamp1(String timestampStr) {
	try {
	    if (timestampStr != null && !"".equals(timestampStr)) {
		return new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(timestampStr).getTime());
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月8日
     * @function：TODO 字符串转Timestamp格式方法2(传入的字符串必须为yyyy-MM-dd hh:mm:ss格式)
     * @param date
     * @return
     */
    public static Timestamp stringToTimestamp2(String timestampStr) {
	try {
	    if (timestampStr != null && !"".equals(timestampStr)) {
		return Timestamp.valueOf(timestampStr);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月20日
     * @function：TODO 将字符串转为Timestamp格式，并为日期加一天（格式：yyyy-MM-dd）
     * @param date
     * @return
     */
    public static Timestamp StringTotimestampAddOneDay(String timestampStr) {
	try {
	    if (timestampStr != null && !"".equals(timestampStr)) {
		Calendar calendar = new GregorianCalendar();
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timestampStr);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1); // 把日期往后增加一天.整数往后推,负数往前移动
		
		return new Timestamp(calendar.getTime().getTime());
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO string转date（格式：yyyy-MM-dd）
     * @param date
     * @return
     */
    public static Date stringToDate(String dateStr) {
	try {
	    if (dateStr != null && !"".equals(dateStr)) {
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO string转date,并加天数（格式：yyyy-MM-dd）
     * @param date
     * @return
     */
    public static Date stringToDateAddDay(String dateStr, Integer day) {
	try {
	    if (dateStr != null && !"".equals(dateStr)) {
		Calendar calendar = new GregorianCalendar();
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day); // 把日期往后增加day天.整数往后推,负数往前移动
		
		return calendar.getTime();
	    }
	} catch (ParseException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月8日
     * @function：TODO Timestamp转特定格式字符串（格式：yyyy-MM-dd）
     * @param time
     * @return
     */
    public static String timestampToString(Timestamp time) {
	try {
	    if (time != null && !"".equals(time)) {
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO date转字符串(格式：yyyy-MM-dd)
     * @param date
     * @return
     */
    public static String DateToString(Date date) {
	try {
	    if (date != null && !"".equals(date)) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
}
