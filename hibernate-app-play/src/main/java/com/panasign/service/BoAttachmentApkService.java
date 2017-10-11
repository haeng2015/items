package com.panasign.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.panasign.bean.BoAttachmentApk;
import com.panasign.core.DataGrid;
import com.panasign.vo.AttachmentApkVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：apk文件接口
 */
//@net.bull.javamelody.MonitoredWithSpring
public interface BoAttachmentApkService {
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO bo转vo对象
	 * @param boAttachmentApk
	 * @return
	 * @throws Exception
	 */
	public abstract AttachmentApkVO boToVo(BoAttachmentApk boAttachmentApk) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 加载apk资源信息
	 * @param AttachmentApkVO
	 * @return
	 * @throws Exception
	 */
	abstract DataGrid loadApkListInfo(AttachmentApkVO AttachmentApkVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 根据id查找apk对象
	 * @param apkId
	 * @return
	 * @throws Exception
	 */
	public BoAttachmentApk findBoAttachmentApkById(Integer apkId) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 通过版本号查找对象
	 * @param versionCode
	 * @return
	 * @throws Exception
	 */
	BoAttachmentApk findBoAttachmentApkByVersion(Integer versionCode) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 添加保存apk对象
	 * @param attachmentApkVO
	 * @param apkFile
	 * @param request
	 * @return
	 * @throws Exception
	 */
	BoAttachmentApk addAndSaveApk(AttachmentApkVO attachmentApkVO, MultipartFile apkFile, HttpServletRequest request) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 根据id删除apk对象（可批量）
	 * @param apkIds
	 * @return
	 * @throws Exception
	 */
	Integer deleteApkById(Integer[] apkIds) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 获得最高版本的apk资源
	 * @return
	 * @throws Exception
	 */
	BoAttachmentApk getLastVersionApk() throws Exception;
	
}
