package com.pbms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cjm
 * 
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
    
    private static Logger log = LoggerFactory.getLogger(DateUtils.class);
    /**
     * "yyyy-MM-dd"
     */
    private static String defaultPattern = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public final static String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm
     */
    public final static String TIME = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH
     */
    public final static String HOUR = "yyyy-MM-dd HH";
    /**
     * yyyy-MM-dd
     */
    public final static String DATE = "yyyy-MM-dd";
    /**
     * yyyy/MM/dd/
     */
    public final static String DIR = "yyyy/MM/dd/";
    /**
     * yyyyMM
     */
    public final static String OPTMONTH = "yyyyMM";
    /**
     * SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
     */
    public final static SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * SimpleDateFormat("yyyy-MM-dd HH:mm")
     */
    public final static SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * SimpleDateFormat("yyyy-MM-dd HH")
     */
    public final static SimpleDateFormat FORMAT_HOUR = new SimpleDateFormat("yyyy-MM-dd HH");
    /**
     * SimpleDateFormat("yyyy-MM-dd")
     */
    public final static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * SimpleDateFormat("yyyy/MM/dd/")
     */
    public final static SimpleDateFormat FORMAT_DIR = new SimpleDateFormat("yyyy/MM/dd/");
    /**
     * SimpleDateFormat("yyyyMM")
     */
    public final static SimpleDateFormat FORMAT_OPTMONTH = new SimpleDateFormat("yyyyMM");
    
    /**
     * 根据pattern判断字符串是否为合法日期
     * 
     * @param dateStr
     * @param pattern
     * @param flag
     *            如果参数为假,则不进行其它格式的判断
     * @return
     */
    public static boolean isValidDate(String dateStr, String pattern, boolean flag) {
	boolean isValid = false;
	// String patterns = "yyyy-MM-dd,MM/dd/yyyy";
	
	if (pattern == null || pattern.length() < 1) {
	    pattern = "yyyy-MM-dd";
	}
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    // sdf.setLenient(false);
	    String date = sdf.format(sdf.parse(dateStr));
	    if (date.equalsIgnoreCase(dateStr)) {
		isValid = true;
	    }
	} catch (Exception e) {
	    isValid = false;
	}
	// 如果目标格式不正确，判断是否是其它格式的日期
	if (flag && !isValid) {
	    isValid = isValidDatePatterns(dateStr, "");
	}
	return isValid;
    }
    
    /**
     * 根据pattern判断字符串是否为合法日期
     * 
     * @param dateStr
     * @param pattern
     * @return
     */
    public static boolean isValidDate(String dateStr, String pattern) {
	return isValidDate(dateStr, pattern, true);
    }
    
    public static boolean isValidDatePatterns(String dateStr, String patterns) {
	if (patterns == null || patterns.length() < 1) {
	    patterns = "yyyy-MM-dd;dd/MM/yyyy;yyyy/MM/dd;yyyy/M/d h:mm";
	}
	boolean isValid = false;
	String[] patternArr = patterns.split(";");
	for (int i = 0; i < patternArr.length; i++) {
	    try {
		SimpleDateFormat sdf = new SimpleDateFormat(patternArr[i]);
		// sdf.setLenient(false);
		String date = sdf.format(sdf.parse(dateStr));
		if (date.equalsIgnoreCase(dateStr)) {
		    isValid = true;
		    DateUtils.defaultPattern = patternArr[i];
		    break;
		}
	    } catch (Exception e) {
		isValid = false;
	    }
	}
	return isValid;
    }
    
    public static String getFormatDate(String dateStr, String pattern) {
	pattern = StringUtils.isNotBlank(pattern) ? pattern : defaultPattern;
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.defaultPattern);
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String date = format.format(sdf.parse(dateStr));
	    return date;
	} catch (Exception e) {
	    System.out.println("日期格转换失败.....");
	}
	return null;
    }
    
    public static String getFormatDate(Date date, SimpleDateFormat format) {
	if (format == null) {
	    format = FORMAT_TIMESTAMP;
	}
	try {
	    String strDate = format.format(date);
	    return strDate;
	} catch (Exception e) {
	    // System.out.println("日期格转换失败::::::::");
	    log.info("日期格转换失败::::::::");
	}
	return null;
    }
    
    public static String getFormatDate(Date date, String pattern) {
	pattern = StringUtils.isNotBlank(pattern) ? pattern : defaultPattern;
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    String strDate = sdf.format(date);
	    return strDate;
	} catch (Exception e) {
	    System.out.println("日期格转换失败>>>>>>>");
	}
	return null;
    }
    
    public static Date parseDate(String s) {
	DateFormat df = DateFormat.getDateInstance();
	Date myDate = null;
	try {
	    myDate = df.parse(s);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return myDate;
    }
    
    public static Date parseDate(String strDate, String pattern) {
	pattern = StringUtils.isNotBlank(pattern) ? pattern : defaultPattern;
	try {
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    Date nowDate = sdf.parse(strDate);
	    return nowDate;
	} catch (Exception e) {
	    // System.out.println(strDate+"日期解析失败！");
	    log.info(strDate + "日期解析失败！");
	}
	return null;
    }
    
    public static Date getNowDate(String pattern) {
	pattern = StringUtils.isNotBlank(pattern) ? pattern : defaultPattern;
	try {
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    String strDate = sdf.format(date);
	    Date nowDate = sdf.parse(strDate);
	    return nowDate;
	} catch (Exception e) {
	    System.out.println("无法获得当前日期！");
	}
	return null;
    }
    
    // 获取时间
    
    // private SimpleDateFormat sdf;
    @SuppressWarnings("unused")
    private static java.util.Date today;
    @SuppressWarnings("unused")
    private static String changeday;
    @SuppressWarnings("unused")
    private static Integer month;
    
    // /**
    // * 获取月份
    // */
    // public static Integer getMonth(){
    // today = new java.util.Date();
    // // sdf=new SimpleDateFormat("yyyyMM");
    // changeday=FORMAT_OPTMONTH.format(today);
    // month=Integer.parseInt(changeday);
    // return month;
    // }
    
    /**
     * 获取月份
     */
    public static Integer getMonth(java.util.Date date) {
	String changedayStr = FORMAT_OPTMONTH.format(date);
	return Integer.parseInt(changedayStr);
    }
    
    // public static void main(String[] args) {
    // System.out.println(getMonth(new Date()));
    // // boolean isd = DateUtils.isValidDate("08/09/2007", "yyyy-MM-dd");
    // // if(isd){
    // // String date = DateUtils.getFormatDate("08/09/2007", "yyyy-MM-dd");
    // // System.out.println(date);
    // // }
    // // System.out.println(date);
    //
    // DateFormat df = DateFormat.getDateInstance();
    // try {
    // Date myDate = df.parse("2007-07-09");
    // System.out.println(myDate.toString());
    // } catch (ParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }
}
