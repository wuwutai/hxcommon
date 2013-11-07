/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: PrimaryKey.java
 * @Package com.hongxiang.dbutil
 * @Description: 生成表主键
 * @Author: Hanlx
 * @date 2013-04-02 13:31:56 +0800
 */
package com.hongxiang.util.db;

/**                              
 * @description : 生成表主键
 * @Author: Hanlx               
 * @Date: 2013-04-02 13:31:56 +0800 
 */
public class PrimaryKey {

    public static String CreatePrimaryKey() {
        return java.util.UUID.randomUUID().toString().replace("-","");
    }
}
 