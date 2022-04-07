package com.learn.sign;

import com.learn.rsa.RsaDemo2;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-07 21:00:21:00
 * {@link}
 * 数字签名与校验
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type SingatureDemo.java
 * @Description
 * @date 2022/4/7 21:00
 */
public class SignatureDemo {
    public static void main(String[] args) throws Exception {
        String a = "123";
        PublicKey publicKey = RsaDemo2.readPublicKey("a.pub", "RSA");
        PrivateKey privateKey = RsaDemo2.readPrivateKey("a.pri", "RSA");
        // 获取数字签名
        String signatureData = getSignature(a, "sha256withrsa", privateKey);
        System.out.println(signatureData);
        // 校验签名
        boolean check = verifySignature(a, "sha256withrsa", publicKey, signatureData);
        System.out.println("校验结果: " + check);
    }

    /*
     * 校验签名
     * @param input 原文
     * @param algorithm 算法
     * @param publicKey 公钥
     * @param signatureData 签名
     * @return boolean
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static boolean verifySignature(String input, String algorithm, PublicKey publicKey, String signatureData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化校验
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.decode(signatureData));
    }

    /*
     * 签名
     * @param input
     * @param algorithm 算法
     * @param privateKey 私钥
     * @return java.lang.String
     * @author charmFlightChen@gmail.com
     * @date 2022/4/7
     **/
    public static String getSignature(String input, String algorithm, PrivateKey privateKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 使用base64编码
        return Base64.encode(sign);
    }
}
