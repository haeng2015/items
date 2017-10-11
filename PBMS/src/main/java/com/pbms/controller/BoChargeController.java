package com.pbms.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoCharge;
import com.pbms.service.BoChargeService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.ChargeVO;
import com.pbms.vo.JsonVO;

@Controller
@RequestMapping("/charge")
public class BoChargeController {
    
    private static final Logger LOGGER = Logger.getLogger(BoChargeController.class);
    
    @Autowired
    private BoChargeService boChargeService;
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 到s收费列表页面
     * @return
     */
    @RequestMapping("/toChargeList")
    public ModelAndView toChargeList() {
	ModelAndView mv = new ModelAndView("/charge/chargeList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载房间列表信息
     * @param pageContext
     * @return PrintWriter方式返回json格式
     */
    @RequestMapping("/loadChargeList")
    public void loadChargeList(DataGridPage pageContext, HttpServletResponse response) {
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    DataGrid dataGrid = this.boChargeService.loadChargeList(pageContext);
	    jsonString = JSON.toJSONString(dataGrid);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoChargeController -> loadChargeList : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 到添加、更新页面
     * @param buildingVO
     * @return
     */
    @RequestMapping("/editCharge")
    public ModelAndView editCharge(ChargeVO chargeVO) {
	ModelAndView mv = new ModelAndView("/charge/editCharge");
	BoCharge boCharge = null;
	try {
	    if (chargeVO.getChargeId() != null && !"".equals(chargeVO.getChargeId())) {
		boCharge = this.boChargeService.findBoChargeById(chargeVO.getChargeId());
		mv.addObject("chargeVO", this.boChargeService.boToVo(boCharge));
	    }
	} catch (Exception e) {
	    LOGGER.error("BoChargeController -> editCharge : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存、更新对象（注解@ResponseBody方式返回json格式）
     * @param buildingVO
     * @param response
     *            其中@ResponseBody转换json对象的注解
     */
    @RequestMapping("/saveOrUpdateCharge")
    @ResponseBody
    public JsonVO saveOrUpdateCharge(ChargeVO chargeVO) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	try {
	    result = this.boChargeService.saveOrUpdateCharge(chargeVO);
	    if (result > 0) {
		json.setReflag(true);
		if (chargeVO.getChargeId() != null && !"".equals(chargeVO.getChargeId())) {
		    json.setInfoMsg("项目更新成功！");
		} else {
		    json.setInfoMsg("项目添加成功！");
		}
	    } else {
		json.setReflag(false);
		if (chargeVO.getChargeId() != null && !"".equals(chargeVO.getChargeId())) {
		    json.setInfoMsg("项目更新失败！");
		} else {
		    json.setInfoMsg("项目添加失败！");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("BoChargeController -> saveOrUpdateCharge : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 删除对象
     * @param buildingVO
     * @param response
     */
    @RequestMapping("/deleteCharge")
    @ResponseBody
    public JsonVO deleteCharge(ChargeVO chargeVO) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	try {
	    result = this.boChargeService.deleteCharge(chargeVO.getChargeIds());
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("项目删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("项目删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoChargeController -> deleteCharge : " + e.getMessage());
	}
	return json;
    }
    
}
