package com.hongxiang.dbutil;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/*****************************
* @ClassName: DynamicDataSource
* @Description: 动态配置数据源
* @author: 刘馨远 
* @company: 北京鸿翔远成科技有限公司
* @date 2013-03-18 14:24:25 +0800
******************************/
public class DynamicDataSource extends AbstractRoutingDataSource  {
    @Override
    public Object determineCurrentLookupKey() { 
//        return CustomerContextHolder.getCustomerType(); 
        return null;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

//    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//        // TODO Auto-generated method stub
//        return null;
//    }  
}
 