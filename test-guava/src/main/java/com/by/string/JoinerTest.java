package com.by.string;

import com.google.common.base.Joiner;

import java.util.Arrays;

/**
 * description:
 * create       2017/6/21 10:17
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class JoinerTest {
    public static void main(String[] args) {
        JoinerTest tester = new JoinerTest();
        tester.testJoiner();
    }

    private void testJoiner() {
        System.out.println(Joiner.on(",")
                .skipNulls()
                .join(Arrays.asList(1, 2, 3, 4, 5, null, 6)));
    }
}
