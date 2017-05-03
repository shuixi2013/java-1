/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.by.org.apache.commons.lang3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.apache.commons.lang3.Conversion;
import org.junit.Test;


/**
 * Unit tests {@link Conversion}.
 */
public class ConversionTest {

    /**
     * Tests {@link Conversion#hexDigitToInt(char)}.
     */
    @Test
    public void testHexDigitToInt() {
        Assert.assertEquals(0, Conversion.hexDigitToInt('0'));
        Assert.assertEquals(1, Conversion.hexDigitToInt('1'));
        Assert.assertEquals(2, Conversion.hexDigitToInt('2'));
        Assert.assertEquals(3, Conversion.hexDigitToInt('3'));
        Assert.assertEquals(4, Conversion.hexDigitToInt('4'));
        Assert.assertEquals(5, Conversion.hexDigitToInt('5'));
        Assert.assertEquals(6, Conversion.hexDigitToInt('6'));
        Assert.assertEquals(7, Conversion.hexDigitToInt('7'));
        Assert.assertEquals(8, Conversion.hexDigitToInt('8'));
        Assert.assertEquals(9, Conversion.hexDigitToInt('9'));
        Assert.assertEquals(10, Conversion.hexDigitToInt('A'));
        Assert.assertEquals(10, Conversion.hexDigitToInt('a'));
        Assert.assertEquals(11, Conversion.hexDigitToInt('B'));
        Assert.assertEquals(11, Conversion.hexDigitToInt('b'));
        Assert.assertEquals(12, Conversion.hexDigitToInt('C'));
        Assert.assertEquals(12, Conversion.hexDigitToInt('c'));
        Assert.assertEquals(13, Conversion.hexDigitToInt('D'));
        Assert.assertEquals(13, Conversion.hexDigitToInt('d'));
        Assert.assertEquals(14, Conversion.hexDigitToInt('E'));
        Assert.assertEquals(14, Conversion.hexDigitToInt('e'));
        Assert.assertEquals(15, Conversion.hexDigitToInt('F'));
        Assert.assertEquals(15, Conversion.hexDigitToInt('f'));
        try {
            Conversion.hexDigitToInt('G');
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#hexDigitMsb0ToInt(char)}.
     */
    @Test
    public void testHexDigitMsb0ToInt() {
        Assert.assertEquals(0x0, Conversion.hexDigitMsb0ToInt('0'));
        Assert.assertEquals(0x8, Conversion.hexDigitMsb0ToInt('1'));
        Assert.assertEquals(0x4, Conversion.hexDigitMsb0ToInt('2'));
        Assert.assertEquals(0xC, Conversion.hexDigitMsb0ToInt('3'));
        Assert.assertEquals(0x2, Conversion.hexDigitMsb0ToInt('4'));
        Assert.assertEquals(0xA, Conversion.hexDigitMsb0ToInt('5'));
        Assert.assertEquals(0x6, Conversion.hexDigitMsb0ToInt('6'));
        Assert.assertEquals(0xE, Conversion.hexDigitMsb0ToInt('7'));
        Assert.assertEquals(0x1, Conversion.hexDigitMsb0ToInt('8'));
        Assert.assertEquals(0x9, Conversion.hexDigitMsb0ToInt('9'));
        Assert.assertEquals(0x5, Conversion.hexDigitMsb0ToInt('A'));
        Assert.assertEquals(0x5, Conversion.hexDigitMsb0ToInt('a'));
        Assert.assertEquals(0xD, Conversion.hexDigitMsb0ToInt('B'));
        Assert.assertEquals(0xD, Conversion.hexDigitMsb0ToInt('b'));
        Assert.assertEquals(0x3, Conversion.hexDigitMsb0ToInt('C'));
        Assert.assertEquals(0x3, Conversion.hexDigitMsb0ToInt('c'));
        Assert.assertEquals(0xB, Conversion.hexDigitMsb0ToInt('D'));
        Assert.assertEquals(0xB, Conversion.hexDigitMsb0ToInt('d'));
        Assert.assertEquals(0x7, Conversion.hexDigitMsb0ToInt('E'));
        Assert.assertEquals(0x7, Conversion.hexDigitMsb0ToInt('e'));
        Assert.assertEquals(0xF, Conversion.hexDigitMsb0ToInt('F'));
        Assert.assertEquals(0xF, Conversion.hexDigitMsb0ToInt('f'));
        try {
            Conversion.hexDigitMsb0ToInt('G');
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#hexDigitToBinary(char)}.
     */
    @Test
    public void testHexDigitToBinary() {
        assertBinaryEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitToBinary('0'));
        assertBinaryEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitToBinary('1'));
        assertBinaryEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitToBinary('2'));
        assertBinaryEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitToBinary('3'));
        assertBinaryEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitToBinary('4'));
        assertBinaryEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitToBinary('5'));
        assertBinaryEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitToBinary('6'));
        assertBinaryEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitToBinary('7'));
        assertBinaryEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitToBinary('8'));
        assertBinaryEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitToBinary('9'));
        assertBinaryEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('A'));
        assertBinaryEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('a'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('B'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('b'));
        assertBinaryEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('C'));
        assertBinaryEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('c'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('D'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('d'));
        assertBinaryEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('E'));
        assertBinaryEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('e'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('F'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('f'));
        try {
            Conversion.hexDigitToBinary('G');
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#hexDigitMsb0ToBinary(char)}.
     */
    @Test
    public void testHexDigitMsb0ToBinary() {
        assertBinaryEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitMsb0ToBinary('0'));
        assertBinaryEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitMsb0ToBinary('1'));
        assertBinaryEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitMsb0ToBinary('2'));
        assertBinaryEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitMsb0ToBinary('3'));
        assertBinaryEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitMsb0ToBinary('4'));
        assertBinaryEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitMsb0ToBinary('5'));
        assertBinaryEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitMsb0ToBinary('6'));
        assertBinaryEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitMsb0ToBinary('7'));
        assertBinaryEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitMsb0ToBinary('8'));
        assertBinaryEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitMsb0ToBinary('9'));
        assertBinaryEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBinary('A'));
        assertBinaryEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBinary('a'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBinary('B'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBinary('b'));
        assertBinaryEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBinary('C'));
        assertBinaryEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBinary('c'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBinary('D'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBinary('d'));
        assertBinaryEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBinary('E'));
        assertBinaryEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBinary('e'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBinary('F'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBinary('f'));
        try {
            Conversion.hexDigitMsb0ToBinary('G');
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#binaryToHexDigit(boolean[])}.
     */
    @Test
    public void testBinaryToHexDigit() {
        Assert.assertEquals(
            '0', Conversion.binaryToHexDigit(new boolean[]{false, false, false, false}));
        Assert.assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true, false, false, false}));
        Assert.assertEquals('2', Conversion.binaryToHexDigit(new boolean[]{false, true, false, false}));
        Assert.assertEquals('3', Conversion.binaryToHexDigit(new boolean[]{true, true, false, false}));
        Assert.assertEquals('4', Conversion.binaryToHexDigit(new boolean[]{false, false, true, false}));
        Assert.assertEquals('5', Conversion.binaryToHexDigit(new boolean[]{true, false, true, false}));
        Assert.assertEquals('6', Conversion.binaryToHexDigit(new boolean[]{false, true, true, false}));
        Assert.assertEquals('7', Conversion.binaryToHexDigit(new boolean[]{true, true, true, false}));
        Assert.assertEquals('8', Conversion.binaryToHexDigit(new boolean[]{false, false, false, true}));
        Assert.assertEquals('9', Conversion.binaryToHexDigit(new boolean[]{true, false, false, true}));
        Assert.assertEquals('a', Conversion.binaryToHexDigit(new boolean[]{false, true, false, true}));
        Assert.assertEquals('b', Conversion.binaryToHexDigit(new boolean[]{true, true, false, true}));
        Assert.assertEquals('c', Conversion.binaryToHexDigit(new boolean[]{false, false, true, true}));
        Assert.assertEquals('d', Conversion.binaryToHexDigit(new boolean[]{true, false, true, true}));
        Assert.assertEquals('e', Conversion.binaryToHexDigit(new boolean[]{false, true, true, true}));
        Assert.assertEquals('f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true}));
        Assert.assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true}));
        Assert.assertEquals(
            'f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true, true}));
        try {
            Conversion.binaryToHexDigit(new boolean[]{});
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#binaryBeMsb0ToHexDigit(boolean[], int)}.
     */
    @Test
    public void testBinaryToHexDigit_2args() {
        final boolean[] shortArray = new boolean[]{false, true, true};
        Assert.assertEquals('6', Conversion.binaryToHexDigit(shortArray, 0));
        Assert.assertEquals('3', Conversion.binaryToHexDigit(shortArray, 1));
        Assert.assertEquals('1', Conversion.binaryToHexDigit(shortArray, 2));
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        Assert.assertEquals('5', Conversion.binaryToHexDigit(longArray, 0));
        Assert.assertEquals('2', Conversion.binaryToHexDigit(longArray, 1));
        Assert.assertEquals('9', Conversion.binaryToHexDigit(longArray, 2));
        Assert.assertEquals('c', Conversion.binaryToHexDigit(longArray, 3));
        Assert.assertEquals('6', Conversion.binaryToHexDigit(longArray, 4));
        Assert.assertEquals('3', Conversion.binaryToHexDigit(longArray, 5));
        Assert.assertEquals('1', Conversion.binaryToHexDigit(longArray, 6));
    }

    /**
     * Tests {@link Conversion#binaryToHexDigitMsb0_4bits(boolean[])}.
     */
    @Test
    public void testBinaryToHexDigitMsb0_bits() {
        Assert.assertEquals(
            '0',
            Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, false, false}));
        Assert.assertEquals(
            '1',
            Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, false, true}));
        Assert.assertEquals(
            '2',
            Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, true, false}));
        Assert.assertEquals(
            '3', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, false, true, true}));
        Assert.assertEquals(
            '4',
            Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, false, false}));
        Assert.assertEquals(
            '5', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, false, true}));
        Assert.assertEquals(
            '6', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, true, false}));
        Assert.assertEquals(
            '7', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{false, true, true, true}));
        Assert.assertEquals(
            '8',
            Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, false}));
        Assert.assertEquals(
            '9', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, false, true}));
        Assert.assertEquals(
            'a', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, true, false}));
        Assert.assertEquals(
            'b', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, false, true, true}));
        Assert.assertEquals(
            'c', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, false, false}));
        Assert.assertEquals(
            'd', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, false, true}));
        Assert.assertEquals(
            'e', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true, false}));
        Assert.assertEquals(
            'f', Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{true, true, true, true}));
        try {
            Conversion.binaryToHexDigitMsb0_4bits(new boolean[]{});
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#binaryToHexDigitMsb0_4bits(boolean[], int)}.
     */
    @Test
    public void testBinaryToHexDigitMsb0_4bits_2args() {
        // boolean[] shortArray = new boolean[]{true,true,false};
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(shortArray,0));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(shortArray,1));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(shortArray,2));
        final boolean[] shortArray = new boolean[]{true, true, false, true};
        Assert.assertEquals('d', Conversion.binaryToHexDigitMsb0_4bits(shortArray, 0));
        final boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        Assert.assertEquals('a', Conversion.binaryToHexDigitMsb0_4bits(longArray, 0));
        Assert.assertEquals('4', Conversion.binaryToHexDigitMsb0_4bits(longArray, 1));
        Assert.assertEquals('9', Conversion.binaryToHexDigitMsb0_4bits(longArray, 2));
        Assert.assertEquals('3', Conversion.binaryToHexDigitMsb0_4bits(longArray, 3));
        // assertEquals('6', Conversion.BinaryToHexDigitMsb0(longArray,4));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray,5));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray,6));
        final boolean[] maxLengthArray = new boolean[]{
            true, false, true, false, false, true, true, true};
        Assert.assertEquals('a', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 0));
        Assert.assertEquals('4', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 1));
        Assert.assertEquals('9', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 2));
        Assert.assertEquals('3', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 3));
        Assert.assertEquals('7', Conversion.binaryToHexDigitMsb0_4bits(maxLengthArray, 4));
        // assertEquals('7', Conversion.BinaryToHexDigitMsb0(longArray,5));
        // assertEquals('3', Conversion.BinaryToHexDigitMsb0(longArray,6));
        // assertEquals('1', Conversion.BinaryToHexDigitMsb0(longArray,7));
        final boolean[] javaDocCheck = new boolean[]{
            true, false, false, true, true, false, true, false};
        Assert.assertEquals('d', Conversion.binaryToHexDigitMsb0_4bits(javaDocCheck, 3));

    }

    /**
     * Tests {@link Conversion#binaryToHexDigit(boolean[])}.
     */
    @Test
    public void testBinaryBeMsb0ToHexDigit() {
        Assert.assertEquals(
            '0', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, false, false}));
        Assert.assertEquals(
            '1', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, false, true}));
        Assert.assertEquals(
            '2', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, true, false}));
        Assert.assertEquals(
            '3', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, false, true, true}));
        Assert.assertEquals(
            '4', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, false, false}));
        Assert.assertEquals(
            '5', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, false, true}));
        Assert.assertEquals(
            '6', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, true, false}));
        Assert.assertEquals(
            '7', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{false, true, true, true}));
        Assert.assertEquals(
            '8', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, false, false}));
        Assert.assertEquals(
            '9', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, false, true}));
        Assert.assertEquals(
            'a', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, true, false}));
        Assert.assertEquals(
            'b', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, false, true, true}));
        Assert.assertEquals(
            'c', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, false, false}));
        Assert.assertEquals(
            'd', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, false, true}));
        Assert.assertEquals(
            'e', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, false}));
        Assert.assertEquals(
            'f', Conversion.binaryBeMsb0ToHexDigit(new boolean[]{true, true, true, true}));
        Assert.assertEquals(
            '4',
            Conversion.binaryBeMsb0ToHexDigit(new boolean[]{
                true, false, false, false, false, false, false, false, false, false, false,
                false, false, true, false, false}));
        try {
            Conversion.binaryBeMsb0ToHexDigit(new boolean[]{});
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#binaryToHexDigit(boolean[], int)}.
     */
    @Test
    public void testBinaryBeMsb0ToHexDigit_2args() {
        Assert.assertEquals(
            '5',
            Conversion.binaryBeMsb0ToHexDigit(new boolean[]{
                true, false, false, false, false, false, false, false, false, false, false,
                true, false, true, false, false}, 2));

        final boolean[] shortArray = new boolean[]{true, true, false};
        Assert.assertEquals('6', Conversion.binaryBeMsb0ToHexDigit(shortArray, 0));
        Assert.assertEquals('3', Conversion.binaryBeMsb0ToHexDigit(shortArray, 1));
        Assert.assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(shortArray, 2));
        final boolean[] shortArray2 = new boolean[]{true, true, true, false, false, true, false, true};
        Assert.assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 0));
        Assert.assertEquals('2', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 1));
        Assert.assertEquals('9', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 2));
        Assert.assertEquals('c', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 3));
        Assert.assertEquals('e', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 4));
        Assert.assertEquals('7', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 5));
        Assert.assertEquals('3', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 6));
        Assert.assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(shortArray2, 7));
        final boolean[] multiBytesArray = new boolean[]{
            true, true, false, false, true, false, true, false, true, true, true, false, false,
            true, false, true};
        Assert.assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 0));
        Assert.assertEquals('2', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 1));
        Assert.assertEquals('9', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 2));
        Assert.assertEquals('c', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 3));
        Assert.assertEquals('e', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 4));
        Assert.assertEquals('7', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 5));
        Assert.assertEquals('b', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 6));
        Assert.assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 7));

        Assert.assertEquals('a', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 8));
        Assert.assertEquals('5', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 9));
        Assert.assertEquals('2', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 10));
        Assert.assertEquals('9', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 11));
        Assert.assertEquals('c', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 12));
        Assert.assertEquals('6', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 13));
        Assert.assertEquals('3', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 14));
        Assert.assertEquals('1', Conversion.binaryBeMsb0ToHexDigit(multiBytesArray, 15));

    }

    /**
     * Tests {@link Conversion#intToHexDigit(int)}.
     */
    @Test
    public void testIntToHexDigit() {
        Assert.assertEquals('0', Conversion.intToHexDigit(0));
        Assert.assertEquals('1', Conversion.intToHexDigit(1));
        Assert.assertEquals('2', Conversion.intToHexDigit(2));
        Assert.assertEquals('3', Conversion.intToHexDigit(3));
        Assert.assertEquals('4', Conversion.intToHexDigit(4));
        Assert.assertEquals('5', Conversion.intToHexDigit(5));
        Assert.assertEquals('6', Conversion.intToHexDigit(6));
        Assert.assertEquals('7', Conversion.intToHexDigit(7));
        Assert.assertEquals('8', Conversion.intToHexDigit(8));
        Assert.assertEquals('9', Conversion.intToHexDigit(9));
        Assert.assertEquals('a', Conversion.intToHexDigit(10));
        Assert.assertEquals('b', Conversion.intToHexDigit(11));
        Assert.assertEquals('c', Conversion.intToHexDigit(12));
        Assert.assertEquals('d', Conversion.intToHexDigit(13));
        Assert.assertEquals('e', Conversion.intToHexDigit(14));
        Assert.assertEquals('f', Conversion.intToHexDigit(15));
        try {
            Conversion.intToHexDigit(16);
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#intToHexDigitMsb0(int)}.
     */
    @Test
    public void testIntToHexDigitMsb0() {
        Assert.assertEquals('0', Conversion.intToHexDigitMsb0(0));
        Assert.assertEquals('8', Conversion.intToHexDigitMsb0(1));
        Assert.assertEquals('4', Conversion.intToHexDigitMsb0(2));
        Assert.assertEquals('c', Conversion.intToHexDigitMsb0(3));
        Assert.assertEquals('2', Conversion.intToHexDigitMsb0(4));
        Assert.assertEquals('a', Conversion.intToHexDigitMsb0(5));
        Assert.assertEquals('6', Conversion.intToHexDigitMsb0(6));
        Assert.assertEquals('e', Conversion.intToHexDigitMsb0(7));
        Assert.assertEquals('1', Conversion.intToHexDigitMsb0(8));
        Assert.assertEquals('9', Conversion.intToHexDigitMsb0(9));
        Assert.assertEquals('5', Conversion.intToHexDigitMsb0(10));
        Assert.assertEquals('d', Conversion.intToHexDigitMsb0(11));
        Assert.assertEquals('3', Conversion.intToHexDigitMsb0(12));
        Assert.assertEquals('b', Conversion.intToHexDigitMsb0(13));
        Assert.assertEquals('7', Conversion.intToHexDigitMsb0(14));
        Assert.assertEquals('f', Conversion.intToHexDigitMsb0(15));
        try {
            Conversion.intToHexDigitMsb0(16);
            Assert.fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }

    static String dbgPrint(final boolean[] src) {
        final StringBuilder sb = new StringBuilder();
        for (final boolean e : src) {
            if (e) {
                sb.append("1,");
            } else {
                sb.append("0,");
            }
        }
        final String out = sb.toString();
        return out.substring(0, out.length() - 1);
    }

    // org.junit.Assert(boolean[], boolean[]) does not exist in JUnit 4.2
    static void assertBinaryEquals(final boolean[] expected, final boolean[] actual) {
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++ ) {
            try {
                Assert.assertEquals(expected[i], actual[i]);
            } catch (final Throwable e) {
                final String msg = "Mismatch at index "
                    + i
                    + " between:\n"
                    + dbgPrint(expected)
                    + " and\n"
                    + dbgPrint(actual);
                Assert.fail(msg + "\n" + e.getMessage());
            }
        }
    }

    /**
     * Tests {@link Conversion#intArrayToLong(int[], int, long, int, int)}.
     */
    @Test
    public void testIntArrayToLong() {
        final int[] src = new int[]{0xCDF1F0C1, 0x0F123456, 0x78000000};
        Assert.assertEquals(0x0000000000000000L, Conversion.intArrayToLong(src, 0, 0L, 0, 0));
        Assert.assertEquals(0x0000000000000000L, Conversion.intArrayToLong(src, 1, 0L, 0, 0));
        Assert.assertEquals(0x00000000CDF1F0C1L, Conversion.intArrayToLong(src, 0, 0L, 0, 1));
        Assert.assertEquals(0x0F123456CDF1F0C1L, Conversion.intArrayToLong(src, 0, 0L, 0, 2));
        Assert.assertEquals(0x000000000F123456L, Conversion.intArrayToLong(src, 1, 0L, 0, 1));
        Assert.assertEquals(
            0x123456789ABCDEF0L, Conversion.intArrayToLong(src, 0, 0x123456789ABCDEF0L, 0, 0));
        Assert.assertEquals(
            0x1234567878000000L, Conversion.intArrayToLong(src, 2, 0x123456789ABCDEF0L, 0, 1));
        // assertEquals(0x0F12345678000000L,Conversion.intsToLong(src,1,0x123456789ABCDEF0L,32,2));
    }

    /**
     * Tests {@link Conversion#shortArrayToLong(short[], int, long, int, int)}.
     */
    @Test
    public void testShortArrayToLong() {
        final short[] src = new short[]{
            (short)0xCDF1, (short)0xF0C1, (short)0x0F12, (short)0x3456, (short)0x7800};
        Assert.assertEquals(0x0000000000000000L, Conversion.shortArrayToLong(src, 0, 0L, 0, 0));
        Assert.assertEquals(0x000000000000CDF1L, Conversion.shortArrayToLong(src, 0, 0L, 0, 1));
        Assert.assertEquals(0x00000000F0C1CDF1L, Conversion.shortArrayToLong(src, 0, 0L, 0, 2));
        Assert.assertEquals(0x780034560F12F0C1L, Conversion.shortArrayToLong(src, 1, 0L, 0, 4));
        Assert.assertEquals(
            0x123456789ABCDEF0L, Conversion.shortArrayToLong(src, 0, 0x123456789ABCDEF0L, 0, 0));
        Assert.assertEquals(
            0x123456CDF1BCDEF0L,
            Conversion.shortArrayToLong(src, 0, 0x123456789ABCDEF0L, 24, 1));
        Assert.assertEquals(
            0x123478003456DEF0L,
            Conversion.shortArrayToLong(src, 3, 0x123456789ABCDEF0L, 16, 2));
    }

    /**
     * Tests {@link Conversion#byteArrayToLong(byte[], int, long, int, int)}.
     */
    @Test
    public void testByteArrayToLong() {
        final byte[] src = new byte[]{
            (byte)0xCD, (byte)0xF1, (byte)0xF0, (byte)0xC1, (byte)0x0F, (byte)0x12, (byte)0x34,
            (byte)0x56, (byte)0x78};
        Assert.assertEquals(0x0000000000000000L, Conversion.byteArrayToLong(src, 0, 0L, 0, 0));
        Assert.assertEquals(0x00000000000000CDL, Conversion.byteArrayToLong(src, 0, 0L, 0, 1));
        Assert.assertEquals(0x00000000C1F0F1CDL, Conversion.byteArrayToLong(src, 0, 0L, 0, 4));
        Assert.assertEquals(0x000000000FC1F0F1L, Conversion.byteArrayToLong(src, 1, 0L, 0, 4));
        Assert.assertEquals(
            0x123456789ABCDEF0L, Conversion.byteArrayToLong(src, 0, 0x123456789ABCDEF0L, 0, 0));
        Assert.assertEquals(
            0x12345678CDBCDEF0L, Conversion.byteArrayToLong(src, 0, 0x123456789ABCDEF0L, 24, 1));
        Assert.assertEquals(
            0x123456789A7856F0L, Conversion.byteArrayToLong(src, 7, 0x123456789ABCDEF0L, 8, 2));
    }

    /**
     * Tests {@link Conversion#shortArrayToInt(short[], int, int, int, int)}.
     */
    @Test
    public void testShortArrayToInt() {
        final short[] src = new short[]{
            (short)0xCDF1, (short)0xF0C1, (short)0x0F12, (short)0x3456, (short)0x7800};
        Assert.assertEquals(0x00000000, Conversion.shortArrayToInt(src, 0, 0, 0, 0));
        Assert.assertEquals(0x0000CDF1, Conversion.shortArrayToInt(src, 0, 0, 0, 1));
        Assert.assertEquals(0xF0C1CDF1, Conversion.shortArrayToInt(src, 0, 0, 0, 2));
        Assert.assertEquals(0x0F12F0C1, Conversion.shortArrayToInt(src, 1, 0, 0, 2));
        Assert.assertEquals(0x12345678, Conversion.shortArrayToInt(src, 0, 0x12345678, 0, 0));
        Assert.assertEquals(0xCDF15678, Conversion.shortArrayToInt(src, 0, 0x12345678, 16, 1));
        // assertEquals(0x34567800,Conversion.ShortArrayToInt(src, 3, 0x12345678, 16, 2));
    }

    /**
     * Tests {@link Conversion#byteArrayToInt(byte[], int, int, int, int)}.
     */
    @Test
    public void testByteArrayToInt() {
        final byte[] src = new byte[]{
            (byte)0xCD, (byte)0xF1, (byte)0xF0, (byte)0xC1, (byte)0x0F, (byte)0x12, (byte)0x34,
            (byte)0x56, (byte)0x78};
        Assert.assertEquals(0x00000000, Conversion.byteArrayToInt(src, 0, 0, 0, 0));
        Assert.assertEquals(0x000000CD, Conversion.byteArrayToInt(src, 0, 0, 0, 1));
        Assert.assertEquals(0xC1F0F1CD, Conversion.byteArrayToInt(src, 0, 0, 0, 4));
        Assert.assertEquals(0x0FC1F0F1, Conversion.byteArrayToInt(src, 1, 0, 0, 4));
        Assert.assertEquals(0x12345678, Conversion.byteArrayToInt(src, 0, 0x12345678, 0, 0));
        Assert.assertEquals(0xCD345678, Conversion.byteArrayToInt(src, 0, 0x12345678, 24, 1));
        // assertEquals(0x56341278,Conversion.ByteArrayToInt(src, 5, 0x01234567, 8, 4));
    }

    /**
     * Tests {@link Conversion#byteArrayToShort(byte[], int, short, int, int)}.
     */
    @Test
    public void testByteArrayToShort() {
        final byte[] src = new byte[]{
            (byte)0xCD, (byte)0xF1, (byte)0xF0, (byte)0xC1, (byte)0x0F, (byte)0x12, (byte)0x34,
            (byte)0x56, (byte)0x78};
        Assert.assertEquals((short)0x0000, Conversion.byteArrayToShort(src, 0, (short)0, 0, 0));
        Assert.assertEquals((short)0x00CD, Conversion.byteArrayToShort(src, 0, (short)0, 0, 1));
        Assert.assertEquals((short)0xF1CD, Conversion.byteArrayToShort(src, 0, (short)0, 0, 2));
        Assert.assertEquals((short)0xF0F1, Conversion.byteArrayToShort(src, 1, (short)0, 0, 2));
        Assert.assertEquals((short)0x1234, Conversion.byteArrayToShort(src, 0, (short)0x1234, 0, 0));
        Assert.assertEquals((short)0xCD34, Conversion.byteArrayToShort(src, 0, (short)0x1234, 8, 1));
        // assertEquals((short)0x5678,Conversion.ByteArrayToShort(src, 7, (short) 0x0123, 8,
        // 2));
    }

    /**
     * Tests {@link Conversion#hexToLong(String, int, long, int, int)}.
     */
    @Test
    public void testHexToLong() {
        final String src = "CDF1F0C10F12345678";
        Assert.assertEquals(0x0000000000000000L, Conversion.hexToLong(src, 0, 0L, 0, 0));
        Assert.assertEquals(0x000000000000000CL, Conversion.hexToLong(src, 0, 0L, 0, 1));
        Assert.assertEquals(0x000000001C0F1FDCL, Conversion.hexToLong(src, 0, 0L, 0, 8));
        Assert.assertEquals(0x0000000001C0F1FDL, Conversion.hexToLong(src, 1, 0L, 0, 8));
        Assert.assertEquals(
            0x123456798ABCDEF0L, Conversion.hexToLong(src, 0, 0x123456798ABCDEF0L, 0, 0));
        Assert.assertEquals(
            0x1234567876BCDEF0L, Conversion.hexToLong(src, 15, 0x123456798ABCDEF0L, 24, 3));
    }

    /**
     * Tests {@link Conversion#hexToInt(String, int, int, int, int)}.
     */
    @Test
    public void testHexToInt() {
        final String src = "CDF1F0C10F12345678";
        Assert.assertEquals(0x00000000, Conversion.hexToInt(src, 0, 0, 0, 0));
        Assert.assertEquals(0x0000000C, Conversion.hexToInt(src, 0, 0, 0, 1));
        Assert.assertEquals(0x1C0F1FDC, Conversion.hexToInt(src, 0, 0, 0, 8));
        Assert.assertEquals(0x01C0F1FD, Conversion.hexToInt(src, 1, 0, 0, 8));
        Assert.assertEquals(0x12345679, Conversion.hexToInt(src, 0, 0x12345679, 0, 0));
        Assert.assertEquals(0x87645679, Conversion.hexToInt(src, 15, 0x12345679, 20, 3));
    }

    /**
     * Tests {@link Conversion#hexToShort(String, int, short, int, int)}.
     */
    @Test
    public void testHexToShort() {
        final String src = "CDF1F0C10F12345678";
        Assert.assertEquals((short)0x0000, Conversion.hexToShort(src, 0, (short)0, 0, 0));
        Assert.assertEquals((short)0x000C, Conversion.hexToShort(src, 0, (short)0, 0, 1));
        Assert.assertEquals((short)0x1FDC, Conversion.hexToShort(src, 0, (short)0, 0, 4));
        Assert.assertEquals((short)0xF1FD, Conversion.hexToShort(src, 1, (short)0, 0, 4));
        Assert.assertEquals((short)0x1234, Conversion.hexToShort(src, 0, (short)0x1234, 0, 0));
        Assert.assertEquals((short)0x8764, Conversion.hexToShort(src, 15, (short)0x1234, 4, 3));
    }

    /**
     * Tests {@link Conversion#hexToByte(String, int, byte, int, int)}.
     */
    @Test
    public void testHexToByte() {
        final String src = "CDF1F0C10F12345678";
        Assert.assertEquals((byte)0x00, Conversion.hexToByte(src, 0, (byte)0, 0, 0));
        Assert.assertEquals((byte)0x0C, Conversion.hexToByte(src, 0, (byte)0, 0, 1));
        Assert.assertEquals((byte)0xDC, Conversion.hexToByte(src, 0, (byte)0, 0, 2));
        Assert.assertEquals((byte)0xFD, Conversion.hexToByte(src, 1, (byte)0, 0, 2));
        Assert.assertEquals((byte)0x34, Conversion.hexToByte(src, 0, (byte)0x34, 0, 0));
        Assert.assertEquals((byte)0x84, Conversion.hexToByte(src, 17, (byte)0x34, 4, 1));
    }

    /**
     * Tests {@link Conversion#binaryToLong(boolean[], int, long, int, int)}.
     */
    @Test
    public void testBinaryToLong() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        Assert.assertEquals(0x0000000000000000L, Conversion.binaryToLong(src, 0, 0L, 0, 0));
        Assert.assertEquals(0x000000000000000CL, Conversion.binaryToLong(src, 0, 0L, 0, 1 * 4));
        Assert.assertEquals(0x000000001C0F1FDCL, Conversion.binaryToLong(src, 0, 0L, 0, 8 * 4));
        Assert.assertEquals(0x0000000001C0F1FDL, Conversion.binaryToLong(src, 1 * 4, 0L, 0, 8 * 4));
        Assert.assertEquals(
            0x123456798ABCDEF0L, Conversion.binaryToLong(src, 0, 0x123456798ABCDEF0L, 0, 0));
        Assert.assertEquals(
            0x1234567876BCDEF0L,
            Conversion.binaryToLong(src, 15 * 4, 0x123456798ABCDEF0L, 24, 3 * 4));
    }

    /**
     * Tests {@link Conversion#binaryToInt(boolean[], int, int, int, int)}.
     */
    @Test
    public void testBinaryToInt() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        Assert.assertEquals(0x00000000, Conversion.binaryToInt(src, 0 * 4, 0, 0, 0 * 4));
        Assert.assertEquals(0x0000000C, Conversion.binaryToInt(src, 0 * 4, 0, 0, 1 * 4));
        Assert.assertEquals(0x1C0F1FDC, Conversion.binaryToInt(src, 0 * 4, 0, 0, 8 * 4));
        Assert.assertEquals(0x01C0F1FD, Conversion.binaryToInt(src, 1 * 4, 0, 0, 8 * 4));
        Assert.assertEquals(0x12345679, Conversion.binaryToInt(src, 0 * 4, 0x12345679, 0, 0 * 4));
        Assert.assertEquals(0x87645679, Conversion.binaryToInt(src, 15 * 4, 0x12345679, 20, 3 * 4));
    }

    /**
     * Tests {@link Conversion#binaryToShort(boolean[], int, short, int, int)}.
     */
    @Test
    public void testBinaryToShort() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        Assert.assertEquals((short)0x0000, Conversion.binaryToShort(src, 0 * 4, (short)0, 0, 0 * 4));
        Assert.assertEquals((short)0x000C, Conversion.binaryToShort(src, 0 * 4, (short)0, 0, 1 * 4));
        Assert.assertEquals((short)0x1FDC, Conversion.binaryToShort(src, 0 * 4, (short)0, 0, 4 * 4));
        Assert.assertEquals((short)0xF1FD, Conversion.binaryToShort(src, 1 * 4, (short)0, 0, 4 * 4));
        Assert.assertEquals(
            (short)0x1234, Conversion.binaryToShort(src, 0 * 4, (short)0x1234, 0, 0 * 4));
        Assert.assertEquals(
            (short)0x8764, Conversion.binaryToShort(src, 15 * 4, (short)0x1234, 4, 3 * 4));
    }

    /**
     * Tests {@link Conversion#binaryToByte(boolean[], int, byte, int, int)}.
     */
    @Test
    public void testBinaryToByte() {
        final boolean[] src = new boolean[]{
            false, false, true, true, true, false, true, true, true, true, true, true, true,
            false, false, false, true, true, true, true, false, false, false, false, false,
            false, true, true, true, false, false, false, false, false, false, false, true,
            true, true, true, true, false, false, false, false, true, false, false, true, true,
            false, false, false, false, true, false, true, false, true, false, false, true,
            true, false, true, true, true, false, false, false, false, true};
        // conversion of "CDF1F0C10F12345678" by HexToBinary
        Assert.assertEquals((byte)0x00, Conversion.binaryToByte(src, 0 * 4, (byte)0, 0, 0 * 4));
        Assert.assertEquals((byte)0x0C, Conversion.binaryToByte(src, 0 * 4, (byte)0, 0, 1 * 4));
        Assert.assertEquals((byte)0xDC, Conversion.binaryToByte(src, 0 * 4, (byte)0, 0, 2 * 4));
        Assert.assertEquals((byte)0xFD, Conversion.binaryToByte(src, 1 * 4, (byte)0, 0, 2 * 4));
        Assert.assertEquals((byte)0x34, Conversion.binaryToByte(src, 0 * 4, (byte)0x34, 0, 0 * 4));
        Assert.assertEquals((byte)0x84, Conversion.binaryToByte(src, 17 * 4, (byte)0x34, 4, 1 * 4));
    }

    /**
     * Tests {@link Conversion#longToIntArray(long, int, int[], int, int)}.
     */
    @Test
    public void testLongToIntArray() {
        Assert.assertArrayEquals(
            new int[]{}, Conversion.longToIntArray(0x0000000000000000L, 0, new int[]{}, 0, 0));
        Assert.assertArrayEquals(
            new int[]{}, Conversion.longToIntArray(0x0000000000000000L, 100, new int[]{}, 0, 0));
        Assert.assertArrayEquals(
            new int[]{}, Conversion.longToIntArray(0x0000000000000000L, 0, new int[]{}, 100, 0));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 0, 0));
        Assert.assertArrayEquals(
            new int[]{0x90ABCDEF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new int[]{0x90ABCDEF, 0x12345678, 0xFFFFFFFF, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 0, 2));
        // assertArrayEquals(new
        // int[]{0x90ABCDEF,0x12345678,0x90ABCDEF,0x12345678},Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0,new int[]{-1,-1,-1,-1},0,4));//rejected by assertion
        // assertArrayEquals(new
        // int[]{0xFFFFFFFF,0x90ABCDEF,0x12345678,0x90ABCDEF},Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 0,new int[]{-1,-1,-1,-1},1,3));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x90ABCDEF, 0x12345678},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 2, 2));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x90ABCDEF, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0x90ABCDEF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 0, new int[]{-1, -1, -1, -1}, 3, 1));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x4855E6F7, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 1, new int[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x242AF37B, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 2, new int[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x121579BD, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 3, new int[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new int[]{0xFFFFFFFF, 0xFFFFFFFF, 0x890ABCDE, 0xFFFFFFFF},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 4, new int[]{-1, -1, -1, -1}, 2, 1));
        // assertArrayEquals(new
        // int[]{0x4855E6F7,0x091A2B3C,0x4855E6F7,0x091A2B3C},Conversion.longToIntArray(0x1234567890ABCDEFL,
        // 1,new int[]{-1,-1,-1,-1},0,4));//rejected by assertion
        Assert.assertArrayEquals(
            new int[]{0x091A2B3C},
            Conversion.longToIntArray(0x1234567890ABCDEFL, 33, new int[]{0}, 0, 1));
    }

    /**
     * Tests {@link Conversion#longToShortArray(long, int, short[], int, int)}.
     */
    @Test
    public void testLongToShortArray() {
        Assert.assertArrayEquals(
            new short[]{},
            Conversion.longToShortArray(0x0000000000000000L, 0, new short[]{}, 0, 0));
        Assert.assertArrayEquals(
            new short[]{},
            Conversion.longToShortArray(0x0000000000000000L, 100, new short[]{}, 0, 0));
        Assert.assertArrayEquals(
            new short[]{},
            Conversion.longToShortArray(0x0000000000000000L, 0, new short[]{}, 100, 0));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xFFFF, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 0));
        Assert.assertArrayEquals(
            new short[]{(short)0xCDEF, (short)0xFFFF, (short)0xFFFF, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xCDEF, (short)0x90AB, (short)0xFFFF, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 2));
        Assert.assertArrayEquals(
            new short[]{(short)0xCDEF, (short)0x90AB, (short)0x5678, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 3));
        Assert.assertArrayEquals(
            new short[]{(short)0xCDEF, (short)0x90AB, (short)0x5678, (short)0x1234},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 0, 4));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xCDEF, (short)0x90AB, (short)0x5678},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 1, 3));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xCDEF, (short)0x90AB},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 2, 2));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xCDEF, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xFFFF, (short)0xCDEF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 0, new short[]{-1, -1, -1, -1}, 3, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xE6F7, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 1, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xF37B, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 2, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x79BD, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 3, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xBCDE, (short)0xFFFF},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 4, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xE6F7, (short)0x4855, (short)0x2B3C, (short)0x091A},
            Conversion.longToShortArray(
                0x1234567890ABCDEFL, 1, new short[]{-1, -1, -1, -1}, 0, 4));
        Assert.assertArrayEquals(
            new short[]{(short)0x2B3C},
            Conversion.longToShortArray(0x1234567890ABCDEFL, 33, new short[]{0}, 0, 1));
    }

    /**
     * Tests {@link Conversion#intToShortArray(int, int, short[], int, int)}.
     */
    @Test
    public void testIntToShortArray() {
        Assert.assertArrayEquals(
            new short[]{}, Conversion.intToShortArray(0x00000000, 0, new short[]{}, 0, 0));
        Assert.assertArrayEquals(
            new short[]{}, Conversion.intToShortArray(0x00000000, 100, new short[]{}, 0, 0));
        Assert.assertArrayEquals(
            new short[]{}, Conversion.intToShortArray(0x00000000, 0, new short[]{}, 100, 0));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xFFFF, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 0, 0));
        Assert.assertArrayEquals(
            new short[]{(short)0x5678, (short)0xFFFF, (short)0xFFFF, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0x5678, (short)0x1234, (short)0xFFFF, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 0, 2));
        // assertArrayEquals(new
        // short[]{(short)0x5678,(short)0x1234,(short)0x5678,(short)0xFFFF},Conversion.intToShortArray(0x12345678,
        // 0,new short[]{-1,-1,-1,-1},0,3));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short)0x5678,(short)0x1234,(short)0x5678,(short)0x1234},Conversion.intToShortArray(0x12345678,
        // 0,new short[]{-1,-1,-1,-1},0,4));
        // assertArrayEquals(new
        // short[]{(short)0xFFFF,(short)0x5678,(short)0x1234,(short)0x5678},Conversion.intToShortArray(0x12345678,
        // 0,new short[]{-1,-1,-1,-1},1,3));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x5678, (short)0x1234},
            Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 2, 2));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x5678, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0xFFFF, (short)0x5678},
            Conversion.intToShortArray(0x12345678, 0, new short[]{-1, -1, -1, -1}, 3, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x2B3C, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 1, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x159E, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 2, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x8ACF, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 3, new short[]{-1, -1, -1, -1}, 2, 1));
        Assert.assertArrayEquals(
            new short[]{(short)0xFFFF, (short)0xFFFF, (short)0x4567, (short)0xFFFF},
            Conversion.intToShortArray(0x12345678, 4, new short[]{-1, -1, -1, -1}, 2, 1));
        // assertArrayEquals(new
        // short[]{(short)0xE6F7,(short)0x4855,(short)0x2B3C,(short)0x091A},Conversion.intToShortArray(0x12345678,
        // 1,new short[]{-1,-1,-1,-1},0,4));//rejected by assertion
        // assertArrayEquals(new
        // short[]{(short)0x2B3C},Conversion.intToShortArray(0x12345678,33,new
        // short[]{0},0,1));//rejected by assertion
        Assert.assertArrayEquals(
            new short[]{(short)0x091A},
            Conversion.intToShortArray(0x12345678, 17, new short[]{0}, 0, 1));
    }

    /**
     * Tests {@link Conversion#longToByteArray(long, int, byte[], int, int)}.
     */
    @Test
    public void testLongToByteArray() {
        Assert.assertArrayEquals(
            new byte[]{},
            Conversion.longToByteArray(0x0000000000000000L, 0, new byte[]{}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{},
            Conversion.longToByteArray(0x0000000000000000L, 100, new byte[]{}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{},
            Conversion.longToByteArray(0x0000000000000000L, 0, new byte[]{}, 100, 0));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 2));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xAB, (byte)0x90, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 4));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xAB, (byte)0x90, (byte)0x78, (byte)0x56,
                (byte)0x34, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 7));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xAB, (byte)0x90, (byte)0x78, (byte)0x56,
                (byte)0x34, (byte)0x12, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 8));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 2));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xAB,
                (byte)0x90, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xAB,
                (byte)0x90, (byte)0x78, (byte)0x56, (byte)0x34, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 7));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xAB,
                (byte)0x90, (byte)0x78, (byte)0x56, (byte)0x34, (byte)0x12},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 8));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xF7, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 1, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0x7B, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 2, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0x6F, (byte)0x5E, (byte)0x85,
                (byte)0xC4, (byte)0xB3, (byte)0xA2, (byte)0x91, (byte)0x00},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 5, new byte[]{
                -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 8));
        // assertArrayEquals(new
        // byte[]{(byte)0xFF,(byte)0x00,(byte)0xFF,(byte)0x5E,(byte)0x85,(byte)0xC4,(byte)0xB3,(byte)0xA2,(byte)0x91,(byte)0x00,(byte)0x00},Conversion.longToByteArray(0x1234567890ABCDEFL,13,new
        // byte[]{-1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1},3,8));//rejected by assertion
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0x5E, (byte)0x85, (byte)0xC4,
                (byte)0xB3, (byte)0xA2, (byte)0x91, (byte)0x00, (byte)0xFF},
            Conversion.longToByteArray(0x1234567890ABCDEFL, 13, new byte[]{
                -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 7));
    }

    /**
     * Tests {@link Conversion#intToByteArray(int, int, byte[], int, int)}.
     */
    @Test
    public void testIntToByteArray() {
        Assert.assertArrayEquals(
            new byte[]{}, Conversion.intToByteArray(0x00000000, 0, new byte[]{}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{}, Conversion.intToByteArray(0x00000000, 100, new byte[]{}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{}, Conversion.intToByteArray(0x00000000, 0, new byte[]{}, 100, 0));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 2));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xAB, (byte)0x90, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 4));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 2));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xAB,
                (byte)0x90, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xF7, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 1, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0x7B, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 2, new byte[]{
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0x6F, (byte)0x5E, (byte)0x85,
                (byte)0xFC, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 5, new byte[]{
                -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 4));
        // assertArrayEquals(new
        // byte[]{(byte)0xFF,(byte)0x00,(byte)0xFF,(byte)0x5E,(byte)0x85,(byte)0xFC,(byte)0x00,(byte)0xFF,(byte)0xFF,(byte)0xFF,(byte)0xFF},Conversion.intToByteArray(0x90ABCDEF,13,new
        // byte[]{-1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1},3,4));//rejected by assertion
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0x5E, (byte)0x85, (byte)0xFC,
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF},
            Conversion.intToByteArray(0x90ABCDEF, 13, new byte[]{
                -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1}, 3, 3));
    }

    /**
     * Tests {@link Conversion#shortToByteArray(short, int, byte[], int, int)}.
     */
    @Test
    public void testShortToByteArray() {
        Assert.assertArrayEquals(
            new byte[]{}, Conversion.shortToByteArray((short)0x0000, 0, new byte[]{}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{}, Conversion.shortToByteArray((short)0x0000, 100, new byte[]{}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{}, Conversion.shortToByteArray((short)0x0000, 0, new byte[]{}, 100, 0));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 0, 0));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xEF, (byte)0xCD, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 0, 2));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 3, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xEF, (byte)0xCD, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 0, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 3, 2));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xF7, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 1, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0x7B, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 2, new byte[]{
                -1, -1, -1, -1, -1, -1, -1}, 0, 1));
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0x6F, (byte)0xFE, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 5, new byte[]{
                -1, 0, -1, -1, -1, -1, -1}, 3, 2));
        // assertArrayEquals(new
        // byte[]{(byte)0xFF,(byte)0x00,(byte)0xFF,(byte)0x5E,(byte)0xFF,(byte)0xFF,(byte)0xFF},Conversion.shortToByteArray((short)0xCDEF,13,new
        // byte[]{-1, 0,-1,-1,-1,-1,-1},3,2));//rejected by assertion
        Assert.assertArrayEquals(
            new byte[]{
                (byte)0xFF, (byte)0x00, (byte)0xFF, (byte)0xFE, (byte)0xFF, (byte)0xFF,
                (byte)0xFF}, Conversion.shortToByteArray((short)0xCDEF, 13, new byte[]{
                -1, 0, -1, -1, -1, -1, -1}, 3, 1));
    }

    /**
     * Tests {@link Conversion#longToHex(long, int, String, int, int)}.
     */
    @Test
    public void testLongToHex() {
        Assert.assertEquals("", Conversion.longToHex(0x0000000000000000L, 0, "", 0, 0));
        Assert.assertEquals("", Conversion.longToHex(0x0000000000000000L, 100, "", 0, 0));
        Assert.assertEquals("", Conversion.longToHex(0x0000000000000000L, 0, "", 100, 0));
        Assert.assertEquals(
            "ffffffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 0, 0));
        Assert.assertEquals(
            "3fffffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDE3L, 0, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "feffffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 0, 2));
        Assert.assertEquals(
            "fedcffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 0, 4));
        Assert.assertEquals(
            "fedcba098765432fffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 0, 15));
        Assert.assertEquals(
            "fedcba0987654321ffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 0, 16));
        Assert.assertEquals(
            "fff3ffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDE3L, 0, "ffffffffffffffffffffffff", 3, 1));
        Assert.assertEquals(
            "ffffefffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 3, 2));
        Assert.assertEquals(
            "ffffedcfffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 3, 4));
        Assert.assertEquals(
            "ffffedcba098765432ffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 3, 15));
        Assert.assertEquals(
            "ffffedcba0987654321fffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "ffffffffffffffffffffffff", 3, 16));
        Assert.assertEquals(
            "7fffffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 1, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "bfffffffffffffffffffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 2, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "fffdb975121fca86420fffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 3, "ffffffffffffffffffffffff", 3, 16));
        // assertEquals("ffffffffffffffffffffffff",Conversion.longToHex(0x1234567890ABCDEFL,4,"ffffffffffffffffffffffff",3,16));//rejected
        // by assertion
        Assert.assertEquals(
            "fffedcba0987654321ffffff",
            Conversion.longToHex(0x1234567890ABCDEFL, 4, "ffffffffffffffffffffffff", 3, 15));
        Assert.assertEquals(
            "fedcba0987654321", Conversion.longToHex(0x1234567890ABCDEFL, 0, "", 0, 16));
        try {
            Conversion.longToHex(0x1234567890ABCDEFL, 0, "", 1, 8);
            Assert.fail("Thrown " + StringIndexOutOfBoundsException.class.getName() + " expected");
        } catch (final StringIndexOutOfBoundsException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#intToHex(int, int, String, int, int)}.
     */
    @Test
    public void testIntToHex() {
        Assert.assertEquals("", Conversion.intToHex(0x00000000, 0, "", 0, 0));
        Assert.assertEquals("", Conversion.intToHex(0x00000000, 100, "", 0, 0));
        Assert.assertEquals("", Conversion.intToHex(0x00000000, 0, "", 100, 0));
        Assert.assertEquals(
            "ffffffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 0, 0));
        Assert.assertEquals(
            "3fffffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDE3, 0, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "feffffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 0, 2));
        Assert.assertEquals(
            "fedcffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 0, 4));
        Assert.assertEquals(
            "fedcba0fffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 0, 7));
        Assert.assertEquals(
            "fedcba09ffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 0, 8));
        Assert.assertEquals(
            "fff3ffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDE3, 0, "ffffffffffffffffffffffff", 3, 1));
        Assert.assertEquals(
            "ffffefffffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 3, 2));
        Assert.assertEquals(
            "ffffedcfffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 3, 4));
        Assert.assertEquals(
            "ffffedcba0ffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 3, 7));
        Assert.assertEquals(
            "ffffedcba09fffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 0, "ffffffffffffffffffffffff", 3, 8));
        Assert.assertEquals(
            "7fffffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 1, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "bfffffffffffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 2, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "fffdb97512ffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 3, "ffffffffffffffffffffffff", 3, 8));
        // assertEquals("ffffffffffffffffffffffff",Conversion.intToHex(0x90ABCDEF,
        // 4,"ffffffffffffffffffffffff",3,8));//rejected by assertion
        Assert.assertEquals(
            "fffedcba09ffffffffffffff",
            Conversion.intToHex(0x90ABCDEF, 4, "ffffffffffffffffffffffff", 3, 7));
        Assert.assertEquals("fedcba09", Conversion.intToHex(0x90ABCDEF, 0, "", 0, 8));
        try {
            Conversion.intToHex(0x90ABCDEF, 0, "", 1, 8);
            Assert.fail("Thrown " + StringIndexOutOfBoundsException.class.getName() + " expected");
        } catch (final StringIndexOutOfBoundsException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#shortToHex(short, int, String, int, int)}.
     */
    @Test
    public void testShortToHex() {
        Assert.assertEquals("", Conversion.shortToHex((short)0x0000, 0, "", 0, 0));
        Assert.assertEquals("", Conversion.shortToHex((short)0x0000, 100, "", 0, 0));
        Assert.assertEquals("", Conversion.shortToHex((short)0x0000, 0, "", 100, 0));
        Assert.assertEquals(
            "ffffffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 0, "ffffffffffffffffffffffff", 0, 0));
        Assert.assertEquals(
            "3fffffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDE3, 0, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "feffffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 0, "ffffffffffffffffffffffff", 0, 2));
        Assert.assertEquals(
            "fedfffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 0, "ffffffffffffffffffffffff", 0, 3));
        Assert.assertEquals(
            "fedcffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 0, "ffffffffffffffffffffffff", 0, 4));
        Assert.assertEquals(
            "fff3ffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDE3, 0, "ffffffffffffffffffffffff", 3, 1));
        Assert.assertEquals(
            "ffffefffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 0, "ffffffffffffffffffffffff", 3, 2));
        Assert.assertEquals(
            "7fffffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 1, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "bfffffffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 2, "ffffffffffffffffffffffff", 0, 1));
        Assert.assertEquals(
            "fffdb9ffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 3, "ffffffffffffffffffffffff", 3, 4));
        // assertEquals("ffffffffffffffffffffffff",Conversion.shortToHex((short)0xCDEF,
        // 4,"ffffffffffffffffffffffff",3,4));//rejected by assertion
        Assert.assertEquals(
            "fffedcffffffffffffffffff",
            Conversion.shortToHex((short)0xCDEF, 4, "ffffffffffffffffffffffff", 3, 3));
        Assert.assertEquals("fedc", Conversion.shortToHex((short)0xCDEF, 0, "", 0, 4));
        try {
            Conversion.shortToHex((short)0xCDEF, 0, "", 1, 4);
            Assert.fail("Thrown " + StringIndexOutOfBoundsException.class.getName() + " expected");
        } catch (final StringIndexOutOfBoundsException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#byteToHex(byte, int, String, int, int)}.
     */
    @Test
    public void testByteToHex() {
        Assert.assertEquals("", Conversion.byteToHex((byte)0x00, 0, "", 0, 0));
        Assert.assertEquals("", Conversion.byteToHex((byte)0x00, 100, "", 0, 0));
        Assert.assertEquals("", Conversion.byteToHex((byte)0x00, 0, "", 100, 0));
        Assert.assertEquals("00000", Conversion.byteToHex((byte)0xEF, 0, "00000", 0, 0));
        Assert.assertEquals("f0000", Conversion.byteToHex((byte)0xEF, 0, "00000", 0, 1));
        Assert.assertEquals("fe000", Conversion.byteToHex((byte)0xEF, 0, "00000", 0, 2));
        Assert.assertEquals("000f0", Conversion.byteToHex((byte)0xEF, 0, "00000", 3, 1));
        Assert.assertEquals("000fe", Conversion.byteToHex((byte)0xEF, 0, "00000", 3, 2));
        Assert.assertEquals("70000", Conversion.byteToHex((byte)0xEF, 1, "00000", 0, 1));
        Assert.assertEquals("b0000", Conversion.byteToHex((byte)0xEF, 2, "00000", 0, 1));
        Assert.assertEquals("000df", Conversion.byteToHex((byte)0xEF, 3, "00000", 3, 2));
        // assertEquals("00000",Conversion.byteToHex((byte)0xEF, 4,"00000",3,2));//rejected by
        // assertion
        Assert.assertEquals("000e0", Conversion.byteToHex((byte)0xEF, 4, "00000", 3, 1));
        Assert.assertEquals("fe", Conversion.byteToHex((byte)0xEF, 0, "", 0, 2));
        try {
            Conversion.byteToHex((byte)0xEF, 0, "", 1, 2);
            Assert.fail("Thrown " + StringIndexOutOfBoundsException.class.getName() + " expected");
        } catch (final StringIndexOutOfBoundsException e) {
            // OK
        }
    }

    /**
     * Tests {@link Conversion#longToBinary(long, int, boolean[], int, int)}.
     */
    @Test
    public void testLongToBinary() {
        assertBinaryEquals(
            new boolean[]{},
            Conversion.longToBinary(0x0000000000000000L, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{},
            Conversion.longToBinary(0x0000000000000000L, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{},
            Conversion.longToBinary(0x0000000000000000L, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69],
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 0));

        assertBinaryEquals(
            new boolean[]{
                true, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 1));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 2));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 3));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, true, true, false, true, false, true, false, true,
                false, false, false, false, true, false, false, true, false, false, false,
                true, true, true, true, false, false, true, true, false, true, false, true,
                false, false, false, true, false, true, true, false, false, false, true, false,
                false, true, false, false, false, false, false, false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 63));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, true, true, false, true, false, true, false, true,
                false, false, false, false, true, false, false, true, false, false, false,
                true, true, true, true, false, false, true, true, false, true, false, true,
                false, false, false, true, false, true, true, false, false, false, true, false,
                false, true, false, false, false, false, false, false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 0, 64));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false,
                false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 2, 1));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, true, true, true, false, true, true, true, true, false,
                true, true, false, false, true, true, true, true, false, true, false, true,
                false, true, false, false, false, false, true, false, false, true, false,
                false, false, true, true, true, true, false, false, true, true, false, true,
                false, true, false, false, false, true, false, true, true, false, false, false,
                true, false, false, true, false, false, false, false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 0, new boolean[69], 2, 64));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, true, true, true, true, false, true, true, false,
                false, true, true, true, true, false, true, false, true, false, true, false,
                false, false, false, true, false, false, true, false, false, false, true, true,
                true, true, false, false, true, true, false, true, false, true, false, false,
                false, true, false, true, true, false, false, false, true, false, false, true,
                false, false, false, false, false, false, false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 1, new boolean[69], 0, 63));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, true, true, true, true, false, true, true, false, false,
                true, true, true, true, false, true, false, true, false, true, false, false,
                false, false, true, false, false, true, false, false, false, true, true, true,
                true, false, false, true, true, false, true, false, true, false, false, false,
                true, false, true, true, false, false, false, true, false, false, true, false,
                false, false, false, false, false, false, false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 2, new boolean[69], 0, 62));

        // assertBinaryEquals(new boolean[]{false,false,false, true, true, false, true, true,
        // true, true, false, true, true, false, false, true, true, true, true, false, true,
        // false, true, false, true, false, false, false, false, true, false, false, true,
        // false, false, false, true, true, true, true, false, false, true, true, false, true,
        // false, true, false, false, false, true, false, true, true, false, false, false, true,
        // false, false, true, false, false, false
        // ,false,false,false,false},Conversion.longToBinary(0x1234567890ABCDEFL, 2,new
        // boolean[69], 3, 63));//rejected by assertion
        assertBinaryEquals(
            new boolean[]{
                false, false, false, true, true, false, true, true, true, true, false, true,
                true, false, false, true, true, true, true, false, true, false, true, false,
                true, false, false, false, false, true, false, false, true, false, false,
                false, true, true, true, true, false, false, true, true, false, true, false,
                true, false, false, false, true, false, true, true, false, false, false, true,
                false, false, true, false, false, false, false, false, false, false},
            Conversion.longToBinary(0x1234567890ABCDEFL, 2, new boolean[69], 3, 62));
    }

    /**
     * Tests {@link Conversion#intToBinary(int, int, boolean[], int, int)}.
     */
    @Test
    public void testIntToBinary() {
        assertBinaryEquals(
            new boolean[]{}, Conversion.intToBinary(0x00000000, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.intToBinary(0x00000000, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.intToBinary(0x00000000, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69], Conversion.intToBinary(0x90ABCDEF, 0, new boolean[69], 0, 0));
        assertBinaryEquals(new boolean[]{
            true, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 1));
        assertBinaryEquals(new boolean[]{
            true, true, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 2));
        assertBinaryEquals(new boolean[]{
            true, true, true, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 3));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, true, true, false, true, false, true, false, true,
                false, false, false, false, true, false, false, false, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 31));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, true, true, false, true, false, true, false, true,
                false, false, false, false, true, false, false, true, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 0, 32));
        assertBinaryEquals(new boolean[]{
            false, false, true, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 2, 1));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, true, true, true, false, true, true, true, true, false,
                true, true, false, false, true, true, true, true, false, true, false, true,
                false, true, false, false, false, false, true, false, false, true, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 0, new boolean[37], 2, 32));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, true, true, true, true, false, true, true, false,
                false, true, true, true, true, false, true, false, true, false, true, false,
                false, false, false, true, false, false, true, false, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 1, new boolean[37], 0, 31));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, true, true, true, true, false, true, true, false, false,
                true, true, true, true, false, true, false, true, false, true, false, false,
                false, false, true, false, false, true, false, false, false, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37], 0, 30));
        // assertBinaryEquals(new boolean[]{false, false, false, true, true, false, true,
        // true,
        // true, true, false, true, true, false, false, true, true, true, true, false, true,
        // false, true, false, true, false, false, false, false, true, false, false, false,
        // false, false, false, false},Conversion.intToBinary(0x90ABCDEF, 2,new boolean[37],
        // 3,31));//rejected by assertion
        assertBinaryEquals(
            new boolean[]{
                false, false, false, true, true, false, true, true, true, true, false, true,
                true, false, false, true, true, true, true, false, true, false, true, false,
                true, false, false, false, false, true, false, false, true, false, false,
                false, false}, Conversion.intToBinary(0x90ABCDEF, 2, new boolean[37], 3, 30));
    }

    /**
     * Tests {@link Conversion#shortToBinary(short, int, boolean[], int, int)}.
     */
    @Test
    public void testShortToBinary() {
        assertBinaryEquals(
            new boolean[]{}, Conversion.shortToBinary((short)0x0000, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{},
            Conversion.shortToBinary((short)0x0000, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{},
            Conversion.shortToBinary((short)0x0000, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69], Conversion.shortToBinary((short)0xCDEF, 0, new boolean[69], 0, 0));
        assertBinaryEquals(
            new boolean[]{
                true, false, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 1));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 2));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 3));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 15));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, true, false, true, true, true, true, false, true, true,
                false, false, true, true, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 0, 16));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, false, false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 2, 1));
        assertBinaryEquals(
            new boolean[]{
                false, false, true, true, true, true, false, true, true, true, true, false,
                true, true, false, false, true, true, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 0, new boolean[21], 2, 16));
        assertBinaryEquals(
            new boolean[]{
                true, true, true, false, true, true, true, true, false, true, true, false,
                false, true, true, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 1, new boolean[21], 0, 15));
        assertBinaryEquals(
            new boolean[]{
                true, true, false, true, true, true, true, false, true, true, false, false,
                true, true, false, false, false, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 2, new boolean[21], 0, 14));
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // true, true, false, true, true, false, false, true, false, false, false, false,
        // false},Conversion.shortToBinary((short)0xCDEF, 2,new boolean[21],
        // 3,15));//rejected by
        // assertion
        assertBinaryEquals(
            new boolean[]{
                false, false, false, true, true, false, true, true, true, true, false, true,
                true, false, false, true, true, false, false, false, false},
            Conversion.shortToBinary((short)0xCDEF, 2, new boolean[21], 3, 14));
    }

    /**
     * Tests {@link Conversion#byteToBinary(byte, int, boolean[], int, int)}.
     */
    @Test
    public void testByteToBinary() {
        assertBinaryEquals(
            new boolean[]{}, Conversion.byteToBinary((byte)0x00, 0, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.byteToBinary((byte)0x00, 100, new boolean[]{}, 0, 0));
        assertBinaryEquals(
            new boolean[]{}, Conversion.byteToBinary((byte)0x00, 0, new boolean[]{}, 100, 0));
        assertBinaryEquals(
            new boolean[69], Conversion.byteToBinary((byte)0xEF, 0, new boolean[69], 0, 0));
        assertBinaryEquals(new boolean[]{
            true, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 1));
        assertBinaryEquals(new boolean[]{
            true, false, false, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 2));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 3));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, true, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 7));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, true, false, false, true, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 0, 8));
        assertBinaryEquals(new boolean[]{
            false, false, true, false, false, false, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 2, 1));
        assertBinaryEquals(new boolean[]{
            false, false, true, false, true, false, true, false, false, true, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 0, new boolean[13], 2, 8));
        assertBinaryEquals(new boolean[]{
            false, true, false, true, false, false, true, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 1, new boolean[13], 0, 7));
        assertBinaryEquals(new boolean[]{
            true, false, true, false, false, true, false, false, false, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 2, new boolean[13], 0, 6));
        // assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true,
        // false, false, false, false, false},Conversion.byteToBinary((byte)0x95, 2,new
        // boolean[13], 3, 7));//rejected by assertion
        assertBinaryEquals(new boolean[]{
            false, false, false, true, false, true, false, false, true, false, false, false,
            false}, Conversion.byteToBinary((byte)0x95, 2, new boolean[13], 3, 6));
    }

    /**
     * Tests {@link Conversion#uuidToByteArray(UUID, byte[], int, int)}.
     */
    @Test
    public void testUuidToByteArray() {
        Assert.assertArrayEquals(new byte[]{
            (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
            (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
            (byte)0xff, (byte)0xff}, Conversion.uuidToByteArray(new UUID(
            0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL), new byte[16], 0, 16));
        Assert.assertArrayEquals(new byte[]{
            (byte)0x88, (byte)0x99, (byte)0xaa, (byte)0xbb, (byte)0xcc, (byte)0xdd, (byte)0xee,
            (byte)0xff, (byte)0x00, (byte)0x11, (byte)0x22, (byte)0x33, (byte)0x44, (byte)0x55,
            (byte)0x66, (byte)0x77}, Conversion.uuidToByteArray(new UUID(
            0xFFEEDDCCBBAA9988L, 0x7766554433221100L), new byte[16], 0, 16));
        Assert.assertArrayEquals(new byte[]{
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x88, (byte)0x99, (byte)0xaa,
            (byte)0xbb, (byte)0xcc, (byte)0xdd, (byte)0xee, (byte)0xff, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00}, Conversion.uuidToByteArray(new UUID(
            0xFFEEDDCCBBAA9988L, 0x7766554433221100L), new byte[16], 4, 8));
        Assert.assertArrayEquals(new byte[]{
            (byte)0x00, (byte)0x00, (byte)0x88, (byte)0x99, (byte)0xaa, (byte)0xbb, (byte)0xcc,
            (byte)0xdd, (byte)0xee, (byte)0xff, (byte)0x00, (byte)0x11, (byte)0x22, (byte)0x33,
            (byte)0x00, (byte)0x00}, Conversion.uuidToByteArray(new UUID(
            0xFFEEDDCCBBAA9988L, 0x7766554433221100L), new byte[16], 2, 12));
    }

    /**
     * Tests {@link Conversion#byteArrayToUuid(byte[], int)}.
     */
    @Test
    public void testByteArrayToUuid() {
        Assert.assertEquals(
            new UUID(0xFFFFFFFFFFFFFFFFL, 0xFFFFFFFFFFFFFFFFL),
            Conversion.byteArrayToUuid(new byte[]{
                (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,
                (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff}, 0));
        Assert.assertEquals(
            new UUID(0xFFEEDDCCBBAA9988L, 0x7766554433221100L),
            Conversion.byteArrayToUuid(new byte[]{
                (byte)0x88, (byte)0x99, (byte)0xaa, (byte)0xbb, (byte)0xcc, (byte)0xdd,
                (byte)0xee, (byte)0xff, (byte)0x00, (byte)0x11, (byte)0x22, (byte)0x33,
                (byte)0x44, (byte)0x55, (byte)0x66, (byte)0x77}, 0));
        Assert.assertEquals(
            new UUID(0xFFEEDDCCBBAA9988L, 0x7766554433221100L),
            Conversion.byteArrayToUuid(new byte[]{
                0, 0, (byte)0x88, (byte)0x99, (byte)0xaa, (byte)0xbb, (byte)0xcc, (byte)0xdd,
                (byte)0xee, (byte)0xff, (byte)0x00, (byte)0x11, (byte)0x22, (byte)0x33,
                (byte)0x44, (byte)0x55, (byte)0x66, (byte)0x77}, 2));
    }
}
