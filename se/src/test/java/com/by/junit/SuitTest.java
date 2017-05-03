package com.by.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AnnotationsTest.class, AssertionsTest.class })
public class SuitTest {
	//测试顺序
//	public static void Testsuite(){ 
//	    suite.addTest(new SomeTestCase(“testDoThisFirst”); 
//	    suite.addTest(new SomeTestCase(“testDoThisSecond”); 
//	    return suite; 
//	} 
}

