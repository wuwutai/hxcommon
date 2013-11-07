package com.hongxiang.base;

import java.util.Map;

/**                              
 * @description : 全局变量类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 11:02:03 +0800 
 */
public class BaseConfigBean {
	public static final String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = "root_web_application_context_attribute";
	
	public static final String ROOT_WEB_SESSION_USER = "root_web_session_user";
	
	public static final String ROOT_WEB_SESSION_THEME = "root_web_session_theme";
	/**
	* @Fields systemName : 系统拥有的名称
	*/
	private String systemName;
	
	/**
	* @Fields uploadDir : 文件上传目录
	*/ 
	private String uploadDir;
	/**
	* @Fields uploadFileSize : 上传文件大小限制，单位 兆
	*/ 
	private String uploadFileSize;
	/**
	* @Fields uploadFileExtName : 上传文件扩展名，用|分隔
	*/ 
	private String uploadFileExtName;
	/**
	* @Fields enterPageSize : 企业页面显示条目数
	*/ 
	private String enterPageSize;
	/**
	* @Fields personalPageSize : 个人页面显示条目数
	*/ 
	private String personalPageSize;
	/**
	* @Fields userDefaultPassword : 用户默认密码
	*/
	private String userDefaultPassword;
    /**
	* @Fields customParameter : 自定义属性，用来以后扩展使用
	*/ 
	private Map<?,?> customParameter;
	/**
	* @Fields captchaCodeLength :验证码最小长度设置
	*/
	private int captchaCodeMinLength;
	
	/**
	* @Fields captchaCodeMaxLength : 验证码最大长度设置
	*/
	private int captchaCodeMaxLength;
	
	/**
	* @Fields captchaBackGroundColor : 验证码背景颜色
	*/
	private String captchaBackGroundColor;
	
	/**
	* @Fields captchaImgWidth : 验证码图片宽度（单位：像素px）
	*/
	private int captchaImgWidth;
	
	/**
	* @Fields captchaImgHeight : 验证码图片高度（单位：像素px）
	*/
	private int captchaImgHeight;
	
	/**
	* @Fields captchaMaxFontSize : 验证码最大字体大小（单位：像素px）
	*/
	private int captchaMaxFontSize;
	
	/**
	* @Fields captchaMinFontSize : 验证码最小字体大小（单位：像素px）
	*/
	private int captchaMinFontSize;
	/**
	* @Fields captchaCodeChars : 验证码拥有的字符集合
	*/
	private String captchaCodeChars;
	
	
	/**
	* @Fields copyRight : 版权信息
	*/
	private String copyRight;
	
	
	
	
	
	
    public String getSystemName() {
        return systemName;
    }
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
    public String getCaptchaCodeChars() {
        return captchaCodeChars;
    }
    public void setCaptchaCodeChars(String captchaCodeChars) {
        this.captchaCodeChars = captchaCodeChars;
    }
    public int getCaptchaCodeMinLength() {
        return captchaCodeMinLength;
    }
    public void setCaptchaCodeMinLength(int captchaCodeMinLength) {
        this.captchaCodeMinLength = captchaCodeMinLength;
    }
    public int getCaptchaCodeMaxLength() {
        return captchaCodeMaxLength;
    }
    public void setCaptchaCodeMaxLength(int captchaCodeMaxLength) {
        this.captchaCodeMaxLength = captchaCodeMaxLength;
    }
    public String getCaptchaBackGroundColor() {
        return captchaBackGroundColor;
    }
    public void setCaptchaBackGroundColor(String captchaBackGroundColor) {
        this.captchaBackGroundColor = captchaBackGroundColor;
    }
    public int getCaptchaImgWidth() {
        return captchaImgWidth;
    }
    public void setCaptchaImgWidth(int captchaImgWidth) {
        this.captchaImgWidth = captchaImgWidth;
    }
    public int getCaptchaImgHeight() {
        return captchaImgHeight;
    }
    public void setCaptchaImgHeight(int captchaImgHeight) {
        this.captchaImgHeight = captchaImgHeight;
    }
    public int getCaptchaMaxFontSize() {
        return captchaMaxFontSize;
    }
    public void setCaptchaMaxFontSize(int captchaMaxFontSize) {
        this.captchaMaxFontSize = captchaMaxFontSize;
    }
    public int getCaptchaMinFontSize() {
        return captchaMinFontSize;
    }
    public void setCaptchaMinFontSize(int captchaMinFontSize) {
        this.captchaMinFontSize = captchaMinFontSize;
    }
    /**
	 * @return the uploadFileSize
	 */
	public String getUploadFileSize() {
		return uploadFileSize;
	}
	/**
	 * @param uploadFileSize 要设置的 uploadFileSize
	 */
	public void setUploadFileSize(String uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}
	/**
	 * @return the uploadFileExtName
	 */
	public String getUploadFileExtName() {
		return uploadFileExtName;
	}
	/**
	 * @param uploadFileExtName 要设置的 uploadFileExtName
	 */
	public void setUploadFileExtName(String uploadFileExtName) {
		this.uploadFileExtName = uploadFileExtName;
	}
	/**
	 * @return the customParameter
	 */
	public Map<?, ?> getCustomParameter() {
		return customParameter;
	}
	/**
	 * @param customParameter 要设置的 customParameter
	 */
	public void setCustomParameter(Map<?, ?> customParameter) {
		this.customParameter = customParameter;
	}
	/**
	 * @return the uploadDir
	 */
	public String getUploadDir() {
		return uploadDir;
	}
	/**
	 * @param uploadDir 要设置的 uploadDir
	 */
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	/**
	 * @return the enterPageSize
	 */
	public String getEnterPageSize() {
		return enterPageSize;
	}
	/**
	 * @param enterPageSize the enterPageSize to set
	 */
	public void setEnterPageSize(String enterPageSize) {
		this.enterPageSize = enterPageSize;
	}
	/**
	 * @return the personalPageSize
	 */
	public String getPersonalPageSize() {
		return personalPageSize;
	}
	/**
	 * @param personalPageSize the personalPageSize to set
	 */
	public void setPersonalPageSize(String personalPageSize) {
		this.personalPageSize = personalPageSize;
	}
    /**
     * @return the userDefaultPassword
     */
    public String getUserDefaultPassword() {
        return userDefaultPassword;
    }
    /**
     * @param userDefaultPassword the userDefaultPassword to set
     */
    public void setUserDefaultPassword(String userDefaultPassword) {
        this.userDefaultPassword = userDefaultPassword;
    }
	/**
	 * @return the copyRight
	 */
	public String getCopyRight() {
		return copyRight;
	}
	/**
	 * @param copyRight the copyRight to set
	 */
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	
	
	
}
 