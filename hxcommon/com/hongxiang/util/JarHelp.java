package com.hongxiang.util;

import com.hongxiang.util.FileCommon;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarHelp extends URLClassLoader{

    public JarHelp(URL url) {
        super(new URL[] { url },Thread.currentThread().getContextClassLoader());  
    }
    
    /**
     * 执行jar文件中的一个类文件中的一个方法
     * @param jarPath jar路径
     * @param classPath 类文件路径
     * @param methodName 函数名
     * @arg 参数类型
     * */
    public static Object executeJarClass(String jarPath,String classPath,String methodName,Class<?>... arg)
    {
        try {
            Class<?> klz = JarHelp.getClassObject(jarPath,classPath);
            Object klzObj = klz.newInstance();
            return klz.getClass().getMethod(methodName, arg).invoke(klzObj, arg);
        } catch (Exception e) {}
        return null;        
    }
    
    /**
     * 加载jar中的一个类
     * */
    public static Class<?> getClassObject(String jarPath,String classPath)
    {
        try {
            URL[] urls = new URL[]{ new File(jarPath).toURI().toURL() };
            JarHelp loader = new JarHelp(new File(jarPath).toURI().toURL());  
            Class <?> klz = loader.loadClass(classPath);
            return klz;
        } catch (Exception e) {
            System.out.println(jarPath+"  鍔犺浇澶辫触锛�");
            return null;
        }
    }
    
    /**
     * 获取jar文件
     * @param jarPath jar路径
     * @param name jar
     * */
    public static String GetFile(String jarPath,String name)
    {
        InputStream stream = GetInputStream(jarPath,name);
        
        return stream == null ? FileCommon.inputStream2String(stream) : null;
    }
    
    /**
     * 获取jar中的一个文件
     * @param jarPath jar路径
     * @param name jar 
     * */
    public static InputStream GetInputStream(String jarPath,String name)
    {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(new File(jarPath));
        } catch (Exception e) {return null;}
        Enumeration<JarEntry> ienumen = jarFile.entries();
        while(ienumen.hasMoreElements())
        {
            JarEntry entity = (JarEntry)ienumen.nextElement();
            if(entity != null && entity.getName().substring(entity.getName().lastIndexOf("/")+1).equals(name))
            {
                try 
                {
                    return jarFile.getInputStream(entity);
                } catch (IOException e)
                {
                    return null;
                } 
            }
        }
        
        
        return null;
    }
    
}
 