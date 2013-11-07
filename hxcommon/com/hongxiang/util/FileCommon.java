package com.hongxiang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

public class FileCommon {
    
    /**
     * 获取网站根路径
     * */
    public static String GetWebRoot(){
        String path = ServletActionContext.getServletContext().getRealPath("/");
        File f = new File(path);
        for(int i = 0;i < 3;i++)
           f = f.getParentFile();
        
        return f.getPath();
    }
    
    /**
     * 获取文件
     * @param path 路径
     * */
    public static String GetFile(String path) {
        FileReader fReader = null;
        BufferedReader br = null;
        StringBuffer sb=new StringBuffer();
        try{
            File file = new File(path);
            if(!file.exists() || file.isDirectory()) return "";
            fReader = new FileReader(file);
            br = new BufferedReader(fReader);
            String line = br.readLine();
            while(line != null){
                sb.append(line);
                line = br.readLine();
            }
        }catch(IOException e){return ""; }
        finally{try{
            if(br != null)br.close(); 
            if(fReader != null) fReader.close();
        }catch(IOException e){};}
                
        return sb.toString();
    }
    
    /**
     * 路径合并
     * @param path 路径节点
     * */
    public static String Combine(String... path){
        StringBuffer result = new StringBuffer();
        for(int i = 0;i < path.length; i++){
            result.append(path[i]);
            if(result.lastIndexOf(File.separator) != result.length()-1 && i != path.length-1 )
                result.append(File.separator);
        }
        
        return result.toString();
    }
    
    /**
     * 获取文件类型
     * */
    public static String GetType(String fileName)
    {
       int index = fileName.lastIndexOf(".");
       if(index == -1) return "Directory";
       
       return fileName.substring(index+1);
    }
    
    /**
     * 根据文件类型从根节点开始扫描文件
     * @param root 根节点
     * @param type 文件类型
     * */
    public static ArrayList<String> GetFiles(final String root,final String type)
    {
        ArrayList<String> result = new  ArrayList<String>();
        File file = new File(root);
        if(!file.exists() || !file.isDirectory()) return result;
        for(File f : file.listFiles())
             if(f.isDirectory()) 
                 getFiles(f,type,result);
             else 
                 if(GetType(f.getPath()).toLowerCase().equals(type)) 
                     result.add(f.getPath());
   
        return result;
    }
    private static void getFiles(File file,String type,ArrayList<String> result)
    {
        for (File f : file.listFiles())
            if(f.isDirectory()) 
                getFiles(f,type,result);
            else{
                if(GetType(f.getPath()).toLowerCase().equals(type))
                    result.add(f.getPath());
            }
    }
    
    /**
     * inputStream2tring
     * */
    public static String inputStream2String(InputStream is){
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = in.readLine()) != null)
              buffer.append(line);
        } catch (IOException e) {
           return null;
        }
        
        return buffer.toString();
     }

    /**
     * 讲字符串输出到响应报文
     * */
    public static void ResponseWrite(String str){
        PrintWriter out = null;
        try {
            out = ServletActionContext.getResponse().getWriter();
            out.write(str);
        } catch (Exception e) {}
        finally{  if(out!=null) out.close();}
    }
    
    
} 