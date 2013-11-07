/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: VtoSybase.java
 * @Package com.hongxiang.db
 * @Description: V5自定义数据库类型转Sybase数据库类型
 * @Author: Hanlx
 * @date 2013-04-07 11:38:37 +0800
 */
package com.hongxiang.util.db.dbconvert;

import java.util.HashMap;
import java.util.Map;

/**                              
 * @description : V5自定义数据库类型转Sybase数据库类型
 * @Author: Hanlx             
 * @Date: 2013-04-07 11:38:37 +0800 
 */
public final class VtoSybase {
    private static Map<String, String> map = new HashMap<String, String>(36);
    static {
        //数字类型
        map.put("integer", "INT");
        map.put("short integer", "SMALLINT");
        map.put("long integer", "INT");
        map.put("byte", "TINYINT");
        map.put("number", "NUMERIC");
        map.put("decimal", "DECIMAL");
        map.put("float", "FLOAT");
        map.put("short float", "REAL");
        map.put("long float", "FLOAT");
        map.put("money", "MONEY");
        map.put("serial", "NUMERIC");
        map.put("boolean", "TINYINT");
        
        //字符类型
        map.put("characters", "CHAR");
        map.put("variable characters", "VARCHAR");
        map.put("long characters", "VARCHAR");
        map.put("long varcharacters", "TEXT");
        map.put("text", "TEXT");
        map.put("multibyte", "NCHAR");
        map.put("variable multibyte", "NVARCHAR");

        //日期类型
        map.put("date", "DATETIME");
        map.put("time", "DATETIME");
        map.put("date time", "DATETIME");
        map.put("timestamp", "TIMESTAMP");

        //其它类型
        map.put("binary", "BINARY");
        map.put("long binary", "VARBINARY");
        map.put("bitmap", "IMAGE");
        map.put("image", "IMAGE");
    }

    public static String getValue(String key) {
        return map.get(key.toLowerCase());
    }
}
 