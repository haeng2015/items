package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoRole;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.UserVO;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年3月28日
 * @说明：角色专用接口
 */
public interface BoRoleService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    public UserVO boToVo(BoRole boRole);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    public BoRole voToBo(UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 加载role列表
     * @param param
     * @return
     */
    public PagingResult<BoRole> loadRoleList(PageEntity param);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 加载列表信息（datagrid数据格式）
     * @param dataGridPage
     * @return
     */
    public DataGrid loadRoleListInfo(DataGridPage dataGridPage);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 根据id查找role
     * @param roleVO
     * @return
     */
    public BoRole findRoleById(Integer roleId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 根据类型查找角色
     * @param type
     * @return
     */
    public BoRole findRoleByType(String type);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 查找所有角色信息
     * @return
     */
    public List<UserVO> findAllRoles();
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 更新或添加角色
     * @param roleVO
     * @return
     */
    public Integer updateOrAddRole(UserVO userVO,UserVO loginUser);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 根据id删除角色（批量）
     * @param roleIds
     * @return
     */
    public Integer deleteRoleByIds(List<String> roleIds);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为管理员添加角色
     * @param boRole
     * @return
     */
    public BoRole addBoRoleForAdmin(BoRole boRole);
    
}
