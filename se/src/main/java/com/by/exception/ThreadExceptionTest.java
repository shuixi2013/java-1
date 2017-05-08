package com.by.exception;

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
public class ThreadExceptionTest implements Runnable {
	public void run() {
		firstMethod();
	}

	public void firstMethod() {
		secondMethod();
	}

	@SuppressWarnings("unused")
	public void secondMethod() {
		int a = 5;
		int b = 0;
		int c = a / b;
	}

	public static void main(String[] args) {
		new Thread(new ThreadExceptionTest()).start();
		System.out.println("end");
	}
}
