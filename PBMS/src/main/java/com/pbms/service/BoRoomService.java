package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoRoom;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.RoomVO;

public interface BoRoomService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    public RoomVO boToVo(BoRoom boRoom);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    public BoRoom voToBo(RoomVO roomVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    public BoRoom findBoRoomById(Integer roomId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月5日
     * @function：TODO 根据楼栋外键id查找对象
     * @param buildId
     * @return
     */
    public List<BoRoom> findBoRoomByBuild(Integer buildId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月5日
     * @function：TODO 根据楼栋+房间外键id查找对象
     * @param buildId
     * @return
     */
    public BoRoom findBoRoomByBuild_Room(RoomVO roomVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载楼栋列表
     * @param pageContext
     * @return
     */
    public DataGrid loadRoomList(DataGridPage pageContext);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新对象
     * @param buildingVO
     * @return
     */
    public BoRoom saveOrUpdateRoom(RoomVO roomVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id删除对象
     * @param buildingVO
     * @return
     */
    public Integer deleteBoRoom(List<Integer> roomIds);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据楼栋id删除对象
     * @param buildingVO
     * @return
     */
    public Integer deleteBoRoomByBuild(Integer buildId);
    
}
