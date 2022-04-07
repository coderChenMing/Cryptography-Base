package com.learn.desaes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-06 10:20:10:20
 * {@link}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type DesAesDemo.java
 * @Description
 * @date 2022/4/6 10:20
 */
public class DesDemo {

    public static void main(String[] args) throws Exception {
        // 明文
        String input = "小羊肖恩1111小羊肖恩1111";
        // 密钥
        String key = "12345678";// 使用DES加密,密钥必须是8字节
        // 算法
        // String transformation = "DES";
        // String transformation = "DES/ECB/PKCS5Padding";
        // 使用String transformation = "DES";和 String transformation = "DES/ECB/PKCS5Padding";加密后结果相同

        // 使用CBC加密模式需要获取方向向量
        // String transformation = "DES/CBC/PKCS5Padding";
        // 如果使用NoPadding,被加密明文必须是8bytes的整数倍,向量也必须是8个字节
        String transformation = "DES/CBC/NoPadding";


        // 加密类型
        String encryptType = "DES";

        String desEncrypt = desEncrypt(input, key, transformation, encryptType);
        // ECB:Woi9XivRUj+bRIUUEJOEQA==
        // CBC:urWUKs+QXdtour4SeOPf3g==
        System.out.println("加密后密文: " + desEncrypt);
        String desDecrypt = desDecrypt(desEncrypt, key, transformation, encryptType);
        System.out.println("解密后明文: " + desDecrypt);
    }

    public static String desEncrypt(String input, String key, String transformation, String encryptType) throws Exception {

        Cipher des = Cipher.getInstance(transformation);
        IvParameterSpec iv = getIv();
        // 加解密规则:密钥字节,加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), encryptType);
        // init 模式：加密和解密,加解密规则
        des.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        // 调用加密方法
        // 明文字节数组
        byte[] bytes = des.doFinal(input.getBytes());
       /* for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        // 直接打印密文会出现乱码
        System.out.println(new String(bytes));*/
        // 为了防止出现乱码使用base64编码
        String encode = Base64.encode(bytes);
        //System.out.println("密文的base64编码: " + encode);
        return encode;
    }

    private static IvParameterSpec getIv() {
        return new IvParameterSpec("12345678".getBytes());
    }


    /*
     * 解密
     * @param encryptDes :密文
     * @param key 密钥
     * @param transformation 加密算法
     * @param decryptType  加密类型：也是一种算法
     * @return 明文
     * @author charmFlightChen@gmail.com
     * @date 2022/4/6
     **/
    public static String desDecrypt(String encryptDes, String key, String transformation, String decryptType) throws Exception {

        byte[] decode = Base64.decode(encryptDes);
        IvParameterSpec iv = getIv();
        Cipher des = Cipher.getInstance(transformation);
        // 加解密规则:密钥字节,加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), decryptType);
        // init 模式：加密和解密,加解密规则
        des.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

        byte[] bytes = des.doFinal(decode);
        // 返回明文
        return new String(bytes);
    }
}
