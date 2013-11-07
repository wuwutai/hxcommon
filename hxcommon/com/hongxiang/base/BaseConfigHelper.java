package com.hongxiang.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.hongxiang.util.PropertiesUtil;
import com.hongxiang.util.WebUtil;

/**                              
 * @description : 基础配置文件
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 11:02:20 +0800 
 */
public class BaseConfigHelper {
	
	/** 
	 * @Title: setBaseConfig 
	 * @Description: 设置全局变量的函数 
	 * @param context
	 * @param baseconfigbean
	 */
	public static void setBaseConfig(ServletContext context,BaseConfigBean baseconfigbean){
		context.setAttribute(BaseConfigBean.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, baseconfigbean);
	}
	
	
	/** 
	 * @Title: getBaseConfig 
	 * @Description:  获取全局变量的函数 
	 * @param context
	 * @return
	 */
	public static BaseConfigBean getBaseConfig(ServletContext context){
		return (BaseConfigBean)context.getAttribute(BaseConfigBean.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}
	
	
	/** 
	 * @Title: init 
	 * @Description: 初始化bean函数 利用java反射机制
	 * @param servletcontextevent
	 * @throws Exception
	 */
	public static void init(ServletContextEvent servletcontextevent) throws Exception{
	    //加载系统配置文件
	    PropertiesUtil sysProperties = new PropertiesUtil("system.properties");
	    Class<?> baseconfigbeanClass = Class.forName("com.hongxiang.base.BaseConfigBean");
	    //创建实例
	    BaseConfigBean baseconfigbean = (BaseConfigBean)baseconfigbeanClass.newInstance();
	    Field[] fields = baseconfigbeanClass.getDeclaredFields();
	    
	    String[] noSetMethodStr = {"ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE","ROOT_WEB_SESSION_USER","ROOT_WEB_SESSION_THEME","customParameter"};
	    List<String> noSetMethods = Arrays.asList(noSetMethodStr);
	    
	    //开始添加属性
	    for(Field field : fields){
	        String fieldName = field.getName();
	        if(!noSetMethods.contains(fieldName)){
	            String fieldTypeString = field.getType().toString();
	            String val = sysProperties.getValue(fieldName);
	            String setMethodName = "set" + WebUtil.firstWordToUpperCase(fieldName);
	               
	            // 获取方法
	            Method method = baseconfigbeanClass.getMethod(setMethodName, field.getType());
	            
	            //当为int类型时
	            if("int".equals(fieldTypeString)){
	                method.invoke(baseconfigbean,Integer.valueOf(val));
	            }else{
	                method.invoke(baseconfigbean,val);
	            }
	        }
	    }
	    
	    //将属性添加全局变量中
	    BaseConfigHelper.setBaseConfig(servletcontextevent.getServletContext(),baseconfigbean);
	    
	}
	
	
}
 