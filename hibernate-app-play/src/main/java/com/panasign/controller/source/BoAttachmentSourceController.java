package com.panasign.controller.source;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.panasign.bean.BoAttachmentSource;
import com.panasign.controller.common.BaseController;
import com.panasign.core.DataGrid;
import com.panasign.service.BoAttachmentSourceService;
import com.panasign.vo.AttachmentSourceVO;
import com.panasign.vo.JsonMsgVo;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月29日
 * @说明：资源文件控制调度器
 */
@Controller
@RequestMapping("/resource")
public class BoAttachmentSourceController extends BaseController {
	
	private static final Logger LOG = Logger.getLogger(BoAttachmentSourceController.class);
	
	@Autowired
	public BoAttachmentSourceService boAttachmentSourceService;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 进入资源列表
	 * @return
	 */
	@RequestMapping("/toResourceList")
	public ModelAndView toResourceList(AttachmentSourceVO attachmentSourceVO) {
		ModelAndView mv = new ModelAndView("/resource/resourceList");
		try {
			Integer[] userDeviceIds = attachmentSourceVO.getUserDeviceIds();
			
			if (userDeviceIds != null && userDeviceIds.length > 0) {
				
				// 转换为字符串,逗号隔开（优化方案，避免使用+拼接方式，优先选择StringUtils.join》
				// StringBuffer 》 +拼接）
				mv.addObject("userDeviceIdStrs", StringUtils.join(userDeviceIds, ","));
			}
			
		} catch (Exception e) {
			LOG.error("【异常】AttachmentSourceController --> toResourceList : " + e.getMessage());
		}
		return mv;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 加载资源文件信息
	 * @param attachmentSourceVO
	 * @return
	 */
	@RequestMapping("/loadResourceList")
	@ResponseBody
	public DataGrid loadResourceList(AttachmentSourceVO attachmentSourceVO) {
		DataGrid dataGrid = new DataGrid();
		try {
			dataGrid = boAttachmentSourceService.loadResourceListInfo(attachmentSourceVO);
		} catch (Exception e) {
			LOG.error("【异常】AttachmentSourceController --> loadResourceList : " + e.getMessage());
		}
		return dataGrid;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 编辑资源文件
	 * @param attachmentSourceVO
	 * @return
	 */
	@RequestMapping("/toResourceEdit")
	public ModelAndView toResourceEdit(AttachmentSourceVO attachmentSourceVO) {
		ModelAndView mv = new ModelAndView("/resource/resourceEdit");
		
		try {
			if (attachmentSourceVO.getResourceId() != null && !"".equals(attachmentSourceVO.getResourceId())) {
				
				BoAttachmentSource boAttachmentSource = boAttachmentSourceService.findBoAttachmentSourceById(attachmentSourceVO.getResourceId());
				
				if (boAttachmentSource != null) {
					mv.addObject("attachmentSourceVO", boAttachmentSourceService.boToVo(boAttachmentSource));
				} else {
					mv = new ModelAndView("redirect:/paramsError.do");
				}
			}
		} catch (Exception e) {
			LOG.error("【异常】AttachmentSourceController --> toResourceEdit : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月1日
	 * @function：TODO 保存添加资源文件
	 * @param attachmentSourceVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveOrUpdateResource")
	@ResponseBody
	public JsonMsgVo saveOrUpdateResource(AttachmentSourceVO attachmentSourceVO, HttpServletRequest request, HttpServletResponse response) {
		JsonMsgVo json = new JsonMsgVo();
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				// 普通request请求对象转换成可以处理文件的multiRequest对象
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取参数名对应的文件列表
				Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
				MultipartFile sourceFile = fileMap.get("resourceFileUpload");
				
				// 新增时检测是否有文件
				if (attachmentSourceVO.getResourceId() == null || "".equals(attachmentSourceVO.getResourceId())) {
					if (sourceFile == null || "".equals(sourceFile)) {
						json.setReflag(false);
						json.setInfoMsg("资源文件为空！");
					} else {
						if ("".equals(sourceFile.getOriginalFilename()) || sourceFile.getSize() == 0) {
							json.setReflag(false);
							json.setInfoMsg("资源文件为空！");
						}
					}
				}
				
				BoAttachmentSource attachmentSource = boAttachmentSourceService.addAndSaveSource(attachmentSourceVO, sourceFile, multiRequest);
				if (attachmentSource != null) {
					// 更新包括文件时，删除服务器中原来的残留文件
					if (attachmentSourceVO.getResourceId() != null || !"".equals(attachmentSourceVO.getResourceId())) {
						if (!"".equals(sourceFile.getOriginalFilename()) || sourceFile.getSize() > 0) {
							
						}
					}
					
					json.setReflag(true);
					json.setInfoMsg("资源文件上传成功！");
				} else {
					json.setReflag(false);
					json.setInfoMsg("资源文件上传失败！");
				}
			} else {
				json.setReflag(false);
				json.setInfoMsg("资源文件为空！");
			}
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】AttachmentSourceController --> saveOrUpdateResource()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月1日
	 * @function：TODO 根据名称下载资源文件
	 * @param attachmentSourceVO
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadResourceByName")
	public void downloadResourceByName(String uniqueName, HttpServletRequest request, HttpServletResponse response) {
		try {
			super.downloadSource(uniqueName.replace(BoAttachmentSource.RESOURCE_DIR, ""), request, response);
		} catch (Exception e) {
			LOG.error("【异常】AttachmentSourceController --> downloadResourceByName()：" + e.getMessage());
		}
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月1日
	 * @function：TODO 删除资源文件
	 * @param attachmentSourceVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteResource")
	@ResponseBody
	public JsonMsgVo deleteResource(AttachmentSourceVO attachmentSourceVO, HttpServletRequest request) {
		JsonMsgVo json = new JsonMsgVo();
		Integer count = 0;
		try {
			count = boAttachmentSourceService.deleteReSourceById(attachmentSourceVO.getResourceIds(), request);
			
			if (count > 0) {
				json.setReflag(true);
				json.setInfoMsg("资源文件删除成功！");
			} else {
				json.setReflag(false);
				json.setInfoMsg("资源文件删除失败！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】AttachmentSourceController --> deleteResource()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 资源推送到设备
	 * @param resourceId
	 * @return
	 */
	@RequestMapping("/pushResourceToDevice")
	public ModelAndView pushResourceToDevice(Integer resourceId) {
		ModelAndView mv = new ModelAndView("/device/deviceList");
		try {
			mv.addObject("resourceId", resourceId);
		} catch (Exception e) {
			LOG.error("【异常】AttachmentSourceController --> pushResourceToDevice()：" + e.getMessage());
		}
		return mv;
	}
	
}
