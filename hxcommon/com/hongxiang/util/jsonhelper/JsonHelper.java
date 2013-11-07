/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: JsonHelpers.java
 * @Package com.hongxiang.util
 * @Description: Json工具类
 * @Author: Hanlx
 * @date 2013-04-08 11:38:43 +0800
 */
package com.hongxiang.util.jsonhelper;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**                              
 * @description : Json工具类
 * @Author: Hanlx             
 * @Date: 2013-04-08 11:38:43 +0800 
 */
public class JsonHelper {
    /**
     * @Title: getJsonValue 
     * @Description: 递归获取最终jsonTag的JSONObject对象
     * @param jsonObject JSONObject对象
     * @param jsonTag Json字符串Key值
     * @return 最终jsonTag的JSONObject对象
     */
    public static JSONObject getJsonValue(JSONObject jsonObject, String jsonTag) {
        JSONObject result = null;
        if(jsonTag.contains(">")) {            
            String currentTag = jsonTag.substring(0, jsonTag.indexOf(">"));
            String newJsonTag = jsonTag.substring(jsonTag.indexOf(">") + 1);
            result = getJsonValue(jsonObject.getJSONObject(currentTag), newJsonTag);
        } else {
            result = jsonObject;
        }
        return result;
    }
    
    /**
     * @Title: getString 
     * @Description: 获取Json键对应的值为String的对象
     * @param jsonObject JSONObject对象
     * @param jsonTag Json字符串Key值，格式："键值1>键值2>键值3>...."
     * @return Json键对应的值为String的对象
     */
    public static String getString(JSONObject jsonObject, String jsonTag) {
        return getJsonValue(jsonObject, jsonTag).getString(jsonTag);
    }
    
    /**
     * @Title: getInt 
     * @Description: 获取Json键对应的值为int的对象
     * @param jsonObject JSONObject对象
     * @param jsonTag Json字符串Key值，格式："键值1>键值2>键值3>...."
     * @return Json键对应的值为int的对象
     */
    public static int getInt(JSONObject jsonObject, String jsonTag) {
        return getJsonValue(jsonObject, jsonTag).getInt(jsonTag);
    }
    
    /**
     * @Title: getJsonArray 
     * @Description: 获取Json键对应的值为JSONArray的对象
     * @param jsonObject JSONObject对象
     * @param jsonTag Json字符串Key值，格式："键值1>键值2>键值3>...."
     * @return Json键对应的值为JSONArray的对象
     */
    public static JSONArray getJsonArray(JSONObject jsonObject, String jsonTag) {
        return getJsonValue(jsonObject, jsonTag).getJSONArray(jsonTag);
    }
    
    /**
     * @Title: getJsonObject 
     * @Description: 获取Json键对应的值为JSONObject的对象
     * @param jsonObject JSONObject对象
     * @param jsonTag Json字符串Key值，格式："键值1>键值2>键值3>...."
     * @return Json键对应的值为JSONObject的对象
     */
    public static JSONObject getJsonObject(JSONObject jsonObject, String jsonTag) {
        return getJsonValue(jsonObject, jsonTag).getJSONObject(jsonTag);
    }
    
    
}
 