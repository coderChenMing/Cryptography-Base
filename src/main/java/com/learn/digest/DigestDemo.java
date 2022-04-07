package com.learn.digest;

import java.security.MessageDigest;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-07 16:40:16:40
 * {@link}
 *  数字摘要算法demo
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type DigestEmo.java
 * @Description 常见算法；MD5 SHA-1 SHA-256 SHA-512
 * @date 2022/4/7 16:40
 */
public class DigestDemo {
    public static void main(String[] args) throws Exception {
        String input = "aa";
        // aa 在线加密结果:4124BC0A9335C27F086F24BA207A4912
        String algorithm = "MD5";
        String outPut= getStringBuilder(input, algorithm);
        System.out.println(outPut);
        System.out.println("--------------------------------------");
        algorithm = "SHA-1";
        outPut= getStringBuilder(input, algorithm);
        System.out.println(outPut);
        System.out.println("--------------------------------------");
        algorithm = "SHA-256";
        outPut= getStringBuilder(input, algorithm);
        System.out.println(outPut);
        System.out.println("--------------------------------------");
        algorithm = "SHA-512";
        outPut= getStringBuilder(input, algorithm);
        System.out.println(outPut);


    }

    public static String getStringBuilder(String input, String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        byte[] outPut = messageDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : outPut) {
            String s = Integer.toHexString(b & 0xFF);// 转16进制
            // 如果密文长度是1需要在高位补0
            if (s.length() == 1) {
                s ="0"+s;
            }
            sb.append(s);
           // System.out.print(s);// 4124bc0a9335c27f086f24ba207a4912
        }
        return sb.toString();
        //System.out.println("MD5加密结果:"+Base64.encode(outPut));// 结果:QSS8CpM1wn8IbyS6IHpJEg==
    }
}
