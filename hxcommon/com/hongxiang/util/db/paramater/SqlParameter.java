/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: SqlParameters.java
 * @Package com.bean
 * @Description: 注入时封装的sql参数的Bean
 * @Author: 赵聪
 * @date 2013-04-11 09:50:51 +0800
 */
package com.hongxiang.util.db.paramater;

/**                              
 * @description :注入时封装的sql参数的Bean
 * @Author: 赵聪               
 * @Date: 2013-04-11 09:50:51 +0800 
 */
public class SqlParameter {
    private String mark;
    private String value;
    private EParameterType type;
    
    /** 
     * @Title: 
     * @Description: 默认构造函数
     * @param mark
     * @param value
     * @param type 
     */
    public SqlParameter(String mark, String value, EParameterType type) {
        super();
        this.mark = mark;
        this.value = value;
        this.type = type;
    }
    
    /**
     * @return mark
     */
    public String getMark() {
        return mark;
    }
    /**
     * @param mark to set mark 
     */
    public void setMark(String mark) {
        this.mark = mark;
    }
    /**
     * @return value
     */
    public String getValue() {
        return value;
    }
    /**
     * @param value to set value 
     */
    public void setValue(String value) {
        this.value = value;
    }
    /**
     * @return type
     */
    public EParameterType getType() {
        return type;
    }
    /**
     * @param type to set type 
     */
    public void setType(EParameterType type) {
        this.type = type;
    }
}
 