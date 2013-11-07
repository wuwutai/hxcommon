package com.hongxiang.interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/*****************************
* @ClassName: DBChangeInterceptor
* @Description: 数据源切换拦截器
* @author: 刘馨远 
* @company: 北京鸿翔远成科技有限公司
* @date 2013-03-20 09:29:03 +0800
******************************/
public class DBChangeInterceptor implements Interceptor {
    
    
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
        
    }

    @Override
    public void init() {
        
    }
    
    @Override
    public String intercept(ActionInvocation actioninvocation) throws Exception {
//        if(CustomerContextHolder.getDbType()=="" || CustomerContextHolder.getDbType()==null ){
//            System.out.println("2222222222222222222222222222");
//            String dbType = JDomUtil.ParseCSL();
//            CustomerContextHolder.setDbOper(dbType);
//            CustomerContextHolder.setCustomerType(dbType);
//        }
        return actioninvocation.invoke();
    }

}
 