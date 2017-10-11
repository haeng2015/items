package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoBuilding;
import com.pbms.pojo.BoOwner;
import com.pbms.pojo.BoRoom;
import com.pbms.service.BoBuildingService;
import com.pbms.service.BoOwnerService;
import com.pbms.service.BoRoomService;
import com.pbms.util.DataGrid;
import com.pbms.util.DateTimeUtils;
import com.pbms.util.DataGridPage;
import com.pbms.vo.RoomVO;

@Service("boRoomService")
public class BoRoomServiceImpl implements BoRoomService {
    
    // xml文件中的namespac值
    public static final String NAMESPACE = "boRoomMapper";
    private BaseDao<BoRoom> baseDao;
    @Resource
    private BoBuildingService boBuildingService;
    @Autowired
    private BoOwnerService boOwnerService;
    
    public BaseDao<BoRoom> getBaseDao() {
	return baseDao;
    }
    
    @Resource
    public void setBaseDao(BaseDao<BoRoom> baseDao) {
	this.baseDao = baseDao;
    }
    
    public BoBuildingService getBoBuildingService() {
	return boBuildingService;
    }
    
    public void setBoBuildingService(BoBuildingService boBuildingService) {
	this.boBuildingService = boBuildingService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO bo转vo
     * @param boBuilding
     * @return
     */
    @Override
    public RoomVO boToVo(BoRoom boRoom) {
	RoomVO roomVO = new RoomVO();
	BoBuilding boBuilding = null;
	BoOwner boOwner = null;
	if (boRoom != null && !"".equals(boRoom)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    roomVO.setRoomStartDate(DateTimeUtils.timestampToString(boRoom.getRoomStartTime()));
	    roomVO.setRoomEndDate(DateTimeUtils.timestampToString(boRoom.getRoomEndTime()));
	    BeanUtils.copyProperties(boRoom, roomVO);
	    
	    // 将楼栋信息放入房间vo对象中
	    if (boRoom.getBuildId() != null && !"".equals(boRoom.getBuildId())) {
		boBuilding = this.boBuildingService.findBoBuildingById(boRoom.getBuildId());
		roomVO.setBuildName(boBuilding.getBuildName());
	    } else {
		roomVO.setBuildId(0);
		roomVO.setBuildName("暂未指定楼栋!");
	    }
	    
	    // 将用户信息放入vo对象中(根据房间id查找业主信息)
	    boOwner = this.boOwnerService.findBoOwnerByRoom(boRoom.getRoomId());
	    if (boOwner != null && !"".equals(boOwner)) {
		roomVO.setOwnerName(boOwner.getOwnerName());
	    } else {
		roomVO.setOwnerId(0);
		roomVO.setOwnerName("暂无业主");
	    }
	    return roomVO;
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
    public BoRoom voToBo(RoomVO roomVO) {
	BoRoom boRoom = new BoRoom();
	if (roomVO != null && !"".equals(roomVO)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    boRoom.setRoomStartTime(DateTimeUtils.stringToTimestamp1(roomVO.getRoomStartDate()));
	    boRoom.setRoomEndTime(DateTimeUtils.stringToTimestamp1(roomVO.getRoomEndDate()));
	    BeanUtils.copyProperties(roomVO, boRoom);
	    return boRoom;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO 加载楼栋列表对象
     * @param pageContext
     * @return
     */
    @Override
    public DataGrid loadRoomList(DataGridPage pageContext) {
	if (pageContext != null && !"".equals(pageContext)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    DataGrid dataGrid = this.baseDao.pageDataGrid(pageContext);
	    
	    if (dataGrid != null && !"".equals(dataGrid)) {
		@SuppressWarnings("unchecked")
		List<BoRoom> boRoomList = (List<BoRoom>) dataGrid.getRows();
		List<RoomVO> roomVOs = new ArrayList<RoomVO>();
		// bo转vo
		for (BoRoom boRoom : boRoomList) {
		    roomVOs.add(this.boToVo(boRoom));
		}
		dataGrid.setRows(roomVOs);
		return dataGrid;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新对象
     * @param buildingVO
     * @return
     */
    @Override
    public BoRoom saveOrUpdateRoom(RoomVO roomVO) {
	BoRoom boRoom = new BoRoom();
	Integer result = 0;
	if (roomVO != null && !"".equals(roomVO)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    if (roomVO.getRoomId() != null && !"".equals(roomVO.getRoomId())) { // 更新
		// mybatis的更新不用先查找出以前的对象，直接传入id即可
		if (roomVO.getBuildId() == 0) {
		    roomVO.setBuildId(null);
		}
		if (roomVO.getOwnerId() == 0) {
		    roomVO.setOwnerId(null);
		}
		result = this.baseDao.updateSelective(this.voToBo(roomVO));
	    } else {
		boRoom = this.voToBo(roomVO);
		result = this.baseDao.insertSelective(boRoom);
	    }
	    if (result > 0) {
		return boRoom;
	    }
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
    public BoRoom findBoRoomById(Integer roomId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (roomId != null && roomId > 0) {
	    return this.baseDao.get(roomId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月5日
     * @function：TODO 根据楼栋外键id查找对象
     * @param buildId
     * @return
     */
    @Override
    public List<BoRoom> findBoRoomByBuild(Integer buildId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (buildId != null && !"".equals(buildId)) {
	    return this.baseDao.selectFks(buildId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月5日
     * @function：TODO 根据楼栋+房间外键id查找对象
     * @param buildId
     * @return
     */
    @Override
    public BoRoom findBoRoomByBuild_Room(RoomVO roomVO) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (roomVO != null && !"".equals(roomVO)) {
	    return this.baseDao.selectFk(roomVO);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id删除对象
     * @param buildingVO
     * @return
     */
    @Override
    public Integer deleteBoRoom(List<Integer> roomIds) {
	Integer result = 0;
	if (roomIds != null && !roomIds.isEmpty() && roomIds.size() > 0) {
	    for (Integer roomId : roomIds) {
		this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		result = this.baseDao.delete(roomId);
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据楼栋id删除对象
     * @param buildingVO
     * @return
     */
    @Override
    public Integer deleteBoRoomByBuild(Integer buildId) {
	Integer result = 0;
	if (buildId != null && !"".equals(buildId)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    result = this.baseDao.deleteFK(buildId);
	}
	return result;
    }
    
}
