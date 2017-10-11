package com.panasign.core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.panasign.utils.StringUtils;


/**
 * 
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Liu.ruxing
 * @createDate: 2015-11-26
 */
public class VisitorIpInfoUtils {

	/**
	 * 获取访问用户的客户端IP（适用于公网与局域网）.
	 */
	public static final String getIpAddr(final HttpServletRequest request)
	        throws Exception {
	    if (request == null) {
	        throw (new Exception("getIpAddr method HttpServletRequest Object is null"));
	    }
	    String ipString = request.getHeader("x-forwarded-for");
	    if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
	        ipString = request.getHeader("Proxy-Client-IP");
	    }
	    if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
	        ipString = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
	        ipString = request.getRemoteAddr();
	    }
	 
	    // 多个路由时，取第一个非unknown的ip
	    final String[] arr = ipString.split(",");
	    for (final String str : arr) {
	        if (!"unknown".equalsIgnoreCase(str)) {
	            ipString = str;
	            break;
	        }
	    }
	 
	    return ipString;
	}
	
	/**获取用户地理信息
	 * @param strIP
	 * @return
	 */
	public static final String getCityByIp(String strIP){
		try {
			// 百度地图Api解析
			JSONObject json = readJsonFromUrl("http://api.map.baidu.com/location/ip?ak=1fe2840a4669b1985e3e169b3a65afbe&ip="+strIP);
			Integer status = (Integer) json.get("status");
			if(json!=null && status==0){
			    return (String) ((JSONObject) json.get("content")).get("address");
			}else{
				return null;
			}
	    }catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject readJsonFromUrl(String url) throws IOException{
		InputStream is = new URL(url).openStream();
		BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(rd);
		
		JSONObject json = JSON.parseObject(jsonText); 
		is.close();
		return json;
	  }
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
	
}
