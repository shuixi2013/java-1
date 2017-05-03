package com.by.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * 外部运行测试
 * @author baoyang
 *
 */
public class JunitRunner {
	
	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(AssertionsTest.class);
		for (Failure fail : result.getFailures()) {
			System.out.println(fail.toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("All tests finished successfully...");
		}
	}
}
