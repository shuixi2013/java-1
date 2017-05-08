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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Substring methods
 */
public class StringUtilsSubstringTest  {
    private static final String FOO = "foo";
    private static final String BAR = "bar";
    private static final String BAZ = "baz";
    private static final String FOOBAR = "foobar";
    private static final String SENTENCE = "foo bar baz";

    //-----------------------------------------------------------------------

    @Test
    public void testSubstring_StringInt() {
        Assert.assertEquals(null, StringUtils.substring(null, 0));
        Assert.assertEquals("", StringUtils.substring("", 0));
        Assert.assertEquals("", StringUtils.substring("", 2));
        
        Assert.assertEquals("", StringUtils.substring(SENTENCE, 80));
        Assert.assertEquals(BAZ, StringUtils.substring(SENTENCE, 8));
        Assert.assertEquals(BAZ, StringUtils.substring(SENTENCE, -3));
        Assert.assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0));
        Assert.assertEquals("abc", StringUtils.substring("abc", -4));
        Assert.assertEquals("abc", StringUtils.substring("abc", -3));
        Assert.assertEquals("bc", StringUtils.substring("abc", -2));
        Assert.assertEquals("c", StringUtils.substring("abc", -1));
        Assert.assertEquals("abc", StringUtils.substring("abc", 0));
        Assert.assertEquals("bc", StringUtils.substring("abc", 1));
        Assert.assertEquals("c", StringUtils.substring("abc", 2));
        Assert.assertEquals("", StringUtils.substring("abc", 3));
        Assert.assertEquals("", StringUtils.substring("abc", 4));
    }
    
    @Test
    public void testSubstring_StringIntInt() {
        Assert.assertEquals(null, StringUtils.substring(null, 0, 0));
        Assert.assertEquals(null, StringUtils.substring(null, 1, 2));
        Assert.assertEquals("", StringUtils.substring("", 0, 0));
        Assert.assertEquals("", StringUtils.substring("", 1, 2));
        Assert.assertEquals("", StringUtils.substring("", -2, -1));
        
        Assert.assertEquals("", StringUtils.substring(SENTENCE, 8, 6));
        Assert.assertEquals(FOO, StringUtils.substring(SENTENCE, 0, 3));
        Assert.assertEquals("o", StringUtils.substring(SENTENCE, -9, 3));
        Assert.assertEquals(FOO, StringUtils.substring(SENTENCE, 0, -8));
        Assert.assertEquals("o", StringUtils.substring(SENTENCE, -9, -8));
        Assert.assertEquals(SENTENCE, StringUtils.substring(SENTENCE, 0, 80));
        Assert.assertEquals("", StringUtils.substring(SENTENCE, 2, 2));
        Assert.assertEquals("b",StringUtils.substring("abc", -2, -1));
    }
           
    @Test
    public void testLeft_String() {
        Assert.assertSame(null, StringUtils.left(null, -1));
        Assert.assertSame(null, StringUtils.left(null, 0));
        Assert.assertSame(null, StringUtils.left(null, 2));
        
        Assert.assertEquals("", StringUtils.left("", -1));
        Assert.assertEquals("", StringUtils.left("", 0));
        Assert.assertEquals("", StringUtils.left("", 2));
        
        Assert.assertEquals("", StringUtils.left(FOOBAR, -1));
        Assert.assertEquals("", StringUtils.left(FOOBAR, 0));
        Assert.assertEquals(FOO, StringUtils.left(FOOBAR, 3));
        Assert.assertSame(FOOBAR, StringUtils.left(FOOBAR, 80));
    }
    
    @Test
    public void testRight_String() {
        Assert.assertSame(null, StringUtils.right(null, -1));
        Assert.assertSame(null, StringUtils.right(null, 0));
        Assert.assertSame(null, StringUtils.right(null, 2));
        
        Assert.assertEquals("", StringUtils.right("", -1));
        Assert.assertEquals("", StringUtils.right("", 0));
        Assert.assertEquals("", StringUtils.right("", 2));
        
        Assert.assertEquals("", StringUtils.right(FOOBAR, -1));
        Assert.assertEquals("", StringUtils.right(FOOBAR, 0));
        Assert.assertEquals(BAR, StringUtils.right(FOOBAR, 3));
        Assert.assertSame(FOOBAR, StringUtils.right(FOOBAR, 80));
    }
    
    @Test
    public void testMid_String() {
        Assert.assertSame(null, StringUtils.mid(null, -1, 0));
        Assert.assertSame(null, StringUtils.mid(null, 0, -1));
        Assert.assertSame(null, StringUtils.mid(null, 3, 0));
        Assert.assertSame(null, StringUtils.mid(null, 3, 2));
        
        Assert.assertEquals("", StringUtils.mid("", 0, -1));
        Assert.assertEquals("", StringUtils.mid("", 0, 0));
        Assert.assertEquals("", StringUtils.mid("", 0, 2));
        
        Assert.assertEquals("", StringUtils.mid(FOOBAR, 3, -1));
        Assert.assertEquals("", StringUtils.mid(FOOBAR, 3, 0));
        Assert.assertEquals("b", StringUtils.mid(FOOBAR, 3, 1));
        Assert.assertEquals(FOO, StringUtils.mid(FOOBAR, 0, 3));
        Assert.assertEquals(BAR, StringUtils.mid(FOOBAR, 3, 3));
        Assert.assertEquals(FOOBAR, StringUtils.mid(FOOBAR, 0, 80));
        Assert.assertEquals(BAR, StringUtils.mid(FOOBAR, 3, 80));
        Assert.assertEquals("", StringUtils.mid(FOOBAR, 9, 3));
        Assert.assertEquals(FOO, StringUtils.mid(FOOBAR, -1, 3));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testSubstringBefore_StringString() {
        Assert.assertEquals("foo", StringUtils.substringBefore("fooXXbarXXbaz", "XX"));

        Assert.assertEquals(null, StringUtils.substringBefore(null, null));
        Assert.assertEquals(null, StringUtils.substringBefore(null, ""));
        Assert.assertEquals(null, StringUtils.substringBefore(null, "XX"));
        Assert.assertEquals("", StringUtils.substringBefore("", null));
        Assert.assertEquals("", StringUtils.substringBefore("", ""));
        Assert.assertEquals("", StringUtils.substringBefore("", "XX"));
        
        Assert.assertEquals("foo", StringUtils.substringBefore("foo", null));
        Assert.assertEquals("foo", StringUtils.substringBefore("foo", "b"));
        Assert.assertEquals("f", StringUtils.substringBefore("foot", "o"));
        Assert.assertEquals("", StringUtils.substringBefore("abc", "a"));
        Assert.assertEquals("a", StringUtils.substringBefore("abcba", "b"));
        Assert.assertEquals("ab", StringUtils.substringBefore("abc", "c"));
        Assert.assertEquals("", StringUtils.substringBefore("abc", ""));
    }
    
    @Test
    public void testSubstringAfter_StringString() {
        Assert.assertEquals("barXXbaz", StringUtils.substringAfter("fooXXbarXXbaz", "XX"));
        
        Assert.assertEquals(null, StringUtils.substringAfter(null, null));
        Assert.assertEquals(null, StringUtils.substringAfter(null, ""));
        Assert.assertEquals(null, StringUtils.substringAfter(null, "XX"));
        Assert.assertEquals("", StringUtils.substringAfter("", null));
        Assert.assertEquals("", StringUtils.substringAfter("", ""));
        Assert.assertEquals("", StringUtils.substringAfter("", "XX"));
        
        Assert.assertEquals("", StringUtils.substringAfter("foo", null));
        Assert.assertEquals("ot", StringUtils.substringAfter("foot", "o"));
        Assert.assertEquals("bc", StringUtils.substringAfter("abc", "a"));
        Assert.assertEquals("cba", StringUtils.substringAfter("abcba", "b"));
        Assert.assertEquals("", StringUtils.substringAfter("abc", "c"));
        Assert.assertEquals("abc", StringUtils.substringAfter("abc", ""));
        Assert.assertEquals("", StringUtils.substringAfter("abc", "d"));
    }

    @Test
    public void testSubstringBeforeLast_StringString() {
        Assert.assertEquals("fooXXbar", StringUtils.substringBeforeLast("fooXXbarXXbaz", "XX"));

        Assert.assertEquals(null, StringUtils.substringBeforeLast(null, null));
        Assert.assertEquals(null, StringUtils.substringBeforeLast(null, ""));
        Assert.assertEquals(null, StringUtils.substringBeforeLast(null, "XX"));
        Assert.assertEquals("", StringUtils.substringBeforeLast("", null));
        Assert.assertEquals("", StringUtils.substringBeforeLast("", ""));
        Assert.assertEquals("", StringUtils.substringBeforeLast("", "XX"));

        Assert.assertEquals("foo", StringUtils.substringBeforeLast("foo", null));
        Assert.assertEquals("foo", StringUtils.substringBeforeLast("foo", "b"));
        Assert.assertEquals("fo", StringUtils.substringBeforeLast("foo", "o"));
        Assert.assertEquals("abc\r\n", StringUtils.substringBeforeLast("abc\r\n", "d"));
        Assert.assertEquals("abc", StringUtils.substringBeforeLast("abcdabc", "d"));
        Assert.assertEquals("abcdabc", StringUtils.substringBeforeLast("abcdabcd", "d"));
        Assert.assertEquals("a", StringUtils.substringBeforeLast("abc", "b"));
        Assert.assertEquals("abc ", StringUtils.substringBeforeLast("abc \n", "\n"));
        Assert.assertEquals("a", StringUtils.substringBeforeLast("a", null));
        Assert.assertEquals("a", StringUtils.substringBeforeLast("a", ""));
        Assert.assertEquals("", StringUtils.substringBeforeLast("a", "a"));
    }
    
    @Test
    public void testSubstringAfterLast_StringString() {
        Assert.assertEquals("baz", StringUtils.substringAfterLast("fooXXbarXXbaz", "XX"));

        Assert.assertEquals(null, StringUtils.substringAfterLast(null, null));
        Assert.assertEquals(null, StringUtils.substringAfterLast(null, ""));
        Assert.assertEquals(null, StringUtils.substringAfterLast(null, "XX"));
        Assert.assertEquals("", StringUtils.substringAfterLast("", null));
        Assert.assertEquals("", StringUtils.substringAfterLast("", ""));
        Assert.assertEquals("", StringUtils.substringAfterLast("", "a"));

        Assert.assertEquals("", StringUtils.substringAfterLast("foo", null));
        Assert.assertEquals("", StringUtils.substringAfterLast("foo", "b"));
        Assert.assertEquals("t", StringUtils.substringAfterLast("foot", "o"));
        Assert.assertEquals("bc", StringUtils.substringAfterLast("abc", "a"));
        Assert.assertEquals("a", StringUtils.substringAfterLast("abcba", "b"));
        Assert.assertEquals("", StringUtils.substringAfterLast("abc", "c"));
        Assert.assertEquals("", StringUtils.substringAfterLast("", "d"));
        Assert.assertEquals("", StringUtils.substringAfterLast("abc", ""));
    }        
        
    //-----------------------------------------------------------------------
    @Test
    public void testSubstringBetween_StringString() {
        Assert.assertEquals(null, StringUtils.substringBetween(null, "tag"));
        Assert.assertEquals("", StringUtils.substringBetween("", ""));
        Assert.assertEquals(null, StringUtils.substringBetween("", "abc"));
        Assert.assertEquals("", StringUtils.substringBetween("    ", " "));
        Assert.assertEquals(null, StringUtils.substringBetween("abc", null));
        Assert.assertEquals("", StringUtils.substringBetween("abc", ""));
        Assert.assertEquals(null, StringUtils.substringBetween("abc", "a"));
        Assert.assertEquals("bc", StringUtils.substringBetween("abca", "a"));
        Assert.assertEquals("bc", StringUtils.substringBetween("abcabca", "a"));
        Assert.assertEquals("bar", StringUtils.substringBetween("\nbar\n", "\n"));
    }
            
    @Test
    public void testSubstringBetween_StringStringString() {
        Assert.assertEquals(null, StringUtils.substringBetween(null, "", ""));
        Assert.assertEquals(null, StringUtils.substringBetween("", null, ""));
        Assert.assertEquals(null, StringUtils.substringBetween("", "", null));
        Assert.assertEquals("", StringUtils.substringBetween("", "", ""));
        Assert.assertEquals("", StringUtils.substringBetween("foo", "", ""));
        Assert.assertEquals(null, StringUtils.substringBetween("foo", "", "]"));
        Assert.assertEquals(null, StringUtils.substringBetween("foo", "[", "]"));
        Assert.assertEquals("", StringUtils.substringBetween("    ", " ", "  "));
        Assert.assertEquals("bar", StringUtils.substringBetween("<foo>bar</foo>", "<foo>", "</foo>") );
    }

   /**
     * Tests the substringsBetween method that returns an String Array of substrings.
     */
    @Test
    public void testSubstringsBetween_StringStringString() {

        String[] results = StringUtils.substringsBetween("[one], [two], [three]", "[", "]");
        Assert.assertEquals(3, results.length);
        Assert.assertEquals("one", results[0]);
        Assert.assertEquals("two", results[1]);
        Assert.assertEquals("three", results[2]);

        results = StringUtils.substringsBetween("[one], [two], three", "[", "]");
        Assert.assertEquals(2, results.length);
        Assert.assertEquals("one", results[0]);
        Assert.assertEquals("two", results[1]);

        results = StringUtils.substringsBetween("[one], [two], three]", "[", "]");
        Assert.assertEquals(2, results.length);
        Assert.assertEquals("one", results[0]);
        Assert.assertEquals("two", results[1]);

        results = StringUtils.substringsBetween("[one], two], three]", "[", "]");
        Assert.assertEquals(1, results.length);
        Assert.assertEquals("one", results[0]);

        results = StringUtils.substringsBetween("one], two], [three]", "[", "]");
        Assert.assertEquals(1, results.length);
        Assert.assertEquals("three", results[0]);

        // 'ab hello ba' will match, but 'ab non ba' won't
        // this is because the 'a' is shared between the two and can't be matched twice
        results = StringUtils.substringsBetween("aabhellobabnonba", "ab", "ba");
        Assert.assertEquals(1, results.length);
        Assert.assertEquals("hello", results[0]);

        results = StringUtils.substringsBetween("one, two, three", "[", "]");
        Assert.assertNull(results);

        results = StringUtils.substringsBetween("[one, two, three", "[", "]");
        Assert.assertNull(results);

        results = StringUtils.substringsBetween("one, two, three]", "[", "]");
        Assert.assertNull(results);

        results = StringUtils.substringsBetween("[one], [two], [three]", "[", null);
        Assert.assertNull(results);

        results = StringUtils.substringsBetween("[one], [two], [three]", null, "]");
        Assert.assertNull(results);

        results = StringUtils.substringsBetween("[one], [two], [three]", "", "");
        Assert.assertNull(results);

        results = StringUtils.substringsBetween(null, "[", "]");
        Assert.assertNull(results);

        results = StringUtils.substringsBetween("", "[", "]");
        Assert.assertEquals(0, results.length);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCountMatches_String() {
        Assert.assertEquals(0, StringUtils.countMatches(null, null));
        Assert.assertEquals(0, StringUtils.countMatches("blah", null));
        Assert.assertEquals(0, StringUtils.countMatches(null, "DD"));

        Assert.assertEquals(0, StringUtils.countMatches("x", ""));
        Assert.assertEquals(0, StringUtils.countMatches("", ""));

        Assert.assertEquals(3,
             StringUtils.countMatches("one long someone sentence of one", "one"));
        Assert.assertEquals(0,
             StringUtils.countMatches("one long someone sentence of one", "two"));
        Assert.assertEquals(4,
             StringUtils.countMatches("oooooooooooo", "ooo"));
    }

    @Test
    public void testCountMatches_char() {
        Assert.assertEquals(0, StringUtils.countMatches(null, 'D'));
        Assert.assertEquals(5, StringUtils.countMatches("one long someone sentence of one", ' '));
        Assert.assertEquals(6, StringUtils.countMatches("one long someone sentence of one", 'o'));
        Assert.assertEquals(4, StringUtils.countMatches("oooooooooooo", "ooo"));
    }

}
