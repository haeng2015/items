package com.pbms.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.pbms.pojo.BoAccessory;
import com.pbms.pojo.BoOwner;
import com.pbms.pojo.BoRoom;
import com.pbms.service.AddressService;
import com.pbms.service.BoAccessoryService;
import com.pbms.service.BoBuildingService;
import com.pbms.service.BoOwnerService;
import com.pbms.service.BoRoomService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.AddressVO;
import com.pbms.vo.BuildingVO;
import com.pbms.vo.JsonVO;
import com.pbms.vo.OwnerVO;
import com.pbms.vo.UserVO;

@Controller
@RequestMapping("/owner")
public class BoOwnerController {
    
    private static final Logger LOGGER = Logger.getLogger(BoOwnerController.class);
    
    @Autowired
    private BoOwnerService boOwnerService;
    private AddressService addressService;
    @Resource
    private BoAccessoryService boAccessoryService;
    @Autowired
    private BoBuildingService boBuildingService;
    @Autowired
    private BoRoomService boRoomService;
    
    public AddressService getAddressService() {
	return addressService;
    }
    
    @Resource
    public void setAddressService(AddressService addressService) {
	this.addressService = addressService;
    }
    
    public BoAccessoryService getBoAccessoryService() {
	return boAccessoryService;
    }
    
    public void setBoAccessoryService(BoAccessoryService boAccessoryService) {
	this.boAccessoryService = boAccessoryService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月17日
     * @function：TODO 进入业主列表页
     * @return
     */
    @RequestMapping("/toOwnerList")
    public ModelAndView toBuildingList() {
	ModelAndView mv = new ModelAndView("/owner/ownerList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月17日
     * @function：TODO 加载业主列表
     * @param pageContext
     * @return
     */
    @RequestMapping("/loadOwnerList")
    @ResponseBody
    public DataGrid loadOwnerList(DataGridPage pageContext) {
	DataGrid dataGrid = new DataGrid();
	try {
	    dataGrid = this.boOwnerService.loadOwnerList(pageContext);
	} catch (Exception e) {
	    LOGGER.error("BoOwnerController -> loadOwnerList : " + e.getMessage());
	}
	return dataGrid;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月17日
     * @function：TODO 进入业主编辑页（添加、修改）
     * @param addressVO
     * @return
     */
    @RequestMapping("/editOwner")
    public ModelAndView editOwner(OwnerVO ownerVO) {
	ModelAndView mv = null;
	List<BuildingVO> buildingList = null;
	BuildingVO buildingVO = null;
	List<BoRoom> boRooms = null;
	List<BoOwner> owners = null;
	BoRoom boRoom = null;
	BoOwner boOwner = null;
	try {
	    if ("2".equals(ownerVO.getMark())) {
		mv = new ModelAndView("/owner/ownerDetailed");
	    } else {
		mv = new ModelAndView("/owner/editOwner");
	    }
	    // 加载所有楼栋列表
	    buildingList = this.boBuildingService.findAllBoBuildings();
	    
	    // 移除没有空余房间的楼栋
	    // for (BuildingVO building : buildingList) {
	    // //这种方式移除有报错（并发访问，下标未减少）
	    Iterator<BuildingVO> buildingIterators = buildingList.iterator();
	    while (buildingIterators.hasNext()) {
		buildingVO = buildingIterators.next();
		// 业主房间信息
		owners = this.boOwnerService.findAllBoOwnersByBuildId(buildingVO.getBuildId());
		// 所有房间信息
		boRooms = this.boRoomService.findBoRoomByBuild(buildingVO.getBuildId());
		
		if (boRooms != null && boRooms.size() > 0) {
		    // 移除已经存在的房间信息
		    Iterator<BoRoom> roomIterators = boRooms.iterator();
		    while (roomIterators.hasNext()) {
			boRoom = roomIterators.next();
			for (BoOwner owner : owners) {
			    if (owner.getRoomId() == boRoom.getRoomId()) {
				roomIterators.remove();
			    }
			}
		    }
		}
		
		// 移除业主已占领的房间，若没有空闲房间，则移除该楼栋
		if (boRooms != null && boRooms.size() == 0 && boRooms.isEmpty()) {
		    buildingIterators.remove();
		}
	    }
	    
	    // 加载省列表
	    List<AddressVO> addressList = this.addressService.findAllProvinces();
	    if (addressList != null) {
		mv.addObject("addressList", addressList);
	    }
	    
	    // 查找业主信息
	    if (ownerVO.getOwnerId() != null && !"".equals(ownerVO.getOwnerId())) {
		boOwner = this.boOwnerService.findBoOwnerById(ownerVO.getOwnerId());
		mv.addObject("ownerVO", this.boOwnerService.boToVo(boOwner));
		
		//如果移除后的buildingList中没有了本楼栋，则将本业主的楼栋信息查找出放入list中，如果有则不用放入
		if(boOwner.getBuildId() != null && !"".equals(boOwner.getBuildId())){
		    BuildingVO buildingVO2 = this.boBuildingService.boToVo(this.boBuildingService.findBoBuildingById(boOwner.getBuildId()));
		    if(!buildingList.contains(buildingVO2)){
			buildingList.add(buildingVO2);
		    }
		}
		
		//对buildingList中元素排序(根据楼栋id)
		Collections.sort(buildingList, new Comparator<BuildingVO>() {
		    @Override
		    public int compare(BuildingVO o1, BuildingVO o2) {
			return o1.getBuildId() - o2.getBuildId();
		    }

		});
		
		// 附件
		BoAccessory boAccessory = new BoAccessory();
		boAccessory.setOwner(boOwner);
		List<BoAccessory> boAccessoryList = this.boAccessoryService.getAttachmentsByObject(boAccessory);
		mv.addObject("boAccessoryOwner", boAccessoryList);
	    }
	    
	    if (buildingList != null) {
		mv.addObject("buildingList", buildingList);
	    }
	    
	} catch (Exception e) {
	    LOGGER.error("BoOwnerController -> editOwner : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存、更新对象（注解@ResponseBody方式返回json格式）
     * @param buildingVO
     * @param response
     * @ResponseBody 为转换json对象的注解
     */
    @RequestMapping("/saveOrUpdateOwner")
    @ResponseBody
    public JsonVO saveOrUpdateOwner(OwnerVO ownerVO, HttpServletRequest request) {
	JsonVO json = new JsonVO();
	BoOwner boOwner = null;
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
	    
	    boOwner = this.boOwnerService.saveOrUpdateOwner(ownerVO, userVO);
	    if (boOwner != null && fileMap != null && fileMap.size() > 0) {
		result = this.boAccessoryService.addAndUpdateAttachmentsForObject(userVO, BoAccessory.OWNER_SIGN,
			boOwner, request, fileMap);
	    } else {
		result = 1;
	    }
	    if (result > 0) {
		json.setReflag(true);
		if (ownerVO.getOwnerId() != null && !"".equals(ownerVO.getOwnerId())) {
		    json.setInfoMsg("业主更新成功！");
		} else {
		    json.setInfoMsg("业主添加成功！");
		}
	    } else {
		json.setReflag(false);
		if (ownerVO.getOwnerId() != null && !"".equals(ownerVO.getOwnerId())) {
		    json.setInfoMsg("业主更新失败！");
		} else {
		    json.setInfoMsg("业主添加失败！");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("BoOwnerController -> saveOrUpdateOwner : " + e.getMessage());
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
    @RequestMapping("/deleteOwner")
    @ResponseBody
    public JsonVO deleteOwner(OwnerVO ownerVO) {
	JsonVO json = new JsonVO();
	Integer result = 0;
	try {
	    result = this.boOwnerService.deleteOwner(ownerVO);
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("业主删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("业主删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoOwnerController -> deleteOwner : " + e.getMessage());
	}
	return json;
    }

    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 下载附件
     * @param attId
     * @return
     */
    @RequestMapping("/uploadAttachmentForOwner")
    public void uploadAttachmentForOwner(Integer id, HttpServletRequest request, HttpServletResponse response) {
	HttpServletResponse responseReturn = null;
	try {
	    responseReturn = this.boAccessoryService.uploadAttachmentById(id, request, response);
	    if (responseReturn == null) {
		response.sendError(404, "File not found!");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoOwnerController --> uploadAttachmentForOwner ： " + e.getMessage());
	}
    }

     /**
      * @author：He.hp
      * @date：2017年3月14日
      * @function：TODO 删除附件
      * @param attId
      * @return
      */
     @RequestMapping("/deleteAttachmentForOwner")
     @ResponseBody
     public JsonVO deleteAttachmentForOwner(Integer id, HttpServletResponse response) {
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
 	    LOGGER.error("BoOwnerController --> deleteAttachmentForOwner ： " + e.getMessage());
 	}
 	return json;
     }
    
}
