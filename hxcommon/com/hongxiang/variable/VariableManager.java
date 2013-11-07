/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: VariableManager.java
 * @Package com.hongxiang.variable
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: 秦京
 * @date 2013-10-23 18:50:51 +0800
 */
package com.hongxiang.variable;

import java.util.HashMap;
import java.util.Map;

import com.hongxiang.definedexception.VariableException;

/**                              
 * @description : 处理后台类型，返回类型和对应的值
 * @Author: 秦京                 
 * @Date: 2013-10-23 18:50:51 +0800 
 */
public class VariableManager {
    
    /**
     * 
     * @Title: getValueAndType 
     * @Description: 处理后台类型，返回类型和对应的值 
     * @param sourceType
     * @param value
     * @return
     * @throws VariableException 
     */
    public static Map<String, String> getValueAndType(String sourceType,String source) throws VariableException{
        
        Map<String, String> map = new HashMap<String, String>();
        Variable ee = JudgeVariable.getVariable(sourceType);
        String realValue = "";//从传入的未处理的value中获取实际值，传入的vlaue可能为web.ui.AddEntityRecord取得的值就为AddEntityRecord
        
        switch (ee) {
        case PAGEVARIANT:
            realValue = source.split("\\.")[source.split("\\.").length-1];
            break;
        case CUSTOM:
            realValue = source;        
            break;
        case LOCALVARIANT:
            realValue = source.split("\\.")[source.split("\\.").length-1];
            break;
        case SYSTEMVARIABLE:
            realValue = source.split("\\.")[source.split("\\.").length-1];
            break;
        case FUNCTION:
            realValue = source.split("\\.")[source.split("\\.").length-1];
            break;
        default:
            break;
        }
        
        map.put("type", sourceType);
        map.put("value", realValue);
        return map;
    }
}
 