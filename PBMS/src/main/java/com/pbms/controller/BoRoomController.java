package com.pbms.controller;

import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoAccessory;
import com.pbms.pojo.BoOwner;
import com.pbms.pojo.BoRoom;
import com.pbms.service.BoAccessoryService;
import com.pbms.service.BoBuildingService;
import com.pbms.service.BoOwnerService;
import com.pbms.service.BoRoomService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.BuildingVO;
import com.pbms.vo.JsonVO;
import com.pbms.vo.OwnerVO;
import com.pbms.vo.RoomVO;
import com.pbms.vo.UserVO;

@Controller
@RequestMapping("/room")
public class BoRoomController {
    private static final Logger LOGGER = Logger.getLogger(BoRoomController.class);
    
    @Autowired
    private BoRoomService boRoomService;
    @Autowired
    private BoBuildingService boBuildingService;
    private BoOwnerService boOwnerService;
    @Autowired
    private BoAccessoryService boAccessoryService;
    
    public BoOwnerService getBoOwnerService() {
	return boOwnerService;
    }
    
    @Resource
    public void setBoOwnerService(BoOwnerService boOwnerService) {
	this.boOwnerService = boOwnerService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 到房间列表页面
     * @return
     */
    @RequestMapping("/toRoomList")
    public ModelAndView toBuildingList() {
	ModelAndView mv = new ModelAndView("/room/roomList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载房间列表信息
     * @param pageContext
     * @return PrintWriter方式返回json格式
     */
    @RequestMapping("/loadRoomingList")
    public void loadRoomingList(DataGridPage pageContext, HttpServletResponse response) {
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    DataGrid dataGrid = this.boRoomService.loadRoomList(pageContext);
	    jsonString = JSON.toJSONString(dataGrid);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoRoomController -> loadRoomingList : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 到添加、更新页面
     * @param buildingVO
     * @return
     */
    @RequestMapping("/editRoom")
    public ModelAndView editRoom(RoomVO roomVO) {
	ModelAndView mv = null;
	BoRoom boRoom = null;
	try {
	    if ("2".equals(roomVO.getMark())) {
		mv = new ModelAndView("/room/roomDetailed");
	    } else {
		mv = new ModelAndView("/room/editRoom");
	    }
	    List<BuildingVO> buildingVOs = this.boBuildingService.findAllBoBuildings();
	    if (buildingVOs != null && buildingVOs.size() > 0) {
		mv.addObject("buildingVOs", buildingVOs);
	    }
	    List<OwnerVO> OwnerVOs = this.boOwnerService.findAllBoOwners();
	    if (OwnerVOs != null && OwnerVOs.size() > 0) {
		mv.addObject("OwnerVOs", OwnerVOs);
	    }
	    if (roomVO.getRoomId() != null && !"".equals(roomVO.getRoomId())) {
		boRoom = this.boRoomService.findBoRoomById(roomVO.getRoomId());
		mv.addObject("roomVO", this.boRoomService.boToVo(boRoom));
		
		// 附件
		BoAccessory boAccessory = new BoAccessory();
		boAccessory.setRoom(boRoom);
		List<BoAccessory> boAccessoryList = this.boAccessoryService.getAttachmentsByObject(boAccessory);
		mv.addObject("boAccessoryRooms", boAccessoryList);
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoomController -> editRoom : " + e.getMessage());
	}
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存、更新对象（注解@ResponseBody方式返回json格式）
     * @param buildingVO
     * @param response
     */
    @RequestMapping("/saveOrUpdateRoom")
    @ResponseBody
    // 转换json对象的注解
    public JsonVO saveOrUpdateBuilding(RoomVO roomVO, HttpServletRequest request) {
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
	    
	    BoRoom boRoom = this.boRoomService.saveOrUpdateRoom(roomVO);
	    if (boRoom != null && !"".equals(boRoom) && fileMap != null && !"".equals(fileMap) && fileMap.size() > 0) {
		// 更新对象时，返回的对象属性都为空，包括主键id
		if (roomVO.getRoomId() != null && !"".equals(roomVO.getRoomId())) {
		    boRoom.setRoomId(roomVO.getRoomId());
		}
		result = this.boAccessoryService.addAndUpdateAttachmentsForObject(userVO, BoAccessory.ROOM_SIGN,
			boRoom, request, fileMap);
	    } else {
		if (boRoom != null && !"".equals(boRoom)) {
		    result = 1;
		}
	    }
	    if (result > 0) {
		json.setReflag(true);
		if (roomVO.getRoomId() != null && !"".equals(roomVO.getRoomId())) {
		    json.setDlgType(2);
		    json.setInfoMsg("房间更新成功！");
		} else {
		    json.setInfoMsg("房间添加成功！");
		}
	    } else {
		json.setReflag(false);
		if (roomVO.getRoomId() != null && !"".equals(roomVO.getRoomId())) {
		    json.setInfoMsg("房间更新失败！");
		} else {
		    json.setInfoMsg("房间添加失败！");
		}
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoomController -> saveOrUpdateBuilding : " + e.getMessage());
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
    @RequestMapping("/deleteRoom")
    @ResponseBody
    public JsonVO deleteRoom(RoomVO roomVO) {
	JsonVO json = new JsonVO();
	try {
	    Integer result = this.boRoomService.deleteBoRoom(roomVO.getRoomIds());
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("房间删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("房间删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoomController -> deleteRoom : " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 下载房间附件
     * @param attId
     * @return
     */
    @RequestMapping("/uploadAttachmentForRoom")
    public void uploadAttachmentForRoom(Integer id, HttpServletRequest request, HttpServletResponse response) {
	try {
	    HttpServletResponse responseReturn = this.boAccessoryService.uploadAttachmentById(id, request, response);
	    if (responseReturn == null) {
		response.sendError(404, "File not found!");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoomController --> uploadAttachmentForRoom ： " + e.getMessage());
	}
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 删除附件
     * @param attId
     * @return
     */
    @RequestMapping("/deleteAttachmentForRoom")
    @ResponseBody
    public JsonVO deleteAttachmentForRoom(Integer id, HttpServletResponse response) {
	JsonVO json = new JsonVO();
	try {
	    Integer result = this.boAccessoryService.deleteAttachmentById(id);
	    if (result > 0) {
		json.setReflag(true);
		json.setInfoMsg("附件删除成功！");
	    } else {
		json.setReflag(false);
		json.setInfoMsg("附件删除失败！");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoomController --> deleteAttachmentForRoom ： " + e.getMessage());
	}
	return json;
    }
    
    /**
     * @author：He.hp
     * @date：2017年3月14日
     * @function：TODO 通过楼栋获得房间
     * @param attId
     * @return
     */
    @RequestMapping("/getRoomsByBuild")
    @ResponseBody
    public JsonVO getRoomsByBuild(Integer buildId,Integer roomId) {
	JsonVO json = new JsonVO();
	List<BoRoom> boRooms = null;
	List<BoOwner> owners = null;
	BoRoom boRoom = null;
	try {
	    // 先查找该楼栋下的房间是否已经有业主了，如果有，则移除
	    
	    // 业主房间
	    owners = this.boOwnerService.findAllBoOwnersByBuildId(buildId);
	    
	    // 所有房间信息
	    boRooms = this.boRoomService.findBoRoomByBuild(buildId);
	    
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
	    
	    //业主信息修改时，查找其下的房间信息（由于该楼栋不存在房间时会无法显示）
	    if(roomId != null && !"".equals(roomId)){
		RoomVO roomVO = new RoomVO();
		roomVO.setBuildId(buildId);
		roomVO.setRoomId(roomId);
		boRooms.add(this.boRoomService.findBoRoomByBuild_Room(roomVO));
	    }
	    // 移除存在后的房间信息列表
	    if (boRooms != null && boRooms.size() > 0) {
		json.setReflag(true);
		json.setReturnObj(boRooms);
	    } else {
		json.setReflag(false);
		json.setInfoMsg("该楼栋没有空闲房间!");
	    }
	} catch (Exception e) {
	    LOGGER.error("BoRoomController --> getRoomsByBuild ： " + e.getMessage());
	}
	return json;
    }
    
}
