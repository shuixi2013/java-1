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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * Tests the methods in the {@link org.apache.commons.lang3.Range} class.
 * </p>
 */
@SuppressWarnings("boxing")
public class RangeTest {

    private Range<Byte> byteRange;
    private Range<Byte> byteRange2;
    private Range<Byte> byteRange3;

    private Range<Integer> intRange;
    private Range<Long> longRange;
    private Range<Float> floatRange;
    private Range<Double> doubleRange;

    @SuppressWarnings("cast") // intRange
    @Before
    public void setUp() {
        byteRange = Range.between((byte) 0, (byte) 5);
        byteRange2 = Range.between((byte) 0, (byte) 5);
        byteRange3 = Range.between((byte) 0, (byte) 10);

        intRange = Range.between((int) 10, (int) 20);
        longRange = Range.between((long) 10, (long) 20);
        floatRange = Range.between((float) 10, (float) 20);
        doubleRange = Range.between((double) 10, (double) 20);
    }

    // -----------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testComparableConstructors() {
        final Comparable c = new Comparable() {
            @Override
            public int compareTo(final Object other) {
                return 1;
            }
        };
        final Range r1 = Range.is(c);
        final Range r2 = Range.between(c, c);
        Assert.assertEquals(true, r1.isNaturalOrdering());
        Assert.assertEquals(true, r2.isNaturalOrdering());
    }

    @Test
    public void testIsWithCompare() {
        final Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(final Integer o1, final Integer o2) {
                return 0; // all integers are equal
            }
        };
        Range<Integer> ri = Range.is(10);
        Assert.assertFalse("should not contain null", ri.contains(null));
        Assert.assertTrue("should contain 10", ri.contains(10));
        Assert.assertFalse("should not contain 11", ri.contains(11));
        ri = Range.is(10, c);
        Assert.assertFalse("should not contain null", ri.contains(null));
        Assert.assertTrue("should contain 10", ri.contains(10));
        Assert.assertTrue("should contain 11", ri.contains(11));
    }

    @Test
    public void testBetweenWithCompare() {
        final Comparator<Integer> c = new Comparator<Integer>() {
            @Override
            public int compare(final Integer o1, final Integer o2) {
                return 0; // all integers are equal
            }
        };
        final Comparator<String> lengthComp = new Comparator<String>() {
            @Override
            public int compare(final String str1, final String str2) {
                return str1.length() - str2.length();
            }
        };
        Range<Integer> rb = Range.between(-10, 20);
        Assert.assertFalse("should not contain null", rb.contains(null));
        Assert.assertTrue("should contain 10", rb.contains(10));
        Assert.assertTrue("should contain -10", rb.contains(-10));
        Assert.assertFalse("should not contain 21", rb.contains(21));
        Assert.assertFalse("should not contain -11", rb.contains(-11));
        rb = Range.between(-10, 20, c);
        Assert.assertFalse("should not contain null", rb.contains(null));
        Assert.assertTrue("should contain 10", rb.contains(10));
        Assert.assertTrue("should contain -10", rb.contains(-10));
        Assert.assertTrue("should contain 21", rb.contains(21));
        Assert.assertTrue("should contain -11", rb.contains(-11));
        Range<String> rbstr = Range.between("house", "i");
        Assert.assertFalse("should not contain null", rbstr.contains(null));
        Assert.assertTrue("should contain house", rbstr.contains("house"));
        Assert.assertTrue("should contain i", rbstr.contains("i"));
        Assert.assertFalse("should not contain hose", rbstr.contains("hose"));
        Assert.assertFalse("should not contain ice", rbstr.contains("ice"));
        rbstr = Range.between("house", "i", lengthComp);
        Assert.assertFalse("should not contain null", rbstr.contains(null));
        Assert.assertTrue("should contain house", rbstr.contains("house"));
        Assert.assertTrue("should contain i", rbstr.contains("i"));
        Assert.assertFalse("should not contain houses", rbstr.contains("houses"));
        Assert.assertFalse("should not contain ''", rbstr.contains(""));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testRangeOfChars() {
        final Range<Character> chars = Range.between('a', 'z');
        Assert.assertTrue(chars.contains('b'));
        Assert.assertFalse(chars.contains('B'));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testEqualsObject() {
        Assert.assertEquals(byteRange, byteRange);
        Assert.assertEquals(byteRange, byteRange2);
        Assert.assertEquals(byteRange2, byteRange2);
        Assert.assertTrue(byteRange.equals(byteRange));
        Assert.assertTrue(byteRange2.equals(byteRange2));
        Assert.assertTrue(byteRange3.equals(byteRange3));
        Assert.assertFalse(byteRange2.equals(byteRange3));
        Assert.assertFalse(byteRange2.equals(null));
        Assert.assertFalse(byteRange2.equals("Ni!"));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(byteRange.hashCode(), byteRange2.hashCode());
        Assert.assertFalse(byteRange.hashCode() == byteRange3.hashCode());

        Assert.assertEquals(intRange.hashCode(), intRange.hashCode());
        Assert.assertTrue(intRange.hashCode() != 0);
    }

    @Test
    public void testToString() {
        Assert.assertNotNull(byteRange.toString());

        final String str = intRange.toString();
        Assert.assertEquals("[10..20]", str);
        Assert.assertEquals("[-20..-10]", Range.between(-20, -10).toString());
    }

    @Test
    public void testToStringFormat() {
        final String str = intRange.toString("From %1$s to %2$s");
        Assert.assertEquals("From 10 to 20", str);
    }

    // -----------------------------------------------------------------------
    @Test
    public void testGetMinimum() {
        Assert.assertEquals(10, (int) intRange.getMinimum());
        Assert.assertEquals(10L, (long) longRange.getMinimum());
        Assert.assertEquals(10f, floatRange.getMinimum(), 0.00001f);
        Assert.assertEquals(10d, doubleRange.getMinimum(), 0.00001d);
    }

    @Test
    public void testGetMaximum() {
        Assert.assertEquals(20, (int) intRange.getMaximum());
        Assert.assertEquals(20L, (long) longRange.getMaximum());
        Assert.assertEquals(20f, floatRange.getMaximum(), 0.00001f);
        Assert.assertEquals(20d, doubleRange.getMaximum(), 0.00001d);
    }

    @Test
    public void testContains() {
        Assert.assertFalse(intRange.contains(null));

        Assert.assertFalse(intRange.contains(5));
        Assert.assertTrue(intRange.contains(10));
        Assert.assertTrue(intRange.contains(15));
        Assert.assertTrue(intRange.contains(20));
        Assert.assertFalse(intRange.contains(25));
    }

    @Test
    public void testIsAfter() {
        Assert.assertFalse(intRange.isAfter(null));

        Assert.assertTrue(intRange.isAfter(5));
        Assert.assertFalse(intRange.isAfter(10));
        Assert.assertFalse(intRange.isAfter(15));
        Assert.assertFalse(intRange.isAfter(20));
        Assert.assertFalse(intRange.isAfter(25));
    }

    @Test
    public void testIsStartedBy() {
        Assert.assertFalse(intRange.isStartedBy(null));

        Assert.assertFalse(intRange.isStartedBy(5));
        Assert.assertTrue(intRange.isStartedBy(10));
        Assert.assertFalse(intRange.isStartedBy(15));
        Assert.assertFalse(intRange.isStartedBy(20));
        Assert.assertFalse(intRange.isStartedBy(25));
    }

    @Test
    public void testIsEndedBy() {
        Assert.assertFalse(intRange.isEndedBy(null));

        Assert.assertFalse(intRange.isEndedBy(5));
        Assert.assertFalse(intRange.isEndedBy(10));
        Assert.assertFalse(intRange.isEndedBy(15));
        Assert.assertTrue(intRange.isEndedBy(20));
        Assert.assertFalse(intRange.isEndedBy(25));
    }

    @Test
    public void testIsBefore() {
        Assert.assertFalse(intRange.isBefore(null));

        Assert.assertFalse(intRange.isBefore(5));
        Assert.assertFalse(intRange.isBefore(10));
        Assert.assertFalse(intRange.isBefore(15));
        Assert.assertFalse(intRange.isBefore(20));
        Assert.assertTrue(intRange.isBefore(25));
    }

    @Test
    public void testElementCompareTo() {
        try {
            intRange.elementCompareTo(null);
            Assert.fail("NullPointerException should have been thrown");
        } catch (final NullPointerException npe) {
            // expected
        }

        Assert.assertEquals(-1, intRange.elementCompareTo(5));
        Assert.assertEquals(0, intRange.elementCompareTo(10));
        Assert.assertEquals(0, intRange.elementCompareTo(15));
        Assert.assertEquals(0, intRange.elementCompareTo(20));
        Assert.assertEquals(1, intRange.elementCompareTo(25));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testContainsRange() {

        // null handling
        Assert.assertFalse(intRange.containsRange(null));

        // easy inside range
        Assert.assertTrue(intRange.containsRange(Range.between(12, 18)));

        // outside range on each side
        Assert.assertFalse(intRange.containsRange(Range.between(32, 45)));
        Assert.assertFalse(intRange.containsRange(Range.between(2, 8)));

        // equals range
        Assert.assertTrue(intRange.containsRange(Range.between(10, 20)));

        // overlaps
        Assert.assertFalse(intRange.containsRange(Range.between(9, 14)));
        Assert.assertFalse(intRange.containsRange(Range.between(16, 21)));

        // touches lower boundary
        Assert.assertTrue(intRange.containsRange(Range.between(10, 19)));
        Assert.assertFalse(intRange.containsRange(Range.between(10, 21)));

        // touches upper boundary
        Assert.assertTrue(intRange.containsRange(Range.between(11, 20)));
        Assert.assertFalse(intRange.containsRange(Range.between(9, 20)));

        // negative
        Assert.assertFalse(intRange.containsRange(Range.between(-11, -18)));
    }

    @Test
    public void testIsAfterRange() {
        Assert.assertFalse(intRange.isAfterRange(null));

        Assert.assertTrue(intRange.isAfterRange(Range.between(5, 9)));

        Assert.assertFalse(intRange.isAfterRange(Range.between(5, 10)));
        Assert.assertFalse(intRange.isAfterRange(Range.between(5, 20)));
        Assert.assertFalse(intRange.isAfterRange(Range.between(5, 25)));
        Assert.assertFalse(intRange.isAfterRange(Range.between(15, 25)));

        Assert.assertFalse(intRange.isAfterRange(Range.between(21, 25)));

        Assert.assertFalse(intRange.isAfterRange(Range.between(10, 20)));
    }

    @Test
    public void testIsOverlappedBy() {

        // null handling
        Assert.assertFalse(intRange.isOverlappedBy(null));

        // easy inside range
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(12, 18)));

        // outside range on each side
        Assert.assertFalse(intRange.isOverlappedBy(Range.between(32, 45)));
        Assert.assertFalse(intRange.isOverlappedBy(Range.between(2, 8)));

        // equals range
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(10, 20)));

        // overlaps
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(9, 14)));
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(16, 21)));

        // touches lower boundary
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(10, 19)));
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(10, 21)));

        // touches upper boundary
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(11, 20)));
        Assert.assertTrue(intRange.isOverlappedBy(Range.between(9, 20)));

        // negative
        Assert.assertFalse(intRange.isOverlappedBy(Range.between(-11, -18)));
    }

    @Test
    public void testIsBeforeRange() {
        Assert.assertFalse(intRange.isBeforeRange(null));

        Assert.assertFalse(intRange.isBeforeRange(Range.between(5, 9)));

        Assert.assertFalse(intRange.isBeforeRange(Range.between(5, 10)));
        Assert.assertFalse(intRange.isBeforeRange(Range.between(5, 20)));
        Assert.assertFalse(intRange.isBeforeRange(Range.between(5, 25)));
        Assert.assertFalse(intRange.isBeforeRange(Range.between(15, 25)));

        Assert.assertTrue(intRange.isBeforeRange(Range.between(21, 25)));

        Assert.assertFalse(intRange.isBeforeRange(Range.between(10, 20)));
    }

    @Test
    public void testIntersectionWith() {
        Assert.assertSame(intRange, intRange.intersectionWith(intRange));
        Assert.assertSame(byteRange, byteRange.intersectionWith(byteRange));
        Assert.assertSame(longRange, longRange.intersectionWith(longRange));
        Assert.assertSame(floatRange, floatRange.intersectionWith(floatRange));
        Assert.assertSame(doubleRange, doubleRange.intersectionWith(doubleRange));

        Assert.assertEquals(Range.between(10, 15), intRange.intersectionWith(Range.between(5, 15)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersectionWithNull() {
        intRange.intersectionWith(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIntersectionWithNonOverlapping() {
        intRange.intersectionWith(Range.between(0, 9));
    }

    // -----------------------------------------------------------------------
    @Test
    public void testSerializing() {
        SerializationUtils.clone(intRange);
    }

}
