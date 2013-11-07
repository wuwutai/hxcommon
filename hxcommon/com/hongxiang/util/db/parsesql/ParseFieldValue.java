/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: GetFieldValueByType.java
 * @Package com.hongxiang.util.db.parsesql
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: 秦京
 * @date 2013-08-19 16:28:51 +0800
 */
package com.hongxiang.util.db.parsesql;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hongxiang.tableinfo.Column;
import com.hongxiang.tableinfo.TableInfo;

/**                              
 * @description : 根据类型得到对应的字段值
 * @Author: 秦京             
 * @Date: 2013-08-19 16:28:51 +0800 
 */
public final class ParseFieldValue {
    
    public static Object getFieldValueByType(TableInfo tableInfo, String fieldName, String fieldValue){
        List<Column> columns = tableInfo.getColumns();
        Object obj = null;
        
        for(int j = 0 ; j < columns.size() ; j++){
            Column column = columns.get(j);
            if(column.getName().equals(fieldName)){
                String fieldType = column.getDataType();
                if(!"".equals(fieldType)){
                    ParameterType parameterType = ParseStringToParameter.getParameterType(fieldType);
                    switch (parameterType) {
                    case INT:
                        Integer intValue = Integer.parseInt(fieldValue);
                        obj = intValue;
                        break;
                    case SHORT:
                        Short shortValue = Short.parseShort(fieldValue);
                        obj = shortValue;
                        break;
                    case LONG:
                        Long longValue = Long.parseLong(fieldValue);
                        obj = longValue;
                        break;
                    case BYTE:
                        Byte byteValue = Byte.parseByte(fieldValue);
                        obj = byteValue;
                        break;    
                    case BIGDECIMAL:
                        BigDecimal bigDecimalValue = new BigDecimal(fieldValue);
                        obj = bigDecimalValue;
                        break;
                    case DOUBLE:
                        Double doubleValue = Double.parseDouble(fieldValue);
                        obj = doubleValue;
                        break;
                    case BOOLEAN:
                        Boolean booleanValue = Boolean.parseBoolean(fieldValue);
                        obj = booleanValue;
                        break;
                    case FLOAT:
                        Float floatValue = Float.parseFloat(fieldValue);
                        obj = floatValue;
                        break;
                    case STREAM:
                        obj = fieldValue;
                        break;
                    case STRING:
                        obj = fieldValue;
                    default:
                        break;
                    }
                }
            }
            
        }
        
        return obj;
    }
}
 