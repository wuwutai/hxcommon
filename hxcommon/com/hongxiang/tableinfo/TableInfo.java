package com.hongxiang.tableinfo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.hongxiang.util.FileCommon;


public class TableInfo  implements Serializable  {
    
    public String tableName;
    public List<Column> columns;
    
    public static String tablePath = "D:\\JavaCode\\hxweb\\config\\tables\\";
    
    public TableInfo(){
        columns = new ArrayList<Column>();
    }
    
    public TableInfo(String tableName){
        String json = FileCommon.GetFile(getProjectPath() + tableName + ".table");
//        String json = FileCommon.GetFile(tablePath + tableName + ".table");
        TableInfo tableInfo  = null;
        try {
            tableInfo = JSON.parseObject(json,TableInfo.class);
            System.out.println("**********init TableInfo*******Success");
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        
        this.tableName = tableInfo.tableName;
        this.columns = tableInfo.columns;
    }
    
    private String getProjectPath() {
        
            String classPathString = getClass().getProtectionDomain().getCodeSource()
                    .getLocation().getPath();
            classPathString = classPathString.substring(1, classPathString.length());
//            System.out.println("******************************"+classPathString+"*****************************");
            String webInfString = classPathString.substring(0, classPathString.lastIndexOf("WEB-INF"));
            
//            System.out.println("*********"+(webInfString + "classes/config/tables/")+"*********");
            return webInfString + "WEB-INF/classes/config/tables/";
//            return "D:/apache-tomcat-6.0.36/webapps/demo/WEB-INF/classes/config/tables/";
       
    }
    
    public List<Column> GetKeys(){
        List<Column> keys = new ArrayList<Column>();
        for (Column item : this.columns) 
            if(item.getPrimaryIdentifier())
                keys.add(item);
        
        return keys;
    };
    
  
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * @return columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @param columns to set columns 
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
} 