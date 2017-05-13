package com.by;

import com.sun.istack.internal.NotNull;

import java.util.Optional;

/**
 * description:
 * create       2017/5/7 17:30
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(Optional.<String>ofNullable(null).orElseGet(()->"ni"));
    }

    private static void test(@NotNull String s) {
        System.out.println(s);
    }


}