package com.hongxiang.systemoptioninfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.hongxiang.context.HXContext;
import com.hongxiang.util.WebUtil;

public class ParseSystemOptionInfo {

    @SuppressWarnings("rawtypes")
    public static String getTypeByID(String key, HXContext context) {
        String type = "";
        try {
            ImportSystemOptionToSession(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = (HashMap)context.getActionContext().getSession().get("SystemOptionInfo");
        if(map.containsKey(key)) {
            type = ((SystemOptionInfoBean)map.get(key)).getType();
        }
        
        return type;
    }
    
    @SuppressWarnings("rawtypes")
    public static Object getValueByID(String key, HXContext context) {
        Object obj = null;
        try {
            ImportSystemOptionToSession(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = (HashMap)context.getActionContext().getSession().get("SystemOptionInfo");
        if(map.containsKey(key)) {
            obj = ((SystemOptionInfoBean)map.get(key)).getValue();
        }
        
        return obj;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static boolean setValueByID(String key, HXContext context, Object value) {
        boolean bool = false;
        try {
            ImportSystemOptionToSession(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map map = (HashMap)context.getActionContext().getSession().get("SystemOptionInfo");
        if(map.containsKey(key)) {
            SystemOptionInfoBean info = (SystemOptionInfoBean)map.get(key);
            info.setValue(value);
            map.remove(key);
            map.put(key, info);
            bool = true;
        }
        return bool;
    }
    
    @SuppressWarnings("unchecked")
    static void ImportSystemOptionToSession(HXContext context) throws JDOMException, IOException {
        HttpServletRequest req = (HttpServletRequest) context.getActionContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = req.getSession(true);
        if(null == session.getAttribute("SystemOptionInfo")) {
            String path = WebUtil.getWebRootAbsolutePath();
            path = path.substring(0, path.lastIndexOf("\\")) + "\\WEB-INF\\classes\\SystemOptionInf\\ServiceVariable.xml";
            
            SAXBuilder builder = new SAXBuilder();
            if(new File(path).exists()) {
                InputStream file = new FileInputStream(path);
                //锟斤拷锟斤拷牡锟斤拷锟斤拷锟�
                Document document = builder.build(file);
                //锟斤拷酶锟节碉拷
                Element root = document.getRootElement();
                List<Element> elList = root.getChildren();
                Map map = new HashMap();
                for(Element e:elList) {
                    String id = e.getChildText("id");
                    String type = e.getChildText("type");
                    String value = e.getChildText("value");
                    SystemOptionInfoBean bean = new SystemOptionInfoBean(id, type, value);
                    map.put(id, bean);
                }
                session.setAttribute("SystemOptionInfo", map);
            }            
        }
    }
    
}
 