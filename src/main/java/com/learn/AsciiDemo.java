package com.learn;


/**
 * Project: encrypt-decrypt
 * File Created at 2022-04-05 18:54:18:54
 * {@link}
 *
 * @author <a href="mailto:charmFlightChen@gmail.com">chenming</a>
 * @version 1.0.0
 * @Type AsciiDemo.java
 * @Description
 * @date 2022/4/5 18:54
 */
public class AsciiDemo {
    public static void main(String[] args) {
        //testChar();
        //testStr();
        caesar();
    }

    public static void testChar() {
        char a = '0';
        int b = a;
        System.out.println("ascii码对应的十进制数字:" + b);
    }

    public static void testStr() {
        for (char c : "AaZz0".toCharArray()) {
            int b = c;
            System.out.println(b);
        }
    }

    /**
     * 移位法
     */
    public static void caesar() {
        String input = "Hello World";
        char[] chars = input.toCharArray();
        int key = 3;
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            b += key;//右移三位
            aChar = (char) b;
            sb.append(aChar);
            //System.out.println(aChar);
        }
        System.out.println(sb.toString());
    }
}
