package com.panasign.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.panasign.vo.JsonMsgVo;

/**
 * 权限拦截器
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-7-3
 */
public class SessionIntecepter implements HandlerInterceptor {
	// private Logger LOG = Logger.getLogger(PrivilegeIntecepter.class);
	/**
	 * 请求预处理 request.getHeader("x-requested-with")为null ： 该请求为普通请求
	 * request.getHeader("x-requested-with")为XMLHttpRequest ： 该请求为ajax请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		PrintWriter pw = null;

		// 普通请求
		if (request.getHeader("x-requested-with") == null) {
			// 未登录
			if (session.getAttribute("loginUser") == null) {
				// 跳转方式至登录页
				response.sendRedirect(request.getContextPath() + "/login.do");
				return false;
			}
			
			return true;
		}else{
			// 未登录
			if (session.getAttribute("loginUser") == null) {
				JsonMsgVo msg = new JsonMsgVo();
				msg.setReflag(false);
				msg.setInfoMsg("您未登录，请重新登录！");
				String json = "";
				json = JSON.toJSONStringWithDateFormat(msg, "yyyy-MM-dd HH:mm:ss");
				pw = response.getWriter();
				pw.write(json);
				pw.flush();
				return false;
			}
			
			 return true;
		}
	}

	/**
	 * 控制器处理完毕，生成view前
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * view展示完毕
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
