package com.hongxiang.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;


/**                              
 * @description : 列表的通用过滤函数
 * @Author: 刘馨远                 
 * @Date: 2013-04-01 15:56:57 +0800 
 */
public class SortListUtil<E> {
	
	/**
     * @Fields log : log4j句柄
     */
    protected Logger log = Logger.getLogger(getClass()); 
    
    
    /** 
     * @Title: sort 
     * @Description: 排序方法
     * @param list
     * @param method
     * @param sort
     */
    @SuppressWarnings(value = "unchecked")
	public void sort(List<E> list, final String method, final String sort){
		
    	//调用比较函数
		Collections.sort(list, new Comparator() {
			public int compare(Object a, Object b) {
				//标志位
			    int ret = 0;
	
			    try{
	
				    Method m1 = ((E)a).getClass().getMethod(method, null);
		
				    Method m2 = ((E)b).getClass().getMethod(method, null);
		
				    if(sort != null && "desc".equals(sort)){//倒序
				    	ret = m2.invoke(((E)b), null).toString().compareTo(m1.invoke(((E)a), null).toString());
				    }else{//正序
				    	ret = m1.invoke(((E)a), null).toString().compareTo(m2.invoke(((E)b), null).toString());
				    }
	
			    }catch(NoSuchMethodException ne){
			    	log.error(ne.getMessage());
			    }catch(IllegalAccessException ie){
			    	log.error(ie.getMessage());
				}catch(InvocationTargetException it){
					log.error(it.getMessage());
				}
			    return ret;
		    }
		});
    }
}
 