/**
 *
 */
package com.panasign.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: DateTimeUtils
 * @Description
 * @Author tangzheng
 * @Date 2013-2-23 上午09:31:11
 * @Copyright: 版权归 HundSun 所有
 */
public class DateTimeUtils {
	public static final int MILLISECOND = 1;
	public static final int SECOND = MILLISECOND * 1000;
	public static final int MINUTE = SECOND * 60;
	public static final int HOUR = MINUTE * 60;
	public static final int DAY = HOUR * 24;
	public static final int WEEK = DAY * 7;
	public static final String CHINESE_DATE_FORMAT = "yyyy-MM-dd";
	public static final String CHINESE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 字符串转换成日期类型
	 * @param dateString
	 * @param formatString	传null或者""，就为默认"yyyy-MM-dd"。如需要精度，建议传入"yyyy-MM-dd HH:mm:ss"
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String dateString, String formatString) {
		formatString = StringUtils.isBlank(formatString) ? CHINESE_DATE_FORMAT : formatString;
		SimpleDateFormat dateFmt = new SimpleDateFormat(formatString);
		try {
			return dateFmt.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期转换成字符串类型
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String dateToString(Date date, String formatString) {
		formatString = StringUtils.isBlank(formatString) ? CHINESE_DATE_FORMAT : formatString;
		SimpleDateFormat dateFmt = new SimpleDateFormat(formatString);
		return dateFmt.format(date);
	}

	/**
	 * 格式化时间YYYY-MM-DD
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String formatDateTime(Date date, String formatString) {
		formatString = StringUtils.isBlank(formatString) ? CHINESE_DATE_FORMAT : formatString;
		SimpleDateFormat dateFmt = new SimpleDateFormat(formatString);
		return dateFmt.format(date);
	}

	/**
	 * 格式化时间YYYY-MM-DD HH:NN:SS
	 * @param date
	 * @param formatString
	 * @return
	 */
	public static String formatFullDateTime(Date date, String formatString) {
		formatString = StringUtils.isBlank(formatString) ? CHINESE_DATE_FORMAT : formatString;
		SimpleDateFormat dateFmt = new SimpleDateFormat(formatString);
		return dateFmt.format(date);
	}

	/**
	 * 给指定的时间添加天数或减少天数
	 * 
	 * @param date
	 * @param addDay
	 *            正数代表后几日，负数代表前几日
	 * @return
	 * 
	 */
	public static Date addDaysByDateTime(Date date, int addDay) {
		return new Date(date.getTime() + addDay * DAY);
	}

	/**
	 * 取下一天
	 */
	public static Date getNextDay(Date currentDate) {
		return addDaysByDateTime(currentDate, 1);
	}

	/**
	 * 取上一天
	 */
	public static Date getLastDay(Date currentDate) {
		return addDaysByDateTime(currentDate, -1);
	}

	/**
	 * 取n个月之后的时间，如果n为负数，则是n个月之前的时间
	 * @param currentDate
	 * @param offset
	 * @return
	 * @author Wu.Liang
	 */
	public static Date getMonthByOffset(Date currentDate, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 取当月月初
	 */
	public static Date getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);// 当前月的第一天
		return cal.getTime();
	}

	/**
	 * 取当月月底
	 */
	public static Date getLastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);// 当前月的最后一天
		cal.roll(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return cal.getTime();
	}
	
	/**
	 * 根据某一日期得该日期的当月的第一天
	 * @param day
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * 得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		return cal.getTime();
	}
	
	/**
	 * 获取某日凌晨日期
	 * 
	 * @param date
	 * @flag true 返回yyyy-MM-dd 00:00:00日期<br>
	 *       false 返回yyyy-MM-dd 23:59:59日期
	 * @return
	 */
	public static Date getDatetime(Date date, boolean flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		// 时分秒（毫秒数）
		long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
		// 凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

		if (!flag) {
			// 凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
		}
		return cal.getTime();
	}

	/**
	* 是否闰年
	* @param year 年
	* @return
	*/
	public boolean isLeapYear(int year) {
	    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	/**
	 * 从String转成timestamp的基本方法
	 * @param dateStr
	 * @param format
	 * @return
	 * @author: Wu.Liang
	 */
	public static Timestamp stringToTimestampBase(String dateStr,String format){
		String tag = (format==null || "".equals(format))?"yyyy-MM-dd HH:mm:ss":format;
		SimpleDateFormat sdf = new SimpleDateFormat(tag);
		Calendar cal = Calendar.getInstance();
		   try {
		    Date date = sdf.parse(dateStr);
		    date.getTime();
		    cal.setTime(date);
		    return new Timestamp(cal.getTimeInMillis());
		   } catch (ParseException e) {
		    e.printStackTrace();
		   }
		 
		   cal.setTime(new Date());
		   return new Timestamp(cal.getTimeInMillis());
	}
	
	/**
	 * String 到Timestamp转换，"yyyy-MM-dd HH:mm:ss"格式
	 * @param dateStr
	 * @return
	 * @author: Wu.Liang
	 */
	public static Timestamp stringToTimestamp(String dateStr){
		 return stringToTimestampBase(dateStr,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * String 到Timestamp转换，"yyyy-MM-dd"格式
	 * @param dateStr
	 * @return
	 * @author: Wu.Liang
	 */
	public static Timestamp stringToTimestampDate(String dateStr){
		return stringToTimestampBase(dateStr,"yyyy-MM-dd");
	}
	
	/**
	 * 获取当前时间的秒数字符串，后面自动加6位随机数字
	 * @return
	 * @author Wu.Liang
	 */
	public static String getCurrentTimeString(){
		Date d = new Date();
		Random random = new Random();
		String str = d.getTime() + "" + random.nextInt(4) + random.nextInt(5) + random.nextInt(6) + random.nextInt(7) + random.nextInt(8) + random.nextInt(9 );
		return str;
	}
	
	/**
	 * 取当前日期的后n个月，如果n为负数，则为前n个月
	 * @param month
	 * @return
	 * @author Wu.Liang
	 */
	public static Date getDateByRollMonths(int n){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
	public static String dateToStringByFormat(Date date, String format){
		SimpleDateFormat df=null;
		if(null==format || "".equals(format)){
			df = new SimpleDateFormat(CHINESE_DATE_FORMAT);// HH:mm:ss
		}else{
			df = new SimpleDateFormat(format);// HH:mm:ss
		}
		return df.format(date);
	}
	
	/**
	 * Timestamp To String  default:"yyyy-MM-dd HH:mm:ss"
	 * @param time
	 * @param format
	 * @return String 
	 * @author Liu.ruxing
	 */
	public static String  timestampToStringByFormat(Timestamp time, String format){
		SimpleDateFormat df=null;
		if(null==format || "".equals(format)){
			df = new SimpleDateFormat(CHINESE_DATETIME_FORMAT);// HH:mm:ss
		}else{
			df = new SimpleDateFormat(format);// HH:mm:ss
		}
		return df.format(time);
	}
	
	/**系统时间转String(格式：20150101122001******);
	 * @return  
	 * @author Liu.ruxing
	 */
	public static String getSystemTimeToString() {
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	    String dateString = formatter.format(currentTime);
	    Random random = new Random();
	    return dateString+random.nextInt(4) + random.nextInt(5) + random.nextInt(6) + random.nextInt(7) + random.nextInt(8) + random.nextInt(9 );
	}
	
	/** 日期格式转换
	  * @param dateFormat
	  * @param millSec
	  * @return
	  * @author: Liu.ruxing
	  */
	public static String LongToDate(String dateFormat,Long millSec){
	     SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	     Date date= new Date(millSec);
	            return sdf.format(date);
	    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Long(41234).intValue());
		// String oneDay = "2012-09-03";
		// System.out.println(DateTimeUtils.dateToString(DateTimeUtils
		// .getNextDay(DateTimeUtils.stringToDate(oneDay, "")), ""));
		//
		// System.out.println(DateTimeUtils.dateToString(
		// DateTimeUtils.addDaysByDateTime(
		// DateTimeUtils.stringToDate(oneDay, ""), -2), ""));
		//
		// String oneMonth = "2012-01";
		// //
		// System.out.println(DateTimeUtils.dateToString(DateTimeUtils.getLastMonth(DateTimeUtils.stringToDate(oneMonth,"yyyy-MM")),"yyyy-MM"));
		// System.out.println(DateTimeUtils.dateToString(
		// DateTimeUtils.getMonthByOffset(
		// DateTimeUtils.stringToDate(oneMonth, "yyyy-MM"), -3),
		// "yyyy-MM"));
//		System.out.println(getDatetime(getFirstDayOfMonth(2014, 3), false));
		
//		Date date = getDateByRollMonths(-12);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
//		System.out.println(df.format(date));
//		Date date = getMonthByOffset(new Date(),0);
//		System.out.println(dateToStringByFormat(date,null));
	}

}
