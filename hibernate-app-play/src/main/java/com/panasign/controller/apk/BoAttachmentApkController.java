package com.panasign.controller.apk;

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

import com.panasign.bean.BoAttachmentApk;
import com.panasign.controller.common.BaseController;
import com.panasign.core.DataGrid;
import com.panasign.service.BoAttachmentApkService;
import com.panasign.vo.AttachmentApkVO;
import com.panasign.vo.JsonMsgVo;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月29日
 * @说明：apk资源文件控制调度器
 */
@Controller
@RequestMapping("/apk")
public class BoAttachmentApkController extends BaseController {
	
	private static final Logger LOG = Logger.getLogger(BoAttachmentApkController.class);
	
	@Autowired
	private BoAttachmentApkService boAttachmentApkService;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 进入apk列表页面
	 * @return
	 */
	@RequestMapping("/toApkList")
	public ModelAndView toApkList() {
		ModelAndView mv = new ModelAndView("/apk/apkList");
		return mv;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 加载apk资源信息
	 * @param AttachmentApkVO
	 * @return
	 */
	@RequestMapping("/loadApkList")
	@ResponseBody
	public DataGrid loadApkList(AttachmentApkVO AttachmentApkVO) {
		DataGrid dataGrid = new DataGrid();
		try {
			dataGrid = boAttachmentApkService.loadApkListInfo(AttachmentApkVO);
		} catch (Exception e) {
			LOG.error("【异常】AttachmentApkController --> loadApkList : " + e.getMessage());
		}
		return dataGrid;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 进入apk编辑页面
	 * @return
	 */
	@RequestMapping("/toApkEdit")
	public ModelAndView toApkEdit(AttachmentApkVO attachmentApkVO) {
		ModelAndView mv = new ModelAndView("/apk/apkEdit");
		
		try {
			if (attachmentApkVO.getApkId() != null && !"".equals(attachmentApkVO.getApkId())) {
				
				BoAttachmentApk boAttachmentApk = boAttachmentApkService.findBoAttachmentApkById(attachmentApkVO.getApkId());
				if (boAttachmentApk != null) {
					
					mv.addObject("attachmentApkVO", boAttachmentApkService.boToVo(boAttachmentApk));
				} else {
					mv = new ModelAndView("redirect:/paramsError.do");
				}
			}
		} catch (Exception e) {
			LOG.error("【异常】AttachmentApkController --> toApkEdit : " + e.getMessage());
		}
		
		return mv;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 检查版本号是否已存在(通过versionCode.getClass().getName()查看类型)
	 * @param versionCode
	 * @return
	 */
	@RequestMapping("/checkApkVersionNum")
	@ResponseBody
	public JsonMsgVo checkApkVersionNum(String versionCode) {
		JsonMsgVo json = new JsonMsgVo();
		try {
			BoAttachmentApk boAttachmentApk = boAttachmentApkService.findBoAttachmentApkByVersion(Integer.parseInt(versionCode));
			
			if (boAttachmentApk != null) {
				json.setReflag(false);
				json.setInfoMsg("版本号(" + versionCode + ")已存在！");
			} else {
				json.setReflag(true);
				json.setInfoMsg("版本号(" + versionCode + ")可用！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】AttachmentApkController --> checkApkVersionNum : " + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 添加/修改apk资源
	 * @param attachmentApkVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/saveOrUpdateApk")
	@ResponseBody
	public JsonMsgVo saveOrUpdateApk(AttachmentApkVO attachmentApkVO, HttpServletRequest request, HttpServletResponse response) {
		JsonMsgVo json = new JsonMsgVo();
		BoAttachmentApk boAttachmentApk = null;
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) {
				// 普通request请求对象转换成可以处理文件的multiRequest对象
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取参数名对应的文件列表
				Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
				MultipartFile apkFile = fileMap.get("apkFileUpload");
				
				// 新增时检测,更新时不用
				if (attachmentApkVO.getApkId() == null || "".equals(attachmentApkVO.getApkId())) {
					if ("".equals(apkFile.getOriginalFilename()) || apkFile.getSize() == 0) {
						json.setReflag(false);
						json.setInfoMsg("文件为空！");
						return json;
					}
				}
				
				boAttachmentApk = boAttachmentApkService.addAndSaveApk(attachmentApkVO, apkFile, request);
				
				if (boAttachmentApk == null) {
					json.setReflag(false);
					if (attachmentApkVO.getApkId() != null && !"".equals(attachmentApkVO.getApkId())) { // 更新
						json.setInfoMsg("apk资源更新失败！");
					} else {
						json.setInfoMsg("apk资源保存失败！");
					}
					
				} else {
					json.setReflag(true);
					if (attachmentApkVO.getApkId() != null && !"".equals(attachmentApkVO.getApkId())) { // 更新
						json.setInfoMsg("apk资源更新成功！");
					} else {
						json.setInfoMsg("apk资源保存成功！");
					}
				}
				
			} else {
				json.setReflag(false);
				json.setInfoMsg("文件不存在！");
			}
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】AttachmentApkController --> saveOrUpdateApk()：" + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 删除apk对象
	 * @param AttachmentApkVO
	 * @return
	 */
	@RequestMapping("/deleteApk")
	@ResponseBody
	public JsonMsgVo deleteApk(AttachmentApkVO attachmentApkVO) {
		JsonMsgVo json = new JsonMsgVo();
		int count = 0;
		try {
			count = boAttachmentApkService.deleteApkById(attachmentApkVO.getApkIds());
			
			if (count > 0) {
				json.setReflag(true);
				json.setInfoMsg("apk文件删除成功！");
			} else {
				json.setReflag(false);
				json.setInfoMsg("apk文件删除失败！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("操作出错：" + e.getMessage());
			LOG.error("【异常】AttachmentApkController --> deleteApk : " + e.getMessage());
		}
		return json;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 下载apk附件
	 * @param fileName
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadApk")
	public void downloadApk(String uniqueName, HttpServletRequest request, HttpServletResponse response) {
		
		try {
			super.downloadApk(uniqueName.replace(BoAttachmentApk.APK_DIR, ""), request, response);
		} catch (Exception e) {
			LOG.error("【附件下载异常：】 AttachmentApkController --> downloadApk : " + e.getMessage());
		}
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年9月1日
	 * @function：TODO 下载最高版本apk文件（此为提供给Android终端的外部接口）
	 * @param versionCode
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadLastApk")
	@ResponseBody
	public JsonMsgVo downloadLastApk(Integer versionCode, HttpServletRequest request, HttpServletResponse response) {
		JsonMsgVo json = new JsonMsgVo();
		BoAttachmentApk boAttachmentApk = null;
		Integer lastVersionNum = 0;
		
		try {
			// 版本比较，若版本有升级，则下载最高版本的apk文件
			if (versionCode != null && !"".equals(versionCode)) {
				
				boAttachmentApk = boAttachmentApkService.getLastVersionApk();
				
				if (boAttachmentApk != null) {
					lastVersionNum = boAttachmentApk.getVersionCode();
					
					if (lastVersionNum > versionCode) {
						
						super.downloadApk(boAttachmentApk.getUniqueName(), request, response);
					} else {
						json.setReflag(false);
						json.setInfoMsg("当前版本已是最新版！");
					}
					
				} else {
					json.setReflag(false);
					json.setInfoMsg("获得最高版本出错！");
				}
				
			} else {
				json.setReflag(false);
				json.setInfoMsg("版本参数错误！");
			}
			
		} catch (Exception e) {
			json.setReflag(false);
			json.setInfoMsg("apk安装包下载出错：" + e.getMessage());
			LOG.error("【附件下载异常：】 AttachmentApkController --> downloadLastApk : " + e.getMessage());
		}
		return json;
	}
	
}
