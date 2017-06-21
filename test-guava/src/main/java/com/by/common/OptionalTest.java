package com.by.common;

import com.google.common.base.Optional;

/**
 * description:
 * create       2017/6/20 14:39
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class OptionalTest {

    private static int temp = 10;

    public static void main(String[] args) {
        OptionalTest guavaTester = new OptionalTest();

        Integer value1 = null;
        Integer value2 = temp;
        //Optional.fromNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.fromNullable(value1);
        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);

        System.out.println(guavaTester.sum(a, b));
    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());

        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.or - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.or(0);

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();

        return value1 + value2;
    }
}
