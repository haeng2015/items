/**
 * Copyright 2015-2025. All rights reserved. Powered by panasign.
 */
package com.panasign.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;


/**
 * 数据转义
 * 
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Liu.ruxing
 * @createDate: 2015-7-6
 */
public class ControlDataUtils {
	
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

	/**
	 * HttpServletRequest 参数值 获取
	 * 
	 * @param request
	 *            页面请求
	 * @param param
	 *            请求的参数
	 * @return
	 * @author: Liu.ruxing
	 */
	public static String getRequestParameter(HttpServletRequest request,
			String param) {
		if (request != null && request.getParameter(param)!=null) {
			return request.getParameter(param).replaceAll(" ", "");
		}
		return "";
	}

	/**
	 * String 转Timestamp 工具
	 * 
	 * @param str
	 * @return
	 * @author: Liu.ruxing
	 */
	public static Timestamp strToTimestamp(String str) {

		Timestamp ts = null;
		try {
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	 /** 日期格式转换
	  * @param dateFormat
	  * @param millSec
	  * @return
	  * @author: Liu.ruxing
	  */
	public static String transferLongToDate(String dateFormat,Long millSec){
	     SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	     Date date= new Date(millSec);
	            return sdf.format(date);
	    }
	
	/**数组转 String 
	 * @param boCompanyDynamicIds
	 * @return
	 */
	public static String arrayToString(String[] boCompanyDynamicIds){
		StringBuilder sb= new StringBuilder();
		if(boCompanyDynamicIds==null || boCompanyDynamicIds.equals("")){
			return "";
		}
		if(boCompanyDynamicIds.length > 0){
			for (int i = 0; i < boCompanyDynamicIds.length; i++) {
				sb.append("'"+boCompanyDynamicIds[i]+"',");
			}
			return sb.toString().substring(0,sb.toString().lastIndexOf(","));
		}else{
			return "";
		}
	}
	
	/**数组转 String 
	 * @param boCompanyDynamicIds
	 * @return
	 */
	public static List<String> arrayToList(String[] boCompanyDynamicIds){
		StringBuilder sb= new StringBuilder();
		List<String> list= new ArrayList<String>();
		if(boCompanyDynamicIds==null || boCompanyDynamicIds.equals("")){
			return null;
		}
		if(boCompanyDynamicIds.length > 0){
			for (int i = 0; i < boCompanyDynamicIds.length; i++) {
				sb.append("'"+boCompanyDynamicIds[i]+"',");
				list.add(boCompanyDynamicIds[i]);
			}
			
			return list;
		}else{
			return null;
		}
	}
	
	/**Long类型判断
	 * @param str
	 * @return
	 */
	public static boolean isValidLong(String str) {
		try {
			long _v = Long.parseLong(str);
			if (_v == 0) {
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

