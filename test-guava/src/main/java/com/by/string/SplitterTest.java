package com.by.string;

import com.google.common.base.Splitter;

/**
 * description:
 * create       2017/6/21 10:19
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class SplitterTest {
    public static void main(String[] args) {
        SplitterTest tester = new SplitterTest();
        tester.testSplitter();
    }

    private void testSplitter() {
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog."));
    }
}
