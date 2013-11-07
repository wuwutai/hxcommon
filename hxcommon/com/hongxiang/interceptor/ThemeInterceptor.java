package com.hongxiang.interceptor;

import com.hongxiang.base.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**                              
 * @description : 主题过滤拦截器，用来获取当前用户使用的主题信息
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 12:22:38 +0800 
 */
public class ThemeInterceptor implements Interceptor {

    private static final long serialVersionUID = 7539867667830346987L;

    /*
    * <p>Title: 销毁方法</p>
    * <p>Description: </p>
    * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
    */
    @Override
    public void destroy() {
        
    }

    /*
    * <p>Title: 初始化方法</p>
    * <p>Description: </p>
    * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
    */
    @Override
    public void init() {
        
    }

    /*
    * <p>Title: 拦截器实现函数</p>
    * <p>Description: </p>
    * @param arg0
    * @return
    * @throws Exception
    * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
    */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String themeName = "default";
        BaseAction action = (BaseAction) invocation.getAction();
        action.setThemeName(themeName);
        return invocation.invoke();
    }

}
 