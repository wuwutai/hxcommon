/** 
 * @CopyRright 北京鸿翔远成技术有限公司
 * @File: SystemOption.java
 * @Package com.hongxiang.util.systemoption
 * @Description: 读取并初始化系统变量
 * @Author: Hanlx
 * @date 2013-05-10 10:04:06 +0800
 */
package com.hongxiang.util.systemoption;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.hongxiang.util.WebUtil;

/**                              
 * @description : 读取并初始化系统变量
 * @Author: Hanlx             
 * @Date: 2013-05-10 10:04:06 +0800 
 */
public class SystemOption {
    private static Map<String, SystemVariantBean> beansMap; 
    /** 
     * @Title: initSystemOption 
     * @Description: 初始化信息
     */
    public static void initSystemOption(HttpSession session) {
        String path = WebUtil.getClassPath() + "systemoption\\SystemVanriant.vsvf";
        File file = new File(path);        
        if(file.exists()) {
            //TODO: 读取xml文件
            
            beansMap = readXml(file);
            session.setAttribute("SystemVariant", beansMap);
        }
    }
    
    /** 
     * @Title: readXml 
     * @Description: 读取文件信息
     * @param xmlPath
     */
    public static Map<String, SystemVariantBean> readXml(File file) {
        SAXBuilder sax = new SAXBuilder();
        beansMap = new HashMap<String, SystemVariantBean>();
        Document doc;
        try {
            doc = sax.build(file);
            Element rootEle = doc.getRootElement();
            
            List nodesList = rootEle.getChildren();//获取根节点下所有子节点
            SystemVariantBean bean = null;
            for(Iterator it = nodesList.iterator(); it.hasNext();) {
                
                Element eNode = (Element)it.next();
                
                String name = eNode.getChildText("Name");
                String chineseName = eNode.getChildText("ChineseName");
                String initValue = eNode.getChildText("InitValue");
                String valueType = eNode.getChildText("ValueType");
                String remark = eNode.getChildText("Remark");
                String variantType = eNode.getChildText("VariantType");
                bean = new SystemVariantBean(name, chineseName, initValue, valueType, remark, variantType); 
                beansMap.put(name, bean);
            }
          return beansMap;
          
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 
     * @Title: getSystemVariantInfo 
     * @Description: 得到系统变量信息Json
     * @param key
     * @return
     */
    public static  String getSystemVariantInfo(String key){
        String jsonData = "";
        SystemVariantBean bean = beansMap.get(key);
        if(bean == null){
            return "";
        }
        jsonData = "'Name':'{"+bean.getName()+"}','Value':'{"+bean.getInitValue()+"}','ValueType':{"+bean.getValueType()+"}";
        return jsonData;
    }
}
 