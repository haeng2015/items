package com.pbms.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pbms.pojo.BoAuth;
import com.pbms.service.BoAuthService;
import com.pbms.vo.TreeNode;

/**
 * @版权所有：Hehaipeng
 * @项目名称:PBMS物业后台管理系统
 * @创建者:Hehaipeng
 * @创建日期:2017年4月30日
 * @说明：自定义监听器（作用：初始化时载入一些参数），需要在web.xml文件中调用
 */
public class InitServletContext implements ServletContextListener {
    private static Logger logger = Logger.getLogger(InitServletContext.class);
    
    private BoAuthService boAuthService;
    
    /**
     * @author：Hehaipeng
     * @date：2017年4月30日
     * @function：TODO 获取用户权限的菜单树
     * @param userType
     * @return
     */
    private List<TreeNode> getAuthTree() {
	List<TreeNode> tree = new ArrayList<TreeNode>();
	List<BoAuth> auths = this.boAuthService.findAllBoAuth();
	tree = this.boAuthService.parseAuthToTree(auths);
	return tree;
    }
    
    /**
     * 初始化载入参数
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
	ServletContext servletContext = sce.getServletContext();
	WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	if (springContext != null) {
	    
	    // 4种身份菜单加入服务器环境。	
	    boAuthService = (BoAuthService) springContext.getBean("boAuthService");
	    servletContext.setAttribute("authTree", this.getAuthTree());
	    
	    // 省份列表加入服务器环境
	    
	    /**
	     * 把上传服务器的URL地址加载到context专用
	     * 
	     */
//	    servletContext.setAttribute("fileServerURL", "http://localhost:8888/imageServer/imageUpload.php");
	    
	    /**
	     * 设备信息接口服务器IP地址 http://yuntest.panasign.com/panasignPI
	     * http://localhost/panasignPI
	     */
//	    servletContext.setAttribute("PIServerURL", "http://localhost:8888/panasignPI");
	} else {
	    logger.info("获取应用程序上下文失败!");
	    return;
	}
	logger.info("服务器运行，初始化数据中……" + sce.getServletContext());
	logger.info("初始化完成!");
    }
    
    /**
     * 服务器关闭销毁一些数据
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	logger.info("服务器关闭，销毁数据中……");
	
    }
    
}
