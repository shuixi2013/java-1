package com.by.common;

/**
 * description:
 * create       2017/5/3 16:25
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class BitTest {

    public static void main(String[] args) {
        int i = -1;
        System.out.println(i >>= 10);
        System.out.println(i >>>= 10);
        long l = -1;
        l >>= 10;
        System.out.println(l);
        short s = -1;
        s >>>= 10;
        System.out.println(s);
        byte b = -1;
        b >>>= 10;
        System.out.println(b);
    }
}
