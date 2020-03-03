package com.byk.account.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: yikai.bi
 */
@Controller
public class CustomLoginController {
    Logger logger = LoggerFactory.getLogger(getClass());
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    /**
     * 前往认证(登录)页面
     * @return
     */
    @RequestMapping("/login/page")
    public String toLogin() {
        logger.info("登陆方法进来了");
        return "login";
    }

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    /**
     * 获取图形验证码
     */
    @RequestMapping("/code/image")
    public void imageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 获取验证码字符串
        String code = defaultKaptcha.createText();
        logger.info("生成的图形验证码是：" + code);
        // 2. 字符串把它放到session中
        request.getSession().setAttribute(SESSION_KEY , code);
        // 3. 获取验证码图片
        BufferedImage image = defaultKaptcha.createImage(code);
        // 4. 将验证码图片把它写出去
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

}
