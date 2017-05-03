package com.by.junit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * 参数化测试
 * @author baoyang
 *
 */
@RunWith(Parameterized.class)
public class ParameterTest {

	private int expected;
	private int first;
	private int second;

	public ParameterTest(int expectedResult, int firstNumber, int secondNumber) {
		this.expected = expectedResult;
		this.first = firstNumber;
		this.second = secondNumber;
	}

	@Parameters
//	@Parameters(name = "{index}: add({0}+{1})={2}")
	public static Collection<Integer[]> addedNumbers() {
		return Arrays.asList(new Integer[][] { { 2, 1, 2 }, { 3, 2, 3 },
				{ 4, 3, 4 }, { 5, 4, 5 }, });
	}

	@Test
	public void sum() {
		System.out.println("Addition with parameters : " + first + " and "
				+ second);
		Assert.assertEquals(expected, Math.max(first, second));
	}
}
