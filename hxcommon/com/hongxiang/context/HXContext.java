/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: RetrieveRuleMapperImpl.java
 * @Package hongxiang.businessrule.retrieverule.mapper.impl
 * @Description: 
 * @Author: 韩临霄
 * @date 2013-10-12 17:15:25 +0800
 */
package com.hongxiang.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;

import com.opensymphony.xwork2.ActionContext;


public class HXContext {
       
    public HXContext(){};
    
    private Map<String,Object> brResultPool = new HashMap<String, Object>();
    
    //添加规则返回值
    public void AddBrResult(String key,Object value){
        brResultPool.put(key, value);
    }
    
    //获取规则返回值
    public Object GetBrResult(String key){
        return brResultPool.get(key);
    }
    
    
    /**
     * action上下文
     * */
    private ActionContext actionContext;

    /**
     * 方法函数上下文
     * */
    private MethodContext methodContext;
    
    
    /**
     * ServletConfig
     * */
    private ServletConfig servletConfig;
    
    /**
     * @return actionContext
     */
    public ActionContext getActionContext() {
        return actionContext;
    }

    /**
     * @param actionContext to set actionContext 
     */
    public void setActionContext(ActionContext actionContext) {
        this.actionContext = actionContext;
    }

    /**
     * @return methodContext
     */
    public MethodContext getMethodContext() {
        return methodContext;
    }

    /**
     * @param methodContext to set methodContext 
     */
    public void setMethodContext(MethodContext methodContext) {
        this.methodContext = methodContext;
    }

    /**
     * @return servletConfig
     */
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    /**
     * @param servletConfig to set servletConfig 
     */
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    /** 
     * @Title: 
     * @Description: Ĭ�Ϲ��캯��
     * @param actionContext
     * @param methodContext
     * @param servletConfig 
     */
    public HXContext(ActionContext actionContext, MethodContext methodContext,
        ServletConfig servletConfig) {
        super();
        this.actionContext = actionContext;
        this.methodContext = methodContext;
        this.servletConfig = servletConfig;
    }
    
}
 