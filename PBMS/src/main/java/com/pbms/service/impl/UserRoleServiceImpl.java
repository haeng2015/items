package com.pbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoRole;
import com.pbms.pojo.BoUser;
import com.pbms.pojo.UserRole;
import com.pbms.service.BoRoleService;
import com.pbms.service.BoUserService;
import com.pbms.service.UserRoleService;
import com.pbms.vo.UserVO;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    
    // xml文件中的namespac值
    public static final String NAMESPACE = "userRoleMapper";
    private BaseDao<UserRole> baseDao;
    @Resource
    private BoUserService boUserService;
    @Resource
    private BoRoleService boRoleService;
    
    public BaseDao<UserRole> getBaseDao() {
	return baseDao;
    }
    
    @Resource
    public void setBaseDao(BaseDao<UserRole> baseDao) {
	this.baseDao = baseDao;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月23日
     * @function：TODO bo转vo
     * @param userRole
     * @return
     */
    @Override
    public UserVO boToVo(UserRole userRole) {
	UserVO userVO = new UserVO();
	if (userRole != null && !"".equals(userRole)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(userRole, userVO);
	}
	return userVO;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月9日
     * @function：TODO vo转bo
     * @param userVO
     * @return
     */
    @Override
    public UserRole voToBo(UserVO userVO) {
	UserRole userRole = new UserRole();
	if (userVO != null && !"".equals(userVO)) {
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(userVO, userRole);
	}
	return userRole;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 更新或添加用户角色
     * @param roleVO
     * @return
     * @throws Exception
     */
    @Override
    public Integer updateOrAddUserRole(UserVO userVO) throws Exception {
	Integer result = 0;
	if (userVO != null && !"".equals(userVO)) {
	    UserRole userRole = new UserRole();
	    if (userVO.getUserRoleId() != null && !"".equals(userVO.getUserRoleId())) { // 更新
		userRole.setId(userVO.getUserRoleId());
		userRole.setUser(this.boUserService.findBoUserById(userVO.getUserId()));
		userRole.setRole(this.boRoleService.findRoleById(userVO.getRoleId()));
		this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		result = this.baseDao.updateSelective(userRole);
	    } else { // 添加
		userRole.setUser(this.boUserService.findBoUserById(userVO.getUserId()));
		userRole.setRole(this.boRoleService.findRoleById(userVO.getRoleId()));
		this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		result = this.baseDao.insertSelective(userRole);
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月23日
     * @function：TODO 根据用户id查找
     * @return
     */
    @Override
    public UserRole findUserRolesByUserId(Integer userId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	return this.baseDao.selectFk(userId);
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 根据id删除用户角色（批量）
     * @param roleIds
     * @return
     */
    @Override
    public Integer deleteUserRoleByIds(List<String> urIds) {
	Integer result = 0;
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (urIds != null && !"".equals(urIds)) {
	    for (String urId : urIds) {
		result = this.baseDao.delete(Integer.parseInt(urId));
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月27日
     * @function：TODO 根据用户删除对象
     * @param userId
     * @return
     */
    @Override
    public Integer deleteUserRoleByUser(Integer userId) {
	Integer result = 0;
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (userId != null && !"".equals(userId)) {
	    result = this.baseDao.deleteFK(userId);
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为超级管理员添加超级角色(超级管理员、超级角色标志都为1,且都只有一个)
     * @param adminId
     * @return
     */
    @Override
    public UserRole addRoleForAdmin(String superType) {
	BoRole boRole = null;
	BoUser boUser = null;
	UserRole userRole = null;
	try {
	    if (superType != null && !"".equals(superType)) {
		// 查找超级管理员
		boUser = this.boUserService.findAdminByType(superType);
		if (boUser != null) {
		    // 先检查是否已存在超级角色
		    boRole = this.boRoleService.findRoleByType(superType);
		    if (boRole == null || "".equals(boRole)) {
			boRole = new BoRole();
			boRole.setRoleName("超级管理角色");
			boRole.setRoleType(superType); // 超级管理角色表示
			boRole.setUserId(boUser.getUserId());
			
			// 添加超级管理角色
			boRole = this.boRoleService.addBoRoleForAdmin(boRole);
			
		    } else { // 存在超级角色
		    
			// 先检查该超级角色是否已经被赋予超级管理员
			userRole = this.findUserRolesByUserId(boUser.getUserId());
			if (userRole == null || "".equals(userRole)) {
			    // 将该角色赋予超级管理员
			    userRole = new UserRole();
			    userRole.setRole(this.boRoleService.findRoleById(boRole.getRoleId()));
			    userRole.setUser(this.boUserService.findBoUserById(boUser.getUserId()));
			    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
			    this.baseDao.insertSelective(userRole);
			}
			return userRole;
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
    
}
