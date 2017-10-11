package com.pbms.interceptor;

import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.util.StringUtils;
import com.pbms.vo.JsonVO;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年4月27日
 * @说明：权限拦截器(实现接口：HandlerInterceptor)
 */
public class AuthInterceptor implements HandlerInterceptor {
    
    /**
     * 请求预处理 request.getHeader("x-requested-with")为null ： 该请求为普通请求
     * request.getHeader("x-requested-with")为XMLHttpRequest ： 该请求为ajax请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	HttpSession session = request.getSession();
	PrintWriter pw = null;
	
	// 拿到session中的权限集合
	@SuppressWarnings("unchecked")
	Set<String> accessableURLs = (Set<String>) session.getAttribute("authRequestUrl");
	
	// request.getRequestURI() :
	// /Panasign-cloud/purchaser/privilege/orgList.do //全链接
	// request.getContextPath() : /Panasign-cloud //项目名
	// 截取当前访问的菜单url（去掉项目名部分）
	String currentMenuUrl = StringUtils.replaceSomeChar(request.getRequestURI(), request.getContextPath(), "");
	
	// 普通请求
	if (request.getHeader("x-requested-with") == null && !"/header.do".equals(currentMenuUrl)
		&& !"/footer.do".equals(currentMenuUrl) && !"/checkSession.do".equals(currentMenuUrl)) {
	    
	    // 未登录
	    if (session.getAttribute("userVO") == null) {
		response.sendRedirect(request.getContextPath() + "/login.do");
		return false;
	    } else {
		if (currentMenuUrl.contains(";")) {
		    currentMenuUrl = currentMenuUrl.split(";")[0];
		}
		
		if (accessableURLs.contains(currentMenuUrl)) {
		    return true;
		} else {
		    // 没有权限，进入指定页面
		    response.sendRedirect(request.getContextPath() + "/noAuth.do");
		    return false;
		}
	    }
	} else { // ajax请求
	    JsonVO json = new JsonVO();
	    String jsonStr = "";
	    if (session.getAttribute("userVO") == null) { // 未登录
		json.setReflag(false);
		json.setInfoMsg("您未登录，请重新登录！");
		jsonStr = JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd");
		pw = response.getWriter();
		pw.write(jsonStr);
		pw.flush();
		if (pw != null) {
		    pw.close();
		}
		return false;
	    } else {
		if (accessableURLs.contains(currentMenuUrl)) {
		    return true;
		} else {
		    json.setReflag(false);
		    json.setInfoMsg("您没有访问权限！");
		    response.setContentType("application/json;charset=UTF-8");
		    jsonStr = JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss");
		    pw = response.getWriter();
		    pw.write(jsonStr);
		    pw.flush();
		    if (pw != null) {
			pw.close();
		    }
		    return false;
		}
	    }
	}
    }
    
    /**
     * 控制器处理完毕，生成view前进入该方法
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	    ModelAndView modelAndView) throws Exception {
	
    }
    
    /**
     * view展示完毕，进入该方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	    throws Exception {
	
    }
    
}
