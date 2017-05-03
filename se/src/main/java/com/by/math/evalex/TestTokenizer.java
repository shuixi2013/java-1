package com.by.math.evalex;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import com.udojava.evalex.Expression;


public class TestTokenizer {
	
	@Test
	public void testNumbers() {
		Expression e;
		Iterator<String> i;
		
		e = new Expression("1");
		i = e.getExpressionTokenizer();
		Assert.assertEquals("1", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertNull(i.next());
		
		e = new Expression("-1");
		i = e.getExpressionTokenizer();
		Assert.assertEquals("-1", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertNull(i.next());
		
		e = new Expression("123");
		i = e.getExpressionTokenizer();
		Assert.assertEquals("123", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertNull(i.next());
		
		e = new Expression("-123");
		i = e.getExpressionTokenizer();
		Assert.assertEquals("-123", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertNull(i.next());
		
		e = new Expression("123.4");
		i = e.getExpressionTokenizer();
		Assert.assertEquals("123.4", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertNull(i.next());
		
		e = new Expression("-123.456");
		i = e.getExpressionTokenizer();
		Assert.assertEquals("-123.456", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertNull(i.next());
	}

    @Test
    public void testTokenizerExtraSpaces() {
        Expression e = new Expression("1 ");
        Iterator<String> i = e.getExpressionTokenizer();
        Assert.assertTrue(i.hasNext());
        Assert.assertEquals("1", i.next());
        Assert.assertFalse(i.hasNext());
        Assert.assertNull(i.next());

        e = new Expression("       ");
        i = e.getExpressionTokenizer();
        Assert.assertFalse(i.hasNext());
        Assert.assertNull(i.next());

        e = new Expression("   1      ");
        i = e.getExpressionTokenizer();
        Assert.assertTrue(i.hasNext());
        Assert.assertEquals("1", i.next());
        Assert.assertFalse(i.hasNext());
        Assert.assertNull(i.next());

        e = new Expression("  1   +   2    ");
        i = e.getExpressionTokenizer();
        Assert.assertEquals("1", i.next());
        Assert.assertEquals("+", i.next());
        Assert.assertTrue(i.hasNext());
        Assert.assertEquals("2", i.next());
        Assert.assertFalse(i.hasNext());
        Assert.assertNull(i.next());
    }

    @Test
	public void testTokenizer1() {
		Expression e = new Expression("1+2");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("1", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("2", i.next());
	}

	@Test
	public void testTokenizer2() {
		Expression e = new Expression("1 + 2");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("1", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("2", i.next());
	}
	
	@Test
	public void testTokenizer3() {
		Expression e = new Expression(" 1 + 2 ");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("1", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("2", i.next());
	}
	
	@Test
	public void testTokenizer4() {
		Expression e = new Expression("1+2-3/4*5");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("1", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("2", i.next());
		Assert.assertEquals("-", i.next());
		Assert.assertEquals("3", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("4", i.next());
		Assert.assertEquals("*", i.next());
		Assert.assertEquals("5", i.next());
	}
		
	@Test
	public void testTokenizer5() {
		Expression e = new Expression("1+2.1-3.45/4.982*5.0");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("1", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("2.1", i.next());
		Assert.assertEquals("-", i.next());
		Assert.assertEquals("3.45", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("4.982", i.next());
		Assert.assertEquals("*", i.next());
		Assert.assertEquals("5.0", i.next());
	}
	
	@Test
	public void testTokenizer6() {
		Expression e = new Expression("-3+4*-1");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("-3", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("4", i.next());
		Assert.assertEquals("*", i.next());
		Assert.assertEquals("-1", i.next());
	}
	
	@Test
	public void testTokenizer7() {
		Expression e = new Expression("(-3+4)*-1/(7-(5*-8))");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("-3", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("4", i.next());
		Assert.assertEquals(")", i.next());
		Assert.assertEquals("*", i.next());
		Assert.assertEquals("-1", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("7", i.next());
		Assert.assertEquals("-", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("5", i.next());
		Assert.assertEquals("*", i.next());
		Assert.assertEquals("-8", i.next());
		Assert.assertEquals(")", i.next());
		Assert.assertEquals(")", i.next());
	}
	
	public void testTokenizer8() {
		Expression e = new Expression("(1.9+2.8)/4.7");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("1.9", i.next());
		Assert.assertEquals("+", i.next());
		Assert.assertEquals("2.8", i.next());
		Assert.assertEquals(")", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("4.7", i.next());
	}
	
	@Test
	public void testTokenizerFunction1() {
		Expression e = new Expression("ABS(3.5)");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("ABS", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("3.5", i.next());
		Assert.assertEquals(")", i.next());
	}
	
	@Test
	public void testTokenizerFunction2() {
		Expression e = new Expression("3-ABS(3.5)/9");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("3", i.next());
		Assert.assertEquals("-", i.next());
		Assert.assertEquals("ABS", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("3.5", i.next());
		Assert.assertEquals(")", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("9", i.next());
	}
	@Test
	
	public void testTokenizerFunction3() {
		Expression e = new Expression("MAX(3.5,5.2)");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("MAX", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("3.5", i.next());
		Assert.assertEquals(",", i.next());
		Assert.assertEquals("5.2", i.next());
		Assert.assertEquals(")", i.next());
	}
	
	@Test
	public void testTokenizerFunction4() {
		Expression e = new Expression("3-MAX(3.5,5.2)/9");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("3", i.next());
		Assert.assertEquals("-", i.next());
		Assert.assertEquals("MAX", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("3.5", i.next());
		Assert.assertEquals(",", i.next());
		Assert.assertEquals("5.2", i.next());
		Assert.assertEquals(")", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("9", i.next());
	}
	
	@Test
	public void testTokenizerFunction5() {
		Expression e = new Expression("3/MAX(-3.5,-5.2)/9");
		Iterator<String> i = e.getExpressionTokenizer();
		
		Assert.assertEquals("3", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("MAX", i.next());
		Assert.assertEquals("(", i.next());
		Assert.assertEquals("-3.5", i.next());
		Assert.assertEquals(",", i.next());
		Assert.assertEquals("-5.2", i.next());
		Assert.assertEquals(")", i.next());
		Assert.assertEquals("/", i.next());
		Assert.assertEquals("9", i.next());
	}
}
