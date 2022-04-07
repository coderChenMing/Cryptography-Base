package com.learn;

import java.io.UnsupportedEncodingException;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-05 19:11:19:11
 * {@link Integer}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type CarserDemo.java
 * @Description
 * @date 2022/4/5 19:11
 */
public class ByteDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //testByteInEnglish();
        testByteInCN();
    }

    private static void testByteInEnglish() {
        for (byte aByte : "a".getBytes()) {
            int b = aByte;
            System.out.println("十进制: "+b);
            System.out.println("二进制: "+Integer.toBinaryString(b));
        }
    }

    public static void testByteInCN() throws UnsupportedEncodingException {
        // 默认utf-8 一个中文对应三个字节
        for (byte aByte : "我".getBytes()) {
            int b = aByte;
            System.out.println("十进制: "+b);
            System.out.println("二进制: "+Integer.toBinaryString(b));
        }
        System.out.println("------------------------------------------------------------");
        // 一个中文对应两个字节
        for (byte aByte : "我".getBytes("gbk")) {
            int b = aByte;
            System.out.println("十进制: "+b);
            System.out.println("二进制: "+Integer.toBinaryString(b));
        }
    }
}
