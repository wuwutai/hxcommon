package com.hongxiang.util.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**                              
 * @description : freemarker 自定义方法用来加载css是否为发布版本
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:35:55 +0800 
 */
public class GetCss implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		
		String resultCssPath = "";
		
		if(args.size()!=2)
            throw new TemplateModelException("Wrong arguments!");
		
		//获取原有路径
		String oPath = args.get(0).toString();
		boolean isRelease = Boolean.parseBoolean(args.get(1).toString());
		
		if(isRelease){
			resultCssPath = oPath.substring(0,oPath.length() - 4) + ".min.css";
		}else{
			resultCssPath = oPath;
		}
		
		return resultCssPath;
	}

}
 