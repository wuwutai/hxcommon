/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: GetImgPath.java
 * @Package com.hongxiang.util.freemarker
 * @Description:  获取图片路径
 * @Author: 刘馨远
 * @date 2013-04-15 12:56:00 +0800
 */
package com.hongxiang.util.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**                              
 * @description : 获取图片路径
 * @Author: 刘馨远                 
 * @Date: 2013-04-15 12:56:00 +0800 
 */
public class GetImgPath implements TemplateMethodModel{

    /** 
     * @see freemarker.template.TemplateMethodModel#exec(java.util.List) 
     */
    @Override
    public Object exec(List args) throws TemplateModelException {
        
        String resultImgPath = "";
        
        if(args.size()!=2)
            throw new TemplateModelException("Wrong arguments!");
        
        //获取原有路径
        String oPath = args.get(0).toString();
        boolean isRelease = Boolean.parseBoolean(args.get(1).toString());
        
        if(isRelease){
//            resultImgPath = oPath.substring(0,oPath.length() - 4) + ".min.css";
            resultImgPath = oPath;
        }else{
            resultImgPath = oPath;
        }
        
        return resultImgPath;
        
    }

}
 