package com.panasign.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具方法
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-9-30
 */
public class RegularExpressionTool {
	
	/**
	 * 正则表达式匹配规则方法
	 * @param str	要进行匹配的字符串
	 * @param regularExpression	要匹配的规则格式
	 * @author Wu.Liang
	 */
	public static boolean matcherRegular(String str, String regularExpression){
		//编译生成匹配模型
		Pattern pattern = Pattern.compile(regularExpression);
		
		//匹配模型根据格式，生成比较对象
		Matcher matcher = pattern.matcher(str);
		
		//返回比较对象中的比较结果方法
		return matcher.matches();
	}
	
	/**
	 * 可包含中文的email名。
	 * 传入email地址，返回是否符合email规则的结果。
	 * 匹配规则如下：
	 * 1、“@”符号前，以非“.”开头的一个或多个字符，加上零个或一个“.”，再加上非“.”的一个或多个字符；
	 * 2、“@”符号后，以非“.”开头的一个或多个字符，加上“.”和二到三位非“.”字符（重复出现1-2次）。
	 * @param emailString
	 * @return boolean
	 * @author Wu.Liang
	 */
	public static boolean isEmailAddressCHN(String emailString){
		return matcherRegular(emailString, "[^\\.]+\\.?[^\\.]+@[^\u4E00-\u9FA5&&[^\\.]]+(\\.[^\u4E00-\u9FA5&&[^\\.]]{2,3}){1,2}");
	}
	
	/**
	 * 不可包含中文字符的email名。
	 * 传入email地址，返回是否符合email规则的结果。
	 * 匹配规则如下：
	 * 1、“@”符号前，以非“.”开头的一个或多个字符，加上零个或一个“.”，再加上非“.”的一个或多个字符；
	 * 2、“@”符号后，以非“.”开头的一个或多个字符，加上“.”和二到三位非“.”字符（重复出现1-2次）。
	 * @param emailString
	 * @return boolean
	 * @author Wu.Liang
	 */
	public static boolean isEmailAddress(String emailString){
		return matcherRegular(emailString, "[^\u4E00-\u9FA5&&[^\\.]]+\\.?[^\u4E00-\u9FA5&&[^\\.]]+@[^\u4E00-\u9FA5&&[^\\.]]+(\\.[^\u4E00-\u9FA5&&[^\\.]]{2,3}){1,2}");
	}
	
	/**
	 * 检验字符串是否包含中文
	 * @param str
	 * @return
	 * @author Wu.Liang
	 */
	public static boolean isContainsCHN(String str){
		return matcherRegular(str, ".*[\u4E00-\u9FA5&&]+.*");
	}
}
