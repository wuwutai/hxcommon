/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: VtoDB2.java
 * @Package com.hongxiang.db
 * @Description: V5自定义数据库类型转DB2数据库类型
 * @Author: Hanlx
 * @date 2013-04-07 11:37:45 +0800
 */
package com.hongxiang.util.db.dbconvert;

import java.util.HashMap;
import java.util.Map;

/**                              
 * @description : V5自定义数据库类型转DB2数据库类型
 * @Author: Hanlx             
 * @Date: 2013-04-07 11:37:45 +0800 
 */
public final class VtoDB2 {
    private static Map<String, String> map = new HashMap<String, String>(36);
    static {
        //数字类型
        map.put("integer", "INT");
        map.put("short integer", "SMALLINT");
        map.put("long integer", "BIGINT");
        map.put("byte", "SMALLINT");
        map.put("number", "DECIMAL");
        map.put("decimal", "DECIMAL");
        map.put("float", "FLOAT");
        map.put("short float", "FLOAT");
        map.put("long float", "FLOAT");
        map.put("money", "DECIMAL");
        map.put("serial", "DECIMAL");
        map.put("boolean", "SMALLINT");
        
        //字符类型
        map.put("characters", "CHAR");
        map.put("variable characters", "VARCHAR");
        map.put("long characters", "VARCHAR");
        map.put("long varcharacters", "CLOB");
        map.put("text", "CLOB");
        map.put("multibyte", "NCHAR");
        map.put("variable multibyte", "NVARCHAR");

        //日期类型
        map.put("date", "TIMESTAMP");
        map.put("time", "TIMESTAMP");
        map.put("date time", "TIMESTAMP");
        map.put("timestamp", "CHAR(8)");

        //其它类型
        map.put("binary", "CHAR(?) FOR BIT DATA");
        map.put("long binary", "VARCHAR({?}) FOR BIT DATA");
        map.put("bitmap", "BLOB");
        map.put("image", "BLOB");
    }

    public static String getValue(String key) {
        return map.get(key.toLowerCase());
    }
}
 