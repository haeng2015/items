package com.panasign.service;

import java.util.List;

import com.panasign.bean.BoUserDevice;
import com.panasign.core.TreeGrid;
import com.panasign.vo.UserDeviceVO;

/**
 * @版权所有：柏年软件
 * @项目名称:柏年云项目第一期
 * @创建者:He.hp
 * @创建日期:2017年8月28日
 * @说明：用户设备接口
 */
public interface BoUserDeviceService {
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO bo转vo对象
	 * @param boUserDevice
	 * @return
	 * @throws Exception
	 */
	public UserDeviceVO boToVo(BoUserDevice boUserDevice) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 加载设备信息
	 * @param deviceVO
	 * @return
	 * @throws Exception
	 */
	public TreeGrid loadDeviceListInfoByTree(UserDeviceVO userDeviceVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据设备id查找对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BoUserDevice findBoUserDeviceById(Integer id) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月31日
	 * @function：TODO 根据设备id查找对象
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	public BoUserDevice findBoUserDeviceByDeviceId(String deviceId) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 根据父id查找
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	public List<BoUserDevice> findBoUserDeviceByPid(Integer pid) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年9月1日
	 * @function：TODO 获得所有用户名（父节点）
	 * @return
	 * @throws Exception
	 */
	public List<UserDeviceVO> findAllBoUserDeviceUserName() throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 根据用户名查找
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public BoUserDevice findBoUserDeviceByUserName(String userName) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 根据父用户获得所有子设备信息
	 * @param userParents
	 * @return
	 * @throws Exception
	 */
	public List<UserDeviceVO> getAllChildTreeByPid(List<UserDeviceVO> userDeviceVOs) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月29日
	 * @function：TODO 递归子设备节点信息
	 * @param boUserDevice
	 * @throws Exception
	 */
	public void recursionChildTreeNode(UserDeviceVO userDeviceVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 保存添加设备
	 * @param deviceVO
	 * @return
	 * @throws Exception
	 */
	public BoUserDevice addAndSaveDevice(UserDeviceVO userDeviceVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月30日
	 * @function：TODO 为设备推送资源（添加/更新），一个设备只能推送一个资源
	 * @param userDeviceVO
	 * @return
	 * @throws Exception
	 */
	public Integer pushResourceForDevice(UserDeviceVO userDeviceVO) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年8月28日
	 * @function：TODO 根据id删除设备(可批量)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer deleteDeviceById(Integer[] ids) throws Exception;
	
	/**
	 * @author：He.hp
	 * @date：2017年9月1日
	 * @function：TODO 更新或删除为设备推送的资源文件
	 * @param userDeviceIds
	 * @return
	 * @throws Exception
	 */
	public Integer updateOrDeleteResourceToDeviceById(UserDeviceVO userDeviceVO) throws Exception;
	
}
