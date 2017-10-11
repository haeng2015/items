package com.panasign.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2016-3-3
 */
@Controller
@RequestMapping("/")
public class ErrorPageController extends BaseController {
	/**
	 * 访问出错默认页面
	 * @return
	 * @author Wu.Liang
	 */
	@RequestMapping("accessError")
	public ModelAndView accessError(){
		ModelAndView mav = new ModelAndView("error/accessError");
		return mav;
	}
	
	/**
	 * 参数出错页面
	 * @return
	 * @author Wu.Liang
	 */
	@RequestMapping("paramsError")
	public ModelAndView paramsError(){
		ModelAndView mav = new ModelAndView("error/paramsError");
		return mav;
	}
}	
