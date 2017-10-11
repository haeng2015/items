package com.panasign.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * servletContext初始化时载入一些参数
 * @copyright：柏年软件
 * @project:柏年云项目第一期
 * @author: Wu.Liang
 * @createDate: 2015-7-6
 */
public class InitServletContext implements ServletContextListener {
	private static Logger LOG = Logger.getLogger(InitServletContext.class);
	
	/**
	 * 初始化载入参数
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		LOG.info("服务器运行，初始化数据中……"+sce.getServletContext());
		ServletContext servletContext = sce.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		  if(springContext != null){
			  
//			  servletContext.setAttribute("provinceVOs", cityVOs);
			  
		  }else{
			  LOG.info("获取应用程序上下文失败!");
			  return;
		  }
		  LOG.info("初始化完成!");
	}

	/**
	 * 服务器关闭销毁一些数据
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOG.info("服务器关闭，销毁数据中……");
	}

}
