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

import com.panasign.bean.BoAttachmentApk;
import com.panasign.core.DataGrid;
import com.panasign.dao.BaseDaoInterface;
import com.panasign.service.BoAttachmentApkService;
import com.panasign.utils.DateTimeUtil;
import com.panasign.utils.FileUnitConversionUtils;
import com.panasign.vo.AttachmentApkVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：apk文件接口实现类
 */
@Service("boAttachmentApkService")
public class BoAttachmentApkServiceImpl implements BoAttachmentApkService {
	
	private BaseDaoInterface<BoAttachmentApk> dao;
	
	public BaseDaoInterface<BoAttachmentApk> getDao() {
		return dao;
	}
	
	@Resource
	public void setDao(BaseDaoInterface<BoAttachmentApk> dao) {
		this.dao = dao;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO bo转vo对象
	 * @param boAttachmentApk
	 * @return
	 * @throws Exception
	 */
	@Override
	public AttachmentApkVO boToVo(BoAttachmentApk boAttachmentApk) throws Exception {
		if (boAttachmentApk != null && !"".equals(boAttachmentApk)) {
			AttachmentApkVO attachmentApkVO = new AttachmentApkVO();
			BeanUtils.copyProperties(boAttachmentApk, attachmentApkVO); // 只复制名称相同的属性
			
			attachmentApkVO.setApkId(boAttachmentApk.getId());
			attachmentApkVO.setFileSize(FileUnitConversionUtils.getPrintSize(boAttachmentApk.getSize()));
			attachmentApkVO.setCreateDate(DateTimeUtil.timestampToString(boAttachmentApk.getCreateTime()));
			attachmentApkVO.setUpdateDate(DateTimeUtil.timestampToString(boAttachmentApk.getUpdateTime()));
			
			return attachmentApkVO;
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 加载apk资源信息
	 * @param AttachmentApkVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public DataGrid loadApkListInfo(AttachmentApkVO attachmentApkVO) throws Exception {
		DataGrid dataGrid = new DataGrid();
		if (attachmentApkVO != null && !"".equals(attachmentApkVO)) {
			Map<String, Object> params = new HashMap<String, Object>(3);
			
			String hqlCount = "select count(*) ";
			StringBuffer hql = new StringBuffer(" from BoAttachmentApk apk where apk.isDeleted=:isDeleted ");
			params.put("isDeleted", "0");
			
			// 条件：版本号查找
			if (!"".equals(attachmentApkVO.getVersionCode()) && attachmentApkVO.getVersionCode() != null) {
				hql.append(" and apk.versionCode like '%" + attachmentApkVO.getVersionCode() + "%' ");
			}
			
			// 条件：版本名称查找
			if (!"".equals(attachmentApkVO.getFileName()) && attachmentApkVO.getFileName() != null) {
				hql.append(" and apk.fileName like '%" + attachmentApkVO.getFileName() + "%' ");
			}
			
			// 条件：发布日期查找
			if (!"".equals(attachmentApkVO.getSearchBeginTime()) && attachmentApkVO.getSearchBeginTime() != null) {
				hql.append(" and apk.createTime>=:startDate ");
				params.put("startDate", DateTimeUtil.stringToTimestamp1(attachmentApkVO.getSearchBeginTime()));
			}
			if (!"".equals(attachmentApkVO.getSearchEndTime()) && attachmentApkVO.getSearchEndTime() != null) {
				hql.append(" and apk.createTime<=:endDate ");
				params.put("endDate", DateTimeUtil.stringToTimestamp1(attachmentApkVO.getSearchEndTime()));
			}
			
			dataGrid.setTotal(dao.count(hqlCount + hql.toString(), params));
			
			// 排序
			if (attachmentApkVO.getSort() != null && !"".equals(attachmentApkVO.getSort())) {
				if ("createDate".equals(attachmentApkVO.getSort())) {
					hql.append(" order by ").append("apk.createTime ").append(attachmentApkVO.getOrder());
				} else if ("updateDate".equals(attachmentApkVO.getSort())) {
					hql.append(" order by ").append("apk.updateTime ").append(attachmentApkVO.getOrder());
				} else if ("fileSize".equals(attachmentApkVO.getSort())) {
					hql.append(" order by ").append("apk.size ").append(attachmentApkVO.getOrder());
				} else {
					hql.append(" order by ").append(attachmentApkVO.getSort()).append(" ").append(attachmentApkVO.getOrder());
				}
			} else {
				hql.append(" order by apk.versionCode desc ");
			}
			
			List<BoAttachmentApk> BoAttachmentApkList = dao.find(hql.toString(), params, attachmentApkVO.getPage(), attachmentApkVO.getRows());
			
			List<AttachmentApkVO> AttachmentApkVOs = new ArrayList<AttachmentApkVO>();
			
			if (BoAttachmentApkList != null && BoAttachmentApkList.size() > 0) {
				for (BoAttachmentApk boAttachmentApk : BoAttachmentApkList) {
					AttachmentApkVOs.add(boToVo(boAttachmentApk));
				}
			}
			dataGrid.setRows(AttachmentApkVOs);
		}
		return dataGrid;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月24日
	 * @function：TODO 根据id查找apk对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentApk findBoAttachmentApkById(Integer apkId) throws Exception {
		if (apkId == null) {
			return null;
		}
		return dao.get(BoAttachmentApk.class, apkId);
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 通过版本号查找对象
	 * @param versionCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentApk findBoAttachmentApkByVersion(Integer versionCode) throws Exception {
		if (versionCode != null && !"".equals(versionCode)) {
			
			Map<String, Object> params = new HashMap<String, Object>(2);
			
			String hql = " from BoAttachmentApk apk where apk.isDeleted=:isDeleted and apk.versionCode=:versionCode ";
			params.put("isDeleted", "0");
			params.put("versionCode", versionCode);
			
			return dao.get(hql, params);
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 添加保存apk对象
	 * @param AttachmentApkVO
	 * @param apkFile
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentApk addAndSaveApk(AttachmentApkVO attachmentApkVO, MultipartFile apkFile, HttpServletRequest request) throws Exception {
		
		if (attachmentApkVO == null || apkFile == null) {
			return null;
		}
		
		BoAttachmentApk boAttachmentApk = null;
		
		if (attachmentApkVO.getApkId() != null && !"".equals(attachmentApkVO.getApkId())) { // 更新
			boAttachmentApk = findBoAttachmentApkById(attachmentApkVO.getApkId());
			boAttachmentApk.setUpdateTime(new Timestamp(new Date().getTime()));
			
		} else { // 新增
			boAttachmentApk = new BoAttachmentApk();
			
			boAttachmentApk.setIsDeleted("0");
			boAttachmentApk.setCreateTime(new Timestamp(new Date().getTime()));
			
		}
		// 版本号
		boAttachmentApk.setVersionCode(attachmentApkVO.getVersionCode());
		
		if (attachmentApkVO.getRemarks() != null && !"".equals(attachmentApkVO.getRemarks())) {
			boAttachmentApk.setRemarks(attachmentApkVO.getRemarks());
		}
		
		if (!"".equals(apkFile.getOriginalFilename()) && apkFile.getSize() > 0) {
			
			// 文件大小
			boAttachmentApk.setSize(apkFile.getSize());
			
			// 文件上传时的名字
			boAttachmentApk.setFileName(apkFile.getOriginalFilename());
			
			// 版本名称(从文件名中截取获得，如：myapk-2.4.5.zip)
			boAttachmentApk.setVersionName(boAttachmentApk.getFileName().substring(boAttachmentApk.getFileName().indexOf('-') + 1, boAttachmentApk.getFileName().lastIndexOf('.')));
			
			// 系统中随机的UUID名字，这是实际存放的文件名
			boAttachmentApk.setUniqueName(UUID.randomUUID().toString().replace("-", "")
					+ boAttachmentApk.getFileName().substring(boAttachmentApk.getFileName().lastIndexOf("."), boAttachmentApk.getFileName().length()));
			
			boAttachmentApk.setUri("/" + BoAttachmentApk.APK_DIR + boAttachmentApk.getUniqueName());
			// 文件要保存的实际路径
			String path = request.getServletContext().getRealPath("/") + BoAttachmentApk.APK_DIR + boAttachmentApk.getUniqueName();
			// 保存上传的文件
			apkFile.transferTo(new File(path));
		}
		
		// 保存数据库对象
		dao.saveOrUpdate(boAttachmentApk);
		
		return boAttachmentApk;
	}
	
	public static void main(String[] args) {
		String name = "js弹框选择-3.4.65.rar";
		
		System.out.println(name.substring(name.indexOf('-') + 1, name.lastIndexOf('.')));
		// System.out.println(name.split("-")[1].split("."));
		
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 根据id删除apk对象（可批量）
	 * @param apkIds
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer deleteApkById(Integer[] apkIds) throws Exception {
		
		int count = 0;
		if (apkIds != null && apkIds.length > 0) {
			BoAttachmentApk boAttachmentApk = null;
			
			for (int i = 0, length = apkIds.length; i < length; i++) {
				
				boAttachmentApk = findBoAttachmentApkById(apkIds[i]);
				if (boAttachmentApk == null) {
					break;
				}
				
				boAttachmentApk.setIsDeleted("1");
				dao.update(boAttachmentApk);
				
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月25日
	 * @function：TODO 获得最高版本的apk资源
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoAttachmentApk getLastVersionApk() throws Exception {
		
		return dao.find("FROM BoAttachmentApk apk WHERE apk.isDeleted='0' ORDER BY apk.versionCode DESC", false).get(0);
		
	}
	
}
