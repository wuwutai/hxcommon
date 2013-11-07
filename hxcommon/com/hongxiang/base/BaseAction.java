package com.hongxiang.base;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hongxiang.context.HXContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**                              
 * @description : struts2的基础类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:59:13 +0800 
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -8136717383450925090L;

	private String logResourceKeyName;
	
	private HXContext hxContext;
	
	/**
     * @return hxContext
     */
    public HXContext getHxContext() {
        try {
            hxContext = new HXContext();
            hxContext.setActionContext(getContext());       
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hxContext;
    }

    /**
     * @param hxContext to set hxContext 
     */
    public void setHxContext(HXContext hxContext) {
        this.hxContext = hxContext;
    }

    /**
	* @Fields start : 此参数用于ajax的表格，即 表格数量起始索引
	*/
	private int start;
	
	/**
	* @Fields limit : 此参数用于ajax的表格，即 表格数量偏移量
	*/
	private int limit;
	
	
	/**
	 * @Fields LIST : 添加一个列表页面静态指向
	 */
	public static final String LIST = "list";

	/**
	 * @Fields sysLogService : 系统日志service
	 */
//	@Autowired(required = true)
//	public ILoggerService sysLogService;

	/**
	 * @Fields resultFlag : 用户ajax方法的标志位
	 */
	private boolean resultFlag;

	/**
	 * @Fields page : 当前页
	 */
	private int page;

	/**
	 * @Fields enterPageSize : 企业页面显示条目数
	 */
	private int enterPageSize;

	/**
	 * @Fields personalPageSize : 个人页面显示条目数
	 */
	private int personalPageSize;

	/**
	 * @Fields message : 页面返回的信息
	 */
	private String message;

	/**
	 * @Fields title : 页面的标题
	 */
	private String title;

	/**
	 * @Fields themeName : 主题名称
	 */
	private String themeName;

	/**
	 * @Fields firstClassMenuId: 一级菜单ID
	 */
	private String firstClassMenuId;

	/**
	 * @Fields secondClassMenuId: 二级菜单ID
	 */
	private String secondClassMenuId;

	/**
	 * @Fields selectedMenuId : 选中的三级菜单ID
	 */
	private String selectedMenuId;

	
	

	/**
	 * @return the logResourceKeyName
	 */
	public String getLogResourceKeyName() {
		return logResourceKeyName;
	}

	/**
	 * @param logResourceKeyName
	 *            the logResourceKeyName to set
	 */
	public void setLogResourceKeyName(String logResourceKeyName) {
		this.logResourceKeyName = logResourceKeyName;
	}

	   /**
	    * @Title: getContext
	    * @Description: 获取context
	    * @param @return    设定文件
	    * @return ActionContext    返回类型
	    * @author: 刘馨远
	    * @date 2013-03-15 13:15:14 +0800
	    * @throws
	    * @修改记录 <日期 时间 记录变更人> 
	    * @修改描述 <修改原因，修改内容>
	    */
	    public static ActionContext getContext(){
	        return ActionContext.getContext();
	        
	    }

	

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            要设置的 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the resultFlag
	 */
	public boolean getResultFlag() {
		return resultFlag;
	}

	/**
	 * @param resultFlag
	 *            the resultFlag to set
	 */
	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
	}

	/**
	 * @return the enterPageSize
	 */
	public int getEnterPageSize() {
		if (enterPageSize == 0) {
			enterPageSize = Integer.parseInt(BaseConfigHelper.getBaseConfig(ServletActionContext.getServletContext())
					.getEnterPageSize());
		}
		return enterPageSize;
	}

	/**
	 * @param enterPageSize
	 *            the enterPageSize to set
	 */
	public void setEnterPageSize(int enterPageSize) {
		this.enterPageSize = enterPageSize;
	}

	/**
	 * @return the personalPageSize
	 */
	public int getPersonalPageSize() {
		if (personalPageSize == 0) {
			personalPageSize = Integer.parseInt(BaseConfigHelper
					.getBaseConfig(ServletActionContext.getServletContext()).getPersonalPageSize());
		}
		return personalPageSize;
	}

	/**
	 * @param personalPageSize
	 *            the personalPageSize to set
	 */
	public void setPersonalPageSize(int personalPageSize) {
		this.personalPageSize = personalPageSize;
	}

	/** 
	 * @Title: getSession 
	 * @Description: 获取session的函数
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/** 
	 * @Title: getSessionValue 
	 * @Description: 根据key获取session的值
	 * @param name
	 * @return
	 */
	public static Object getSessionValue(String name) {
		return (getRequest().getSession()).getAttribute(name);
	}

	/** 
	 * @Title: setSessionValue 
	 * @Description: 往session中设置值 
	 * @param name
	 * @param value
	 */
	public static void setSessionValue(String name, Object value) {
		(getRequest().getSession()).setAttribute(name, value);
	}

	/** 
	 * @Title: getApplication 
	 * @Description: 获取 application 对象
	 * @return
	 */
	public static ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	/** 
	 * @Title: getRequest 
	 * @Description:获取页面的request对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/** 
	 * @Title: getResponse 
	 * @Description:  获取页面的response对象 
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/** 
	 * @Title: isAjaxRequest 
	 * @Description:判断当前请求是否为ajax请求
	 * @return
	 */
	public static boolean isAjaxRequest() {
		boolean result = false;
		String requestType = getRequest().getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			result = true;
		}
		return result;
	}

	/** 
	 * @Title: getClassName 
	 * @Description:  获得当前类的全名
	 * @return
	 */
	public String getClassName() {
		return this.getClass().getName();
	}

	/**
	 * @return the firstClassMenuId
	 */
	public String getFirstClassMenuId() {
		return firstClassMenuId;
	}

	/**
	 * @param firstClassMenuId
	 *            the firstClassMenuId to set
	 */
	public void setFirstClassMenuId(String firstClassMenuId) {
		this.firstClassMenuId = firstClassMenuId;
	}

	/**
	 * @return the secondClassMenuId
	 */
	public String getSecondClassMenuId() {
		return secondClassMenuId;
	}

	/**
	 * @param secondClassMenuId
	 *            the secondClassMenuId to set
	 */
	public void setSecondClassMenuId(String secondClassMenuId) {
		this.secondClassMenuId = secondClassMenuId;
	}

	/**
	 * @return the selectedMenuId
	 */
	public String getSelectedMenuId() {
		return selectedMenuId;
	}

	/**
	 * @param selectedMenuId
	 *            the selectedMenuId to set
	 */
	public void setSelectedMenuId(String selectedMenuId) {
		this.selectedMenuId = selectedMenuId;
	}


	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		if (limit == 0) {
			limit = Integer.parseInt(BaseConfigHelper.getBaseConfig(ServletActionContext.getServletContext())
				.getEnterPageSize());
		}
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
}
 