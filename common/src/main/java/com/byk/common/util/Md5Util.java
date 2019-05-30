package com.byk.common.util;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Md5Util {


    public static String hmacSign(String aValue, String aKey) {
        byte[] k_ipad = new byte[64];
        byte[] k_opad = new byte[64];
        byte[] keyb;
        byte[] value;
        try {
            keyb = aKey.getBytes("UTF-8");
            value = aValue.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }

        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0x36);
            k_opad[i] = (byte) (keyb[i] ^ 0x5C);
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte[] dg = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return toHex(dg);
    }

    public static String toHex(byte[] input) {
        if (input == null) {
            return null;
        }
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xFF;
            if (current < 16) {
                output.append("0");
            }
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

    /**
     * @description:获取验签字符串
     * @param sn
     * @return String
     */
    public static String getSign(String sn){
        String md5Str = getMd5Str(sn);
        String retStr = "";
        if(null != md5Str) {
            retStr = md5Str.substring(5,25);
        }
        return retStr;
    }

    public static String getMd5Str(String source) {
        byte b[] = getMd5Bytes(source);
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        //logger.info("getMd5Str source: {}" , source);
        return buf.toString();
    }

    public static byte[] getMd5Bytes(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes("UTF-8"));
            byte b[] = md.digest();
            return b;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String getTimeKey(String milisec){
        return milisec.substring(6, 9)+milisec.substring(10,13);
    }

    /**
     * 按照聚合支付规则的加密方法
     * 1、para按照key进行排序，然后组成字符串
     * 2、字符串儿的末尾加上key
     * 3、md5加密
     * 4、小写转大写
     * @author zhou.zhao
     */
    public static String encryption(Map<String,String> para, String key){
        StringBuffer retMsgB = new StringBuffer();
        TreeMap<String,String> tm = new TreeMap<String,String>();
        tm.putAll(para);
        for (Map.Entry<String, String> entry : tm.entrySet()) {
            String mkey = entry.getKey();
            String mvalue = entry.getValue();

            if(!StringUtils.isBlank(mkey)&&!StringUtils.isBlank(mvalue)){
                String temValue = new String(mvalue);
                //如果 mvalue 包含转移 \ 去除
                if(temValue.contains("\\")){
                    temValue = temValue.replace("\\", "");
                }
                retMsgB.append(mkey+"="+temValue+"&");
            }

        }
        String retMsg = retMsgB.toString();
        if(retMsg.charAt(retMsg.length()-1)=='&'){
            retMsg = retMsg.substring(0,retMsg.length()-1);
        }
        retMsg = retMsg+key;
        //logger.info("验签字符串为： " + retMsg);
        /** 加密 、转大写 */
        retMsg = getMd5Str(retMsg).toUpperCase();
        //logger.info("加密验签字符串为： " + retMsg);
        return retMsg.toString();
    }

}
