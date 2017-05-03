package com.by.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

//@Ignore("Not Ready to Run")
public class AnnotationsTest {
	
	private ArrayList<String> testList;

	@BeforeClass //方法必须是public static
	public static void onceExecutedBeforeAll() {
		System.out.println("@BeforeClass: onceExecutedBeforeAll");
	}

	@Before
	public void executedBeforeEach() {
		testList = new ArrayList<String>();
		System.out.println("@Before: executedBeforeEach");
	}

	@AfterClass //方法必须是public static
	public static void onceExecutedAfterAll() {
		System.out.println("@AfterClass: onceExecutedAfterAll");
	}

	@After
	public void executedAfterEach() {
		testList.clear();
		System.out.println("@After: executedAfterEach");
	}

	@Test
	public void EmptyCollection() {
		Assert.assertTrue(testList.isEmpty());
		System.out.println("@Test: EmptyArrayList");

	}

	@Test
	public void OneItemCollection() {
		testList.add("oneItem");
		Assert.assertEquals(1, testList.size());
		System.out.println("@Test: OneItemArrayList");
	}
	
	@Test
	public void OneItemCollection1() {
		testList.add("oneItem");
		Assert.assertEquals(1, testList.size());
		System.out.println("@Test: OneItemArrayList");
	}
	
	@Test(expected = ArithmeticException.class)  //异常测试
	public void divisionWithException() {  
	  @SuppressWarnings("unused")
	  int i = 1/0;
	}
	
	
	@Test(timeout = 1000)  //超时测试
	public void infinity() {  
		while (true);  
	}  

	@Ignore
	@Test
	public void executionIgnored() {
		System.out.println("@Ignore: This execution is ignored");
	}
}
