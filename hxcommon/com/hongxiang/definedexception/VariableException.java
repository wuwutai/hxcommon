/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: VariableException.java
 * @Package com.hongxiang.definedexception
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: 秦京
 * @date 2013-10-21 11:11:39 +0800
 */
package com.hongxiang.definedexception;

/**                              
 * @description : 自定义异常
 * @Author: 秦京             
 * @Date: 2013-10-21 11:11:39 +0800 
 */
public class VariableException extends Exception{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    public VariableException(String exception){
        super(exception);
    }
}
 