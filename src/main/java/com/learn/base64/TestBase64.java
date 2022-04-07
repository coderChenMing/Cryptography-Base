package com.learn.base64;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-06 16:17:16:17
 * {@link}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type TestBase64.java
 * @Description
 * @date 2022/4/6 16:17
 */
public class TestBase64 {
    public static void main(String[] args) {
        // 一个字节,由于base64一次处理三个字节,需要填充=处理
        System.out.println("一个字节: "+Base64.encode("1".getBytes()));
        System.out.println("两个字节: "+Base64.encode("12".getBytes()));
        System.out.println("三个字节: "+Base64.encode("123".getBytes()));
        System.out.println("六个字节: "+Base64.encode("中国".getBytes()));

    }
}
