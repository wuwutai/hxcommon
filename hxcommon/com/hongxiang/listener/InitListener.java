package com.hongxiang.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.hongxiang.base.BaseConfigHelper;

/**                              
 * @description : 系统初始化侦听器用来加载默认配置等信息
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 12:27:06 +0800 
 */
public class InitListener implements ServletContextListener{
	/**
     * @Fields logger : log4j句柄
     */
    protected Logger logger = Logger.getLogger(getClass());  
	
	/*
	* <p>Title: contextDestroyed</p>
	* <p>Description: </p>
	* @param arg0
	* @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	*/ 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("系统正在关闭……");
	}

	/*
	* <p>Title: contextInitialized</p>
	* <p>Description: </p>
	* @param arg0
	* @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	*/ 
	@Override
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		
		//加载系统配置文件
		try {
			logger.info("系统正在加载配置信息……");
			//加载系统配置
			BaseConfigHelper.init(servletcontextevent);
			
		} catch (Exception e) {
			logger.error("加载系统时出错。异常如下:/n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
} 