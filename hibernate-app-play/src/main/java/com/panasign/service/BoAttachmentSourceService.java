package com.panasign.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.panasign.bean.BoAttachmentSource;
import com.panasign.core.DataGrid;
import com.panasign.vo.AttachmentSourceVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：资源文件接口
 */
public interface BoAttachmentSourceService {
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO bo转vo对象
	 * @param boAttachmentSource
	 * @return
	 * @throws Exception
	 */
	public AttachmentSourceVO boToVo(BoAttachmentSource boAttachmentSource) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 加载资源文件信息
	 * @param attachmentSourceVO
	 * @return
	 * @throws Exception
	 */
	public DataGrid loadResourceListInfo(AttachmentSourceVO attachmentSourceVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月1日
	 * @function：TODO 添加保存资源文件
	 * @param AttachmentSourceVO
	 * @return
	 */
	public BoAttachmentSource addAndSaveSource(AttachmentSourceVO attachmentSourceVO, MultipartFile sourceFile, HttpServletRequest request) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据id查找对象
	 * @param resourceId
	 * @return
	 * @throws Exception
	 */
	public BoAttachmentSource findBoAttachmentSourceById(Integer resourceId) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据类型和设备查找对象
	 * @param attachmentSourceVO
	 * @return
	 * @throws Exception
	 */
	public BoAttachmentSource findBoAttachmentSourceByTypeAndDevice(AttachmentSourceVO attachmentSourceVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月2日
	 * @function：TODO 根据id删除资源文件(可批量)
	 * @param sourceIds
	 */
	public Integer deleteReSourceById(Integer[] sourceIds, HttpServletRequest request) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 根据url删除服务器原有的文件资源
	 * @param fileName
	 * @throws Exception
	 */
	public void deleteServerSourceFile(String url) throws Exception;
	
}
