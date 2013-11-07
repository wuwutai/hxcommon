package com.hongxiang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**                              
 * @description : 文件操作类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:16:38 +0800 
 */
public class FileUtil {
	
	/**
	* @Fields log : log4j句柄
	*/
	protected static Logger log = Logger.getLogger(FileUtil.class); 

	/** 
	 * @Title: getPackagePathByWildcards 
	 * @Description:  通过通配符查找文件夹或者文件所属的包名 
	 * @param wildcards
	 * @param separator
	 * @return
	 */
	public static String getPackagePathByWildcards(String wildcards,String separator){
		StringBuffer result = new StringBuffer();
		String tmp = "";
		try {
			ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver(); 
	        Resource[] source = resourceLoader.getResources(wildcards); 
	        for (int i = 0; i < source.length; i++) { 
	            Resource resource = source[i]; 
	            String uri = resource.getURL().toString();
	            if(uri.indexOf("classes") == -1){
                    tmp = uri.substring((uri.indexOf("jar!")+5),uri.length()-1).replace("/", ".");
                    System.out.println(uri.substring((uri.indexOf("jar!")+5),uri.length()-1).replace("/", "."));
                }else{
                    tmp = (uri.substring((uri.indexOf("classes")+8),uri.length()-1)).replace("/", ".");
                    System.out.println(tmp);
                }
	            if(i==0){
	            	result.append(tmp);
	            }else{
	            	result.append(separator);
	            	result.append(tmp);
	            }
	        } 
		} catch (IOException e) {
			log.error("获取包路径失败，异常如下："+e.getMessage());
		} 
		return result.toString();
	}

    /** 
     * @Title: getSchemaTableInfo 
     * @Description: 把文件的信息转化成字符串
     * @param formName
     * @return
     */
    public static String getFile(String path) {
        BufferedReader br = null;
        String dataInfo = "";
        String str1 = "";
        
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            
            while ((str1 = br.readLine()) != null) {
                dataInfo += str1;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataInfo;
    }
    
  
}
 