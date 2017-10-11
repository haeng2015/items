package com.panasign.controller.login;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.panasign.controller.common.BaseController;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月24日
 * @说明：进入app、资源文件主管理界面
 */
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
	private Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login() {
		ModelAndView mv = null;
		try {
			mv = new ModelAndView("main");
			
		} catch (Exception e) {
			logger.error("LoginController --> main 方法异常：" + e.getMessage());
		}
		return mv;
	}
	
	
	
	
	
	
}
