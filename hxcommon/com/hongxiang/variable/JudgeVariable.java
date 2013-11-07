/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: JudgeVariable.java
 * @Package com.hongxiang.variable
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: 秦京
 * @date 2013-10-21 11:06:53 +0800
 */
package com.hongxiang.variable;

import com.hongxiang.definedexception.VariableException;

/**                              
 * @description : 判断后台变量类型来源
 * @Author: 秦京             
 * @Date: 2013-10-21 11:06:53 +0800 
 */
public class JudgeVariable {
    public static Variable getVariable(String str) throws VariableException{
        Variable en = null;
        if("".equals(str))
            throw new VariableException("类型"+str+"来源为空");
        
        if("pagevariant".equals(str.toLowerCase())){
            en = Variable.PAGEVARIANT;
        }else if ("localvariant".equals(str.toLowerCase())) {
            en = Variable.LOCALVARIANT;
        }else if ("systemvariable".equals(str.toLowerCase())) {
            en = Variable.SYSTEMVARIABLE;
        }else if ("function".equals(str.toLowerCase())) {
            en = Variable.FUNCTION;
        }else if ("custom".equals(str.toLowerCase())) {
            en = Variable.CUSTOM;
        }else{
            return null;
        }
        return en;
    }
    
}
 