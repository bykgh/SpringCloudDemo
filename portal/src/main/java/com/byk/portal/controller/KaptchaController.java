package com.byk.portal.controller;


import com.byk.common.common.PortalCommon;
import com.byk.common.common.RedisCommon;
import com.byk.portal.util.ProbUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;


/**
 * @Author: yikai.bi
 * @Description: 图形验证码
 * @Date: 2019-06-24 18:00
 * @Version: 1.0
 */
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KaptchaController.class);
    @Resource
    private DefaultKaptcha captchaProducer;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录注册显示验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/verCode")
    @PermitAll
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        configureResponse(response);
        ProbUtil.setColorPropertyRandom(captchaProducer.getConfig());
        String capText = captchaProducer.createText();

        HttpSession session = request.getSession();
        session.setAttribute(PortalCommon.VER_CODE_SESSION_KEY,capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }


    /**
     * 输入用户名之后再显示验证码
     * @param request
     * @param response
     * @param userCode
     * @throws Exception
     */
    @RequestMapping(value = "/verCodeByUserCode")
    @PermitAll
    public void getKaptchaImageByUserCode(HttpServletRequest request, HttpServletResponse response, String userCode) throws Exception {
        configureResponse(response);
        ProbUtil.setColorPropertyRandom(captchaProducer.getConfig());
        String capText = captchaProducer.createText();
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(RedisCommon.KAPTCHA_VER_CODE + userCode, capText, 60, TimeUnit.SECONDS);
        LOGGER.info("getKaptchaImage info send,phone:" + userCode + ",code:" + capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    private void configureResponse(HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
    }

}
