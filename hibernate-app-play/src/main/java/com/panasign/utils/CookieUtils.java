package com.panasign.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


/**
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-10-17
 */
public class CookieUtils {
	public final static int WEEK = 60 * 60 * 24 * 7;
	public final static int MONTH = 60 * 60 * 24 * 30;
	public final static int YEAR = 60 * 60 * 24 * 365;
	
	/**
	 * 添加cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param timeToLive 保存cookie的秒数
	 * @author Wu.Liang
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int timeToLive){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(timeToLive);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
}
