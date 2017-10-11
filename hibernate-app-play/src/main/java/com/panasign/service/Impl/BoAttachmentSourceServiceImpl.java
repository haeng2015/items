package com.panasign.service.Impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.panasign.bean.BoAttachmentSource;
import com.panasign.core.DataGrid;
import com.panasign.dao.BaseDaoInterface;
import com.panasign.service.BoAttachmentSourceService;
import com.panasign.utils.DateTimeUtil;
import com.panasign.utils.FileUnitConversionUtils;
import com.panasign.vo.AttachmentSourceVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：资源文件接口实现类
 */
@Service("boAttachmentSourceService")
public class BoAttachmentSourceServiceImpl implements BoAttachmentSourceService {
	
	private static BaseDaoInterface<BoAttachmentSource> dao;
	
	public BaseDaoInterface<BoAttachmentSource> getDao() {
		return dao;
	}
	
	@Resource
	public void setDao(BaseDaoInterface<BoAttachmentSource> dao) {
		BoAttachmentSourceServiceImpl.dao = dao;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO bo转vo对象
	 * @param boAttachmentSource
	 * @return
	 * @throws Exception
	 */
	@Override
	public AttachmentSourceVO boToVo(BoAttachmentSource boAttachmentSource) throws Exception {
		if (boAttachmentSource != null && !"".equals(boAttachmentSource)) {
			AttachmentSourceVO attachmentSourceVO = new AttachmentSourceVO();
			BeanUtils.copyProperties(boAttachmentSource, attachmentSourceVO); // 只复制名称相同的属性
			
			attachmentSourceVO.setResourceId(boAttachmentSource.getId());
			attachmentSourceVO.setFileSize(FileUnitConversionUtils.getPrintSize(boAttachmentSource.getSize()));
			attachmentSourceVO.setCreateDate(DateTimeUtil.timestampToString(boAttachmentSource.getCreateTime()));
			attachmentSourceVO.setUpdateDate(DateTimeUtil.timestampToString(boAttachmentSource.getUpdateTime()));
			
			return attachmentSourceVO;
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 加载资源文件信息
	 * @param attachmentSourceVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataGrid loadResourceListInfo(AttachmentSourceVO attachmentSourceVO) throws Exception {
		DataGrid dataGrid = new DataGrid();
		if (attachmentSourceVO != null && !"".equals(attachmentSourceVO)) {
			Map<String, Object> params = new HashMap<String, Object>();
			
			String hqlCount = "select count(*) ";
			StringBuffer hql = new StringBuffer(" from BoAttachmentSource source where source.isDeleted=:isDeleted ");
			params.put("isDeleted", "0");
			
			// 条件：文件类型查找
			if (!"".equals(attachmentSourceVO.getType()) && attachmentSourceVO.getType() != null) {
				hql.append(" and source.type like '%" + attachmentSourceVO.getType() + "%' ");
			}
			
			// 条件：版本名称查找
			if (!"".equals(attachmentSourceVO.getFileName()) && attachmentSourceVO.getFileName() != null) {
				hql.append(" and source.fileName like '%" + attachmentSourceVO.getFileName() + "%' ");
			}
			
			// 条件：发布日期查找
			if (!"".equals(attachmentSourceVO.getSearchBeginTime()) && attachmentSourceVO.getSearchBeginTime() != null) {
				hql.append(" and source.createTime>=:startDate ");
				params.put("startDate", DateTimeUtil.stringToTimestamp1(attachmentSourceVO.getSearchBeginTime()));
			}
			if (!"".equals(attachmentSourceVO.getSearchEndTime()) && attachmentSourceVO.getSearchEndTime() != null) {
				hql.append(" and source.createTime<=:endDate ");
				params.put("endDate", DateTimeUtil.stringToTimestamp1(attachmentSourceVO.getSearchEndTime()));
			}
			
			dataGrid.setTotal(dao.count(hqlCount + hql.toString(), params));
			
			// 排序
			if (attachmentSourceVO.getSort() != null && !"".equals(attachmentSourceVO.getSort())) {
				
				if ("createDate".equals(attachmentSourceVO.getSort())) {
					hql.append(" order by ").append("source.createTime ").append(attachmentSourceVO.getOrder());
				} else if ("updateDate".equals(attachmentSourceVO.getSort())) {
					hql.append(" order by ").append("source.updateTime ").append(attachmentSourceVO.getOrder());
				} else if ("fileSize".equals(attachmentSourceVO.getSort())) {
					hql.append(" order by ").append("source.size ").append(attachmentSourceVO.getOrder());
				} else {
					hql.append(" order by ").append(attachmentSourceVO.getSort()).append(" ").append(attachmentSourceVO.getOrder());
				}
				
			} else {
				hql.append(" order by source.createTime desc ");
			}
			
			List<BoAttachmentSource> boAttachmentApkList = dao.find(hql.toString(), params, attachmentSourceVO.getPage(), attachmentSourceVO.getRows());
			
			List<AttachmentSourceVO> attachmentSourceVOs = new ArrayList<AttachmentSourceVO>();
			
			if (boAttachmentApkList != null && boAttachmentApkList.size() > 0) {
				for (BoAttachmentSource boAttachmentSource : boAttachmentApkList) {
					attachmentSourceVOs.add(boToVo(boAttachmentSource));
				}
			}
			dataGrid.setRows(attachmentSourceVOs);
		}
		return dataGrid;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月1日
	 * @function：TODO 添加保存资源文件
	 * @param attachmentSourceVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentSource addAndSaveSource(AttachmentSourceVO attachmentSourceVO, MultipartFile sourceFile, HttpServletRequest request) throws Exception {
		
		if (sourceFile == null || attachmentSourceVO == null) {
			return null;
		}
		
		BoAttachmentSource boAttachmentSource = null;
		
		if (attachmentSourceVO.getResourceId() != null && !"".equals(attachmentSourceVO.getResourceId())) { // 更新
			boAttachmentSource = findBoAttachmentSourceById(attachmentSourceVO.getResourceId());
			boAttachmentSource.setUpdateTime(new Timestamp(new Date().getTime()));
			
		} else {
			
			if (sourceFile.getSize() > 0 && !sourceFile.isEmpty()) { // 移除空文件的字段
				boAttachmentSource = new BoAttachmentSource();
				
				boAttachmentSource.setIsDeleted("0");
				boAttachmentSource.setCreateTime(new Timestamp(new Date().getTime()));
			}
		}
		
		// 更新和新增公用字段
		boAttachmentSource.setType(attachmentSourceVO.getType());
		boAttachmentSource.setRemarks(attachmentSourceVO.getRemarks());
		
		// 防止更新时文件为空
		if (!"".equals(sourceFile.getOriginalFilename()) || sourceFile.getSize() > 0) {
			
			// 文件上传时的名字
			boAttachmentSource.setFileName(sourceFile.getOriginalFilename());
			boAttachmentSource.setSize(sourceFile.getSize());
			// 系统中随机的UUID名字，这是实际存放的文件名
			boAttachmentSource.setUniqueName(UUID.randomUUID().toString().replace("-", "")
					+ boAttachmentSource.getFileName().substring(boAttachmentSource.getFileName().lastIndexOf("."), boAttachmentSource.getFileName().length()));
			
			boAttachmentSource.setUri("/" + BoAttachmentSource.RESOURCE_DIR + boAttachmentSource.getUniqueName());
			
			// 文件要保存的实际路径
			String path = request.getServletContext().getRealPath("/") + BoAttachmentSource.RESOURCE_DIR + boAttachmentSource.getUniqueName();
			// 保存上传的文件
			sourceFile.transferTo(new File(path));
		}
		
		// 保存数据库对象
		dao.saveOrUpdate(boAttachmentSource);
		
		return boAttachmentSource;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据id查找对象
	 * @param resourceId
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentSource findBoAttachmentSourceById(Integer resourceId) throws Exception {
		if (resourceId != null && !"".equals(resourceId)) {
			return dao.get(BoAttachmentSource.class, resourceId);
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月2日
	 * @function：TODO 根据id删除资源文件(可批量)
	 * @param attachmentSourceVO
	 * @throws Exception
	 */
	@Override
	public Integer deleteReSourceById(Integer[] sourceIds, HttpServletRequest request) throws Exception {
		
		int count = 0;
		if (sourceIds != null && sourceIds.length > 0) {
			BoAttachmentSource boAttachmentSource = null;
			
			for (int i = 0, length = sourceIds.length; i < length; i++) {
				
				boAttachmentSource = findBoAttachmentSourceById(sourceIds[i]);
				if (boAttachmentSource == null) {
					break;
				}
				
				boAttachmentSource.setIsDeleted("1");
				dao.update(boAttachmentSource);
				
				// 删除服务器文件资源
//				deleteServerSourceFile(request.getSession().getServletContext().getRealPath("") + boAttachmentSource.getUri());
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据类型和设备查找对象
	 * @param attachmentSourceVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentSource findBoAttachmentSourceByTypeAndDevice(AttachmentSourceVO attachmentSourceVO) throws Exception {
		
		if (attachmentSourceVO != null && !"".equals(attachmentSourceVO)) {
			
			Map<String, Object> params = new HashMap<String, Object>(3);
			String hql = "from BoAttachmentSource source where source.isDeleted=:isDeleted and source.type=:type and source.boAppDevice.deviceId=:deviceId";
			params.put("isDeleted", "0");
			params.put("type", attachmentSourceVO.getType());
			params.put("deviceId", attachmentSourceVO.getDeviceId());
			
			return dao.get(hql, params);
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年9月12日
	 * @function：TODO 根据url删除资源文件
	 * @param url
	 * @throws Exception
	 */
	@Override
	public void deleteServerSourceFile(String url) throws Exception {
		if (url != null && !"".equals(url)) {
			
			File file = new File(url);
			file.delete();
		}
	}
	
}
