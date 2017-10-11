/**
 * 
 */
package com.panasign.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StringUtils
 * @Description: 封装基本的字符串操作，继承自apache StringUtils
 * @Author TangZheng
 * @Date 2012-2-25 上午10:36:55
 * @Copyright: 版权归 HundSun 所有
 */
public final class StringUtils extends org.apache.commons.lang.StringUtils{
	
	/**
	 * 验证指定的字符串是否包含在包含逗号分隔符的字符串序列内
	 * @param parentStr 包含逗号分隔符的字符串序列
	 * @param childStr 指定的字符串
	 * @return
	 */
	public static boolean containString(String parentStr, String childStr) {
		List<String> listStr = Arrays.asList(parentStr.split(","));//StringTokenizer
		listStr = trimList(listStr);
		return listStr.contains(childStr);
	}
	
	/**
	 * 验证指定的字符串是否包含在包含逗号分隔符的字符串序列内，忽略字符串大小写
	 * @param parentStr 包含逗号分隔符的字符串序列
	 * @param childStr 指定的字符串
	 * @return
	 */
	public static boolean containStringIgnoreCase(String parentStr, String childStr) {
		List<String> listStr = Arrays.asList(parentStr.split(","));//StringTokenizer
		listStr = trimList(listStr);
		listStr = toCaseList(listStr, true);
		return listStr.contains(childStr.toUpperCase());
	}
	
	/**
	 * 将目标字符串列表内的字符串忽略前导空白和尾部空白
	 * @param listStr 待处理的字符串列表
	 * @return 经处理的字符串列表
	 */
	public static List<String> trimList(List<String> listStr) {
		List<String> tgtList = new ArrayList<String>();
		for(String s: listStr) {
			if(StringUtils.isNotBlank(s)) tgtList.add(s.trim());
		}
		return tgtList;
	}
	
	/**
	 * 将目标字符串数组内的字符串忽略前导空白和尾部空白
	 * @param strArr 待处理的字符串数组
	 * @return 经处理的字符串数组
	 */
	public static String[] trimArray(String[] strArr) {
		List<String> tarList = new ArrayList<String>();
		for(String s: strArr) {
			if(StringUtils.isNotBlank(s)) tarList.add(s.trim());
		}
		return tarList.toArray(new String[0]);
	}
	
	/**
	 *  将目标字符串数组内的字符串忽略前导空白和尾部空白并在前后增加sequ标识符
	 * @param strArr
	 * @param sequ
	 * @return
	 */
	public static String[] trimArray(String[] strArr, CharSequence sequ) {
		List<String> tarList = new ArrayList<String>();
		for(String s: strArr) {
			if(StringUtils.isNotBlank(s)) tarList.add(sequ+s.trim()+sequ);
		}
		return tarList.toArray(new String[0]);
	}
	
	/**
	 * @param listStr 待转换的字符串列表
	 * @param toUpperCase true将listStr内字符串全部转换为大写，否则全部小写
	 * @return 经转换的字符串列表
	 */
	public static List<String> toCaseList(List<String> listStr, boolean toUpperCase) {
		List<String> target = new ArrayList<String>();
		for(String s: listStr) {
			if(toUpperCase) {
				target.add(s.toUpperCase());
			} else {
				target.add(s.toLowerCase());
			}
		}
		return target;
	}
	
	/**
	 * 将指定字符串转换为UTF-8编码格式的字符串
	 * @param codeStr 待转换的字符串
	 * @return
	 */
	public static String transcode(String codeStr) {
        return transcode(codeStr, "iso-8859-1", "utf-8");
	}
	
	/**
	 * 
	 * @param codeStr
	 * @param decodeCharset
	 * @param encodeCharset
	 * @return
	 */
    public static String transcode(String codeStr, String decodeCharset, String encodeCharset) {
        try{
            //encodeUrl = new String(URLEncoder.encode(url, "UTF-8"));
            return new String(codeStr.getBytes(decodeCharset), encodeCharset);
         } catch (UnsupportedEncodingException e) {
         }
         return "";
    }
    
    /**
     * 从Map中提取键值对拼装URL参数
     * @param urlMap
     * @return
     */
    public static String assembleyURLParam(Map<String, String> urlMap) {
        String urlParamStr = "";
        if(urlMap!=null && urlMap.size()>0) {
            StringBuilder builder = new StringBuilder("?");
            for(Map.Entry<String,String> map:urlMap.entrySet()) {
                builder.append(map.getKey()).append("=").append(map.getValue()).append("&");
            }
            urlParamStr = builder.toString().substring(0, builder.lastIndexOf("&"));
        }
        return urlParamStr;
    }
    
    /**
     * 将字符串中某些字符替换成其他字符
     * @param str 目标字符串
     * @param target 替换的目标
     * @param replacement 想要替换成的
     * @return 
     * @author: Wu.Liang
     */
    public static String replaceSomeChar(String str,String target, String replacement){
    	return str.replace(target, replacement);
    }
    
    /**
     * 将list字符串数组集合，根据tag拼成一串，比如tag=‘，’，结果串为“'string1'，'string2'，'string3'”,
     * 此方法适用于sql中：“select * from xxx where x in (适用于此处);”
     * @param list
     * @param tag 做分隔符的字符
     * @return
     * @author: Wu.Liang
     */
    public static String parseListToStringForSQL(Collection<String> collection){
    	StringBuffer sb = new StringBuffer("");
    	if(collection!=null && collection.size()>0){
    		Iterator<String> it = collection.iterator();
    		int count = 0;
    		//循环取出容器中的字符串
    		while (it.hasNext()) {
    			String str = it.next();
    			count++;
    			if(count >= collection.size()){
    				sb.append(str);
    			}else{
    				sb.append(str);
    				sb.append(",");
    			}
			}
    	}
    	return sb.toString();
    }
    
    /**
     * 将List<String>数组直接转成  “str01,str02,str03...” 格式
     * @param list
     * @return
     * @author Wu.Liang
     */
    public static String listToString(List<String> list){
    	if(list==null || list.size()==0){
    		return null;
    	}
    	StringBuffer buffer = new StringBuffer("");
    	for(int i =0; i<list.size(); i++){
    		if(i==list.size()-1){
    			buffer.append(list.get(i));
    		}else{
    			buffer.append(list.get(i)).append(",");
    		}
    	}
    	return buffer.toString();
    }
    
    public static void main(String[] args) {
    	List<String> list = new ArrayList<String>();
    	list.add("A00001");
    	list.add("A00002");
    	list.add("A00003");
    	list.add("A00006");
    	System.out.println(parseListToStringForSQL(list));
    }

}
