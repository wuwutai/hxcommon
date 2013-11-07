/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: BusinessRuleUtil.java
 * @Package com.hongxiang.util
 * @Description: 业务规则工具类读取properties文件的包路径
 * @Author: 赵聪
 * @date 2013-04-17 15:46:00 +0800
 */
package com.hongxiang.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @description : 业务规则工具类读取properties文件的包路径
 * @Author: 赵聪
 * @Date: 2013-04-17 15:46:00 +0800
 */
public class BusinessRuleUtil {
    private static Map<String, String> ruleMap = new HashMap<String, String>();
    private static Map<String, String> transactionMap = new HashMap<String, String>();
    static {
        String path = WebUtil.getClassPath() + "businessrule";
        String transactionPath = WebUtil.getClassPath()+"transinfo";
        File rootFile = new File(path);
        File tranFile = new File(transactionPath);
        //递归调用文件
        recursionFile(rootFile);
        recursionTransactionFile(tranFile);
    }

    /** 
     * @Title: init 
     * @Author: 赵聪 
     * @date 2013-04-17 17:23:18 +0800
     * @Description: 初始化调用 
     */
    public void init() {
        
    }
    /** 
     * @Title: recursionTransactionFile 
     * @Description: 递归调用事务块文件
     * @param transactionPath
     */
    private static void recursionTransactionFile(File tranFile) {
        File[] chiledFiles2 = tranFile.listFiles();
        
        for (File file2 : chiledFiles2) {
            //是文件则读取文件内容
            if (file2.isFile()) {
                try {
                    PropertiesUtil pUtil2 = null;
                    pUtil2 = new PropertiesUtil();
                    //加载指定文件
                    String path2 = file2.getAbsolutePath();
                    Properties props2 = pUtil2.loadFile(path2);

                    Set<Object> keyset2 = props2.keySet();
                    for (Object obj2 : keyset2) {
                        String propKey2 = obj2.toString();
                            
                        if (transactionMap.get(propKey2) == null) {
                            transactionMap.put(obj2.toString(), props2.getProperty(obj2.toString()).toString());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                //是目录进行递归
                recursionTransactionFile(file2); 
            }
        }
    }
    /**
     * @Title: recursionFile
     * @Author: 赵聪
     * @date 2013-04-17 16:49:57 +0800
     * @Description: 递归文件目录
     * @param rootFile
     */
    private static void recursionFile(File rootFile) {
        File[] chiledFiles = rootFile.listFiles();
        
        for (File file : chiledFiles) {
            //是文件则读取文件内容
            if (file.isFile()) {
                try {
                    PropertiesUtil pUtil = null;
                    pUtil = new PropertiesUtil();
                    //加载指定文件
                    String path = file.getAbsolutePath();
                    Properties props = pUtil.loadFile(path);

                    Set<Object> keyset = props.keySet();
                    for (Object obj : keyset) {
                        String propKey = obj.toString();
                            
                        if (ruleMap.get(propKey) == null) {
                            ruleMap.put(obj.toString(), props.getProperty(obj.toString()).toString());
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                //是目录进行递归
                recursionFile(file); 
            }
        }

    }
    /** 
     * @Title: getBusinessRulePath 
     * @Author: 赵聪 
     * @date 2013-04-17 17:24:23 +0800
     * @Description: 根据业务规则key拿到业务规则路径 
     * @param key
     * @return
     */
    public static String getBusinessRulePath(Object key){
        return ruleMap.get(key);
    }
    /** 
     * @Title: getTransactionPath 
     * @Description: 根据key拿到事务块路径 
     * @param key
     * @return
     */
    public static String getTransactionPath(Object key) {
        return transactionMap.get(key);
    }

}
 