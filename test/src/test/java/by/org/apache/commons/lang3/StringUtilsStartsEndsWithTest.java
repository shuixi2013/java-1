/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package by.org.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.lang3.text.StrBuilder;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - StartsWith/EndsWith methods
 */
public class StringUtilsStartsEndsWithTest {
    private static final String foo    = "foo";
    private static final String bar    = "bar";
    private static final String foobar = "foobar";
    private static final String FOO    = "FOO";
    private static final String BAR    = "BAR";
    private static final String FOOBAR = "FOOBAR";

    //-----------------------------------------------------------------------

    /**
     * Test StringUtils.startsWith()
     */
    @Test
    public void testStartsWith() {
        Assert.assertTrue("startsWith(null, null)", StringUtils.startsWith(null, (String)null));
        Assert.assertFalse("startsWith(FOOBAR, null)", StringUtils.startsWith(FOOBAR, (String)null));
        Assert.assertFalse("startsWith(null, FOO)",    StringUtils.startsWith(null, FOO));
        Assert.assertTrue("startsWith(FOOBAR, \"\")",  StringUtils.startsWith(FOOBAR, ""));

        Assert.assertTrue("startsWith(foobar, foo)",  StringUtils.startsWith(foobar, foo));
        Assert.assertTrue("startsWith(FOOBAR, FOO)",  StringUtils.startsWith(FOOBAR, FOO));
        Assert.assertFalse("startsWith(foobar, FOO)", StringUtils.startsWith(foobar, FOO));
        Assert.assertFalse("startsWith(FOOBAR, foo)", StringUtils.startsWith(FOOBAR, foo));

        Assert.assertFalse("startsWith(foo, foobar)", StringUtils.startsWith(foo, foobar));
        Assert.assertFalse("startsWith(foo, foobar)", StringUtils.startsWith(bar, foobar));

        Assert.assertFalse("startsWith(foobar, bar)", StringUtils.startsWith(foobar, bar));
        Assert.assertFalse("startsWith(FOOBAR, BAR)", StringUtils.startsWith(FOOBAR, BAR));
        Assert.assertFalse("startsWith(foobar, BAR)", StringUtils.startsWith(foobar, BAR));
        Assert.assertFalse("startsWith(FOOBAR, bar)", StringUtils.startsWith(FOOBAR, bar));
    }

    /**
     * Test StringUtils.testStartsWithIgnoreCase()
     */
    @Test
    public void testStartsWithIgnoreCase() {
        Assert.assertTrue("startsWithIgnoreCase(null, null)",    StringUtils.startsWithIgnoreCase(null, (String)null));
        Assert.assertFalse("startsWithIgnoreCase(FOOBAR, null)", StringUtils.startsWithIgnoreCase(FOOBAR, (String)null));
        Assert.assertFalse("startsWithIgnoreCase(null, FOO)",    StringUtils.startsWithIgnoreCase(null, FOO));
        Assert.assertTrue("startsWithIgnoreCase(FOOBAR, \"\")",  StringUtils.startsWithIgnoreCase(FOOBAR, ""));

        Assert.assertTrue("startsWithIgnoreCase(foobar, foo)", StringUtils.startsWithIgnoreCase(foobar, foo));
        Assert.assertTrue("startsWithIgnoreCase(FOOBAR, FOO)", StringUtils.startsWithIgnoreCase(FOOBAR, FOO));
        Assert.assertTrue("startsWithIgnoreCase(foobar, FOO)", StringUtils.startsWithIgnoreCase(foobar, FOO));
        Assert.assertTrue("startsWithIgnoreCase(FOOBAR, foo)", StringUtils.startsWithIgnoreCase(FOOBAR, foo));

        Assert.assertFalse("startsWithIgnoreCase(foo, foobar)", StringUtils.startsWithIgnoreCase(foo, foobar));
        Assert.assertFalse("startsWithIgnoreCase(foo, foobar)", StringUtils.startsWithIgnoreCase(bar, foobar));

        Assert.assertFalse("startsWithIgnoreCase(foobar, bar)", StringUtils.startsWithIgnoreCase(foobar, bar));
        Assert.assertFalse("startsWithIgnoreCase(FOOBAR, BAR)", StringUtils.startsWithIgnoreCase(FOOBAR, BAR));
        Assert.assertFalse("startsWithIgnoreCase(foobar, BAR)", StringUtils.startsWithIgnoreCase(foobar, BAR));
        Assert.assertFalse("startsWithIgnoreCase(FOOBAR, bar)", StringUtils.startsWithIgnoreCase(FOOBAR, bar));
    }

    @Test
    public void testStartsWithAny() {
        Assert.assertFalse(StringUtils.startsWithAny(null, (String[])null));
        Assert.assertFalse(StringUtils.startsWithAny(null, "abc"));
        Assert.assertFalse(StringUtils.startsWithAny("abcxyz", (String[])null));
        Assert.assertFalse(StringUtils.startsWithAny("abcxyz"));
        Assert.assertTrue(StringUtils.startsWithAny("abcxyz", "abc"));
        Assert.assertTrue(StringUtils.startsWithAny("abcxyz", null, "xyz", "abc"));
        Assert.assertFalse(StringUtils.startsWithAny("abcxyz", null, "xyz", "abcd"));
        Assert.assertTrue(StringUtils.startsWithAny("abcxyz", new String[]{""}));
        Assert.assertFalse(StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX"));
        Assert.assertFalse(StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc"));

        Assert.assertTrue("StringUtils.startsWithAny(abcxyz, StringBuilder(xyz), StringBuffer(abc))", StringUtils.startsWithAny("abcxyz", new StringBuilder("xyz"), new StringBuffer("abc")));
        Assert.assertTrue("StringUtils.startsWithAny( StrBuilder(abcxyz), StringBuilder(xyz), StringBuffer(abc))", StringUtils.startsWithAny( new StrBuilder("abcxyz"), new StringBuilder("xyz"), new StringBuffer("abc")));
    }
 

    /**
     * Test StringUtils.endsWith()
     */
    @Test
    public void testEndsWith() {
        Assert.assertTrue("endsWith(null, null)",    StringUtils.endsWith(null, (String)null));
        Assert.assertFalse("endsWith(FOOBAR, null)", StringUtils.endsWith(FOOBAR, (String)null));
        Assert.assertFalse("endsWith(null, FOO)",    StringUtils.endsWith(null, FOO));
        Assert.assertTrue("endsWith(FOOBAR, \"\")",  StringUtils.endsWith(FOOBAR, ""));

        Assert.assertFalse("endsWith(foobar, foo)", StringUtils.endsWith(foobar, foo));
        Assert.assertFalse("endsWith(FOOBAR, FOO)", StringUtils.endsWith(FOOBAR, FOO));
        Assert.assertFalse("endsWith(foobar, FOO)", StringUtils.endsWith(foobar, FOO));
        Assert.assertFalse("endsWith(FOOBAR, foo)", StringUtils.endsWith(FOOBAR, foo));

        Assert.assertFalse("endsWith(foo, foobar)", StringUtils.endsWith(foo, foobar));
        Assert.assertFalse("endsWith(foo, foobar)", StringUtils.endsWith(bar, foobar));

        Assert.assertTrue("endsWith(foobar, bar)",  StringUtils.endsWith(foobar, bar));
        Assert.assertTrue("endsWith(FOOBAR, BAR)",  StringUtils.endsWith(FOOBAR, BAR));
        Assert.assertFalse("endsWith(foobar, BAR)", StringUtils.endsWith(foobar, BAR));
        Assert.assertFalse("endsWith(FOOBAR, bar)", StringUtils.endsWith(FOOBAR, bar));

        // "alpha,beta,gamma,delta".endsWith("delta")
        Assert.assertTrue("endsWith(\u03B1\u03B2\u03B3\u03B4, \u03B4)",
                StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4", "\u03B4"));
        // "alpha,beta,gamma,delta".endsWith("gamma,DELTA")
        Assert.assertFalse("endsWith(\u03B1\u03B2\u03B3\u03B4, \u03B3\u0394)",
                StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4", "\u03B3\u0394"));
    }

    /**
     * Test StringUtils.endsWithIgnoreCase()
     */
    @Test
    public void testEndsWithIgnoreCase() {
        Assert.assertTrue("endsWithIgnoreCase(null, null)",    StringUtils.endsWithIgnoreCase(null, (String)null));
        Assert.assertFalse("endsWithIgnoreCase(FOOBAR, null)", StringUtils.endsWithIgnoreCase(FOOBAR, (String)null));
        Assert.assertFalse("endsWithIgnoreCase(null, FOO)",    StringUtils.endsWithIgnoreCase(null, FOO));
        Assert.assertTrue("endsWithIgnoreCase(FOOBAR, \"\")",  StringUtils.endsWithIgnoreCase(FOOBAR, ""));

        Assert.assertFalse("endsWithIgnoreCase(foobar, foo)", StringUtils.endsWithIgnoreCase(foobar, foo));
        Assert.assertFalse("endsWithIgnoreCase(FOOBAR, FOO)", StringUtils.endsWithIgnoreCase(FOOBAR, FOO));
        Assert.assertFalse("endsWithIgnoreCase(foobar, FOO)", StringUtils.endsWithIgnoreCase(foobar, FOO));
        Assert.assertFalse("endsWithIgnoreCase(FOOBAR, foo)", StringUtils.endsWithIgnoreCase(FOOBAR, foo));

        Assert.assertFalse("endsWithIgnoreCase(foo, foobar)", StringUtils.endsWithIgnoreCase(foo, foobar));
        Assert.assertFalse("endsWithIgnoreCase(foo, foobar)", StringUtils.endsWithIgnoreCase(bar, foobar));

        Assert.assertTrue("endsWithIgnoreCase(foobar, bar)", StringUtils.endsWithIgnoreCase(foobar, bar));
        Assert.assertTrue("endsWithIgnoreCase(FOOBAR, BAR)", StringUtils.endsWithIgnoreCase(FOOBAR, BAR));
        Assert.assertTrue("endsWithIgnoreCase(foobar, BAR)", StringUtils.endsWithIgnoreCase(foobar, BAR));
        Assert.assertTrue("endsWithIgnoreCase(FOOBAR, bar)", StringUtils.endsWithIgnoreCase(FOOBAR, bar));

        // javadoc
        Assert.assertTrue(StringUtils.endsWithIgnoreCase("abcdef", "def"));
        Assert.assertTrue(StringUtils.endsWithIgnoreCase("ABCDEF", "def"));
        Assert.assertFalse(StringUtils.endsWithIgnoreCase("ABCDEF", "cde"));

        // "alpha,beta,gamma,delta".endsWith("DELTA")
        Assert.assertTrue("endsWith(\u03B1\u03B2\u03B3\u03B4, \u0394)",
                StringUtils.endsWithIgnoreCase("\u03B1\u03B2\u03B3\u03B4", "\u0394"));
        // "alpha,beta,gamma,delta".endsWith("GAMMA")
        Assert.assertFalse("endsWith(\u03B1\u03B2\u03B3\u03B4, \u0393)",
                StringUtils.endsWithIgnoreCase("\u03B1\u03B2\u03B3\u03B4", "\u0393"));
    }

    @Test
    public void testEndsWithAny() {
        Assert.assertFalse("StringUtils.endsWithAny(null, null)", StringUtils.endsWithAny(null, (String)null));
        Assert.assertFalse("StringUtils.endsWithAny(null, new String[] {abc})", StringUtils.endsWithAny(null, new String[] {"abc"}));
        Assert.assertFalse("StringUtils.endsWithAny(abcxyz, null)", StringUtils.endsWithAny("abcxyz", (String)null));
        Assert.assertTrue("StringUtils.endsWithAny(abcxyz, new String[] {\"\"})", StringUtils.endsWithAny("abcxyz", new String[] {""}));
        Assert.assertTrue("StringUtils.endsWithAny(abcxyz, new String[] {xyz})", StringUtils.endsWithAny("abcxyz", new String[] {"xyz"}));
        Assert.assertTrue("StringUtils.endsWithAny(abcxyz, new String[] {null, xyz, abc})", StringUtils.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"}));
        Assert.assertFalse("StringUtils.endsWithAny(defg, new String[] {null, xyz, abc})", StringUtils.endsWithAny("defg", new String[] {null, "xyz", "abc"}));
        Assert.assertTrue(StringUtils.endsWithAny("abcXYZ", "def", "XYZ"));
        Assert.assertFalse(StringUtils.endsWithAny("abcXYZ", "def", "xyz"));
        Assert.assertTrue(StringUtils.endsWithAny("abcXYZ", "def", "YZ"));

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type. 
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        Assert.assertFalse(StringUtils.endsWithAny("abcXYZ", (CharSequence) null));
        Assert.assertFalse(StringUtils.endsWithAny("abcXYZ", (CharSequence[]) null));
        Assert.assertTrue(StringUtils.endsWithAny("abcXYZ", ""));

        Assert.assertTrue("StringUtils.endsWithAny(abcxyz, StringBuilder(abc), StringBuffer(xyz))", StringUtils.endsWithAny("abcxyz", new StringBuilder("abc"), new StringBuffer("xyz")));
        Assert.assertTrue("StringUtils.endsWithAny( StrBuilder(abcxyz), StringBuilder(abc), StringBuffer(xyz))", StringUtils.endsWithAny( new StrBuilder("abcxyz"), new StringBuilder("abc"), new StringBuffer("xyz")));
    }


}
