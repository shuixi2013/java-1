package com.by.io.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 读一个整数
		int temp = scanner.nextInt();
		System.out.println(temp);
		// 读取浮点数
		float flo = scanner.nextFloat();
		System.out.println(flo);
	}

	/**
	 * Scanner的小例子，从文件中读内容
	 * */
	public static void Test(String[] args) {

		File file = new File("d:" + File.separator + "hello.txt");
		Scanner sca = null;
		try {
			sca = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str = sca.next();
		System.out.println("从文件中读取的内容是：" + str);
	}
}
