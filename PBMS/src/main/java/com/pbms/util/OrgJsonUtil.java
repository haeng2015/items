package com.pbms.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月28日
 * @说明：1:将JavaBean转换成Map、JSONObject 2:将Map转换成Javabean 3:将JSONObject转换成Map、Javabean
 * 	(基于org.json的jar包中json与bean对象转换的工具类)
 */
public class OrgJsonUtil {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 将Javabean转换为Map
     * @param javaBean
     * @return Map对象
     */
    public static Map<Object, Object> beanToMap(Object javaBean) {
	
	Map<Object, Object> result = new HashMap<Object, Object>();
	Method[] methods = javaBean.getClass().getDeclaredMethods();
	
	for (Method method : methods) {
	    try {
		if (method.getName().startsWith("get")) {
		    
		    String field = method.getName();
		    field = field.substring(field.indexOf("get") + 3);
		    field = field.toLowerCase().charAt(0) + field.substring(1);
		    
		    Object value = method.invoke(javaBean, (Object[]) null);
		    
		    result.put(field, null == value ? "" : value.toString());  //不能对布尔型变量映射
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 将Json对象转换成Map
     * @param jsonString
     * @return Map对象
     * @throws JSONException
     */
    public static Map<Object, Object> jsonObjToMap(String jsonString) throws JSONException {
	
	JSONObject jsonObject = new JSONObject(jsonString);
	
	Map<Object, Object> result = new HashMap<Object, Object>();
	Iterator<String> iterator = jsonObject.keys();
	String key = null;
	String value = null;
	
	while (iterator.hasNext()) {
	    key = (String) iterator.next();
	    value = jsonObject.getString(key);
	    result.put(key, value);
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 将JavaBean转换成JSONObject（通过Map中转）
     * @param bean
     * @return json对象
     */
    public static JSONObject beanToJsonObj(Object bean) {
	
	return new JSONObject(beanToMap(bean));
	
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO 将Map转换成Javabean对象
     * @param javabean
     * @param data Map数据
     * @return
     */
    public static Object mapToBean(Object javabean, Map<Object, Object> data) {
	
	Method[] methods = javabean.getClass().getDeclaredMethods();
	for (Method method : methods) {
	    
	    try {
		if (method.getName().startsWith("set")) {
		    
		    String field = method.getName();
		    field = field.substring(field.indexOf("set") + 3);
		    field = field.toLowerCase().charAt(0) + field.substring(1);
		    method.invoke(javabean, new Object[] {
		    
		    data.get(field)
		    
		    });
		}
	    } catch (Exception e) {
	    }
	}
	return javabean;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月28日
     * @function：TODO JSONObject到JavaBean
     * @param javabean
     * @param jsonString
     * @throws ParseException  json解析异常
     * @throws JSONException
     * @return json对象
     */
    public static void JsonObjToBean(Object javabean, String jsonString) throws ParseException, JSONException {
	
	JSONObject jsonObject = new JSONObject(jsonString);
	
	Map<Object, Object> map = jsonObjToMap(jsonObject.toString());
	
	mapToBean(javabean, map);
	
    }
    
}
