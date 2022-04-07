package com.learn.rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-07 19:24:19:24
 * {@link}
 * 公钥私钥保存与读取方式完成加解密
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type RsaDemo2.java
 * @Description
 * @date 2022/4/7 19:24
 */
public class RsaDemo2 {
    public static void main(String[] args) throws Exception {
        String algorithm = "RSA";
        String input = "中国";
        // generateKeysToFile(algorithm,"a.pub","a.pri");
        // 公钥加密
        String encrypt = rsaPublicKeyEncrypt(algorithm, readPublicKey("a.pub", algorithm), input);
        System.out.println(encrypt);

        // 私钥解密
        String decrypt = rsaPrivateKeyDecrypt(algorithm, readPrivateKey("a.pri", algorithm), encrypt);
        System.out.println(decrypt);
    }

    public static PublicKey readPublicKey(String publicPath, String algorithm) throws Exception {
        String publicKey = FileUtils.readFileToString(new File(publicPath), StandardCharsets.UTF_8);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建公钥的规则
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey));
        // 返回公钥对象
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    public static PrivateKey readPrivateKey(String privatePath, String algorithm) throws Exception {
        String privateKey = FileUtils.readFileToString(new File(privatePath), StandardCharsets.UTF_8);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建私钥的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
        // 返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }

    /*
     * 生成公钥私钥并保存
     * @param algorithm
     * @param publicPath
     * @param privatePath
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static void generateKeysToFile(String algorithm, String publicPath, String privatePath) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        String privateEncoded = Base64.encode(keyPair.getPrivate().getEncoded());
        String publicEncoded = Base64.encode(keyPair.getPublic().getEncoded());
        FileUtils.writeStringToFile(new File(publicPath), publicEncoded, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(new File(privatePath), privateEncoded, StandardCharsets.UTF_8);

    }

    /*
     * 私钥解密
     * @param algorithm
     * @param privateKey
     * @param encryptInput
     * @return java.lang.String
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static String rsaPrivateKeyDecrypt(String algorithm, Key privateKey, String encryptInput) throws Exception {
        // 使用私钥加密
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 加密
        byte[] decodeOutput = cipher.doFinal(Base64.decode(encryptInput));
        return new String(decodeOutput);
    }

    /*
     * 公钥加密
     * @param algorithm
     * @param publicKey
     * @param input
     * @return java.lang.String
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static String rsaPublicKeyEncrypt(String algorithm, Key publicKey, String input) throws Exception {
        // 使用私钥加密
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 加密
        byte[] encodedInput = cipher.doFinal(input.getBytes());

        return Base64.encode(encodedInput);
    }
}
