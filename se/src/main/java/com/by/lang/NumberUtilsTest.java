package com.by.lang;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberUtilsTest {
	public static void main(String[] args) {
		System.out.println("**NumberUtils**");
		System.out.println("从数组中选出最大值.");
		// System.out.println(NumberUtils.max(new int[] { 1, 2, 3, 4 }));
		System.out.println("判断字符串是否全是整数.");
		System.out.println(NumberUtils.isDigits("123.1"));

		System.out.println("判断字符串是否是有效数字.");
		System.out.println(NumberUtils.isNumber("0123.1"));
	}
}
