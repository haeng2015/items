package com.pbms.service;

import java.util.List;

import com.pbms.pojo.BoAuth;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.TreeNode;
import com.pbms.vo.UserVO;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年3月28日
 * @说明：角色专用接口
 */
public interface BoAuthService {
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO bo转vo
     * @param boRole
     * @return
     */
    public UserVO boToVo(BoAuth boAuth);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO vo转bo
     * @param roleVO
     * @return
     */
    public BoAuth voToBo(UserVO userVO);
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 加载role列表
     * @param param
     * @return
     */
    public PagingResult<BoAuth> loadAuthList(PageEntity param);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 获得所有权限
     * @return
     */
    public List<BoAuth> findAllBoAuth();
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月30日
     * @function：TODO 把传入的menus转换成树结构
     * @param menus
     * @return
     */
    public List<TreeNode> parseAuthToTree(List<BoAuth> auth);
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 根据id查找权限
     * @param authId
     * @return
     */
    public BoAuth findBoAuthById(Integer authId);
    
}
