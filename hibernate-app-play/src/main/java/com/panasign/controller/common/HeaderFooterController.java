package com.panasign.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月24日
 * @说明：页面头底部控制器
 */
@Controller
@RequestMapping("/")
public class HeaderFooterController extends BaseController {
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 页面头部
	 * @return
	 */
	@RequestMapping("header")
	public ModelAndView supplierHeader(){
		ModelAndView mav = new ModelAndView("header");
		return mav;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 页面底部
	 * @return
	 */
	@RequestMapping("footer")
	public ModelAndView supplierFooter(){
		ModelAndView mav = new ModelAndView("footer");
		return mav;
	}
}
