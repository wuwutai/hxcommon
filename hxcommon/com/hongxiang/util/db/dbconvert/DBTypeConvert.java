/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: DBTypeConvert.java
 * @Package com.hongxiang.db
 * @Description: 数据库字段类型转换
 * @Author: 赵聪
 * @date 2013-04-07 10:56:26 +0800
 */
package com.hongxiang.util.db.dbconvert;

import java.util.HashMap;
import java.util.Map;

import com.hongxiang.util.db.paramater.EParameterType;


/**                              
 * @description : 数据库字段类型转换
 * @Author: 赵聪               
 * @Date: 2013-04-07 10:56:26 +0800 
 * @修改人：Hanlx
 * @修改日期：2013-04-07 13:40
 */
public final class DBTypeConvert {
    
    private static Map<String, EParameterType>  typeMap = new HashMap<String, EParameterType>(36);
    static{
        //number 类型
        typeMap.put("integer", EParameterType.NUMBER);
        typeMap.put("short integer", EParameterType.NUMBER);
        typeMap.put("long integer", EParameterType.NUMBER);
        typeMap.put("byte", EParameterType.NUMBER);
        typeMap.put("number", EParameterType.NUMBER);
        typeMap.put("decimal", EParameterType.NUMBER);
        typeMap.put("float", EParameterType.NUMBER);
        typeMap.put("short float", EParameterType.NUMBER);
        typeMap.put("money", EParameterType.NUMBER);
        typeMap.put("long float", EParameterType.NUMBER);
        typeMap.put("serial", EParameterType.NUMBER);
        //boolean类型
        typeMap.put("boolean", EParameterType.BOOLEAN);
        //string 类型
        typeMap.put("long varcharacters", EParameterType.STRING);
        typeMap.put("characters", EParameterType.STRING);
        typeMap.put("variable characters", EParameterType.STRING);
        typeMap.put("long characters", EParameterType.STRING);
        typeMap.put("text", EParameterType.STRING);
        typeMap.put("multibyte", EParameterType.STRING);
        typeMap.put("variable multibyte", EParameterType.STRING);
        typeMap.put("date", EParameterType.STRING);
        typeMap.put("time", EParameterType.STRING);
        typeMap.put("date time", EParameterType.STRING);
        typeMap.put("timestamp", EParameterType.STRING);
        //normal类型
        typeMap.put("binary", EParameterType.NORMAL);
        typeMap.put("long binary", EParameterType.NORMAL);
        typeMap.put("bitmap", EParameterType.NORMAL);
        typeMap.put("image", EParameterType.NORMAL);
    }
    
    public static EParameterType getParameterType(String key){
        return typeMap.get(key);
    }
    
     public static String getDBTypeFieldValue(String dataType, String fieldType){
         DBListEnum dbEnum = DBTypeMap.getDBToEnum(dataType);
         String convertFieldType = "";
         switch (dbEnum) {
         case MYSQL:
             convertFieldType =  VtoMysql.getValue(fieldType);
             break;
         case SQLSERVER:
             convertFieldType =  VtoMSSQL.getValue(fieldType);
             break;
         case ORACLE:
             convertFieldType =  VtoOracle.getValue(fieldType);
             break;
         case DB2:
             convertFieldType =  VtoDB2.getValue(fieldType);
             break;
         case INFORMIX:
             convertFieldType =  VtoInformix.getValue(fieldType);
             break;
         case SYBASE:
             convertFieldType =  VtoSybase.getValue(fieldType);
             break;
         default:
             break;
         }
         return convertFieldType;
     }
     /** 
      * @Title: reasonFieldTypeGetValue 
      * @Description: 根据类型拿到合法的value
      * @param value
      * @param paramType
      * @return
      */
     public static  String reasonFieldTypeGetValue(String value, String fieldType) {
         EParameterType paramType = DBTypeConvert.getParameterType(fieldType);
         String resonValue = "";
         switch (paramType) {
         case NUMBER:
             resonValue = value;
             break;
         default:
             resonValue = "'"+value+"'";
             break;
         }
         return resonValue;
     }
}
 