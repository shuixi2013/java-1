package com.by.lang;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringUtilsTest {
	public static void main(String[] args) {
		System.out.println("**RandomStringUtilsDemo**");
		System.out.println("在指定字符串中生成长度为n的随机字符串.");
		System.out.println(RandomStringUtils.random(5, "1234567890"));

		System.out.println("指定从字符或数字中生成随机字符串.");
		System.out.println(RandomStringUtils.random(5, true, false));
		System.out.println(RandomStringUtils.random(5, false, true));
		// 10位英字
		System.out.println(RandomStringUtils.randomAlphabetic(10));
		// 10位英数
		System.out.println(RandomStringUtils.randomAlphanumeric(10));
		// 10位ASCII码
		System.out.println(RandomStringUtils.randomAscii(10));
		// 指定文字10位
		System.out.println(RandomStringUtils.random(10, "abcde"));

	}
}
