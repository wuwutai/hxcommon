package com.hongxiang.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

/**                              
 * @description : 树形操作工具
 * @Author: 刘馨远                 
 * @Date: 2013-04-01 15:55:13 +0800 
 */
public class TreeUtil {
    
    
    /**
    * @Fields keyName : 树形主键属性名
    */
    private String keyName;
    
    /**
    * @Fields childrenName : 树形子节点属性名
    */
    private String childrenName;
    
    /**
    * @Fields parentKeyName : 树形父亲主键属性名
    */
    private String parentKeyName;
    
    /**
    * @Fields classobj : class对象实体
    */
    private Class<?> classobj;
    
    
    /**
    * @Fields tree : 返回的树形菜单
    */
    private List<?> tree;
    
    
    
    /**
    * @Fields dataList : 数据列表
    */
    private List<?> dataList;
    
    /**
    * <p>Title: </p>
    * <p>Description: </p>
    */
    public TreeUtil(){
        
    }
    
    /**
    * <p>Title: 构造函数</p>
    * <p>Description: </p>
    * @param c
    * @param keyName
    * @param parentKeyName
    * @param childrenName
    */
    public TreeUtil(List<?> dataList, String keyName, String parentKeyName,String childrenName){
        init(dataList,keyName,parentKeyName,childrenName);
    }
    
    
    /** 
     * @Title: init 
     * @Description: 树形工具初始化函数
     * @param dataList
     * @param keyName
     * @param parentKeyName
     * @param childrenName
     */
    public void init(List<?> dataList, String keyName, String parentKeyName,String childrenName){
        if(null!=dataList && !dataList.isEmpty()){
            this.dataList = dataList;
            this.classobj = (dataList.get(0)).getClass();
            this.keyName = keyName;
            this.parentKeyName = parentKeyName;
            this.childrenName = childrenName;
        }
    }
    
    
    /** 
     * @Title: keyMethodName 
     * @Description:获取key的方法名称
     * @param methodName
     * @return
     */
    private String keyMethodName(String methodName){
        return methodName + this.getKeyName().substring(0,1).toUpperCase() + this.getKeyName().substring(1,this.getKeyName().length());
    }

    
    /** 
     * @Title: parentMethodName 
     * @Description: 获取parentKey的方法名称 
     * @param methodName
     * @return
     */
    private String parentMethodName(String methodName){
        return methodName + this.getParentKeyName().substring(0,1).toUpperCase() 
                + this.getParentKeyName().substring(1,this.getParentKeyName().length());
    }

    
    /** 
     * @Title: childMethodName 
     * @Description: 获取children的方法名称 
     * @param methodName
     * @return
     */
    private String childMethodName(String methodName){
        return methodName + this.getChildrenName().substring(0,1).toUpperCase()  
                + this.getChildrenName().substring(1,this.getChildrenName().length());
    }
    
    /** 
     * @Title: getNodeById 
     * @Description: 通过主键来查找树形节点
     * @param tree
     * @param key
     * @return
     * @throws Exception
     */
    public Object getNodeById(List<?> tree, String key) throws Exception
    {
        List<Object> list = new ArrayList<Object>();
        list.add(tree);
        String keyMethodName = keyMethodName("get");
        String childMethodName = childMethodName("get");
        while(list.size()>0){
            List<?> treeList = (List<?>)list.get(0);
            list.remove(0);
            for(int i=0;i<treeList.size();i++){
                Object tmptree = (Object)treeList.get(i);
                Method getkey = tmptree.getClass().getDeclaredMethod(keyMethodName);
                String ipsStr = (String) getkey.invoke(tmptree);
                //此处需要修改
                if(ipsStr.equals(key)){
                    return tmptree;
                }else{
                    Method getChildren = tmptree.getClass().getDeclaredMethod(childMethodName);
                    List<?>children = (List<?>)getChildren.invoke(tmptree);
                    if(children!=null){
                        list.add(children);
                    }
                }
            }
        }
        return null;
    }
    
    /** 
     * @Title: getRootNodes 
     * @Description: 在集合中挑选根节点集合
     * @param dataList
     * @return
     * @throws Exception
     */
    public List<Object> getRootNodes(List<?> dataList) throws Exception{
        List<Object> result = new ArrayList<Object>();
        String parentKeyMethodName = parentMethodName("get");
        
        for(int i=0,len = dataList.size();i<len;i++){
            Object obj = dataList.get(i);
            Method getarentKey = obj.getClass().getDeclaredMethod(parentKeyMethodName);
            String parentKeyValue = (String) getarentKey.invoke(obj);
            
            if(StringUtils.isEmpty(parentKeyValue)){
                result.add(obj);
            }else if(this.getNodeByKey(parentKeyValue)==null){
                result.add(obj);
            }
        }
        
        return result;
    }
    
    
    /** 
     * @Title: getNodeByKey 
     * @Description: 在数据集合中通过key值返回当前节点 
     * @param keyValue
     * @return
     * @throws Exception
     */
    public Object getNodeByKey(String keyValue) throws Exception{
        Object result = null;
        String keyMethodName = keyMethodName("get");
        for(int i=0,len = this.getDataList().size();i<len;i++){
            Object obj = this.getDataList().get(i);
            Method getkey = obj.getClass().getDeclaredMethod(keyMethodName);
            String realKeyValue = (String) getkey.invoke(obj);
            if(keyValue.equals(realKeyValue)){
                result = obj;
                break;
            }
        }
        return result;
    }
    
    
    
    /** 
     * @Title: getAllTree 
     * @Description: 生成树形结构的方法
     * @return
     */
    public List<?> getAllTree(){
        List<Object> result = new ArrayList<Object>();
        try {
            result = getRootNodes(this.getDataList());
            String childSetMethodName = childMethodName("set");
        
            for(int i=0,len = result.size();i<len;i++){
                Object itemRootNode = result.get(i);
                List<Object> children = transTree(itemRootNode,this.getDataList());
                Method setChildrenMethod = itemRootNode.getClass().getDeclaredMethod(childSetMethodName,java.util.List.class);
                setChildrenMethod.invoke(itemRootNode,children);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
   
    /** 
     * @Title: transTree 
     * @Description: 树形转换函数
     * @param vo
     * @param dataList
     * @return
     * @throws Exception
     */
    private List<Object> transTree(Object vo,List<?> dataList) throws Exception{
        List<Object> result = new ArrayList<Object>();
        if(!dataList.isEmpty()){
            //查询子节点
            for(int i=0,len = dataList.size();i < len ; i++){
                Object obj = dataList.get(i);
                Method getKeyMethod = obj.getClass().getDeclaredMethod(this.keyMethodName("get"));
                Method getParentKeyMethod = obj.getClass().getDeclaredMethod(this.parentMethodName("get"));
                String tmpKeyValue = (String)getKeyMethod.invoke(vo);
                String tmpParentKeyValue = (String)getParentKeyMethod.invoke(obj);
                if(tmpParentKeyValue.equals(tmpKeyValue)){
                    result.add(obj);
                } 
            }
            
            for(int i=0,len = result.size();i < len ; i++){
                Object domain = result.get(i);
                List<Object> children = transTree(domain,dataList);
                Method setChildrenMethod = domain.getClass().getDeclaredMethod(this.childMethodName("set"),java.util.List.class);
                setChildrenMethod.invoke(domain,children);
            }
        }
        return result;
    }
    
    
    
    
    public static void main(String[] args){
        TreeVo tree1 = new TreeVo();
        tree1.setId("1");
        tree1.setPid("0");
        tree1.setName("1");
        
        TreeVo tree1_1 = new TreeVo();
        tree1_1.setId("1_1");
        tree1_1.setPid("1");
        tree1_1.setName("1_1");
        
        TreeVo tree1_1_1 = new TreeVo();
        tree1_1_1.setId("1_1_1");
        tree1_1_1.setPid("1_1");
        tree1_1_1.setName("1_1_1");
        
        TreeVo tree2 = new TreeVo();
        tree2.setId("2");
        tree2.setPid("0");
        tree2.setName("2");
        
        TreeVo tree2_1 = new TreeVo();
        tree2_1.setId("2_1");
        tree2_1.setPid("2");
        tree2_1.setName("2_1");
        
        TreeVo tree2_1_1 = new TreeVo();
        tree2_1_1.setId("2_1_1");
        tree2_1_1.setPid("2_1");
        tree2_1_1.setName("2_1_1");
        
        
        
        List<TreeVo> list = new ArrayList<TreeVo>();
        list.add(tree1);
        list.add(tree2);
        list.add(tree2_1);
        list.add(tree1_1);
        list.add(tree2_1_1);
        list.add(tree1_1_1);
        
        List<TreeVo> list2 = new ArrayList<TreeVo>();
        list2.add(tree1);
        list2.add(tree2);
        
        
        TreeUtil util = new TreeUtil(list,"id","pid","children");
        List<TreeVo> tree= (List<TreeVo>)util.getAllTree();
//        List<TreeVo> tree= (List<TreeVo>)util.getAllTree2(list2,list);
//        util.transTree(list);
        System.out.println(22);
    }
    

    /**
     * @return the keyName
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * @param keyName the keyName to set
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * @return the childrenName
     */
    public String getChildrenName() {
        return childrenName;
    }

    /**
     * @param childrenName the childrenName to set
     */
    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    /**
     * @return the parentKeyName
     */
    public String getParentKeyName() {
        return parentKeyName;
    }

    /**
     * @param parentKeyName the parentKeyName to set
     */
    public void setParentKeyName(String parentKeyName) {
        this.parentKeyName = parentKeyName;
    }

    /**
     * @return the classobj
     */
    public Class<?> getClassobj() {
        return classobj;
    }

    /**
     * @param classobj the classobj to set
     */
    public void setClassobj(Class<?> classobj) {
        this.classobj = classobj;
    }

    /**
     * @return the tree
     */
    public List<?> getTree() {
        return tree;
    }

    /**
     * @param tree the tree to set
     */
    public void setTree(List<?> tree) {
        this.tree = tree;
    }

    /**
     * @return the dataList
     */
    public List<?> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }
    
}
 