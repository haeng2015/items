package com.panasign.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 验证身份证号码 身份证号码, 可以解析身份证号码的各个字段，
 * 以及验证身份证号码是否有效; 身份证号码构成：6位地址编码+8位生日+3位顺序码+1位校验码
 * 地址编码只验证前两位<br>
 * 用法  IdCardValidateUtils.Validate(idcard)== IdCardValidateUtils.SUCCESS 时身份证验证通过
 * @author tianyang
 * 
 */
public class IdCardValidateUtils {
	private static final Logger log = LoggerFactory.getLogger(IdCardValidateUtils.class);
	
	public final static String SUCCESS = "身份证验证通过"; 
	private final static String BIRTH_DATE_FORMAT = "yyyyMMdd"; // 身份证号码中的出生日期的格式
	private final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L); // 身份证的最小出生日期,1900年1月1日
	private final static int NEW_CARD_NUMBER_LENGTH = 18;
	private final static int OLD_CARD_NUMBER_LENGTH = 15;
	private final static char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' }; // 18位身份证中最后一位校验码
	private final static int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };// 18位身份证中，各个数字的生成校验码时的权值
	
	private static String cardNumber;                           // 完整的身份证号码
	private static HashMap<Integer, String> area = new HashMap<Integer, String>();//地区码以及它对应的值
	static{
		      area.put(11,"北京"); area.put(12,"天津");   area.put(13,"河北"); 
		      area.put(14,"山西"); area.put(15,"内蒙古"); area.put(21,"辽宁");
		      area.put(22,"吉林"); area.put(23,"黑龙江"); area.put(31,"上海");
		      area.put(32,"江苏"); area.put(33,"浙江");   area.put(34,"安徽");
		      area.put(35,"福建"); area.put(36,"江西");   area.put(37,"山东");
		      area.put(41,"河南"); area.put(42,"湖北");   area.put(43,"湖南");
		      area.put(44,"广东"); area.put(45,"广西");   area.put(46,"海南");
		      area.put(51,"四川"); area.put(52,"贵州");   area.put(53,"云南");
		      area.put(54,"西藏"); area.put(61,"陕西");   area.put(62,"甘肃"); 
		      area.put(53,"云南"); area.put(63,"青海");   area.put(64,"宁夏");
		      area.put(65,"新疆"); area.put(71,"台湾");   area.put(81,"香港");
		      area.put(82,"澳门"); area.put(91,"国外");
	}
	/**
	 * 身份证验证的入口方法
	 * @param idCard
	 * @return
	 * @throws ParseException 
	 */
	public static String Validate(String idCard) throws ParseException{
		idCard = idCard.trim();
		Boolean result = false;
		    //*********************身份证字符长度检验**************************************
		if( OLD_CARD_NUMBER_LENGTH == idCard.length() || NEW_CARD_NUMBER_LENGTH == idCard.length()){
			if (OLD_CARD_NUMBER_LENGTH == idCard.length()) { //将15位的身份证号码转换为18位的
				cardNumber = contertToNewCardNumber(idCard);
			}else{
				cardNumber = idCard;
			}
			//*********************身份证字符格式检验**************************************
			for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {//前17位
				char ch = cardNumber.charAt(i);
				result = ch >= '0' && ch <= '9';
				if(result==false){
					break;
				}
			}
			if(result){
				result = calculateVerifyCode(cardNumber) == cardNumber.charAt(NEW_CARD_NUMBER_LENGTH - 1);// 身份证号的第18位校验正确
				if(result){
					//*********************身份证字符生日日期检验**************************************
					if(!validateBirthDay()){
						log.info("{}身份证验证失败,身份证生日异常",cardNumber);
						return "身份证生日异常";
					}else if(!validateAreaCode()){
						log.info("{}身份证验证失败,身份证地区码异常",cardNumber);
						return "身份证地区码异常";
					}
				}else{
					log.info("{}身份证验证失败,身份证格式错误-校验码位错误",cardNumber);
					return "身份证格式错误-校验码位错误";
				}
			}else{
				log.info("{}身份证验证失败,身份证格式错误-前17位",cardNumber);
				return "身份证格式错误-前17位";
			}
		}else{
			log.info("{}身份证验证失败,身份证长度异常",idCard);
			return "身份证长度异常";
		}
		log.info("{}身份证验证成功",cardNumber);
		return SUCCESS;
	}

	/**
	 * 把15位身份证号码转换到18位身份证号码<br>
	 * 15位身份证号码与18位身份证号码的区别为：<br>
	 * 15位身份证号码中，"出生年份"字段是2位，转换时需要补入"19"，表示20世纪<br>
	 * 15位身份证无最后一位校验码。18位身份证中，校验码根据根据前17位生成
	 * 320311   77 0706 001
	 * 320311 1977 0706 001+校验码
	 * @param cardNumber
	 * @return
	 */
	private static String contertToNewCardNumber(String oldCardNumber) {
		StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
		buf.append(oldCardNumber.substring(0, 6));
		buf.append("19");
		buf.append(oldCardNumber.substring(6));
		buf.append(IdCardValidateUtils.calculateVerifyCode(buf));
		return buf.toString();
	}
	
	/**
	 * 校验码（第十八位数）：
	 * 十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0...16 ，先对前17位数字的权求和；
	 * Ai:表示第i位置上的身份证号码数字值 
	 * Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2; 
	 * 计算模 Y = mod(S, 11)< 通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 
	 * 校验码: 1 0 X 9 8 7 6 5 4 3 2
	 * @param cardNumber
	 * @return
	 */
	private static char calculateVerifyCode(CharSequence cardNumber) {
		int sum = 0;
		for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
			char ch = cardNumber.charAt(i);
			sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
		}
		return VERIFY_CODE[sum % 11];
	}
	
	/**
	 * 验证身份证生日是否正常
	 * @return
	 * @throws ParseException 
	 */
	private static Boolean validateBirthDay() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(BIRTH_DATE_FORMAT);
		Date birthDate = format.parse(cardNumber.substring(6, 14));
		if(null == birthDate){
			return false;
		}else if( !birthDate.before(new Date()) ){
			return false;
		}else if( !birthDate.after(MINIMAL_BIRTH_DATE) ){
			return false;
		}else{
			/*
			 * 出生日期中的年、月、日必须正确,比如月份范围是[1,12],日期范围是[1,31]，
			 * 还需要校验闰年、大月、小月的情况时,月份和日期相符合
			 */
			String birthDay = cardNumber.substring(6, 14);
			String realBirthDay = format.format(birthDate);
			if( !birthDay.equals(realBirthDay)){
				return false;
			}
		}	
		return true;
	}
	
	/**
	 * 验证地区码是否正常
	 * @return
	 */
	private static boolean  validateAreaCode() {
		Integer code = Integer.parseInt(cardNumber.substring(0, 2));
		return area.get(code) != null;
	}

	
	/**
	 * 测试代码
	 * @param args
	 */
//	public static void main(String[] args) {
//		String str = "30031177070600";
//		for (int i = 0; i < 10; i++) {
//			try {
//				System.out.println((str+i)+"\t"+IdCardValidateUtils.Validate(str+i) );
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
////		str = "52252919880225221";
//		str = "11052119890306191";
//		for (int i = 0; i < 10; i++) {
//			try {
//				System.out.println((str+i)+"\t"+IdCardValidateUtils.Validate(str+i) );
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
