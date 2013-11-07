package com.hongxiang.interceptor;


import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import com.hongxiang.service.CacheService;

public class RemoveCacheInterceptor implements AfterReturningAdvice {

    @SuppressWarnings("unused")
    private CacheService cacheService;
    public void setCacheService(CacheService cacheService)
    {
        this.cacheService = cacheService;
    }

    public void afterReturning(Object object, Method method, Object[] args, Object target) throws Throwable
    {
        System.out.println("----------------------删除缓存拦截器----------------------");
        String cacheKey = target.getClass().getName();
        System.out.println(cacheKey);

        cacheService.remove(cacheKey);
    }

}
 