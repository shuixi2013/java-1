package com.by.math.evalex;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import com.udojava.evalex.Expression;


public class TestBooleans {

	@Test
	public void testAndTokenizer() {
		Expression e = new Expression("1&&0");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("1", i.next());
		Assert.assertEquals("&&", i.next());
		Assert.assertEquals("0", i.next());
	}

	@Test
	public void testAndRPN() {
		Assert.assertEquals("1 0 &&", new Expression("1&&0").toRPN());
	}
	
	@Test
	public void testAndEval() {
		Assert.assertEquals("0", new Expression("1&&0").eval().toString());
		Assert.assertEquals("1", new Expression("1&&1").eval().toString());
		Assert.assertEquals("0", new Expression("0&&0").eval().toString());
		Assert.assertEquals("0", new Expression("0&&1").eval().toString());
	}
	
	@Test
	public void testOrEval() {
		Assert.assertEquals("1", new Expression("1||0").eval().toString());
		Assert.assertEquals("1", new Expression("1||1").eval().toString());
		Assert.assertEquals("0", new Expression("0||0").eval().toString());
		Assert.assertEquals("1", new Expression("0||1").eval().toString());
	}
	
	@Test
	public void testCompare() {
		Assert.assertEquals("1", new Expression("2>1").eval().toString());
		Assert.assertEquals("0", new Expression("2<1").eval().toString());
		Assert.assertEquals("0", new Expression("1>2").eval().toString());
		Assert.assertEquals("1", new Expression("1<2").eval().toString());
		Assert.assertEquals("0", new Expression("1=2").eval().toString());
		Assert.assertEquals("1", new Expression("1=1").eval().toString());
		Assert.assertEquals("1", new Expression("1>=1").eval().toString());
		Assert.assertEquals("1", new Expression("1.1>=1").eval().toString());
		Assert.assertEquals("0", new Expression("1>=2").eval().toString());
		Assert.assertEquals("1", new Expression("1<=1").eval().toString());
		Assert.assertEquals("0", new Expression("1.1<=1").eval().toString());
		Assert.assertEquals("1", new Expression("1<=2").eval().toString());
		Assert.assertEquals("0", new Expression("1=2").eval().toString());
		Assert.assertEquals("1", new Expression("1=1").eval().toString());
		Assert.assertEquals("1", new Expression("1!=2").eval().toString());
		Assert.assertEquals("0", new Expression("1!=1").eval().toString());
	}
	
	@Test
	public void testCompareCombined() {
		Assert.assertEquals("1", new Expression("(2>1)||(1=0)").eval().toString());
		Assert.assertEquals("0", new Expression("(2>3)||(1=0)").eval().toString());
		Assert.assertEquals("1", new Expression("(2>3)||(1=0)||(1&&1)").eval().toString());
	}
	
	@Test
	public void testMixed() {
		Assert.assertEquals("0", new Expression("1.5 * 7 = 3").eval().toString());
		Assert.assertEquals("1", new Expression("1.5 * 7 = 10.5").eval().toString());
	}
	
	@Test
	public void testNot() {
		Assert.assertEquals("0", new Expression("not(1)").eval().toString());
		Assert.assertEquals("1", new Expression("not(0)").eval().toString());
		Assert.assertEquals("1", new Expression("not(1.5 * 7 = 3)").eval().toString());
		Assert.assertEquals("0", new Expression("not(1.5 * 7 = 10.5)").eval().toString());
	}

	@Test
	public void testConstants() {
		Assert.assertEquals("1", new Expression("TRUE!=FALSE").eval().toString());
		Assert.assertEquals("0", new Expression("TRUE==2").eval().toString());
		Assert.assertEquals("1", new Expression("NOT(TRUE)==FALSE").eval().toString());
		Assert.assertEquals("1", new Expression("NOT(FALSE)==TRUE").eval().toString());
		Assert.assertEquals("0", new Expression("TRUE && FALSE").eval().toString());
		Assert.assertEquals("1", new Expression("TRUE || FALSE").eval().toString());
	}

	@Test
	public void testIf() {
		Assert.assertEquals("5", new Expression("if(TRUE, 5, 3)").eval().toString());
		Assert.assertEquals("3", new Expression("IF(FALSE, 5, 3)").eval().toString());
		Assert.assertEquals("5.35", new Expression("If(2, 5.35, 3)").eval().toString());
	}
}
