package com.hongxiang.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
/**                              
 * @description : Properties文件操作类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:25:35 +0800 
 */
public class PropertiesUtil {
	
	private  Properties propertie;
	private  FileInputStream inputFile;
	private  FileOutputStream outputFile;
	private  String fileName;
    
	public PropertiesUtil(){
	    propertie = new Properties();
	}
	/**
	* <p>Title: 构造函数</p>
	* <p>Description: <构造函数/p>
	* @param String filePath 文件路径
	* @throws Exception
	*/ 
	public PropertiesUtil(String filePath) throws Exception {
	    propertie = new Properties();
		filePath = WebUtil.getClassPath()+filePath;
		//将有空格的路径 格式化
		filePath = filePath.replace("%20", " ");
		load(filePath);
	}
	
	
	/** 
	 * @Title: load 
	 * @Description: 读取属性文件的函数 
	 * @param filePath
	 * @throws Exception
	 */
	private void load(String filePath) throws Exception{
		inputFile = new FileInputStream(filePath);
		propertie.load(inputFile);
		inputFile.close();
	}
	/** 
     * @Title: load 
     * @Description: 读取属性文件的函数 
     * @param filePath
     * @throws Exception
     */
	public  Properties loadFile(String filePath){
	    try {
            inputFile = new FileInputStream(filePath);
	        propertie.load(inputFile);
	    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                inputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	    
	    return propertie;
	}
	/** 
     * @Title: load 
     * @Description: 读取属性文件的函数 
     * @param filePath
     * @throws Exception
     */
	private  void load() throws Exception{
	    String filePath = WebUtil.getClassPath();
	  //将有空格的路径 格式化
	    filePath = filePath.replace("%20", " ")+fileName;
        inputFile = new FileInputStream(filePath);
        propertie.load(inputFile);
        inputFile.close();
    }
	
	
	/** 
	 * @Title: getValue 
	 * @Description: 通过key获取属性文件的值
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);// 得到某一属性的值
			return value;
		}else{
			return "";
		}
	}

	
	/** 
	 * @Title: getValue 
	 * @Description: 重载函数，得到key的值
	 * @param filePath
	 * @param key
	 * @return
	 */
	public String getValue(String filePath, String key) {
		try {
			String value = "";
			load(filePath);//加载属性文件
			if (propertie.containsKey(key)) {
				value = propertie.getProperty(key);
				return value;
			}else{
				return value;
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		}catch (IOException e) {
			e.printStackTrace();
			return "";
		}catch (Exception ex) {
			ex.printStackTrace();
			return "";
	   }
	}

	
	/** 
	 * @Title: clear 
	 * @Description: 清除properties文件中所有的key和其值 
	 */
	public void clear() {
		propertie.clear();
	}

	
	/** 
	 * @Title: setValue 
	 * @Description: 根据key开始赋值
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value) {
		propertie.setProperty(key, value);
	}

	
	/** 
	 * @Title: saveFile 
	 * @Description: 将更改后的文件数据存入指定的文件中，该文件可以事先不存在
	 * @param fileName
	 * @param description
	 * @throws Exception
	 */
	public void saveFile(String fileName, String description) throws Exception {
		outputFile = new FileOutputStream(WebUtil.getClassPath() +  fileName);
		propertie.store(outputFile, description);
		outputFile.close();
	}


    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }


    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
 