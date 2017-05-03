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
package com.by.org.apache.commons.lang3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;

/**
 * Unit tests for methods of {@link org.apache.commons.lang3.StringUtils}
 * which been moved to their own test classes.
 */
@SuppressWarnings("deprecation") // deliberate use of deprecated code
public class StringUtilsTest {

    static final String WHITESPACE;
    static final String NON_WHITESPACE;
    static final String HARD_SPACE;
    static final String TRIMMABLE;
    static final String NON_TRIMMABLE;

    static {
        String ws = "";
        String nws = "";
        final String hs = String.valueOf(((char) 160));
        String tr = "";
        String ntr = "";
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            if (Character.isWhitespace((char) i)) {
                ws += String.valueOf((char) i);
                if (i > 32) {
                    ntr += String.valueOf((char) i);
                }
            } else if (i < 40) {
                nws += String.valueOf((char) i);
            }
        }
        for (int i = 0; i <= 32; i++) {
            tr += String.valueOf((char) i);
        }
        WHITESPACE = ws;
        NON_WHITESPACE = nws;
        HARD_SPACE = hs;
        TRIMMABLE = tr;
        NON_TRIMMABLE = ntr;
    }

    private static final String[] ARRAY_LIST = {"foo", "bar", "baz"};
    private static final String[] EMPTY_ARRAY_LIST = {};
    private static final String[] NULL_ARRAY_LIST = {null};
    private static final Object[] NULL_TO_STRING_LIST = {
            new Object() {
                @Override
                public String toString() {
                    return null;
                }
            }
    };
    private static final String[] MIXED_ARRAY_LIST = {null, "", "foo"};
    private static final Object[] MIXED_TYPE_LIST = {"foo", Long.valueOf(2L)};
    private static final long[] LONG_PRIM_LIST = {1, 2};
    private static final int[] INT_PRIM_LIST = {1, 2};
    private static final byte[] BYTE_PRIM_LIST = {1, 2};
    private static final short[] SHORT_PRIM_LIST = {1, 2};
    private static final char[] CHAR_PRIM_LIST = {'1', '2'};
    private static final float[] FLOAT_PRIM_LIST = {1, 2};
    private static final double[] DOUBLE_PRIM_LIST = {1, 2};

    private static final String SEPARATOR = ",";
    private static final char SEPARATOR_CHAR = ';';

    private static final String TEXT_LIST = "foo,bar,baz";
    private static final String TEXT_LIST_CHAR = "foo;bar;baz";
    private static final String TEXT_LIST_NOSEP = "foobarbaz";

    private static final String FOO_UNCAP = "foo";
    private static final String FOO_CAP = "Foo";

    private static final String SENTENCE_UNCAP = "foo bar baz";
    private static final String SENTENCE_CAP = "Foo Bar Baz";

    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new StringUtils());
        final Constructor<?>[] cons = StringUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(StringUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(StringUtils.class.getModifiers()));
    }

    @Test
    public void testUpperCase() {
        Assert.assertNull(StringUtils.upperCase(null));
        Assert.assertNull(StringUtils.upperCase(null, Locale.ENGLISH));
        Assert.assertEquals("upperCase(String) failed",
                "FOO TEST THING", StringUtils.upperCase("fOo test THING"));
        Assert.assertEquals("upperCase(empty-string) failed",
                "", StringUtils.upperCase(""));
        Assert.assertEquals("upperCase(String, Locale) failed",
                "FOO TEST THING", StringUtils.upperCase("fOo test THING", Locale.ENGLISH));
        Assert.assertEquals("upperCase(empty-string, Locale) failed",
                "", StringUtils.upperCase("", Locale.ENGLISH));
    }

    @Test
    public void testLowerCase() {
        Assert.assertNull(StringUtils.lowerCase(null));
        Assert.assertNull(StringUtils.lowerCase(null, Locale.ENGLISH));
        Assert.assertEquals("lowerCase(String) failed",
                "foo test thing", StringUtils.lowerCase("fOo test THING"));
        Assert.assertEquals("lowerCase(empty-string) failed",
                "", StringUtils.lowerCase(""));
        Assert.assertEquals("lowerCase(String, Locale) failed",
                "foo test thing", StringUtils.lowerCase("fOo test THING", Locale.ENGLISH));
        Assert.assertEquals("lowerCase(empty-string, Locale) failed",
                "", StringUtils.lowerCase("", Locale.ENGLISH));
    }

    @Test
    public void testCapitalize() {
        Assert.assertNull(StringUtils.capitalize(null));

        Assert.assertEquals("capitalize(empty-string) failed",
                "", StringUtils.capitalize(""));
        Assert.assertEquals("capitalize(single-char-string) failed",
                "X", StringUtils.capitalize("x"));
        Assert.assertEquals("capitalize(String) failed",
                FOO_CAP, StringUtils.capitalize(FOO_CAP));
        Assert.assertEquals("capitalize(string) failed",
                FOO_CAP, StringUtils.capitalize(FOO_UNCAP));

        Assert.assertEquals("capitalize(String) is not using TitleCase",
                "\u01C8", StringUtils.capitalize("\u01C9"));

        // Javadoc examples
        Assert.assertNull(StringUtils.capitalize(null));
        Assert.assertEquals("", StringUtils.capitalize(""));
        Assert.assertEquals("Cat", StringUtils.capitalize("cat"));
        Assert.assertEquals("CAt", StringUtils.capitalize("cAt"));
        Assert.assertEquals("'cat'", StringUtils.capitalize("'cat'"));
    }

    @Test
    public void testUnCapitalize() {
        Assert.assertNull(StringUtils.uncapitalize(null));

        Assert.assertEquals("uncapitalize(String) failed",
                FOO_UNCAP, StringUtils.uncapitalize(FOO_CAP));
        Assert.assertEquals("uncapitalize(string) failed",
                FOO_UNCAP, StringUtils.uncapitalize(FOO_UNCAP));
        Assert.assertEquals("uncapitalize(empty-string) failed",
                "", StringUtils.uncapitalize(""));
        Assert.assertEquals("uncapitalize(single-char-string) failed",
                "x", StringUtils.uncapitalize("X"));

        // Examples from uncapitalize Javadoc
        Assert.assertEquals("cat", StringUtils.uncapitalize("cat"));
        Assert.assertEquals("cat", StringUtils.uncapitalize("Cat"));
        Assert.assertEquals("cAT", StringUtils.uncapitalize("CAT"));
    }

    @Test
    public void testReCapitalize() {
        // reflection type of tests: Sentences.
        Assert.assertEquals("uncapitalize(capitalize(String)) failed",
                SENTENCE_UNCAP, StringUtils.uncapitalize(StringUtils.capitalize(SENTENCE_UNCAP)));
        Assert.assertEquals("capitalize(uncapitalize(String)) failed",
                SENTENCE_CAP, StringUtils.capitalize(StringUtils.uncapitalize(SENTENCE_CAP)));

        // reflection type of tests: One word.
        Assert.assertEquals("uncapitalize(capitalize(String)) failed",
                FOO_UNCAP, StringUtils.uncapitalize(StringUtils.capitalize(FOO_UNCAP)));
        Assert.assertEquals("capitalize(uncapitalize(String)) failed",
                FOO_CAP, StringUtils.capitalize(StringUtils.uncapitalize(FOO_CAP)));
    }

    @Test
    public void testSwapCase_String() {
        Assert.assertNull(StringUtils.swapCase(null));
        Assert.assertEquals("", StringUtils.swapCase(""));
        Assert.assertEquals("  ", StringUtils.swapCase("  "));

        Assert.assertEquals("i", WordUtils.swapCase("I"));
        Assert.assertEquals("I", WordUtils.swapCase("i"));
        Assert.assertEquals("I AM HERE 123", StringUtils.swapCase("i am here 123"));
        Assert.assertEquals("i aM hERE 123", StringUtils.swapCase("I Am Here 123"));
        Assert.assertEquals("I AM here 123", StringUtils.swapCase("i am HERE 123"));
        Assert.assertEquals("i am here 123", StringUtils.swapCase("I AM HERE 123"));

        final String test = "This String contains a TitleCase character: \u01C8";
        final String expect = "tHIS sTRING CONTAINS A tITLEcASE CHARACTER: \u01C9";
        Assert.assertEquals(expect, WordUtils.swapCase(test));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testJoin_Objects() {
        Assert.assertEquals("abc", StringUtils.join("a", "b", "c"));
        Assert.assertEquals("a", StringUtils.join(null, "", "a"));
        Assert.assertNull(StringUtils.join((Object[]) null));
    }

    @Test
    public void testJoin_Objectarray() {
//        assertNull(StringUtils.join(null)); // generates warning
        Assert.assertNull(StringUtils.join((Object[]) null)); // equivalent explicit cast
        // test additional varargs calls
        Assert.assertEquals("", StringUtils.join(new Object[0])); // empty array
        Assert.assertEquals("", StringUtils.join((Object) null)); // => new Object[]{null}

        Assert.assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST));
        Assert.assertEquals("", StringUtils.join(NULL_ARRAY_LIST));
        Assert.assertEquals("null", StringUtils.join(NULL_TO_STRING_LIST));
        Assert.assertEquals("abc", StringUtils.join(new String[]{"a", "b", "c"}));
        Assert.assertEquals("a", StringUtils.join(new String[]{null, "a", ""}));
        Assert.assertEquals("foo", StringUtils.join(MIXED_ARRAY_LIST));
        Assert.assertEquals("foo2", StringUtils.join(MIXED_TYPE_LIST));
    }

    @Test
    public void testJoin_ArrayCharSeparator() {
        Assert.assertNull(StringUtils.join((Object[]) null, ','));
        Assert.assertEquals(TEXT_LIST_CHAR, StringUtils.join(ARRAY_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, SEPARATOR_CHAR));
        Assert.assertEquals(";;foo", StringUtils.join(MIXED_ARRAY_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("foo;2", StringUtils.join(MIXED_TYPE_LIST, SEPARATOR_CHAR));

        Assert.assertEquals("/", StringUtils.join(MIXED_ARRAY_LIST, '/', 0, MIXED_ARRAY_LIST.length - 1));
        Assert.assertEquals("foo", StringUtils.join(MIXED_TYPE_LIST, '/', 0, 1));
        Assert.assertEquals("null", StringUtils.join(NULL_TO_STRING_LIST, '/', 0, 1));
        Assert.assertEquals("foo/2", StringUtils.join(MIXED_TYPE_LIST, '/', 0, 2));
        Assert.assertEquals("2", StringUtils.join(MIXED_TYPE_LIST, '/', 1, 2));
        Assert.assertEquals("", StringUtils.join(MIXED_TYPE_LIST, '/', 2, 1));
    }

    @Test
    public void testJoin_ArrayOfChars() {
        Assert.assertNull(StringUtils.join((char[]) null, ','));
        Assert.assertEquals("1;2", StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2", StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((char[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(CHAR_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfBytes() {
        Assert.assertNull(StringUtils.join((byte[]) null, ','));
        Assert.assertEquals("1;2", StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2", StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((byte[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(BYTE_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfInts() {
        Assert.assertNull(StringUtils.join((int[]) null, ','));
        Assert.assertEquals("1;2", StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2", StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((int[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(INT_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfLongs() {
        Assert.assertNull(StringUtils.join((long[]) null, ','));
        Assert.assertEquals("1;2", StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2", StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((long[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(LONG_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfFloats() {
        Assert.assertNull(StringUtils.join((float[]) null, ','));
        Assert.assertEquals("1.0;2.0", StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2.0", StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((float[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(FLOAT_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfDoubles() {
        Assert.assertNull(StringUtils.join((double[]) null, ','));
        Assert.assertEquals("1.0;2.0", StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2.0", StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((double[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(DOUBLE_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayOfShorts() {
        Assert.assertNull(StringUtils.join((short[]) null, ','));
        Assert.assertEquals("1;2", StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR));
        Assert.assertEquals("2", StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR, 1, 2));
        Assert.assertNull(StringUtils.join((short[]) null, SEPARATOR_CHAR, 0, 1));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR, 0, 0));
        Assert.assertEquals(StringUtils.EMPTY, StringUtils.join(SHORT_PRIM_LIST, SEPARATOR_CHAR, 1, 0));
    }

    @Test
    public void testJoin_ArrayString() {
        Assert.assertNull(StringUtils.join((Object[]) null, null));
        Assert.assertEquals(TEXT_LIST_NOSEP, StringUtils.join(ARRAY_LIST, null));
        Assert.assertEquals(TEXT_LIST_NOSEP, StringUtils.join(ARRAY_LIST, ""));

        Assert.assertEquals("", StringUtils.join(NULL_ARRAY_LIST, null));

        Assert.assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, null));
        Assert.assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, ""));
        Assert.assertEquals("", StringUtils.join(EMPTY_ARRAY_LIST, SEPARATOR));

        Assert.assertEquals(TEXT_LIST, StringUtils.join(ARRAY_LIST, SEPARATOR));
        Assert.assertEquals(",,foo", StringUtils.join(MIXED_ARRAY_LIST, SEPARATOR));
        Assert.assertEquals("foo,2", StringUtils.join(MIXED_TYPE_LIST, SEPARATOR));

        Assert.assertEquals("/", StringUtils.join(MIXED_ARRAY_LIST, "/", 0, MIXED_ARRAY_LIST.length - 1));
        Assert.assertEquals("", StringUtils.join(MIXED_ARRAY_LIST, "", 0, MIXED_ARRAY_LIST.length - 1));
        Assert.assertEquals("foo", StringUtils.join(MIXED_TYPE_LIST, "/", 0, 1));
        Assert.assertEquals("foo/2", StringUtils.join(MIXED_TYPE_LIST, "/", 0, 2));
        Assert.assertEquals("2", StringUtils.join(MIXED_TYPE_LIST, "/", 1, 2));
        Assert.assertEquals("", StringUtils.join(MIXED_TYPE_LIST, "/", 2, 1));
    }

    @Test
    public void testJoin_IteratorChar() {
        Assert.assertNull(StringUtils.join((Iterator<?>) null, ','));
        Assert.assertEquals(TEXT_LIST_CHAR, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), SEPARATOR_CHAR));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST).iterator(), SEPARATOR_CHAR));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), SEPARATOR_CHAR));
        Assert.assertEquals("foo", StringUtils.join(Collections.singleton("foo").iterator(), 'x'));
    }

    @Test
    public void testJoin_IteratorString() {
        Assert.assertNull(StringUtils.join((Iterator<?>) null, null));
        Assert.assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), null));
        Assert.assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), ""));
        Assert.assertEquals("foo", StringUtils.join(Collections.singleton("foo").iterator(), "x"));
        Assert.assertEquals("foo", StringUtils.join(Collections.singleton("foo").iterator(), null));

        Assert.assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST).iterator(), null));

        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), null));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), ""));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST).iterator(), SEPARATOR));

        Assert.assertEquals(TEXT_LIST, StringUtils.join(Arrays.asList(ARRAY_LIST).iterator(), SEPARATOR));

        Assert.assertNull(StringUtils.join(Arrays.asList(NULL_TO_STRING_LIST).iterator(), SEPARATOR));
    }

    @Test
    public void testJoin_IterableChar() {
        Assert.assertNull(StringUtils.join((Iterable<?>) null, ','));
        Assert.assertEquals(TEXT_LIST_CHAR, StringUtils.join(Arrays.asList(ARRAY_LIST), SEPARATOR_CHAR));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST), SEPARATOR_CHAR));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), SEPARATOR_CHAR));
        Assert.assertEquals("foo", StringUtils.join(Collections.singleton("foo"), 'x'));
    }

    @Test
    public void testJoin_IterableString() {
        Assert.assertNull(StringUtils.join((Iterable<?>) null, null));
        Assert.assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST), null));
        Assert.assertEquals(TEXT_LIST_NOSEP, StringUtils.join(Arrays.asList(ARRAY_LIST), ""));
        Assert.assertEquals("foo", StringUtils.join(Collections.singleton("foo"), "x"));
        Assert.assertEquals("foo", StringUtils.join(Collections.singleton("foo"), null));

        Assert.assertEquals("", StringUtils.join(Arrays.asList(NULL_ARRAY_LIST), null));

        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), null));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), ""));
        Assert.assertEquals("", StringUtils.join(Arrays.asList(EMPTY_ARRAY_LIST), SEPARATOR));

        Assert.assertEquals(TEXT_LIST, StringUtils.join(Arrays.asList(ARRAY_LIST), SEPARATOR));
    }

    @Test
    public void testJoinWith() {
        Assert.assertEquals("", StringUtils.joinWith(",", new Object[0]));        // empty array
        Assert.assertEquals("", StringUtils.joinWith(",", (Object[]) NULL_ARRAY_LIST));
        Assert.assertEquals("null", StringUtils.joinWith(",", NULL_TO_STRING_LIST));   //toString method prints 'null'

        Assert.assertEquals("a,b,c", StringUtils.joinWith(",", new Object[]{"a", "b", "c"}));
        Assert.assertEquals(",a,", StringUtils.joinWith(",", new Object[]{null, "a", ""}));

        Assert.assertEquals("ab", StringUtils.joinWith(null, "a", "b"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testJoinWithThrowsException() {
        StringUtils.joinWith(",", (Object[]) null);
    }


    @Test
    public void testSplit_String() {
        Assert.assertNull(StringUtils.split(null));
        Assert.assertEquals(0, StringUtils.split("").length);

        String str = "a b  .c";
        String[] res = StringUtils.split(str);
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals(".c", res[2]);

        str = " a ";
        res = StringUtils.split(str);
        Assert.assertEquals(1, res.length);
        Assert.assertEquals("a", res[0]);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.split(str);
        Assert.assertEquals(2, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b" + NON_WHITESPACE + "c", res[1]);
    }

    @Test
    public void testSplit_StringChar() {
        Assert.assertNull(StringUtils.split(null, '.'));
        Assert.assertEquals(0, StringUtils.split("", '.').length);

        String str = "a.b.. c";
        String[] res = StringUtils.split(str, '.');
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals(" c", res[2]);

        str = ".a.";
        res = StringUtils.split(str, '.');
        Assert.assertEquals(1, res.length);
        Assert.assertEquals("a", res[0]);

        str = "a b c";
        res = StringUtils.split(str, ' ');
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals("c", res[2]);
    }

    @Test
    public void testSplit_StringString_StringStringInt() {
        Assert.assertNull(StringUtils.split(null, "."));
        Assert.assertNull(StringUtils.split(null, ".", 3));

        Assert.assertEquals(0, StringUtils.split("", ".").length);
        Assert.assertEquals(0, StringUtils.split("", ".", 3).length);

        innerTestSplit('.', ".", ' ');
        innerTestSplit('.', ".", ',');
        innerTestSplit('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplit(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplit(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        String[] results;
        final String[] expectedResults = {"ab", "de fg"};
        results = StringUtils.split("ab   de fg", null, 2);
        Assert.assertEquals(expectedResults.length, results.length);
        for (int i = 0; i < expectedResults.length; i++) {
            Assert.assertEquals(expectedResults[i], results[i]);
        }

        final String[] expectedResults2 = {"ab", "cd:ef"};
        results = StringUtils.split("ab:cd:ef", ":", 2);
        Assert.assertEquals(expectedResults2.length, results.length);
        for (int i = 0; i < expectedResults2.length; i++) {
            Assert.assertEquals(expectedResults2[i], results[i]);
        }
    }

    private void innerTestSplit(final char separator, final String sepStr, final char noMatch) {
        final String msg = "Failed on separator hex(" + Integer.toHexString(separator) +
                "), noMatch hex(" + Integer.toHexString(noMatch) + "), sepStr(" + sepStr + ")";

        final String str = "a" + separator + "b" + separator + separator + noMatch + "c";
        String[] res;
        // (str, sepStr)
        res = StringUtils.split(str, sepStr);
        Assert.assertEquals(msg, 3, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, "b", res[1]);
        Assert.assertEquals(msg, noMatch + "c", res[2]);

        final String str2 = separator + "a" + separator;
        res = StringUtils.split(str2, sepStr);
        Assert.assertEquals(msg, 1, res.length);
        Assert.assertEquals(msg, "a", res[0]);

        res = StringUtils.split(str, sepStr, -1);
        Assert.assertEquals(msg, 3, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, "b", res[1]);
        Assert.assertEquals(msg, noMatch + "c", res[2]);

        res = StringUtils.split(str, sepStr, 0);
        Assert.assertEquals(msg, 3, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, "b", res[1]);
        Assert.assertEquals(msg, noMatch + "c", res[2]);

        res = StringUtils.split(str, sepStr, 1);
        Assert.assertEquals(msg, 1, res.length);
        Assert.assertEquals(msg, str, res[0]);

        res = StringUtils.split(str, sepStr, 2);
        Assert.assertEquals(msg, 2, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, str.substring(2), res[1]);
    }

    @Test
    public void testSplitByWholeString_StringStringBoolean() {
        Assert.assertArrayEquals(null, StringUtils.splitByWholeSeparator(null, "."));

        Assert.assertEquals(0, StringUtils.splitByWholeSeparator("", ".").length);

        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de", "fg"};

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null);
        Assert.assertEquals(splitOnNullExpectedResults.length, splitOnNullResults.length);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
            Assert.assertEquals(splitOnNullExpectedResults[i], splitOnNullResults[i]);
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiously"};
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy");
        Assert.assertEquals(splitOnStringExpectedResults.length, splitOnStringResults.length);
        for (int i = 0; i < splitOnStringExpectedResults.length; i += 1) {
            Assert.assertEquals(splitOnStringExpectedResults[i], splitOnStringResults[i]);
        }

        final String[] splitWithMultipleSeparatorExpectedResults = {"ab", "cd", "ef"};
        final String[] splitWithMultipleSeparator = StringUtils.splitByWholeSeparator("ab:cd::ef", ":");
        Assert.assertEquals(splitWithMultipleSeparatorExpectedResults.length, splitWithMultipleSeparator.length);
        for (int i = 0; i < splitWithMultipleSeparatorExpectedResults.length; i++) {
            Assert.assertEquals(splitWithMultipleSeparatorExpectedResults[i], splitWithMultipleSeparator[i]);
        }
    }

    @Test
    public void testSplitByWholeString_StringStringBooleanInt() {
        Assert.assertArrayEquals(null, StringUtils.splitByWholeSeparator(null, ".", 3));

        Assert.assertEquals(0, StringUtils.splitByWholeSeparator("", ".", 3).length);

        final String stringToSplitOnNulls = "ab   de fg";
        final String[] splitOnNullExpectedResults = {"ab", "de fg"};
        //String[] splitOnNullExpectedResults = { "ab", "de" } ;

        final String[] splitOnNullResults = StringUtils.splitByWholeSeparator(stringToSplitOnNulls, null, 2);
        Assert.assertEquals(splitOnNullExpectedResults.length, splitOnNullResults.length);
        for (int i = 0; i < splitOnNullExpectedResults.length; i += 1) {
            Assert.assertEquals(splitOnNullExpectedResults[i], splitOnNullResults[i]);
        }

        final String stringToSplitOnCharactersAndString = "abstemiouslyaeiouyabstemiouslyaeiouyabstemiously";

        final String[] splitOnStringExpectedResults = {"abstemiously", "abstemiouslyaeiouyabstemiously"};
        //String[] splitOnStringExpectedResults = { "abstemiously", "abstemiously" } ;
        final String[] splitOnStringResults = StringUtils.splitByWholeSeparator(stringToSplitOnCharactersAndString, "aeiouy", 2);
        Assert.assertEquals(splitOnStringExpectedResults.length, splitOnStringResults.length);
        for (int i = 0; i < splitOnStringExpectedResults.length; i++) {
            Assert.assertEquals(splitOnStringExpectedResults[i], splitOnStringResults[i]);
        }
    }

    @Test
    public void testSplitByWholeSeparatorPreserveAllTokens_StringStringInt() {
        Assert.assertArrayEquals(null, StringUtils.splitByWholeSeparatorPreserveAllTokens(null, ".", -1));

        Assert.assertEquals(0, StringUtils.splitByWholeSeparatorPreserveAllTokens("", ".", -1).length);

        // test whitespace
        String input = "ab   de fg";
        String[] expected = new String[]{"ab", "", "", "de", "fg"};

        String[] actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, null, -1);
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < actual.length; i += 1) {
            Assert.assertEquals(expected[i], actual[i]);
        }

        // test delimiter singlechar
        input = "1::2:::3::::4";
        expected = new String[]{"1", "", "2", "", "", "3", "", "", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", -1);
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < actual.length; i += 1) {
            Assert.assertEquals(expected[i], actual[i]);
        }

        // test delimiter multichar
        input = "1::2:::3::::4";
        expected = new String[]{"1", "2", ":3", "", "4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, "::", -1);
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < actual.length; i += 1) {
            Assert.assertEquals(expected[i], actual[i]);
        }

        // test delimiter char with max
        input = "1::2::3:4";
        expected = new String[]{"1", "", "2", ":3:4"};

        actual = StringUtils.splitByWholeSeparatorPreserveAllTokens(input, ":", 4);
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < actual.length; i += 1) {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void testSplitPreserveAllTokens_String() {
        Assert.assertNull(StringUtils.splitPreserveAllTokens(null));
        Assert.assertEquals(0, StringUtils.splitPreserveAllTokens("").length);

        String str = "abc def";
        String[] res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(2, res.length);
        Assert.assertEquals("abc", res[0]);
        Assert.assertEquals("def", res[1]);

        str = "abc  def";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("abc", res[0]);
        Assert.assertEquals("", res[1]);
        Assert.assertEquals("def", res[2]);

        str = " abc ";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("abc", res[1]);
        Assert.assertEquals("", res[2]);

        str = "a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals(".c", res[2]);

        str = " a b .c";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("a", res[1]);
        Assert.assertEquals("b", res[2]);
        Assert.assertEquals(".c", res[3]);

        str = "a  b  .c";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(5, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("", res[1]);
        Assert.assertEquals("b", res[2]);
        Assert.assertEquals("", res[3]);
        Assert.assertEquals(".c", res[4]);

        str = " a  ";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("a", res[1]);
        Assert.assertEquals("", res[2]);
        Assert.assertEquals("", res[3]);

        str = " a  b";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("a", res[1]);
        Assert.assertEquals("", res[2]);
        Assert.assertEquals("b", res[3]);

        str = "a" + WHITESPACE + "b" + NON_WHITESPACE + "c";
        res = StringUtils.splitPreserveAllTokens(str);
        Assert.assertEquals(WHITESPACE.length() + 1, res.length);
        Assert.assertEquals("a", res[0]);
        for (int i = 1; i < WHITESPACE.length() - 1; i++) {
            Assert.assertEquals("", res[i]);
        }
        Assert.assertEquals("b" + NON_WHITESPACE + "c", res[WHITESPACE.length()]);
    }

    @Test
    public void testSplitPreserveAllTokens_StringChar() {
        Assert.assertNull(StringUtils.splitPreserveAllTokens(null, '.'));
        Assert.assertEquals(0, StringUtils.splitPreserveAllTokens("", '.').length);

        String str = "a.b. c";
        String[] res = StringUtils.splitPreserveAllTokens(str, '.');
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals(" c", res[2]);

        str = "a.b.. c";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals("", res[2]);
        Assert.assertEquals(" c", res[3]);

        str = ".a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("a", res[1]);
        Assert.assertEquals("", res[2]);

        str = ".a..";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("a", res[1]);
        Assert.assertEquals("", res[2]);
        Assert.assertEquals("", res[3]);

        str = "..a.";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("", res[1]);
        Assert.assertEquals("a", res[2]);
        Assert.assertEquals("", res[3]);

        str = "..a";
        res = StringUtils.splitPreserveAllTokens(str, '.');
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("", res[1]);
        Assert.assertEquals("a", res[2]);

        str = "a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        Assert.assertEquals(3, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals("c", res[2]);

        str = "a  b  c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        Assert.assertEquals(5, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("", res[1]);
        Assert.assertEquals("b", res[2]);
        Assert.assertEquals("", res[3]);
        Assert.assertEquals("c", res[4]);

        str = " a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("a", res[1]);
        Assert.assertEquals("b", res[2]);
        Assert.assertEquals("c", res[3]);

        str = "  a b c";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        Assert.assertEquals(5, res.length);
        Assert.assertEquals("", res[0]);
        Assert.assertEquals("", res[1]);
        Assert.assertEquals("a", res[2]);
        Assert.assertEquals("b", res[3]);
        Assert.assertEquals("c", res[4]);

        str = "a b c ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        Assert.assertEquals(4, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals("c", res[2]);
        Assert.assertEquals("", res[3]);

        str = "a b c  ";
        res = StringUtils.splitPreserveAllTokens(str, ' ');
        Assert.assertEquals(5, res.length);
        Assert.assertEquals("a", res[0]);
        Assert.assertEquals("b", res[1]);
        Assert.assertEquals("c", res[2]);
        Assert.assertEquals("", res[3]);
        Assert.assertEquals("", res[3]);

        // Match example in javadoc
        {
            String[] results;
            final String[] expectedResults = {"a", "", "b", "c"};
            results = StringUtils.splitPreserveAllTokens("a..b.c", '.');
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }
    }

    @Test
    public void testSplitPreserveAllTokens_StringString_StringStringInt() {
        Assert.assertNull(StringUtils.splitPreserveAllTokens(null, "."));
        Assert.assertNull(StringUtils.splitPreserveAllTokens(null, ".", 3));

        Assert.assertEquals(0, StringUtils.splitPreserveAllTokens("", ".").length);
        Assert.assertEquals(0, StringUtils.splitPreserveAllTokens("", ".", 3).length);

        innerTestSplitPreserveAllTokens('.', ".", ' ');
        innerTestSplitPreserveAllTokens('.', ".", ',');
        innerTestSplitPreserveAllTokens('.', ".,", 'x');
        for (int i = 0; i < WHITESPACE.length(); i++) {
            for (int j = 0; j < NON_WHITESPACE.length(); j++) {
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), null, NON_WHITESPACE.charAt(j));
                innerTestSplitPreserveAllTokens(WHITESPACE.charAt(i), String.valueOf(WHITESPACE.charAt(i)), NON_WHITESPACE.charAt(j));
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab de fg", null, 2);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "  de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 2);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "::de:fg"};
            results = StringUtils.splitPreserveAllTokens("ab:::de:fg", ":", 2);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", " de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 3);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "de fg"};
            results = StringUtils.splitPreserveAllTokens("ab   de fg", null, 4);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            final String[] expectedResults = {"ab", "cd:ef"};
            String[] results;
            results = StringUtils.splitPreserveAllTokens("ab:cd:ef", ":", 2);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab::cd:ef", ":", 2);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", ":cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 3);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("ab:::cd:ef", ":", 4);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens(":ab:::cd:ef", ":", 5);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

        {
            String[] results;
            final String[] expectedResults = {"", "", "ab", "", "", "cd:ef"};
            results = StringUtils.splitPreserveAllTokens("::ab:::cd:ef", ":", 6);
            Assert.assertEquals(expectedResults.length, results.length);
            for (int i = 0; i < expectedResults.length; i++) {
                Assert.assertEquals(expectedResults[i], results[i]);
            }
        }

    }

    private void innerTestSplitPreserveAllTokens(final char separator, final String sepStr, final char noMatch) {
        final String msg = "Failed on separator hex(" + Integer.toHexString(separator) +
                "), noMatch hex(" + Integer.toHexString(noMatch) + "), sepStr(" + sepStr + ")";

        final String str = "a" + separator + "b" + separator + separator + noMatch + "c";
        String[] res;
        // (str, sepStr)
        res = StringUtils.splitPreserveAllTokens(str, sepStr);
        Assert.assertEquals(msg, 4, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, "b", res[1]);
        Assert.assertEquals(msg, "", res[2]);
        Assert.assertEquals(msg, noMatch + "c", res[3]);

        final String str2 = separator + "a" + separator;
        res = StringUtils.splitPreserveAllTokens(str2, sepStr);
        Assert.assertEquals(msg, 3, res.length);
        Assert.assertEquals(msg, "", res[0]);
        Assert.assertEquals(msg, "a", res[1]);
        Assert.assertEquals(msg, "", res[2]);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, -1);
        Assert.assertEquals(msg, 4, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, "b", res[1]);
        Assert.assertEquals(msg, "", res[2]);
        Assert.assertEquals(msg, noMatch + "c", res[3]);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, 0);
        Assert.assertEquals(msg, 4, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, "b", res[1]);
        Assert.assertEquals(msg, "", res[2]);
        Assert.assertEquals(msg, noMatch + "c", res[3]);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, 1);
        Assert.assertEquals(msg, 1, res.length);
        Assert.assertEquals(msg, str, res[0]);

        res = StringUtils.splitPreserveAllTokens(str, sepStr, 2);
        Assert.assertEquals(msg, 2, res.length);
        Assert.assertEquals(msg, "a", res[0]);
        Assert.assertEquals(msg, str.substring(2), res[1]);
    }

    @Test
    public void testSplitByCharacterType() {
        Assert.assertNull(StringUtils.splitByCharacterType(null));
        Assert.assertEquals(0, StringUtils.splitByCharacterType("").length);

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ab", " ", "de", " ",
                "fg"}, StringUtils.splitByCharacterType("ab de fg")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ab", "   ", "de", " ",
                "fg"}, StringUtils.splitByCharacterType("ab   de fg")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ab", ":", "cd", ":",
                "ef"}, StringUtils.splitByCharacterType("ab:cd:ef")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"number", "5"},
                StringUtils.splitByCharacterType("number5")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"foo", "B", "ar"},
                StringUtils.splitByCharacterType("fooBar")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"foo", "200", "B", "ar"},
                StringUtils.splitByCharacterType("foo200Bar")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ASFR", "ules"},
                StringUtils.splitByCharacterType("ASFRules")));
    }

    @Test
    public void testSplitByCharacterTypeCamelCase() {
        Assert.assertNull(StringUtils.splitByCharacterTypeCamelCase(null));
        Assert.assertEquals(0, StringUtils.splitByCharacterTypeCamelCase("").length);

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ab", " ", "de", " ",
                "fg"}, StringUtils.splitByCharacterTypeCamelCase("ab de fg")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ab", "   ", "de", " ",
                "fg"}, StringUtils.splitByCharacterTypeCamelCase("ab   de fg")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ab", ":", "cd", ":",
                "ef"}, StringUtils.splitByCharacterTypeCamelCase("ab:cd:ef")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"number", "5"},
                StringUtils.splitByCharacterTypeCamelCase("number5")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"foo", "Bar"},
                StringUtils.splitByCharacterTypeCamelCase("fooBar")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"foo", "200", "Bar"},
                StringUtils.splitByCharacterTypeCamelCase("foo200Bar")));

        Assert.assertTrue(ArrayUtils.isEquals(new String[]{"ASF", "Rules"},
                StringUtils.splitByCharacterTypeCamelCase("ASFRules")));
    }

    @Test
    public void testDeleteWhitespace_String() {
        Assert.assertNull(StringUtils.deleteWhitespace(null));
        Assert.assertEquals("", StringUtils.deleteWhitespace(""));
        Assert.assertEquals("", StringUtils.deleteWhitespace("  \u000C  \t\t\u001F\n\n \u000B  "));
        Assert.assertEquals("", StringUtils.deleteWhitespace(StringUtilsTest.WHITESPACE));
        Assert.assertEquals(StringUtilsTest.NON_WHITESPACE, StringUtils.deleteWhitespace(StringUtilsTest.NON_WHITESPACE));
        // Note: u-2007 and u-000A both cause problems in the source code
        // it should ignore 2007 but delete 000A
        Assert.assertEquals("\u00A0\u202F", StringUtils.deleteWhitespace("  \u00A0  \t\t\n\n \u202F  "));
        Assert.assertEquals("\u00A0\u202F", StringUtils.deleteWhitespace("\u00A0\u202F"));
        Assert.assertEquals("test", StringUtils.deleteWhitespace("\u000Bt  \t\n\u0009e\rs\n\n   \tt"));
    }

    @Test
    public void testLang623() {
        Assert.assertEquals("t", StringUtils.replaceChars("\u00DE", '\u00DE', 't'));
        Assert.assertEquals("t", StringUtils.replaceChars("\u00FE", '\u00FE', 't'));
    }

    @Test
    public void testReplace_StringStringString() {
        Assert.assertNull(StringUtils.replace(null, null, null));
        Assert.assertNull(StringUtils.replace(null, null, "any"));
        Assert.assertNull(StringUtils.replace(null, "any", null));
        Assert.assertNull(StringUtils.replace(null, "any", "any"));

        Assert.assertEquals("", StringUtils.replace("", null, null));
        Assert.assertEquals("", StringUtils.replace("", null, "any"));
        Assert.assertEquals("", StringUtils.replace("", "any", null));
        Assert.assertEquals("", StringUtils.replace("", "any", "any"));

        Assert.assertEquals("FOO", StringUtils.replace("FOO", "", "any"));
        Assert.assertEquals("FOO", StringUtils.replace("FOO", null, "any"));
        Assert.assertEquals("FOO", StringUtils.replace("FOO", "F", null));
        Assert.assertEquals("FOO", StringUtils.replace("FOO", null, null));

        Assert.assertEquals("", StringUtils.replace("foofoofoo", "foo", ""));
        Assert.assertEquals("barbarbar", StringUtils.replace("foofoofoo", "foo", "bar"));
        Assert.assertEquals("farfarfar", StringUtils.replace("foofoofoo", "oo", "ar"));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringString() {
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, null, null));
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, null, "any"));
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", null));
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", "any"));

        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", null, null));
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", null, "any"));
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", "any", null));
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", "any", "any"));

        Assert.assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", "", "any"));
        Assert.assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", null, "any"));
        Assert.assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", "F", null));
        Assert.assertEquals("FOO", StringUtils.replaceIgnoreCase("FOO", null, null));

        Assert.assertEquals("", StringUtils.replaceIgnoreCase("foofoofoo", "foo", ""));
        Assert.assertEquals("barbarbar", StringUtils.replaceIgnoreCase("foofoofoo", "foo", "bar"));
        Assert.assertEquals("farfarfar", StringUtils.replaceIgnoreCase("foofoofoo", "oo", "ar"));

        // IgnoreCase
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("foofoofoo", "FOO", ""));
        Assert.assertEquals("barbarbar", StringUtils.replaceIgnoreCase("fooFOOfoo", "foo", "bar"));
        Assert.assertEquals("farfarfar", StringUtils.replaceIgnoreCase("foofOOfoo", "OO", "ar"));
    }

    @Test
    public void testReplacePattern() {
        Assert.assertNull(StringUtils.replacePattern(null, "", ""));
        Assert.assertEquals("any", StringUtils.replacePattern("any", null, ""));
        Assert.assertEquals("any", StringUtils.replacePattern("any", "", null));

        Assert.assertEquals("zzz", StringUtils.replacePattern("", "", "zzz"));
        Assert.assertEquals("zzz", StringUtils.replacePattern("", ".*", "zzz"));
        Assert.assertEquals("", StringUtils.replacePattern("", ".+", "zzz"));

        Assert.assertEquals("z", StringUtils.replacePattern("<__>\n<__>", "<.*>", "z"));
        Assert.assertEquals("z", StringUtils.replacePattern("<__>\\n<__>", "<.*>", "z"));
        Assert.assertEquals("X", StringUtils.replacePattern("<A>\nxy\n</A>", "<A>.*</A>", "X"));

        Assert.assertEquals("ABC___123", StringUtils.replacePattern("ABCabc123", "[a-z]", "_"));
        Assert.assertEquals("ABC_123", StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", "_"));
        Assert.assertEquals("ABC123", StringUtils.replacePattern("ABCabc123", "[^A-Z0-9]+", ""));
        Assert.assertEquals("Lorem_ipsum_dolor_sit",
                     StringUtils.replacePattern("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));
    }

    @Test
    public void testRemovePattern() {
        Assert.assertNull(StringUtils.removePattern(null, ""));
        Assert.assertEquals("any", StringUtils.removePattern("any", null));

        Assert.assertEquals("", StringUtils.removePattern("", ""));
        Assert.assertEquals("", StringUtils.removePattern("", ".*"));
        Assert.assertEquals("", StringUtils.removePattern("", ".+"));

        Assert.assertEquals("AB", StringUtils.removePattern("A<__>\n<__>B", "<.*>"));
        Assert.assertEquals("AB", StringUtils.removePattern("A<__>\\n<__>B", "<.*>"));
        Assert.assertEquals("", StringUtils.removePattern("<A>x\\ny</A>", "<A>.*</A>"));
        Assert.assertEquals("", StringUtils.removePattern("<A>\nxy\n</A>", "<A>.*</A>"));

        Assert.assertEquals("ABC123", StringUtils.removePattern("ABCabc123", "[a-z]"));
    }

    @Test
    public void testReplaceAll() {
        Assert.assertNull(StringUtils.replaceAll(null, "", ""));

        Assert.assertEquals("any", StringUtils.replaceAll("any", null, ""));
        Assert.assertEquals("any", StringUtils.replaceAll("any", "", null));

        Assert.assertEquals("zzz", StringUtils.replaceAll("", "", "zzz"));
        Assert.assertEquals("zzz", StringUtils.replaceAll("", ".*", "zzz"));
        Assert.assertEquals("", StringUtils.replaceAll("", ".+", "zzz"));
        Assert.assertEquals("ZZaZZbZZcZZ", StringUtils.replaceAll("abc", "", "ZZ"));

        Assert.assertEquals("z\nz", StringUtils.replaceAll("<__>\n<__>", "<.*>", "z"));
        Assert.assertEquals("z", StringUtils.replaceAll("<__>\n<__>", "(?s)<.*>", "z"));

        Assert.assertEquals("ABC___123", StringUtils.replaceAll("ABCabc123", "[a-z]", "_"));
        Assert.assertEquals("ABC_123", StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", "_"));
        Assert.assertEquals("ABC123", StringUtils.replaceAll("ABCabc123", "[^A-Z0-9]+", ""));
        Assert.assertEquals("Lorem_ipsum_dolor_sit",
                     StringUtils.replaceAll("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));

        try {
            StringUtils.replaceAll("any", "{badRegexSyntax}", "");
            Assert.fail("StringUtils.replaceAll expecting PatternSyntaxException");
        } catch (final PatternSyntaxException ex) {
            // empty
        }
    }

    @Test
    public void testReplaceFirst() {
        Assert.assertNull(StringUtils.replaceFirst(null, "", ""));

        Assert.assertEquals("any", StringUtils.replaceFirst("any", null, ""));
        Assert.assertEquals("any", StringUtils.replaceFirst("any", "", null));

        Assert.assertEquals("zzz", StringUtils.replaceFirst("", "", "zzz"));
        Assert.assertEquals("zzz", StringUtils.replaceFirst("", ".*", "zzz"));
        Assert.assertEquals("", StringUtils.replaceFirst("", ".+", "zzz"));
        Assert.assertEquals("ZZabc", StringUtils.replaceFirst("abc", "", "ZZ"));

        Assert.assertEquals("z\n<__>", StringUtils.replaceFirst("<__>\n<__>", "<.*>", "z"));
        Assert.assertEquals("z", StringUtils.replaceFirst("<__>\n<__>", "(?s)<.*>", "z"));

        Assert.assertEquals("ABC_bc123", StringUtils.replaceFirst("ABCabc123", "[a-z]", "_"));
        Assert.assertEquals("ABC_123abc", StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", "_"));
        Assert.assertEquals("ABC123abc", StringUtils.replaceFirst("ABCabc123abc", "[^A-Z0-9]+", ""));
        Assert.assertEquals("Lorem_ipsum  dolor   sit",
                     StringUtils.replaceFirst("Lorem ipsum  dolor   sit", "( +)([a-z]+)", "_$2"));

        try {
            StringUtils.replaceFirst("any", "{badRegexSyntax}", "");
            Assert.fail("StringUtils.replaceFirst expecting PatternSyntaxException");
        } catch (final PatternSyntaxException ex) {
            // empty
        }
    }

    @Test
    public void testReplace_StringStringStringInt() {
        Assert.assertNull(StringUtils.replace(null, null, null, 2));
        Assert.assertNull(StringUtils.replace(null, null, "any", 2));
        Assert.assertNull(StringUtils.replace(null, "any", null, 2));
        Assert.assertNull(StringUtils.replace(null, "any", "any", 2));

        Assert.assertEquals("", StringUtils.replace("", null, null, 2));
        Assert.assertEquals("", StringUtils.replace("", null, "any", 2));
        Assert.assertEquals("", StringUtils.replace("", "any", null, 2));
        Assert.assertEquals("", StringUtils.replace("", "any", "any", 2));

        final String str = new String(new char[]{'o', 'o', 'f', 'o', 'o'});
        Assert.assertSame(str, StringUtils.replace(str, "x", "", -1));

        Assert.assertEquals("f", StringUtils.replace("oofoo", "o", "", -1));
        Assert.assertEquals("oofoo", StringUtils.replace("oofoo", "o", "", 0));
        Assert.assertEquals("ofoo", StringUtils.replace("oofoo", "o", "", 1));
        Assert.assertEquals("foo", StringUtils.replace("oofoo", "o", "", 2));
        Assert.assertEquals("fo", StringUtils.replace("oofoo", "o", "", 3));
        Assert.assertEquals("f", StringUtils.replace("oofoo", "o", "", 4));

        Assert.assertEquals("f", StringUtils.replace("oofoo", "o", "", -5));
        Assert.assertEquals("f", StringUtils.replace("oofoo", "o", "", 1000));
    }

    @Test
    public void testReplaceIgnoreCase_StringStringStringInt() {
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, null, null, 2));
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, null, "any", 2));
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", null, 2));
        Assert.assertEquals(null, StringUtils.replaceIgnoreCase(null, "any", "any", 2));

        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", null, null, 2));
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", null, "any", 2));
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", "any", null, 2));
        Assert.assertEquals("", StringUtils.replaceIgnoreCase("", "any", "any", 2));

        String str = new String(new char[] { 'o', 'o', 'f', 'o', 'o' });
        Assert.assertSame(str, StringUtils.replaceIgnoreCase(str, "x", "", -1));

        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", -1));
        Assert.assertEquals("oofoo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 0));
        Assert.assertEquals("ofoo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 1));
        Assert.assertEquals("foo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 2));
        Assert.assertEquals("fo", StringUtils.replaceIgnoreCase("oofoo", "o", "", 3));
        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", 4));

        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", -5));
        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "o", "", 1000));

        // IgnoreCase
        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", -1));
        Assert.assertEquals("oofoo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 0));
        Assert.assertEquals("ofoo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 1));
        Assert.assertEquals("foo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 2));
        Assert.assertEquals("fo", StringUtils.replaceIgnoreCase("oofoo", "O", "", 3));
        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", 4));

        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", -5));
        Assert.assertEquals("f", StringUtils.replaceIgnoreCase("oofoo", "O", "", 1000));
    }

    @Test
    public void testReplaceOnce_StringStringString() {
        Assert.assertNull(StringUtils.replaceOnce(null, null, null));
        Assert.assertNull(StringUtils.replaceOnce(null, null, "any"));
        Assert.assertNull(StringUtils.replaceOnce(null, "any", null));
        Assert.assertNull(StringUtils.replaceOnce(null, "any", "any"));

        Assert.assertEquals("", StringUtils.replaceOnce("", null, null));
        Assert.assertEquals("", StringUtils.replaceOnce("", null, "any"));
        Assert.assertEquals("", StringUtils.replaceOnce("", "any", null));
        Assert.assertEquals("", StringUtils.replaceOnce("", "any", "any"));

        Assert.assertEquals("FOO", StringUtils.replaceOnce("FOO", "", "any"));
        Assert.assertEquals("FOO", StringUtils.replaceOnce("FOO", null, "any"));
        Assert.assertEquals("FOO", StringUtils.replaceOnce("FOO", "F", null));
        Assert.assertEquals("FOO", StringUtils.replaceOnce("FOO", null, null));

        Assert.assertEquals("foofoo", StringUtils.replaceOnce("foofoofoo", "foo", ""));
    }

    @Test
    public void testReplaceOnceIgnoreCase_StringStringString() {
        Assert.assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, null, null));
        Assert.assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, null, "any"));
        Assert.assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, "any", null));
        Assert.assertEquals(null, StringUtils.replaceOnceIgnoreCase(null, "any", "any"));

        Assert.assertEquals("", StringUtils.replaceOnceIgnoreCase("", null, null));
        Assert.assertEquals("", StringUtils.replaceOnceIgnoreCase("", null, "any"));
        Assert.assertEquals("", StringUtils.replaceOnceIgnoreCase("", "any", null));
        Assert.assertEquals("", StringUtils.replaceOnceIgnoreCase("", "any", "any"));

        Assert.assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", "", "any"));
        Assert.assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", null, "any"));
        Assert.assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", "F", null));
        Assert.assertEquals("FOO", StringUtils.replaceOnceIgnoreCase("FOO", null, null));

        Assert.assertEquals("foofoo", StringUtils.replaceOnceIgnoreCase("foofoofoo", "foo", ""));

        // Ignore Case
        Assert.assertEquals("Foofoo", StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", ""));
    }

    /**
     * Test method for 'StringUtils.replaceEach(String, String[], String[])'
     */
    @Test
    public void testReplace_StringStringArrayStringArray() {
        //JAVADOC TESTS START
        Assert.assertNull(StringUtils.replaceEach(null, new String[]{"a"}, new String[]{"b"}));
        Assert.assertEquals(StringUtils.replaceEach("", new String[]{"a"}, new String[]{"b"}), "");
        Assert.assertEquals(StringUtils.replaceEach("aba", null, null), "aba");
        Assert.assertEquals(StringUtils.replaceEach("aba", new String[0], null), "aba");
        Assert.assertEquals(StringUtils.replaceEach("aba", null, new String[0]), "aba");
        Assert.assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, null), "aba");

        Assert.assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{""}), "b");
        Assert.assertEquals(StringUtils.replaceEach("aba", new String[]{null}, new String[]{"a"}), "aba");
        Assert.assertEquals(StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}), "wcte");
        Assert.assertEquals(StringUtils.replaceEach("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}), "dcte");
        //JAVADOC TESTS END

        Assert.assertEquals("bcc", StringUtils.replaceEach("abc", new String[]{"a", "b"}, new String[]{"b", "c"}));
        Assert.assertEquals("q651.506bera", StringUtils.replaceEach("d216.102oren",
                new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                        "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
                        "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                        "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
                new String[]{"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a",
                        "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "N", "O", "P", "Q",
                        "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "A", "B", "C", "D", "E", "F", "G",
                        "H", "I", "J", "K", "L", "M", "5", "6", "7", "8", "9", "1", "2", "3", "4"}));

        // Test null safety inside arrays - LANG-552
        Assert.assertEquals(StringUtils.replaceEach("aba", new String[]{"a"}, new String[]{null}), "aba");
        Assert.assertEquals(StringUtils.replaceEach("aba", new String[]{"a", "b"}, new String[]{"c", null}), "cbc");

        try {
            StringUtils.replaceEach("abba", new String[]{"a"}, new String[]{"b", "a"});
            Assert.fail("StringUtils.replaceEach(String, String[], String[]) expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            // expected
        }
    }

    /**
     * Test method for 'StringUtils.replaceEachRepeatedly(String, String[], String[])'
     */
    @Test
    public void testReplace_StringStringArrayStringArrayBoolean() {
        //JAVADOC TESTS START
        Assert.assertNull(StringUtils.replaceEachRepeatedly(null, new String[]{"a"}, new String[]{"b"}));
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("", new String[]{"a"}, new String[]{"b"}), "");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("aba", null, null), "aba");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[0], null), "aba");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("aba", null, new String[0]), "aba");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[0], null), "aba");

        Assert.assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[]{"a"}, new String[]{""}), "b");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("aba", new String[]{null}, new String[]{"a"}), "aba");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"w", "t"}), "wcte");
        Assert.assertEquals(StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "t"}), "tcte");

        try {
            StringUtils.replaceEachRepeatedly("abcde", new String[]{"ab", "d"}, new String[]{"d", "ab"});
            Assert.fail("Should be a circular reference");
        } catch (final IllegalStateException e) {
        }

        //JAVADOC TESTS END
    }

    @Test
    public void testReplaceChars_StringCharChar() {
        Assert.assertNull(StringUtils.replaceChars(null, 'b', 'z'));
        Assert.assertEquals("", StringUtils.replaceChars("", 'b', 'z'));
        Assert.assertEquals("azcza", StringUtils.replaceChars("abcba", 'b', 'z'));
        Assert.assertEquals("abcba", StringUtils.replaceChars("abcba", 'x', 'z'));
    }

    @Test
    public void testReplaceChars_StringStringString() {
        Assert.assertNull(StringUtils.replaceChars(null, null, null));
        Assert.assertNull(StringUtils.replaceChars(null, "", null));
        Assert.assertNull(StringUtils.replaceChars(null, "a", null));
        Assert.assertNull(StringUtils.replaceChars(null, null, ""));
        Assert.assertNull(StringUtils.replaceChars(null, null, "x"));

        Assert.assertEquals("", StringUtils.replaceChars("", null, null));
        Assert.assertEquals("", StringUtils.replaceChars("", "", null));
        Assert.assertEquals("", StringUtils.replaceChars("", "a", null));
        Assert.assertEquals("", StringUtils.replaceChars("", null, ""));
        Assert.assertEquals("", StringUtils.replaceChars("", null, "x"));

        Assert.assertEquals("abc", StringUtils.replaceChars("abc", null, null));
        Assert.assertEquals("abc", StringUtils.replaceChars("abc", null, ""));
        Assert.assertEquals("abc", StringUtils.replaceChars("abc", null, "x"));

        Assert.assertEquals("abc", StringUtils.replaceChars("abc", "", null));
        Assert.assertEquals("abc", StringUtils.replaceChars("abc", "", ""));
        Assert.assertEquals("abc", StringUtils.replaceChars("abc", "", "x"));

        Assert.assertEquals("ac", StringUtils.replaceChars("abc", "b", null));
        Assert.assertEquals("ac", StringUtils.replaceChars("abc", "b", ""));
        Assert.assertEquals("axc", StringUtils.replaceChars("abc", "b", "x"));

        Assert.assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yz"));
        Assert.assertEquals("ayya", StringUtils.replaceChars("abcba", "bc", "y"));
        Assert.assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yzx"));

        Assert.assertEquals("abcba", StringUtils.replaceChars("abcba", "z", "w"));
        Assert.assertSame("abcba", StringUtils.replaceChars("abcba", "z", "w"));

        // Javadoc examples:
        Assert.assertEquals("jelly", StringUtils.replaceChars("hello", "ho", "jy"));
        Assert.assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yz"));
        Assert.assertEquals("ayya", StringUtils.replaceChars("abcba", "bc", "y"));
        Assert.assertEquals("ayzya", StringUtils.replaceChars("abcba", "bc", "yzx"));

        // From http://issues.apache.org/bugzilla/show_bug.cgi?id=25454
        Assert.assertEquals("bcc", StringUtils.replaceChars("abc", "ab", "bc"));
        Assert.assertEquals("q651.506bera", StringUtils.replaceChars("d216.102oren",
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789",
                "nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM567891234"));
    }

    @Test
    public void testOverlay_StringStringIntInt() {
        Assert.assertNull(StringUtils.overlay(null, null, 2, 4));
        Assert.assertNull(StringUtils.overlay(null, null, -2, -4));

        Assert.assertEquals("", StringUtils.overlay("", null, 0, 0));
        Assert.assertEquals("", StringUtils.overlay("", "", 0, 0));
        Assert.assertEquals("zzzz", StringUtils.overlay("", "zzzz", 0, 0));
        Assert.assertEquals("zzzz", StringUtils.overlay("", "zzzz", 2, 4));
        Assert.assertEquals("zzzz", StringUtils.overlay("", "zzzz", -2, -4));

        Assert.assertEquals("abef", StringUtils.overlay("abcdef", null, 2, 4));
        Assert.assertEquals("abef", StringUtils.overlay("abcdef", null, 4, 2));
        Assert.assertEquals("abef", StringUtils.overlay("abcdef", "", 2, 4));
        Assert.assertEquals("abef", StringUtils.overlay("abcdef", "", 4, 2));
        Assert.assertEquals("abzzzzef", StringUtils.overlay("abcdef", "zzzz", 2, 4));
        Assert.assertEquals("abzzzzef", StringUtils.overlay("abcdef", "zzzz", 4, 2));

        Assert.assertEquals("zzzzef", StringUtils.overlay("abcdef", "zzzz", -1, 4));
        Assert.assertEquals("zzzzef", StringUtils.overlay("abcdef", "zzzz", 4, -1));
        Assert.assertEquals("zzzzabcdef", StringUtils.overlay("abcdef", "zzzz", -2, -1));
        Assert.assertEquals("zzzzabcdef", StringUtils.overlay("abcdef", "zzzz", -1, -2));
        Assert.assertEquals("abcdzzzz", StringUtils.overlay("abcdef", "zzzz", 4, 10));
        Assert.assertEquals("abcdzzzz", StringUtils.overlay("abcdef", "zzzz", 10, 4));
        Assert.assertEquals("abcdefzzzz", StringUtils.overlay("abcdef", "zzzz", 8, 10));
        Assert.assertEquals("abcdefzzzz", StringUtils.overlay("abcdef", "zzzz", 10, 8));
    }

    @Test
    public void testRepeat_StringInt() {
        Assert.assertNull(StringUtils.repeat(null, 2));
        Assert.assertEquals("", StringUtils.repeat("ab", 0));
        Assert.assertEquals("", StringUtils.repeat("", 3));
        Assert.assertEquals("aaa", StringUtils.repeat("a", 3));
        Assert.assertEquals("", StringUtils.repeat("a", -2));
        Assert.assertEquals("ababab", StringUtils.repeat("ab", 3));
        Assert.assertEquals("abcabcabc", StringUtils.repeat("abc", 3));
        final String str = StringUtils.repeat("a", 10000);  // bigger than pad limit
        Assert.assertEquals(10000, str.length());
        Assert.assertTrue(StringUtils.containsOnly(str, new char[]{'a'}));
    }

    @Test
    public void testRepeat_StringStringInt() {
        Assert.assertNull(StringUtils.repeat(null, null, 2));
        Assert.assertNull(StringUtils.repeat(null, "x", 2));
        Assert.assertEquals("", StringUtils.repeat("", null, 2));

        Assert.assertEquals("", StringUtils.repeat("ab", "", 0));
        Assert.assertEquals("", StringUtils.repeat("", "", 2));

        Assert.assertEquals("xx", StringUtils.repeat("", "x", 3));

        Assert.assertEquals("?, ?, ?", StringUtils.repeat("?", ", ", 3));
    }

    @Test
    public void testRepeat_CharInt() {
        Assert.assertEquals("zzz", StringUtils.repeat('z', 3));
        Assert.assertEquals("", StringUtils.repeat('z', 0));
        Assert.assertEquals("", StringUtils.repeat('z', -2));
    }

    @Test
    public void testChop() {

        final String[][] chopCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {"foo", "fo"},
                {"foo\nfoo", "foo\nfo"},
                {"\n", ""},
                {"\r", ""},
                {"\r\n", ""},
                {null, null},
                {"", ""},
                {"a", ""},
        };
        for (final String[] chopCase : chopCases) {
            final String original = chopCase[0];
            final String expectedResult = chopCase[1];
            Assert.assertEquals("chop(String) failed",
                    expectedResult, StringUtils.chop(original));
        }
    }

    @Test
    public void testChomp() {

        final String[][] chompCases = {
                {FOO_UNCAP + "\r\n", FOO_UNCAP},
                {FOO_UNCAP + "\n", FOO_UNCAP},
                {FOO_UNCAP + "\r", FOO_UNCAP},
                {FOO_UNCAP + " \r", FOO_UNCAP + " "},
                {FOO_UNCAP, FOO_UNCAP},
                {FOO_UNCAP + "\n\n", FOO_UNCAP + "\n"},
                {FOO_UNCAP + "\r\n\r\n", FOO_UNCAP + "\r\n"},
                {"foo\nfoo", "foo\nfoo"},
                {"foo\n\rfoo", "foo\n\rfoo"},
                {"\n", ""},
                {"\r", ""},
                {"a", "a"},
                {"\r\n", ""},
                {"", ""},
                {null, null},
                {FOO_UNCAP + "\n\r", FOO_UNCAP + "\n"}
        };
        for (final String[] chompCase : chompCases) {
            final String original = chompCase[0];
            final String expectedResult = chompCase[1];
            Assert.assertEquals("chomp(String) failed",
                    expectedResult, StringUtils.chomp(original));
        }

        Assert.assertEquals("chomp(String, String) failed",
                "foo", StringUtils.chomp("foobar", "bar"));
        Assert.assertEquals("chomp(String, String) failed",
                "foobar", StringUtils.chomp("foobar", "baz"));
        Assert.assertEquals("chomp(String, String) failed",
                "foo", StringUtils.chomp("foo", "foooo"));
        Assert.assertEquals("chomp(String, String) failed",
                "foobar", StringUtils.chomp("foobar", ""));
        Assert.assertEquals("chomp(String, String) failed",
                "foobar", StringUtils.chomp("foobar", null));
        Assert.assertEquals("chomp(String, String) failed",
                "", StringUtils.chomp("", "foo"));
        Assert.assertEquals("chomp(String, String) failed",
                "", StringUtils.chomp("", null));
        Assert.assertEquals("chomp(String, String) failed",
                "", StringUtils.chomp("", ""));
        Assert.assertEquals("chomp(String, String) failed",
                null, StringUtils.chomp(null, "foo"));
        Assert.assertEquals("chomp(String, String) failed",
                null, StringUtils.chomp(null, null));
        Assert.assertEquals("chomp(String, String) failed",
                null, StringUtils.chomp(null, ""));
        Assert.assertEquals("chomp(String, String) failed",
                "", StringUtils.chomp("foo", "foo"));
        Assert.assertEquals("chomp(String, String) failed",
                " ", StringUtils.chomp(" foo", "foo"));
        Assert.assertEquals("chomp(String, String) failed",
                "foo ", StringUtils.chomp("foo ", "foo"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testRightPad_StringInt() {
        Assert.assertNull(StringUtils.rightPad(null, 5));
        Assert.assertEquals("     ", StringUtils.rightPad("", 5));
        Assert.assertEquals("abc  ", StringUtils.rightPad("abc", 5));
        Assert.assertEquals("abc", StringUtils.rightPad("abc", 2));
        Assert.assertEquals("abc", StringUtils.rightPad("abc", -1));
    }

    @Test
    public void testRightPad_StringIntChar() {
        Assert.assertNull(StringUtils.rightPad(null, 5, ' '));
        Assert.assertEquals("     ", StringUtils.rightPad("", 5, ' '));
        Assert.assertEquals("abc  ", StringUtils.rightPad("abc", 5, ' '));
        Assert.assertEquals("abc", StringUtils.rightPad("abc", 2, ' '));
        Assert.assertEquals("abc", StringUtils.rightPad("abc", -1, ' '));
        Assert.assertEquals("abcxx", StringUtils.rightPad("abc", 5, 'x'));
        final String str = StringUtils.rightPad("aaa", 10000, 'a');  // bigger than pad length
        Assert.assertEquals(10000, str.length());
        Assert.assertTrue(StringUtils.containsOnly(str, new char[]{'a'}));
    }

    @Test
    public void testRightPad_StringIntString() {
        Assert.assertNull(StringUtils.rightPad(null, 5, "-+"));
        Assert.assertEquals("     ", StringUtils.rightPad("", 5, " "));
        Assert.assertNull(StringUtils.rightPad(null, 8, null));
        Assert.assertEquals("abc-+-+", StringUtils.rightPad("abc", 7, "-+"));
        Assert.assertEquals("abc-+~", StringUtils.rightPad("abc", 6, "-+~"));
        Assert.assertEquals("abc-+", StringUtils.rightPad("abc", 5, "-+~"));
        Assert.assertEquals("abc", StringUtils.rightPad("abc", 2, " "));
        Assert.assertEquals("abc", StringUtils.rightPad("abc", -1, " "));
        Assert.assertEquals("abc  ", StringUtils.rightPad("abc", 5, null));
        Assert.assertEquals("abc  ", StringUtils.rightPad("abc", 5, ""));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testLeftPad_StringInt() {
        Assert.assertNull(StringUtils.leftPad(null, 5));
        Assert.assertEquals("     ", StringUtils.leftPad("", 5));
        Assert.assertEquals("  abc", StringUtils.leftPad("abc", 5));
        Assert.assertEquals("abc", StringUtils.leftPad("abc", 2));
    }

    @Test
    public void testLeftPad_StringIntChar() {
        Assert.assertNull(StringUtils.leftPad(null, 5, ' '));
        Assert.assertEquals("     ", StringUtils.leftPad("", 5, ' '));
        Assert.assertEquals("  abc", StringUtils.leftPad("abc", 5, ' '));
        Assert.assertEquals("xxabc", StringUtils.leftPad("abc", 5, 'x'));
        Assert.assertEquals("\uffff\uffffabc", StringUtils.leftPad("abc", 5, '\uffff'));
        Assert.assertEquals("abc", StringUtils.leftPad("abc", 2, ' '));
        final String str = StringUtils.leftPad("aaa", 10000, 'a');  // bigger than pad length
        Assert.assertEquals(10000, str.length());
        Assert.assertTrue(StringUtils.containsOnly(str, new char[]{'a'}));
    }

    @Test
    public void testLeftPad_StringIntString() {
        Assert.assertNull(StringUtils.leftPad(null, 5, "-+"));
        Assert.assertNull(StringUtils.leftPad(null, 5, null));
        Assert.assertEquals("     ", StringUtils.leftPad("", 5, " "));
        Assert.assertEquals("-+-+abc", StringUtils.leftPad("abc", 7, "-+"));
        Assert.assertEquals("-+~abc", StringUtils.leftPad("abc", 6, "-+~"));
        Assert.assertEquals("-+abc", StringUtils.leftPad("abc", 5, "-+~"));
        Assert.assertEquals("abc", StringUtils.leftPad("abc", 2, " "));
        Assert.assertEquals("abc", StringUtils.leftPad("abc", -1, " "));
        Assert.assertEquals("  abc", StringUtils.leftPad("abc", 5, null));
        Assert.assertEquals("  abc", StringUtils.leftPad("abc", 5, ""));
    }

    @Test
    public void testLengthString() {
        Assert.assertEquals(0, StringUtils.length(null));
        Assert.assertEquals(0, StringUtils.length(""));
        Assert.assertEquals(0, StringUtils.length(StringUtils.EMPTY));
        Assert.assertEquals(1, StringUtils.length("A"));
        Assert.assertEquals(1, StringUtils.length(" "));
        Assert.assertEquals(8, StringUtils.length("ABCDEFGH"));
    }

    @Test
    public void testLengthStringBuffer() {
        Assert.assertEquals(0, StringUtils.length(new StringBuffer("")));
        Assert.assertEquals(0, StringUtils.length(new StringBuffer(StringUtils.EMPTY)));
        Assert.assertEquals(1, StringUtils.length(new StringBuffer("A")));
        Assert.assertEquals(1, StringUtils.length(new StringBuffer(" ")));
        Assert.assertEquals(8, StringUtils.length(new StringBuffer("ABCDEFGH")));
    }

    @Test
    public void testLengthStringBuilder() {
        Assert.assertEquals(0, StringUtils.length(new StringBuilder("")));
        Assert.assertEquals(0, StringUtils.length(new StringBuilder(StringUtils.EMPTY)));
        Assert.assertEquals(1, StringUtils.length(new StringBuilder("A")));
        Assert.assertEquals(1, StringUtils.length(new StringBuilder(" ")));
        Assert.assertEquals(8, StringUtils.length(new StringBuilder("ABCDEFGH")));
    }

    @Test
    public void testLength_CharBuffer() {
        Assert.assertEquals(0, StringUtils.length(CharBuffer.wrap("")));
        Assert.assertEquals(1, StringUtils.length(CharBuffer.wrap("A")));
        Assert.assertEquals(1, StringUtils.length(CharBuffer.wrap(" ")));
        Assert.assertEquals(8, StringUtils.length(CharBuffer.wrap("ABCDEFGH")));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCenter_StringInt() {
        Assert.assertNull(StringUtils.center(null, -1));
        Assert.assertNull(StringUtils.center(null, 4));
        Assert.assertEquals("    ", StringUtils.center("", 4));
        Assert.assertEquals("ab", StringUtils.center("ab", 0));
        Assert.assertEquals("ab", StringUtils.center("ab", -1));
        Assert.assertEquals("ab", StringUtils.center("ab", 1));
        Assert.assertEquals("    ", StringUtils.center("", 4));
        Assert.assertEquals(" ab ", StringUtils.center("ab", 4));
        Assert.assertEquals("abcd", StringUtils.center("abcd", 2));
        Assert.assertEquals(" a  ", StringUtils.center("a", 4));
        Assert.assertEquals("  a  ", StringUtils.center("a", 5));
    }

    @Test
    public void testCenter_StringIntChar() {
        Assert.assertNull(StringUtils.center(null, -1, ' '));
        Assert.assertNull(StringUtils.center(null, 4, ' '));
        Assert.assertEquals("    ", StringUtils.center("", 4, ' '));
        Assert.assertEquals("ab", StringUtils.center("ab", 0, ' '));
        Assert.assertEquals("ab", StringUtils.center("ab", -1, ' '));
        Assert.assertEquals("ab", StringUtils.center("ab", 1, ' '));
        Assert.assertEquals("    ", StringUtils.center("", 4, ' '));
        Assert.assertEquals(" ab ", StringUtils.center("ab", 4, ' '));
        Assert.assertEquals("abcd", StringUtils.center("abcd", 2, ' '));
        Assert.assertEquals(" a  ", StringUtils.center("a", 4, ' '));
        Assert.assertEquals("  a  ", StringUtils.center("a", 5, ' '));
        Assert.assertEquals("xxaxx", StringUtils.center("a", 5, 'x'));
    }

    @Test
    public void testCenter_StringIntString() {
        Assert.assertNull(StringUtils.center(null, 4, null));
        Assert.assertNull(StringUtils.center(null, -1, " "));
        Assert.assertNull(StringUtils.center(null, 4, " "));
        Assert.assertEquals("    ", StringUtils.center("", 4, " "));
        Assert.assertEquals("ab", StringUtils.center("ab", 0, " "));
        Assert.assertEquals("ab", StringUtils.center("ab", -1, " "));
        Assert.assertEquals("ab", StringUtils.center("ab", 1, " "));
        Assert.assertEquals("    ", StringUtils.center("", 4, " "));
        Assert.assertEquals(" ab ", StringUtils.center("ab", 4, " "));
        Assert.assertEquals("abcd", StringUtils.center("abcd", 2, " "));
        Assert.assertEquals(" a  ", StringUtils.center("a", 4, " "));
        Assert.assertEquals("yayz", StringUtils.center("a", 4, "yz"));
        Assert.assertEquals("yzyayzy", StringUtils.center("a", 7, "yz"));
        Assert.assertEquals("  abc  ", StringUtils.center("abc", 7, null));
        Assert.assertEquals("  abc  ", StringUtils.center("abc", 7, ""));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testRotate_StringInt() {
        Assert.assertEquals(null, StringUtils.rotate(null, 1));
        Assert.assertEquals("", StringUtils.rotate("", 1));
        Assert.assertEquals("abcdefg", StringUtils.rotate("abcdefg", 0));
        Assert.assertEquals("fgabcde", StringUtils.rotate("abcdefg", 2));
        Assert.assertEquals("cdefgab", StringUtils.rotate("abcdefg", -2));
        Assert.assertEquals("abcdefg", StringUtils.rotate("abcdefg", 7));
        Assert.assertEquals("abcdefg", StringUtils.rotate("abcdefg", -7));
        Assert.assertEquals("fgabcde", StringUtils.rotate("abcdefg", 9));
        Assert.assertEquals("cdefgab", StringUtils.rotate("abcdefg", -9));
        Assert.assertEquals("efgabcd", StringUtils.rotate("abcdefg", 17));
        Assert.assertEquals("defgabc", StringUtils.rotate("abcdefg", -17));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testReverse_String() {
        Assert.assertNull(StringUtils.reverse(null));
        Assert.assertEquals("", StringUtils.reverse(""));
        Assert.assertEquals("sdrawkcab", StringUtils.reverse("backwards"));
    }

    @Test
    public void testReverseDelimited_StringChar() {
        Assert.assertNull(StringUtils.reverseDelimited(null, '.'));
        Assert.assertEquals("", StringUtils.reverseDelimited("", '.'));
        Assert.assertEquals("c.b.a", StringUtils.reverseDelimited("a.b.c", '.'));
        Assert.assertEquals("a b c", StringUtils.reverseDelimited("a b c", '.'));
        Assert.assertEquals("", StringUtils.reverseDelimited("", '.'));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testDefault_String() {
        Assert.assertEquals("", StringUtils.defaultString(null));
        Assert.assertEquals("", StringUtils.defaultString(""));
        Assert.assertEquals("abc", StringUtils.defaultString("abc"));
    }

    @Test
    public void testDefault_StringString() {
        Assert.assertEquals("NULL", StringUtils.defaultString(null, "NULL"));
        Assert.assertEquals("", StringUtils.defaultString("", "NULL"));
        Assert.assertEquals("abc", StringUtils.defaultString("abc", "NULL"));
    }

    @Test
    public void testDefaultIfEmpty_StringString() {
        Assert.assertEquals("NULL", StringUtils.defaultIfEmpty(null, "NULL"));
        Assert.assertEquals("NULL", StringUtils.defaultIfEmpty("", "NULL"));
        Assert.assertEquals("abc", StringUtils.defaultIfEmpty("abc", "NULL"));
        Assert.assertNull(StringUtils.defaultIfEmpty("", null));
        // Tests compatibility for the API return type
        final String s = StringUtils.defaultIfEmpty("abc", "NULL");
        Assert.assertEquals("abc", s);
    }

    @Test
    public void testDefaultIfBlank_StringString() {
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(null, "NULL"));
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank("", "NULL"));
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(" ", "NULL"));
        Assert.assertEquals("abc", StringUtils.defaultIfBlank("abc", "NULL"));
        Assert.assertNull(StringUtils.defaultIfBlank("", null));
        // Tests compatibility for the API return type
        final String s = StringUtils.defaultIfBlank("abc", "NULL");
        Assert.assertEquals("abc", s);
    }

    @Test
    public void testDefaultIfEmpty_StringBuilders() {
        Assert.assertEquals("NULL", StringUtils.defaultIfEmpty(new StringBuilder(""), new StringBuilder("NULL")).toString());
        Assert.assertEquals("abc", StringUtils.defaultIfEmpty(new StringBuilder("abc"), new StringBuilder("NULL")).toString());
        Assert.assertNull(StringUtils.defaultIfEmpty(new StringBuilder(""), null));
        // Tests compatibility for the API return type
        final StringBuilder s = StringUtils.defaultIfEmpty(new StringBuilder("abc"), new StringBuilder("NULL"));
        Assert.assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuilders() {
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuilder(""), new StringBuilder("NULL")).toString());
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuilder(" "), new StringBuilder("NULL")).toString());
        Assert.assertEquals("abc", StringUtils.defaultIfBlank(new StringBuilder("abc"), new StringBuilder("NULL")).toString());
        Assert.assertNull(StringUtils.defaultIfBlank(new StringBuilder(""), null));
        // Tests compatibility for the API return type
        final StringBuilder s = StringUtils.defaultIfBlank(new StringBuilder("abc"), new StringBuilder("NULL"));
        Assert.assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfEmpty_StringBuffers() {
        Assert.assertEquals("NULL", StringUtils.defaultIfEmpty(new StringBuffer(""), new StringBuffer("NULL")).toString());
        Assert.assertEquals("abc", StringUtils.defaultIfEmpty(new StringBuffer("abc"), new StringBuffer("NULL")).toString());
        Assert.assertNull(StringUtils.defaultIfEmpty(new StringBuffer(""), null));
        // Tests compatibility for the API return type
        final StringBuffer s = StringUtils.defaultIfEmpty(new StringBuffer("abc"), new StringBuffer("NULL"));
        Assert.assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfBlank_StringBuffers() {
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuffer(""), new StringBuffer("NULL")).toString());
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(new StringBuffer(" "), new StringBuffer("NULL")).toString());
        Assert.assertEquals("abc", StringUtils.defaultIfBlank(new StringBuffer("abc"), new StringBuffer("NULL")).toString());
        Assert.assertNull(StringUtils.defaultIfBlank(new StringBuffer(""), null));
        // Tests compatibility for the API return type
        final StringBuffer s = StringUtils.defaultIfBlank(new StringBuffer("abc"), new StringBuffer("NULL"));
        Assert.assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfEmpty_CharBuffers() {
        Assert.assertEquals("NULL", StringUtils.defaultIfEmpty(CharBuffer.wrap(""), CharBuffer.wrap("NULL")).toString());
        Assert.assertEquals("abc", StringUtils.defaultIfEmpty(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL")).toString());
        Assert.assertNull(StringUtils.defaultIfEmpty(CharBuffer.wrap(""), null));
        // Tests compatibility for the API return type
        final CharBuffer s = StringUtils.defaultIfEmpty(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL"));
        Assert.assertEquals("abc", s.toString());
    }

    @Test
    public void testDefaultIfBlank_CharBuffers() {
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(CharBuffer.wrap(""), CharBuffer.wrap("NULL")).toString());
        Assert.assertEquals("NULL", StringUtils.defaultIfBlank(CharBuffer.wrap(" "), CharBuffer.wrap("NULL")).toString());
        Assert.assertEquals("abc", StringUtils.defaultIfBlank(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL")).toString());
        Assert.assertNull(StringUtils.defaultIfBlank(CharBuffer.wrap(""), null));
        // Tests compatibility for the API return type
        final CharBuffer s = StringUtils.defaultIfBlank(CharBuffer.wrap("abc"), CharBuffer.wrap("NULL"));
        Assert.assertEquals("abc", s.toString());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testAbbreviate_StringInt() {
        Assert.assertNull(StringUtils.abbreviate(null, 10));
        Assert.assertEquals("", StringUtils.abbreviate("", 10));
        Assert.assertEquals("short", StringUtils.abbreviate("short", 10));
        Assert.assertEquals("Now is ...", StringUtils.abbreviate("Now is the time for all good men to come to the aid of their party.", 10));

        final String raspberry = "raspberry peach";
        Assert.assertEquals("raspberry p...", StringUtils.abbreviate(raspberry, 14));
        Assert.assertEquals("raspberry peach", StringUtils.abbreviate("raspberry peach", 15));
        Assert.assertEquals("raspberry peach", StringUtils.abbreviate("raspberry peach", 16));
        Assert.assertEquals("abc...", StringUtils.abbreviate("abcdefg", 6));
        Assert.assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", 7));
        Assert.assertEquals("abcdefg", StringUtils.abbreviate("abcdefg", 8));
        Assert.assertEquals("a...", StringUtils.abbreviate("abcdefg", 4));
        Assert.assertEquals("", StringUtils.abbreviate("", 4));

        try {
            @SuppressWarnings("unused")
            final
            String res = StringUtils.abbreviate("abc", 3);
            Assert.fail("StringUtils.abbreviate expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            // empty
        }
    }

    @Test
    public void testAbbreviate_StringIntInt() {
        Assert.assertNull(StringUtils.abbreviate(null, 10, 12));
        Assert.assertEquals("", StringUtils.abbreviate("", 0, 10));
        Assert.assertEquals("", StringUtils.abbreviate("", 2, 10));

        try {
            @SuppressWarnings("unused")
            final
            String res = StringUtils.abbreviate("abcdefghij", 0, 3);
            Assert.fail("StringUtils.abbreviate expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            // empty
        }
        try {
            @SuppressWarnings("unused")
            final
            String res = StringUtils.abbreviate("abcdefghij", 5, 6);
            Assert.fail("StringUtils.abbreviate expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            // empty
        }


        final String raspberry = "raspberry peach";
        Assert.assertEquals("raspberry peach", StringUtils.abbreviate(raspberry, 11, 15));

        Assert.assertNull(StringUtils.abbreviate(null, 7, 14));
        assertAbbreviateWithOffset("abcdefg...", -1, 10);
        assertAbbreviateWithOffset("abcdefg...", 0, 10);
        assertAbbreviateWithOffset("abcdefg...", 1, 10);
        assertAbbreviateWithOffset("abcdefg...", 2, 10);
        assertAbbreviateWithOffset("abcdefg...", 3, 10);
        assertAbbreviateWithOffset("abcdefg...", 4, 10);
        assertAbbreviateWithOffset("...fghi...", 5, 10);
        assertAbbreviateWithOffset("...ghij...", 6, 10);
        assertAbbreviateWithOffset("...hijk...", 7, 10);
        assertAbbreviateWithOffset("...ijklmno", 8, 10);
        assertAbbreviateWithOffset("...ijklmno", 9, 10);
        assertAbbreviateWithOffset("...ijklmno", 10, 10);
        assertAbbreviateWithOffset("...ijklmno", 10, 10);
        assertAbbreviateWithOffset("...ijklmno", 11, 10);
        assertAbbreviateWithOffset("...ijklmno", 12, 10);
        assertAbbreviateWithOffset("...ijklmno", 13, 10);
        assertAbbreviateWithOffset("...ijklmno", 14, 10);
        assertAbbreviateWithOffset("...ijklmno", 15, 10);
        assertAbbreviateWithOffset("...ijklmno", 16, 10);
        assertAbbreviateWithOffset("...ijklmno", Integer.MAX_VALUE, 10);
    }

    private void assertAbbreviateWithOffset(final String expected, final int offset, final int maxWidth) {
        final String abcdefghijklmno = "abcdefghijklmno";
        final String message = "abbreviate(String,int,int) failed";
        final String actual = StringUtils.abbreviate(abcdefghijklmno, offset, maxWidth);
        if (offset >= 0 && offset < abcdefghijklmno.length()) {
            Assert.assertTrue(message + " -- should contain offset character",
                    actual.indexOf((char) ('a' + offset)) != -1);
        }
        Assert.assertTrue(message + " -- should not be greater than maxWidth",
                actual.length() <= maxWidth);
        Assert.assertEquals(message, expected, actual);
    }

    @Test
    public void testAbbreviateMiddle() {
        // javadoc examples
        Assert.assertNull(StringUtils.abbreviateMiddle(null, null, 0));
        Assert.assertEquals("abc", StringUtils.abbreviateMiddle("abc", null, 0));
        Assert.assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 0));
        Assert.assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 3));
        Assert.assertEquals("ab.f", StringUtils.abbreviateMiddle("abcdef", ".", 4));

        // JIRA issue (LANG-405) example (slightly different than actual expected result)
        Assert.assertEquals(
                "A very long text with un...f the text is complete.",
                StringUtils.abbreviateMiddle(
                        "A very long text with unimportant stuff in the middle but interesting start and " +
                                "end to see if the text is complete.", "...", 50));

        // Test a much longer text :)
        final String longText = "Start text" + StringUtils.repeat("x", 10000) + "Close text";
        Assert.assertEquals(
                "Start text->Close text",
                StringUtils.abbreviateMiddle(longText, "->", 22));

        // Test negative length
        Assert.assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", -1));

        // Test boundaries
        // Fails to change anything as method ensures first and last char are kept
        Assert.assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 1));
        Assert.assertEquals("abc", StringUtils.abbreviateMiddle("abc", ".", 2));

        // Test length of n=1
        Assert.assertEquals("a", StringUtils.abbreviateMiddle("a", ".", 1));

        // Test smallest length that can lead to success
        Assert.assertEquals("a.d", StringUtils.abbreviateMiddle("abcd", ".", 3));

        // More from LANG-405
        Assert.assertEquals("a..f", StringUtils.abbreviateMiddle("abcdef", "..", 4));
        Assert.assertEquals("ab.ef", StringUtils.abbreviateMiddle("abcdef", ".", 5));
    }

    @Test
    public void testTruncate_StringInt() {
        Assert.assertNull(StringUtils.truncate(null, 12));
        try {
            StringUtils.truncate(null, -1);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate(null, -10);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate(null, Integer.MIN_VALUE);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        Assert.assertEquals("", StringUtils.truncate("", 10));
        Assert.assertEquals("", StringUtils.truncate("", 10));
        Assert.assertEquals("abc", StringUtils.truncate("abcdefghij", 3));
        Assert.assertEquals("abcdef", StringUtils.truncate("abcdefghij", 6));
        Assert.assertEquals("", StringUtils.truncate("abcdefghij", 0));
        try {
            StringUtils.truncate("abcdefghij", -1);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -100);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", Integer.MIN_VALUE);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        Assert.assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 10));
        Assert.assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE));
        Assert.assertEquals("abcde", StringUtils.truncate("abcdefghijklmno", 5));
        Assert.assertEquals("abc", StringUtils.truncate("abcdefghijklmno", 3));
    }

    @Test
    public void testTruncate_StringIntInt() {
        Assert.assertNull(StringUtils.truncate(null, 0, 12));
        try {
            StringUtils.truncate(null, -1, 0);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate(null, -10, -4);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate(null, Integer.MIN_VALUE, Integer.MIN_VALUE);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        Assert.assertNull(StringUtils.truncate(null, 10, 12));
        Assert.assertEquals("", StringUtils.truncate("", 0, 10));
        Assert.assertEquals("", StringUtils.truncate("", 2, 10));
        Assert.assertEquals("abc", StringUtils.truncate("abcdefghij", 0, 3));
        Assert.assertEquals("fghij", StringUtils.truncate("abcdefghij", 5, 6));
        Assert.assertEquals("", StringUtils.truncate("abcdefghij", 0, 0));
        try {
            StringUtils.truncate("abcdefghij", 0, -1);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", 0, -10);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", 0, -100);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", 1, -100);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", 0, Integer.MIN_VALUE);
            Assert.fail("maxWith cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -1, 0);
            Assert.fail("offset cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -10, 0);
            Assert.fail("offset cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -100, 1);
            Assert.fail("offset cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, 0);
            Assert.fail("offset cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -1, -1);
            Assert.fail("offset cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -10, -10);
            Assert.fail("offset cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", -100, -100);
            Assert.fail("offset  cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        try {
            StringUtils.truncate("abcdefghij", Integer.MIN_VALUE, Integer.MIN_VALUE);
            Assert.fail("offset  cannot be negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
        final String raspberry = "raspberry peach";
        Assert.assertEquals("peach", StringUtils.truncate(raspberry, 10, 15));
        Assert.assertEquals("abcdefghij", StringUtils.truncate("abcdefghijklmno", 0, 10));
        Assert.assertEquals("abcdefghijklmno", StringUtils.truncate("abcdefghijklmno", 0, Integer.MAX_VALUE));
        Assert.assertEquals("bcdefghijk", StringUtils.truncate("abcdefghijklmno", 1, 10));
        Assert.assertEquals("cdefghijkl", StringUtils.truncate("abcdefghijklmno", 2, 10));
        Assert.assertEquals("defghijklm", StringUtils.truncate("abcdefghijklmno", 3, 10));
        Assert.assertEquals("efghijklmn", StringUtils.truncate("abcdefghijklmno", 4, 10));
        Assert.assertEquals("fghijklmno", StringUtils.truncate("abcdefghijklmno", 5, 10));
        Assert.assertEquals("fghij", StringUtils.truncate("abcdefghijklmno", 5, 5));
        Assert.assertEquals("fgh", StringUtils.truncate("abcdefghijklmno", 5, 3));
        Assert.assertEquals("klm", StringUtils.truncate("abcdefghijklmno", 10, 3));
        Assert.assertEquals("klmno", StringUtils.truncate("abcdefghijklmno", 10, Integer.MAX_VALUE));
        Assert.assertEquals("n", StringUtils.truncate("abcdefghijklmno", 13, 1));
        Assert.assertEquals("no", StringUtils.truncate("abcdefghijklmno", 13, Integer.MAX_VALUE));
        Assert.assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, 1));
        Assert.assertEquals("o", StringUtils.truncate("abcdefghijklmno", 14, Integer.MAX_VALUE));
        Assert.assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, 1));
        Assert.assertEquals("", StringUtils.truncate("abcdefghijklmno", 15, Integer.MAX_VALUE));
        Assert.assertEquals("", StringUtils.truncate("abcdefghijklmno", Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testDifference_StringString() {
        Assert.assertNull(StringUtils.difference(null, null));
        Assert.assertEquals("", StringUtils.difference("", ""));
        Assert.assertEquals("abc", StringUtils.difference("", "abc"));
        Assert.assertEquals("", StringUtils.difference("abc", ""));
        Assert.assertEquals("i am a robot", StringUtils.difference(null, "i am a robot"));
        Assert.assertEquals("i am a machine", StringUtils.difference("i am a machine", null));
        Assert.assertEquals("robot", StringUtils.difference("i am a machine", "i am a robot"));
        Assert.assertEquals("", StringUtils.difference("abc", "abc"));
        Assert.assertEquals("you are a robot", StringUtils.difference("i am a robot", "you are a robot"));
    }

    @Test
    public void testDifferenceAt_StringString() {
        Assert.assertEquals(-1, StringUtils.indexOfDifference(null, null));
        Assert.assertEquals(0, StringUtils.indexOfDifference(null, "i am a robot"));
        Assert.assertEquals(-1, StringUtils.indexOfDifference("", ""));
        Assert.assertEquals(0, StringUtils.indexOfDifference("", "abc"));
        Assert.assertEquals(0, StringUtils.indexOfDifference("abc", ""));
        Assert.assertEquals(0, StringUtils.indexOfDifference("i am a machine", null));
        Assert.assertEquals(7, StringUtils.indexOfDifference("i am a machine", "i am a robot"));
        Assert.assertEquals(-1, StringUtils.indexOfDifference("foo", "foo"));
        Assert.assertEquals(0, StringUtils.indexOfDifference("i am a robot", "you are a robot"));
        //System.out.println("indexOfDiff: " + StringUtils.indexOfDifference("i am a robot", "not machine"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testGetLevenshteinDistance_StringString() {
        Assert.assertEquals(0, StringUtils.getLevenshteinDistance("", ""));
        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("", "a"));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", ""));
        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("frog", "fog"));
        Assert.assertEquals(3, StringUtils.getLevenshteinDistance("fly", "ant"));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("elephant", "hippo"));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("hippo", "elephant"));
        Assert.assertEquals(8, StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz"));
        Assert.assertEquals(8, StringUtils.getLevenshteinDistance("zzzzzzzz", "hippo"));
        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("hello", "hallo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLevenshteinDistance_NullString() throws Exception {
        StringUtils.getLevenshteinDistance("a", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLevenshteinDistance_StringNull() throws Exception {
        StringUtils.getLevenshteinDistance(null, "a");
    }

    @Test
    public void testGetLevenshteinDistance_StringStringInt() {
        // empty strings
        Assert.assertEquals(0, StringUtils.getLevenshteinDistance("", "", 0));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "", 8));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "", 7));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("aaapppp", "", 6));

        // unequal strings, zero threshold
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("b", "a", 0));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("a", "b", 0));

        // equal strings
        Assert.assertEquals(0, StringUtils.getLevenshteinDistance("aa", "aa", 0));
        Assert.assertEquals(0, StringUtils.getLevenshteinDistance("aa", "aa", 2));

        // same length
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("aaa", "bbb", 2));
        Assert.assertEquals(3, StringUtils.getLevenshteinDistance("aaa", "bbb", 3));

        // big stripe
        Assert.assertEquals(6, StringUtils.getLevenshteinDistance("aaaaaa", "b", 10));

        // distance less than threshold
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "b", 8));
        Assert.assertEquals(3, StringUtils.getLevenshteinDistance("a", "bbb", 4));

        // distance equal to threshold
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("aaapppp", "b", 7));
        Assert.assertEquals(3, StringUtils.getLevenshteinDistance("a", "bbb", 3));

        // distance greater than threshold
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("a", "bbb", 2));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("bbb", "a", 2));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("aaapppp", "b", 6));

        // stripe runs off array, strings not similar
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("a", "bbb", 1));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("bbb", "a", 1));

        // stripe runs off array, strings are similar
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("12345", "1234567", 1));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("1234567", "12345", 1));

        // old getLevenshteinDistance test cases
        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("frog", "fog", 1));
        Assert.assertEquals(3, StringUtils.getLevenshteinDistance("fly", "ant", 3));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("elephant", "hippo", 7));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("elephant", "hippo", 6));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("hippo", "elephant", 7));
        Assert.assertEquals(-1, StringUtils.getLevenshteinDistance("hippo", "elephant", 6));
        Assert.assertEquals(8, StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz", 8));
        Assert.assertEquals(8, StringUtils.getLevenshteinDistance("zzzzzzzz", "hippo", 8));
        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("hello", "hallo", 1));

        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("frog", "fog", Integer.MAX_VALUE));
        Assert.assertEquals(3, StringUtils.getLevenshteinDistance("fly", "ant", Integer.MAX_VALUE));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("elephant", "hippo", Integer.MAX_VALUE));
        Assert.assertEquals(7, StringUtils.getLevenshteinDistance("hippo", "elephant", Integer.MAX_VALUE));
        Assert.assertEquals(8, StringUtils.getLevenshteinDistance("hippo", "zzzzzzzz", Integer.MAX_VALUE));
        Assert.assertEquals(8, StringUtils.getLevenshteinDistance("zzzzzzzz", "hippo", Integer.MAX_VALUE));
        Assert.assertEquals(1, StringUtils.getLevenshteinDistance("hello", "hallo", Integer.MAX_VALUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLevenshteinDistance_NullStringInt() throws Exception {
        StringUtils.getLevenshteinDistance(null, "a", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLevenshteinDistance_StringNullInt() throws Exception {
        StringUtils.getLevenshteinDistance("a", null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLevenshteinDistance_StringStringNegativeInt() throws Exception {
        StringUtils.getLevenshteinDistance("a", "a", -1);
    }

    @Test
    public void testGetJaroWinklerDistance_StringString() {
        Assert.assertEquals(0.93d, StringUtils.getJaroWinklerDistance("frog", "fog"), 0.0d);
        Assert.assertEquals(0.0d, StringUtils.getJaroWinklerDistance("fly", "ant"), 0.0d);
        Assert.assertEquals(0.44d, StringUtils.getJaroWinklerDistance("elephant", "hippo"), 0.0d);
        Assert.assertEquals(0.84d, StringUtils.getJaroWinklerDistance("dwayne", "duane"), 0.0d);
        Assert.assertEquals(0.93d, StringUtils.getJaroWinklerDistance("ABC Corporation", "ABC Corp"), 0.0d);
        Assert.assertEquals(0.95d, StringUtils.getJaroWinklerDistance("D N H Enterprises Inc", "D & H Enterprises, Inc."), 0.0d);
        Assert.assertEquals(0.92d, StringUtils.getJaroWinklerDistance("My Gym Children's Fitness Center", "My Gym. Childrens Fitness"), 0.0d);
        Assert.assertEquals(0.88d, StringUtils.getJaroWinklerDistance("PENNSYLVANIA", "PENNCISYLVNIA"), 0.0d);
        Assert.assertEquals(0.63d, StringUtils.getJaroWinklerDistance("Haus Ingeborg", "Ingeborg Esser"), 0.0d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJaroWinklerDistance_NullNull() throws Exception {
        StringUtils.getJaroWinklerDistance(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJaroWinklerDistance_StringNull() throws Exception {
        StringUtils.getJaroWinklerDistance(" ", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetJaroWinklerDistance_NullString() throws Exception {
        StringUtils.getJaroWinklerDistance(null, "clear");
    }

    @Test
    public void testGetFuzzyDistance() throws Exception {
        Assert.assertEquals(0, StringUtils.getFuzzyDistance("", "", Locale.ENGLISH));
        Assert.assertEquals(0, StringUtils.getFuzzyDistance("Workshop", "b", Locale.ENGLISH));
        Assert.assertEquals(1, StringUtils.getFuzzyDistance("Room", "o", Locale.ENGLISH));
        Assert.assertEquals(1, StringUtils.getFuzzyDistance("Workshop", "w", Locale.ENGLISH));
        Assert.assertEquals(2, StringUtils.getFuzzyDistance("Workshop", "ws", Locale.ENGLISH));
        Assert.assertEquals(4, StringUtils.getFuzzyDistance("Workshop", "wo", Locale.ENGLISH));
        Assert.assertEquals(3, StringUtils.getFuzzyDistance("Apache Software Foundation", "asf", Locale.ENGLISH));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFuzzyDistance_NullNullNull() throws Exception {
        StringUtils.getFuzzyDistance(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFuzzyDistance_StringNullLoclae() throws Exception {
        StringUtils.getFuzzyDistance(" ", null, Locale.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFuzzyDistance_NullStringLocale() throws Exception {
        StringUtils.getFuzzyDistance(null, "clear", Locale.ENGLISH);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFuzzyDistance_StringStringNull() throws Exception {
        StringUtils.getFuzzyDistance(" ", "clear", null);
    }

    /**
     * A sanity check for {@link StringUtils#EMPTY}.
     */
    @Test
    public void testEMPTY() {
        Assert.assertNotNull(StringUtils.EMPTY);
        Assert.assertEquals("", StringUtils.EMPTY);
        Assert.assertEquals(0, StringUtils.EMPTY.length());
    }

    /**
     * Test for {@link StringUtils#isAllLowerCase(CharSequence)}.
     */
    @Test
    public void testIsAllLowerCase() {
        Assert.assertFalse(StringUtils.isAllLowerCase(null));
        Assert.assertFalse(StringUtils.isAllLowerCase(StringUtils.EMPTY));
        Assert.assertFalse(StringUtils.isAllLowerCase("  "));
        Assert.assertTrue(StringUtils.isAllLowerCase("abc"));
        Assert.assertFalse(StringUtils.isAllLowerCase("abc "));
        Assert.assertFalse(StringUtils.isAllLowerCase("abc\n"));
        Assert.assertFalse(StringUtils.isAllLowerCase("abC"));
        Assert.assertFalse(StringUtils.isAllLowerCase("ab c"));
        Assert.assertFalse(StringUtils.isAllLowerCase("ab1c"));
        Assert.assertFalse(StringUtils.isAllLowerCase("ab/c"));
    }

    /**
     * Test for {@link StringUtils#isAllUpperCase(CharSequence)}.
     */
    @Test
    public void testIsAllUpperCase() {
        Assert.assertFalse(StringUtils.isAllUpperCase(null));
        Assert.assertFalse(StringUtils.isAllUpperCase(StringUtils.EMPTY));
        Assert.assertFalse(StringUtils.isAllUpperCase("  "));
        Assert.assertTrue(StringUtils.isAllUpperCase("ABC"));
        Assert.assertFalse(StringUtils.isAllUpperCase("ABC "));
        Assert.assertFalse(StringUtils.isAllUpperCase("ABC\n"));
        Assert.assertFalse(StringUtils.isAllUpperCase("aBC"));
        Assert.assertFalse(StringUtils.isAllUpperCase("A C"));
        Assert.assertFalse(StringUtils.isAllUpperCase("A1C"));
        Assert.assertFalse(StringUtils.isAllUpperCase("A/C"));
    }

    @Test
    public void testRemoveStart() {
        // StringUtils.removeStart("", *)        = ""
        Assert.assertNull(StringUtils.removeStart(null, null));
        Assert.assertNull(StringUtils.removeStart(null, ""));
        Assert.assertNull(StringUtils.removeStart(null, "a"));

        // StringUtils.removeStart(*, null)      = *
        Assert.assertEquals(StringUtils.removeStart("", null), "");
        Assert.assertEquals(StringUtils.removeStart("", ""), "");
        Assert.assertEquals(StringUtils.removeStart("", "a"), "");

        // All others:
        Assert.assertEquals(StringUtils.removeStart("www.domain.com", "www."), "domain.com");
        Assert.assertEquals(StringUtils.removeStart("domain.com", "www."), "domain.com");
        Assert.assertEquals(StringUtils.removeStart("domain.com", ""), "domain.com");
        Assert.assertEquals(StringUtils.removeStart("domain.com", null), "domain.com");
    }

    @Test
    public void testRemoveStartIgnoreCase() {
        // StringUtils.removeStart("", *)        = ""
        Assert.assertNull("removeStartIgnoreCase(null, null)", StringUtils.removeStartIgnoreCase(null, null));
        Assert.assertNull("removeStartIgnoreCase(null, \"\")", StringUtils.removeStartIgnoreCase(null, ""));
        Assert.assertNull("removeStartIgnoreCase(null, \"a\")", StringUtils.removeStartIgnoreCase(null, "a"));

        // StringUtils.removeStart(*, null)      = *
        Assert.assertEquals("removeStartIgnoreCase(\"\", null)", StringUtils.removeStartIgnoreCase("", null), "");
        Assert.assertEquals("removeStartIgnoreCase(\"\", \"\")", StringUtils.removeStartIgnoreCase("", ""), "");
        Assert.assertEquals("removeStartIgnoreCase(\"\", \"a\")", StringUtils.removeStartIgnoreCase("", "a"), "");

        // All others:
        Assert.assertEquals("removeStartIgnoreCase(\"www.domain.com\", \"www.\")", StringUtils.removeStartIgnoreCase("www.domain.com", "www."), "domain.com");
        Assert.assertEquals("removeStartIgnoreCase(\"domain.com\", \"www.\")", StringUtils.removeStartIgnoreCase("domain.com", "www."), "domain.com");
        Assert.assertEquals("removeStartIgnoreCase(\"domain.com\", \"\")", StringUtils.removeStartIgnoreCase("domain.com", ""), "domain.com");
        Assert.assertEquals("removeStartIgnoreCase(\"domain.com\", null)", StringUtils.removeStartIgnoreCase("domain.com", null), "domain.com");

        // Case insensitive:
        Assert.assertEquals("removeStartIgnoreCase(\"www.domain.com\", \"WWW.\")", StringUtils.removeStartIgnoreCase("www.domain.com", "WWW."), "domain.com");
    }

    @Test
    public void testRemoveEnd() {
        // StringUtils.removeEnd("", *)        = ""
        Assert.assertNull(StringUtils.removeEnd(null, null));
        Assert.assertNull(StringUtils.removeEnd(null, ""));
        Assert.assertNull(StringUtils.removeEnd(null, "a"));

        // StringUtils.removeEnd(*, null)      = *
        Assert.assertEquals(StringUtils.removeEnd("", null), "");
        Assert.assertEquals(StringUtils.removeEnd("", ""), "");
        Assert.assertEquals(StringUtils.removeEnd("", "a"), "");

        // All others:
        Assert.assertEquals(StringUtils.removeEnd("www.domain.com.", ".com"), "www.domain.com.");
        Assert.assertEquals(StringUtils.removeEnd("www.domain.com", ".com"), "www.domain");
        Assert.assertEquals(StringUtils.removeEnd("www.domain", ".com"), "www.domain");
        Assert.assertEquals(StringUtils.removeEnd("domain.com", ""), "domain.com");
        Assert.assertEquals(StringUtils.removeEnd("domain.com", null), "domain.com");
    }

    @Test
    public void testRemoveEndIgnoreCase() {
        // StringUtils.removeEndIgnoreCase("", *)        = ""
        Assert.assertNull("removeEndIgnoreCase(null, null)", StringUtils.removeEndIgnoreCase(null, null));
        Assert.assertNull("removeEndIgnoreCase(null, \"\")", StringUtils.removeEndIgnoreCase(null, ""));
        Assert.assertNull("removeEndIgnoreCase(null, \"a\")", StringUtils.removeEndIgnoreCase(null, "a"));

        // StringUtils.removeEnd(*, null)      = *
        Assert.assertEquals("removeEndIgnoreCase(\"\", null)", StringUtils.removeEndIgnoreCase("", null), "");
        Assert.assertEquals("removeEndIgnoreCase(\"\", \"\")", StringUtils.removeEndIgnoreCase("", ""), "");
        Assert.assertEquals("removeEndIgnoreCase(\"\", \"a\")", StringUtils.removeEndIgnoreCase("", "a"), "");

        // All others:
        Assert.assertEquals("removeEndIgnoreCase(\"www.domain.com.\", \".com\")", StringUtils.removeEndIgnoreCase("www.domain.com.", ".com"), "www.domain.com.");
        Assert.assertEquals("removeEndIgnoreCase(\"www.domain.com\", \".com\")", StringUtils.removeEndIgnoreCase("www.domain.com", ".com"), "www.domain");
        Assert.assertEquals("removeEndIgnoreCase(\"www.domain\", \".com\")", StringUtils.removeEndIgnoreCase("www.domain", ".com"), "www.domain");
        Assert.assertEquals("removeEndIgnoreCase(\"domain.com\", \"\")", StringUtils.removeEndIgnoreCase("domain.com", ""), "domain.com");
        Assert.assertEquals("removeEndIgnoreCase(\"domain.com\", null)", StringUtils.removeEndIgnoreCase("domain.com", null), "domain.com");

        // Case insensitive:
        Assert.assertEquals("removeEndIgnoreCase(\"www.domain.com\", \".COM\")", StringUtils.removeEndIgnoreCase("www.domain.com", ".COM"), "www.domain");
        Assert.assertEquals("removeEndIgnoreCase(\"www.domain.COM\", \".com\")", StringUtils.removeEndIgnoreCase("www.domain.COM", ".com"), "www.domain");
    }

    @Test
    public void testRemove_String() {
        // StringUtils.remove(null, *)        = null
        Assert.assertNull(StringUtils.remove(null, null));
        Assert.assertNull(StringUtils.remove(null, ""));
        Assert.assertNull(StringUtils.remove(null, "a"));

        // StringUtils.remove("", *)          = ""
        Assert.assertEquals("", StringUtils.remove("", null));
        Assert.assertEquals("", StringUtils.remove("", ""));
        Assert.assertEquals("", StringUtils.remove("", "a"));

        // StringUtils.remove(*, null)        = *
        Assert.assertNull(StringUtils.remove(null, null));
        Assert.assertEquals("", StringUtils.remove("", null));
        Assert.assertEquals("a", StringUtils.remove("a", null));

        // StringUtils.remove(*, "")          = *
        Assert.assertNull(StringUtils.remove(null, ""));
        Assert.assertEquals("", StringUtils.remove("", ""));
        Assert.assertEquals("a", StringUtils.remove("a", ""));

        // StringUtils.remove("queued", "ue") = "qd"
        Assert.assertEquals("qd", StringUtils.remove("queued", "ue"));

        // StringUtils.remove("queued", "zz") = "queued"
        Assert.assertEquals("queued", StringUtils.remove("queued", "zz"));
    }

    @Test
    public void testRemoveIgnoreCase_String() {
        // StringUtils.removeIgnoreCase(null, *) = null
        Assert.assertEquals(null, StringUtils.removeIgnoreCase(null, null));
        Assert.assertEquals(null, StringUtils.removeIgnoreCase(null, ""));
        Assert.assertEquals(null, StringUtils.removeIgnoreCase(null, "a"));

        // StringUtils.removeIgnoreCase("", *) = ""
        Assert.assertEquals("", StringUtils.removeIgnoreCase("", null));
        Assert.assertEquals("", StringUtils.removeIgnoreCase("", ""));
        Assert.assertEquals("", StringUtils.removeIgnoreCase("", "a"));

        // StringUtils.removeIgnoreCase(*, null) = *
        Assert.assertEquals(null, StringUtils.removeIgnoreCase(null, null));
        Assert.assertEquals("", StringUtils.removeIgnoreCase("", null));
        Assert.assertEquals("a", StringUtils.removeIgnoreCase("a", null));

        // StringUtils.removeIgnoreCase(*, "") = *
        Assert.assertEquals(null, StringUtils.removeIgnoreCase(null, ""));
        Assert.assertEquals("", StringUtils.removeIgnoreCase("", ""));
        Assert.assertEquals("a", StringUtils.removeIgnoreCase("a", ""));

        // StringUtils.removeIgnoreCase("queued", "ue") = "qd"
        Assert.assertEquals("qd", StringUtils.removeIgnoreCase("queued", "ue"));

        // StringUtils.removeIgnoreCase("queued", "zz") = "queued"
        Assert.assertEquals("queued", StringUtils.removeIgnoreCase("queued", "zz"));

        // IgnoreCase
        // StringUtils.removeIgnoreCase("quEUed", "UE") = "qd"
        Assert.assertEquals("qd", StringUtils.removeIgnoreCase("quEUed", "UE"));

        // StringUtils.removeIgnoreCase("queued", "zZ") = "queued"
        Assert.assertEquals("queued", StringUtils.removeIgnoreCase("queued", "zZ"));
    }

    @Test
    public void testRemove_char() {
        // StringUtils.remove(null, *)       = null
        Assert.assertNull(StringUtils.remove(null, 'a'));
        Assert.assertNull(StringUtils.remove(null, 'a'));
        Assert.assertNull(StringUtils.remove(null, 'a'));

        // StringUtils.remove("", *)          = ""
        Assert.assertEquals("", StringUtils.remove("", 'a'));
        Assert.assertEquals("", StringUtils.remove("", 'a'));
        Assert.assertEquals("", StringUtils.remove("", 'a'));

        // StringUtils.remove("queued", 'u') = "qeed"
        Assert.assertEquals("qeed", StringUtils.remove("queued", 'u'));

        // StringUtils.remove("queued", 'z') = "queued"
        Assert.assertEquals("queued", StringUtils.remove("queued", 'z'));
    }

    @Test
    public void testRemoveAll() {
        Assert.assertNull(StringUtils.removeAll(null, ""));
        Assert.assertEquals("any", StringUtils.removeAll("any", null));

        Assert.assertEquals("any", StringUtils.removeAll("any", ""));
        Assert.assertEquals("", StringUtils.removeAll("any", ".*"));
        Assert.assertEquals("", StringUtils.removeAll("any", ".+"));
        Assert.assertEquals("", StringUtils.removeAll("any", ".?"));

        Assert.assertEquals("A\nB", StringUtils.removeAll("A<__>\n<__>B", "<.*>"));
        Assert.assertEquals("AB", StringUtils.removeAll("A<__>\n<__>B", "(?s)<.*>"));
        Assert.assertEquals("ABC123", StringUtils.removeAll("ABCabc123abc", "[a-z]"));

        try {
            StringUtils.removeAll("any", "{badRegexSyntax}");
            Assert.fail("StringUtils.removeAll expecting PatternSyntaxException");
        } catch (final PatternSyntaxException ex) {
            // empty
        }
    }

    @Test
    public void testRemoveFirst() {
        Assert.assertNull(StringUtils.removeFirst(null, ""));
        Assert.assertEquals("any", StringUtils.removeFirst("any", null));

        Assert.assertEquals("any", StringUtils.removeFirst("any", ""));
        Assert.assertEquals("", StringUtils.removeFirst("any", ".*"));
        Assert.assertEquals("", StringUtils.removeFirst("any", ".+"));
        Assert.assertEquals("bc", StringUtils.removeFirst("abc", ".?"));

        Assert.assertEquals("A\n<__>B", StringUtils.removeFirst("A<__>\n<__>B", "<.*>"));
        Assert.assertEquals("AB", StringUtils.removeFirst("A<__>\n<__>B", "(?s)<.*>"));
        Assert.assertEquals("ABCbc123", StringUtils.removeFirst("ABCabc123", "[a-z]"));
        Assert.assertEquals("ABC123abc", StringUtils.removeFirst("ABCabc123abc", "[a-z]+"));

        try {
            StringUtils.removeFirst("any", "{badRegexSyntax}");
            Assert.fail("StringUtils.removeFirst expecting PatternSyntaxException");
        } catch (final PatternSyntaxException ex) {
            // empty
        }
    }

    @Test
    public void testDifferenceAt_StringArray() {
        Assert.assertEquals(-1, StringUtils.indexOfDifference((String[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfDifference(new String[]{}));
        Assert.assertEquals(-1, StringUtils.indexOfDifference(new String[]{"abc"}));
        Assert.assertEquals(-1, StringUtils.indexOfDifference(new String[]{null, null}));
        Assert.assertEquals(-1, StringUtils.indexOfDifference(new String[]{"", ""}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{"", null}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{"abc", null, null}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{null, null, "abc"}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{"", "abc"}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{"abc", ""}));
        Assert.assertEquals(-1, StringUtils.indexOfDifference(new String[]{"abc", "abc"}));
        Assert.assertEquals(1, StringUtils.indexOfDifference(new String[]{"abc", "a"}));
        Assert.assertEquals(2, StringUtils.indexOfDifference(new String[]{"ab", "abxyz"}));
        Assert.assertEquals(2, StringUtils.indexOfDifference(new String[]{"abcde", "abxyz"}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{"abcde", "xyz"}));
        Assert.assertEquals(0, StringUtils.indexOfDifference(new String[]{"xyz", "abcde"}));
        Assert.assertEquals(7, StringUtils.indexOfDifference(new String[]{"i am a machine", "i am a robot"}));
    }

    @Test
    public void testGetCommonPrefix_StringArray() {
        Assert.assertEquals("", StringUtils.getCommonPrefix((String[]) null));
        Assert.assertEquals("", StringUtils.getCommonPrefix());
        Assert.assertEquals("abc", StringUtils.getCommonPrefix("abc"));
        Assert.assertEquals("", StringUtils.getCommonPrefix(null, null));
        Assert.assertEquals("", StringUtils.getCommonPrefix("", ""));
        Assert.assertEquals("", StringUtils.getCommonPrefix("", null));
        Assert.assertEquals("", StringUtils.getCommonPrefix("abc", null, null));
        Assert.assertEquals("", StringUtils.getCommonPrefix(null, null, "abc"));
        Assert.assertEquals("", StringUtils.getCommonPrefix("", "abc"));
        Assert.assertEquals("", StringUtils.getCommonPrefix("abc", ""));
        Assert.assertEquals("abc", StringUtils.getCommonPrefix("abc", "abc"));
        Assert.assertEquals("a", StringUtils.getCommonPrefix("abc", "a"));
        Assert.assertEquals("ab", StringUtils.getCommonPrefix("ab", "abxyz"));
        Assert.assertEquals("ab", StringUtils.getCommonPrefix("abcde", "abxyz"));
        Assert.assertEquals("", StringUtils.getCommonPrefix("abcde", "xyz"));
        Assert.assertEquals("", StringUtils.getCommonPrefix("xyz", "abcde"));
        Assert.assertEquals("i am a ", StringUtils.getCommonPrefix("i am a machine", "i am a robot"));
    }

    @Test
    public void testNormalizeSpace() {
        // Java says a non-breaking whitespace is not a whitespace.
        Assert.assertFalse(Character.isWhitespace('\u00A0'));
        //
        Assert.assertNull(StringUtils.normalizeSpace(null));
        Assert.assertEquals("", StringUtils.normalizeSpace(""));
        Assert.assertEquals("", StringUtils.normalizeSpace(" "));
        Assert.assertEquals("", StringUtils.normalizeSpace("\t"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\n"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u0009"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u000B"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u000C"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u001C"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u001D"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u001E"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\u001F"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\f"));
        Assert.assertEquals("", StringUtils.normalizeSpace("\r"));
        Assert.assertEquals("a", StringUtils.normalizeSpace("  a  "));
        Assert.assertEquals("a b c", StringUtils.normalizeSpace("  a  b   c  "));
        Assert.assertEquals("a b c", StringUtils.normalizeSpace("a\t\f\r  b\u000B   c\n"));
        Assert.assertEquals("a   b c", StringUtils.normalizeSpace("a\t\f\r  " + HARD_SPACE + HARD_SPACE + "b\u000B   c\n"));
        Assert.assertEquals("b", StringUtils.normalizeSpace("\u0000b"));
        Assert.assertEquals("b", StringUtils.normalizeSpace("b\u0000"));
    }

    @Test
    public void testLANG666() {
        Assert.assertEquals("12", StringUtils.stripEnd("120.00", ".0"));
        Assert.assertEquals("121", StringUtils.stripEnd("121.00", ".0"));
    }

    // Methods on StringUtils that are immutable in spirit (i.e. calculate the length)
    // should take a CharSequence parameter. Methods that are mutable in spirit (i.e. capitalize)
    // should take a String or String[] parameter and return String or String[].
    // This test enforces that this is done.
    @Test
    public void testStringUtilsCharSequenceContract() {
        final Class<StringUtils> c = StringUtils.class;
        // Methods that are expressly excluded from testStringUtilsCharSequenceContract()
        final String[] excludeMethods = {
            "public static int org.apache.commons.lang3.StringUtils.compare(java.lang.String,java.lang.String)",
            "public static int org.apache.commons.lang3.StringUtils.compare(java.lang.String,java.lang.String,boolean)",
            "public static int org.apache.commons.lang3.StringUtils.compareIgnoreCase(java.lang.String,java.lang.String)",
            "public static int org.apache.commons.lang3.StringUtils.compareIgnoreCase(java.lang.String,java.lang.String,boolean)"
        };
        final Method[] methods = c.getMethods();

        for (final Method m : methods) {
            String methodStr = m.toString();
            if (m.getReturnType() == String.class || m.getReturnType() == String[].class) {
                // Assume this is mutable and ensure the first parameter is not CharSequence.
                // It may be String or it may be something else (String[], Object, Object[]) so
                // don't actively test for that.
                final Class<?>[] params = m.getParameterTypes();
                if (params.length > 0 && (params[0] == CharSequence.class || params[0] == CharSequence[].class)) {
                    if (!ArrayUtils.contains(excludeMethods, methodStr)) {
                        Assert.fail("The method \"" + methodStr + "\" appears to be mutable in spirit and therefore must not accept a CharSequence");
                    }
                }
            } else {
                // Assume this is immutable in spirit and ensure the first parameter is not String.
                // As above, it may be something other than CharSequence.
                final Class<?>[] params = m.getParameterTypes();
                if (params.length > 0 && (params[0] == String.class || params[0] == String[].class)) {
                    if (!ArrayUtils.contains(excludeMethods, methodStr)) {
                        Assert.fail("The method \"" + methodStr + "\" appears to be immutable in spirit and therefore must not accept a String");
                    }
                }
            }
        }
    }

    /**
     * Tests {@link StringUtils#toString(byte[], String)}
     *
     * @throws java.io.UnsupportedEncodingException because the method under test max throw it
     * @see StringUtils#toString(byte[], String)
     */
    @Test
    public void testToString() throws UnsupportedEncodingException {
        final String expectedString = "The quick brown fox jumped over the lazy dog.";
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        // sanity check start
        Assert.assertArrayEquals(expectedBytes, expectedString.getBytes());
        // sanity check end
        Assert.assertEquals(expectedString, StringUtils.toString(expectedBytes, null));
        Assert.assertEquals(expectedString, StringUtils.toString(expectedBytes, SystemUtils.FILE_ENCODING));
        String encoding = "UTF-16";
        expectedBytes = expectedString.getBytes(Charset.forName(encoding));
        Assert.assertEquals(expectedString, StringUtils.toString(expectedBytes, encoding));
    }

    @Test
    public void testEscapeSurrogatePairs() throws Exception {
        Assert.assertEquals("\uD83D\uDE30", StringEscapeUtils.escapeCsv("\uD83D\uDE30"));
        // Examples from https://en.wikipedia.org/wiki/UTF-16
        Assert.assertEquals("\uD800\uDC00", StringEscapeUtils.escapeCsv("\uD800\uDC00"));
        Assert.assertEquals("\uD834\uDD1E", StringEscapeUtils.escapeCsv("\uD834\uDD1E"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeCsv("\uDBFF\uDFFD"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeHtml3("\uDBFF\uDFFD"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeHtml4("\uDBFF\uDFFD"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.escapeXml("\uDBFF\uDFFD"));
    }

    /**
     * Tests LANG-858.
     */
    @Test
    public void testEscapeSurrogatePairsLang858() {
        Assert.assertEquals("\\uDBFF\\uDFFD", StringEscapeUtils.escapeJava("\uDBFF\uDFFD"));       //fail LANG-858
        Assert.assertEquals("\\uDBFF\\uDFFD", StringEscapeUtils.escapeEcmaScript("\uDBFF\uDFFD")); //fail LANG-858
    }

    @Test
    public void testUnescapeSurrogatePairs() throws Exception {
        Assert.assertEquals("\uD83D\uDE30", StringEscapeUtils.unescapeCsv("\uD83D\uDE30"));
        // Examples from https://en.wikipedia.org/wiki/UTF-16
        Assert.assertEquals("\uD800\uDC00", StringEscapeUtils.unescapeCsv("\uD800\uDC00"));
        Assert.assertEquals("\uD834\uDD1E", StringEscapeUtils.unescapeCsv("\uD834\uDD1E"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.unescapeCsv("\uDBFF\uDFFD"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.unescapeHtml3("\uDBFF\uDFFD"));
        Assert.assertEquals("\uDBFF\uDFFD", StringEscapeUtils.unescapeHtml4("\uDBFF\uDFFD"));
    }

    /**
     * Tests {@code appendIfMissing}.
     */
    @Test
    public void testAppendIfMissing() {
        Assert.assertEquals("appendIfMissing(null,null)", null, StringUtils.appendIfMissing(null, null));
        Assert.assertEquals("appendIfMissing(abc,null)", "abc", StringUtils.appendIfMissing("abc", null));
        Assert.assertEquals("appendIfMissing(\"\",xyz)", "xyz", StringUtils.appendIfMissing("", "xyz"));
        Assert.assertEquals("appendIfMissing(abc,xyz)", "abcxyz", StringUtils.appendIfMissing("abc", "xyz"));
        Assert.assertEquals("appendIfMissing(abcxyz,xyz)", "abcxyz", StringUtils.appendIfMissing("abcxyz", "xyz"));
        Assert.assertEquals("appendIfMissing(aXYZ,xyz)", "aXYZxyz", StringUtils.appendIfMissing("aXYZ", "xyz"));

        Assert.assertEquals("appendIfMissing(null,null,null)", null, StringUtils.appendIfMissing(null, null, (CharSequence[]) null));
        Assert.assertEquals("appendIfMissing(abc,null,null)", "abc", StringUtils.appendIfMissing("abc", null, (CharSequence[]) null));
        Assert.assertEquals("appendIfMissing(\"\",xyz,null))", "xyz", StringUtils.appendIfMissing("", "xyz", (CharSequence[]) null));
        Assert.assertEquals("appendIfMissing(abc,xyz,{null})", "abcxyz", StringUtils.appendIfMissing("abc", "xyz", new CharSequence[]{null}));
        Assert.assertEquals("appendIfMissing(abc,xyz,\"\")", "abc", StringUtils.appendIfMissing("abc", "xyz", ""));
        Assert.assertEquals("appendIfMissing(abc,xyz,mno)", "abcxyz", StringUtils.appendIfMissing("abc", "xyz", "mno"));
        Assert.assertEquals("appendIfMissing(abcxyz,xyz,mno)", "abcxyz", StringUtils.appendIfMissing("abcxyz", "xyz", "mno"));
        Assert.assertEquals("appendIfMissing(abcmno,xyz,mno)", "abcmno", StringUtils.appendIfMissing("abcmno", "xyz", "mno"));
        Assert.assertEquals("appendIfMissing(abcXYZ,xyz,mno)", "abcXYZxyz", StringUtils.appendIfMissing("abcXYZ", "xyz", "mno"));
        Assert.assertEquals("appendIfMissing(abcMNO,xyz,mno)", "abcMNOxyz", StringUtils.appendIfMissing("abcMNO", "xyz", "mno"));
    }

    /**
     * Tests {@code appendIfMissingIgnoreCase}.
     */
    @Test
    public void testAppendIfMissingIgnoreCase() {
        Assert.assertEquals("appendIfMissingIgnoreCase(null,null)", null, StringUtils.appendIfMissingIgnoreCase(null, null));
        Assert.assertEquals("appendIfMissingIgnoreCase(abc,null)", "abc", StringUtils.appendIfMissingIgnoreCase("abc", null));
        Assert.assertEquals("appendIfMissingIgnoreCase(\"\",xyz)", "xyz", StringUtils.appendIfMissingIgnoreCase("", "xyz"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abc,xyz)", "abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abcxyz,xyz)", "abcxyz", StringUtils.appendIfMissingIgnoreCase("abcxyz", "xyz"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abcXYZ,xyz)", "abcXYZ", StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz"));

        Assert.assertEquals("appendIfMissingIgnoreCase(null,null,null)", null, StringUtils.appendIfMissingIgnoreCase(null, null, (CharSequence[]) null));
        Assert.assertEquals("appendIfMissingIgnoreCase(abc,null,null)", "abc", StringUtils.appendIfMissingIgnoreCase("abc", null, (CharSequence[]) null));
        Assert.assertEquals("appendIfMissingIgnoreCase(\"\",xyz,null)", "xyz", StringUtils.appendIfMissingIgnoreCase("", "xyz", (CharSequence[]) null));
        Assert.assertEquals("appendIfMissingIgnoreCase(abc,xyz,{null})", "abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", new CharSequence[]{null}));
        Assert.assertEquals("appendIfMissingIgnoreCase(abc,xyz,\"\")", "abc", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", ""));
        Assert.assertEquals("appendIfMissingIgnoreCase(abc,xyz,mno)", "abcxyz", StringUtils.appendIfMissingIgnoreCase("abc", "xyz", "mno"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abcxyz,xyz,mno)", "abcxyz", StringUtils.appendIfMissingIgnoreCase("abcxyz", "xyz", "mno"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abcmno,xyz,mno)", "abcmno", StringUtils.appendIfMissingIgnoreCase("abcmno", "xyz", "mno"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abcXYZ,xyz,mno)", "abcXYZ", StringUtils.appendIfMissingIgnoreCase("abcXYZ", "xyz", "mno"));
        Assert.assertEquals("appendIfMissingIgnoreCase(abcMNO,xyz,mno)", "abcMNO", StringUtils.appendIfMissingIgnoreCase("abcMNO", "xyz", "mno"));
    }

    /**
     * Tests {@code prependIfMissing}.
     */
    @Test
    public void testPrependIfMissing() {
        Assert.assertEquals("prependIfMissing(null,null)", null, StringUtils.prependIfMissing(null, null));
        Assert.assertEquals("prependIfMissing(abc,null)", "abc", StringUtils.prependIfMissing("abc", null));
        Assert.assertEquals("prependIfMissing(\"\",xyz)", "xyz", StringUtils.prependIfMissing("", "xyz"));
        Assert.assertEquals("prependIfMissing(abc,xyz)", "xyzabc", StringUtils.prependIfMissing("abc", "xyz"));
        Assert.assertEquals("prependIfMissing(xyzabc,xyz)", "xyzabc", StringUtils.prependIfMissing("xyzabc", "xyz"));
        Assert.assertEquals("prependIfMissing(XYZabc,xyz)", "xyzXYZabc", StringUtils.prependIfMissing("XYZabc", "xyz"));

        Assert.assertEquals("prependIfMissing(null,null null)", null, StringUtils.prependIfMissing(null, null, (CharSequence[]) null));
        Assert.assertEquals("prependIfMissing(abc,null,null)", "abc", StringUtils.prependIfMissing("abc", null, (CharSequence[]) null));
        Assert.assertEquals("prependIfMissing(\"\",xyz,null)", "xyz", StringUtils.prependIfMissing("", "xyz", (CharSequence[]) null));
        Assert.assertEquals("prependIfMissing(abc,xyz,{null})", "xyzabc", StringUtils.prependIfMissing("abc", "xyz", new CharSequence[]{null}));
        Assert.assertEquals("prependIfMissing(abc,xyz,\"\")", "abc", StringUtils.prependIfMissing("abc", "xyz", ""));
        Assert.assertEquals("prependIfMissing(abc,xyz,mno)", "xyzabc", StringUtils.prependIfMissing("abc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissing(xyzabc,xyz,mno)", "xyzabc", StringUtils.prependIfMissing("xyzabc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissing(mnoabc,xyz,mno)", "mnoabc", StringUtils.prependIfMissing("mnoabc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissing(XYZabc,xyz,mno)", "xyzXYZabc", StringUtils.prependIfMissing("XYZabc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissing(MNOabc,xyz,mno)", "xyzMNOabc", StringUtils.prependIfMissing("MNOabc", "xyz", "mno"));
    }

    /**
     * Tests {@code prependIfMissingIgnoreCase}.
     */
    @Test
    public void testPrependIfMissingIgnoreCase() {
        Assert.assertEquals("prependIfMissingIgnoreCase(null,null)", null, StringUtils.prependIfMissingIgnoreCase(null, null));
        Assert.assertEquals("prependIfMissingIgnoreCase(abc,null)", "abc", StringUtils.prependIfMissingIgnoreCase("abc", null));
        Assert.assertEquals("prependIfMissingIgnoreCase(\"\",xyz)", "xyz", StringUtils.prependIfMissingIgnoreCase("", "xyz"));
        Assert.assertEquals("prependIfMissingIgnoreCase(abc,xyz)", "xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz"));
        Assert.assertEquals("prependIfMissingIgnoreCase(xyzabc,xyz)", "xyzabc", StringUtils.prependIfMissingIgnoreCase("xyzabc", "xyz"));
        Assert.assertEquals("prependIfMissingIgnoreCase(XYZabc,xyz)", "XYZabc", StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz"));

        Assert.assertEquals("prependIfMissingIgnoreCase(null,null null)", null, StringUtils.prependIfMissingIgnoreCase(null, null, (CharSequence[]) null));
        Assert.assertEquals("prependIfMissingIgnoreCase(abc,null,null)", "abc", StringUtils.prependIfMissingIgnoreCase("abc", null, (CharSequence[]) null));
        Assert.assertEquals("prependIfMissingIgnoreCase(\"\",xyz,null)", "xyz", StringUtils.prependIfMissingIgnoreCase("", "xyz", (CharSequence[]) null));
        Assert.assertEquals("prependIfMissingIgnoreCase(abc,xyz,{null})", "xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz", new CharSequence[]{null}));
        Assert.assertEquals("prependIfMissingIgnoreCase(abc,xyz,\"\")", "abc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz", ""));
        Assert.assertEquals("prependIfMissingIgnoreCase(abc,xyz,mno)", "xyzabc", StringUtils.prependIfMissingIgnoreCase("abc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissingIgnoreCase(xyzabc,xyz,mno)", "xyzabc", StringUtils.prependIfMissingIgnoreCase("xyzabc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissingIgnoreCase(mnoabc,xyz,mno)", "mnoabc", StringUtils.prependIfMissingIgnoreCase("mnoabc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissingIgnoreCase(XYZabc,xyz,mno)", "XYZabc", StringUtils.prependIfMissingIgnoreCase("XYZabc", "xyz", "mno"));
        Assert.assertEquals("prependIfMissingIgnoreCase(MNOabc,xyz,mno)", "MNOabc", StringUtils.prependIfMissingIgnoreCase("MNOabc", "xyz", "mno"));
    }

    /**
     * Tests {@link StringUtils#toEncodedString(byte[], Charset)}
     *
     * @see StringUtils#toEncodedString(byte[], Charset)
     */
    @Test
    public void testToEncodedString() {
        final String expectedString = "The quick brown fox jumped over the lazy dog.";
        String encoding = SystemUtils.FILE_ENCODING;
        byte[] expectedBytes = expectedString.getBytes(Charset.defaultCharset());
        // sanity check start
        Assert.assertArrayEquals(expectedBytes, expectedString.getBytes());
        // sanity check end
        Assert.assertEquals(expectedString, StringUtils.toEncodedString(expectedBytes, Charset.defaultCharset()));
        Assert.assertEquals(expectedString, StringUtils.toEncodedString(expectedBytes, Charset.forName(encoding)));
        encoding = "UTF-16";
        expectedBytes = expectedString.getBytes(Charset.forName(encoding));
        Assert.assertEquals(expectedString, StringUtils.toEncodedString(expectedBytes, Charset.forName(encoding)));
    }

    // -----------------------------------------------------------------------

    @Test
    public void testWrap_StringChar() {
        Assert.assertNull(StringUtils.wrap(null, '\0'));
        Assert.assertNull(StringUtils.wrap(null, '1'));

        Assert.assertEquals("", StringUtils.wrap("", '\0'));
        Assert.assertEquals("xabx", StringUtils.wrap("ab", 'x'));
        Assert.assertEquals("\"ab\"", StringUtils.wrap("ab", '\"'));
        Assert.assertEquals("\"\"ab\"\"", StringUtils.wrap("\"ab\"", '\"'));
        Assert.assertEquals("'ab'", StringUtils.wrap("ab", '\''));
        Assert.assertEquals("''abcd''", StringUtils.wrap("'abcd'", '\''));
        Assert.assertEquals("'\"abcd\"'", StringUtils.wrap("\"abcd\"", '\''));
        Assert.assertEquals("\"'abcd'\"", StringUtils.wrap("'abcd'", '\"'));
    }

    @Test
    public void testWrapIfMissing_StringChar() {
        Assert.assertNull(StringUtils.wrapIfMissing(null, '\0'));
        Assert.assertNull(StringUtils.wrapIfMissing(null, '1'));

        Assert.assertEquals("", StringUtils.wrapIfMissing("", '\0'));
        Assert.assertEquals("xabx", StringUtils.wrapIfMissing("ab", 'x'));
        Assert.assertEquals("\"ab\"", StringUtils.wrapIfMissing("ab", '\"'));
        Assert.assertEquals("\"ab\"", StringUtils.wrapIfMissing("\"ab\"", '\"'));
        Assert.assertEquals("'ab'", StringUtils.wrapIfMissing("ab", '\''));
        Assert.assertEquals("'abcd'", StringUtils.wrapIfMissing("'abcd'", '\''));
        Assert.assertEquals("'\"abcd\"'", StringUtils.wrapIfMissing("\"abcd\"", '\''));
        Assert.assertEquals("\"'abcd'\"", StringUtils.wrapIfMissing("'abcd'", '\"'));
        Assert.assertEquals("/x/", StringUtils.wrapIfMissing("x", '/'));
        Assert.assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z", '/'));
        Assert.assertEquals("/x/y/z/", StringUtils.wrapIfMissing("/x/y/z", '/'));
        Assert.assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z/", '/'));
        Assert.assertEquals("/", StringUtils.wrapIfMissing("/", '/'));
    }

    @Test
    public void testWrapIfMissing_StringString() {
        Assert.assertNull(StringUtils.wrapIfMissing(null, "\0"));
        Assert.assertNull(StringUtils.wrapIfMissing(null, "1"));

        Assert.assertEquals("", StringUtils.wrapIfMissing("", "\0"));
        Assert.assertEquals("xabx", StringUtils.wrapIfMissing("ab", "x"));
        Assert.assertEquals("\"ab\"", StringUtils.wrapIfMissing("ab", "\""));
        Assert.assertEquals("\"ab\"", StringUtils.wrapIfMissing("\"ab\"", "\""));
        Assert.assertEquals("'ab'", StringUtils.wrapIfMissing("ab", "\'"));
        Assert.assertEquals("'abcd'", StringUtils.wrapIfMissing("'abcd'", "\'"));
        Assert.assertEquals("'\"abcd\"'", StringUtils.wrapIfMissing("\"abcd\"", "\'"));
        Assert.assertEquals("\"'abcd'\"", StringUtils.wrapIfMissing("'abcd'", "\""));
        Assert.assertEquals("/x/", StringUtils.wrapIfMissing("x", "/"));
        Assert.assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z", "/"));
        Assert.assertEquals("/x/y/z/", StringUtils.wrapIfMissing("/x/y/z", "/"));
        Assert.assertEquals("/x/y/z/", StringUtils.wrapIfMissing("x/y/z/", "/"));
        Assert.assertEquals("/", StringUtils.wrapIfMissing("/", "/"));
        Assert.assertEquals("ab/ab", StringUtils.wrapIfMissing("/", "ab"));
        Assert.assertEquals("ab/ab", StringUtils.wrapIfMissing("ab/ab", "ab"));
    }

    @Test
    public void testWrap_StringString() {
        Assert.assertNull(StringUtils.wrap(null, null));
        Assert.assertNull(StringUtils.wrap(null, ""));
        Assert.assertNull(StringUtils.wrap(null, "1"));

        Assert.assertEquals(null, StringUtils.wrap(null, null));
        Assert.assertEquals("", StringUtils.wrap("", ""));
        Assert.assertEquals("ab", StringUtils.wrap("ab", null));
        Assert.assertEquals("xabx", StringUtils.wrap("ab", "x"));
        Assert.assertEquals("\"ab\"", StringUtils.wrap("ab", "\""));
        Assert.assertEquals("\"\"ab\"\"", StringUtils.wrap("\"ab\"", "\""));
        Assert.assertEquals("'ab'", StringUtils.wrap("ab", "'"));
        Assert.assertEquals("''abcd''", StringUtils.wrap("'abcd'", "'"));
        Assert.assertEquals("'\"abcd\"'", StringUtils.wrap("\"abcd\"", "'"));
        Assert.assertEquals("\"'abcd'\"", StringUtils.wrap("'abcd'", "\""));
    }
}
