package com.by.string;

import com.google.common.base.CaseFormat;

/**
 * description:
 * create       2017/6/21 10:26
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class CaseFormatTest {
    public static void main(String[] args) {
        CaseFormatTest tester = new CaseFormatTest();
        tester.testCaseFormat();
    }

    private void testCaseFormat() {
        String data = "test_data";
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }
}
