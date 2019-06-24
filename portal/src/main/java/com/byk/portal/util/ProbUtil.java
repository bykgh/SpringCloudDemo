package com.byk.portal.util;

import com.google.code.kaptcha.util.Config;

import java.util.*;

/**
 * @Author: love
 * @Description: 图形验证码工具类
 * @Date: 2019-06-24 17:59
 * @Version: 1.0
 */
public class ProbUtil {

    private final static String[] colors = {"189,16,224", "126,211,33", "255,189,15"};

    private static List<String> initColor(String color){
        List<String> colorList = new ArrayList<String>();
        for(String c:colors){
            if(!c.equals(color)){
                colorList.add(c);
            }
        }
        return colorList;
    }

    @Deprecated
    private static Map<String, String> getColorLimit(String color){
        List<String> colorList = initColor(color);
        Map<String, String> colorMap = new HashMap<String, String>();
        int num = 100/colorList.size();
        for(int i=0;i<colorList.size();i++){
            colorMap.put(colorList.get(i), (i*num) + "-" + ((i+1)*num));
        }
        return colorMap;
    }

    private static Map<String, String> getColorLimit(){
        Map<String, String> colorMap = new HashMap<String, String>();
        int num = 100/colors.length;
        for(int i=0;i<colors.length;i++){
            colorMap.put(colors[i], (i*num) + "-" + ((i+1)*num));
        }
        return colorMap;
    }

    public static void setColorPropertyRandom(Config config){
        Map<String, String> colorMap = getColorLimit();
        Random random = new Random();
        int randomNum = random.nextInt(99);
        for(Map.Entry<String, String> entry : colorMap.entrySet()){
            int limitLower = Integer.parseInt(entry.getValue().split("-")[0]);
            int limitUpper = Integer.parseInt(entry.getValue().split("-")[1]);
            if(randomNum>=limitLower&&randomNum<limitUpper){
                Properties properties = config.getProperties();
                properties.setProperty("kaptcha.textproducer.font.color", entry.getKey());
                properties.setProperty("kaptcha.noise.color", entry.getKey());
                return;
            }
        }
    }

}
