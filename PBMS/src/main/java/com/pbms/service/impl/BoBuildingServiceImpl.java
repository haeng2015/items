package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoBuilding;
import com.pbms.pojo.BoRoom;
import com.pbms.service.BoBuildingService;
import com.pbms.service.BoRoomService;
import com.pbms.util.DataGrid;
import com.pbms.util.DateTimeUtils;
import com.pbms.util.DataGridPage;
import com.pbms.vo.BuildingVO;

@Service("boBuildingService")
public class BoBuildingServiceImpl implements BoBuildingService {
    
    /**
     * building的mapper文件的namespace常量值：boBuildingMapper
     */
    public static final String NAMESPACE = "boBuildingMapper"; // xml文件中的namespac值
    private BaseDao<BoBuilding> baseDao;
    
    @Autowired
    private BoRoomService boRoomService;
    
    public BaseDao<BoBuilding> getBaseDao() {
	return baseDao;
    }
    
    @Resource
    public void setBaseDao(BaseDao<BoBuilding> baseDao) {
	this.baseDao = baseDao;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月7日
     * @function：TODO bo转vo
     * @param boBuilding
     * @return
     */
    @Override
    public BuildingVO boToVo(BoBuilding boBuilding) {
	BuildingVO buildingVO = new BuildingVO();
	if (boBuilding != null && !"".equals(boBuilding)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    buildingVO.setBuildStartDate(DateTimeUtils.timestampToString(boBuilding.getBuildStartTime()));
	    buildingVO.setBuildEndDate(DateTimeUtils.timestampToString(boBuilding.getBuildEndTime()));
	    BeanUtils.copyProperties(boBuilding, buildingVO);
	    return buildingVO;
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
    public BoBuilding voToBo(BuildingVO buildingVO) {
	BoBuilding boBuilding = new BoBuilding();
	if (buildingVO != null && !"".equals(buildingVO)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    boBuilding.setBuildStartTime(DateTimeUtils.stringToTimestamp1(buildingVO.getBuildStartDate()));
	    boBuilding.setBuildEndTime(DateTimeUtils.stringToTimestamp1(buildingVO.getBuildEndDate()));
	    BeanUtils.copyProperties(buildingVO, boBuilding);
	    return boBuilding;
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
    public DataGrid loadBuildingList(DataGridPage pageContext) {
	if (pageContext != null && !"".equals(pageContext)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    DataGrid dataGrid = this.baseDao.pageDataGrid(pageContext);
	    
	    if (dataGrid != null && !"".equals(dataGrid)) {
		@SuppressWarnings("unchecked")
		List<BoBuilding> boBuildingList = (List<BoBuilding>) dataGrid.getRows();
		List<BuildingVO> buildvVos = new ArrayList<BuildingVO>();
		// bo转vo
		for (BoBuilding boBuilding : boBuildingList) {
		    buildvVos.add(this.boToVo(boBuilding));
		}
		dataGrid.setRows(buildvVos);
		return dataGrid;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新Building对象
     * @param buildingVO
     * @return
     */
    @Override
    public BoBuilding saveOrUpdateBuilding(BuildingVO buildingVO) {
	BoBuilding boBuilding = new BoBuilding();
	if (buildingVO != null && !"".equals(buildingVO)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    if (buildingVO.getBuildId() != null && !"".equals(buildingVO.getBuildId())) { // 更新
		// buildingVO =
		// this.findBoBuildingById(buildingVO.getBuildId());
		// mybatis的更新不用先查找出以前的对象，直接传入id即可
		// return this.baseDao.update(this.voToBo(buildingVO));
		this.baseDao.updateSelective(this.voToBo(buildingVO));
	    } else {
		boBuilding = this.voToBo(buildingVO);
		boBuilding.setIsDeleted("0");
		this.baseDao.insertSelective(boBuilding);
	    }
	}
	return boBuilding;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    @Override
    public BoBuilding findBoBuildingById(Integer buildId) {
	if (buildId != null && buildId > 0) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.get(buildId);
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
    public Integer deleteBuilding(BuildingVO buildingVO) {
	Integer result = 0;
	List<BoRoom> boRooms = null;
	if (buildingVO != null && !"".equals(buildingVO)) {
	    for (String buildId : buildingVO.getBuildIds()) {
		this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		result = this.baseDao.isDeleted(Integer.parseInt(buildId));
		
		// 同时删除其下的所有房间
		if (result > 0) {
		    //先查找是否有房间
		    boRooms = this.boRoomService.findBoRoomByBuild(Integer.parseInt(buildId));
		    if(boRooms != null && boRooms.size()>0){
			result = this.boRoomService.deleteBoRoomByBuild(Integer.parseInt(buildId));
		    }
		}
	    }
	    // 批量删除
	    // this.baseDao.isDeletedBatch(buildingVO.getBuildIds());
	    return result;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月15日
     * @function：TODO 查找 出所有楼栋（转vo对象）
     * @return
     */
    @Override
    public List<BuildingVO> findAllBoBuildings() {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	List<BoBuilding> boBuildings = this.baseDao.selects();
	List<BuildingVO> boBuildingVOs = new ArrayList<BuildingVO>();
	for (BoBuilding boBuilding : boBuildings) {
	    boBuildingVOs.add(this.boToVo(boBuilding));
	}
	return boBuildingVOs;
    }
    
}
