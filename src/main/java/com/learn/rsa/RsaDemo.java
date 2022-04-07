package com.learn.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-07 17:22:17:22
 * {@link}
 * 非对称加密rsa
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type RsaDemo.java
 * @Description
 * @date 2022/4/7 17:22
 */
public class RsaDemo {

    // 获取生成器实例
    private static   KeyPairGenerator keyPairGenerator;
    private static   KeyPair keyPair;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String input = "中国";
        String algorithm = "RSA";
        // 使用私钥加密
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(algorithm));
        // 加密
        byte[] encodedInput = cipher.doFinal(input.getBytes());
        System.out.println("私钥加密且base64编码: "+Base64.encode(encodedInput));

        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE, getPublicKey(algorithm));
        // 解密
        byte[] decryptOutput = cipher.doFinal(encodedInput);
        System.out.println("公钥解密: "+new String(decryptOutput));
    }

    /*
     *  获取私钥
     * @param algorithm
     * @return java.security.PrivateKey
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static PrivateKey getPrivateKey(String algorithm) throws Exception {
        // 获取生成器实例
        /*String privateEncoded = Base64.encode(privateKey.getEncoded());
        System.out.println(privateEncoded);*/
        // 获取公钥和私钥
        return keyPair.getPrivate();
    }

    /*
     * 获取公钥
     * @param algorithm
     * @return java.security.PublicKey
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static PublicKey getPublicKey(String algorithm) throws Exception {
        /*String publicEncoded = Base64.encode(publicKey.getEncoded());
        System.out.println(publicEncoded);*/
        // 获取公钥和私钥
        return keyPair.getPublic();
    }
}
