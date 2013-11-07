package com.hongxiang.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**                              
 * @description : JDom操作xml的工具类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:19:10 +0800 
 */
public class JDomUtil {
    
    /**
     * @Title: ParseCSL
     * @Description: 解析.sbl文件获取数据库类型
     * @param filePath  文件路径
     * @return String    返回类型
     * @author: 刘馨远
     * @date 2013-03-19 16:42:24 +0800
     * @throws
     * @修改记录 <日期 时间 记录变更人> 
     * @修改描述 <修改原因，修改内容>
     */
//     public static  JdbcBean ParseCSL(){
//         
//         JdbcBean bean = new JdbcBean();
//         SAXBuilder builder = new SAXBuilder();
//         Document document;
//          String dataTypeFilePath = WebUtil.getClassPath()+"hongxiang/businessrule/loaddata/jdbc.sbl";
//          try {
//             document = builder.build(dataTypeFilePath);
//             Element root = document.getRootElement();//获得根节点
//             
//             List list = root.getChildren();//获取根节点下所有子节点
//             Element produce = null;
//             for(int i = 0;i < list.size();i++){
//                 produce = (Element)list.get(i);//获取指定元素节点信息 
//              String  className = produce.getChildText("className");
//              String url = produce.getChildText("url");
//              String name = produce.getChildText("name");
//              String password = produce.getChildText("password");
//              bean.setClassName(className);
//              bean.setPassword(password);
//              bean.setUrl(url);
//              bean.setName(name);
//             }
//         } catch (JDOMException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }        
//          return bean;
//     }
     
     /** 
      * @Title: ParseSBL
      * @Description: 解析数据库类型.sbl文件
      * @return
      */
     public static String  ParseSBL(){
         String datatType = "";
         SAXBuilder builder = new SAXBuilder();
         Document document;
          
         String Path = WebUtil.getClassPath();//classes目录 路径
         String dataTypeFilePath = Path+"dbtype.sbl";
         try {
             document = builder.build(dataTypeFilePath);
             Element root = document.getRootElement();//获得根节点
             
             List list = root.getChildren();//获取根节点下所有子节点
             Element produce = null;
             for(int i = 0;i < list.size();i++){
                 produce = (Element)list.get(i);//获取指定元素节点信息 
                datatType = produce.getChildText("DBType");
             }
         } catch (JDOMException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }        
         return datatType;
     }
     
	/** 
	 * @Title: buildFromFile 
	 * @Description: 根据指定路径的XML文件建立JDom对象
	 * @param filePath
	 * @return
	 */
	public static Document buildFromFile(String filePath) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new File(filePath));
			return anotherDocument;
		} catch(JDOMException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 
	 * @Title: buildFromXMLString 
	 * @Description: 根据XML 字符串 建立JDom对象
	 * @param xmlString
	 * @return
	 */
	public static Document buildFromXMLString(String xmlString) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document anotherDocument = builder.build(new StringReader(xmlString));
			return anotherDocument;
		} catch(JDOMException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	 * @Title: buildFromDom 
	 * @Description: 根据Dom对象建立JDom对象
	 * @param Dom
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Document buildFromDom(org.w3c.dom.Document Dom) throws JDOMException, IOException {
		org.jdom.input.DOMBuilder builder = new org.jdom.input.DOMBuilder();
		Document jdomDoc = builder.build(Dom);
		return jdomDoc;
	}

	/** 
	 * @Title: outputToStdout 
	 * @Description: 这个方法使用XMLOutputer将一个JDom对象输出到标准输出设备，使用 utf-8 编码
	 * @param myDocument
	 */
	public static void outputToStdout(Document myDocument) {
		outputToStdout(myDocument,"utf-8");
	}
	
	/** 
	 * @Title: outputToStdout 
	 * @Description: 这个方法使用XMLOutputer将一个JDom对象输出到标准输出设备
	 * @param myDocument
	 * @param encoding
	 */
	public static void outputToStdout(Document myDocument,String encoding) {
		try {
			new XMLOutputter();
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding(encoding));
			outputter.output(myDocument, System.out);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @Title: outputToString 
	 * @Description: 这个方法将JDom对象转换字符串. 
	 * @param document
	 * @return
	 */
	public static String outputToString(Document document){
		return outputToString(document,"utf-8"); 
	}
	
	/** 
	 * @Title: outputToString 
	 * @Description: 这个方法将JDom对象转换字符串.
	 * @param document
	 * @param encoding
	 * @return
	 */
	public static String outputToString(Document document,String encoding){
		ByteArrayOutputStream byteRep = new ByteArrayOutputStream();
		XMLOutputter docWriter = new XMLOutputter(Format.getPrettyFormat().setEncoding(encoding));
		try{
			docWriter.output(document, byteRep);
		}catch(Exception e){
	
		}
		return byteRep.toString();
	}
	
	public static org.w3c.dom.Document outputToDom(org.jdom.Document jdomDoc) throws JDOMException {
		org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
		return outputter.output(jdomDoc);
	}
	
	/** 
	 * @Title: outputToFile 
	 * @Description: 这个方法使用XMLOutputter将JDom对象输出到文件
	 * @param myDocument
	 * @param filePath
	 */
	public static void outputToFile(Document myDocument,String filePath) {
		outputToFile(myDocument,filePath,"utf-8");
	}
	
	/** 
	 * @Title: outputToFile 
	 * @Description:这个方法使用XMLOutputter将JDom对象输出到文件
	 * @param myDocument
	 * @param filePath
	 * @param encoding
	 */
	public static void outputToFile(Document myDocument,String filePath,String encoding) {
		try {
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat().setEncoding(encoding));
		
			//output to a file
			FileWriter writer = new FileWriter(filePath);
			outputter.output(myDocument, writer);
			writer.close();
		} catch(java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @Title: executeXSL 
	 * @Description: 这个方法将JDom对象通过样式单转换.
	 * @param myDocument
	 * @param xslFilePath
	 * @param xmlResult
	 */
	public static void executeXSL(Document myDocument,String xslFilePath,StreamResult xmlResult) {
		try {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		// Make the input sources for the XML and XSLT documents
		org.jdom.output.DOMOutputter outputter = new org.jdom.output.DOMOutputter();
		org.w3c.dom.Document domDocument = outputter.output(myDocument);
		javax.xml.transform.Source xmlSource = new javax.xml.transform.dom.DOMSource(domDocument);
		StreamSource xsltSource = new StreamSource(new FileInputStream(xslFilePath));
		//Get a XSLT transformer
		Transformer transformer = tFactory.newTransformer(xsltSource);
		//do the transform
		transformer.transform(xmlSource, xmlResult);
		
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(TransformerConfigurationException e) {
			e.printStackTrace();
		} catch(TransformerException e) {
			e.printStackTrace();
		} catch(org.jdom.JDOMException e) {
			e.printStackTrace();
		}
	}
}
 