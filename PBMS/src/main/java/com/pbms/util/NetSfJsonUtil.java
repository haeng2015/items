package com.pbms.util;

 import java.io.IOException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月27日
 * @说明：实体类和JSON对象之间相互转化（依赖包jackson-all-1.7.6.jar、jsoup-1.5.2.jar）
 */
public class NetSfJsonUtil {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 将json转化为实体POJO
     * @param jsonStr
     * @param obj
     * @return
     */
    public static <T> Object jsonToObj(String jsonStr, Class<T> obj) {
	T t = null;
	try {
	    ObjectMapper objectMapper = new ObjectMapper();
	    t = objectMapper.readValue(jsonStr, obj);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return t;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 实体POJO转化为JSON( net.sf.json.jsonobject 没有 new
     *                JSONObject(String)的构造方法,而org.json.JSONObject有)
     * @param obj
     * @return
     * @throws JSONException
     * @throws IOException
     */
    public static <T> JSONObject objectToJson(T obj) throws JSONException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	// Convert object to JSON string
	String jsonStr = "";
	try {
	    jsonStr = mapper.writeValueAsString(obj);
	} catch (IOException e) {
	    throw e;
	}
	return JSONObject.fromObject(jsonStr);
    }
    
    
}
