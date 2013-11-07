/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: ParseCondition.java
 * @Package com.hongxiang.util.db.parsesql
 * @Description: 封装操作数据库的sql语句where条件部分
 * @Author: 秦京
 * @date 2013-08-01 10:59:20 +0800
 */
package com.hongxiang.util.db.parsesql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hongxiang.context.HXContext;
import com.hongxiang.definedexception.VariableException;
import com.hongxiang.systemoptioninfo.ParseSystemOptionInfo;
import com.hongxiang.util.db.paramater.EParameterType;
import com.hongxiang.util.db.paramater.SqlParameter;
import com.hongxiang.variable.VariableManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**                              
 * @description : 封装操作数据库的sql语句where条件部分
 * @Author: 秦京             
 * @Date: 2013-08-01 10:59:20 +0800 
 */
public final class ParseCondition {
    
    /**
     * 
     * @Title: parseJsonToCondition 
     * @Author: 秦京
     * @date 2013-08-01 11:12:12 +0800
     * @Description: 封装操作数据库的sql语句where条件部分
     * @return String
     */
    public static List parseJsonToCondition(JSONArray jsonArray){
//        StringBuilder whereStr = new StringBuilder();
        
        List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
        for(int j = 0;j<jsonArray.size();j++){
            
            //封装where条件的Map
            Map<String, Object> whereSql = new HashMap<String, Object>();
            JSONObject obj = JSONObject.fromObject(jsonArray.get(j));
            String logicalOperator = (String) ("".equals(obj.getString("LogicalOperator"))?"":obj.get("LogicalOperator"));
            String leftBracket = (String) ("".equals(obj.getString("LeftBracket"))?"":obj.get("LeftBracket"));
            String fieldType = (String) ("".equals(obj.getString("FieldType"))?"":obj.get("FieldType"));
            String fieldName = (String) ("".equals(obj.getString("FieldName"))?"":obj.get("FieldName"));
            if(!"".equals(fieldName))
                fieldName = fieldName.split("\\.")[1];
            
            String operator = (String) ("".equals(obj.getString("Operator"))?"":obj.get("Operator"));
            String value = (String) ("".equals(obj.getString("Value"))?"":obj.get("Value"));
            String rightBracket = (String) ("".equals(obj.getString("RightBracket"))?"":obj.get("RightBracket"));
            
            if("".equals(logicalOperator) && "".equals(leftBracket) && "".equals(fieldType) && "".equals(fieldName) && "".equals(operator)
                    && "".equals(value) && "".equals(rightBracket))
                return null;
            
            whereSql.put("logicalOperator", logicalOperator);
            whereSql.put("leftBracket", leftBracket);
            whereSql.put("fieldName", fieldName);
            whereSql.put("operator", operator);
            whereSql.put("value", value);
            whereSql.put("rightBracket", rightBracket);
            //根据fieldType类型将value转为对应的java类型
            if(!"".equals(fieldType)){
                ParameterType parameterType = ParseStringToParameter.getParameterType(fieldType);
                switch (parameterType) {
                case INT:
                    Integer intValue = Integer.parseInt(value);
                    whereSql.put("value", intValue);
                    break;
                case SHORT:
                    Short shortValue = Short.parseShort(value);
                    whereSql.put("value", shortValue);
                    break;
                case LONG:
                    Long longValue = Long.parseLong(value);
                    whereSql.put("value", longValue);
                    break;
                case BYTE:
                    Byte byteValue = Byte.parseByte(value);
                    whereSql.put("value", byteValue);
                    break;    
                case BIGDECIMAL:
                    BigDecimal bigDecimalValue = new BigDecimal(value);
                    whereSql.put("value", bigDecimalValue);
                    break;
                case DOUBLE:
                    Double doubleValue = Double.parseDouble(value);
                    whereSql.put("value", doubleValue);
                    break;
                case BOOLEAN:
                    Boolean booleanValue = Boolean.parseBoolean(value);
                    whereSql.put("value", booleanValue);
                    break;
                case FLOAT:
                    Float floatValue = Float.parseFloat(value);
                    whereSql.put("value", floatValue);
                    break;
                case STRING:
                    whereSql.put("value", value);
                    break;
                default:
                    break;
                }
            }
            
            params.add(whereSql);
        }
        
        return params;
    }
    
    /**
     * 
     * @Title: parseJsonToOrder 
     * @Author: 秦京
     * @date 2013-08-15 10:08:00 +0800
     * @Description: 封装Mybatis的order部分 
     * @param jsonArray
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List parseJsonToOrder(JSONArray jsonArray){
        List<Map<String, String>> lst = new ArrayList<Map<String,String>>();
        for (int j = 0; j < jsonArray.size(); j++) {
            JSONObject jsonObj = JSONObject.fromObject(jsonArray.get(j));
            Map map = new HashMap();
            String field = (String)jsonObj.get("Field");
            field = field.split("\\.")[1];
            String orderType = (String) jsonObj.get("Sort");
            map.put("field", field);
            map.put("orderType", orderType);
            lst.add(map);
        }
        return lst;
    }
    
    /**
     * 
     * @Title: parseJsonToParam 
     * @Author: 秦京
     * @date 2013-08-01 15:56:31 +0800
     * @Description: 封装操作数据库的sql语句where条件部分参数  
     * @param jsonArray
     * @return List
     */
    public static List<SqlParameter> parseJsonToParam(JSONArray jsonArray){
        List<SqlParameter> list = new ArrayList<SqlParameter>();
        for(int j = 0;j<jsonArray.size();j++){
            JSONObject obj = JSONObject.fromObject(jsonArray.get(j));
            String fieldType = (String) ("".equals(obj.get("FieldType"))?"":obj.get("FieldType"));
            String fieldName = (String) ("".equals(obj.get("FieldName"))?"":obj.get("FieldName"));
            String value = (String) ("".equals(obj.get("Value"))?"":obj.get("Value"));
            EParameterType type = null;
            if("string".equals(fieldType.toLowerCase())){
                type = EParameterType.STRING;
            }else if("number".equals(fieldType.toLowerCase())){
                type = EParameterType.NUMBER;
            }else if("boolean".equals(fieldType.toLowerCase())){
                type = EParameterType.BOOLEAN;
            }else{
                type = EParameterType.NORMAL;
            }
            SqlParameter sqlParameter = new SqlParameter("#"+fieldName, value, type);
            list.add(sqlParameter);
            
        }
        return list;
    }
    
    /**
     * 
     * @Title: parseJsonArrayToCondition 
     * @Description: 针对于纯后台规则的将where语句中condition部分的JSON转化为list，以便于mybatis可操作 
     * @param array
     * @return
     * @throws VariableException 
     */
    public static List<Map<String,Object>> parseJsonArrayToCondition(JSONArray array,HXContext hxContext) throws VariableException{
        List<Map<String, Object>> params = new ArrayList<Map<String,Object>>();
        for(int j = 0;j<array.size();j++){
          //封装where条件的Map
            Map<String, Object> whereSql = new HashMap<String, Object>();
            JSONObject obj = JSONObject.fromObject(array.get(j));
            String logicalOperator = (String) ("".equals(obj.getString("LogicalOperator"))?"":obj.get("LogicalOperator"));
            String leftBracket = (String) ("".equals(obj.getString("LeftBracket"))?"":obj.get("LeftBracket"));
            String fieldType = (String) ("".equals(obj.getString("FieldType"))?"":obj.get("FieldType"));
            String fieldName = (String) ("".equals(obj.getString("FieldName"))?"":obj.get("FieldName"));
            if(!"".equals(fieldName))
                fieldName = fieldName.split("\\.")[1];
            
            String operator = (String) ("".equals(obj.getString("Operator"))?"":obj.get("Operator"));
            String valueType = (String) ("".equals(obj.getString("ValueType"))?"":obj.get("ValueType"));
            String value = (String) ("".equals(obj.getString("Value"))?"":obj.get("Value"));
            String rightBracket = (String) ("".equals(obj.getString("RightBracket"))?"":obj.get("RightBracket"));
            
            Object realValue = getValue(valueType, value, hxContext);//取得字段名对应的实际值，传入的值形式可能为 web.ui.AddEntityRecord
            
            if("".equals(logicalOperator) && "".equals(leftBracket) && "".equals(fieldType) && "".equals(fieldName) && "".equals(operator)
                    && "".equals(value) && "".equals(rightBracket))
                return null;
            
            whereSql.put("logicalOperator", logicalOperator);
            whereSql.put("leftBracket", leftBracket);
            whereSql.put("fieldName", fieldName);
            whereSql.put("operator", operator);
            whereSql.put("value", realValue);
            whereSql.put("rightBracket", rightBracket);
            
            params.add(whereSql);
        }
        
        return params;
    }
    
    /**
     * 
     * @Title: getValue 
     * @Description: 根据sourceType和source从HXContext中获取值 
     * @param sourceType
     * @param source
     * @return
     * @throws VariableException 
     */
    @SuppressWarnings("unused")
    public static Object getValue(String sourceType,String source,HXContext hxContext) throws VariableException{
        Map<String, String> valueMap = VariableManager.getValueAndType(sourceType, source);
        String value = valueMap.get("value");
        if("".equals(value))
            throw new VariableException("后台变量来源类型异常");
        else
            return ParseSystemOptionInfo.getValueByID(value, hxContext);
        
    }
}
 