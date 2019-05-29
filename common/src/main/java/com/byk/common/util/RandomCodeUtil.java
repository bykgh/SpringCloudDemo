package com.byk.common.util;

public class RandomCodeUtil {
    /**
     * 产生一个随机的N位的字符串
     *
     * @param fix 字符串的位数
     * @return
     */
    public static String genRandomCode(int fix) {
        String chars = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        char[] rands = new char[fix];
        for (int i = 0; i < fix; i++) {
            int rand = (int) (Math.random() * 62);
            rands[i] = chars.charAt(rand);
        }
        return new String(rands);
    }

    public static void main(String[] ad) {
        System.out.println(genRandomCode(8));
    }
}
