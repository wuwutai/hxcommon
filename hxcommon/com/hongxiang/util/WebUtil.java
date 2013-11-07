package com.hongxiang.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.hongxiang.base.BaseAction;

/**                              
 * @description : 工具类，主要用来获取客户端的系统参数、客户端的ip地址、当前项目的url和项目的绝对路径
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:29:02 +0800 
 */
public class WebUtil {
	
    /**
     * @Fields mixings : 随机字符串，用于文件重命名
     */ 
     private static final String mixings = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
     
     /**
     * @Fields f : 日期格式
     */ 
     private static SimpleDateFormat f = new SimpleDateFormat("MMddyyHHmmss");
     
     
     
    /** 
     * @Title: firstWordToUpperCase 
     * @Description: 将字符串首字母转换成大写字母的函数
     * @param str
     * @return
     */
    public static String firstWordToUpperCase(String str){
         Pattern expression = Pattern.compile("\\w+");
         Matcher matcher = expression.matcher(str);
         String result = "";
         while (matcher.find()){
             result = matcher.group();
             String x = String.valueOf((char)(result.charAt(0)-32));
             result = result.replaceFirst("[a-zA-Z]",x);
         }
         return result;
     }
	
	/** 
	 * @Title: getClassPath 
	 * @Description:  返回系统的classes根目录全路径 
	 * @return
	 */
	public static String getClassPath(){
		String path = "";
		Properties prop = System.getProperties();
		//获取操作系统类型
		String os = prop.getProperty("os.name");
		//代表是windows系统
		if(os.startsWith("win") || os.startsWith("Win")){
			path = WebUtil.class.getResource("/").getPath().toString().substring(1);
		}else{
			path = WebUtil.class.getResource("/").getPath().toString();
		}
		prop = null;
		return path;
	}
	
	/** 
     * @Title: getWebRootAbsolutePath 
     * @Description: 获取web绝对路径 
     * @return
     */
    public static String getWebRootAbsolutePath(){
        return BaseAction.getApplication().getRealPath("/");
    }
	
    /** 
     * @Title: getWebAbsolutePath 
     * @Description: 根据相对路径返回绝对路径
     * @param path
     * @return
     */
    public static String getWebAbsolutePath(String path){
        return BaseAction.getApplication().getRealPath(path);
    }
    
	/** 
	 * @Title: getRemoteAddr 
	 * @Description: 获取客户端ip地址 
	 * @return
	 */
	public static String getRemoteAddr(){
		return BaseAction.getRequest().getRemoteAddr();
	}
	
	
	/** 
	 * @Title: getRemoteSystem 
	 * @Description: 获取客户端的系统版本
	 * @return
	 */
	public static String getRemoteSystem(){
		return BaseAction.getRequest().getHeader("User-Agent");
	}
	
	
	/** 
	 * @Title: getServerHomeUrl 
	 * @Description: 获取服务器主机名，例如http://localhost:8080
	 * @return
	 */
	public static String getServerHomeUrl(){
		HttpServletRequest request = BaseAction.getRequest();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	}
	
	

	
	

	/** 
	 * @Title: getFileExtName 
	 * @Description:  获取文件后缀名
	 * @param fileName
	 * @return
	 */
	public static String getFileExtName(String fileName){
		String extName = "";
		if (fileName.lastIndexOf(".") >= 0){  
			extName = fileName.substring(fileName.lastIndexOf("."));  
		}
		return extName;
	}
	
	
	/** 
	 * @Title: getRanName 
	 * @Description: 根据时间生成一个随机数 
	 * @return
	 */
	public static String getRanName(){
	    int length = 6;
	    Date date = new Date();
        String d = f.format(date.getTime());
        StringBuffer sb = new StringBuffer(d);
        Random random = new Random();
        for (int i = 0; i < length; i++){
            sb.append(mixings.charAt(random.nextInt(mixings.length())));
        }
        return sb.toString();
	}
	
	/** 
	 * @Title: getfilePath 
	 * @Description: 将字符串转成小写
	 * @param fielPath
	 * @return
	 */
	public static String getfilePath(String fielPath){
	    
	    return fielPath.toLowerCase();
	    
	}
	
}
 