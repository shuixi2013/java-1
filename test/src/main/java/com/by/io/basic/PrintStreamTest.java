package by.io.basic;

import java.io.*;

/**
 * Description: <br/>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PrintStreamTest {
	public static void main(String[] args) {
		try (FileOutputStream fos = new FileOutputStream("test.txt");
				PrintStream ps = new PrintStream(fos)) {
			// 使用PrintStream执行输出
			ps.println("普通字符串");
			// 直接使用PrintStream输出对象
			ps.println(new PrintStreamTest());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * 使用PrintStream进行输出 并进行格式化
	 * */
	public static void Test(String[] args) throws IOException {
		PrintStream print = new PrintStream(new FileOutputStream(new File("d:"
				+ File.separator + "hello.txt")));
		String name = "Rollen";
		int age = 20;
		print.printf("姓名：%s. 年龄：%d.", name, age);
		print.close();
	}
}
