package com.hongxiang.util.freemarker;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**                              
 * @description : 字符串截取的函数，两个参数，第一个待截取字符串，第二个限制长度，一旦超过这个长度就用……代替
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:36:52 +0800 
 */
public class SubStr implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if(args.size()!=2)
            throw new TemplateModelException("Wrong arguments!");
		
		int length=2*Integer.parseInt(args.get(1).toString(),10);
        String str =args.get(0).toString();
        
        String newStr = null;
        if(!StringUtils.isEmpty(str)){
        	if(str.length()>length){
        		newStr = str.substring(0,length)+"……";
        	}else{
        		newStr = str;
        	}
        }
		return newStr;
	}

}
 