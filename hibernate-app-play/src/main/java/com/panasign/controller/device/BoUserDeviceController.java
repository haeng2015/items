package com.panasign.controller.device;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.panasign.bean.BoAttachmentSource;
import com.panasign.bean.BoUserDevice;
import com.panasign.controller.common.BaseController;
import com.panasign.core.TreeGrid;
import com.panasign.service.BoAttachmentSourceService;
import com.panasign.service.BoUserDeviceService;
import com.panasign.vo.JsonMsgVo;
import com.panasign.vo.UserDeviceVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月29日
 * @说明：用户设备控制调度器
 */
@Controller
@RequestMapping("/device")
public class BoUserDeviceController extends BaseController {
	
	private static final Logger LOG = Logger.getLogger(BoUserDeviceController.class);
	
	@Autowired
	public BoUserDeviceService boUserDeviceService;
	
	@Autowired
	public BoAttachmentSourceService boAttachmentSourceService;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 进入设备列表(当mark=1时进入推送列表)
	 * @return
	 */
	@RequestMapping("/toDeviceList")
	public ModelAndView todeviceList(String mark) {
		ModelAndView mv = null;
		try {
			// 已推送资源的设备列表
			if ("1".equals(mark)) {
				mv = new ModelAndView("/push/pushList");
				
				mv.addObject("userDeviceVOs", boUserDeviceService.findAllBoUserDeviceUserName());
				
			} else {
				mv = new ModelAndView("/device/deviceList");
			}
			
		} catch (Exception e) {
			LOG.error("【异常】BoUserDeviceController --> loadDeviceList : " + e.getMessage());
		}
		return mv;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 加载设备信息
	 * @param deviceVO
	 * @return
	 */
	@RequestMapping("/loadDeviceList")
	@ResponseBody
	public TreeGrid loadDeviceList(UserDeviceVO userDeviceVO) {
		TreeGrid treeGrid = new TreeGrid();
		try {
			treeGrid = boUserDeviceService.loadDeviceListInfoByTree(userDeviceVO);
		} catch (Exception e) {
			LOG.error("【异常】BoUserDeviceController --> loadDeviceList : " + e.getMessage());
		}
		return treeGrid;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 编辑设备文件
	 * @param attachmentSourceVO
	 * @return
	 */
	@RequestMapping("/toDeviceEdit")
	public ModelAndView toDeviceEdit(UserDeviceVO userDeviceVO) {
		ModelAndView mv = new ModelAndView("/device/deviceEdit");
		
		try {
			BoUserDevice boUserDevice = null;
			if (userDeviceVO.getUserDeviceId() != null && !"".equals(userDeviceVO.getUserDeviceId())) {
				
				boUserDevice = boUserDeviceService.findBoUserDeviceById(userDeviceVO.getUserDeviceId());
				
				if (boUserDevice != null) {
					
					// 查找该设备的父节点，并赋值到该设备中(如果是父节点则不用赋值)
					if (boUserDevice.getBoUserDevice() != null && !"".equals(boUserDevice.getBoUserDevice())) {
						
						BoUserDevice userDevice = boUserDeviceService.findBoUserDeviceById(boUserDevice.getBoUserDevice().getId());
						if (userDevice != null) {
							boUserDevice.setUserName(userDevice.getUserName());
						}
					}
					
					mv.addObject("deviceVO", boUserDeviceService.boToVo(boUserDevice));
					
				} else {
					mv = new ModelAndView("redirect:/paramsError.do");
				}
			}
			
			if (userDeviceVO.getUserName() != null && !"".equals(userDeviceVO.getUserName())) {
				mv.addObject("userName", userDeviceVO.getUserName());
			}
			
		} catch (Exception e) {
			LOG.error("【异常】BoUserDeviceController --> toDeviceEdit : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 保存添加设备
	 * @param deviceVO
	 * @return
	 */
	@RequestMapping("/saveOrUpdateDevice")
	@ResponseBody
	public JsonMsgVo saveOrUpdateDevice(UserDeviceVO userDeviceVO) {
		JsonMsgVo json = new JsonMsgVo();
		BoUserDevice boUserDevice = null;
		try {
			boUserDevice = boUserDeviceService.addAndSaveDevice(userDeviceVO);
			
			if (boUserDevice != null) {
				json.setReflag(true);
				if (userDeviceVO.getUserDeviceId() != null && !"".equals(userDeviceVO.getUserDeviceId())) {
					json.setInfoMsg("用户设备更新成功！");
				} else {
					json.setInfoMsg("用户设备添加成功！");
				}
			} else {
				json.setReflag(false);
				if (userDeviceVO.getUserDeviceId() != null && !"".equals(userDeviceVO.getUserDeviceId())) {
					json.setInfoMsg("用户设备更新失败！");
				} else {
					json.setInfoMsg("用户设备添加失败！");
				}
			}
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> saveOrUpdateDevice()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年9月4日
	 * @function：TODO 检查该设备id是否已存在
	 * @param userDeviceVO
	 * @return
	 */
	@RequestMapping("/checkDeviceId")
	@ResponseBody
	public JsonMsgVo checkDeviceId(UserDeviceVO userDeviceVO) {
		JsonMsgVo json = new JsonMsgVo();
		BoUserDevice boUserDevice = null;
		try {
			boUserDevice = boUserDeviceService.findBoUserDeviceByDeviceId(userDeviceVO.getDeviceId());
			if (boUserDevice != null) {
				json.setReflag(false);
				json.setInfoMsg("该设备ID已存在！");
			} else {
				json.setReflag(true);
				json.setInfoMsg("设备ID可用！");
			}
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> checkDeviceId()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 推送资源到设备
	 * @param userDeviceVO
	 * @return
	 */
	@RequestMapping("/pushResourceToDevice")
	@ResponseBody
	public JsonMsgVo pushResourceToDevice(UserDeviceVO userDeviceVO) {
		JsonMsgVo json = new JsonMsgVo();
		Integer count = 0;
		try {
			count = boUserDeviceService.pushResourceForDevice(userDeviceVO);
			if (count > 0) {
				json.setReflag(true);
				json.setInfoMsg("资源推送成功！");
			} else {
				json.setReflag(false);
				json.setInfoMsg("资源推送失败！");
			}
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> pushResourceToDevice()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 删除设备
	 * @param deviceVO
	 * @return
	 */
	@RequestMapping("/deleteDevice")
	@ResponseBody
	public JsonMsgVo deleteDevice(UserDeviceVO userDeviceVO) {
		JsonMsgVo json = new JsonMsgVo();
		Integer count = 0;
		try {
			count = boUserDeviceService.deleteDeviceById(userDeviceVO.getUserDeviceIds());
			
			if (count > 0) {
				json.setReflag(true);
				json.setInfoMsg("设备删除成功！");
			} else {
				json.setReflag(false);
				json.setInfoMsg("设备删除失败！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> deleteDevice()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年9月1日
	 * @function：TODO 删除/更改设备推送资源文件
	 * @param userDeviceVO
	 * @return
	 */
	@RequestMapping("/updateResourceToDevice")
	@ResponseBody
	public JsonMsgVo updateResourceToDevice(UserDeviceVO userDeviceVO) {
		JsonMsgVo json = new JsonMsgVo();
		Integer count = 0;
		try {
			if ("2".equals(userDeviceVO.getMark()) || "3".equals(userDeviceVO.getMark())) {
				
				count = boUserDeviceService.updateOrDeleteResourceToDeviceById(userDeviceVO);
			} else {
				json.setReflag(false);
				json.setInfoMsg("参数错误！");
				return json;
			}
			
			if (count > 0) {
				json.setReflag(true);
				if ("2".equals(userDeviceVO.getMark())) { // 删除
					json.setInfoMsg("设备资源删除成功！");
				}
				if ("3".equals(userDeviceVO.getMark())) { // 更改
					json.setInfoMsg("设备资源更改成功！");
				}
				
			} else {
				json.setReflag(false);
				if ("2".equals(userDeviceVO.getMark())) { // 删除
					json.setInfoMsg("设备资源删除失败！");
				}
				if ("3".equals(userDeviceVO.getMark())) { // 更改
					json.setInfoMsg("设备资源更改失败！");
				}
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> updateResourceToDevice()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 通过设备id请求下载资源（此为提供给Android终端的外部接口）
	 *  	请求该方法时，必须返回设备ID（UserDeviceVO.deviceId）和资源唯一名（UserDeviceVO.uniqueName）；
	 *  	如果设备ID正确且资源唯一名与最新推送的资源名不一致，或资源名为空（非null）时，则提供下载资源文件的url接口（JsonMsgVo.confirmUrl），
	 *  	否则返回提示信息，且资源下载接口为空或null（JsonMsgVo.confirmUrl）.
	 * @param userDeviceVO
	 * @return
	 */
	@RequestMapping("/getResourceByDeviceId")
	@ResponseBody
	public JsonMsgVo getResourceByDeviceId(UserDeviceVO userDeviceVO) {
		BoUserDevice boUserDevice = null;
		JsonMsgVo json = new JsonMsgVo();
		try {
			if (userDeviceVO.getDeviceId() != null && !"".equals(userDeviceVO.getDeviceId())) {
				
				boUserDevice = boUserDeviceService.findBoUserDeviceByDeviceId(userDeviceVO.getDeviceId());
			} else {
				json.setReflag(false);
				json.setInfoMsg("设备参数错误！");
				
				return json;
			}
			
			if (boUserDevice != null) {
				
				if (boUserDevice.getBoAttachmentSource() != null && !"".equals(boUserDevice.getBoAttachmentSource())) {
					
					// 判断是否有新资源被推送，若推送了则提供下载接口(通过文件唯一名判断)
					if (userDeviceVO.getUniqueName() != null) {
						
						if (!"".equals(userDeviceVO.getUniqueName()) && userDeviceVO.getUniqueName().equals(boUserDevice.getBoAttachmentSource().getUniqueName())) {
							
							json.setReflag(false);
							json.setInfoMsg("设备（ID:" + boUserDevice.getDeviceId() + "）没有被推送新资源！");
							json.setReturnObj(boAttachmentSourceService.boToVo(boUserDevice.getBoAttachmentSource()));
							json.setConfirmUrl("");
							
							return json;
						}
					} else {
						json.setReflag(false);
						json.setInfoMsg("资源参数错误！");
						
						return json;
					}
					
					json.setReflag(true);
					json.setInfoMsg("设备（ID:" + boUserDevice.getDeviceId() + "）请求下载资源！");
					json.setReturnObj(boAttachmentSourceService.boToVo(boUserDevice.getBoAttachmentSource()));
					json.setConfirmUrl("http://10.66.0.17:8080/hibernate-app-play/device/downloadResourceByDeviceId.do?deviceId=" + boUserDevice.getDeviceId());
					
				} else {
					json.setReflag(false);
					json.setInfoMsg("未找到该设备（ID:" + boUserDevice.getDeviceId() + "）请求的资源！");
					json.setReturnObj(boUserDeviceService.boToVo(boUserDevice));
					json.setConfirmUrl("");
				}
				
			} else {
				json.setReflag(false);
				json.setInfoMsg("未找到该设备（ID:" + userDeviceVO.getDeviceId() + "）！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("资源请求下载出错:" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> getResourceByDeviceId()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 通过设备id下载资源（此为提供给Android终端的外部接口）
	 * 	请求该接口时需要传入设备ID（UserDeviceVO.deviceId），在设备ID正确的情况下提供下载资源，
	 * 	为防止外部更改请求路径及参数信息，需要再次检查设备ID的可用性，并提供相关提示信息。
	 * @param userDeviceVO
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadResourceByDeviceId")
	@ResponseBody
	public JsonMsgVo downloadResourceByDeviceId(UserDeviceVO userDeviceVO, HttpServletRequest request, HttpServletResponse response) {
		BoUserDevice boUserDevice = null;
		JsonMsgVo json = new JsonMsgVo();
		try {
			if (userDeviceVO.getDeviceId() != null && !"".equals(userDeviceVO.getDeviceId())) {
				
				boUserDevice = boUserDeviceService.findBoUserDeviceByDeviceId(userDeviceVO.getDeviceId());
			} else {
				json.setReflag(false);
				json.setInfoMsg("参数错误！");
				return json;
			}
			
			if (boUserDevice != null) {
				
				if (boUserDevice.getBoAttachmentSource() != null && !"".equals(boUserDevice.getBoAttachmentSource())) {
					
					super.downloadFileReturn(boUserDevice.getBoAttachmentSource().getUniqueName().replace(BoAttachmentSource.RESOURCE_DIR, ""), request, response);
					
				} else {
					json.setReflag(false);
					json.setInfoMsg("未找到该设备（ID:" + boUserDevice.getDeviceId() + "）请求的资源！");
					json.setReturnObj(boUserDeviceService.boToVo(boUserDevice));
				}
			} else {
				json.setReflag(false);
				json.setInfoMsg("未找到该设备（ID:" + userDeviceVO.getDeviceId() + "）！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("资源下载出错:" + e.getMessage());
			LOG.error("【异常】BoUserDeviceController --> downloadResourceByDeviceId()：" + e.getMessage());
		}
		return json;
	}
	
}
