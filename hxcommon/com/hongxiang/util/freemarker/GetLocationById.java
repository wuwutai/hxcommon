package com.hongxiang.util.freemarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @ClassName: GetLocationById
 * @Description: 获取城市列表的freemarker函数
 * @author: 高龙
 * @company: 上海苏秦网络技术有限公司
 * @date 2012-04-19 11:16:34 +0800
 */
public class GetLocationById implements TemplateMethodModel {

    @Override
    public Object exec(List args) throws TemplateModelException {
        String basicJsonListJson = args.get(0).toString();
        String provinceCode = args.get(1).toString();
        String cityCode = args.get(2).toString();
        String areaCode = args.get(3).toString();
        String provinceId = provinceCode + "0000";
        String cityId = provinceCode + cityCode + "00";
        String areaId = provinceCode + cityCode + areaCode;

        List<Map<String, Object>> list = JSON.parseObject(basicJsonListJson,
            List.class);

        StringBuffer stringbuffer = new StringBuffer();
        for (Map<String, Object> item : list) {
            if (provinceId.equals(item.get("id"))) {
                stringbuffer.append((String) item.get("name")).append(" ");
            }
            if (cityId.equals(item.get("id"))) {
                stringbuffer.append((String) item.get("name")).append(" ");
            }
            if (areaId.equals(item.get("id"))) {
                stringbuffer.append((String) item.get("name"));
            }
        }
        return stringbuffer;
    }

}
 