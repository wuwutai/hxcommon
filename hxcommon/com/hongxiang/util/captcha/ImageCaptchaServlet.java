package com.hongxiang.util.captcha;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;

/**                              
 * @description :  图片验证码访问servlet
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:37:58 +0800 
 */
public class ImageCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = -5382766069139170499L;
	
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	/*
	* <p>Title: doGet</p>
	* <p>Description: </p>
	* @param httpServletRequest
	* @param httpServletResponse
	* @throws ServletException
	* @throws IOException
	* @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	protected void doGet(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		try {
			CaptchaServiceSingleton.getInstance(httpServletRequest).writeCaptchaImage(httpServletRequest, httpServletResponse);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		} catch (CaptchaServiceException e) {
			httpServletResponse
					.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
	}
}

 