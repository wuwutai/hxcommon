
package com.hongxiang.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.hongxiang.service.CacheService;

/**                              
 * @description :缓存拦截器
 * @Author: 刘馨远             
 * @Date: 2013-06-14 09:52:01 +0800 
 */
public class CachingInterceptor implements MethodInterceptor {
    private CacheService cacheService;
    public void setCacheService(CacheService cacheService)
    {
        this.cacheService = cacheService;
    }
    
    
    /** 
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation) 
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String key = (String)invocation.getArguments()[0];
        Object result = cacheService.get(key);
    //   如果函数返回结果不在Cache中,执行函数并将结果放入Cache
        if(result == null) 
        {   
            result = invocation.proceed();
            cacheService.put(key, result);
        }
        return result;
    }
    
    

}
 