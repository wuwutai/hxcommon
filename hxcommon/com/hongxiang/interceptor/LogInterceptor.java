package com.hongxiang.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hongxiang.base.BaseAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**                              
 * @description : 日志拦截器
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 12:22:19 +0800 
 */
public class LogInterceptor extends AbstractInterceptor {
    
    private static final long serialVersionUID = -7929172616238516132L;
    
    /**
    * @Fields log : log4j句柄
    */
    protected Logger log = Logger.getLogger(getClass());  
    
    /**
    * @Fields sysLogService : 系统日志service
    */
//    @Autowired(required = true)
//    private ILoggerService sysLogService;
    
    /*
    * <p>Title: 系统操作日志拦截器  intercept</p>
    * <p>Description: </p>
    * @param ActionInvocation invocation
    * @return
    * @throws Exception
    * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
    */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception{
        //获取此次调用的方法名
        String method = invocation.getProxy().getMethod();
        String className = invocation.getProxy().getConfig().getClassName();
        String resourceKeyName = className + "." + method;
        
        //获取baseAction实例
        BaseAction action = (BaseAction) invocation.getAction();
        //将日志句柄传入BaseAction中
        action.setLogResourceKeyName(resourceKeyName);
        
        //执行action
        String resultcode = invocation.invoke();
        //获取request
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
        //保存操作日志
//        sysLogService.saveLog(LogType.OPERATION,"",resourceKeyName, request);
        return resultcode;
    }

}
 