package com.byk.portal.config;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author: love
 * @Description:图形验证码配置
 * @Date: 2019-06-24 17:45
 * @Version: 1.0
 */
@Configuration
public class CaptchaConfig {

    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        // properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "189,16,224");
        properties.setProperty("kaptcha.background.clear.from", "255,255,255"); //背景渐变
        properties.setProperty("kaptcha.background.clear.to", "255,255,255");
        properties.setProperty("kaptcha.noise.color", "189,16,224"); //干扰色
        properties.setProperty("kaptcha.image.width", "158");
        properties.setProperty("kaptcha.image.height", "80");
        properties.setProperty("kaptcha.textproducer.font.size", "60");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
//		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑"); //字体
        properties.setProperty("kaptcha.textproducer.char.string", "ABCDEFGHIGKLMNOPRSTUVWXYZ123456789");
//		properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy"); //图片样式
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
