/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: SqlCommand.java
 * @Package com.hongxiang.db.paramater
 * @Description: 组装Sql语句
 * @Author: Hanlx
 * @date 2013-04-11 10:00:58 +0800
 */
package com.hongxiang.util.db.paramater;

import java.util.List;

/**
 * @description : 组装Sql语句
 * @Author: Hanlx
 * @Date: 2013-04-11 10:00:58 +0800
 */
public class SqlCommand {
    /**
     * @Title: MakeUpSqlCommand
     * @Author: Hanlx
     * @date 2013-04-11 10:02:38 +0800
     * @Description: 组装Sql语句
     * @param str
     *            带参数的Sql字符串
     * @param param
     *            Sql参数数组对象
     * @return 完整Sql指令
     */
    public static String MakeUpSqlCommand(String str, List<SqlParameter> params) {
        if (null != params && params.size() > 0) {
            for (SqlParameter param : params) {
                String mark = param.getMark();
                String value = param.getValue();
                System.out.println(value);
                EParameterType type = param.getType();

                if (EParameterType.BOOLEAN == type
                    || EParameterType.NUMBER == type) {
                    boolean flag = checkValue(value, type);
                    if (flag) {
                        str = str.replace(mark, value);
                    } else {
                        return null;
                    }
                } else if (EParameterType.STRING == type) {
                    str =  str.replace(mark, "'" +value.replace("'", "''")+ "'") ;
                } else {
                    str = str.replace(mark, value);
                }
            }
        }
        return str;
    }

    /**
     * @Title: checkValue
     * @Author: Hanlx
     * @date 2013-04-11 11:21:18 +0800
     * @Description: 检查变量值是否合法
     * @param value
     *            变量值
     * @param type
     *            变量类型
     * @return
     */
    private static boolean checkValue(String value, EParameterType type) {
        boolean flag = false;
        switch (type) {
        case NUMBER:
            flag = checkIntType(value);
            break;
        case BOOLEAN:
            flag = checkBoolType(value);
            break;
        }
        return flag;
    }

    /**
     * @Title: checkIntType
     * @Author: Hanlx
     * @date 2013-04-11 10:31:37 +0800
     * @Description: 判断是否为数值类型
     * @param value
     *            变量值
     * @return true/false
     */
    private static boolean checkIntType(String value) {
        boolean flag = true;
        String first = value.substring(0, 1);
        if ("-".equals(first)) {
            value = value.substring(1, value.length() - 1);
        }
        if (value.contains(".")) {
            value = value.replace(".", "");
        }

        int length = value.length();
        for (int i = length; --i >= 0;) {
            if (!Character.isDigit(value.charAt(i))) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * @Title: checkBoolType
     * @Author: Hanlx
     * @date 2013-04-11 11:22:07 +0800
     * @Description: 检查变量是否是boolean类型
     * @param value
     *            变量值
     * @return
     */
    private static boolean checkBoolType(String value) {
        boolean flag = false;
        value = value.toUpperCase();
        if ("0".equals(value) || "1".equals(value) || "TRUE".equals(value)
            || "FALSE".equals(value)) {
            flag = true;
        }
        return flag;
    }
}
 