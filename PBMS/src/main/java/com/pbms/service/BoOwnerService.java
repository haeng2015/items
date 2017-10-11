package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoOwner;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.vo.OwnerVO;
import com.pbms.vo.UserVO;

public interface BoOwnerService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    public OwnerVO boToVo(BoOwner boOwner);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    public BoOwner voToBo(OwnerVO ownerVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id查找对象
     * @param buildId
     * @return
     */
    public BoOwner findBoOwnerById(Integer ownerId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据room查找对象
     * @param buildId
     * @return
     */
    public BoOwner findBoOwnerByRoom(Integer roomId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月15日
     * @function：TODO 根据楼栋查找 出所有对象
     * @return
     */
    public List<BoOwner> findAllBoOwnersByBuildId(Integer buildId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月15日
     * @function：TODO 查找 出所有对象
     * @return
     */
    public List<OwnerVO> findAllBoOwners();
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月1日
     * @function：TODO 加载列表
     * @param pageContext
     * @return
     */
    public DataGrid loadOwnerList(DataGridPage pageContext);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 保存/更新对象
     * @return
     */
    public BoOwner saveOrUpdateOwner(OwnerVO ownerVO, UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月5日
     * @function：TODO 根据id删除对象
     * @return
     */
    public Integer deleteOwner(OwnerVO ownerVO);
    
}
