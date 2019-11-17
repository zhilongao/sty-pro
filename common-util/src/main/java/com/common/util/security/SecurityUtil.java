package com.common.util.security;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author long
 * @Date 2019/11/9 18:06
 */
public class SecurityUtil {
    /**
     * AES加密
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public static String encodeAes(String src, String key) throws Exception{
        if (key == null || key.length() != 16) {
            System.out.print("Key为空null");
            return null;
        }
        byte[] raw = key.getBytes("UTF-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return new org.apache.commons.codec.binary.Base64().encodeToString(encrypted);
    }

    /**
     * AES解密
     * @param src
     * @param key
     * @return
     * @throws Exception
     */
    public static String decodeAes(String src, String key) throws Exception{
        if (key == null || key.length() != 16) {
            System.out.print("Key为空null");
            return null;
        }
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        //先用base64解密
        byte[] encrypted1 = new org.apache.commons.codec.binary.Base64().decode(src);
        try {
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }



    /**
     * 编码
     * @param text
     * @return
     */
    public static String encodeC(String text){
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        byte[] bytes = null;
        String res = null;
        try {
            bytes = text.getBytes("UTF-8");
            res = base64.encodeToString(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 解码
     * @param text
     * @return
     */
    public static String decodeC(String text) {
        org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
        String res = null;
        byte[] bytes = null;
        bytes = base64.decode(text);
        try {
            res = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 编码
     * @param text
     * @return
     */
    public static String encodeB(String text) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = null;
        String res = null;
        try {
            bytes = text.getBytes("UTF-8");
            byte[] encode = encoder.encode(bytes);
            res = new String(encode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 节码
     * @param text
     * @return
     */
    public static String decodeB(String text) {
        Base64.Decoder decoder = Base64.getDecoder();
        String res = null;
        byte[] decode = decoder.decode(text);
        try {
            res = new String(decode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 编码
     * @param text
     * @return
     */
    public static String encodeA(String text) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        byte[] bytes = null;
        String res = null;
        try {
            bytes = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            res = base64Encoder.encode(bytes);
        }
        return res;
    }

    /**
     * 节码
     * @param text
     * @return
     */
    public static String decodeA(String text) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] bytes = null;
        String res = null;
        try {
            bytes = base64Decoder.decodeBuffer(text);
            res = new String(bytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }



    /**
     * md5加密
     * @param text
     * @param key
     * @return
     */
    public static String md5(String text, String key) {
        return DigestUtils.md5Hex(text + key);
    }
}
