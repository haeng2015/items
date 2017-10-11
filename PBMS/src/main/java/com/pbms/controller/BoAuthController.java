package com.pbms.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.pbms.pojo.BoAuth;
import com.pbms.service.BoAuthService;
import com.pbms.util.PageEntity;
import com.pbms.util.PagingResult;
import com.pbms.vo.TreeNode;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年3月28日
 * @说明：角色控制器
 */
@Controller
@RequestMapping("/user")
public class BoAuthController {
    
    private static final Logger LOGGER = Logger.getLogger(BoAuthController.class);
    
    @Autowired
    private BoAuthService boAuthService;
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 到权限列表
     * @return
     */
    @RequestMapping("/toAuthList")
    public ModelAndView toRoleList() {
	ModelAndView mv = new ModelAndView("/auth/authList");
	return mv;
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年3月30日
     * @function：TODO 加载角色列表
     * @param params
     * @return
     */
    @RequestMapping("/loadAuthList")
    public void loadAuthList(PageEntity param, HttpServletResponse response) {
	PagingResult<BoAuth> pagingResult = new PagingResult<BoAuth>();
	PrintWriter pw = null;
	String jsonString = null;
	try {
	    pagingResult = this.boAuthService.loadAuthList(param);
	    jsonString = JSON.toJSONString(pagingResult);
	    pw = response.getWriter();
	    pw.write(jsonString);
	    pw.flush();
	} catch (Exception e) {
	    LOGGER.error("BoAuthController -> loadAuthList : " + e.getMessage());
	}
    }
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月30日
     * @function：TODO 加载权限树结构
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/loadAuthTree")
    @ResponseBody
    public List<TreeNode> loadAuthTree(HttpServletRequest request) {
	List<TreeNode> authTree = new ArrayList<TreeNode>();
	try {
	    authTree = (List<TreeNode>) request.getSession().getServletContext().getAttribute("authTree");
	} catch (Exception e) {
	    TreeNode tn = new TreeNode();
	    tn.setId(1);
	    tn.setText("初始化权限列表出错，请重试！");
	    authTree.add(tn);
	    LOGGER.error("BoAuthController -> loadAuthTree : " + e.getMessage());
	}
	return authTree;
    }
    
}
