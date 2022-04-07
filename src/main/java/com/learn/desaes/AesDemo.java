package com.learn.desaes;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

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
public class AesDemo {

    public static void main(String[] args) throws Exception {
        // 明文
        String input = "小羊肖恩";
        // 密钥
        String key = "24d2b8f406c6447f";// 使用AES加密,密钥必须是16字节
        // 算法
        String transformation = "AES";

        // 加密类型
        String encryptType = "AES";

        String desEncrypt = aesEncrypt(input, key, transformation, encryptType);
        System.out.println("加密后密文: "+desEncrypt);
        String desDecrypt = aesDecrypt(desEncrypt, key, transformation, encryptType);
        System.out.println("解密后明文: "+desDecrypt);
    }

    public static String aesEncrypt(String input, String key, String transformation, String encryptType) throws Exception {

        Cipher aes = getInstance(transformation);
        // 加解密规则:密钥字节,加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), encryptType);
        // init 模式：加密和解密,加解密规则
        aes.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 调用加密方法
        // 明文字节数组
        byte[] bytes = aes.doFinal(input.getBytes());
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

    private static Cipher getInstance(String transformation) throws NoSuchAlgorithmException, NoSuchPaddingException {
        return Cipher.getInstance(transformation);
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
    public static String aesDecrypt(String encryptDes, String key, String transformation, String decryptType) throws Exception {

        byte[] decode = getDecode(encryptDes);

        Cipher des = getInstance(transformation);
        // 加解密规则:密钥字节,加密类型
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), decryptType);
        // init 模式：加密和解密,加解密规则
        des.init(Cipher.DECRYPT_MODE, secretKeySpec);

        byte[] bytes = des.doFinal(decode);
        // 返回明文
        return new String(bytes);
    }

    private static byte[] getDecode(String encryptDes) {
        return Base64.decode(encryptDes);
    }
}
