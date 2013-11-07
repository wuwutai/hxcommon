/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: DBTypeMap.java
 * @Package com.hongxiang.db
 * @Description: 把传入String类型的数据库类型转换成枚举，方便使用switch
 * @Author: 赵聪
 * @date 2013-04-07 10:46:55 +0800
 */
package com.hongxiang.util.db.dbconvert;

import java.util.HashMap;
import java.util.Map;

/**                              
 * @description : 把传入String类型的数据库类型转换成枚举，方便使用switch
 * @Author: 赵聪               
 * @Date: 2013-04-07 10:46:55 +0800 
 */
public final class DBTypeMap {
    private static Map<String, DBListEnum> map = new HashMap<String, DBListEnum>(4);
    static{
        map.put("mysql", DBListEnum.MYSQL);
        map.put("oracle", DBListEnum.ORACLE);
        map.put("sqlserver", DBListEnum.SQLSERVER);
    }
    public static DBListEnum getDBToEnum(String dataType){
       return   map.get(dataType.toLowerCase());
    }
}
 