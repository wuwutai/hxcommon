/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: VtoMysql.java
 * @Package com.hongxiang.db
 * @Description: V5自定义数据库类型转MySql数据库类型
 * @Author: Hanlx
 * @date 2013-04-07 11:37:36 +0800
 */
package com.hongxiang.util.db.dbconvert;

import java.util.HashMap;
import java.util.Map;

/**                              
 * @description : V5自定义数据库类型转MySql数据库类型
 * @Author: Hanlx             
 * @Date: 2013-04-07 11:37:36 +0800 
 */
public final class VtoMysql {
    private static Map<String, String> map = new HashMap<String, String>(36);
    static {
        //数字类型
        map.put("integer", "INT");
        map.put("short integer", "SMALLINT");
        map.put("long integer", "BIGINT");
        map.put("byte", "TINYINT");
        map.put("number", "DECIMAL");
        map.put("decimal", "DECIMAL");
        map.put("float", "FLOAT");
        map.put("short float", "FLOAT");
        map.put("long float", "DOUBLE");
        map.put("money", "MONEY");
        map.put("serial", "DECIMAL");
        map.put("boolean", "bit");
        
        //字符类型
        map.put("characters", "CHAR");
        map.put("variable characters", "VARCHAR");
        map.put("long characters", "VARCHAR");
        map.put("long varcharacters", "TEXT");
        map.put("text", "TEXT");
        map.put("multibyte", "BLOB");
        map.put("variable multibyte", "LONGBLOB");

        //日期类型
        map.put("date", "DATE");
        map.put("time", "TIME");
        map.put("date time", "DATETIME");
        map.put("timestamp", "TIMESTAMP");

        //其它类型
        map.put("binary", "BLOB");
        map.put("long binary", "LOGNGBLOB");
        map.put("bitmap", "LOGNGBLOB");
        map.put("image", "LOGNGBLOB");
    }

    public static String getValue(String key) {
        return map.get(key.toLowerCase());
    }
}
 