package com.panasign.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文本替换器
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2016-3-18
 */
public class Replacer {
	/**
	 * 匹配模型	“以（一个或多个）〈p〉\t\t\t\t\t\t\t\t\t\t\t\t\〈/p〉开头，或以（一个或多个）〈p〉\t\t\t\t\t\t\t\t\t\t\t\t〈/p〉结尾，其中（\t数量为10~12个）”
	 */
	private static Pattern PATTERN_HTML_NULL_ROW_P = Pattern.compile("^((<p>\t{10,999}</p>)+)|((<p>\t{10,999}</p>)+)$");
	/**
	 * 匹配器	for “以（一个或多个）〈p〉\t\t\t\t\t\t\t\t\t\t\t\t\〈/p〉开头，或以（一个或多个）〈p〉\t\t\t\t\t\t\t\t\t\t\t\t〈/p〉结尾，其中（\t数量为10~12个）”
	 */
	private static Matcher MATCHER_HTML_NULL_ROW_P = null;
	
	/**
	 * 传入一个html文本，将文本中在首位的〈p〉\t\t\t\t\t\t\t\t\t\t\t\t\〈/p〉空行p段落全部替换成 ""
	 * @param htmlContent	html内容
	 * @return StringBuffer
	 * @author Wu.Liang
	 */
	public static StringBuffer parseHTMLnullRowP(String htmlContent){
		MATCHER_HTML_NULL_ROW_P = PATTERN_HTML_NULL_ROW_P.matcher(htmlContent);
		StringBuffer sb = new StringBuffer();
		 while (MATCHER_HTML_NULL_ROW_P.find()) {
			 MATCHER_HTML_NULL_ROW_P.appendReplacement(sb, "");
		 }
		 MATCHER_HTML_NULL_ROW_P.appendTail(sb);
		 return sb;
	}
}
