package com.pbms.controller.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.vo.JsonVO;
import com.pbms.vo.UserVO;

/**
 * @版权所有：
 * @项目名称:
 * @创建者:Hehaipeng
 * @创建日期:2017年3月27日
 * @说明：头部、底部控制器（不进入拦截器）
 */
@Controller
@RequestMapping("/")
public class HeadFooterController {
    
    private static final Logger LOGGER = Logger.getLogger(HeadFooterController.class);
    
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 头部页面加载
     * @return
     */
    @RequestMapping("header")
    public ModelAndView header() {
	ModelAndView mv = new ModelAndView("/common/header");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月27日
     * @function：TODO 底部页面加载
     * @return
     */
    @RequestMapping("footer")
    public ModelAndView footer() {
	ModelAndView mv = new ModelAndView("/common/footer");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 检查session是否还存在
     * @param request
     * @param response
     */
    @RequestMapping("checkSession")
    public void checkSession(HttpServletRequest request, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
	    if (userVO == null) {
		json.setReflag(true);
		json.setInfoMsg("登录过期失效，请重新登录后再操作！");
	    } else {
		json.setReflag(false);
	    }
	    response.setContentType("application/json;charset=UTF-8");
	    pw = response.getWriter();
	    jsonString = JSON.toJSONString(json);
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    json.setReflag(false);
	    LOGGER.error("HeadFooterController -> checkSession : " + e.getMessage());
	}
    }
}
