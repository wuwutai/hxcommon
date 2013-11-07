package com.hongxiang.util.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**                              
 * @description : 获取基础类型列表的freemarker函数
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:35:45 +0800 
 */
public class GetBasicListByTypeId implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		String basicJsonListJson = args.get(0).toString();
		String typeId = args.get(1).toString();
		
		List<Map<String,Object>> list = JSON.parseObject(basicJsonListJson, List.class);
		
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		
		for(Map<String,Object> item : list){
			if(typeId.equals(item.get("type"))){
				Map<String,String> map = new HashMap<String,String>();
				map.put("code", (String)item.get("code"));
				map.put("name", (String)item.get("name"));
				result.add(map);
			}
		}
		return result;
	}

}
 