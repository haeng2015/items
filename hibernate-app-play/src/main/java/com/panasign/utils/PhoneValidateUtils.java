package com.panasign.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于验证手机号码格式是否合理的工具类<br>
 * 用法：isMobileNO(mobiles) == true 验证通过
 * @author tianyang10552
 *
 */
public class PhoneValidateUtils {
	private static final Logger log = LoggerFactory.getLogger(PhoneValidateUtils.class);
	/**
	 * 用正则表达式验证手机号码格式
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
	    boolean flag = false;
	    try {
	        //13********* ,15********,18*********
	        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	        Matcher m = p.matcher(mobiles);
	        flag = m.matches();
	        if(flag){
	        	log.info("验证的手机号码{}格式正常",mobiles);
	        }else{
	        	log.info("验证的手机号码{}格式异常",mobiles);
	        }
	    } catch (Exception e) {
	        flag = false;
	        log.info("验证的手机号码{}格式异常",mobiles);
	    }
	    return flag;
	}
	/**
	 * 用正则表达式验证手机号码格式,只要是非空的数字即可
	 * @param mobiles
	 * @return
	 */
	public static boolean isNum(String mobiles) {
	    boolean flag = false;
	    try {
	        Pattern p = Pattern.compile("^[0-9]*$");
	        Matcher m = p.matcher(mobiles);
	        flag = m.matches();
	        if(flag){
	        	log.info("验证的手机号码{}格式正常",mobiles);
	        }else{
	        	log.info("验证的手机号码{}格式异常",mobiles);
	        }
	    } catch (Exception e) {
	        flag = false;
	        log.info("验证的手机号码{}格式异常",mobiles);
	    }
	    return flag;
	}
	
	/**
	 * 测试用主方法
	 * @param args
	 */
//	public static void main(String[] args) {
//		String string = "1876715757";
//		for (int i = 0 ; i< 12 ;i++){
//			System.out.print(isMobileNO(string+i)+"\t");
//		}
//		System.out.println();
//		string = "1176715757";
//		for (int i = 0 ; i< 10 ;i++){
//			System.out.print(isMobileNO(string+i)+"\t");
//		}
//	}
}
