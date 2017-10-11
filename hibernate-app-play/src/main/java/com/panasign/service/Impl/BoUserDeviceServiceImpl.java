package com.panasign.service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panasign.bean.BoAttachmentSource;
import com.panasign.bean.BoUserDevice;
import com.panasign.core.TreeGrid;
import com.panasign.dao.BaseDaoInterface;
import com.panasign.service.BoAttachmentSourceService;
import com.panasign.service.BoUserDeviceService;
import com.panasign.utils.DateTimeUtil;
import com.panasign.utils.FileUnitConversionUtils;
import com.panasign.vo.UserDeviceVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：用户设备接口实现类
 */
@Service("boUserDeviceService")
public class BoUserDeviceServiceImpl implements BoUserDeviceService {
	
	private static BaseDaoInterface<BoUserDevice> dao;
	
	public BaseDaoInterface<BoUserDevice> getDao() {
		return dao;
	}
	
	@Resource
	public void setDao(BaseDaoInterface<BoUserDevice> dao) {
		BoUserDeviceServiceImpl.dao = dao;
	}
	
	@Autowired
	public BoAttachmentSourceService boAttachmentSourceService;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO bo转vo对象
	 * @param boUserDevice
	 * @return
	 * @throws Exception
	 */
	@Override
	public UserDeviceVO boToVo(BoUserDevice boUserDevice) throws Exception {
		if (boUserDevice != null && !"".equals(boUserDevice)) {
			UserDeviceVO userDeviceVO = new UserDeviceVO();
			BeanUtils.copyProperties(boUserDevice, userDeviceVO); // 只复制名称相同的属性
			
			userDeviceVO.setUserDeviceId(boUserDevice.getId());
			userDeviceVO.setCreateDate(DateTimeUtil.timestampToString(boUserDevice.getCreateTime()));
			
			if (boUserDevice.getUpdateTime() != null && !"".equals(boUserDevice.getUpdateTime())) {
				userDeviceVO.setUpdateDate(DateTimeUtil.timestampToString(boUserDevice.getUpdateTime()));
			} else {
				userDeviceVO.setUpdateDate("未更新");
			}
			
			// 资源信息
			if (boUserDevice.getBoAttachmentSource() != null && !"".equals(boUserDevice.getBoAttachmentSource())) {
				
				userDeviceVO.setFileName(boUserDevice.getBoAttachmentSource().getFileName());
				userDeviceVO.setFileSize(FileUnitConversionUtils.getPrintSize(boUserDevice.getBoAttachmentSource().getSize()));
				userDeviceVO.setType(boUserDevice.getBoAttachmentSource().getType());
			}
			
			return userDeviceVO;
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 加载所有用户信息，包括其下的所有设备信息（找到所有的父用户信息）
	 * @return
	 * @throws Exception
	 */
	@Override
	public TreeGrid loadDeviceListInfoByTree(UserDeviceVO userDeviceVO) throws Exception {
		TreeGrid treeGrid = new TreeGrid();
		
		if (userDeviceVO == null) {
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>(1);
		
		String hqlCount = "select count(*) ";
		StringBuffer hql = new StringBuffer(" from BoUserDevice device where device.isDeleted=:isDeleted ");
		params.put("isDeleted", "0");
		
		// 加载已推送的设备信息
		if ("1".equals(userDeviceVO.getMark())) {
			
			// 条件：用户名称
			if (!"".equals(userDeviceVO.getUserName()) && userDeviceVO.getUserName() != null) {
				
				// 父用户
				hql.append(" and device.boUserDevice.id is null and device.id =" + userDeviceVO.getUserName());
				
				// 防止or省略后面的语句，所以执行两遍此语句(由于在数据库中有的父用户节点在子设备节点的前面，有的在后面，导致此问题)
				hql.append(" and device.boAttachmentSource is not null ");
				
				// 子设备
				hql.append(" or device.boUserDevice.id is not null and device.boUserDevice.id =" + userDeviceVO.getUserName());
			}
			
			hql.append(" and device.boAttachmentSource is not null ");
			
			// 条件：设备名称查找
			if (!"".equals(userDeviceVO.getDeviceName()) && userDeviceVO.getDeviceName() != null) {
				hql.append(" and device.deviceName like '%" + userDeviceVO.getDeviceName() + "%' ");
			}
			
			// 条件：设备ID查找
			if (!"".equals(userDeviceVO.getDeviceId()) && userDeviceVO.getDeviceId() != null) {
				hql.append(" and device.deviceId like '%" + userDeviceVO.getDeviceId() + "%' ");
			}
			
		} else {
			// 设备名称、ID查找
			if (!"".equals(userDeviceVO.getDeviceName()) && userDeviceVO.getDeviceName() != null || !"".equals(userDeviceVO.getDeviceId()) && userDeviceVO.getDeviceId() != null) {
				
				// 条件：设备名称查找
				if (!"".equals(userDeviceVO.getDeviceName()) && userDeviceVO.getDeviceName() != null) {
					hql.append(" and device.deviceName like '%" + userDeviceVO.getDeviceName() + "%' ");
				}
				
				// 条件：设备ID查找
				if (!"".equals(userDeviceVO.getDeviceId()) && userDeviceVO.getDeviceId() != null) {
					hql.append(" and device.deviceId like '%" + userDeviceVO.getDeviceId() + "%' ");
				}
				
			} else {
				
				hql.append(" and device.boUserDevice is null ");
				
				// 条件：用户名称
				if (!"".equals(userDeviceVO.getUserName()) && userDeviceVO.getUserName() != null) {
					hql.append(" and device.userName like '%" + userDeviceVO.getUserName() + "%' ");
				}
			}
		}
		
		// 总条数
		Long total = dao.count(hqlCount + hql.toString(), params);
		
		// 排序
		if (userDeviceVO.getSort() != null && !"".equals(userDeviceVO.getSort())) {
			hql.append(" order by ").append(userDeviceVO.getSort()).append(" ").append(userDeviceVO.getOrder());
		} else {
			hql.append(" order by device.deviceId desc ");
		}
		
		List<BoUserDevice> boUserDeviceList = dao.find(hql.toString(), params, userDeviceVO.getPage(), userDeviceVO.getRows());
		
		// 所有父用户信息
		List<UserDeviceVO> userDeviceVOs = new ArrayList<UserDeviceVO>();
		
		if (boUserDeviceList != null && boUserDeviceList.size() > 0) {
			for (BoUserDevice boUserDevice : boUserDeviceList) {
				userDeviceVOs.add(boToVo(boUserDevice));
			}
		}
		
		// 转换加载已推送的设备信息
		if ("1".equals(userDeviceVO.getMark())) {
			
			userDeviceVOs = childNodeUserName(boUserDeviceList);
		} else {
			
			//条件查询时，查找其父节点用户名
			if (!"".equals(userDeviceVO.getDeviceName()) && userDeviceVO.getDeviceName() != null || !"".equals(userDeviceVO.getDeviceId()) && userDeviceVO.getDeviceId() != null) {
				
				userDeviceVOs = childNodeUserName(boUserDeviceList);
				
			} else {
				// 根据父用户获得所有子设备信息，并转换为树结构
				userDeviceVOs = getAllChildTreeByPid(userDeviceVOs);
			}
		}
		
		treeGrid.setTotal(total);
		treeGrid.setRows(userDeviceVOs);
		
		return treeGrid;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 如果子设备没有父用户名，则添加
	 * @param userDeviceVOs
	 * @return
	 * @throws Exception
	 */
	public List<UserDeviceVO> childNodeUserName(List<BoUserDevice> boUserDeviceList) throws Exception {
		
		List<UserDeviceVO> userDeviceVOs = new ArrayList<UserDeviceVO>();
		
		if (boUserDeviceList != null && boUserDeviceList.size() > 0) {
			
			BoUserDevice boUserDevice = null;
			
			for (BoUserDevice userDevice : boUserDeviceList) {
				
				// 判断是否为子设备节点（username为空，pid不为空）
				if (userDevice.getBoUserDevice() != null && !"".equals(userDevice.getBoUserDevice())) {
					
					// 找到其父节点用户
					boUserDevice = findBoUserDeviceById(userDevice.getBoUserDevice().getId());
					// 为子设备赋值父用户名
					userDevice.setUserName(boUserDevice.getUserName());
					
				}
				
				// 转换为vo对象
				userDeviceVOs.add(boToVo(userDevice));
			}
		}
		return userDeviceVOs;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 根据父用户id查找子设备信息
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BoUserDevice> findBoUserDeviceByPid(Integer pid) throws Exception {
		if (pid != null && !"".equals(pid)) {
			Map<String, Object> params = new HashMap<String, Object>(2);
			
			String hql = "from BoUserDevice device where device.isDeleted=:isDeleted and device.boUserDevice.id=:boUserDevice ";
			params.put("isDeleted", "0");
			params.put("boUserDevice", pid);
			
			return dao.find(hql, params);
			
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 根据父用户获得所有子设备信息
	 * @param userParents
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<UserDeviceVO> getAllChildTreeByPid(List<UserDeviceVO> userDeviceVOs) throws Exception {
		
		List<UserDeviceVO> userDeviceVOList = new ArrayList<UserDeviceVO>();
		
		for (UserDeviceVO userDeviceVO : userDeviceVOs) {
			recursionChildTreeNode(userDeviceVO);
			userDeviceVOList.add(userDeviceVO);
		}
		
		return userDeviceVOList;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 递归父用户下所有子设备节点信息（转换树结构）
	 * @param boUserDevice
	 *                父用户对象
	 * @throws Exception
	 */
	@Override
	public void recursionChildTreeNode(UserDeviceVO userDeviceVO) throws Exception {
		List<UserDeviceVO> userDeviceVOList = new ArrayList<UserDeviceVO>();
		
		if (userDeviceVO != null && !"".equals(userDeviceVO)) {
			List<BoUserDevice> boUserDeviceList = findBoUserDeviceByPid(userDeviceVO.getUserDeviceId());
			
			// 所有父用户信息,转换为vo对象
			if (boUserDeviceList != null && boUserDeviceList.size() > 0) {
				for (BoUserDevice boUserDevice : boUserDeviceList) {
					userDeviceVOList.add(boToVo(boUserDevice));
				}
			}
			
		}
		
		if (userDeviceVOList != null && userDeviceVOList.size() > 0) {
			
			userDeviceVO.setChildren(userDeviceVOList);
			
			if (userDeviceVOList.size() > 1) {
				userDeviceVO.setState("open");
			} else {
				userDeviceVO.setState("close");
			}
			
			// 继续寻找下一组织节点
			for (UserDeviceVO userDevice : userDeviceVOList) {
				
				if (getAllChildTreeByPid(userDeviceVOList).size() > 0) {
					recursionChildTreeNode(userDevice);
				}
			}
		}
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据设备id查找对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoUserDevice findBoUserDeviceById(Integer id) throws Exception {
		if (id != null && !"".equals(id)) {
			return dao.get(BoUserDevice.class, id);
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 根据设备id查找对象
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoUserDevice findBoUserDeviceByDeviceId(String deviceId) throws Exception {
		
		if (deviceId == null || "".equals(deviceId)) {
			return null;
		}
		
		Map<String, Object> params = new HashMap<String, Object>(2);
		
		String hql = "from BoUserDevice device where device.isDeleted=:isDeleted and device.deviceId=:deviceId ";
		
		params.put("isDeleted", "0");
		params.put("deviceId", deviceId);
		
		return dao.get(hql, params);
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 根据用户名查找
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoUserDevice findBoUserDeviceByUserName(String userName) throws Exception {
		
		if (userName != null && !"".equals(userName)) {
			Map<String, Object> params = new HashMap<String, Object>();
			
			String hql = "from BoUserDevice device where device.isDeleted=:isDeleted and device.userName=:userName and device.boUserDevice is null";
			
			params.put("isDeleted", "0");
			params.put("userName", userName);
			
			return dao.get(hql, params);
		}
		return null;
		
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年9月1日
	 * @function：TODO 获得所有用户名（父节点）
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<UserDeviceVO> findAllBoUserDeviceUserName() throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String hql = "from BoUserDevice device where device.isDeleted=:isDeleted and device.boUserDevice.id is null";
		
		params.put("isDeleted", "0");
		
		List<BoUserDevice> boUserDeviceList = dao.find(hql, params);
		
		List<UserDeviceVO> userDeviceVOs = new ArrayList<UserDeviceVO>();
		
		if (boUserDeviceList != null && boUserDeviceList.size() > 0) {
			for (BoUserDevice boUserDevice : boUserDeviceList) {
				userDeviceVOs.add(boToVo(boUserDevice));
			}
		}
		
		return userDeviceVOs;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 保存添加设备
	 * @param userDeviceVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public BoUserDevice addAndSaveDevice(UserDeviceVO userDeviceVO) throws Exception {
		if (userDeviceVO != null && !"".equals(userDeviceVO)) {
			BoUserDevice boUserDevice = null;
			BoUserDevice userDevice = null;
			
			// 如果该用户名已存在，则添加为其用户下的子设备
			if (userDeviceVO.getUserName() != null && !"".equals(userDeviceVO.getUserName())) {
				userDevice = findBoUserDeviceByUserName(userDeviceVO.getUserName());
			}
			
			if (userDeviceVO.getUserDeviceId() != null && !"".equals(userDeviceVO.getUserDeviceId())) { // 更新
				boUserDevice = findBoUserDeviceById(userDeviceVO.getUserDeviceId());
				
				// 如果更新存在该用户（两种情况：1、更新父节点用户；2、更新子设备）
				if (userDevice != null) {
					
					// 如果该对象是子节点设备（pid不为空，用户名为空），则需要将username制空，且pid指向父节点的id
					if (boUserDevice.getBoUserDevice() != null && !"".equals(boUserDevice.getBoUserDevice())) {
						
						boUserDevice.setBoUserDevice(userDevice);
						userDeviceVO.setUserName("");
						boUserDevice.setUpdateTime(new Timestamp(new Date().getTime()));
					} else {
						// 如果该对象是父节点用户信息时（pid为空，用户名不为空），
						// 将该用户添加为其他用户的子节点设备，即【添加pid参数，制空用户名】.
						// （如果该用户存在子节点
						// ，则将其下的所有子设备也一并添加为其他用户的子设备
						// ，即【修改所有子设备的pid参数】）
						
						if (!userDevice.getUserName().equals(boUserDevice.getUserName())) { // 如果用户名字未改变，只改变了其他canshu
						
							// 用户名改变时，添加pid，制空用户名
							boUserDevice.setBoUserDevice(userDevice);
							userDeviceVO.setUserName("");
							
							// 更改所有子设备
							updateUserDevice(boUserDevice.getId(), userDevice);
						}
					}
				}
				
			} else { // 新增
				boUserDevice = new BoUserDevice();
				
				boUserDevice.setIsDeleted("0");
				boUserDevice.setCreateTime(new Timestamp(new Date().getTime()));
				boUserDevice.setUpdateTime(null);
				
				// 如果已经存在该用户，则将其设备添加为该用户的子设备即可，用户名制空
				if (userDevice != null) {
					boUserDevice.setBoUserDevice(userDevice);
					userDeviceVO.setUserName("");
				}
			}
			
			boUserDevice.setUserName(userDeviceVO.getUserName());
			boUserDevice.setDeviceId(userDeviceVO.getDeviceId());
			boUserDevice.setDeviceName(userDeviceVO.getDeviceName());
			boUserDevice.setDeviceNum(userDeviceVO.getDeviceNum());
			boUserDevice.setDevicePlace(userDeviceVO.getDevicePlace());
			boUserDevice.setRemarks(userDeviceVO.getRemarks());
			
			dao.saveOrUpdate(boUserDevice);
			
			return boUserDevice;
		}
		return null;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 更改所有子设备的父节点
	 * @param pid
	 * @param boUserDevice
	 * @return
	 * @throws Exception
	 */
	public Integer updateUserDevice(Integer pid, BoUserDevice boUserDevice) throws Exception {
		
		if (boUserDevice == null) {
			return 0;
		}
		
		Integer count = 0;
		List<BoUserDevice> userDevices = null;
		
		if (pid != null && !"".equals(pid)) {
			
			// 找到该用户其下所有的子设备
			userDevices = findBoUserDeviceByPid(pid);
		}
		
		// 如果存在子设备
		if (userDevices != null && userDevices.size() > 0) {
			
			for (BoUserDevice device : userDevices) {
				device.setBoUserDevice(boUserDevice);
				
				dao.update(device);
				
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 为设备推送资源（添加/更新），一个设备只能推送一个资源
	 * @param userDeviceVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer pushResourceForDevice(UserDeviceVO userDeviceVO) throws Exception {
		Integer count = 0;
		if (userDeviceVO != null && !"".equals(userDeviceVO)) {
			
			BoUserDevice boUserDevice = null;
			
			// 资源对象
			BoAttachmentSource boAttachmentSource = boAttachmentSourceService.findBoAttachmentSourceById(userDeviceVO.getResourceId());
			
			if (boAttachmentSource == null) {
				return 0;
			}
			
			// 设备id
			Integer[] deviceIds = userDeviceVO.getUserDeviceIds();
			
			// 为每一台设备添加资源
			for (Integer deviceId : deviceIds) {
				
				boUserDevice = findBoUserDeviceById(deviceId);
				
				if (boUserDevice == null) {
					break;
				}
				
				boUserDevice.setBoAttachmentSource(boAttachmentSource);
				
				dao.saveOrUpdate(boUserDevice);
				
				count++;
			}
			
		}
		return count;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据id删除设备(可批量)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer deleteDeviceById(Integer[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			return null;
		}
		BoUserDevice boUserDevice = null;
		
		int count = 0;
		for (int i = 0; i < ids.length; i++) {
			
			boUserDevice = findBoUserDeviceById(ids[i]);
			if (boUserDevice == null) {
				break;
			}
			
			boUserDevice.setIsDeleted("1");
			dao.update(boUserDevice);
			count++;
			
			// 如果删除用户，则删除其下所有设备信息（判断是否为父节点）
			if (boUserDevice.getBoUserDevice() == null || "".equals(boUserDevice.getBoUserDevice())) {
				
				count += deleteDeviceByPid(ids[i]);
			}
		}
		return count;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 根据父节点删除设备
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public Integer deleteDeviceByPid(Integer pid) throws Exception {
		
		if (pid == null || "".equals(pid)) {
			return null;
		}
		
		int count = 0;
		BoUserDevice boUserDevice = null;
		
		List<BoUserDevice> boUserDeviceList = findBoUserDeviceByPid(pid);
		
		if (boUserDeviceList != null && boUserDeviceList.size() > 0) {
			
			for (BoUserDevice userDevice : boUserDeviceList) {
				boUserDevice = findBoUserDeviceById(userDevice.getId());
				boUserDevice.setIsDeleted("1");
				
				dao.update(boUserDevice);
				
				count++;
			}
		}
		return count;
	}
	
	/**
	 * @author：He.hp
	 * @date：2017年9月1日
	 * @function：TODO 更新或删除为设备推送的资源文件
	 * @param userDeviceIds
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer updateOrDeleteResourceToDeviceById(UserDeviceVO userDeviceVO) throws Exception {
		
		if (userDeviceVO == null || "".equals(userDeviceVO)) {
			return null;
		}
		
		Integer count = 0;
		BoUserDevice boUserDevice = null;
		BoAttachmentSource boAttachmentSource = null;
		
		// 删除（将boAttachmentSource字段制空）
		if ("2".equals(userDeviceVO.getMark())) {
			
			// 批量删除的设备id集合
			Integer[] userDeviceIds = userDeviceVO.getUserDeviceIds();
			
			if (userDeviceIds != null && userDeviceIds.length > 0) {
				
				for (Integer userDeviceId : userDeviceIds) {
					
					boUserDevice = findBoUserDeviceById(userDeviceId);
					
					if (boUserDevice == null) {
						break;
					}
					
					boUserDevice.setBoAttachmentSource(null);
					
					dao.update(boUserDevice);
					
					count++;
				}
			}
		}
		
		// 更改
		if ("3".equals(userDeviceVO.getMark())) {
			
			// 资源id
			Integer resourceId = userDeviceVO.getResourceId();
			
			// 批量修改的设备id集合
			String[] userDeviceIdStrs = userDeviceVO.getUserDeviceIdStrs().split(",");
			
			if(userDeviceIdStrs == null || userDeviceIdStrs.length <= 0){
				return count;
			}
			
			if (resourceId != null && !"".equals(resourceId)) {
				boAttachmentSource = boAttachmentSourceService.findBoAttachmentSourceById(resourceId);
			}
			
			if (boAttachmentSource != null && !"".equals(boAttachmentSource)) {
				
				for (String userDeviceId : userDeviceIdStrs) {
					
					boUserDevice = findBoUserDeviceById(Integer.parseInt(userDeviceId));
					
					if (boUserDevice == null) {
						break;
					}
					
					boUserDevice.setBoAttachmentSource(boAttachmentSource);
					
					dao.update(boUserDevice);
					
					count++;
				}
			}
		}
		return count;
	}
	
}
