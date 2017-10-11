package com.pbms.service;

import java.util.List;

import com.pbms.pojo.RoleAuth;
import com.pbms.vo.UserVO;

public interface RoleAuthService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 根据角色id查找权限
     * @param roleId
     * @return
     */
    public List<RoleAuth> findBoAuthByRole(Integer roleId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 为角色添加权限
     * @param userVO
     * @return
     */
    public Integer addOrUpdateRoleAuth(UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 根据角色删除权限
     * @param userVO
     * @return
     */
    public Integer deleteRoleAuthByRole(Integer roleId);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为超级管理员添加权限
     * @return
     */
    public Integer addAuthForAdmin(Integer adminId);
    
}
