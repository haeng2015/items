package com.pbms.util;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//下面是FastJson的简介：常用的方法！
//  Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
//  public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
//  public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject    
//  public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean 
//  public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
//  public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合 
//  public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
//  public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
//  public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray（和上面方法的区别是返回值是不一样的）

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月27日
 * @说明：这是关于阿里巴巴的FastJson的一个使用Demo，在Java环境下验证的；
 */
public class FastJsonUtil {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 将Json文本数据信息转换为JsonObject对象
     * @param json
     *            = "{\"name\":\"liuzhao\"}";
     * @return
     */
    public static JSONObject jsonStrToJsonObject(String json) {
	return JSON.parseObject(json);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 将Json文本数据转换为JavaBean数据（Json文本信息中的键的名称
     *                必须和JavaBean中的字段名称一样！键中没有的在这个JavaBean中就显示为null）
     * @param json
     *            = "{\"id\":\"0375\",\"city\":\"平顶山\"}";
     */
    public static Object jsonStrToBean(String json) {
	// 一个简单方便 的方法将Json文本信息转换为JsonObject对象的同时转换为JavaBean对象！
	return JSON.parseObject(json, Object.class);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 将Map类型的数据转换为JsonString
     * @param params
     * @return
     */
    public static String MapToJsonStr(Map<String, Object> params) {
	return JSON.toJSONString(params);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO Object转换为Json字符串
     * @param obj
     * @return
     */
    public static String ObjectToJsonStr(Object obj) {
	return JSON.toJSONString(obj);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO JsonStr，转换为JSONArray再转换为JavaBean
     * @param json
     *            =
     *            "{js:[{id:\"110000\",\"city\":\"北#001京市\"},{id:\"110000\",\"city\":\"北#002京市\"}"
     *            +
     *            ",{id:\"110000\",\"city\":\"北#002京市\"},{id:\"110000\",\"city\":\"北#002京市\"},"
     *            + "{id:\"110000\",\"city\":\"#006北#005京市\"}," +
     *            "{id:\"110000\",\"city\":\"北#002京市\"}," +
     *            "{id:\"110000\",\"city\":\"北#002京市\"},{id:\"120000\",\"city\":\"天#009津市\"}]}"
     *            ;
     */
    public static List<Object> jsonStrToBeans(String json, String arr1,String arr2) {
	Object jsonArray = JSON.parseObject(json).get(arr1);
	return JSON.parseArray(jsonArray + "", Object.class);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO JavaBean转换为JSON对象
     * @param obj
     */
    public static JSONObject beanToJsonObject(Object obj) {
	return (JSONObject) JSON.toJSON(obj);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 将JSON文本转换为JavaBean的集合(首先转换为JSONArray，然后再转换为List集合)
     * @param json = "[{\"id\":\"0375\",\"city\":\"平顶山\"},{\"id\":\"0377\",\"city\":\"南阳\"}]";
     */
    public static List<Object> jsonStrToBeans(String json) {
	 return JSON.parseArray(json, Object.class);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 将json数据转化为JSONObject
     * @param json = "[{\"id\":\"0375\",\"city\":\"平顶山\"},{\"id\":\"0377\",\"city\":\"南阳\"}]";
     * @return
     */
    public static JSONObject jsonStrToJsonArrayToJsonObject(String json) {
	// 将JSON文本转换为JSONArray
	JSONArray array = JSON.parseArray(json);
	// 这行必须写：必须加上+"";不然会报出类型强转异常！
	return JSON.parseObject(array.get(1) + "");
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO JavaBean转换为Json格式的数据/Json文本
     * @param obj
     * @return
     */
    public static String beanToJsonStr(Object obj) {
	return JSON.toJSONString(obj);
    }
    
}
