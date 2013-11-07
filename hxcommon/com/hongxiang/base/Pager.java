package com.hongxiang.base;

import java.util.List;

/**                              
 * @description :  分页实体类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 11:03:13 +0800 
 */
public class Pager {
    
    /**
    * @Fields currentPage : 当前页面
    */
    private Integer currentPage;

    
    /**
    * @Fields pageSize : 当前页面拥有条目数
    */
    private Integer pageSize;

    
    /**
    * @Fields recordCount : 数据总条目数
    */
    private Integer recordCount;

    
    /**
    * @Fields pageCount : 分页数量
    */
    private Integer pageCount;

    
    /**
    * @Fields resultList : 返回的数据载体
    */
    private List<?> resultList;
    
    
    /**
    * <p>Title: 构造方法1</p>
    * <p>Description: </p>
    * @param sum 总条目数
    */
    public Pager(int sum){
        
    }
    
    /**
    * <p>Title: 构造方法2</p>
    * <p>Description: </p>
    * @param sum 总条目数
    * @param size 当前页面数量
    */
    public Pager(int sum,int page,int size){
        pageSize = size;
        recordCount = sum;
        currentPage = page;
        init();
    }
    
    
    public void init(){
        pageCount = recordCount%pageSize==0 ? recordCount/pageSize : recordCount/pageSize+1;
        //check page
        currentPage = currentPage < 1 ? 1 : ((currentPage > pageCount)? pageCount : currentPage);
    }
    
    
    /** 
     * @Title: getStart 
     * @Description:得到start
     * @return
     */
    public int getStart(){
        return (currentPage-1) * pageSize;
    }
    
    /**
     * @return the currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the recordCount
     */
    public Integer getRecordCount() {
        return recordCount;
    }

    /**
     * @param recordCount the recordCount to set
     */
    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    /**
     * @return the pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the resultList
     */
    public List<?> getResultList() {
        return resultList;
    }

    /**
     * @param resultList the resultList to set
     */
    public void setResultList(List<?> resultList) {
        this.resultList = resultList;
    }
    
    
}
 