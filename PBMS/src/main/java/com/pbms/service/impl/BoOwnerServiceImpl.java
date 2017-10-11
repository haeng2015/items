package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoAddressCity;
import com.pbms.pojo.BoAddressProvince;
import com.pbms.pojo.BoAddressRegion;
import com.pbms.pojo.BoOwner;
import com.pbms.service.AddressService;
import com.pbms.service.BoAccessoryService;
import com.pbms.service.BoBuildingService;
import com.pbms.service.BoOwnerService;
import com.pbms.service.BoRoomService;
import com.pbms.service.BoUserService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.AddressVO;
import com.pbms.vo.OwnerVO;
import com.pbms.vo.UserVO;

@Service("boOwnerService")
public class BoOwnerServiceImpl implements BoOwnerService {
    
    /**
     * owner的mapper文件的namespace常量值：boOwnerMapper
     */
    public static final String NAMESPACE = "boOwnerMapper"; // xml文件中的namespac值
    @Resource
    private BaseDao<BoOwner> baseDao;
    
    @Autowired
    private BoBuildingService boBuildingService;
    @Autowired
    private BoRoomService boRoomService;
    @Autowired
    private AddressService addressService;
    @Resource
    private BoUserService boUserService;
    @Resource
    private BoAccessoryService boAccessoryService;
    
    public BaseDao<BoOwner> getBaseDao() {
	return baseDao;
    }
    
    public void setBaseDao(BaseDao<BoOwner> baseDao) {
	this.baseDao = baseDao;
    }
    
    public BoUserService getBoUserService() {
	return boUserService;
    }
    
    public void setBoUserService(BoUserService boUserService) {
	this.boUserService = boUserService;
    }
    
    public BoAccessoryService getBoAccessoryService() {
	return boAccessoryService;
    }
    
    public void setBoAccessoryService(BoAccessoryService boAccessoryService) {
	this.boAccessoryService = boAccessoryService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO bo转vo
     * @param boBuilding
     * @return
     */
    @Override
    public OwnerVO boToVo(BoOwner boOwner) {
	OwnerVO ownerVO = new OwnerVO();
	BoAddressRegion boAddressRegion = null;
	BoAddressCity boAddressCity = null;
	BoAddressProvince boAddressProvince = null;
	if (boOwner != null && !"".equals(boOwner)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）(时间转换尽量不用名称相同)
	    BeanUtils.copyProperties(boOwner, ownerVO);
	    
	    // 设置楼栋房间名
	    if (boOwner.getBuildId() != null && !"".equals(boOwner.getBuildId())) {
		ownerVO.setBuildName(this.boBuildingService.findBoBuildingById(boOwner.getBuildId()).getBuildName());
	    } else {
		ownerVO.setBuildName("暂无楼栋");
	    }
	    if (boOwner.getRoomId() != null && !"".equals(boOwner.getRoomId())) {
		ownerVO.setRoomName(this.boRoomService.findBoRoomById(boOwner.getRoomId()).getRoomName());
	    } else {
		ownerVO.setRoomName("暂无房间");
	    }
	    
	    // 由于不能判断是区级id还是市级id，故先区级查找，若为null，则市级查找
	    boAddressRegion = this.addressService.findBoAddressRegionById(boOwner.getOwnerAddrId());
	    if (boAddressRegion != null) {
		boAddressCity = this.addressService.findBoAddressCityById(boAddressRegion.getCity().getcId());
		ownerVO.setRegionId(boOwner.getOwnerAddrId());
		ownerVO.setRegionName(boAddressRegion.getrName());
	    } else {
		boAddressCity = this.addressService.findBoAddressCityById(boOwner.getOwnerAddrId());
	    }
	    ownerVO.setCityId(boAddressCity.getcId());
	    ownerVO.setCityName(boAddressCity.getcName());
	    boAddressProvince = this.addressService.findBoAddressProvinceById(boAddressCity.getProvince().getpId());
	    ownerVO.setProvinceId(boAddressProvince.getpId());
	    ownerVO.setProvinceName(boAddressProvince.getpName());
	    
	    return ownerVO;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO vo转bo
     * @param buildingVO
     * @return
     */
    @Override
    public BoOwner voToBo(OwnerVO ownerVO) {
	BoOwner boOwner = new BoOwner();
	try {
	    if (ownerVO != null && !"".equals(ownerVO)) {
		// copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）(时间转换尽量不用名称相同)
		BeanUtils.copyProperties(ownerVO, boOwner);
		if ("undefined".equals(ownerVO.getRegionId())) {
		    ownerVO.setRegionId(null);
		}
		
		// 地址信息(保存叶子节点id，如果最小级别为市级，则保存市级，否则保存区级id)
		if (ownerVO.getRegionId() != null && !"".equals(ownerVO.getRegionId())) {
		    boOwner.setOwnerAddrId(ownerVO.getRegionId());
		} else if (ownerVO.getCityId() != null && !"".equals(ownerVO.getCityId())) {
		    boOwner.setOwnerAddrId(ownerVO.getCityId());
		}
		
		// 中文地址信息
		AddressVO addressVO = new AddressVO();
		addressVO.setpId(ownerVO.getProvinceId());
		addressVO.setcId(ownerVO.getCityId());
		if (ownerVO.getRegionId() != null && !"".equals(ownerVO.getRegionId())) {
		    addressVO.setrId(ownerVO.getRegionId());
		}
		
		addressVO = this.addressService.findAddressName(addressVO);
		String provinceName = addressVO.getpName();
		String cityName = addressVO.getcName();
		String regionName = "";
		if (addressVO.getrName() != null && !"".equals(addressVO.getrName())) {
		    regionName = addressVO.getrName();
		}
		boOwner.setOwnerAddr(provinceName + cityName + regionName);
		boOwner.setIsDeleted("0");
		
		// 楼栋
		// if (ownerVO.getBuildId() != null &&
		// !"".equals(ownerVO.getBuildId())) {
		// boOwner.setBuilding(this.boBuildingService.findBoBuildingById(ownerVO.getBuildId()));
		// }
		// // 房间
		// if (ownerVO.getRoomId() != null &&
		// !"".equals(ownerVO.getRoomId())) {
		// boOwner.setRoom(this.boRoomService.findBoRoomById(ownerVO.getRoomId()));
		// }
		// // 用户
		// if (ownerVO.getUserId() != null &&
		// !"".equals(ownerVO.getUserId())) {
		// boOwner.setUser(this.boUserService.findBoUserById(ownerVO.getUserId()));
		// }
		this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		return boOwner;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    @Override
    public BoOwner findBoOwnerById(Integer ownerId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (ownerId != null && !"".equals(ownerId)) {
	    return this.baseDao.get(ownerId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据room查找对象
     * @param buildId
     * @return
     */
    @Override
    public BoOwner findBoOwnerByRoom(Integer roomId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (roomId != null && !"".equals(roomId)) {
	    return this.baseDao.selectFk(roomId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月15日
     * @function：TODO 根据楼栋查找 出所有对象
     * @return
     */
    @Override
    public List<BoOwner> findAllBoOwnersByBuildId(Integer buildId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (buildId != null && !"".equals(buildId)) {
	    return this.baseDao.selectFks(buildId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月15日
     * @function：TODO 查找 出所有对象
     * @return
     */
    @Override
    public List<OwnerVO> findAllBoOwners() {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	List<BoOwner> boOwners = this.baseDao.selects();
	List<OwnerVO> ownerVOs = new ArrayList<OwnerVO>();
	for (BoOwner boOwner : boOwners) {
	    ownerVOs.add(this.boToVo(boOwner));
	}
	return ownerVOs;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载列表
     * @param pageContext
     * @return
     */
    @Override
    public DataGrid loadOwnerList(DataGridPage pageContext) {
	if (pageContext != null && !"".equals(pageContext)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    DataGrid dataGrid = this.baseDao.pageDataGrid(pageContext);
	    
	    if (dataGrid != null && !"".equals(dataGrid)) {
		@SuppressWarnings("unchecked")
		List<BoOwner> boOwnerList = (List<BoOwner>) dataGrid.getRows();
		List<OwnerVO> ownerVOs = new ArrayList<OwnerVO>();
		// bo转vo
		for (BoOwner boOwner : boOwnerList) {
		    ownerVOs.add(this.boToVo(boOwner));
		}
		dataGrid.setRows(ownerVOs);
		return dataGrid;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月15日
     * @function：TODO 添加、更新业主信息
     * @param ownerVO
     * @return
     */
    @Override
    public BoOwner saveOrUpdateOwner(OwnerVO ownerVO, UserVO userVO) {
	Integer result = 0;
	BoOwner boOwner = new BoOwner();
	if (userVO == null) {
	    return null;
	}
	if (ownerVO != null && !"".equals(ownerVO)) {
	    ownerVO.setUserId(userVO.getUserId());
	    boOwner = this.voToBo(ownerVO);
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值(必须在voToBo函数下面，因为该方法中调用了其他接口函数)
	    // 更新
	    if (ownerVO.getOwnerId() != null && !"".equals(ownerVO.getOwnerId())) {
		result = this.baseDao.updateSelective(boOwner);
	    } else { // 添加
		result = this.baseDao.insertSelective(boOwner);
	    }
	    if (result > 0) {
		return boOwner;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月15日
     * @function：TODO 根据id删除对象
     * @param ownerVO
     * @return
     */
    @Override
    public Integer deleteOwner(OwnerVO ownerVO) {
	Integer result = 0;
	if (ownerVO != null && !"".equals(ownerVO)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    List<Integer> ownerIds = ownerVO.getOwnerIds();
	    for (Integer ownerId : ownerIds) {
		result = this.baseDao.isDeleted(ownerId);
		
		if (result > 0) {
		    // 删除所有附件
		    result = this.boAccessoryService.deleteAttachmentByObject(ownerId);
		}
	    }
	}
	return result;
    }
}
