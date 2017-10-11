package com.pbms.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoAccessory;
import com.pbms.pojo.BoBuilding;
import com.pbms.service.BoAccessoryService;
import com.pbms.service.BoBuildingService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.BuildingVO;
import com.pbms.vo.JsonVO;
import com.pbms.vo.UserVO;

@Controller
@RequestMapping("/build")
public class BoBuildingController {
    private static final Logger LOGGER = Logger.getLogger(BoBuildingController.class);
    
    @Autowired
    private BoBuildingService boBuildingService;
    @Autowired
    private BoAccessoryService boAccessoryService;
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 到楼栋列表页面
     * @return
     */
    @RequestMapping("/toBuildingList")
    public ModelAndView toBuildingList() {
	ModelAndView mv = new ModelAndView("/build/buildList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载楼栋列表信息
     * @param pageContext
     * @return
     */
    @RequestMapping("/loadBuildingList")
    public void loadBuildingList(DataGridPage pageContext, HttpServletResponse response) {
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    DataGrid dataGrid = this.boBuildingService.loadBuildingList(pageContext);
	    jsonString = JSON.toJSONString(dataGrid);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoBuildingController -> loadBuildingList : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 到添加、更新页面
     * @param buildingVO
     * @return
     */
    @RequestMapping("/editBuilding")
    public ModelAndView editBuilding(BuildingVO buildingVO) {
	ModelAndView mv = null;
	BoBuilding boBuilding = null;
	if ("1".equals(buildingVO.getMark())) {
	    mv = new ModelAndView("/build/buildDetailed");
	} else {
	    mv = new ModelAndView("/build/editBuilding");
	}
	if (buildingVO.getBuildId() != null && !"".equals(buildingVO.getBuildId())) {
	    boBuilding = this.boBuildingService.findBoBuildingById(buildingVO.getBuildId());
	    mv.addObject("buildingVO", this.boBuildingService.boToVo(boBuilding));
	    
	    // 附件
	    BoAccessory boAccessory = new BoAccessory();
	    boAccessory.setBuilding(boBuilding);
	    List<BoAccessory> boAccessoryList = this.boAccessoryService.getAttachmentsByObject(boAccessory);
	    mv.addObject("boAccessoryBuilds", boAccessoryList);
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存、更新对象
     * @param buildingVO
     * @param response
     * @ResponseBody为 转换json对象的注解
     */
    @RequestMapping("/saveOrUpdateBuilding")
    @ResponseBody
    public JsonVO saveOrUpdateBuilding(BuildingVO buildingVO, HttpServletRequest request) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	try {
	    UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
	    
	    // 文件上传处理
	    Map<String, MultipartFile> fileMap = null;
	    // 创建一个通用的多部分解析器
	    CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession()
		    .getServletContext());
	    // 判断 request 是否有文件上传,即多部分请求...
	    if (commonsMultipartResolver.isMultipart(request)) {
		// 普通request请求对象转换成可以处理文件的multiRequest对象
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 获取参数名对应的文件列表
		fileMap = multiRequest.getFileMap();
	    }
	    // 此处返回的BoBuilding对象是保存前的，所以不会有保存到数据库后的属性（如，自增的主键等）
	    BoBuilding boBuilding = this.boBuildingService.saveOrUpdateBuilding(buildingVO);
	    
	    // ？？?::思考：：mybatis保存后对象后如何返回该对象（或者去查找一遍），
	    // 解决方法：保存后返回主键id（mysql）keyProperty="userId"
	    if (boBuilding != null && !"".equals(boBuilding) && fileMap != null && !"".equals(fileMap)
		    && fileMap.size() > 0) {
		// 更新对象时，返回的对象属性都为空，包括主键id
		if (buildingVO.getBuildId() != null && !"".equals(buildingVO.getBuildId())) {
		    boBuilding.setBuildId(buildingVO.getBuildId());
		}
		result = this.boAccessoryService.addAndUpdateAttachmentsForObject(userVO, BoAccessory.BUILDING_SIGN,
			boBuilding, request, fileMap);
	    } else {
		if (boBuilding != null && !"".equals(boBuilding)){
		    result = 1;
		}
	    }
	    if (result > 0) {
		json.setReflag(true);
		if (buildingVO.getBuildId() != null && !"".equals(buildingVO.getBuildId())) {
		    json.setInfoMsg("楼栋更新成功！");
		} else {
		    json.setInfoMsg("楼栋添加成功！");
		}
	    } else {
		json.setReflag(false);
		if (buildingVO.getBuildId() != null && !"".equals(buildingVO.getBuildId())) {
		    json.setInfoMsg("楼栋更新失败！");
		} else {
		    json.setInfoMsg("楼栋添加失败！");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("BoBuildingController -> saveOrUpdateBuilding : " + e.getMessage());
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
    @RequestMapping("/deleteBuilding")
    @ResponseBody
    public JsonVO deleteBuilding(BuildingVO buildingVO, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	try {
	    result = this.boBuildingService.deleteBuilding(buildingVO);
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("楼栋删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("楼栋删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoBuildingController -> deleteBuilding : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 下载楼栋附件
     * @param attId
     * @return
     */
    @RequestMapping("/uploadAttachmentForBuild")
    public void uploadAttachmentForBuild(Integer id, HttpServletRequest request, HttpServletResponse response) {
	try {
	    HttpServletResponse responseReturn = this.boAccessoryService.uploadAttachmentById(id, request, response);
	    if (responseReturn == null) {
		response.sendError(404, "File not found!");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoBuildingController --> uploadAttachmentForBuild ： " + e.getMessage());
	}
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 删除附件
     * @param attId
     * @return
     */
    @RequestMapping("/deleteAttachmentForBuild")
    @ResponseBody
    public JsonVO deleteAttachmentForBuild(Integer id, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	try {
	    result = this.boAccessoryService.deleteAttachmentById(id);
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("附件删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("附件删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoBuildingController --> deleteAttachmentForBuild ： " + e.getMessage());
	}
	return json;
    }
    
}
