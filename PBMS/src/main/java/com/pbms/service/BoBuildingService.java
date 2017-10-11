package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoBuilding;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.BuildingVO;

public interface BoBuildingService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    public BuildingVO boToVo(BoBuilding boBuilding);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    public BoBuilding voToBo(BuildingVO buildingVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    public BoBuilding findBoBuildingById(Integer buildId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月15日
     * @function：TODO 查找 出所有楼栋（转vo对象）
     * @return
     */
    public List<BuildingVO> findAllBoBuildings();
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载楼栋列表
     * @param pageContext
     * @return
     */
    public DataGrid loadBuildingList(DataGridPage pageContext);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新Building对象
     * @param buildingVO
     * @return
     */
    public BoBuilding saveOrUpdateBuilding(BuildingVO buildingVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id删除对象
     * @param buildingVO
     * @return
     */
    public Integer deleteBuilding(BuildingVO buildingVO);
    
}
