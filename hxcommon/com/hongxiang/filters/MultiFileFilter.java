/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: MultiFileFilter.java
 * @Package com.hongxiang.filters
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: 秦京
 * @date 2013-10-21 19:06:06 +0800
 */
package com.hongxiang.filters;

import java.io.File;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.RequestUtils;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**                              
 * @description : 自定义过滤器，过滤Views目录下是否有对应jsp页面
 * @Author: 秦京             
 * @Date: 2013-10-21 19:06:06 +0800 
 */
public class MultiFileFilter extends StrutsPrepareAndExecuteFilter {

    /** 
     * @see org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
        FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String resourcePath = RequestUtils.getServletPath(request);
        
        String rootString = request.getSession().getServletContext().getRealPath
                (resourcePath);
        int index = rootString.lastIndexOf("\\");
        
        rootString = rootString.substring(0, index);
        rootString += "\\Views";
        
        String str = hasFile(rootString,resourcePath.substring(1, resourcePath.length()));
        if("".equals(str))//如果Views目录下不包含有对应的jsp页面
            super.doFilter(req, res, chain);
        else{
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Content-Type", "text/html; charset=utf-8");
            request.getRequestDispatcher(str).forward(request, response);
        }
        
    }
    
    /**
     * 
     * @Title: hasFile 
     * @Author: 秦京
     * @date 2013-10-22 10:20:11 +0800
     * @Description: 检查是否包含jsp文件 
     * @param rootString
     * @param resourcePath
     * @return
     */
    private String hasFile(String rootString,String resourcePath){
        File directory = new File(rootString);
        String string = "";
        if(!"".equals(isDirectory(directory,resourcePath))){
            String retString = isDirectory(directory, resourcePath);
            int index = retString.indexOf("Views");
            string = retString.substring(index, retString.length());
            return string;        
        }
        return "";    
    }
    
    /**
     * 
     * @Title: isDirectory 
     * @Author: 秦京
     * @date 2013-10-22 10:19:46 +0800
     * @Description: 循环遍历检索目录 
     * @param file
     * @param resourcePath
     * @return
     */
    private String isDirectory(File file,String resourcePath){
        String returnStr = "";
        if(file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                String ss = isDirectory(listFiles[i],resourcePath);
                if(!"".equals(ss))
                    return ss;
            }
        }else{
            if(file.getName().equals(resourcePath+".jsp"))
                returnStr+= file.getAbsolutePath();
        }
        return returnStr;
    }
    
    
}
 