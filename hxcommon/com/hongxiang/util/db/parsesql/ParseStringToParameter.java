/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: ParseStringToParameter.java
 * @Package com.hongxiang.util.db.parsesql
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: 秦京
 * @date 2013-08-14 11:05:06 +0800
 */
package com.hongxiang.util.db.parsesql;

import java.util.HashMap;
import java.util.Map;


/**                              
 * @description : 字符串转为符合MyBatis的parameterType类型
 * @Author: 秦京             
 * @Date: 2013-08-14 11:05:06 +0800 
 */
public final class ParseStringToParameter {
    private static Map<String, ParameterType>  typeMap = new HashMap<String, ParameterType>(36);
    static{
        //number 类型
        typeMap.put("integer", ParameterType.INT);
        typeMap.put("short integer", ParameterType.SHORT);
        typeMap.put("long integer", ParameterType.LONG);
        typeMap.put("byte", ParameterType.BYTE);
        typeMap.put("number", ParameterType.BIGDECIMAL);
        typeMap.put("decimal", ParameterType.BIGDECIMAL);
        typeMap.put("float", ParameterType.FLOAT);
        typeMap.put("short float",ParameterType.FLOAT);
        typeMap.put("money", ParameterType.BIGDECIMAL);
        typeMap.put("long float", ParameterType.DOUBLE);
        typeMap.put("serial", ParameterType.INT);
        //boolean类型
        typeMap.put("boolean", ParameterType.BOOLEAN);
        //string 类型
        typeMap.put("long varcharacters", ParameterType.STRING);
        typeMap.put("characters", ParameterType.STRING);
        typeMap.put("variable characters", ParameterType.STRING);
        typeMap.put("long characters", ParameterType.STRING);
        typeMap.put("text", ParameterType.STRING);
        typeMap.put("multibyte", ParameterType.STRING);
        typeMap.put("variable multibyte", ParameterType.STRING);
        typeMap.put("date", ParameterType.STRING);
        typeMap.put("time", ParameterType.STRING);
        typeMap.put("date time", ParameterType.STRING);
        typeMap.put("timestamp", ParameterType.STRING);
        //normal类型
        typeMap.put("binary", ParameterType.STREAM);
        typeMap.put("long binary", ParameterType.STREAM);
        typeMap.put("bitmap", ParameterType.STREAM);
        typeMap.put("image", ParameterType.STREAM);
    }
    
    public static ParameterType getParameterType(String fieldType){
        return typeMap.get(fieldType.toLowerCase());
    }
}
 