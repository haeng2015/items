package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoRole;
import com.pbms.pojo.RoleAuth;
import com.pbms.service.BoRoleService;
import com.pbms.service.RoleAuthService;
import com.pbms.util.DataGrid;
import com.pbms.util.DataGridPage;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.UserVO;

@Service("boRoleService")
public class BoRoleServiceImpl implements BoRoleService {
    
    // xml文件中的namespac值
    public static final String NAMESPACE = "boRoleMapper";
    private BaseDao<BoRole> baseDao;
    @Resource
    private RoleAuthService roleAuthService;
    
    public BaseDao<BoRole> getBaseDao() {
	return baseDao;
    }
    
    @Resource
    public void setBaseDao(BaseDao<BoRole> baseDao) {
	this.baseDao = baseDao;
    }
    
    public RoleAuthService getRoleAuthService() {
	return roleAuthService;
    }
    
    public void setRoleAuthService(RoleAuthService roleAuthService) {
	this.roleAuthService = roleAuthService;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 角色bo转vo
     * @param boRole
     * @return
     */
    public UserVO boToVo(BoRole boRole) {
	if (boRole != null && !"".equals(boRole)) {
	    UserVO userVO = new UserVO();
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(boRole, userVO);
	    return userVO;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 角色vo转bo
     * @param roleVO
     * @return
     */
    public BoRole voToBo(UserVO userVO) {
	if (userVO != null && !"".equals(userVO)) {
	    BoRole boRole = new BoRole();
	    BeanUtils.copyProperties(userVO, boRole);
	    return boRole;
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 加载role列表
     * @param param
     * @return
     */
    @Override
    public PagingResult<BoRole> loadRoleList(PageEntity param) {
	if (param != null && !"".equals(param)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return baseDao.selectPagination(param);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 根据id查找role
     * @param roleVO
     * @return
     */
    @Override
    public BoRole findRoleById(Integer roleId) {
	if (roleId != null && !"".equals(roleId)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return baseDao.get(roleId);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 更新或添加角色
     * @param roleVO
     * @return
     */
    @Override
    public Integer updateOrAddRole(UserVO userVO, UserVO loginUser) {
	Integer result = 0;
	if (userVO != null && !"".equals(userVO)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    BoRole boRole = this.voToBo(userVO);
	    if (userVO.getRoleId() != null && !"".equals(userVO.getRoleId())) { // 更新
		boRole = this.findRoleById(userVO.getRoleId());
		boRole.setRoleType("2"); // 非管理员
		boRole.setUserId(loginUser.getUserId()); // 添加当前用户id
		result = this.baseDao.update(boRole);
	    } else { // 添加
		boRole.setRoleType("2"); // 非管理员
		boRole.setUserId(loginUser.getUserId());
		result = this.baseDao.insertSelective(boRole);
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月19日
     * @function：TODO 加载列表信息（datagrid数据格式）
     * @param dataGridPage
     * @return
     */
    @Override
    public DataGrid loadRoleListInfo(DataGridPage dataGridPage) {
	if (dataGridPage != null && !"".equals(dataGridPage)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    DataGrid dataGrid = this.baseDao.pageDataGrid(dataGridPage);
	    
	    if (dataGrid != null && !"".equals(dataGrid)) {
		@SuppressWarnings("unchecked")
		List<BoRole> boRoleList = (List<BoRole>) dataGrid.getRows();
		List<UserVO> userVOs = new ArrayList<UserVO>();
		// bo转vo
		for (BoRole boRole : boRoleList) {
		    userVOs.add(this.boToVo(boRole));
		}
		dataGrid.setRows(userVOs);
		return dataGrid;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 根据id删除角色（批量）【先删权限，再删角色】
     * @param roleIds
     * @return
     */
    @Override
    public Integer deleteRoleByIds(List<String> roleIds) {
	Integer result = 0;
	List<RoleAuth> roleAuth = null;
	if (roleIds != null && !"".equals(roleIds)) {
	    for (String roleId : roleIds) {
		// 先查找该角色是否存在权限
		roleAuth = this.roleAuthService.findBoAuthByRole(Integer.parseInt(roleId));
		
		// 删除权限
		if (roleAuth != null && roleAuth.size() > 0) {
		    result = this.roleAuthService.deleteRoleAuthByRole(Integer.parseInt(roleId));
		} else {
		    result = 1;
		}
		
		// 删除角色
		if (result > 0) {
		    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
		    result = this.baseDao.delete(Integer.parseInt(roleId));
		}
	    }
	}
	return result;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月22日
     * @function：TODO 查找所有角色信息
     * @return
     */
    @Override
    public List<UserVO> findAllRoles() {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	List<BoRole> roles = this.baseDao.selects();
	List<UserVO> userVOs = new ArrayList<UserVO>();
	for (BoRole boRole : roles) {
	    userVOs.add(this.boToVo(boRole));
	}
	return userVOs;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 为管理员添加角色
     * @param boRole
     * @return
     */
    @Override
    public BoRole addBoRoleForAdmin(BoRole boRole) {
	Integer result = 0;
	if (boRole != null && !"".equals(boRole)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    
	    result = this.baseDao.insertSelective(boRole);
	    if (result > 0) {
		return boRole;
	    }
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月28日
     * @function：TODO 根据类型查找角色
     * @param type
     * @return
     */
    @Override
    public BoRole findRoleByType(String type) {
	if (type != null && !"".equals(type)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return this.baseDao.selectFk(type);
	}
	return null;
    }
    
}
