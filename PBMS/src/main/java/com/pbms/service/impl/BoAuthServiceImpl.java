package com.pbms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pbms.dao.BaseDao;
import com.pbms.pojo.BoAuth;
import com.pbms.service.BoAuthService;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.TreeNode;
import com.pbms.vo.UserVO;

@Service("boAuthService")
public class BoAuthServiceImpl implements BoAuthService {
    
    // xml文件中的namespac值
    public static final String NAMESPACE = "boAuthMapper";
    private BaseDao<BoAuth> baseDao;
    
    public BaseDao<BoAuth> getBaseDao() {
	return baseDao;
    }
    
    @Resource
    public void setBaseDao(BaseDao<BoAuth> baseDao) {
	this.baseDao = baseDao;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 角色bo转vo
     * @param boRole
     * @return
     */
    public UserVO boToVo(BoAuth boAuth) {
	if (boAuth != null && !"".equals(boAuth)) {
	    UserVO userVO = new UserVO();
	    // copyProperties该方法将bo对象转换为vo对象（条件：只转换二者类中属性名相同的）
	    BeanUtils.copyProperties(boAuth, userVO);
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
    public BoAuth voToBo(UserVO userVO) {
	if (userVO != null && !"".equals(userVO)) {
	    BoAuth boAuth = new BoAuth();
	    BeanUtils.copyProperties(userVO, boAuth);
	    return boAuth;
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
    public PagingResult<BoAuth> loadAuthList(PageEntity param) {
	if (param != null && !"".equals(param)) {
	    this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	    return baseDao.selectPagination(param);
	}
	return null;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 获得所有权限
     * @return
     */
    @Override
    public List<BoAuth> findAllBoAuth() {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	List<BoAuth> parentIds = baseDao.selects();
	List<BoAuth> childrenIds = new ArrayList<BoAuth>();
	
	// 循环查子级
	childrenIds = this.findChildrenByPid(parentIds);
	
	//将子节点的子节点添加到父类项中（否则在转treeNode无法查找）
	for (BoAuth boAuth2 : childrenIds) {
	    parentIds.addAll(boAuth2.getChildrens());
	    for (BoAuth boAuth3 : boAuth2.getChildrens()) {
		parentIds.addAll(boAuth3.getChildrens());
	    }
	}
	parentIds.addAll(childrenIds);
	
	return parentIds;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年5月2日
     * @function：TODO 根据父节点查所有子节点
     * @param pId
     * @return
     */
    public List<BoAuth> findChildrenByPid(List<BoAuth> parentIds) {
	List<BoAuth> childrenIds = new ArrayList<BoAuth>();
	if (parentIds != null && !"".equals(parentIds)) {
	    for (BoAuth boAuth : parentIds) {
		if ("0".equals(boAuth.getIsLeafNode())) { // 不是叶子节点
		    childrenIds = this.baseDao.selectFks(boAuth.getAuthId());
		    boAuth.setChildrens(childrenIds);
		}
	    }
	}
	return childrenIds;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月30日
     * @function：TODO 把传入的值转换成树结构
     * @param menus
     * @return
     */
    @Override
    public List<TreeNode> parseAuthToTree(List<BoAuth> auths) {
	List<TreeNode> tree = new ArrayList<TreeNode>();
	Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
	if (auths != null && auths.size() > 0) {
	    for (BoAuth auth : auths) {
		TreeNode node = new TreeNode();
		node.setId(auth.getAuthId());
		node.setText(auth.getAuthName());
		
		// 判断该机构节点是否有父节点，如果父节点不为空则赋值给treenode的父节点
		if (auth.getpId() != null) {
		    node.setParentId(auth.getpId());
		}
		map.put(auth.getAuthId(), node);
	    }
	    
	    for (BoAuth auth : auths) {
		// 如果该机构节点的父节点不为空，但是父节点id为空，则在list中添加有一个
		if (auth.getpId() != null) {
		    if (auth.getpId() == null) {
			tree.add(map.get(auth.getAuthId()));
		    } else {
			TreeNode pNode = map.get(auth.getpId());
			TreeNode cNode = map.get(auth.getAuthId());
			pNode.addChild(cNode);
		    }
		} else {
		    tree.add(map.get(auth.getAuthId()));
		}
	    }
	}
	return tree;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月26日
     * @function：TODO 根据id查找权限
     * @param authId
     * @return
     */
    @Override
    public BoAuth findBoAuthById(Integer authId) {
	this.baseDao.setNamespace(NAMESPACE); // 设置namespace值
	if (authId != null && !"".equals(authId)) {
	    return this.baseDao.get(authId);
	}
	return null;
    }
    
}
