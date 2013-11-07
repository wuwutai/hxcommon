package com.hongxiang.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*****************************
* @ClassName: DataSourceUtil
* @Description: 数据库操作类
* @author: 刘馨远 
* @company: 北京鸿翔远成科技有限公司
* @date 2013-03-21 16:48:08 +0800
******************************/
public class DataSourceUtil {

    
    public static ResultSet laodData(String testsql){
        return null;
//        JdbcBean jdbcBean = JDomUtil.ParseCSL();
//        java.sql.Connection conn = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(jdbcBean.getClassName()).newInstance();
//       
//       
//        //myDB为数据库名   
//        conn =   DriverManager.getConnection(jdbcBean.getUrl() , jdbcBean.getName() , jdbcBean.getPassword()) ;    
//        java.sql.Statement stmt =  conn.createStatement() ;  
//         rs = stmt.executeQuery(testsql) ; 
//         Boolean a = rs.first();
//         System.out.println(a);
//         if(rs != null){   // 关闭记录集   
//             try{   
//                 rs.close() ;   
//             }catch(SQLException e){   
//                 e.printStackTrace() ;   
//             }   
//               }   
//               if(stmt != null){   // 关闭声明   
//             try{   
//                 stmt.close() ;   
//             }catch(SQLException e){   
//                 e.printStackTrace() ;   
//             }   
//               }   
//               if(conn != null){  // 关闭连接对象   
//              try{   
//                 conn.close() ;   
//              }catch(SQLException e){   
//                 e.printStackTrace() ;   
//              }   
//               }  
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rs;
    }
//    
    
}
 