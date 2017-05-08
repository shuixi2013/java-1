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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - IsX methods
 */
public class StringUtilsIsTest  {

    @Test
    public void testIsAlpha() {
        Assert.assertFalse(StringUtils.isAlpha(null));
        Assert.assertFalse(StringUtils.isAlpha(""));
        Assert.assertFalse(StringUtils.isAlpha(" "));
        Assert.assertTrue(StringUtils.isAlpha("a"));
        Assert.assertTrue(StringUtils.isAlpha("A"));
        Assert.assertTrue(StringUtils.isAlpha("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
        Assert.assertFalse(StringUtils.isAlpha("ham kso"));
        Assert.assertFalse(StringUtils.isAlpha("1"));
        Assert.assertFalse(StringUtils.isAlpha("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
        Assert.assertFalse(StringUtils.isAlpha("_"));
        Assert.assertFalse(StringUtils.isAlpha("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAlphanumeric() {
        Assert.assertFalse(StringUtils.isAlphanumeric(null));
        Assert.assertFalse(StringUtils.isAlphanumeric(""));
        Assert.assertFalse(StringUtils.isAlphanumeric(" "));
        Assert.assertTrue(StringUtils.isAlphanumeric("a"));
        Assert.assertTrue(StringUtils.isAlphanumeric("A"));
        Assert.assertTrue(StringUtils.isAlphanumeric("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
        Assert.assertFalse(StringUtils.isAlphanumeric("ham kso"));
        Assert.assertTrue(StringUtils.isAlphanumeric("1"));
        Assert.assertTrue(StringUtils.isAlphanumeric("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
        Assert.assertFalse(StringUtils.isAlphanumeric("_"));
        Assert.assertFalse(StringUtils.isAlphanumeric("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsWhitespace() {
        Assert.assertFalse(StringUtils.isWhitespace(null));
        Assert.assertTrue(StringUtils.isWhitespace(""));
        Assert.assertTrue(StringUtils.isWhitespace(" "));
        Assert.assertTrue(StringUtils.isWhitespace("\t \n \t"));
        Assert.assertFalse(StringUtils.isWhitespace("\t aa\n \t"));
        Assert.assertTrue(StringUtils.isWhitespace(" "));
        Assert.assertFalse(StringUtils.isWhitespace(" a "));
        Assert.assertFalse(StringUtils.isWhitespace("a  "));
        Assert.assertFalse(StringUtils.isWhitespace("  a"));
        Assert.assertFalse(StringUtils.isWhitespace("aba"));
        Assert.assertTrue(StringUtils.isWhitespace(StringUtilsTest.WHITESPACE));
        Assert.assertFalse(StringUtils.isWhitespace(StringUtilsTest.NON_WHITESPACE));
    }

    @Test
    public void testIsAlphaspace() {
        Assert.assertFalse(StringUtils.isAlphaSpace(null));
        Assert.assertTrue(StringUtils.isAlphaSpace(""));
        Assert.assertTrue(StringUtils.isAlphaSpace(" "));
        Assert.assertTrue(StringUtils.isAlphaSpace("a"));
        Assert.assertTrue(StringUtils.isAlphaSpace("A"));
        Assert.assertTrue(StringUtils.isAlphaSpace("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
        Assert.assertTrue(StringUtils.isAlphaSpace("ham kso"));
        Assert.assertFalse(StringUtils.isAlphaSpace("1"));
        Assert.assertFalse(StringUtils.isAlphaSpace("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
        Assert.assertFalse(StringUtils.isAlphaSpace("_"));
        Assert.assertFalse(StringUtils.isAlphaSpace("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAlphanumericSpace() {
        Assert.assertFalse(StringUtils.isAlphanumericSpace(null));
        Assert.assertTrue(StringUtils.isAlphanumericSpace(""));
        Assert.assertTrue(StringUtils.isAlphanumericSpace(" "));
        Assert.assertTrue(StringUtils.isAlphanumericSpace("a"));
        Assert.assertTrue(StringUtils.isAlphanumericSpace("A"));
        Assert.assertTrue(StringUtils.isAlphanumericSpace("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
        Assert.assertTrue(StringUtils.isAlphanumericSpace("ham kso"));
        Assert.assertTrue(StringUtils.isAlphanumericSpace("1"));
        Assert.assertTrue(StringUtils.isAlphanumericSpace("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
        Assert.assertFalse(StringUtils.isAlphanumericSpace("_"));
        Assert.assertFalse(StringUtils.isAlphanumericSpace("hkHKHik*khbkuh"));
    }

    @Test
    public void testIsAsciiPrintable_String() {
        Assert.assertFalse(StringUtils.isAsciiPrintable(null));
        Assert.assertTrue(StringUtils.isAsciiPrintable(""));
        Assert.assertTrue(StringUtils.isAsciiPrintable(" "));
        Assert.assertTrue(StringUtils.isAsciiPrintable("a"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("A"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("1"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("Ceki"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("!ab2c~"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("1000"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("10 00"));
        Assert.assertFalse(StringUtils.isAsciiPrintable("10\t00"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("10.00"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("10,00"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("!ab-c~"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("hkHK=Hik6i?UGH_KJgU7.tUJgKJ*GI87GI,kug"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("\u0020"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("\u0021"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("\u007e"));
        Assert.assertFalse(StringUtils.isAsciiPrintable("\u007f"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("G?lc?"));
        Assert.assertTrue(StringUtils.isAsciiPrintable("=?iso-8859-1?Q?G=FClc=FC?="));
        Assert.assertFalse(StringUtils.isAsciiPrintable("G\u00fclc\u00fc"));
    }
  
    @Test
    public void testIsNumeric() {
        Assert.assertFalse(StringUtils.isNumeric(null));
        Assert.assertFalse(StringUtils.isNumeric(""));
        Assert.assertFalse(StringUtils.isNumeric(" "));
        Assert.assertFalse(StringUtils.isNumeric("a"));
        Assert.assertFalse(StringUtils.isNumeric("A"));
        Assert.assertFalse(StringUtils.isNumeric("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
        Assert.assertFalse(StringUtils.isNumeric("ham kso"));
        Assert.assertTrue(StringUtils.isNumeric("1"));
        Assert.assertTrue(StringUtils.isNumeric("1000"));
        Assert.assertTrue(StringUtils.isNumeric("\u0967\u0968\u0969"));
        Assert.assertFalse(StringUtils.isNumeric("\u0967\u0968 \u0969"));
        Assert.assertFalse(StringUtils.isNumeric("2.3"));
        Assert.assertFalse(StringUtils.isNumeric("10 00"));
        Assert.assertFalse(StringUtils.isNumeric("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
        Assert.assertFalse(StringUtils.isNumeric("_"));
        Assert.assertFalse(StringUtils.isNumeric("hkHKHik*khbkuh"));
        Assert.assertFalse(StringUtils.isNumeric("+123"));
        Assert.assertFalse(StringUtils.isNumeric("-123"));
    }

    @Test
    public void testIsNumericSpace() {
        Assert.assertFalse(StringUtils.isNumericSpace(null));
        Assert.assertTrue(StringUtils.isNumericSpace(""));
        Assert.assertTrue(StringUtils.isNumericSpace(" "));
        Assert.assertFalse(StringUtils.isNumericSpace("a"));
        Assert.assertFalse(StringUtils.isNumericSpace("A"));
        Assert.assertFalse(StringUtils.isNumericSpace("kgKgKgKgkgkGkjkjlJlOKLgHdGdHgl"));
        Assert.assertFalse(StringUtils.isNumericSpace("ham kso"));
        Assert.assertTrue(StringUtils.isNumericSpace("1"));
        Assert.assertTrue(StringUtils.isNumericSpace("1000"));
        Assert.assertFalse(StringUtils.isNumericSpace("2.3"));
        Assert.assertTrue(StringUtils.isNumericSpace("10 00"));
        Assert.assertTrue(StringUtils.isNumericSpace("\u0967\u0968\u0969"));
        Assert.assertTrue(StringUtils.isNumericSpace("\u0967\u0968 \u0969"));
        Assert.assertFalse(StringUtils.isNumericSpace("hkHKHik6iUGHKJgU7tUJgKJGI87GIkug"));
        Assert.assertFalse(StringUtils.isNumericSpace("_"));
        Assert.assertFalse(StringUtils.isNumericSpace("hkHKHik*khbkuh"));
    }

}
