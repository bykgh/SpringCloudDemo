package com.byk.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.List;

/**
 * Title: 字符 工具类
 * Description:
 * Copyright: Copyright (c)2011
 * Company: pay
 *
 * @author huakui.zhang
 */
public class StringUtil {

    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return "".equals(obj.toString().trim());
    }

    public static boolean notNull(String str) {
        return str != null && !"".equals(str);
    }

    public static String safeValue(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 字符填充
     * @param sourceStr
     * @param chr
     * @param direction
     * @param length
     * @return
     */
    public static String fillChar(String sourceStr, String chr, String direction, int length) {
        int strLen = sourceStr.length();
        if (strLen < length) {
            while (strLen < length) {
                StringBuffer sb = new StringBuffer();

                if ("L".equals(direction)) {
                    //左补
                    sb.append(chr).append(sourceStr);
                } else if ("R".equals(direction)) {
                    //右补
                    sb.append(sourceStr).append(chr);
                }
                sourceStr = sb.toString();
                strLen = sourceStr.length();
            }
        }

        return sourceStr;
    }

    /**
     * 替换字符串中的"_",并使它的下一个字母转为大写
     */
    public static String replaceUnderline(String srcStr) {

        srcStr = srcStr.toLowerCase();
        String org = "_";

        String newString = "";
        int first = 0;
        while (srcStr.indexOf(org) != -1) {
            first = srcStr.indexOf(org);
            if (first != srcStr.length()) {
                newString = newString + srcStr.substring(0, first);
                srcStr = srcStr.substring(first + org.length(), srcStr.length());
                srcStr = firstCharacterToUpper(srcStr);
            }
        }
        newString = newString + srcStr;
        return newString;
    }

    /**
     * 首字母大写
     */
    public static String firstCharacterToUpper(String srcStr) {
        return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
    }

    public static String getObjectName(Class clazz) {
        if (clazz == null) {
            return "";
        }
        String clazzName = clazz.getName().toString();
        clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);

        return clazzName;
    }

    /**
     * 将对象转化成JSON字符串
     *
     * @return
     */
    public static String objectToJSONString(Object object, JsonConfig jsonConfig) {
        String JSONString = "";
        try {
            if (object instanceof List) {
                JSONString = JSONArray.fromObject(object, jsonConfig).toString();
            } else {
                JSONString = JSONObject.fromObject(object).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONString;
    }

    public static void main(String[] args) {
        System.out.println(replaceUnderline("PURCHASE_REFUND_abc"));
    }

}
