package com.pbms.service;

import java.util.List;

import com.pbms.pojo.UserRole;
import com.pbms.vo.UserVO;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年3月28日
 * @说明：用户角色专用接口
 */
public interface UserRoleService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月23日
     * @function：TODO bo转vo
     * @param userRole
     * @return
     */
    public UserVO boToVo(UserRole userRole);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO vo转bo
     * @param userVO
     * @return
     */
    public UserRole voToBo(UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月23日
     * @function：TODO 根据用户查找
     * @param boUser
     * @return
     */
    public UserRole findUserRolesByUserId(Integer userId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 为用户更新或添加用户角色
     * @param roleVO
     * @return
     * @throws Exception 
     */
    public Integer updateOrAddUserRole(UserVO userVO) throws Exception;
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 根据id删除用户角色
     * @param roleIds
     * @return
     */
    public Integer deleteUserRoleByIds(List<String> urIds);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月27日
     * @function：TODO 根据用户删除对象
     * @param userId
     * @return
     */
    public Integer deleteUserRoleByUser(Integer userId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为超级管理员添加角色（超级管理员id和超级角色id都指定为adminId）
     * @param adminId
     * @return
     */
    public UserRole addRoleForAdmin(String adminId);
}
