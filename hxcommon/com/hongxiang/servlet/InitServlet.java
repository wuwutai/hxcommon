package com.hongxiang.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**                              
 * @description : 项目初始化servlet用于初始化数据
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:50:36 +0800 
 */
public class InitServlet extends HttpServlet{
	
	/**
     * @Fields logger : log4j句柄
     */
    protected Logger logger = Logger.getLogger(getClass());  
    
	/**
	* @Fields serialVersionUID : serialVersionUID
	*/
	private static final long serialVersionUID = -1160700935418962853L;
	
	
	/*
	* <p>Title: init</p>
	* <p>Description: </p>
	* @param servletConfig
	* @throws ServletException
	* @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	*/
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		logger.info("系统正在加载静态数据……");
		
		
		
//		IStaticDataUtil staticDataUtilService = (IStaticDataUtil) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("staticDataUtilService");
//		staticDataUtilService.initStaticData(getServletContext());
	}
	
}
 