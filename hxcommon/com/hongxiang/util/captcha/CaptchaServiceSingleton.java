package com.hongxiang.util.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hongxiang.base.BaseConfigBean;
import com.hongxiang.base.BaseConfigHelper;
import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**                              
 * @description : 验证码生成类
 * @Author: 刘馨远                 
 * @Date: 2013-03-28 10:37:07 +0800 
 */
public class CaptchaServiceSingleton extends ListImageCaptchaEngine {
    
    private static Logger log = Logger.getLogger(CaptchaServiceSingleton.class);

    private static CaptchaServiceSingleton instance;

    ImageCaptcha imageCaptcha = null;
    
    /**
    * @Fields minWordLength : 字符最小长度
    */
    private static int minWordLength;
    
    /**
    * @Fields maxWordLength : 字符最大长度
    */
    private static int maxWordLength;
    
    /**
    * @Fields minfontSize : 最小字体
    */
    private static int minfontSize;
    
    /**
    * @Fields maxfontSize : 最大字体
    */
    private static int maxfontSize;
    
    /**
    * @Fields imageWidth : 图片宽度
    */
    private static int imageWidth;
    
    /**
    * @Fields imageHeight : 图片高度
    */
    private static int imageHeight;
    
    /**
    * @Fields numberChars : 字符
    */
    private static String numberChars;
    
    /**
    * @Fields backcolor : 背景色
    */
    private static int backcolor;
    
    
    public CaptchaServiceSingleton(HttpServletRequest request) {
       
    }

    /** 
     * @Title: getInstance 
     * @Description: 获取实例函数
     * @param request
     * @return
     */
    public static CaptchaServiceSingleton getInstance(HttpServletRequest request) {
        //获取系统参数
        BaseConfigBean baseconfigbean = BaseConfigHelper.getBaseConfig(request.getSession().getServletContext());
        //开始设置参数
        minWordLength = baseconfigbean.getCaptchaCodeMinLength();
        maxWordLength = baseconfigbean.getCaptchaCodeMaxLength();
        minfontSize = baseconfigbean.getCaptchaMinFontSize();
        maxfontSize = baseconfigbean.getCaptchaMaxFontSize();
        imageWidth =  baseconfigbean.getCaptchaImgWidth();
        imageHeight = baseconfigbean.getCaptchaImgHeight();
        numberChars = baseconfigbean.getCaptchaCodeChars();
        backcolor = Integer.valueOf(baseconfigbean.getCaptchaBackGroundColor(),16);
        
        instance = new CaptchaServiceSingleton(request);
        return instance;
    }

    /*
    * <p>Title: buildInitialFactories  生成图片的代码</p>
    * <p>Description: </p>
    * @see com.octo.captcha.engine.image.ListImageCaptchaEngine#buildInitialFactories()
    */
    protected void buildInitialFactories() {
        
        //随机生成文字
        WordGenerator dictionnaryWords =  new RandomWordGenerator(numberChars);

        //生成随机字体颜色
        TextPaster randomPaster = new DecoratedRandomTextPaster(minWordLength, maxWordLength,
                new RandomListColorGenerator(new Color[] { new Color(23, 170, 27), new Color(220, 34, 11),
                        new Color(23, 67, 172) }), new TextDecorator[] {});
        
        //图片背景颜色
        BackgroundGenerator background = new UniColorBackgroundGenerator(imageWidth, imageHeight,new Color(backcolor));
        
        //随机字体
        FontGenerator font = new RandomFontGenerator(minfontSize, maxfontSize, new Font[] {
                new Font("nyala", Font.BOLD, minfontSize), new Font("Bell MT", Font.PLAIN, maxfontSize),
                new Font("Credit valley", Font.BOLD, minfontSize) });

        ImageDeformation postDef = new ImageDeformationByFilters(new ImageFilter[] {});
        ImageDeformation backDef = new ImageDeformationByFilters(new ImageFilter[] {});
        ImageDeformation textDef = new ImageDeformationByFilters(new ImageFilter[] {});

        WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
        addFactory(new GimpyFactory(dictionnaryWords, word2image));
    }

    
    /** 
     * @Title: writeCaptchaImage 
     * @Description: 验证码输出 
     * @param request
     * @param response
     * @throws IOException
     */
    public void writeCaptchaImage(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        imageCaptcha = getNextImageCaptcha();
        HttpSession session = request.getSession();
        session.setAttribute("imageCaptcha", imageCaptcha);
        BufferedImage image = (BufferedImage) imageCaptcha.getChallenge();

        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            // render the captcha challenge as a JPEG image in the response
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
            encoder.encode(image);
            outputStream.flush();
            outputStream.close();
            outputStream = null;// no close twice
        } catch (IOException ex) {
            log.error("产生图片异常 ＝＝ " + ex);
            throw ex;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                }
            }
            imageCaptcha.disposeChallenge();
        }
    }

    /** 
     * @Title: validateCaptchaResponse 
     * @Description: 验证码验证方法体
     * @param validateCode
     * @param session
     * @return
     */
    public boolean validateCaptchaResponse(String validateCode, HttpSession session) {
        boolean flag = true;
        try {
            imageCaptcha = (ImageCaptcha) session.getAttribute("imageCaptcha");
            if (imageCaptcha == null) {
                flag = false;
            }
            validateCode = validateCode.toUpperCase();// use upper case for
            flag = (imageCaptcha.validateResponse(validateCode)).booleanValue();
            session.removeAttribute("imageCaptcha");
            return flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("校验码校验异常 ＝＝ " + ex);
            return false;
        }
    }
    
    public ImageCaptcha getImageCaptcha() {
        return imageCaptcha;
    }

    public void setImageCaptcha(ImageCaptcha imageCaptcha) {
        this.imageCaptcha = imageCaptcha;
    }
}

/*
 *      用户验证的代码
 *      
        HttpSession session = request.getSession();
        boolean isResponseCorrect = false;
        // retrieve the response
        String validateCode = checkCode.trim();
        System.out.println("checkCode == "
                + checkCode);
        try {
            isResponseCorrect = CaptchaServiceSingleton.getInstance()
                    .validateCaptchaResponse(validateCode, session);

            return isResponseCorrect;
        } catch (Exception e) {
            // should not happen, may be thrown if the id is not valid

            return false;
        }

 * 
 * 
 * */

 