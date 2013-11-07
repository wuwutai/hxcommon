package com.hongxiang.util.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**                              
 * @description : freemarker 自定义方法用来加载是否为发布版本
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:36:37 +0800 
 */
public class GetScript implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		
		String resultScriptPath = "";
		
		if(args.size()!=2)
            throw new TemplateModelException("Wrong arguments!");
		
		//获取原有路径
		String oPath = args.get(0).toString();
		boolean isRelease = Boolean.parseBoolean(args.get(1).toString());
		
		if(isRelease){
			resultScriptPath = oPath.substring(0,oPath.length() - 3) + ".min.js";
		}else{
			resultScriptPath = oPath;
		}
		
		return resultScriptPath;
	}

}
 