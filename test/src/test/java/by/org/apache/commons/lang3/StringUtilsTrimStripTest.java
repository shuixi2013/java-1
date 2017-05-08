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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Trim/Strip methods
 */
public class StringUtilsTrimStripTest  {
    private static final String FOO = "foo";

    @Test
    public void testTrim() {
        Assert.assertEquals(FOO, StringUtils.trim(FOO + "  "));
        Assert.assertEquals(FOO, StringUtils.trim(" " + FOO + "  "));
        Assert.assertEquals(FOO, StringUtils.trim(" " + FOO));
        Assert.assertEquals(FOO, StringUtils.trim(FOO + ""));
        Assert.assertEquals("", StringUtils.trim(" \t\r\n\b "));
        Assert.assertEquals("", StringUtils.trim(StringUtilsTest.TRIMMABLE));
        assertEquals(StringUtilsTest.NON_TRIMMABLE, StringUtils.trim(StringUtilsTest.NON_TRIMMABLE));
        Assert.assertEquals("", StringUtils.trim(""));
        Assert.assertEquals(null, StringUtils.trim(null));
    }

    @Test
    public void testTrimToNull() {
        Assert.assertEquals(FOO, StringUtils.trimToNull(FOO + "  "));
        Assert.assertEquals(FOO, StringUtils.trimToNull(" " + FOO + "  "));
        Assert.assertEquals(FOO, StringUtils.trimToNull(" " + FOO));
        Assert.assertEquals(FOO, StringUtils.trimToNull(FOO + ""));
        Assert.assertEquals(null, StringUtils.trimToNull(" \t\r\n\b "));
        Assert.assertEquals(null, StringUtils.trimToNull(StringUtilsTest.TRIMMABLE));
        assertEquals(StringUtilsTest.NON_TRIMMABLE, StringUtils.trimToNull(StringUtilsTest.NON_TRIMMABLE));
        Assert.assertEquals(null, StringUtils.trimToNull(""));
        Assert.assertEquals(null, StringUtils.trimToNull(null));
    }

    @Test
    public void testTrimToEmpty() {
        Assert.assertEquals(FOO, StringUtils.trimToEmpty(FOO + "  "));
        Assert.assertEquals(FOO, StringUtils.trimToEmpty(" " + FOO + "  "));
        Assert.assertEquals(FOO, StringUtils.trimToEmpty(" " + FOO));
        Assert.assertEquals(FOO, StringUtils.trimToEmpty(FOO + ""));
        Assert.assertEquals("", StringUtils.trimToEmpty(" \t\r\n\b "));
        Assert.assertEquals("", StringUtils.trimToEmpty(StringUtilsTest.TRIMMABLE));
        assertEquals(StringUtilsTest.NON_TRIMMABLE, StringUtils.trimToEmpty(StringUtilsTest.NON_TRIMMABLE));
        Assert.assertEquals("", StringUtils.trimToEmpty(""));
        Assert.assertEquals("", StringUtils.trimToEmpty(null));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testStrip_String() {
        Assert.assertEquals(null, StringUtils.strip(null));
        Assert.assertEquals("", StringUtils.strip(""));
        Assert.assertEquals("", StringUtils.strip("        "));
        Assert.assertEquals("abc", StringUtils.strip("  abc  "));
        assertEquals(StringUtilsTest.NON_WHITESPACE, 
            StringUtils.strip(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE));
    }
    
    @Test
    public void testStripToNull_String() {
        Assert.assertEquals(null, StringUtils.stripToNull(null));
        Assert.assertEquals(null, StringUtils.stripToNull(""));
        Assert.assertEquals(null, StringUtils.stripToNull("        "));
        Assert.assertEquals(null, StringUtils.stripToNull(StringUtilsTest.WHITESPACE));
        Assert.assertEquals("ab c", StringUtils.stripToNull("  ab c  "));
        assertEquals(StringUtilsTest.NON_WHITESPACE, 
            StringUtils.stripToNull(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE));
    }
    
    @Test
    public void testStripToEmpty_String() {
        Assert.assertEquals("", StringUtils.stripToEmpty(null));
        Assert.assertEquals("", StringUtils.stripToEmpty(""));
        Assert.assertEquals("", StringUtils.stripToEmpty("        "));
        Assert.assertEquals("", StringUtils.stripToEmpty(StringUtilsTest.WHITESPACE));
        Assert.assertEquals("ab c", StringUtils.stripToEmpty("  ab c  "));
        assertEquals(StringUtilsTest.NON_WHITESPACE, 
            StringUtils.stripToEmpty(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE));
    }
    
    @Test
    public void testStrip_StringString() {
        // null strip
        Assert.assertEquals(null, StringUtils.strip(null, null));
        Assert.assertEquals("", StringUtils.strip("", null));
        Assert.assertEquals("", StringUtils.strip("        ", null));
        Assert.assertEquals("abc", StringUtils.strip("  abc  ", null));
        assertEquals(StringUtilsTest.NON_WHITESPACE, 
            StringUtils.strip(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, null));

        // "" strip
        Assert.assertEquals(null, StringUtils.strip(null, ""));
        Assert.assertEquals("", StringUtils.strip("", ""));
        Assert.assertEquals("        ", StringUtils.strip("        ", ""));
        Assert.assertEquals("  abc  ", StringUtils.strip("  abc  ", ""));
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.strip(StringUtilsTest.WHITESPACE, ""));
        
        // " " strip
        Assert.assertEquals(null, StringUtils.strip(null, " "));
        Assert.assertEquals("", StringUtils.strip("", " "));
        Assert.assertEquals("", StringUtils.strip("        ", " "));
        Assert.assertEquals("abc", StringUtils.strip("  abc  ", " "));
        
        // "ab" strip
        Assert.assertEquals(null, StringUtils.strip(null, "ab"));
        Assert.assertEquals("", StringUtils.strip("", "ab"));
        Assert.assertEquals("        ", StringUtils.strip("        ", "ab"));
        Assert.assertEquals("  abc  ", StringUtils.strip("  abc  ", "ab"));
        Assert.assertEquals("c", StringUtils.strip("abcabab", "ab"));
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.strip(StringUtilsTest.WHITESPACE, ""));
    }
    
    @Test
    public void testStripStart_StringString() {
        // null stripStart
        Assert.assertEquals(null, StringUtils.stripStart(null, null));
        Assert.assertEquals("", StringUtils.stripStart("", null));
        Assert.assertEquals("", StringUtils.stripStart("        ", null));
        Assert.assertEquals("abc  ", StringUtils.stripStart("  abc  ", null));
        assertEquals(StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, 
            StringUtils.stripStart(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, null));

        // "" stripStart
        Assert.assertEquals(null, StringUtils.stripStart(null, ""));
        Assert.assertEquals("", StringUtils.stripStart("", ""));
        Assert.assertEquals("        ", StringUtils.stripStart("        ", ""));
        Assert.assertEquals("  abc  ", StringUtils.stripStart("  abc  ", ""));
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripStart(StringUtilsTest.WHITESPACE, ""));
        
        // " " stripStart
        Assert.assertEquals(null, StringUtils.stripStart(null, " "));
        Assert.assertEquals("", StringUtils.stripStart("", " "));
        Assert.assertEquals("", StringUtils.stripStart("        ", " "));
        Assert.assertEquals("abc  ", StringUtils.stripStart("  abc  ", " "));
        
        // "ab" stripStart
        Assert.assertEquals(null, StringUtils.stripStart(null, "ab"));
        Assert.assertEquals("", StringUtils.stripStart("", "ab"));
        Assert.assertEquals("        ", StringUtils.stripStart("        ", "ab"));
        Assert.assertEquals("  abc  ", StringUtils.stripStart("  abc  ", "ab"));
        Assert.assertEquals("cabab", StringUtils.stripStart("abcabab", "ab"));
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripStart(StringUtilsTest.WHITESPACE, ""));
    }
    
    @Test
    public void testStripEnd_StringString() {
        // null stripEnd
        Assert.assertEquals(null, StringUtils.stripEnd(null, null));
        Assert.assertEquals("", StringUtils.stripEnd("", null));
        Assert.assertEquals("", StringUtils.stripEnd("        ", null));
        Assert.assertEquals("  abc", StringUtils.stripEnd("  abc  ", null));
        assertEquals(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE, 
            StringUtils.stripEnd(StringUtilsTest.WHITESPACE + StringUtilsTest.NON_WHITESPACE + StringUtilsTest.WHITESPACE, null));

        // "" stripEnd
        Assert.assertEquals(null, StringUtils.stripEnd(null, ""));
        Assert.assertEquals("", StringUtils.stripEnd("", ""));
        Assert.assertEquals("        ", StringUtils.stripEnd("        ", ""));
        Assert.assertEquals("  abc  ", StringUtils.stripEnd("  abc  ", ""));
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripEnd(StringUtilsTest.WHITESPACE, ""));
        
        // " " stripEnd
        Assert.assertEquals(null, StringUtils.stripEnd(null, " "));
        Assert.assertEquals("", StringUtils.stripEnd("", " "));
        Assert.assertEquals("", StringUtils.stripEnd("        ", " "));
        Assert.assertEquals("  abc", StringUtils.stripEnd("  abc  ", " "));
        
        // "ab" stripEnd
        Assert.assertEquals(null, StringUtils.stripEnd(null, "ab"));
        Assert.assertEquals("", StringUtils.stripEnd("", "ab"));
        Assert.assertEquals("        ", StringUtils.stripEnd("        ", "ab"));
        Assert.assertEquals("  abc  ", StringUtils.stripEnd("  abc  ", "ab"));
        Assert.assertEquals("abc", StringUtils.stripEnd("abcabab", "ab"));
        assertEquals(StringUtilsTest.WHITESPACE, StringUtils.stripEnd(StringUtilsTest.WHITESPACE, ""));
    }

    @Test
    public void testStripAll() {
        // test stripAll method, merely an array version of the above strip
        final String[] empty = new String[0];
        final String[] fooSpace = new String[] { "  "+FOO+"  ", "  "+FOO, FOO+"  " };
        final String[] fooDots = new String[] { ".."+FOO+"..", ".."+FOO, FOO+".." };
        final String[] foo = new String[] { FOO, FOO, FOO };

        Assert.assertNull(StringUtils.stripAll((String[]) null));
        // Additional varargs tests
        Assert.assertArrayEquals(empty, StringUtils.stripAll()); // empty array
        Assert.assertArrayEquals(new String[]{null}, StringUtils.stripAll((String) null)); // == new String[]{null}

        Assert.assertArrayEquals(empty, StringUtils.stripAll(empty));
        Assert.assertArrayEquals(foo, StringUtils.stripAll(fooSpace));
        
        Assert.assertNull(StringUtils.stripAll(null, null));
        Assert.assertArrayEquals(foo, StringUtils.stripAll(fooSpace, null));
        Assert.assertArrayEquals(foo, StringUtils.stripAll(fooDots, "."));
    }

    @Test
    public void testStripAccents() {
        final String cue = "\u00C7\u00FA\u00EA";
        Assert.assertEquals( "Failed to strip accents from " + cue, "Cue", StringUtils.stripAccents(cue));

        final String lots = "\u00C0\u00C1\u00C2\u00C3\u00C4\u00C5\u00C7\u00C8\u00C9" + 
                      "\u00CA\u00CB\u00CC\u00CD\u00CE\u00CF\u00D1\u00D2\u00D3" + 
                      "\u00D4\u00D5\u00D6\u00D9\u00DA\u00DB\u00DC\u00DD";
        Assert.assertEquals( "Failed to strip accents from " + lots,
                      "AAAAAACEEEEIIIINOOOOOUUUUY", 
                      StringUtils.stripAccents(lots));

        Assert.assertNull( "Failed null safety", StringUtils.stripAccents(null) );
        Assert.assertEquals( "Failed empty String", "", StringUtils.stripAccents("") );
        Assert.assertEquals( "Failed to handle non-accented text", "control", StringUtils.stripAccents("control") );
        Assert.assertEquals( "Failed to handle easy example", "eclair", StringUtils.stripAccents("\u00E9clair") );
        Assert.assertEquals("ALOSZZCN aloszzcn", StringUtils.stripAccents("\u0104\u0141\u00D3\u015A\u017B\u0179\u0106\u0143 "
                + "\u0105\u0142\u00F3\u015B\u017C\u017A\u0107\u0144"));
    }
}
