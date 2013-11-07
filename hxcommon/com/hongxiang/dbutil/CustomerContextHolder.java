package com.hongxiang.dbutil;


/*****************************
* @ClassName: CustomerContextHolder
* @Description:
* @author: 刘馨远 
* @company: 北京鸿翔远成科技有限公司
* @date 2013-03-18 14:24:13 +0800
******************************/
public class CustomerContextHolder {
    
//    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
//    
//    private static String dbType ;
//    
//    
//    /**
//    * @Fields dbOper : 数据库操作接口（静态）
//    */
//    private static IDBOper dbOper;
//    
//    public static IDBOper getDbOper() {
//        return dbOper;
//    }
//
//    public static void setDbOper(String dbType) {
//        //获取数据库实现类路径
//        String path = "hongxiang.businessrule.dbimpl."+dbType+"Impl";
//        try {
//            //通过反射获取数据库实现类实例
//            dbOper = (IDBOper)Class.forName(path).newInstance();
//            CustomerContextHolder.dbOper = dbOper;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//    }
//
//    public static String getDbType() {
//        return dbType;
//    }
//
//    public static void setCustomerType(String customerType) {  
//        dbType = customerType;
//        contextHolder.set(customerType);  
//    }  
//      
//    public static String getCustomerType() {  
//        return contextHolder.get();  
//    }  
//      
//    public static void clearCustomerType() {  
//        contextHolder.remove();  
//    }  
}
 