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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharUtils}.
 */
public class CharUtilsTest {

    private static final Character CHARACTER_A = new Character('A');
    private static final Character CHARACTER_B = new Character('B');
    private static final char CHAR_COPY = '\u00a9';
    
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new CharUtils());
        final Constructor<?>[] cons = CharUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(BooleanUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(BooleanUtils.class.getModifiers()));
    }
    
    @SuppressWarnings("deprecation") // intentional test of deprecated method
    @Test
    public void testToCharacterObject_char() {
        Assert.assertEquals(new Character('a'), CharUtils.toCharacterObject('a'));
        Assert.assertSame(CharUtils.toCharacterObject('a'), CharUtils.toCharacterObject('a'));
       
        for (int i = 0; i < 128; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            Assert.assertSame(ch, ch2);
            Assert.assertEquals(i, ch.charValue());
        }
        for (int i = 128; i < 196; i++) {
            final Character ch = CharUtils.toCharacterObject((char) i);
            final Character ch2 = CharUtils.toCharacterObject((char) i);
            Assert.assertEquals(ch, ch2);
            Assert.assertTrue(ch != ch2);
            Assert.assertEquals(i, ch.charValue());
            Assert.assertEquals(i, ch2.charValue());
        }
        Assert.assertSame(CharUtils.toCharacterObject("a"), CharUtils.toCharacterObject('a'));
    }
    
    @Test
    public void testToCharacterObject_String() {
        Assert.assertEquals(null, CharUtils.toCharacterObject(null));
        Assert.assertEquals(null, CharUtils.toCharacterObject(""));
        Assert.assertEquals(new Character('a'), CharUtils.toCharacterObject("a"));
        Assert.assertEquals(new Character('a'), CharUtils.toCharacterObject("abc"));
        Assert.assertSame(CharUtils.toCharacterObject("a"), CharUtils.toCharacterObject("a"));
    }
    
    @Test
    public void testToChar_Character() {
        Assert.assertEquals('A', CharUtils.toChar(CHARACTER_A));
        Assert.assertEquals('B', CharUtils.toChar(CHARACTER_B));
        try {
            CharUtils.toChar((Character) null);
        } catch (final IllegalArgumentException ex) {}
    }
    
    @Test
    public void testToChar_Character_char() {
        Assert.assertEquals('A', CharUtils.toChar(CHARACTER_A, 'X'));
        Assert.assertEquals('B', CharUtils.toChar(CHARACTER_B, 'X'));
        Assert.assertEquals('X', CharUtils.toChar((Character) null, 'X'));
    }
    
    @Test
    public void testToChar_String() {
        Assert.assertEquals('A', CharUtils.toChar("A"));
        Assert.assertEquals('B', CharUtils.toChar("BA"));
        try {
            CharUtils.toChar((String) null);
        } catch (final IllegalArgumentException ex) {}
        try {
            CharUtils.toChar("");
        } catch (final IllegalArgumentException ex) {}
    }
    
    @Test
    public void testToChar_String_char() {
        Assert.assertEquals('A', CharUtils.toChar("A", 'X'));
        Assert.assertEquals('B', CharUtils.toChar("BA", 'X'));
        Assert.assertEquals('X', CharUtils.toChar("", 'X'));
        Assert.assertEquals('X', CharUtils.toChar((String) null, 'X'));
    }
    
    @Test
    public void testToIntValue_char() {
        Assert.assertEquals(0, CharUtils.toIntValue('0'));
        Assert.assertEquals(1, CharUtils.toIntValue('1'));
        Assert.assertEquals(2, CharUtils.toIntValue('2'));
        Assert.assertEquals(3, CharUtils.toIntValue('3'));
        Assert.assertEquals(4, CharUtils.toIntValue('4'));
        Assert.assertEquals(5, CharUtils.toIntValue('5'));
        Assert.assertEquals(6, CharUtils.toIntValue('6'));
        Assert.assertEquals(7, CharUtils.toIntValue('7'));
        Assert.assertEquals(8, CharUtils.toIntValue('8'));
        Assert.assertEquals(9, CharUtils.toIntValue('9'));
        try {
            CharUtils.toIntValue('a');
        } catch (final IllegalArgumentException ex) {}
    }
    
    @Test
    public void testToIntValue_char_int() {
        Assert.assertEquals(0, CharUtils.toIntValue('0', -1));
        Assert.assertEquals(3, CharUtils.toIntValue('3', -1));
        Assert.assertEquals(-1, CharUtils.toIntValue('a', -1));
    }
    
    @Test
    public void testToIntValue_Character() {
        Assert.assertEquals(0, CharUtils.toIntValue(new Character('0')));
        Assert.assertEquals(3, CharUtils.toIntValue(new Character('3')));
        try {
            CharUtils.toIntValue(null);
        } catch (final IllegalArgumentException ex) {}
        try {
            CharUtils.toIntValue(CHARACTER_A);
        } catch (final IllegalArgumentException ex) {}
    }
    
    @Test
    public void testToIntValue_Character_int() {
        Assert.assertEquals(0, CharUtils.toIntValue(new Character('0'), -1));
        Assert.assertEquals(3, CharUtils.toIntValue(new Character('3'), -1));
        Assert.assertEquals(-1, CharUtils.toIntValue(new Character('A'), -1));
        Assert.assertEquals(-1, CharUtils.toIntValue(null, -1));
    }
    
    @Test
    public void testToString_char() {
        Assert.assertEquals("a", CharUtils.toString('a'));
        Assert.assertSame(CharUtils.toString('a'), CharUtils.toString('a'));
       
        for (int i = 0; i < 128; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            Assert.assertSame(str, str2);
            Assert.assertEquals(1, str.length());
            Assert.assertEquals(i, str.charAt(0));
        }
        for (int i = 128; i < 196; i++) {
            final String str = CharUtils.toString((char) i);
            final String str2 = CharUtils.toString((char) i);
            Assert.assertEquals(str, str2);
            Assert.assertTrue(str != str2);
            Assert.assertEquals(1, str.length());
            Assert.assertEquals(i, str.charAt(0));
            Assert.assertEquals(1, str2.length());
            Assert.assertEquals(i, str2.charAt(0));
        }
    }
    
    @Test
    public void testToString_Character() {
        Assert.assertEquals(null, CharUtils.toString(null));
        Assert.assertEquals("A", CharUtils.toString(CHARACTER_A));
        Assert.assertSame(CharUtils.toString(CHARACTER_A), CharUtils.toString(CHARACTER_A));
    }
    
    @Test
    public void testToUnicodeEscaped_char() {
        Assert.assertEquals("\\u0041", CharUtils.unicodeEscaped('A'));
        Assert.assertEquals("\\u004c", CharUtils.unicodeEscaped('L'));
       
        for (int i = 0; i < 196; i++) {
            final String str = CharUtils.unicodeEscaped((char) i);
            Assert.assertEquals(6, str.length());
            final int val = Integer.parseInt(str.substring(2), 16);
            Assert.assertEquals(i, val);
        }
        Assert.assertEquals("\\u0999", CharUtils.unicodeEscaped((char) 0x999));
        Assert.assertEquals("\\u1001", CharUtils.unicodeEscaped((char) 0x1001));
    }
    
    @Test
    public void testToUnicodeEscaped_Character() {
        Assert.assertEquals(null, CharUtils.unicodeEscaped(null));
        Assert.assertEquals("\\u0041", CharUtils.unicodeEscaped(CHARACTER_A));
    }
    
    @Test
    public void testIsAscii_char() {
        Assert.assertTrue(CharUtils.isAscii('a'));
        Assert.assertTrue(CharUtils.isAscii('A'));
        Assert.assertTrue(CharUtils.isAscii('3'));
        Assert.assertTrue(CharUtils.isAscii('-'));
        Assert.assertTrue(CharUtils.isAscii('\n'));
        Assert.assertFalse(CharUtils.isAscii(CHAR_COPY));
       
        for (int i = 0; i < 128; i++) {
            if (i < 128) {
                Assert.assertTrue(CharUtils.isAscii((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAscii((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiPrintable_char() {
        Assert.assertTrue(CharUtils.isAsciiPrintable('a'));
        Assert.assertTrue(CharUtils.isAsciiPrintable('A'));
        Assert.assertTrue(CharUtils.isAsciiPrintable('3'));
        Assert.assertTrue(CharUtils.isAsciiPrintable('-'));
        Assert.assertFalse(CharUtils.isAsciiPrintable('\n'));
        Assert.assertFalse(CharUtils.isAscii(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if (i >= 32 && i <= 126) {
                Assert.assertTrue(CharUtils.isAsciiPrintable((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiPrintable((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiControl_char() {
        Assert.assertFalse(CharUtils.isAsciiControl('a'));
        Assert.assertFalse(CharUtils.isAsciiControl('A'));
        Assert.assertFalse(CharUtils.isAsciiControl('3'));
        Assert.assertFalse(CharUtils.isAsciiControl('-'));
        Assert.assertTrue(CharUtils.isAsciiControl('\n'));
        Assert.assertFalse(CharUtils.isAsciiControl(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if (i < 32 || i == 127) {
                Assert.assertTrue(CharUtils.isAsciiControl((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiControl((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiAlpha_char() {
        Assert.assertTrue(CharUtils.isAsciiAlpha('a'));
        Assert.assertTrue(CharUtils.isAsciiAlpha('A'));
        Assert.assertFalse(CharUtils.isAsciiAlpha('3'));
        Assert.assertFalse(CharUtils.isAsciiAlpha('-'));
        Assert.assertFalse(CharUtils.isAsciiAlpha('\n'));
        Assert.assertFalse(CharUtils.isAsciiAlpha(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z')) {
                Assert.assertTrue(CharUtils.isAsciiAlpha((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiAlpha((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiAlphaUpper_char() {
        Assert.assertFalse(CharUtils.isAsciiAlphaUpper('a'));
        Assert.assertTrue(CharUtils.isAsciiAlphaUpper('A'));
        Assert.assertFalse(CharUtils.isAsciiAlphaUpper('3'));
        Assert.assertFalse(CharUtils.isAsciiAlphaUpper('-'));
        Assert.assertFalse(CharUtils.isAsciiAlphaUpper('\n'));
        Assert.assertFalse(CharUtils.isAsciiAlphaUpper(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if (i >= 'A' && i <= 'Z') {
                Assert.assertTrue(CharUtils.isAsciiAlphaUpper((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiAlphaUpper((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiAlphaLower_char() {
        Assert.assertTrue(CharUtils.isAsciiAlphaLower('a'));
        Assert.assertFalse(CharUtils.isAsciiAlphaLower('A'));
        Assert.assertFalse(CharUtils.isAsciiAlphaLower('3'));
        Assert.assertFalse(CharUtils.isAsciiAlphaLower('-'));
        Assert.assertFalse(CharUtils.isAsciiAlphaLower('\n'));
        Assert.assertFalse(CharUtils.isAsciiAlphaLower(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if (i >= 'a' && i <= 'z') {
                Assert.assertTrue(CharUtils.isAsciiAlphaLower((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiAlphaLower((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiNumeric_char() {
        Assert.assertFalse(CharUtils.isAsciiNumeric('a'));
        Assert.assertFalse(CharUtils.isAsciiNumeric('A'));
        Assert.assertTrue(CharUtils.isAsciiNumeric('3'));
        Assert.assertFalse(CharUtils.isAsciiNumeric('-'));
        Assert.assertFalse(CharUtils.isAsciiNumeric('\n'));
        Assert.assertFalse(CharUtils.isAsciiNumeric(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if (i >= '0' && i <= '9') {
                Assert.assertTrue(CharUtils.isAsciiNumeric((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiNumeric((char) i));
            }
        }
    }
    
    @Test
    public void testIsAsciiAlphanumeric_char() {
        Assert.assertTrue(CharUtils.isAsciiAlphanumeric('a'));
        Assert.assertTrue(CharUtils.isAsciiAlphanumeric('A'));
        Assert.assertTrue(CharUtils.isAsciiAlphanumeric('3'));
        Assert.assertFalse(CharUtils.isAsciiAlphanumeric('-'));
        Assert.assertFalse(CharUtils.isAsciiAlphanumeric('\n'));
        Assert.assertFalse(CharUtils.isAsciiAlphanumeric(CHAR_COPY));
       
        for (int i = 0; i < 196; i++) {
            if ((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z') || (i >= '0' && i <= '9')) {
                Assert.assertTrue(CharUtils.isAsciiAlphanumeric((char) i));
            } else {
                Assert.assertFalse(CharUtils.isAsciiAlphanumeric((char) i));
            }
        }
    }

    @Test
    public void testCompare() {
        Assert.assertTrue(CharUtils.compare('a', 'b') < 0);
        Assert.assertTrue(CharUtils.compare('c', 'c') == 0);
        Assert.assertTrue(CharUtils.compare('c', 'a') > 0);
    }
}
