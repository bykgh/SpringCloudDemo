package com.byk.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil {

    private static final byte[] INIT_KEY = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    private static final String ALGORITHM_ALL = "DES/CBC/NoPadding";
    private static final String AlgorithmAll_DESede = "DESede/ECB/NoPadding";

    /**
     * DES加密
     *
     * @param src 需要加密的数据
     * @param key 加密的密钥
     * @return
     */
    public static String desEncrypt(String src, String key) {
        if (StringUtil.isNull(src) || StringUtil.isNull(key)) {
            return null;
        }
        byte[] b = null;
        if (key.length() == 16) {
            b = desEncrypt(INIT_KEY, EncodeUtil.hexStringToByte(src), EncodeUtil.hexStringToByte(key));
        } else if (key.length() == 32) {
            //双倍长3DES加密
            String keyTmp = key.substring(0, 16);
            b = des3Encrypt(EncodeUtil.hexStringToByte(src), EncodeUtil.hexStringToByte(key + keyTmp));
        } else {
            b = des3Encrypt(EncodeUtil.hexStringToByte(src), EncodeUtil.hexStringToByte(key));
        }
        return EncodeUtil.bytesToHexString(b);
    }

    /**
     * 3DES加密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] des3Encrypt(byte[] data, byte[] key) {
        SecretKeyFactory keyFactory;
        //加密
        byte[] result = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            //创建Cipher对象 默认的DES/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance(AlgorithmAll_DESede, "SunJCE");
            //初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 3DES解密
     *
     * @param data
     * @param key
     * @return
     */
    public static byte[] des3Decrypt(byte[] data, byte[] key) {
        SecretKeyFactory keyFactory;
        //解密
        byte[] result = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key);
            keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretkey = keyFactory.generateSecret(dks);
            //创建Cipher对象 默认的DES/ECB/PKCS5Padding
            Cipher cipher = Cipher.getInstance(AlgorithmAll_DESede, "SunJCE");
            //初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * DES加密
     *
     * @param ivstr CBC算法模式特定的偏移向量
     * @param src   需要加密的字节
     * @param key   加密的密钥
     * @return
     */
    public static byte[] desEncrypt(byte[] ivstr, byte[] src, byte[] key) {
        try {
            IvParameterSpec iv = null;
            if (ivstr != null) {
                iv = new IvParameterSpec(ivstr);
            } else {
                iv = new IvParameterSpec(INIT_KEY);
            }
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_ALL, "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
            return cipher.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }


    /**
     * DES解密
     *
     * @param ivstr CBC算法模式特定的偏移向量
     * @param src   需要解密的字节
     * @param key   密钥
     * @return
     */
    public static byte[] desDecrypt(byte[] ivstr, byte[] src, byte[] key) {
        try {
            // ivstr CBC算法模式特定的偏移向量， src 需要加密的数据，key 密钥
            IvParameterSpec iv = null;
            if (ivstr != null) {
                iv = new IvParameterSpec(ivstr);
            } else {
                iv = new IvParameterSpec(INIT_KEY);
            }
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_ALL, "SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
            return cipher.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * DES解密
     *
     * @param ivstr CBC算法模式特定的偏移向量
     * @param src   需要解密String
     * @param key   密钥String
     * @return
     */
    public static String desDecrypt(String src, String key) {
        byte[] b = desDecrypt(INIT_KEY, EncodeUtil.hexStringToByte(src), EncodeUtil.hexStringToByte(key));
        return EncodeUtil.bytesToHexString(b);
    }

    public static void main(String[] args) {
        System.out.println(DesUtil.desEncrypt("2222222222222222", "9AE9E33AECD8966C"));
        System.out.println(DesUtil.desDecrypt("F82DFE7338CA3E29", "9AE9E33AECD8966C"));
    }

}
