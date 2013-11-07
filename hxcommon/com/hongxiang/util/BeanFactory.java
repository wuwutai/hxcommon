/**  
 * @Title: BeanFactory.java
 * @Package com.soooqooo.util
 * @Description: 
 * @author: 梁桂林   
 * @date: 2012-05-09 12:40:54 +0800
 * @company: 上海苏秦网络技术有限公司
 * @version V1.0  
 */
package com.hongxiang.util;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**                              
 * @description : 获取应用程序中的上下文和bean
 * @Author: 刘馨远                 
 * @Date: 2013-04-08 11:22:27 +0800 
 */
public class BeanFactory {

	
	/**
	* @Fields wac : 应用程序上下文
	*/
	private static WebApplicationContext wac;
 
	/** 
	 * @Title: getBean 
	 * @Description: 根据bean的类型得到bean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		ensureWebApplicationContext();
		return (wac.getBean(clazz));
	}

	/** 
	 * @Title: ensureWebApplicationContext 
	 * @Description:  如果wac没有值,则赋值
	 */
	private static void ensureWebApplicationContext() {
		if (wac == null) {
			wac = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		}
	}

	/** 
	 * @Title: getBean 
	 * @Description: 根据bean的名字得到bean
	 * @param name
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name, Class<T> clazz) {
		ensureWebApplicationContext();
		return (T) (wac.getBean(name));
	}
}
 