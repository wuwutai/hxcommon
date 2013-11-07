/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: SystemVariantBean.java
 * @Package com.hongxiang.util.systemoption
 * @Description: 系统变量的bean
 * @Author: 赵聪聪
 * @date 2013-05-10 12:17:01 +0800
 */
package com.hongxiang.util.systemoption;

import java.io.Serializable;

/**                              
 * @description : 系统变量的bean
 * @Author: 赵聪聪                 
 * @Date: 2013-05-10 12:17:01 +0800 
 */
public class SystemVariantBean implements  Serializable{
    private String name;
    private String chineseName;
    private String initValue;
    private String valueType;
    private String remark;
    private String variantType;
    
    /** 
     * @Title: 
     * @Description: 默认构造函数 
     */
    public SystemVariantBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    /** 
     * @Title: 
     * @Description: 默认构造函数
     * @param name
     * @param chineseName
     * @param initValue
     * @param valueType
     * @param remark
     * @param variantType 
     */
    public SystemVariantBean(String name, String chineseName, String initValue,
        String valueType, String remark, String variantType) {
        super();
        this.name = name;
        this.chineseName = chineseName;
        this.initValue = initValue;
        this.valueType = valueType;
        this.remark = remark;
        this.variantType = variantType;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name to set name 
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return chineseName
     */
    public String getChineseName() {
        return chineseName;
    }
    /**
     * @param chineseName to set chineseName 
     */
    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
    /**
     * @return initValue
     */
    public String getInitValue() {
        return initValue;
    }
    /**
     * @param initValue to set initValue 
     */
    public void setInitValue(String initValue) {
        this.initValue = initValue;
    }
    /**
     * @return valueType
     */
    public String getValueType() {
        return valueType;
    }
    /**
     * @param valueType to set valueType 
     */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * @param remark to set remark 
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @return variantType
     */
    public String getVariantType() {
        return variantType;
    }
    /**
     * @param variantType to set variantType 
     */
    public void setVariantType(String variantType) {
        this.variantType = variantType;
    }
}
 