/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: VtoOracle.java
 * @Package com.hongxiang.db
 * @Description: V5自定义数据库类型转Oracle数据库类型
 * @Author: Hanlx
 * @date 2013-04-07 10:38:52 +0800
 */
package com.hongxiang.util.db.dbconvert;

import java.util.HashMap;
import java.util.Map;

/**                              
 * @description : V5自定义数据库类型转Oracle数据库类型
 * @Author: Hanlx             
 * @Date: 2013-04-07 10:38:52 +0800 
 */
public final class VtoOracle {
    private static Map<String, String> map = new HashMap<String, String>(36);
    static {
        //数字类型
        map.put("integer", "INTEGER");
        map.put("short integer", "SMALLINT");
        map.put("long integer", "INTEGER");
        map.put("byte", "SMALLINT");
        map.put("number", "NUMBER");
        map.put("decimal", "DECIMAL");
        map.put("float", "FLOAT");
        map.put("short float", "REAL");
        map.put("long float", "REAL");
        map.put("money", "NUMBER");
        map.put("serial", "NUMBER");
        map.put("boolean", "SMALLINT");
        
        //字符类型
        map.put("characters", "CHAR");
        map.put("variable characters", "VARCHAR2");
        map.put("long characters", "VARCHAR2");
        map.put("long varcharacters", "LONG");
        map.put("text", "LONG");
        map.put("multibyte", "NCHAR");
        map.put("variable multibyte", "NVARCHAR2");

        //日期类型
        map.put("date", "DATE");
        map.put("time", "DATE");
        map.put("date Time", "DATE");
        map.put("timestamp", "TIMESTAMP");

        //其它类型
        map.put("binary", "RAW");
        map.put("long binary", "LONG RAW");
        map.put("bitmap", "BLOB");
        map.put("image", "BLOB");
    }

    public static String getValue(String key) {
        return map.get(key.toLowerCase());
    }
}
 