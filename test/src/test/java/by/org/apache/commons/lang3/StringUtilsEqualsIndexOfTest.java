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
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.test.SystemDefaultsSwitch;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Equals/IndexOf methods
 */
public class StringUtilsEqualsIndexOfTest  {

    @Rule
    public SystemDefaultsSwitch defaults = new SystemDefaultsSwitch();

    private static final String BAR = "bar";
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

    private static final String FOO = "foo";

    private static final String FOOBAR = "foobar";

    private static final String[] FOOBAR_SUB_ARRAY = new String[] {"ob", "ba"};

    // The purpose of this class is to test StringUtils#equals(CharSequence, CharSequence)
    // with a CharSequence implementation whose equals(Object) override requires that the
    // other object be an instance of CustomCharSequence, even though, as char sequences,
    // `seq` may equal the other object.
    private static class CustomCharSequence implements CharSequence {
        private final CharSequence seq;

        public CustomCharSequence(final CharSequence seq) {
            this.seq = seq;
        }

        @Override
        public char charAt(final int index) {
            return seq.charAt(index);
        }

        @Override
        public int length() {
            return seq.length();
        }

        @Override
        public CharSequence subSequence(final int start, final int end) {
            return new CustomCharSequence(seq.subSequence(start, end));
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj == null || !(obj instanceof CustomCharSequence)) {
                return false;
            }
            final CustomCharSequence other = (CustomCharSequence) obj;
            return seq.equals(other.seq);
        }

        @Override
        public int hashCode() {
            return seq.hashCode();
        }

        @Override
        public String toString() {
            return seq.toString();
        }
    }

    @Test
    public void testCustomCharSequence() {
        Assert.assertThat(new CustomCharSequence(FOO), IsNot.<CharSequence>not(FOO));
        Assert.assertThat(FOO, IsNot.<CharSequence>not(new CustomCharSequence(FOO)));
        Assert.assertEquals(new CustomCharSequence(FOO), new CustomCharSequence(FOO));
    }

    @Test
    public void testEquals() {
        final CharSequence fooCs = new StringBuilder(FOO), barCs = new StringBuilder(BAR), foobarCs = new StringBuilder(FOOBAR);
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertTrue(StringUtils.equals(fooCs, fooCs));
        Assert.assertTrue(StringUtils.equals(fooCs, new StringBuilder(FOO)));
        Assert.assertTrue(StringUtils.equals(fooCs, new String(new char[] { 'f', 'o', 'o' })));
        Assert.assertTrue(StringUtils.equals(fooCs, new CustomCharSequence(FOO)));
        Assert.assertTrue(StringUtils.equals(new CustomCharSequence(FOO), fooCs));
        Assert.assertFalse(StringUtils.equals(fooCs, new String(new char[] { 'f', 'O', 'O' })));
        Assert.assertFalse(StringUtils.equals(fooCs, barCs));
        Assert.assertFalse(StringUtils.equals(fooCs, null));
        Assert.assertFalse(StringUtils.equals(null, fooCs));
        Assert.assertFalse(StringUtils.equals(fooCs, foobarCs));
        Assert.assertFalse(StringUtils.equals(foobarCs, fooCs));
    }

    @Test
    public void testEqualsOnStrings() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertTrue(StringUtils.equals(FOO, FOO));
        Assert.assertTrue(StringUtils.equals(FOO, new String(new char[] { 'f', 'o', 'o' })));
        Assert.assertFalse(StringUtils.equals(FOO, new String(new char[] { 'f', 'O', 'O' })));
        Assert.assertFalse(StringUtils.equals(FOO, BAR));
        Assert.assertFalse(StringUtils.equals(FOO, null));
        Assert.assertFalse(StringUtils.equals(null, FOO));
        Assert.assertFalse(StringUtils.equals(FOO, FOOBAR));
        Assert.assertFalse(StringUtils.equals(FOOBAR, FOO));
    }

    @Test
    public void testEqualsIgnoreCase() {
        Assert.assertTrue(StringUtils.equalsIgnoreCase(null, null));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(FOO, FOO));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(FOO, new String(new char[] { 'f', 'o', 'o' })));
        Assert.assertTrue(StringUtils.equalsIgnoreCase(FOO, new String(new char[] { 'f', 'O', 'O' })));
        Assert.assertFalse(StringUtils.equalsIgnoreCase(FOO, BAR));
        Assert.assertFalse(StringUtils.equalsIgnoreCase(FOO, null));
        Assert.assertFalse(StringUtils.equalsIgnoreCase(null, FOO));
        Assert.assertTrue(StringUtils.equalsIgnoreCase("",""));
        Assert.assertFalse(StringUtils.equalsIgnoreCase("abcd","abcd "));
    }

    @Test
    public void testEqualsAny() {
        Assert.assertFalse(StringUtils.equalsAny(FOO));
        Assert.assertFalse(StringUtils.equalsAny(FOO, new String[]{}));

        Assert.assertTrue(StringUtils.equalsAny(FOO, FOO));
        Assert.assertTrue(StringUtils.equalsAny(FOO, BAR, new String(new char[] { 'f', 'o', 'o' })));
        Assert.assertFalse(StringUtils.equalsAny(FOO, BAR, new String(new char[] { 'f', 'O', 'O' })));
        Assert.assertFalse(StringUtils.equalsAny(FOO, BAR));
        Assert.assertFalse(StringUtils.equalsAny(FOO, BAR, null));
        Assert.assertFalse(StringUtils.equalsAny(null, FOO));
        Assert.assertFalse(StringUtils.equalsAny(FOO, FOOBAR));
        Assert.assertFalse(StringUtils.equalsAny(FOOBAR, FOO));

        Assert.assertTrue(StringUtils.equalsAny(null, null, null));
        Assert.assertFalse(StringUtils.equalsAny(null, FOO, BAR, FOOBAR));
        Assert.assertFalse(StringUtils.equalsAny(FOO, null, BAR));
        Assert.assertTrue(StringUtils.equalsAny(FOO, BAR, null, "", FOO, BAR));
        Assert.assertFalse(StringUtils.equalsAny(FOO, FOO.toUpperCase()));

        Assert.assertFalse(StringUtils.equalsAny(null, (CharSequence[]) null));
        Assert.assertTrue(StringUtils.equalsAny(FOO, new CustomCharSequence("foo")));
        Assert.assertTrue(StringUtils.equalsAny(FOO, new StringBuilder("foo")));
        Assert.assertFalse(StringUtils.equalsAny(FOO, new CustomCharSequence("fOo")));
        Assert.assertFalse(StringUtils.equalsAny(FOO, new StringBuilder("fOo")));
    }

    @Test
    public void testEqualsAnyIgnoreCase() {
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOO));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, new String[]{}));

        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO.toUpperCase()));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO, new String(new char[]{'f', 'o', 'o'})));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, BAR, new String(new char[]{'f', 'O', 'O'})));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, BAR));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, BAR, null));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(null, FOO));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, FOOBAR));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOOBAR, FOO));

        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(null, null, null));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(null, FOO, BAR, FOOBAR));
        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(FOO, null, BAR));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, BAR, null, "", FOO.toUpperCase(), BAR));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, FOO.toUpperCase()));

        Assert.assertFalse(StringUtils.equalsAnyIgnoreCase(null, (CharSequence[]) null));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, new CustomCharSequence("fOo")));
        Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(FOO, new StringBuilder("fOo")));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCompare_StringString() {
        Assert.assertTrue(StringUtils.compare(null, null) == 0);
        Assert.assertTrue(StringUtils.compare(null, "a") < 0);
        Assert.assertTrue(StringUtils.compare("a", null) > 0);
        Assert.assertTrue(StringUtils.compare("abc", "abc") == 0);
        Assert.assertTrue(StringUtils.compare("a", "b") < 0);
        Assert.assertTrue(StringUtils.compare("b", "a") > 0);
        Assert.assertTrue(StringUtils.compare("a", "B") > 0);
        Assert.assertTrue(StringUtils.compare("abc", "abd") < 0);
        Assert.assertTrue(StringUtils.compare("ab", "abc") < 0);
        Assert.assertTrue(StringUtils.compare("ab", "ab ") < 0);
        Assert.assertTrue(StringUtils.compare("abc", "ab ") > 0);
    }

    @Test
    public void testCompare_StringStringBoolean() {
        Assert.assertTrue(StringUtils.compare(null, null, false) == 0);
        Assert.assertTrue(StringUtils.compare(null, "a", true) < 0);
        Assert.assertTrue(StringUtils.compare(null, "a", false) > 0);
        Assert.assertTrue(StringUtils.compare("a", null, true) > 0);
        Assert.assertTrue(StringUtils.compare("a", null, false) < 0);
        Assert.assertTrue(StringUtils.compare("abc", "abc", false) == 0);
        Assert.assertTrue(StringUtils.compare("a", "b", false) < 0);
        Assert.assertTrue(StringUtils.compare("b", "a", false) > 0);
        Assert.assertTrue(StringUtils.compare("a", "B", false) > 0);
        Assert.assertTrue(StringUtils.compare("abc", "abd", false) < 0);
        Assert.assertTrue(StringUtils.compare("ab", "abc", false) < 0);
        Assert.assertTrue(StringUtils.compare("ab", "ab ", false) < 0);
        Assert.assertTrue(StringUtils.compare("abc", "ab ", false) > 0);
    }

    @Test
    public void testCompareIgnoreCase_StringString() {
        Assert.assertTrue(StringUtils.compareIgnoreCase(null, null) == 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase(null, "a") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", null) > 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "abc") == 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "ABC") == 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", "b") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("b", "a") > 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", "B") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("A", "b") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "ABD") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("ab", "ABC") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("ab", "AB ") < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "AB ") > 0);
    }

    @Test
    public void testCompareIgnoreCase_StringStringBoolean() {
        Assert.assertTrue(StringUtils.compareIgnoreCase(null, null, false) == 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase(null, "a", true) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase(null, "a", false) > 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", null, true) > 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", null, false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "abc", false) == 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "ABC", false) == 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", "b", false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("b", "a", false) > 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("a", "B", false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("A", "b", false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "ABD", false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("ab", "ABC", false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("ab", "AB ", false) < 0);
        Assert.assertTrue(StringUtils.compareIgnoreCase("abc", "AB ", false) > 0);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIndexOf_char() {
        Assert.assertEquals(-1, StringUtils.indexOf(null, ' '));
        Assert.assertEquals(-1, StringUtils.indexOf("", ' '));
        Assert.assertEquals(0, StringUtils.indexOf("aabaabaa", 'a'));
        Assert.assertEquals(2, StringUtils.indexOf("aabaabaa", 'b'));

        Assert.assertEquals(2, StringUtils.indexOf(new StringBuilder("aabaabaa"), 'b'));
    }

    @Test
    public void testIndexOf_charInt() {
        Assert.assertEquals(-1, StringUtils.indexOf(null, ' ', 0));
        Assert.assertEquals(-1, StringUtils.indexOf(null, ' ', -1));
        Assert.assertEquals(-1, StringUtils.indexOf("", ' ', 0));
        Assert.assertEquals(-1, StringUtils.indexOf("", ' ', -1));
        Assert.assertEquals(0, StringUtils.indexOf("aabaabaa", 'a', 0));
        Assert.assertEquals(2, StringUtils.indexOf("aabaabaa", 'b', 0));
        Assert.assertEquals(5, StringUtils.indexOf("aabaabaa", 'b', 3));
        Assert.assertEquals(-1, StringUtils.indexOf("aabaabaa", 'b', 9));
        Assert.assertEquals(2, StringUtils.indexOf("aabaabaa", 'b', -1));

        Assert.assertEquals(5, StringUtils.indexOf(new StringBuilder("aabaabaa"), 'b', 3));
    }

    @Test
    public void testIndexOf_String() {
        Assert.assertEquals(-1, StringUtils.indexOf(null, null));
        Assert.assertEquals(-1, StringUtils.indexOf("", null));
        Assert.assertEquals(0, StringUtils.indexOf("", ""));
        Assert.assertEquals(0, StringUtils.indexOf("aabaabaa", "a"));
        Assert.assertEquals(2, StringUtils.indexOf("aabaabaa", "b"));
        Assert.assertEquals(1, StringUtils.indexOf("aabaabaa", "ab"));
        Assert.assertEquals(0, StringUtils.indexOf("aabaabaa", ""));

        Assert.assertEquals(2, StringUtils.indexOf(new StringBuilder("aabaabaa"), "b"));
    }

    @Test
    public void testIndexOf_StringInt() {
        Assert.assertEquals(-1, StringUtils.indexOf(null, null, 0));
        Assert.assertEquals(-1, StringUtils.indexOf(null, null, -1));
        Assert.assertEquals(-1, StringUtils.indexOf(null, "", 0));
        Assert.assertEquals(-1, StringUtils.indexOf(null, "", -1));
        Assert.assertEquals(-1, StringUtils.indexOf("", null, 0));
        Assert.assertEquals(-1, StringUtils.indexOf("", null, -1));
        Assert.assertEquals(0, StringUtils.indexOf("", "", 0));
        Assert.assertEquals(0, StringUtils.indexOf("", "", -1));
        Assert.assertEquals(0, StringUtils.indexOf("", "", 9));
        Assert.assertEquals(0, StringUtils.indexOf("abc", "", 0));
        Assert.assertEquals(0, StringUtils.indexOf("abc", "", -1));
        Assert.assertEquals(3, StringUtils.indexOf("abc", "", 9));
        Assert.assertEquals(3, StringUtils.indexOf("abc", "", 3));
        Assert.assertEquals(0, StringUtils.indexOf("aabaabaa", "a", 0));
        Assert.assertEquals(2, StringUtils.indexOf("aabaabaa", "b", 0));
        Assert.assertEquals(1, StringUtils.indexOf("aabaabaa", "ab", 0));
        Assert.assertEquals(5, StringUtils.indexOf("aabaabaa", "b", 3));
        Assert.assertEquals(-1, StringUtils.indexOf("aabaabaa", "b", 9));
        Assert.assertEquals(2, StringUtils.indexOf("aabaabaa", "b", -1));
        Assert.assertEquals(2,StringUtils.indexOf("aabaabaa", "", 2));

        // Test that startIndex works correctly, i.e. cannot match before startIndex
        Assert.assertEquals(7, StringUtils.indexOf("12345678", "8", 5));
        Assert.assertEquals(7, StringUtils.indexOf("12345678", "8", 6));
        Assert.assertEquals(7, StringUtils.indexOf("12345678", "8", 7)); // 7 is last index
        Assert.assertEquals(-1, StringUtils.indexOf("12345678", "8", 8));

        Assert.assertEquals(5, StringUtils.indexOf(new StringBuilder("aabaabaa"), "b", 3));
    }

    @Test
    public void testIndexOfAny_StringCharArray() {
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, (char[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, new char[0]));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, 'a','b'));

        Assert.assertEquals(-1, StringUtils.indexOfAny("", (char[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", new char[0]));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", 'a','b'));

        Assert.assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", (char[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", new char[0]));
        Assert.assertEquals(0, StringUtils.indexOfAny("zzabyycdxx", 'z','a'));
        Assert.assertEquals(3, StringUtils.indexOfAny("zzabyycdxx", 'b','y'));
        Assert.assertEquals(-1, StringUtils.indexOfAny("ab", 'z'));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testIndexOfAny_StringCharArrayWithSupplementaryChars() {
        Assert.assertEquals(0, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20000.toCharArray()));
        Assert.assertEquals(2, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20001.toCharArray()));
        Assert.assertEquals(0, StringUtils.indexOfAny(CharU20000, CharU20000.toCharArray()));
        Assert.assertEquals(-1, StringUtils.indexOfAny(CharU20000, CharU20001.toCharArray()));
    }

    @Test
    public void testIndexOfAny_StringString() {
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, (String) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, ""));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, "ab"));

        Assert.assertEquals(-1, StringUtils.indexOfAny("", (String) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", ""));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", "ab"));

        Assert.assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", (String) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny("zzabyycdxx", ""));
        Assert.assertEquals(0, StringUtils.indexOfAny("zzabyycdxx", "za"));
        Assert.assertEquals(3, StringUtils.indexOfAny("zzabyycdxx", "by"));
        Assert.assertEquals(-1, StringUtils.indexOfAny("ab", "z"));
    }

    @Test
    public void testIndexOfAny_StringStringArray() {
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, (String[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, FOOBAR_SUB_ARRAY));
        Assert.assertEquals(-1, StringUtils.indexOfAny(FOOBAR, (String[]) null));
        Assert.assertEquals(2, StringUtils.indexOfAny(FOOBAR, FOOBAR_SUB_ARRAY));
        Assert.assertEquals(-1, StringUtils.indexOfAny(FOOBAR, new String[0]));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, new String[0]));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", new String[0]));
        Assert.assertEquals(-1, StringUtils.indexOfAny(FOOBAR, new String[] {"llll"}));
        Assert.assertEquals(0, StringUtils.indexOfAny(FOOBAR, new String[] {""}));
        Assert.assertEquals(0, StringUtils.indexOfAny("", new String[] {""}));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", new String[] {"a"}));
        Assert.assertEquals(-1, StringUtils.indexOfAny("", new String[] {null}));
        Assert.assertEquals(-1, StringUtils.indexOfAny(FOOBAR, new String[] {null}));
        Assert.assertEquals(-1, StringUtils.indexOfAny(null, new String[] {null}));
    }

    /**
     * See http://www.oracle.com/technetwork/articles/javase/supplementary-142654.html
     */
    @Test
    public void testIndexOfAny_StringStringWithSupplementaryChars() {
        Assert.assertEquals(0, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20000));
        Assert.assertEquals(2, StringUtils.indexOfAny(CharU20000 + CharU20001, CharU20001));
        Assert.assertEquals(0, StringUtils.indexOfAny(CharU20000, CharU20000));
        Assert.assertEquals(-1, StringUtils.indexOfAny(CharU20000, CharU20001));
    }

    @Test
    public void testIndexOfAnyBut_StringCharArray() {
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(null, (char[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(null, 'a','b'));

        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("", (char[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(""));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("", 'a','b'));

        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx", (char[]) null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx"));
        Assert.assertEquals(3, StringUtils.indexOfAnyBut("zzabyycdxx", 'z','a'));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut("zzabyycdxx", 'b','y'));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("aba", 'a', 'b'));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut("aba", 'z'));

    }

    @Test
    public void testIndexOfAnyBut_StringCharArrayWithSupplementaryChars() {
        Assert.assertEquals(2, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20000.toCharArray()));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20001.toCharArray()));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(CharU20000, CharU20000.toCharArray()));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut(CharU20000, CharU20001.toCharArray()));
    }

    @Test
    public void testIndexOfAnyBut_StringString() {
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(null, (String) null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(null, ""));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(null, "ab"));

        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("", (String) null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("", ""));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("", "ab"));

        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx", (String) null));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut("zzabyycdxx", ""));
        Assert.assertEquals(3, StringUtils.indexOfAnyBut("zzabyycdxx", "za"));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut("zzabyycdxx", "by"));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut("ab", "z"));
    }

    @Test
    public void testIndexOfAnyBut_StringStringWithSupplementaryChars() {
        Assert.assertEquals(2, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20000));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut(CharU20000 + CharU20001, CharU20001));
        Assert.assertEquals(-1, StringUtils.indexOfAnyBut(CharU20000, CharU20000));
        Assert.assertEquals(0, StringUtils.indexOfAnyBut(CharU20000, CharU20001));
    }

    @Test
    public void testIndexOfIgnoreCase_String() {
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase(null, null));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase(null, ""));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("", null));
        Assert.assertEquals(0, StringUtils.indexOfIgnoreCase("", ""));
        Assert.assertEquals(0, StringUtils.indexOfIgnoreCase("aabaabaa", "a"));
        Assert.assertEquals(0, StringUtils.indexOfIgnoreCase("aabaabaa", "A"));
        Assert.assertEquals(2, StringUtils.indexOfIgnoreCase("aabaabaa", "b"));
        Assert.assertEquals(2, StringUtils.indexOfIgnoreCase("aabaabaa", "B"));
        Assert.assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "ab"));
        Assert.assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB"));
        Assert.assertEquals(0, StringUtils.indexOfIgnoreCase("aabaabaa", ""));
    }

    @Test
    public void testIndexOfIgnoreCase_StringInt() {
        Assert.assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", -1));
        Assert.assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0));
        Assert.assertEquals(1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 1));
        Assert.assertEquals(4, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 2));
        Assert.assertEquals(4, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 3));
        Assert.assertEquals(4, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 4));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 5));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 6));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 7));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 8));
        Assert.assertEquals(1, StringUtils.indexOfIgnoreCase("aab", "AB", 1));
        Assert.assertEquals(5, StringUtils.indexOfIgnoreCase("aabaabaa", "", 5));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("ab", "AAB", 0));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("aab", "AAB", 1));
        Assert.assertEquals(-1, StringUtils.indexOfIgnoreCase("abc", "", 9));
    }

    @Test
    public void testLastIndexOf_char() {
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, ' '));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", ' '));
        Assert.assertEquals(7, StringUtils.lastIndexOf("aabaabaa", 'a'));
        Assert.assertEquals(5, StringUtils.lastIndexOf("aabaabaa", 'b'));

        Assert.assertEquals(5, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), 'b'));
    }

    @Test
    public void testLastIndexOf_charInt() {
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, ' ', 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, ' ', -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", ' ', 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", ' ', -1));
        Assert.assertEquals(7, StringUtils.lastIndexOf("aabaabaa", 'a', 8));
        Assert.assertEquals(5, StringUtils.lastIndexOf("aabaabaa", 'b', 8));
        Assert.assertEquals(2, StringUtils.lastIndexOf("aabaabaa", 'b', 3));
        Assert.assertEquals(5, StringUtils.lastIndexOf("aabaabaa", 'b', 9));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", 'b', -1));
        Assert.assertEquals(0, StringUtils.lastIndexOf("aabaabaa", 'a', 0));

        Assert.assertEquals(2, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), 'b', 2));
    }

    @Test
    public void testLastIndexOf_String() {
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, null));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", null));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", "a"));
        Assert.assertEquals(0, StringUtils.lastIndexOf("", ""));
        Assert.assertEquals(8, StringUtils.lastIndexOf("aabaabaa", ""));
        Assert.assertEquals(7, StringUtils.lastIndexOf("aabaabaa", "a"));
        Assert.assertEquals(5, StringUtils.lastIndexOf("aabaabaa", "b"));
        Assert.assertEquals(4, StringUtils.lastIndexOf("aabaabaa", "ab"));

        Assert.assertEquals(4, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), "ab"));
    }

    @Test
    public void testLastIndexOf_StringInt() {
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, null, 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, null, -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, "", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf(null, "", -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", null, 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", null, -1));
        Assert.assertEquals(0, StringUtils.lastIndexOf("", "", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("", "", -1));
        Assert.assertEquals(0, StringUtils.lastIndexOf("", "", 9));
        Assert.assertEquals(0, StringUtils.lastIndexOf("abc", "", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("abc", "", -1));
        Assert.assertEquals(3, StringUtils.lastIndexOf("abc", "", 9));
        Assert.assertEquals(7, StringUtils.lastIndexOf("aabaabaa", "a", 8));
        Assert.assertEquals(5, StringUtils.lastIndexOf("aabaabaa", "b", 8));
        Assert.assertEquals(4, StringUtils.lastIndexOf("aabaabaa", "ab", 8));
        Assert.assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "b", 3));
        Assert.assertEquals(5, StringUtils.lastIndexOf("aabaabaa", "b", 9));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "b", -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "b", 0));
        Assert.assertEquals(0, StringUtils.lastIndexOf("aabaabaa", "a", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "a", -1));

        // Test that fromIndex works correctly, i.e. cannot match after fromIndex
        Assert.assertEquals(7, StringUtils.lastIndexOf("12345678", "8", 9));
        Assert.assertEquals(7, StringUtils.lastIndexOf("12345678", "8", 8));
        Assert.assertEquals(7, StringUtils.lastIndexOf("12345678", "8", 7)); // 7 is last index
        Assert.assertEquals(-1, StringUtils.lastIndexOf("12345678", "8", 6));

        Assert.assertEquals(-1, StringUtils.lastIndexOf("aabaabaa", "b", 1));
        Assert.assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "b", 2));
        Assert.assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "ba", 2));
        Assert.assertEquals(2, StringUtils.lastIndexOf("aabaabaa", "ba", 3));

        Assert.assertEquals(2, StringUtils.lastIndexOf(new StringBuilder("aabaabaa"), "b", 3));
    }

    @Test
    public void testLastIndexOfAny_StringStringArray() {
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(null, (CharSequence) null));   // test both types of ...
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(null, (CharSequence[]) null)); // ... varargs invocation
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(null)); // Missing varag
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(null, FOOBAR_SUB_ARRAY));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, (CharSequence) null));   // test both types of ...
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, (CharSequence[]) null)); // ... varargs invocation
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR)); // Missing vararg
        Assert.assertEquals(3, StringUtils.lastIndexOfAny(FOOBAR, FOOBAR_SUB_ARRAY));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, new String[0]));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(null, new String[0]));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny("", new String[0]));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, new String[] {"llll"}));
        Assert.assertEquals(6, StringUtils.lastIndexOfAny(FOOBAR, new String[] {""}));
        Assert.assertEquals(0, StringUtils.lastIndexOfAny("", new String[] {""}));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny("", new String[] {"a"}));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny("", new String[] {null}));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(FOOBAR, new String[] {null}));
        Assert.assertEquals(-1, StringUtils.lastIndexOfAny(null, new String[] {null}));
    }

    @Test
    public void testLastIndexOfIgnoreCase_String() {
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", null));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, ""));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", "a"));
        Assert.assertEquals(0, StringUtils.lastIndexOfIgnoreCase("", ""));
        Assert.assertEquals(8, StringUtils.lastIndexOfIgnoreCase("aabaabaa", ""));
        Assert.assertEquals(7, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "a"));
        Assert.assertEquals(7, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A"));
        Assert.assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "b"));
        Assert.assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B"));
        Assert.assertEquals(4, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "ab"));
        Assert.assertEquals(4, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB"));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("ab", "AAB"));
        Assert.assertEquals(0, StringUtils.lastIndexOfIgnoreCase("aab", "AAB"));
    }

    @Test
    public void testLastIndexOfIgnoreCase_StringInt() {
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null, 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, null, -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, "", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase(null, "", -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", null, 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", null, -1));
        Assert.assertEquals(0, StringUtils.lastIndexOfIgnoreCase("", "", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("", "", -1));
        Assert.assertEquals(0, StringUtils.lastIndexOfIgnoreCase("", "", 9));
        Assert.assertEquals(0, StringUtils.lastIndexOfIgnoreCase("abc", "", 0));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("abc", "", -1));
        Assert.assertEquals(3, StringUtils.lastIndexOfIgnoreCase("abc", "", 9));
        Assert.assertEquals(7, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 8));
        Assert.assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 8));
        Assert.assertEquals(4, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "AB", 8));
        Assert.assertEquals(2, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 3));
        Assert.assertEquals(5, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 9));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", -1));
        Assert.assertEquals(-1, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "B", 0));
        Assert.assertEquals(0, StringUtils.lastIndexOfIgnoreCase("aabaabaa", "A", 0));
        Assert.assertEquals(1, StringUtils.lastIndexOfIgnoreCase("aab", "AB", 1));
    }

    @Test
    public void testLastOrdinalIndexOf() {
        Assert.assertEquals(-1, StringUtils.lastOrdinalIndexOf(null, "*", 42) );
        Assert.assertEquals(-1, StringUtils.lastOrdinalIndexOf("*", null, 42) );
        Assert.assertEquals(0, StringUtils.lastOrdinalIndexOf("", "", 42) );
        Assert.assertEquals(7, StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 1) );
        Assert.assertEquals(6, StringUtils.lastOrdinalIndexOf("aabaabaa", "a", 2) );
        Assert.assertEquals(5, StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 1) );
        Assert.assertEquals(2, StringUtils.lastOrdinalIndexOf("aabaabaa", "b", 2) );
        Assert.assertEquals(4, StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 1) );
        Assert.assertEquals(1, StringUtils.lastOrdinalIndexOf("aabaabaa", "ab", 2) );
        Assert.assertEquals(8, StringUtils.lastOrdinalIndexOf("aabaabaa", "", 1) );
        Assert.assertEquals(8, StringUtils.lastOrdinalIndexOf("aabaabaa", "", 2) );
    }

    @Test
    public void testOrdinalIndexOf() {
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf(null, null, Integer.MIN_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", null, Integer.MIN_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", "", Integer.MIN_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", Integer.MIN_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", Integer.MIN_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", Integer.MIN_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "", Integer.MIN_VALUE));

        Assert.assertEquals(-1, StringUtils.ordinalIndexOf(null, null, -1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", null, -1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", "", -1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", -1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", -1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", -1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "", -1));

        Assert.assertEquals(-1, StringUtils.ordinalIndexOf(null, null, 0));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", null, 0));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", "", 0));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", 0));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", 0));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", 0));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "", 0));

        Assert.assertEquals(-1, StringUtils.ordinalIndexOf(null, null, 1));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", null, 1));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("", "", 1));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "a", 1));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("aabaabaa", "b", 1));
        Assert.assertEquals(1, StringUtils.ordinalIndexOf("aabaabaa", "ab", 1));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "", 1));

        Assert.assertEquals(-1, StringUtils.ordinalIndexOf(null, null, 2));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", null, 2));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("", "", 2));
        Assert.assertEquals(1, StringUtils.ordinalIndexOf("aabaabaa", "a", 2));
        Assert.assertEquals(5, StringUtils.ordinalIndexOf("aabaabaa", "b", 2));
        Assert.assertEquals(4, StringUtils.ordinalIndexOf("aabaabaa", "ab", 2));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "", 2));

        Assert.assertEquals(-1, StringUtils.ordinalIndexOf(null, null, Integer.MAX_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("", null, Integer.MAX_VALUE));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("", "", Integer.MAX_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "a", Integer.MAX_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "b", Integer.MAX_VALUE));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aabaabaa", "ab", Integer.MAX_VALUE));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("aabaabaa", "", Integer.MAX_VALUE));

        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 0));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 1));
        Assert.assertEquals(1, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 2));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 3));
        Assert.assertEquals(3, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 4));
        Assert.assertEquals(4, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 5));
        Assert.assertEquals(5, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 6));
        Assert.assertEquals(6, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 7));
        Assert.assertEquals(7, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 8));
        Assert.assertEquals(8, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 9));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aaaaaaaaa", "a", 10));

        // match at each possible position
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("aaaaaa", "aa", 1));
        Assert.assertEquals(1, StringUtils.ordinalIndexOf("aaaaaa", "aa", 2));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("aaaaaa", "aa", 3));
        Assert.assertEquals(3, StringUtils.ordinalIndexOf("aaaaaa", "aa", 4));
        Assert.assertEquals(4, StringUtils.ordinalIndexOf("aaaaaa", "aa", 5));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("aaaaaa", "aa", 6));

        Assert.assertEquals(0, StringUtils.ordinalIndexOf("ababab", "aba", 1));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("ababab", "aba", 2));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("ababab", "aba", 3));

        Assert.assertEquals(0, StringUtils.ordinalIndexOf("abababab", "abab", 1));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("abababab", "abab", 2));
        Assert.assertEquals(4, StringUtils.ordinalIndexOf("abababab", "abab", 3));
        Assert.assertEquals(-1, StringUtils.ordinalIndexOf("abababab", "abab", 4));

    }
    
    @Test
    public void testLANG1193() {
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("abc", "ab", 1));
    }

    @Test
    // Non-overlapping test
    public void testLANG1241_1() {
        //                                          0  3  6
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("abaabaab", "ab", 1));
        Assert.assertEquals(3, StringUtils.ordinalIndexOf("abaabaab", "ab", 2));
        Assert.assertEquals(6, StringUtils.ordinalIndexOf("abaabaab", "ab", 3));
    }

    @Test
    // Overlapping matching test
    public void testLANG1241_2() {
        //                                          0 2 4
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("abababa", "aba", 1));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("abababa", "aba", 2));
        Assert.assertEquals(4, StringUtils.ordinalIndexOf("abababa", "aba", 3));
        Assert.assertEquals(0, StringUtils.ordinalIndexOf("abababab", "abab", 1));
        Assert.assertEquals(2, StringUtils.ordinalIndexOf("abababab", "abab", 2));
        Assert.assertEquals(4, StringUtils.ordinalIndexOf("abababab", "abab", 3));
    }
}
