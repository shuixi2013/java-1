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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.test.SystemDefaultsSwitch;
import org.apache.commons.lang3.test.SystemDefaults;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Contains methods
 */
public class StringUtilsContainsTest  {

    @Rule
    public SystemDefaultsSwitch defaults = new SystemDefaultsSwitch();

    /**
     * Supplementary character U+20000
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharU20000 = "\uD840\uDC00";
    /**
     * Supplementary character U+20001
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharU20001 = "\uD840\uDC01";
    /**
     * Incomplete supplementary character U+20000, high surrogate only.
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharUSuppCharHigh = "\uDC00";

    /**
     * Incomplete supplementary character U+20000, low surrogate only.
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    private static final String CharUSuppCharLow = "\uD840";

    @Test
    public void testContains_Char() {
        Assert.assertFalse(StringUtils.contains(null, ' '));
        Assert.assertFalse(StringUtils.contains("", ' '));
        Assert.assertFalse(StringUtils.contains("", null));
        Assert.assertFalse(StringUtils.contains(null, null));
        Assert.assertTrue(StringUtils.contains("abc", 'a'));
        Assert.assertTrue(StringUtils.contains("abc", 'b'));
        Assert.assertTrue(StringUtils.contains("abc", 'c'));
        Assert.assertFalse(StringUtils.contains("abc", 'z'));
    }

    @Test
    public void testContains_String() {
        Assert.assertFalse(StringUtils.contains(null, null));
        Assert.assertFalse(StringUtils.contains(null, ""));
        Assert.assertFalse(StringUtils.contains(null, "a"));
        Assert.assertFalse(StringUtils.contains("", null));
        Assert.assertTrue(StringUtils.contains("", ""));
        Assert.assertFalse(StringUtils.contains("", "a"));
        Assert.assertTrue(StringUtils.contains("abc", "a"));
        Assert.assertTrue(StringUtils.contains("abc", "b"));
        Assert.assertTrue(StringUtils.contains("abc", "c"));
        Assert.assertTrue(StringUtils.contains("abc", "abc"));
        Assert.assertFalse(StringUtils.contains("abc", "z"));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContains_StringWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        Assert.assertFalse(StringUtils.contains(CharUSuppCharHigh, CharU20001));
        Assert.assertFalse(StringUtils.contains(CharUSuppCharLow, CharU20001));
        Assert.assertFalse(StringUtils.contains(CharU20001, CharUSuppCharHigh));
        Assert.assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        Assert.assertTrue(StringUtils.contains(CharU20001, CharUSuppCharLow));
        Assert.assertTrue(StringUtils.contains(CharU20001 + CharUSuppCharLow + "a", "a"));
        Assert.assertTrue(StringUtils.contains(CharU20001 + CharUSuppCharHigh + "a", "a"));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContains_StringWithSupplementaryChars() {
        Assert.assertTrue(StringUtils.contains(CharU20000 + CharU20001, CharU20000));
        Assert.assertTrue(StringUtils.contains(CharU20000 + CharU20001, CharU20001));
        Assert.assertTrue(StringUtils.contains(CharU20000, CharU20000));
        Assert.assertFalse(StringUtils.contains(CharU20000, CharU20001));
    }

    @Test
    public void testContainsAny_StringCharArray() {
        Assert.assertFalse(StringUtils.containsAny(null, (char[]) null));
        Assert.assertFalse(StringUtils.containsAny(null, new char[0]));
        Assert.assertFalse(StringUtils.containsAny(null, 'a', 'b'));

        Assert.assertFalse(StringUtils.containsAny("", (char[]) null));
        Assert.assertFalse(StringUtils.containsAny("", new char[0]));
        Assert.assertFalse(StringUtils.containsAny("", 'a', 'b'));

        Assert.assertFalse(StringUtils.containsAny("zzabyycdxx", (char[]) null));
        Assert.assertFalse(StringUtils.containsAny("zzabyycdxx", new char[0]));
        Assert.assertTrue(StringUtils.containsAny("zzabyycdxx", 'z', 'a'));
        Assert.assertTrue(StringUtils.containsAny("zzabyycdxx", 'b', 'y'));
        Assert.assertTrue(StringUtils.containsAny("zzabyycdxx", 'z', 'y'));
        Assert.assertFalse(StringUtils.containsAny("ab", 'z'));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringCharArrayWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        Assert.assertFalse(StringUtils.containsAny(CharUSuppCharHigh, CharU20001.toCharArray()));
        Assert.assertFalse(StringUtils.containsAny("abc" + CharUSuppCharHigh + "xyz", CharU20001.toCharArray()));
        Assert.assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        Assert.assertFalse(StringUtils.containsAny(CharUSuppCharLow, CharU20001.toCharArray()));
        Assert.assertFalse(StringUtils.containsAny(CharU20001, CharUSuppCharHigh.toCharArray()));
        Assert.assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        Assert.assertTrue(StringUtils.containsAny(CharU20001, CharUSuppCharLow.toCharArray()));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringCharArrayWithSupplementaryChars() {
        Assert.assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20000.toCharArray()));
        Assert.assertTrue(StringUtils.containsAny("a" + CharU20000 + CharU20001, "a".toCharArray()));
        Assert.assertTrue(StringUtils.containsAny(CharU20000 + "a" + CharU20001, "a".toCharArray()));
        Assert.assertTrue(StringUtils.containsAny(CharU20000 + CharU20001 + "a", "a".toCharArray()));
        Assert.assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20001.toCharArray()));
        Assert.assertTrue(StringUtils.containsAny(CharU20000, CharU20000.toCharArray()));
        // Sanity check:
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001));
        Assert.assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        Assert.assertFalse(StringUtils.containsAny(CharU20000, CharU20001.toCharArray()));
        Assert.assertFalse(StringUtils.containsAny(CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsAny_StringString() {
        Assert.assertFalse(StringUtils.containsAny(null, (String) null));
        Assert.assertFalse(StringUtils.containsAny(null, ""));
        Assert.assertFalse(StringUtils.containsAny(null, "ab"));

        Assert.assertFalse(StringUtils.containsAny("", (String) null));
        Assert.assertFalse(StringUtils.containsAny("", ""));
        Assert.assertFalse(StringUtils.containsAny("", "ab"));

        Assert.assertFalse(StringUtils.containsAny("zzabyycdxx", (String) null));
        Assert.assertFalse(StringUtils.containsAny("zzabyycdxx", ""));
        Assert.assertTrue(StringUtils.containsAny("zzabyycdxx", "za"));
        Assert.assertTrue(StringUtils.containsAny("zzabyycdxx", "by"));
        Assert.assertTrue(StringUtils.containsAny("zzabyycdxx", "zy"));
        Assert.assertFalse(StringUtils.containsAny("ab", "z"));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        Assert.assertFalse(StringUtils.containsAny(CharUSuppCharHigh, CharU20001));
        Assert.assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        Assert.assertFalse(StringUtils.containsAny(CharUSuppCharLow, CharU20001));
        Assert.assertFalse(StringUtils.containsAny(CharU20001, CharUSuppCharHigh));
        Assert.assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        Assert.assertTrue(StringUtils.containsAny(CharU20001, CharUSuppCharLow));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsAny_StringWithSupplementaryChars() {
        Assert.assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20000));
        Assert.assertTrue(StringUtils.containsAny(CharU20000 + CharU20001, CharU20001));
        Assert.assertTrue(StringUtils.containsAny(CharU20000, CharU20000));
        // Sanity check:
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001));
        Assert.assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        Assert.assertFalse(StringUtils.containsAny(CharU20000, CharU20001));
        Assert.assertFalse(StringUtils.containsAny(CharU20001, CharU20000));
    }
    
    @Test
    public void testContainsAny_StringStringArray() {
        Assert.assertFalse(StringUtils.containsAny(null, (String[]) null));
        Assert.assertFalse(StringUtils.containsAny(null, new String[0]));
        Assert.assertFalse(StringUtils.containsAny(null, new String[] { "hello" }));
        Assert.assertFalse(StringUtils.containsAny("", (String[]) null));
        Assert.assertFalse(StringUtils.containsAny("", new String[0]));
        Assert.assertFalse(StringUtils.containsAny("", new String[] { "hello" }));
        Assert.assertFalse(StringUtils.containsAny("hello, goodbye", (String[]) null));
        Assert.assertFalse(StringUtils.containsAny("hello, goodbye", new String[0]));
        Assert.assertTrue(StringUtils.containsAny("hello, goodbye", new String[]{"hello", "goodbye"}));
        Assert.assertTrue(StringUtils.containsAny("hello, goodbye", new String[]{"hello", "Goodbye"}));
        Assert.assertFalse(StringUtils.containsAny("hello, goodbye", new String[]{"Hello", "Goodbye"}));
        Assert.assertFalse(StringUtils.containsAny("hello, goodbye", new String[]{"Hello", null}));
        Assert.assertFalse(StringUtils.containsAny("hello, null", new String[] { "Hello", null }));
        // Javadoc examples:
        Assert.assertTrue(StringUtils.containsAny("abcd", "ab", null));
        Assert.assertTrue(StringUtils.containsAny("abcd", "ab", "cd"));
        Assert.assertTrue(StringUtils.containsAny("abc", "d", "abc"));
    }

    @SystemDefaults(locale="de_DE")
    @Test
    public void testContainsIgnoreCase_LocaleIndependence() {
        final Locale[] locales = { Locale.ENGLISH, new Locale("tr"), Locale.getDefault() };

        final String[][] tdata = {
            { "i", "I" },
            { "I", "i" },
            { "\u03C2", "\u03C3" },
            { "\u03A3", "\u03C2" },
            { "\u03A3", "\u03C3" },
        };

        final String[][] fdata = {
            { "\u00DF", "SS" },
        };

        for (final Locale testLocale : locales) {
            Locale.setDefault(testLocale);
            for (int j = 0; j < tdata.length; j++) {
                Assert.assertTrue(Locale.getDefault() + ": " + j + " " + tdata[j][0] + " " + tdata[j][1], StringUtils
                        .containsIgnoreCase(tdata[j][0], tdata[j][1]));
            }
            for (int j = 0; j < fdata.length; j++) {
                Assert.assertFalse(Locale.getDefault() + ": " + j + " " + fdata[j][0] + " " + fdata[j][1], StringUtils
                        .containsIgnoreCase(fdata[j][0], fdata[j][1]));
            }
        }
    }

    @Test
    public void testContainsIgnoreCase_StringString() {
        Assert.assertFalse(StringUtils.containsIgnoreCase(null, null));

        // Null tests
        Assert.assertFalse(StringUtils.containsIgnoreCase(null, ""));
        Assert.assertFalse(StringUtils.containsIgnoreCase(null, "a"));
        Assert.assertFalse(StringUtils.containsIgnoreCase(null, "abc"));

        Assert.assertFalse(StringUtils.containsIgnoreCase("", null));
        Assert.assertFalse(StringUtils.containsIgnoreCase("a", null));
        Assert.assertFalse(StringUtils.containsIgnoreCase("abc", null));

        // Match len = 0
        Assert.assertTrue(StringUtils.containsIgnoreCase("", ""));
        Assert.assertTrue(StringUtils.containsIgnoreCase("a", ""));
        Assert.assertTrue(StringUtils.containsIgnoreCase("abc", ""));

        // Match len = 1
        Assert.assertFalse(StringUtils.containsIgnoreCase("", "a"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("a", "a"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("abc", "a"));
        Assert.assertFalse(StringUtils.containsIgnoreCase("", "A"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("a", "A"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("abc", "A"));

        // Match len > 1
        Assert.assertFalse(StringUtils.containsIgnoreCase("", "abc"));
        Assert.assertFalse(StringUtils.containsIgnoreCase("a", "abc"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("xabcz", "abc"));
        Assert.assertFalse(StringUtils.containsIgnoreCase("", "ABC"));
        Assert.assertFalse(StringUtils.containsIgnoreCase("a", "ABC"));
        Assert.assertTrue(StringUtils.containsIgnoreCase("xabcz", "ABC"));
    }

    @Test
    public void testContainsNone_CharArray() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final char[] chars1= {'b'};
        final char[] chars2= {'.'};
        final char[] chars3= {'c', 'd'};
        final char[] emptyChars = new char[0];
        Assert.assertTrue(StringUtils.containsNone(null, (char[]) null));
        Assert.assertTrue(StringUtils.containsNone("", (char[]) null));
        Assert.assertTrue(StringUtils.containsNone(null, emptyChars));
        Assert.assertTrue(StringUtils.containsNone(str1, emptyChars));
        Assert.assertTrue(StringUtils.containsNone("", emptyChars));
        Assert.assertTrue(StringUtils.containsNone("", chars1));
        Assert.assertTrue(StringUtils.containsNone(str1, chars1));
        Assert.assertTrue(StringUtils.containsNone(str1, chars2));
        Assert.assertTrue(StringUtils.containsNone(str1, chars3));
        Assert.assertFalse(StringUtils.containsNone(str2, chars1));
        Assert.assertTrue(StringUtils.containsNone(str2, chars2));
        Assert.assertTrue(StringUtils.containsNone(str2, chars3));
        Assert.assertFalse(StringUtils.containsNone(str3, chars1));
        Assert.assertFalse(StringUtils.containsNone(str3, chars2));
        Assert.assertTrue(StringUtils.containsNone(str3, chars3));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_CharArrayWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        Assert.assertTrue(StringUtils.containsNone(CharUSuppCharHigh, CharU20001.toCharArray()));
        Assert.assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        Assert.assertTrue(StringUtils.containsNone(CharUSuppCharLow, CharU20001.toCharArray()));
        Assert.assertEquals(-1, CharU20001.indexOf(CharUSuppCharHigh));
        Assert.assertTrue(StringUtils.containsNone(CharU20001, CharUSuppCharHigh.toCharArray()));
        Assert.assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        Assert.assertFalse(StringUtils.containsNone(CharU20001, CharUSuppCharLow.toCharArray()));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_CharArrayWithSupplementaryChars() {
        Assert.assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20000.toCharArray()));
        Assert.assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20001.toCharArray()));
        Assert.assertFalse(StringUtils.containsNone(CharU20000, CharU20000.toCharArray()));
        // Sanity check:
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001));
        Assert.assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        Assert.assertTrue(StringUtils.containsNone(CharU20000, CharU20001.toCharArray()));
        Assert.assertTrue(StringUtils.containsNone(CharU20001, CharU20000.toCharArray()));
    }

    @Test
    public void testContainsNone_String() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab.";
        final String chars1= "b";
        final String chars2= ".";
        final String chars3= "cd";
        Assert.assertTrue(StringUtils.containsNone(null, (String) null));
        Assert.assertTrue(StringUtils.containsNone("", (String) null));
        Assert.assertTrue(StringUtils.containsNone(null, ""));
        Assert.assertTrue(StringUtils.containsNone(str1, ""));
        Assert.assertTrue(StringUtils.containsNone("", ""));
        Assert.assertTrue(StringUtils.containsNone("", chars1));
        Assert.assertTrue(StringUtils.containsNone(str1, chars1));
        Assert.assertTrue(StringUtils.containsNone(str1, chars2));
        Assert.assertTrue(StringUtils.containsNone(str1, chars3));
        Assert.assertFalse(StringUtils.containsNone(str2, chars1));
        Assert.assertTrue(StringUtils.containsNone(str2, chars2));
        Assert.assertTrue(StringUtils.containsNone(str2, chars3));
        Assert.assertFalse(StringUtils.containsNone(str3, chars1));
        Assert.assertFalse(StringUtils.containsNone(str3, chars2));
        Assert.assertTrue(StringUtils.containsNone(str3, chars3));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_StringWithBadSupplementaryChars() {
        // Test edge case: 1/2 of a (broken) supplementary char
        Assert.assertTrue(StringUtils.containsNone(CharUSuppCharHigh, CharU20001));
        Assert.assertEquals(-1, CharUSuppCharLow.indexOf(CharU20001));
        Assert.assertTrue(StringUtils.containsNone(CharUSuppCharLow, CharU20001));
        Assert.assertEquals(-1, CharU20001.indexOf(CharUSuppCharHigh));
        Assert.assertTrue(StringUtils.containsNone(CharU20001, CharUSuppCharHigh));
        Assert.assertEquals(0, CharU20001.indexOf(CharUSuppCharLow));
        Assert.assertFalse(StringUtils.containsNone(CharU20001, CharUSuppCharLow));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testContainsNone_StringWithSupplementaryChars() {
        Assert.assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20000));
        Assert.assertFalse(StringUtils.containsNone(CharU20000 + CharU20001, CharU20001));
        Assert.assertFalse(StringUtils.containsNone(CharU20000, CharU20000));
        // Sanity check:
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001));
        Assert.assertEquals(0, CharU20000.indexOf(CharU20001.charAt(0)));
        Assert.assertEquals(-1, CharU20000.indexOf(CharU20001.charAt(1)));
        // Test:
        Assert.assertTrue(StringUtils.containsNone(CharU20000, CharU20001));
        Assert.assertTrue(StringUtils.containsNone(CharU20001, CharU20000));
    }

    @Test
    public void testContainsOnly_CharArray() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final char[] chars1= {'b'};
        final char[] chars2= {'a'};
        final char[] chars3= {'a', 'b'};
        final char[] emptyChars = new char[0];
        Assert.assertFalse(StringUtils.containsOnly(null, (char[]) null));
        Assert.assertFalse(StringUtils.containsOnly("", (char[]) null));
        Assert.assertFalse(StringUtils.containsOnly(null, emptyChars));
        Assert.assertFalse(StringUtils.containsOnly(str1, emptyChars));
        Assert.assertTrue(StringUtils.containsOnly("", emptyChars));
        Assert.assertTrue(StringUtils.containsOnly("", chars1));
        Assert.assertFalse(StringUtils.containsOnly(str1, chars1));
        Assert.assertTrue(StringUtils.containsOnly(str1, chars2));
        Assert.assertTrue(StringUtils.containsOnly(str1, chars3));
        Assert.assertTrue(StringUtils.containsOnly(str2, chars1));
        Assert.assertFalse(StringUtils.containsOnly(str2, chars2));
        Assert.assertTrue(StringUtils.containsOnly(str2, chars3));
        Assert.assertFalse(StringUtils.containsOnly(str3, chars1));
        Assert.assertFalse(StringUtils.containsOnly(str3, chars2));
        Assert.assertTrue(StringUtils.containsOnly(str3, chars3));
    }

    @Test
    public void testContainsOnly_String() {
        final String str1 = "a";
        final String str2 = "b";
        final String str3 = "ab";
        final String chars1= "b";
        final String chars2= "a";
        final String chars3= "ab";
        Assert.assertFalse(StringUtils.containsOnly(null, (String) null));
        Assert.assertFalse(StringUtils.containsOnly("", (String) null));
        Assert.assertFalse(StringUtils.containsOnly(null, ""));
        Assert.assertFalse(StringUtils.containsOnly(str1, ""));
        Assert.assertTrue(StringUtils.containsOnly("", ""));
        Assert.assertTrue(StringUtils.containsOnly("", chars1));
        Assert.assertFalse(StringUtils.containsOnly(str1, chars1));
        Assert.assertTrue(StringUtils.containsOnly(str1, chars2));
        Assert.assertTrue(StringUtils.containsOnly(str1, chars3));
        Assert.assertTrue(StringUtils.containsOnly(str2, chars1));
        Assert.assertFalse(StringUtils.containsOnly(str2, chars2));
        Assert.assertTrue(StringUtils.containsOnly(str2, chars3));
        Assert.assertFalse(StringUtils.containsOnly(str3, chars1));
        Assert.assertFalse(StringUtils.containsOnly(str3, chars2));
        Assert.assertTrue(StringUtils.containsOnly(str3, chars3));
    }

    @Test
    public void testContainsWhitespace() {
        Assert.assertFalse( StringUtils.containsWhitespace("") );
        Assert.assertTrue( StringUtils.containsWhitespace(" ") );
        Assert.assertFalse( StringUtils.containsWhitespace("a") );
        Assert.assertTrue( StringUtils.containsWhitespace("a ") );
        Assert.assertTrue( StringUtils.containsWhitespace(" a") );
        Assert.assertTrue( StringUtils.containsWhitespace("a\t") );
        Assert.assertTrue( StringUtils.containsWhitespace("\n") );
    }
}
