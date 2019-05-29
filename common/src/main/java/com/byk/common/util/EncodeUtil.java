package com.byk.common.util;

/**
 * Title: 字节数组与字符串之间转换
 * Description:  字节数组与字符串之间转换
 * Copyright: Copyright (c)2010
 * Company: YeePay
 *
 * @author haiyang.hu
 */

public class EncodeUtil {

    /**
     * 字符转换为字节
     *
     * @param c char
     * @return byte
     */
    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * byte与16进制字符串的转换
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);

            }
            stringBuilder.append(hv.toUpperCase());
        }
        return stringBuilder.toString();
    }

}

