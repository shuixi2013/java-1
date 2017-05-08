package by.math.evalex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.udojava.evalex.Expression;


public class TestRPN {
	
	@Test
	public void testSimple() {
		
		Assert.assertEquals("1 2 +", new Expression("1+2").toRPN());
		Assert.assertEquals("1 2 4 / +", new Expression("1+2/4").toRPN());
		Assert.assertEquals("1 2 + 4 /", new Expression("(1+2)/4").toRPN());
		Assert.assertEquals("1.9 2.8 + 4.7 /", new Expression("(1.9+2.8)/4.7").toRPN());
		Assert.assertEquals("1.98 2.87 + 4.76 /", new Expression("(1.98+2.87)/4.76").toRPN());
		Assert.assertEquals("3 4 2 * 1 5 - 2 3 ^ ^ / +", new Expression("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3").toRPN());
	}
	
	@Test
	public void testFunctions() {
		
		Assert.assertEquals("( 23.6 SIN", new Expression("SIN(23.6)").toRPN());
		Assert.assertEquals("( -7 8 MAX", new Expression("MAX(-7,8)").toRPN());
		Assert.assertEquals("( ( 3.7 SIN ( 2.6 -8.0 MAX MAX", new Expression("MAX(SIN(3.7),MAX(2.6,-8.0))").toRPN());
	}

	@Test
	public void testOperatorsInFunctions() {

		Assert.assertEquals("( 23.6 4 / SIN", new Expression("SIN(23.6/4)").toRPN());
	}
}
