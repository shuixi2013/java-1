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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.ArrayUtils}.
 */
@SuppressWarnings("deprecation") // deliberate use of deprecated code
public class ArrayUtilsTest  {

    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new ArrayUtils());
        final Constructor<?>[] cons = ArrayUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(ArrayUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(ArrayUtils.class.getModifiers()));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testToString() {
        Assert.assertEquals("{}", ArrayUtils.toString(null));
        Assert.assertEquals("{}", ArrayUtils.toString(new Object[0]));
        Assert.assertEquals("{}", ArrayUtils.toString(new String[0]));
        Assert.assertEquals("{<null>}", ArrayUtils.toString(new String[] {null}));
        Assert.assertEquals("{pink,blue}", ArrayUtils.toString(new String[] {"pink","blue"}));
        
        Assert.assertEquals("<empty>", ArrayUtils.toString(null, "<empty>"));
        Assert.assertEquals("{}", ArrayUtils.toString(new Object[0], "<empty>"));
        Assert.assertEquals("{}", ArrayUtils.toString(new String[0], "<empty>"));
        Assert.assertEquals("{<null>}", ArrayUtils.toString(new String[] {null}, "<empty>"));
        Assert.assertEquals("{pink,blue}", ArrayUtils.toString(new String[] {"pink","blue"}, "<empty>"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testHashCode() {
        final long[][] array1 = new long[][] {{2,5}, {4,5}};
        final long[][] array2 = new long[][] {{2,5}, {4,6}};
        Assert.assertTrue(ArrayUtils.hashCode(array1) == ArrayUtils.hashCode(array1));
        Assert.assertFalse(ArrayUtils.hashCode(array1) == ArrayUtils.hashCode(array2));
        
        final Object[] array3 = new Object[] {new String(new char[] {'A', 'B'})};
        final Object[] array4 = new Object[] {"AB"};
        Assert.assertTrue(ArrayUtils.hashCode(array3) == ArrayUtils.hashCode(array3));
        Assert.assertTrue(ArrayUtils.hashCode(array3) == ArrayUtils.hashCode(array4));
        
        final Object[] arrayA = new Object[] {new boolean[] {true, false}, new int[] {6, 7}};
        final Object[] arrayB = new Object[] {new boolean[] {true, false}, new int[] {6, 7}};
        Assert.assertTrue(ArrayUtils.hashCode(arrayB) == ArrayUtils.hashCode(arrayA));
    }

    //-----------------------------------------------------------------------
    private void assertIsEquals(final Object array1, final Object array2, final Object array3) {
        Assert.assertTrue(ArrayUtils.isEquals(array1, array1));
        Assert.assertTrue(ArrayUtils.isEquals(array2, array2));
        Assert.assertTrue(ArrayUtils.isEquals(array3, array3));
        Assert.assertFalse(ArrayUtils.isEquals(array1, array2));
        Assert.assertFalse(ArrayUtils.isEquals(array2, array1));
        Assert.assertFalse(ArrayUtils.isEquals(array1, array3));
        Assert.assertFalse(ArrayUtils.isEquals(array3, array1));
        Assert.assertFalse(ArrayUtils.isEquals(array1, array2));
        Assert.assertFalse(ArrayUtils.isEquals(array2, array1));
    }

    @Test
    public void testIsEquals() {
        final long[][] larray1 = new long[][]{{2, 5}, {4, 5}};
        final long[][] larray2 = new long[][]{{2, 5}, {4, 6}};
        final long[] larray3 = new long[]{2, 5};
        this.assertIsEquals(larray1, larray2, larray3);

        final int[][] iarray1 = new int[][]{{2, 5}, {4, 5}};
        final int[][] iarray2 = new int[][]{{2, 5}, {4, 6}};
        final int[] iarray3 = new int[]{2, 5};
        this.assertIsEquals(iarray1, iarray2, iarray3);

        final short[][] sarray1 = new short[][]{{2, 5}, {4, 5}};
        final short[][] sarray2 = new short[][]{{2, 5}, {4, 6}};
        final short[] sarray3 = new short[]{2, 5};
        this.assertIsEquals(sarray1, sarray2, sarray3);

        final float[][] farray1 = new float[][]{{2, 5}, {4, 5}};
        final float[][] farray2 = new float[][]{{2, 5}, {4, 6}};
        final float[] farray3 = new float[]{2, 5};
        this.assertIsEquals(farray1, farray2, farray3);

        final double[][] darray1 = new double[][]{{2, 5}, {4, 5}};
        final double[][] darray2 = new double[][]{{2, 5}, {4, 6}};
        final double[] darray3 = new double[]{2, 5};
        this.assertIsEquals(darray1, darray2, darray3);

        final byte[][] byteArray1 = new byte[][]{{2, 5}, {4, 5}};
        final byte[][] byteArray2 = new byte[][]{{2, 5}, {4, 6}};
        final byte[] byteArray3 = new byte[]{2, 5};
        this.assertIsEquals(byteArray1, byteArray2, byteArray3);

        final char[][] charArray1 = new char[][]{{2, 5}, {4, 5}};
        final char[][] charArray2 = new char[][]{{2, 5}, {4, 6}};
        final char[] charArray3 = new char[]{2, 5};
        this.assertIsEquals(charArray1, charArray2, charArray3);

        final boolean[][] barray1 = new boolean[][]{{true, false}, {true, true}};
        final boolean[][] barray2 = new boolean[][]{{true, false}, {true, false}};
        final boolean[] barray3 = new boolean[]{false, true};
        this.assertIsEquals(barray1, barray2, barray3);

        final Object[] array3 = new Object[]{new String(new char[]{'A', 'B'})};
        final Object[] array4 = new Object[]{"AB"};
        Assert.assertTrue(ArrayUtils.isEquals(array3, array3));
        Assert.assertTrue(ArrayUtils.isEquals(array3, array4));

        Assert.assertTrue(ArrayUtils.isEquals(null, null));
        Assert.assertFalse(ArrayUtils.isEquals(null, array4));
    }
    
    //-----------------------------------------------------------------------
    /**
     * Tests generic array creation with parameters of same type.
     */
    @Test
    public void testArrayCreation()
    {
        final String[] array = ArrayUtils.toArray("foo", "bar");
        Assert.assertEquals(2, array.length);
        Assert.assertEquals("foo", array[0]);
        Assert.assertEquals("bar", array[1]);
    }

    /**
     * Tests generic array creation with general return type.
     */
    @Test
    public void testArrayCreationWithGeneralReturnType()
    {
        final Object obj = ArrayUtils.toArray("foo", "bar");
        Assert.assertTrue(obj instanceof String[]);
    }

    /**
     * Tests generic array creation with parameters of common base type.
     */
    @Test
    public void testArrayCreationWithDifferentTypes()
    {
        final Number[] array = ArrayUtils.<Number>toArray(Integer.valueOf(42), Double.valueOf(Math.PI));
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(Integer.valueOf(42), array[0]);
        Assert.assertEquals(Double.valueOf(Math.PI), array[1]);
    }

    /**
     * Tests generic array creation with generic type.
     */
    @Test
    public void testIndirectArrayCreation()
    {
        final String[] array = toArrayPropagatingType("foo", "bar");
        Assert.assertEquals(2, array.length);
        Assert.assertEquals("foo", array[0]);
        Assert.assertEquals("bar", array[1]);
    }

    /**
     * Tests generic empty array creation with generic type.
     */
    @Test
    public void testEmptyArrayCreation()
    {
        final String[] array = ArrayUtils.<String>toArray();
        Assert.assertEquals(0, array.length);
    }

    /**
     * Tests indirect generic empty array creation with generic type.
     */
    @Test
    public void testIndirectEmptyArrayCreation()
    {
        final String[] array = ArrayUtilsTest.<String>toArrayPropagatingType();
        Assert.assertEquals(0, array.length);
    }

    private static <T> T[] toArrayPropagatingType(final T... items)
    {
        return ArrayUtils.toArray(items);
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testToMap() {
        Map<?, ?> map = ArrayUtils.toMap(new String[][] {{"foo", "bar"}, {"hello", "world"}});
        
        Assert.assertEquals("bar", map.get("foo"));
        Assert.assertEquals("world", map.get("hello"));
        
        Assert.assertEquals(null, ArrayUtils.toMap(null));
        try {
            ArrayUtils.toMap(new String[][] {{"foo", "bar"}, {"short"}});
            Assert.fail("exception expected");
        } catch (final IllegalArgumentException ex) {}
        try {
            ArrayUtils.toMap(new Object[] {new Object[] {"foo", "bar"}, "illegal type"});
            Assert.fail("exception expected");
        } catch (final IllegalArgumentException ex) {}
        try {
            ArrayUtils.toMap(new Object[] {new Object[] {"foo", "bar"}, null});
            Assert.fail("exception expected");
        } catch (final IllegalArgumentException ex) {}
        
        map = ArrayUtils.toMap(new Object[] {new Map.Entry<Object, Object>() {
            @Override
            public Object getKey() {
                return "foo";
            }
            @Override
            public Object getValue() {
                return "bar";
            }
            @Override
            public Object setValue(final Object value) {
                throw new UnsupportedOperationException();
            }
            @Override
            public boolean equals(final Object o) {
                throw new UnsupportedOperationException();
            }
            @Override
            public int hashCode() {
                throw new UnsupportedOperationException();
            }
        }});
        Assert.assertEquals("bar", map.get("foo"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testClone() {
        Assert.assertArrayEquals(null, ArrayUtils.clone((Object[]) null));
        Object[] original1 = new Object[0];
        Object[] cloned1 = ArrayUtils.clone(original1);
        Assert.assertTrue(Arrays.equals(original1, cloned1));
        Assert.assertTrue(original1 != cloned1);
        
        final StringBuffer buf = new StringBuffer("pick");
        original1 = new Object[] {buf, "a", new String[] {"stick"}};
        cloned1 = ArrayUtils.clone(original1);
        Assert.assertTrue(Arrays.equals(original1, cloned1));
        Assert.assertTrue(original1 != cloned1);
        Assert.assertSame(original1[0], cloned1[0]);
        Assert.assertSame(original1[1], cloned1[1]);
        Assert.assertSame(original1[2], cloned1[2]);
    }

    @Test
    public void testCloneBoolean() {
        Assert.assertEquals(null, ArrayUtils.clone((boolean[]) null));
        final boolean[] original = new boolean[] {true, false};
        final boolean[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneLong() {
        Assert.assertEquals(null, ArrayUtils.clone((long[]) null));
        final long[] original = new long[] {0L, 1L};
        final long[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneInt() {
        Assert.assertEquals(null, ArrayUtils.clone((int[]) null));
        final int[] original = new int[] {5, 8};
        final int[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneShort() {
        Assert.assertEquals(null, ArrayUtils.clone((short[]) null));
        final short[] original = new short[] {1, 4};
        final short[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneChar() {
        Assert.assertEquals(null, ArrayUtils.clone((char[]) null));
        final char[] original = new char[] {'a', '4'};
        final char[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneByte() {
        Assert.assertEquals(null, ArrayUtils.clone((byte[]) null));
        final byte[] original = new byte[] {1, 6};
        final byte[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneDouble() {
        Assert.assertEquals(null, ArrayUtils.clone((double[]) null));
        final double[] original = new double[] {2.4d, 5.7d};
        final double[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }
    
    @Test
    public void testCloneFloat() {
        Assert.assertEquals(null, ArrayUtils.clone((float[]) null));
        final float[] original = new float[] {2.6f, 6.4f};
        final float[] cloned = ArrayUtils.clone(original);
        Assert.assertTrue(Arrays.equals(original, cloned));
        Assert.assertTrue(original != cloned);
    }

    //-----------------------------------------------------------------------

    private class TestClass{}
    
    @Test
    public void testNullToEmptyGenericNull() {
        TestClass[] output = ArrayUtils.nullToEmpty(null, TestClass[].class);
    
        Assert.assertTrue(output != null);
        Assert.assertTrue(output.length == 0);
    }
    
    @Test
    public void testNullToEmptyGenericEmpty() {
        TestClass[] input = new TestClass[]{};
        TestClass[] output = ArrayUtils.nullToEmpty(input, TestClass[].class);
    
        Assert.assertSame(input, output);
    }
    
    @Test
    public void testNullToEmptyGeneric() {
        TestClass[] input = new TestClass[]{new TestClass(), new TestClass()};
        TestClass[] output = ArrayUtils.nullToEmpty(input, TestClass[].class);
    
        Assert.assertSame(input, output);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNullToEmptyGenericNullType() {
        TestClass[] input = new TestClass[]{};
        ArrayUtils.nullToEmpty(input, null);
    }    

    @Test
    public void testNullToEmptyBooleanNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.nullToEmpty((boolean[]) null));
    }

    @Test
    public void testNullToEmptyBooleanEmptyArray() throws Exception {
        final boolean[] empty = new boolean[]{};
        final boolean[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyBoolean() {
        final boolean[] original = new boolean[] {true, false};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyLongNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.nullToEmpty((long[]) null));
    }

    @Test
    public void testNullToEmptyLongEmptyArray() throws Exception {
        final long[] empty = new long[]{};
        final long[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_LONG_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyLong() {
        final long[] original = new long[] {1L, 2L};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyIntNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.nullToEmpty((int[]) null));
    }

    @Test
    public void testNullToEmptyIntEmptyArray() throws Exception {
        final int[] empty = new int[]{};
        final int[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_INT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyInt() {
        final int[] original = new int[] {1, 2};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyShortNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.nullToEmpty((short[]) null));
    }

    @Test
    public void testNullToEmptyShortEmptyArray() throws Exception {
        final short[] empty = new short[]{};
        final short[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_SHORT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyShort() {
        final short[] original = new short[] {1, 2};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyCharNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.nullToEmpty((char[]) null));
    }

    @Test
    public void testNullToEmptyCharEmptyArray() throws Exception {
        final char[] empty = new char[]{};
        final char[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_CHAR_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyChar() {
        final char[] original = new char[] {'a', 'b'};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyByteNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.nullToEmpty((byte[]) null));
    }

    @Test
    public void testNullToEmptyByteEmptyArray() throws Exception {
        final byte[] empty = new byte[]{};
        final byte[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_BYTE_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyByte() {
        final byte[] original = new byte[] {0x0F, 0x0E};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyDoubleNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.nullToEmpty((double[]) null));
    }

    @Test
    public void testNullToEmptyDoubleEmptyArray() throws Exception {
        final double[] empty = new double[]{};
        final double[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_DOUBLE_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyDouble() {
        final double[] original = new double[] {1L, 2L};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyFloatNull() throws Exception {
        Assert.assertEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.nullToEmpty((float[]) null));
    }

    @Test
    public void testNullToEmptyFloatEmptyArray() throws Exception {
        final float[] empty = new float[]{};
        final float[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertEquals(ArrayUtils.EMPTY_FLOAT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyFloat() {
        final float[] original = new float[] {2.6f, 3.8f};
        Assert.assertEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Object[]) null));
    }

    @Test
    public void testNullToEmptyObjectEmptyArray() throws Exception {
        final Object[] empty = new Object[]{};
        final Object[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyObject() {
        final Object[] original = new Object[] {Boolean.TRUE, Boolean.FALSE};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyClassNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_CLASS_ARRAY, ArrayUtils.nullToEmpty((Class<?>[]) null));
    }

    @Test
    public void testNullToEmptyClassEmptyArray() throws Exception {
        final Class<?>[] empty = {};
        final Class<?>[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_CLASS_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyClass() {
        final Class<?>[] original = { Object.class, String.class };
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyStringNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.nullToEmpty((String[]) null));
    }

    @Test
    public void testNullToEmptyStringEmptyArray() throws Exception {
        final String[] empty = new String[]{};
        final String[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_STRING_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyString() {
        final String[] original = new String[] {"abc", "def"};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyBooleanObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Boolean[]) null));
    }

    @Test
    public void testNullToEmptyBooleanObjectEmptyArray() throws Exception {
        final Boolean[] empty = new Boolean[]{};
        final Boolean[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyBooleanObject() {
        final Boolean[] original = new Boolean[] {Boolean.TRUE, Boolean.FALSE};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyLongObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Long[]) null));
    }

    @Test
    public void testNullToEmptyLongObjectEmptyArray() throws Exception {
        final Long[] empty = new Long[]{};
        final Long[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_LONG_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyLongObject() {
        @SuppressWarnings("boxing")
        final Long[] original = new Long[] {1L, 2L};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyIntObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Integer[]) null));
    }

    @Test
    public void testNullToEmptyIntObjectEmptyArray() throws Exception {
        final Integer[] empty = new Integer[]{};
        final Integer[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyIntObject() {
        final Integer[] original = new Integer[] {1, 2};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyShortObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Short[]) null));
    }

    @Test
    public void testNullToEmptyShortObjectEmptyArray() throws Exception {
        final Short[] empty = new Short[]{};
        final Short[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyShortObject() {
        @SuppressWarnings("boxing")
        final Short[] original = new Short[] {1, 2};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNUllToEmptyCharObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Character[]) null));
    }

    @Test
    public void testNullToEmptyCharObjectEmptyArray() throws Exception {
        final Character[] empty = new Character[]{};
        final Character[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyCharObject() {
        final Character[] original = new Character[] {'a', 'b'};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyByteObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Byte[]) null));
    }

    @Test
    public void testNullToEmptyByteObjectEmptyArray() throws Exception {
        final Byte[] empty = new Byte[]{};
        final Byte[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyByteObject() {
        final Byte[] original = new Byte[] {0x0F, 0x0E};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyDoubleObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Double[]) null));
    }

    @Test
    public void testNullToEmptyDoubleObjectEmptyArray() throws Exception {
        final Double[] empty = new Double[]{};
        final Double[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyDoubleObject() {
        final Double[] original = new Double[] {1D, 2D};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    @Test
    public void testNullToEmptyFloatObjectNull() throws Exception {
        Assert.assertArrayEquals(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY, ArrayUtils.nullToEmpty((Float[]) null));
    }

    @Test
    public void testNullToEmptyFloatObjectEmptyArray() throws Exception {
        final Float[] empty = new Float[]{};
        final Float[] result = ArrayUtils.nullToEmpty(empty);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY, result);
        Assert.assertNotSame(empty, result);
    }

    @Test
    public void testNullToEmptyFloatObject() {
        final Float[] original = new Float[] {2.6f, 3.8f};
        Assert.assertArrayEquals(original, ArrayUtils.nullToEmpty(original));
    }

    //-----------------------------------------------------------------------

    @Test
    public void testSubarrayObject() {
        final Object[] nullArray = null;
        final Object[] objectArray = { "a", "b", "c", "d", "e", "f"};

        Assert.assertEquals("0 start, mid end", "abcd",
            StringUtils.join(ArrayUtils.subarray(objectArray, 0, 4)));
        Assert.assertEquals("0 start, length end", "abcdef",
            StringUtils.join(ArrayUtils.subarray(objectArray, 0, objectArray.length)));
        Assert.assertEquals("mid start, mid end", "bcd",
            StringUtils.join(ArrayUtils.subarray(objectArray, 1, 4)));
        Assert.assertEquals("mid start, length end", "bcdef",
            StringUtils.join(ArrayUtils.subarray(objectArray, 1, objectArray.length)));

        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));
        Assert.assertEquals("empty array", "",
            StringUtils.join(ArrayUtils.subarray(ArrayUtils.EMPTY_OBJECT_ARRAY, 1, 2)));
        Assert.assertEquals("start > end", "",
            StringUtils.join(ArrayUtils.subarray(objectArray, 4, 2)));
        Assert.assertEquals("start == end", "",
            StringUtils.join(ArrayUtils.subarray(objectArray, 3, 3)));
        Assert.assertEquals("start undershoot, normal end", "abcd",
            StringUtils.join(ArrayUtils.subarray(objectArray, -2, 4)));
        Assert.assertEquals("start overshoot, any end", "",
            StringUtils.join(ArrayUtils.subarray(objectArray, 33, 4)));
        Assert.assertEquals("normal start, end overshoot", "cdef",
            StringUtils.join(ArrayUtils.subarray(objectArray, 2, 33)));
        Assert.assertEquals("start undershoot, end overshoot", "abcdef",
            StringUtils.join(ArrayUtils.subarray(objectArray, -2, 12)));
            
        // array type tests
        final Date[] dateArray = { new java.sql.Date(new Date().getTime()),
            new Date(), new Date(), new Date(), new Date() };

        Assert.assertSame("Object type", Object.class,
            ArrayUtils.subarray(objectArray, 2, 4).getClass().getComponentType());
        Assert.assertSame("java.util.Date type", java.util.Date.class,
            ArrayUtils.subarray(dateArray, 1, 4).getClass().getComponentType());
        Assert.assertNotSame("java.sql.Date type", java.sql.Date.class,
            ArrayUtils.subarray(dateArray, 1, 4).getClass().getComponentType());
        try {
            @SuppressWarnings("unused")
            final
            java.sql.Date[] dummy = (java.sql.Date[])ArrayUtils.subarray(dateArray, 1,3);
            Assert.fail("Invalid downcast");
        } catch (final ClassCastException e) {}
    }

    @Test
    public void testSubarrayLong() {
        final long[] nullArray = null;
        final long[] array = { 999910, 999911, 999912, 999913, 999914, 999915 };
        final long[] leftSubarray     = { 999910, 999911, 999912, 999913 };
        final long[] midSubarray      = { 999911, 999912, 999913, 999914 };
        final long[] rightSubarray    = { 999912, 999913, 999914, 999915 };

        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_LONG_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_LONG_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_LONG_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_LONG_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_LONG_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_LONG_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_LONG_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_LONG_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_LONG_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_LONG_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("long type", long.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrayInt() {
        final int[] nullArray = null;
        final int[] array = { 10, 11, 12, 13, 14, 15 };
        final int[] leftSubarray  = { 10, 11, 12, 13 };
        final int[] midSubarray   = { 11, 12, 13, 14 };
        final int[] rightSubarray = { 12, 13, 14, 15 };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_INT_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_INT_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_INT_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_INT_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_INT_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_INT_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_INT_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_INT_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_INT_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_INT_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("int type", int.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrayShort() {
        final short[] nullArray = null;
        final short[] array = { 10, 11, 12, 13, 14, 15 };
        final short[] leftSubarray    = { 10, 11, 12, 13 };
        final short[] midSubarray     = { 11, 12, 13, 14 };
        final short[] rightSubarray   = { 12, 13, 14, 15 };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_SHORT_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_SHORT_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_SHORT_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_SHORT_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_SHORT_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_SHORT_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_SHORT_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_SHORT_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_SHORT_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_SHORT_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("short type", short.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrChar() {
        final char[] nullArray = null;
        final char[] array = { 'a', 'b', 'c', 'd', 'e', 'f' };
        final char[] leftSubarray     = { 'a', 'b', 'c', 'd', };
        final char[] midSubarray      = { 'b', 'c', 'd', 'e', };
        final char[] rightSubarray    = { 'c', 'd', 'e', 'f', };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_CHAR_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_CHAR_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_CHAR_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_CHAR_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_CHAR_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_CHAR_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_CHAR_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_CHAR_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_CHAR_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_CHAR_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("char type", char.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrayByte() {
        final byte[] nullArray = null;
        final byte[] array = { 10, 11, 12, 13, 14, 15 };
        final byte[] leftSubarray     = { 10, 11, 12, 13 };
        final byte[] midSubarray      = { 11, 12, 13, 14 };
        final byte[] rightSubarray = { 12, 13, 14, 15 };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_BYTE_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_BYTE_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_BYTE_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_BYTE_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_BYTE_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_BYTE_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_BYTE_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_BYTE_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_BYTE_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_BYTE_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("byte type", byte.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrayDouble() {
        final double[] nullArray = null;
        final double[] array = { 10.123, 11.234, 12.345, 13.456, 14.567, 15.678 };
        final double[] leftSubarray   = { 10.123, 11.234, 12.345, 13.456, };
        final double[] midSubarray    = { 11.234, 12.345, 13.456, 14.567, };
        final double[] rightSubarray  = { 12.345, 13.456, 14.567, 15.678 };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_DOUBLE_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_DOUBLE_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_DOUBLE_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_DOUBLE_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_DOUBLE_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_DOUBLE_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_DOUBLE_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_DOUBLE_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_DOUBLE_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_DOUBLE_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("double type", double.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrayFloat() {
        final float[] nullArray = null;
        final float[] array = { 10, 11, 12, 13, 14, 15 };
        final float[] leftSubarray    = { 10, 11, 12, 13 };
        final float[] midSubarray     = { 11, 12, 13, 14 };
        final float[] rightSubarray   = { 12, 13, 14, 15 };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_FLOAT_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_FLOAT_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_FLOAT_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_FLOAT_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_FLOAT_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_FLOAT_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_FLOAT_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_FLOAT_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_FLOAT_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_FLOAT_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("float type", float.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }

    @Test
    public void testSubarrayBoolean() {
        final boolean[] nullArray = null;
        final boolean[] array = { true, true, false, true, false, true };
        final boolean[] leftSubarray  = { true, true, false, true  };
        final boolean[] midSubarray   = { true, false, true, false };
        final boolean[] rightSubarray = { false, true, false, true };


        Assert.assertTrue("0 start, mid end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, 0, 4)));

        Assert.assertTrue("0 start, length end",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, 0, array.length)));

        Assert.assertTrue("mid start, mid end",
            ArrayUtils.isEquals(midSubarray,
                ArrayUtils.subarray(array, 1, 5)));

        Assert.assertTrue("mid start, length end",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, array.length)));


        Assert.assertNull("null input", ArrayUtils.subarray(nullArray, 0, 3));

        Assert.assertEquals("empty array", ArrayUtils.EMPTY_BOOLEAN_ARRAY,
            ArrayUtils.subarray(ArrayUtils.EMPTY_BOOLEAN_ARRAY, 1, 2));

        Assert.assertEquals("start > end", ArrayUtils.EMPTY_BOOLEAN_ARRAY,
            ArrayUtils.subarray(array, 4, 2));

        Assert.assertEquals("start == end", ArrayUtils.EMPTY_BOOLEAN_ARRAY,
            ArrayUtils.subarray(array, 3, 3));

        Assert.assertTrue("start undershoot, normal end",
            ArrayUtils.isEquals(leftSubarray,
                ArrayUtils.subarray(array, -2, 4)));

        Assert.assertEquals("start overshoot, any end",
            ArrayUtils.EMPTY_BOOLEAN_ARRAY,
                ArrayUtils.subarray(array, 33, 4));

        Assert.assertTrue("normal start, end overshoot",
            ArrayUtils.isEquals(rightSubarray,
                ArrayUtils.subarray(array, 2, 33)));

        Assert.assertTrue("start undershoot, end overshoot",
            ArrayUtils.isEquals(array,
                ArrayUtils.subarray(array, -2, 12)));

        // empty-return tests

        Assert.assertSame("empty array, object test",
            ArrayUtils.EMPTY_BOOLEAN_ARRAY,
                ArrayUtils.subarray(ArrayUtils.EMPTY_BOOLEAN_ARRAY, 1, 2));

        Assert.assertSame("start > end, object test",
            ArrayUtils.EMPTY_BOOLEAN_ARRAY,
                ArrayUtils.subarray(array, 4, 1));

        Assert.assertSame("start == end, object test",
            ArrayUtils.EMPTY_BOOLEAN_ARRAY,
                ArrayUtils.subarray(array, 3, 3));

        Assert.assertSame("start overshoot, any end, object test",
            ArrayUtils.EMPTY_BOOLEAN_ARRAY,
                ArrayUtils.subarray(array, 8733, 4));

        // array type tests

        Assert.assertSame("boolean type", boolean.class,
            ArrayUtils.subarray(array, 2, 4).getClass().getComponentType());

    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testSameLength() {
        final Object[] nullArray = null;
        final Object[] emptyArray = new Object[0];
        final Object[] oneArray = new Object[] {"pick"};
        final Object[] twoArray = new Object[] {"pick", "stick"};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }

    @Test
    public void testSameLengthBoolean() {
        final boolean[] nullArray = null;
        final boolean[] emptyArray = new boolean[0];
        final boolean[] oneArray = new boolean[] {true};
        final boolean[] twoArray = new boolean[] {true, false};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthLong() {
        final long[] nullArray = null;
        final long[] emptyArray = new long[0];
        final long[] oneArray = new long[] {0L};
        final long[] twoArray = new long[] {0L, 76L};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthInt() {
        final int[] nullArray = null;
        final int[] emptyArray = new int[0];
        final int[] oneArray = new int[] {4};
        final int[] twoArray = new int[] {5, 7};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthShort() {
        final short[] nullArray = null;
        final short[] emptyArray = new short[0];
        final short[] oneArray = new short[] {4};
        final short[] twoArray = new short[] {6, 8};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthChar() {
        final char[] nullArray = null;
        final char[] emptyArray = new char[0];
        final char[] oneArray = new char[] {'f'};
        final char[] twoArray = new char[] {'d', 't'};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthByte() {
        final byte[] nullArray = null;
        final byte[] emptyArray = new byte[0];
        final byte[] oneArray = new byte[] {3};
        final byte[] twoArray = new byte[] {4, 6};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthDouble() {
        final double[] nullArray = null;
        final double[] emptyArray = new double[0];
        final double[] oneArray = new double[] {1.3d};
        final double[] twoArray = new double[] {4.5d, 6.3d};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    @Test
    public void testSameLengthFloat() {
        final float[] nullArray = null;
        final float[] emptyArray = new float[0];
        final float[] oneArray = new float[] {2.5f};
        final float[] twoArray = new float[] {6.4f, 5.8f};
        
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(nullArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(nullArray, twoArray));
        
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, nullArray));
        Assert.assertTrue(ArrayUtils.isSameLength(emptyArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(emptyArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, emptyArray));
        Assert.assertTrue(ArrayUtils.isSameLength(oneArray, oneArray));
        Assert.assertFalse(ArrayUtils.isSameLength(oneArray, twoArray));
        
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, nullArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, emptyArray));
        Assert.assertFalse(ArrayUtils.isSameLength(twoArray, oneArray));
        Assert.assertTrue(ArrayUtils.isSameLength(twoArray, twoArray));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testSameType() {
        try {
            ArrayUtils.isSameType(null, null);
            Assert.fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            ArrayUtils.isSameType(null, new Object[0]);
            Assert.fail();
        } catch (final IllegalArgumentException ex) {}
        try {
            ArrayUtils.isSameType(new Object[0], null);
            Assert.fail();
        } catch (final IllegalArgumentException ex) {}
        
        Assert.assertTrue(ArrayUtils.isSameType(new Object[0], new Object[0]));
        Assert.assertFalse(ArrayUtils.isSameType(new String[0], new Object[0]));
        Assert.assertTrue(ArrayUtils.isSameType(new String[0][0], new String[0][0]));
        Assert.assertFalse(ArrayUtils.isSameType(new String[0], new String[0][0]));
        Assert.assertFalse(ArrayUtils.isSameType(new String[0][0], new String[0]));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testReverse() {
        final StringBuffer str1 = new StringBuffer("pick");
        final String str2 = "a";
        final String[] str3 = new String[] {"stick"};
        final String str4 = "up";
        
        Object[] array = new Object[] {str1, str2, str3};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], str3);
        Assert.assertEquals(array[1], str2);
        Assert.assertEquals(array[2], str1);
        
        array = new Object[] {str1, str2, str3, str4};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], str4);
        Assert.assertEquals(array[1], str3);
        Assert.assertEquals(array[2], str2);
        Assert.assertEquals(array[3], str1);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertArrayEquals(null, array);
    }

    @Test
    public void testReverseLong() {
        long[] array = new long[] {1L, 2L, 3L};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 3L);
        Assert.assertEquals(array[1], 2L);
        Assert.assertEquals(array[2], 1L);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseInt() {
        int[] array = new int[] {1, 2, 3};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 3);
        Assert.assertEquals(array[1], 2);
        Assert.assertEquals(array[2], 1);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseShort() {
        short[] array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 3);
        Assert.assertEquals(array[1], 2);
        Assert.assertEquals(array[2], 1);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseChar() {
        char[] array = new char[] {'a', 'f', 'C'};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 'C');
        Assert.assertEquals(array[1], 'f');
        Assert.assertEquals(array[2], 'a');

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseByte() {
        byte[] array = new byte[] {2, 3, 4};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 4);
        Assert.assertEquals(array[1], 3);
        Assert.assertEquals(array[2], 2);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseDouble() {
        double[] array = new double[] {0.3d, 0.4d, 0.5d};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 0.5d, 0.0d);
        Assert.assertEquals(array[1], 0.4d, 0.0d);
        Assert.assertEquals(array[2], 0.3d, 0.0d);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseFloat() {
        float[] array = new float[] {0.3f, 0.4f, 0.5f};
        ArrayUtils.reverse(array);
        Assert.assertEquals(array[0], 0.5f, 0.0f);
        Assert.assertEquals(array[1], 0.4f, 0.0f);
        Assert.assertEquals(array[2], 0.3f, 0.0f);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseBoolean() {
        boolean[] array = new boolean[] {false, false, true};
        ArrayUtils.reverse(array);
        Assert.assertTrue(array[0]);
        Assert.assertFalse(array[1]);
        Assert.assertFalse(array[2]);

        array = null;
        ArrayUtils.reverse(array);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseBooleanRange() {
        boolean[] array = new boolean[] {false, false, true};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertTrue(array[0]);
        Assert.assertFalse(array[1]);
        Assert.assertFalse(array[2]);
        // a range
        array = new boolean[] {false, false, true};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertFalse(array[0]);
        Assert.assertFalse(array[1]);
        Assert.assertTrue(array[2]);
        // a range with a negative start
        array = new boolean[] {false, false, true};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertTrue(array[0]);
        Assert.assertFalse(array[1]);
        Assert.assertFalse(array[2]);
        // a range with a large stop idnex
        array = new boolean[] {false, false, true};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertTrue(array[0]);
        Assert.assertFalse(array[1]);
        Assert.assertFalse(array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseByteRange() {
        byte[] array = new byte[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range
        array = new byte[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(3, array[2]);
        // a range with a negative start
        array = new byte[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range with a large stop idnex
        array = new byte[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseCharRange() {
        char[] array = new char[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range
        array = new char[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(3, array[2]);
        // a range with a negative start
        array = new char[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range with a large stop idnex
        array = new char[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseDoubleRange() {
        double[] array = new double[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        // a range
        array = new double[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0], 0);
        Assert.assertEquals(1, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        // a range with a negative start
        array = new double[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        // a range with a large stop idnex
        array = new double[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseFloatRange() {
        float[] array = new float[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        // a range
        array = new float[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0], 0);
        Assert.assertEquals(1, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        // a range with a negative start
        array = new float[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        // a range with a large stop idnex
        array = new float[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseIntRange() {
        int[] array = new int[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range
        array = new int[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(3, array[2]);
        // a range with a negative start
        array = new int[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range with a large stop idnex
        array = new int[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseLongRange() {
        long[] array = new long[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range
        array = new long[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(3, array[2]);
        // a range with a negative start
        array = new long[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range with a large stop idnex
        array = new long[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseShortRange() {
        short[] array = new short[] {1, 2, 3};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range
        array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals(2, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(3, array[2]);
        // a range with a negative start
        array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // a range with a large stop idnex
        array = new short[] {1, 2, 3};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    @Test
    public void testReverseObjectRange() {
        String[] array = new String[] {"1", "2", "3"};
        // The whole array
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("1", array[2]);
        // a range
        array = new String[] {"1", "2", "3"};
        ArrayUtils.reverse(array, 0, 2);
        Assert.assertEquals("2", array[0]);
        Assert.assertEquals("1", array[1]);
        Assert.assertEquals("3", array[2]);
        // a range with a negative start
        array = new String[] {"1", "2", "3"};
        ArrayUtils.reverse(array, -1, 3);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("1", array[2]);
        // a range with a large stop idnex
        array = new String[] {"1", "2", "3"};
        ArrayUtils.reverse(array, -1, array.length + 1000);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("1", array[2]);
        // null
        array = null;
        ArrayUtils.reverse(array, 0, 3);
        Assert.assertEquals(null, array);
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testSwapChar() {
        char[] array = new char[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2);
        Assert.assertArrayEquals(new char[] {3, 2, 1}, array);
        
        array = new char[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 0);
        Assert.assertArrayEquals(new char[] {1, 2, 3}, array);
        
        array = new char[] {1, 2, 3};
        ArrayUtils.swap(array, 1, 0);
        Assert.assertArrayEquals(new char[] {2, 1, 3}, array);
    }
    
    @Test
    public void testSwapCharRange() {
        char[] array = new char[] {1, 2, 3, 4};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);

        array = new char[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 3);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        
        array = new char[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
    }
    
    @Test
    public void testSwapFloat() {
        float[] array = new float[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
    }

    @Test
    public void testSwapFloatRange() {
        float[] array = new float[] {1, 2, 3, 4};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(4, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        Assert.assertEquals(2, array[3], 0);

        array = new float[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 3);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        
        array = new float[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
    }
    
    @Test
    public void testSwapDouble() {
        double[] array = new double[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
    }

    @Test
    public void testSwapDoubleRange() {
        double[] array = new double[] {1, 2, 3, 4};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(4, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        Assert.assertEquals(2, array[3], 0);

        array = new double[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 3);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        
        array = new double[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
    }
    
    @Test
    public void testSwapInt() {
        int[] array = new int[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
    }

    @Test
    public void testSwapIntRange() {
        int[] array = new int[] {1, 2, 3, 4};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
        
        array = new int[] {1, 2, 3};
        ArrayUtils.swap(array, 3, 0);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        
        array = new int[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
    }
    
    @Test
    public void testSwapIntExchangedOffsets() {
        int[] array;
        array = new int[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 1, 2);
        Assert.assertArrayEquals(new int[] {2, 3, 1}, array);
        
        array = new int[] {1, 2, 3};
        ArrayUtils.swap(array, 1, 0, 2);
        Assert.assertArrayEquals(new int[] {2, 3, 1}, array);
    }

    @Test
    public void testSwapLong() {
        long[] array = new long[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
    }

    @Test
    public void testSwapLongRange() {
        long[] array = new long[] {1, 2, 3, 4};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
        
        array = new long[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 3);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        
        array = new long[] {1, 2, 3};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(1, array[2]);
    }

    @Test
    public void testSwapObject() {
        String[] array = new String[] {"1", "2", "3"};
        ArrayUtils.swap(array, 0, 2);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("1", array[2]);
    }

    @Test
    public void testSwapObjectRange() {
        String[] array = new String[] {"1", "2", "3", "4"};
        ArrayUtils.swap(array, 0, 2, 2);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("4", array[1]);
        Assert.assertEquals("1", array[2]);
        Assert.assertEquals("2", array[3]);

        array = new String[] {"1", "2", "3", "4"};
        ArrayUtils.swap(array, -1, 2, 3);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("4", array[1]);
        Assert.assertEquals("1", array[2]);
        Assert.assertEquals("2", array[3]);

        array = new String[] {"1", "2", "3", "4", "5"};
        ArrayUtils.swap(array, -3, 2, 3);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("4", array[1]);
        Assert.assertEquals("5", array[2]);
        Assert.assertEquals("2", array[3]);
        Assert.assertEquals("1", array[4]);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testShiftDouble() {
        double[] array = new double[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0], 0);
        Assert.assertEquals(1, array[1], 0);
        Assert.assertEquals(2, array[2], 0);
        Assert.assertEquals(3, array[3], 0);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0], 0);
        Assert.assertEquals(1, array[1], 0);
        Assert.assertEquals(2, array[2], 0);
        Assert.assertEquals(3, array[3], 0);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(4, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        Assert.assertEquals(2, array[3], 0);
    }

    @Test
    public void testShiftRangeDouble() {
        double[] array = new double[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(3, array[1], 0);
        Assert.assertEquals(2, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
        Assert.assertEquals(5, array[4], 0);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(4, array[2], 0);
        Assert.assertEquals(3, array[3], 0);
        Assert.assertEquals(5, array[4], 0);
    }

    @Test
    public void testShiftRangeNoElemDouble() {
        double[] array = new double[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
    }

    @Test
    public void testShiftAllDouble() {
        double[] array = new double[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
    }
    
    @Test
    public void testShiftFloat() {
        float[] array = new float[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0], 0);
        Assert.assertEquals(1, array[1], 0);
        Assert.assertEquals(2, array[2], 0);
        Assert.assertEquals(3, array[3], 0);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0], 0);
        Assert.assertEquals(1, array[1], 0);
        Assert.assertEquals(2, array[2], 0);
        Assert.assertEquals(3, array[3], 0);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0], 0);
        Assert.assertEquals(4, array[1], 0);
        Assert.assertEquals(1, array[2], 0);
        Assert.assertEquals(2, array[3], 0);
    }

    @Test
    public void testShiftRangeFloat() {
        float[] array = new float[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(3, array[1], 0);
        Assert.assertEquals(2, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
        Assert.assertEquals(5, array[4], 0);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(4, array[2], 0);
        Assert.assertEquals(3, array[3], 0);
        Assert.assertEquals(5, array[4], 0);
    }

    @Test
    public void testShiftRangeNoElemFloat() {
        float[] array = new float[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
    }

    @Test
    public void testShiftAllFloat() {
        float[] array = new float[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0], 0);
        Assert.assertEquals(2, array[1], 0);
        Assert.assertEquals(3, array[2], 0);
        Assert.assertEquals(4, array[3], 0);
    }
    
    @Test
    public void testShiftShort() {
        short[] array = new short[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
        array = new short[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 2);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(5, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
        Assert.assertEquals(3, array[4]);
    }

    @Test
    public void testShiftRangeShort() {
        short[] array = new short[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(3, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(4, array[3]);
        Assert.assertEquals(5, array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(4, array[2]);
        Assert.assertEquals(3, array[3]);
        Assert.assertEquals(5, array[4]);
    }

    @Test
    public void testShiftRangeNoElemShort() {
        short[] array = new short[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }

    @Test
    public void testShiftAllShort() {
        short[] array = new short[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }
    
    @Test
    public void testShiftByte() {
        byte[] array = new byte[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
    }

    @Test
    public void testShiftRangeByte() {
        byte[] array = new byte[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(3, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(4, array[3]);
        Assert.assertEquals(5, array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(4, array[2]);
        Assert.assertEquals(3, array[3]);
        Assert.assertEquals(5, array[4]);
    }

    @Test
    public void testShiftRangeNoElemByte() {
        byte[] array = new byte[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }

    @Test
    public void testShiftAllByte() {
        byte[] array = new byte[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }
    
    @Test
    public void testShiftChar() {
        char[] array = new char[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
    }

    @Test
    public void testShiftRangeChar() {
        char[] array = new char[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(3, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(4, array[3]);
        Assert.assertEquals(5, array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(4, array[2]);
        Assert.assertEquals(3, array[3]);
        Assert.assertEquals(5, array[4]);
    }

    @Test
    public void testShiftRangeNoElemChar() {
        char[] array = new char[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }

    @Test
    public void testShiftAllChar() {
        char[] array = new char[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }
    
    @Test
    public void testShiftLong() {
        long[] array = new long[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
    }

    @Test
    public void testShiftRangeLong() {
        long[] array = new long[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(3, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(4, array[3]);
        Assert.assertEquals(5, array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(4, array[2]);
        Assert.assertEquals(3, array[3]);
        Assert.assertEquals(5, array[4]);
    }

    @Test
    public void testShiftRangeNoElemLong() {
        long[] array = new long[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }

    @Test
    public void testShiftAllLong() {
        long[] array = new long[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }
    
    @Test
    public void testShiftInt() {
        int[] array = new int[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals(4, array[0]);
        Assert.assertEquals(1, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(3, array[3]);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals(3, array[0]);
        Assert.assertEquals(4, array[1]);
        Assert.assertEquals(1, array[2]);
        Assert.assertEquals(2, array[3]);
    }

    @Test
    public void testShiftRangeInt() {
        int[] array = new int[] {1, 2, 3, 4, 5};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(3, array[1]);
        Assert.assertEquals(2, array[2]);
        Assert.assertEquals(4, array[3]);
        Assert.assertEquals(5, array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(4, array[2]);
        Assert.assertEquals(3, array[3]);
        Assert.assertEquals(5, array[4]);
    }

    @Test
    public void testShiftRangeNoElemInt() {
        int[] array = new int[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }

    @Test
    public void testShiftAllInt() {
        int[] array = new int[] {1, 2, 3, 4};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals(1, array[0]);
        Assert.assertEquals(2, array[1]);
        Assert.assertEquals(3, array[2]);
        Assert.assertEquals(4, array[3]);
    }

    @Test
    public void testShiftObject() {
        String[] array = new String[] {"1", "2", "3", "4"};
        ArrayUtils.shift(array, 1);
        Assert.assertEquals("4", array[0]);
        Assert.assertEquals("1", array[1]);
        Assert.assertEquals("2", array[2]);
        Assert.assertEquals("3", array[3]);
        ArrayUtils.shift(array, -1);
        Assert.assertEquals("1", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("3", array[2]);
        Assert.assertEquals("4", array[3]);
        ArrayUtils.shift(array, 5);
        Assert.assertEquals("4", array[0]);
        Assert.assertEquals("1", array[1]);
        Assert.assertEquals("2", array[2]);
        Assert.assertEquals("3", array[3]);
        ArrayUtils.shift(array, -3);
        Assert.assertEquals("3", array[0]);
        Assert.assertEquals("4", array[1]);
        Assert.assertEquals("1", array[2]);
        Assert.assertEquals("2", array[3]);
    }

    @Test
    public void testShiftRangeObject() {
        String[] array = new String[] {"1", "2", "3", "4", "5"};
        ArrayUtils.shift(array, 1, 3, 1);
        Assert.assertEquals("1", array[0]);
        Assert.assertEquals("3", array[1]);
        Assert.assertEquals("2", array[2]);
        Assert.assertEquals("4", array[3]);
        Assert.assertEquals("5", array[4]);
        ArrayUtils.shift(array, 1, 4, 2);
        Assert.assertEquals("1", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("4", array[2]);
        Assert.assertEquals("3", array[3]);
        Assert.assertEquals("5", array[4]);
    }

    @Test
    public void testShiftRangeNoElemObject() {
        String[] array = new String[] {"1", "2", "3", "4"};
        ArrayUtils.shift(array, 1, 1, 1);
        Assert.assertEquals("1", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("3", array[2]);
        Assert.assertEquals("4", array[3]);
    }

    @Test
    public void testShiftAllObject() {
        String[] array = new String[] {"1", "2", "3", "4"};
        ArrayUtils.shift(array, 4);
        Assert.assertEquals("1", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("3", array[2]);
        Assert.assertEquals("4", array[3]);
        ArrayUtils.shift(array, -4);
        Assert.assertEquals("1", array[0]);
        Assert.assertEquals("2", array[1]);
        Assert.assertEquals("3", array[2]);
        Assert.assertEquals("4", array[3]);
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testIndexOf() {
        final Object[] array = new Object[] { "0", "1", "2", "3", null, "0" };
        Assert.assertEquals(-1, ArrayUtils.indexOf(null, null));
        Assert.assertEquals(-1, ArrayUtils.indexOf(null, "0"));
        Assert.assertEquals(-1, ArrayUtils.indexOf(new Object[0], "0"));
        Assert.assertEquals(0, ArrayUtils.indexOf(array, "0"));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, "1"));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, "2"));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, "3"));
        Assert.assertEquals(4, ArrayUtils.indexOf(array, null));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, "notInArray"));
    }

    @Test
    public void testIndexOfWithStartIndex() {
        final Object[] array = new Object[] { "0", "1", "2", "3", null, "0" };
        Assert.assertEquals(-1, ArrayUtils.indexOf(null, null, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(new Object[0], "0", 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(null, "0", 2));
        Assert.assertEquals(5, ArrayUtils.indexOf(array, "0", 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, "1", 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, "2", 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, "3", 2));
        Assert.assertEquals(4, ArrayUtils.indexOf(array, null, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, "notInArray", 2));
        
        Assert.assertEquals(4, ArrayUtils.indexOf(array, null, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, null, 8));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, "0", 8));
    }

    @Test
    public void testLastIndexOf() {
        final Object[] array = new Object[] { "0", "1", "2", "3", null, "0" };
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(null, null));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(null, "0"));
        Assert.assertEquals(5, ArrayUtils.lastIndexOf(array, "0"));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, "1"));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, "2"));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, "3"));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, null));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, "notInArray"));
    }

    @Test
    public void testLastIndexOfWithStartIndex() {
        final Object[] array = new Object[] { "0", "1", "2", "3", null, "0" };
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(null, null, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(null, "0", 2));
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, "0", 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, "1", 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, "2", 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, "3", 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, "3", -1));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, null, 5));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, null, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, "notInArray", 5));
        
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, null, -1));
        Assert.assertEquals(5, ArrayUtils.lastIndexOf(array, "0", 88));
    }

    @Test
    public void testContains() {
        final Object[] array = new Object[] { "0", "1", "2", "3", null, "0" };
        Assert.assertFalse(ArrayUtils.contains(null, null));
        Assert.assertFalse(ArrayUtils.contains(null, "1"));
        Assert.assertTrue(ArrayUtils.contains(array, "0"));
        Assert.assertTrue(ArrayUtils.contains(array, "1"));
        Assert.assertTrue(ArrayUtils.contains(array, "2"));
        Assert.assertTrue(ArrayUtils.contains(array, "3"));
        Assert.assertTrue(ArrayUtils.contains(array, null));
        Assert.assertFalse(ArrayUtils.contains(array, "notInArray"));
    }

    @Test
    public void testContains_LANG_1261() {
        class LANG1261ParentObject {
            @Override
            public boolean equals(Object o) {
                return true;
            }
        }
        class LANG1261ChildObject extends LANG1261ParentObject {
        }

        Object[] array = new LANG1261ChildObject[] { new LANG1261ChildObject() };

        Assert.assertTrue(ArrayUtils.contains(array, new LANG1261ParentObject()));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfLong() {
        long[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 0));
        array = new long[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, 1));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 3));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 99));
    }

    @Test
    public void testIndexOfLongWithStartIndex() {
        long[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 0, 2));
        array = new long[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, 0, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 1, 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, 2, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 3, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 3, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 99, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 0, 6));
    }

    @Test
    public void testLastIndexOfLong() {
        long[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 0));
        array = new long[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, 0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, 1));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, 2));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, 3));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 99));
    }

    @Test
    public void testLastIndexOfLongWithStartIndex() {
        long[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 0, 2));
        array = new long[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, 0, 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, 1, 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, 2, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 99, 4));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, 0, 88));
    }

    @Test
    public void testContainsLong() {
        long[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, 1));
        array = new long[] { 0, 1, 2, 3, 0 };
        Assert.assertTrue(ArrayUtils.contains(array, 0));
        Assert.assertTrue(ArrayUtils.contains(array, 1));
        Assert.assertTrue(ArrayUtils.contains(array, 2));
        Assert.assertTrue(ArrayUtils.contains(array, 3));
        Assert.assertFalse(ArrayUtils.contains(array, 99));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfInt() {
        int[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 0));
        array = new int[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, 1));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 3));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 99));
    }

    @Test
    public void testIndexOfIntWithStartIndex() {
        int[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 0, 2));
        array = new int[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, 0, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 1, 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, 2, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 3, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 3, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 99, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 0, 6));
    }

    @Test
    public void testLastIndexOfInt() {
        int[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 0));
        array = new int[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, 0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, 1));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, 2));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, 3));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 99));
    }

    @Test
    public void testLastIndexOfIntWithStartIndex() {
        int[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 0, 2));
        array = new int[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, 0, 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, 1, 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, 2, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 3, -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 99));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, 0, 88));
    }

    @Test
    public void testContainsInt() {
        int[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, 1));
        array = new int[] { 0, 1, 2, 3, 0 };
        Assert.assertTrue(ArrayUtils.contains(array, 0));
        Assert.assertTrue(ArrayUtils.contains(array, 1));
        Assert.assertTrue(ArrayUtils.contains(array, 2));
        Assert.assertTrue(ArrayUtils.contains(array, 3));
        Assert.assertFalse(ArrayUtils.contains(array, 99));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfShort() {
        short[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (short) 0));
        array = new short[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, (short) 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (short) 1));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (short) 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (short) 3));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (short) 99));
    }

    @Test
    public void testIndexOfShortWithStartIndex() {
        short[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (short) 0, 2));
        array = new short[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, (short) 0, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (short) 1, 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (short) 2, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (short) 3, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (short) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (short) 99, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (short) 0, 6));
    }

    @Test
    public void testLastIndexOfShort() {
        short[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 0));
        array = new short[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (short) 0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (short) 1));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (short) 2));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, (short) 3));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 99));
    }

    @Test
    public void testLastIndexOfShortWithStartIndex() {
        short[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 0, 2));
        array = new short[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, (short) 0, 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (short) 1, 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (short) 2, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 3, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (short) 99));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (short) 0, 88));
    }

    @Test
    public void testContainsShort() {
        short[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, (short) 1));
        array = new short[] { 0, 1, 2, 3, 0 };
        Assert.assertTrue(ArrayUtils.contains(array, (short) 0));
        Assert.assertTrue(ArrayUtils.contains(array, (short) 1));
        Assert.assertTrue(ArrayUtils.contains(array, (short) 2));
        Assert.assertTrue(ArrayUtils.contains(array, (short) 3));
        Assert.assertFalse(ArrayUtils.contains(array, (short) 99));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfChar() {
        char[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 'a'));
        array = new char[] { 'a', 'b', 'c', 'd', 'a' };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, 'a'));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, 'b'));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, 'c'));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 'd'));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 'e'));
    }

    @Test
    public void testIndexOfCharWithStartIndex() {
        char[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 'a', 2));
        array = new char[] { 'a', 'b', 'c', 'd', 'a' };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, 'a', 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 'b', 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, 'c', 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 'd', 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, 'd', -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 'e', 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, 'a', 6));
    }

    @Test
    public void testLastIndexOfChar() {
        char[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 'a'));
        array = new char[] { 'a', 'b', 'c', 'd', 'a' };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, 'a'));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, 'b'));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, 'c'));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, 'd'));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 'e'));
    }

    @Test
    public void testLastIndexOfCharWithStartIndex() {
        char[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 'a', 2));
        array = new char[] { 'a', 'b', 'c', 'd', 'a' };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, 'a', 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, 'b', 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, 'c', 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 'd', 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 'd', -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, 'e'));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, 'a', 88));
    }

    @Test
    public void testContainsChar() {
        char[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, 'b'));
        array = new char[] { 'a', 'b', 'c', 'd', 'a' };
        Assert.assertTrue(ArrayUtils.contains(array, 'a'));
        Assert.assertTrue(ArrayUtils.contains(array, 'b'));
        Assert.assertTrue(ArrayUtils.contains(array, 'c'));
        Assert.assertTrue(ArrayUtils.contains(array, 'd'));
        Assert.assertFalse(ArrayUtils.contains(array, 'e'));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfByte() {
        byte[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (byte) 0));
        array = new byte[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, (byte) 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (byte) 1));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (byte) 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (byte) 3));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (byte) 99));
    }

    @Test
    public void testIndexOfByteWithStartIndex() {
        byte[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (byte) 0, 2));
        array = new byte[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, (byte) 0, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (byte) 1, 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (byte) 2, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (byte) 3, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (byte) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (byte) 99, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (byte) 0, 6));
    }

    @Test
    public void testLastIndexOfByte() {
        byte[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 0));
        array = new byte[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (byte) 0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (byte) 1));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (byte) 2));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, (byte) 3));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 99));
    }

    @Test
    public void testLastIndexOfByteWithStartIndex() {
        byte[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 0, 2));
        array = new byte[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, (byte) 0, 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (byte) 1, 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (byte) 2, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 3, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (byte) 99));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (byte) 0, 88));
    }

    @Test
    public void testContainsByte() {
        byte[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, (byte) 1));
        array = new byte[] { 0, 1, 2, 3, 0 };
        Assert.assertTrue(ArrayUtils.contains(array, (byte) 0));
        Assert.assertTrue(ArrayUtils.contains(array, (byte) 1));
        Assert.assertTrue(ArrayUtils.contains(array, (byte) 2));
        Assert.assertTrue(ArrayUtils.contains(array, (byte) 3));
        Assert.assertFalse(ArrayUtils.contains(array, (byte) 99));
    }
    
    //-----------------------------------------------------------------------
    @SuppressWarnings("cast")
    @Test
    public void testIndexOfDouble() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, (double) 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (double) 1));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (double) 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (double) 3));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (double) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 99));
    }

    @SuppressWarnings("cast")
    @Test
    public void testIndexOfDoubleTolerance() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, (double) 0));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, (double) 0, (double) 0.3));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (double) 2.2, (double) 0.35));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (double) 4.15, (double) 2.0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (double) 1.00001324, (double) 0.0001));
    }

    @SuppressWarnings("cast")
    @Test
    public void testIndexOfDoubleWithStartIndex() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, (double) 0, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 1, 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (double) 2, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (double) 3, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 99, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 6));
    }
    
    @SuppressWarnings("cast")
    @Test
    public void testIndexOfDoubleWithStartIndexTolerance() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2, (double) 0));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 2, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (double) 0, 99, (double) 0.3));
        Assert.assertEquals(0, ArrayUtils.indexOf(array, (double) 0, 0, (double) 0.3));
        Assert.assertEquals(4, ArrayUtils.indexOf(array, (double) 0, 3, (double) 0.3));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (double) 2.2, 0, (double) 0.35));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (double) 4.15, 0, (double) 2.0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (double) 1.00001324, 0, (double) 0.0001));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (double) 4.15, -1, (double) 2.0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (double) 1.00001324, -300, (double) 0.0001));
    }

    @SuppressWarnings("cast")
    @Test
    public void testLastIndexOfDouble() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, (double) 3));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 99));
    }

    @SuppressWarnings("cast")
    @Test
    public void testLastIndexOfDoubleTolerance() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, (double) 0));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0, (double) 0.3));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2.2, (double) 0.35));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, (double) 4.15, (double) 2.0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1.00001324, (double) 0.0001));
    }

    @SuppressWarnings("cast")
    @Test
    public void testLastIndexOfDoubleWithStartIndex() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, (double) 0, 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1, 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 3, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 99));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0, 88));
    }

    @SuppressWarnings("cast")
    @Test
    public void testLastIndexOfDoubleWithStartIndexTolerance() {
        double[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2, (double) 0));
        array = new double[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 0, 2, (double) 0));
        array = new double[] { (double) 3 };
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 1, 0, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (double) 0, 99, (double) 0.3));
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, (double) 0, 3, (double) 0.3));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (double) 2.2, 3, (double) 0.35));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, (double) 4.15, array.length, (double) 2.0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (double) 1.00001324, array.length, (double) 0.0001));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (double) 4.15, -200, (double) 2.0));
    }

    @SuppressWarnings("cast")
    @Test
    public void testContainsDouble() {
        double[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, (double) 1));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertTrue(ArrayUtils.contains(array, (double) 0));
        Assert.assertTrue(ArrayUtils.contains(array, (double) 1));
        Assert.assertTrue(ArrayUtils.contains(array, (double) 2));
        Assert.assertTrue(ArrayUtils.contains(array, (double) 3));
        Assert.assertFalse(ArrayUtils.contains(array, (double) 99));
    }

    @SuppressWarnings("cast")
    @Test
    public void testContainsDoubleTolerance() {
        double[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, (double) 1, (double) 0));
        array = new double[] { 0, 1, 2, 3, 0 };
        Assert.assertFalse(ArrayUtils.contains(array, (double) 4.0, (double) 0.33));
        Assert.assertFalse(ArrayUtils.contains(array, (double) 2.5, (double) 0.49));
        Assert.assertTrue(ArrayUtils.contains(array, (double) 2.5, (double) 0.50));
        Assert.assertTrue(ArrayUtils.contains(array, (double) 2.5, (double) 0.51));
    }
    
    //-----------------------------------------------------------------------
    @SuppressWarnings("cast")
    @Test
    public void testIndexOfFloat() {
        float[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 0));
        array = new float[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 0));
        array = new float[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, (float) 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, (float) 1));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (float) 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (float) 3));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 99));
    }

    @SuppressWarnings("cast")
    @Test
    public void testIndexOfFloatWithStartIndex() {
        float[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 0, 2));
        array = new float[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 0, 2));
        array = new float[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.indexOf(array, (float) 0, 2));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 1, 2));
        Assert.assertEquals(2, ArrayUtils.indexOf(array, (float) 2, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (float) 3, 2));
        Assert.assertEquals(3, ArrayUtils.indexOf(array, (float) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 99, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, (float) 0, 6));
    }

    @SuppressWarnings("cast")
    @Test
    public void testLastIndexOfFloat() {
        float[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0));
        array = new float[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0));
        array = new float[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (float) 0));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (float) 1));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (float) 2));
        Assert.assertEquals(3, ArrayUtils.lastIndexOf(array, (float) 3));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 99));
    }

    @SuppressWarnings("cast")
    @Test
    public void testLastIndexOfFloatWithStartIndex() {
        float[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0, 2));
        array = new float[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 0, 2));
        array = new float[] { 0, 1, 2, 3, 0 };
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, (float) 0, 2));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, (float) 1, 2));
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, (float) 2, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 3, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 3, -1));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, (float) 99));
        Assert.assertEquals(4, ArrayUtils.lastIndexOf(array, (float) 0, 88));
    }

    @SuppressWarnings("cast")
    @Test
    public void testContainsFloat() {
        float[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, (float) 1));
        array = new float[] { 0, 1, 2, 3, 0 };
        Assert.assertTrue(ArrayUtils.contains(array, (float) 0));
        Assert.assertTrue(ArrayUtils.contains(array, (float) 1));
        Assert.assertTrue(ArrayUtils.contains(array, (float) 2));
        Assert.assertTrue(ArrayUtils.contains(array, (float) 3));
        Assert.assertFalse(ArrayUtils.contains(array, (float) 99));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testIndexOfBoolean() {
        boolean[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, true));
        array = new boolean[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, true));
        array = new boolean[] { true, false, true };
        Assert.assertEquals(0, ArrayUtils.indexOf(array, true));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, false));
        array = new boolean[] { true, true };
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, false));
    }

    @Test
    public void testIndexOfBooleanWithStartIndex() {
        boolean[] array = null;
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, true, 2));
        array = new boolean[0];
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, true, 2));
        array = new boolean[] { true, false, true };
        Assert.assertEquals(2, ArrayUtils.indexOf(array, true, 1));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, false, 2));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, false, 0));
        Assert.assertEquals(1, ArrayUtils.indexOf(array, false, -1));
        array = new boolean[] { true, true };
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, false, 0));
        Assert.assertEquals(-1, ArrayUtils.indexOf(array, false, -1));
    }

    @Test
    public void testLastIndexOfBoolean() {
        boolean[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, true));
        array = new boolean[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, true));
        array = new boolean[] { true, false, true };
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, true));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, false));
        array = new boolean[] { true, true };
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, false));
    }

    @Test
    public void testLastIndexOfBooleanWithStartIndex() {
        boolean[] array = null;
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, true, 2));
        array = new boolean[0];
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, true, 2));
        array = new boolean[] { true, false, true };
        Assert.assertEquals(2, ArrayUtils.lastIndexOf(array, true, 2));
        Assert.assertEquals(0, ArrayUtils.lastIndexOf(array, true, 1));
        Assert.assertEquals(1, ArrayUtils.lastIndexOf(array, false, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, true, -1));
        array = new boolean[] { true, true };
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, false, 2));
        Assert.assertEquals(-1, ArrayUtils.lastIndexOf(array, true, -1));
    }

    @Test
    public void testContainsBoolean() {
        boolean[] array = null;
        Assert.assertFalse(ArrayUtils.contains(array, true));
        array = new boolean[] { true, false, true };
        Assert.assertTrue(ArrayUtils.contains(array, true));
        Assert.assertTrue(ArrayUtils.contains(array, false));
        array = new boolean[] { true, true };
        Assert.assertTrue(ArrayUtils.contains(array, true));
        Assert.assertFalse(ArrayUtils.contains(array, false));
    }
    
    // testToPrimitive/Object for boolean
    //  -----------------------------------------------------------------------
    @Test
    public void testToPrimitive_boolean() {
        final Boolean[] b = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
        Assert.assertSame(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.toPrimitive(new Boolean[0]));
        Assert.assertTrue(Arrays.equals(
            new boolean[] {true, false, true},
            ArrayUtils.toPrimitive(new Boolean[] {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE}))
        );

        try {
            ArrayUtils.toPrimitive(new Boolean[] {Boolean.TRUE, null});
            Assert.fail();
        } catch (final NullPointerException ex) {}
    }

    @Test
    public void testToPrimitive_boolean_boolean() {
        Assert.assertEquals(null, ArrayUtils.toPrimitive(null, false));
        Assert.assertSame(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.toPrimitive(new Boolean[0], false));
        Assert.assertTrue(Arrays.equals(
            new boolean[] {true, false, true},
            ArrayUtils.toPrimitive(new Boolean[] {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE}, false))
        );
        Assert.assertTrue(Arrays.equals(
            new boolean[] {true, false, false},
            ArrayUtils.toPrimitive(new Boolean[] {Boolean.TRUE, null, Boolean.FALSE}, false))
        );
        Assert.assertTrue(Arrays.equals(
            new boolean[] {true, true, false},
            ArrayUtils.toPrimitive(new Boolean[] {Boolean.TRUE, null, Boolean.FALSE}, true))
        );
    }

    @Test
    public void testToObject_boolean() {
        final boolean[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
        Assert.assertSame(ArrayUtils.EMPTY_BOOLEAN_OBJECT_ARRAY, ArrayUtils.toObject(new boolean[0]));
        Assert.assertTrue(Arrays.equals(
            new Boolean[] {Boolean.TRUE, Boolean.FALSE, Boolean.TRUE},
            ArrayUtils.toObject(new boolean[] {true, false, true}))
        );
    }

    // testToPrimitive/Object for byte
    //  -----------------------------------------------------------------------
    @Test
    public void testToPrimitive_char() {
        final Character[] b = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
        
        Assert.assertSame(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.toPrimitive(new Character[0]));
        
        Assert.assertTrue(Arrays.equals(
            new char[] {Character.MIN_VALUE, Character.MAX_VALUE, '0'},
            ArrayUtils.toPrimitive(new Character[] {new Character(Character.MIN_VALUE), 
                new Character(Character.MAX_VALUE), new Character('0')}))
        );

        try {
            ArrayUtils.toPrimitive(new Character[] {new Character(Character.MIN_VALUE), null});
            Assert.fail();
        } catch (final NullPointerException ex) {}
    }

    @Test
    public void testToPrimitive_char_char() {
        final Character[] b = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(b, Character.MIN_VALUE));
        
        Assert.assertSame(ArrayUtils.EMPTY_CHAR_ARRAY,
            ArrayUtils.toPrimitive(new Character[0], (char)0));
        
        Assert.assertTrue(Arrays.equals(
            new char[] {Character.MIN_VALUE, Character.MAX_VALUE, '0'},
            ArrayUtils.toPrimitive(new Character[] {new Character(Character.MIN_VALUE), 
                new Character(Character.MAX_VALUE), new Character('0')}, 
                Character.MIN_VALUE))
        );
        
        Assert.assertTrue(Arrays.equals(
            new char[] {Character.MIN_VALUE, Character.MAX_VALUE, '0'},
            ArrayUtils.toPrimitive(new Character[] {new Character(Character.MIN_VALUE), null, 
                new Character('0')}, Character.MAX_VALUE))
        );
    }

    @Test
    public void testToObject_char() {
        final char[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
        
        Assert.assertSame(ArrayUtils.EMPTY_CHARACTER_OBJECT_ARRAY,
            ArrayUtils.toObject(new char[0]));
        
        Assert.assertTrue(Arrays.equals(
            new Character[] {new Character(Character.MIN_VALUE), 
                new Character(Character.MAX_VALUE), new Character('0')},
                ArrayUtils.toObject(new char[] {Character.MIN_VALUE, Character.MAX_VALUE, 
                '0'} ))
        );
    }
    
    // testToPrimitive/Object for byte
    //  -----------------------------------------------------------------------
    @Test
    public void testToPrimitive_byte() {
        final Byte[] b = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
        
        Assert.assertSame(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.toPrimitive(new Byte[0]));
        
        Assert.assertTrue(Arrays.equals(
            new byte[] {Byte.MIN_VALUE, Byte.MAX_VALUE, (byte)9999999},
            ArrayUtils.toPrimitive(new Byte[] {Byte.valueOf(Byte.MIN_VALUE), 
                Byte.valueOf(Byte.MAX_VALUE), Byte.valueOf((byte)9999999)}))
        );

        try {
            ArrayUtils.toPrimitive(new Byte[] {Byte.valueOf(Byte.MIN_VALUE), null});
            Assert.fail();
        } catch (final NullPointerException ex) {}
    }

    @Test
    public void testToPrimitive_byte_byte() {
        final Byte[] b = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(b, Byte.MIN_VALUE));
        
        Assert.assertSame(ArrayUtils.EMPTY_BYTE_ARRAY,
            ArrayUtils.toPrimitive(new Byte[0], (byte)1));
        
        Assert.assertTrue(Arrays.equals(
            new byte[] {Byte.MIN_VALUE, Byte.MAX_VALUE, (byte)9999999},
            ArrayUtils.toPrimitive(new Byte[] {Byte.valueOf(Byte.MIN_VALUE), 
                Byte.valueOf(Byte.MAX_VALUE), Byte.valueOf((byte)9999999)}, 
                Byte.MIN_VALUE))
        );
        
        Assert.assertTrue(Arrays.equals(
            new byte[] {Byte.MIN_VALUE, Byte.MAX_VALUE, (byte)9999999},
            ArrayUtils.toPrimitive(new Byte[] {Byte.valueOf(Byte.MIN_VALUE), null, 
                Byte.valueOf((byte)9999999)}, Byte.MAX_VALUE))
        );
    }

    @Test
    public void testToObject_byte() {
        final byte[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
        
        Assert.assertSame(ArrayUtils.EMPTY_BYTE_OBJECT_ARRAY,
            ArrayUtils.toObject(new byte[0]));
        
        Assert.assertTrue(Arrays.equals(
            new Byte[] {Byte.valueOf(Byte.MIN_VALUE), 
                Byte.valueOf(Byte.MAX_VALUE), Byte.valueOf((byte)9999999)},
                ArrayUtils.toObject(new byte[] {Byte.MIN_VALUE, Byte.MAX_VALUE, 
                (byte)9999999}))
        );
    }

    // testToPrimitive/Object for short
    //  -----------------------------------------------------------------------
    @Test
    public void testToPrimitive_short() {
        final Short[] b = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
        
        Assert.assertSame(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.toPrimitive(new Short[0]));
        
        Assert.assertTrue(Arrays.equals(
            new short[] {Short.MIN_VALUE, Short.MAX_VALUE, (short)9999999},
            ArrayUtils.toPrimitive(new Short[] {Short.valueOf(Short.MIN_VALUE), 
                Short.valueOf(Short.MAX_VALUE), Short.valueOf((short)9999999)}))
        );

        try {
            ArrayUtils.toPrimitive(new Short[] {Short.valueOf(Short.MIN_VALUE), null});
            Assert.fail();
        } catch (final NullPointerException ex) {}
    }

    @Test
    public void testToPrimitive_short_short() {
        final Short[] s = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(s, Short.MIN_VALUE));
        
        Assert.assertSame(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.toPrimitive(new Short[0],
        Short.MIN_VALUE));
        
        Assert.assertTrue(Arrays.equals(
            new short[] {Short.MIN_VALUE, Short.MAX_VALUE, (short)9999999},
            ArrayUtils.toPrimitive(new Short[] {Short.valueOf(Short.MIN_VALUE), 
                Short.valueOf(Short.MAX_VALUE), Short.valueOf((short)9999999)}, Short.MIN_VALUE))
        );
        
        Assert.assertTrue(Arrays.equals(
            new short[] {Short.MIN_VALUE, Short.MAX_VALUE, (short)9999999},
            ArrayUtils.toPrimitive(new Short[] {Short.valueOf(Short.MIN_VALUE), null, 
                Short.valueOf((short)9999999)}, Short.MAX_VALUE))
        );
    }

    @Test
    public void testToObject_short() {
        final short[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
        
        Assert.assertSame(ArrayUtils.EMPTY_SHORT_OBJECT_ARRAY,
        ArrayUtils.toObject(new short[0]));
        
        Assert.assertTrue(Arrays.equals(
            new Short[] {Short.valueOf(Short.MIN_VALUE), Short.valueOf(Short.MAX_VALUE), 
                Short.valueOf((short)9999999)},
            ArrayUtils.toObject(new short[] {Short.MIN_VALUE, Short.MAX_VALUE, 
                (short)9999999}))
        );
    }

    //  testToPrimitive/Object for int
    //  -----------------------------------------------------------------------
     @Test
     public void testToPrimitive_int() {
         final Integer[] b = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
         Assert.assertSame(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.toPrimitive(new Integer[0]));
         Assert.assertTrue(Arrays.equals(
             new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Integer[] {Integer.valueOf(Integer.MIN_VALUE), 
                 Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(9999999)}))
         );

         try {
             ArrayUtils.toPrimitive(new Integer[] {Integer.valueOf(Integer.MIN_VALUE), null});
             Assert.fail();
         } catch (final NullPointerException ex) {}
     }

     @Test
     public void testToPrimitive_int_int() {
         final Long[] l = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(l, Integer.MIN_VALUE));
         Assert.assertSame(ArrayUtils.EMPTY_INT_ARRAY,
         ArrayUtils.toPrimitive(new Integer[0], 1));
         Assert.assertTrue(Arrays.equals(
             new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Integer[] {Integer.valueOf(Integer.MIN_VALUE), 
                 Integer.valueOf(Integer.MAX_VALUE), Integer.valueOf(9999999)},1)));
         Assert.assertTrue(Arrays.equals(
             new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Integer[] {Integer.valueOf(Integer.MIN_VALUE), 
                 null, Integer.valueOf(9999999)}, Integer.MAX_VALUE))
         );
     }
     
    @Test
    public void testToPrimitive_intNull() {
        final Integer[] iArray = null;
        Assert.assertEquals(null, ArrayUtils.toPrimitive(iArray, Integer.MIN_VALUE));
    }

    @Test
    public void testToObject_int() {
        final int[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
    
        Assert.assertSame(
            ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY,
            ArrayUtils.toObject(new int[0]));
    
        Assert.assertTrue(
            Arrays.equals(
                new Integer[] {
                    Integer.valueOf(Integer.MIN_VALUE),
                    Integer.valueOf(Integer.MAX_VALUE),
                    Integer.valueOf(9999999)},
            ArrayUtils.toObject(
                new int[] { Integer.MIN_VALUE, Integer.MAX_VALUE, 9999999 })));
    }

    //  testToPrimitive/Object for long
    //  -----------------------------------------------------------------------
     @Test
     public void testToPrimitive_long() {
         final Long[] b = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
         
         Assert.assertSame(ArrayUtils.EMPTY_LONG_ARRAY,
            ArrayUtils.toPrimitive(new Long[0]));
         
         Assert.assertTrue(Arrays.equals(
             new long[] {Long.MIN_VALUE, Long.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Long[] {Long.valueOf(Long.MIN_VALUE), 
                 Long.valueOf(Long.MAX_VALUE), Long.valueOf(9999999)}))
         );

         try {
             ArrayUtils.toPrimitive(new Long[] {Long.valueOf(Long.MIN_VALUE), null});
             Assert.fail();
         } catch (final NullPointerException ex) {}
     }

     @Test
     public void testToPrimitive_long_long() {
         final Long[] l = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(l, Long.MIN_VALUE));
         
         Assert.assertSame(ArrayUtils.EMPTY_LONG_ARRAY,
         ArrayUtils.toPrimitive(new Long[0], 1));
         
         Assert.assertTrue(Arrays.equals(
             new long[] {Long.MIN_VALUE, Long.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Long[] {Long.valueOf(Long.MIN_VALUE), 
                 Long.valueOf(Long.MAX_VALUE), Long.valueOf(9999999)},1)));
         
         Assert.assertTrue(Arrays.equals(
             new long[] {Long.MIN_VALUE, Long.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Long[] {Long.valueOf(Long.MIN_VALUE), 
                 null, Long.valueOf(9999999)}, Long.MAX_VALUE))
         );
     }
     
    @Test
    public void testToObject_long() {
        final long[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
    
        Assert.assertSame(
            ArrayUtils.EMPTY_LONG_OBJECT_ARRAY,
            ArrayUtils.toObject(new long[0]));
    
        Assert.assertTrue(
            Arrays.equals(
                new Long[] {
                    Long.valueOf(Long.MIN_VALUE),
                    Long.valueOf(Long.MAX_VALUE),
                    Long.valueOf(9999999)},
            ArrayUtils.toObject(
                new long[] { Long.MIN_VALUE, Long.MAX_VALUE, 9999999 })));
    }

    //  testToPrimitive/Object for float
    //  -----------------------------------------------------------------------
     @Test
     public void testToPrimitive_float() {
         final Float[] b = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
         
         Assert.assertSame(ArrayUtils.EMPTY_FLOAT_ARRAY,
            ArrayUtils.toPrimitive(new Float[0]));
         
         Assert.assertTrue(Arrays.equals(
             new float[] {Float.MIN_VALUE, Float.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Float[] {Float.valueOf(Float.MIN_VALUE), 
                 Float.valueOf(Float.MAX_VALUE), Float.valueOf(9999999)}))
         );

         try {
             ArrayUtils.toPrimitive(new Float[] {Float.valueOf(Float.MIN_VALUE), null});
             Assert.fail();
         } catch (final NullPointerException ex) {}
     }

     @Test
     public void testToPrimitive_float_float() {
         final Float[] l = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(l, Float.MIN_VALUE));
         
         Assert.assertSame(ArrayUtils.EMPTY_FLOAT_ARRAY,
         ArrayUtils.toPrimitive(new Float[0], 1));
         
         Assert.assertTrue(Arrays.equals(
             new float[] {Float.MIN_VALUE, Float.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Float[] {Float.valueOf(Float.MIN_VALUE), 
                 Float.valueOf(Float.MAX_VALUE), Float.valueOf(9999999)},1)));
         
         Assert.assertTrue(Arrays.equals(
             new float[] {Float.MIN_VALUE, Float.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Float[] {Float.valueOf(Float.MIN_VALUE), 
                 null, Float.valueOf(9999999)}, Float.MAX_VALUE))
         );
     }
     
    @Test
    public void testToObject_float() {
        final float[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
    
        Assert.assertSame(
            ArrayUtils.EMPTY_FLOAT_OBJECT_ARRAY,
            ArrayUtils.toObject(new float[0]));
    
        Assert.assertTrue(
            Arrays.equals(
                new Float[] {
                    Float.valueOf(Float.MIN_VALUE),
                    Float.valueOf(Float.MAX_VALUE),
                    Float.valueOf(9999999)},
            ArrayUtils.toObject(
                new float[] { Float.MIN_VALUE, Float.MAX_VALUE, 9999999 })));
    }

    //  testToPrimitive/Object for double
    //  -----------------------------------------------------------------------
     @Test
     public void testToPrimitive_double() {
         final Double[] b = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(b));
         
         Assert.assertSame(ArrayUtils.EMPTY_DOUBLE_ARRAY,
            ArrayUtils.toPrimitive(new Double[0]));
         
         Assert.assertTrue(Arrays.equals(
             new double[] {Double.MIN_VALUE, Double.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Double[] {Double.valueOf(Double.MIN_VALUE), 
                 Double.valueOf(Double.MAX_VALUE), Double.valueOf(9999999)}))
         );

         try {
             ArrayUtils.toPrimitive(new Float[] {Float.valueOf(Float.MIN_VALUE), null});
             Assert.fail();
         } catch (final NullPointerException ex) {}
     }

     @Test
     public void testToPrimitive_double_double() {
         final Double[] l = null;
         Assert.assertEquals(null, ArrayUtils.toPrimitive(l, Double.MIN_VALUE));
         
         Assert.assertSame(ArrayUtils.EMPTY_DOUBLE_ARRAY,
         ArrayUtils.toPrimitive(new Double[0], 1));
         
         Assert.assertTrue(Arrays.equals(
             new double[] {Double.MIN_VALUE, Double.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Double[] {Double.valueOf(Double.MIN_VALUE), 
                 Double.valueOf(Double.MAX_VALUE), Double.valueOf(9999999)},1)));
         
         Assert.assertTrue(Arrays.equals(
             new double[] {Double.MIN_VALUE, Double.MAX_VALUE, 9999999},
             ArrayUtils.toPrimitive(new Double[] {Double.valueOf(Double.MIN_VALUE), 
                 null, Double.valueOf(9999999)}, Double.MAX_VALUE))
         );
     }
     
    @Test
    public void testToObject_double() {
        final double[] b = null;
        Assert.assertArrayEquals(null, ArrayUtils.toObject(b));
    
        Assert.assertSame(
            ArrayUtils.EMPTY_DOUBLE_OBJECT_ARRAY,
            ArrayUtils.toObject(new double[0]));
    
        Assert.assertTrue(
            Arrays.equals(
                new Double[] {
                    Double.valueOf(Double.MIN_VALUE),
                    Double.valueOf(Double.MAX_VALUE),
                    Double.valueOf(9999999)},
            ArrayUtils.toObject(
                new double[] { Double.MIN_VALUE, Double.MAX_VALUE, 9999999 })));
    }

    //-----------------------------------------------------------------------
    /**
     * Test for {@link ArrayUtils#isEmpty(java.lang.Object[])}.
     */
    @Test
    public void testIsEmptyObject() {
        final Object[] emptyArray = new Object[] {};
        final Object[] notEmptyArray = new Object[] { new String("Value") };
        Assert.assertTrue(ArrayUtils.isEmpty((Object[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyArray));
    }

    /**
     * Tests for {@link ArrayUtils#isEmpty(long[])},
     * {@link ArrayUtils#isEmpty(int[])},
     * {@link ArrayUtils#isEmpty(short[])},
     * {@link ArrayUtils#isEmpty(char[])},
     * {@link ArrayUtils#isEmpty(byte[])},
     * {@link ArrayUtils#isEmpty(double[])},
     * {@link ArrayUtils#isEmpty(float[])} and
     * {@link ArrayUtils#isEmpty(boolean[])}.
     */
    @Test
    public void testIsEmptyPrimitives() {
        final long[] emptyLongArray = new long[] {};
        final long[] notEmptyLongArray = new long[] { 1L };
        Assert.assertTrue(ArrayUtils.isEmpty((long[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyLongArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyLongArray));

        final int[] emptyIntArray = new int[] {};
        final int[] notEmptyIntArray = new int[] { 1 };
        Assert.assertTrue(ArrayUtils.isEmpty((int[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyIntArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyIntArray));

        final short[] emptyShortArray = new short[] {};
        final short[] notEmptyShortArray = new short[] { 1 };
        Assert.assertTrue(ArrayUtils.isEmpty((short[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyShortArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyShortArray));

        final char[] emptyCharArray = new char[] {};
        final char[] notEmptyCharArray = new char[] { 1 };
        Assert.assertTrue(ArrayUtils.isEmpty((char[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyCharArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyCharArray));

        final byte[] emptyByteArray = new byte[] {};
        final byte[] notEmptyByteArray = new byte[] { 1 };
        Assert.assertTrue(ArrayUtils.isEmpty((byte[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyByteArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyByteArray));

        final double[] emptyDoubleArray = new double[] {};
        final double[] notEmptyDoubleArray = new double[] { 1.0 };
        Assert.assertTrue(ArrayUtils.isEmpty((double[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyDoubleArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyDoubleArray));

        final float[] emptyFloatArray = new float[] {};
        final float[] notEmptyFloatArray = new float[] { 1.0F };
        Assert.assertTrue(ArrayUtils.isEmpty((float[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyFloatArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyFloatArray));

        final boolean[] emptyBooleanArray = new boolean[] {};
        final boolean[] notEmptyBooleanArray = new boolean[] { true };
        Assert.assertTrue(ArrayUtils.isEmpty((boolean[])null));
        Assert.assertTrue(ArrayUtils.isEmpty(emptyBooleanArray));
        Assert.assertFalse(ArrayUtils.isEmpty(notEmptyBooleanArray));
    }
    
   /**
     * Test for {@link ArrayUtils#isNotEmpty(java.lang.Object[])}.
     */
    @Test
    public void testIsNotEmptyObject() {
        final Object[] emptyArray = new Object[] {};
        final Object[] notEmptyArray = new Object[] { new String("Value") };
        Assert.assertFalse(ArrayUtils.isNotEmpty((Object[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyArray));
    }

   /**
     * Tests for {@link ArrayUtils#isNotEmpty(long[])},
     * {@link ArrayUtils#isNotEmpty(int[])},
     * {@link ArrayUtils#isNotEmpty(short[])},
     * {@link ArrayUtils#isNotEmpty(char[])},
     * {@link ArrayUtils#isNotEmpty(byte[])},
     * {@link ArrayUtils#isNotEmpty(double[])},
     * {@link ArrayUtils#isNotEmpty(float[])} and
     * {@link ArrayUtils#isNotEmpty(boolean[])}.
     */
    @Test
    public void testIsNotEmptyPrimitives() {
        final long[] emptyLongArray = new long[] {};
        final long[] notEmptyLongArray = new long[] { 1L };
        Assert.assertFalse(ArrayUtils.isNotEmpty((long[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyLongArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyLongArray));

        final int[] emptyIntArray = new int[] {};
        final int[] notEmptyIntArray = new int[] { 1 };
        Assert.assertFalse(ArrayUtils.isNotEmpty((int[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyIntArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyIntArray));

        final short[] emptyShortArray = new short[] {};
        final short[] notEmptyShortArray = new short[] { 1 };
        Assert.assertFalse(ArrayUtils.isNotEmpty((short[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyShortArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyShortArray));

        final char[] emptyCharArray = new char[] {};
        final char[] notEmptyCharArray = new char[] { 1 };
        Assert.assertFalse(ArrayUtils.isNotEmpty((char[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyCharArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyCharArray));

        final byte[] emptyByteArray = new byte[] {};
        final byte[] notEmptyByteArray = new byte[] { 1 };
        Assert.assertFalse(ArrayUtils.isNotEmpty((byte[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyByteArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyByteArray));

        final double[] emptyDoubleArray = new double[] {};
        final double[] notEmptyDoubleArray = new double[] { 1.0 };
        Assert.assertFalse(ArrayUtils.isNotEmpty((double[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyDoubleArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyDoubleArray));

        final float[] emptyFloatArray = new float[] {};
        final float[] notEmptyFloatArray = new float[] { 1.0F };
        Assert.assertFalse(ArrayUtils.isNotEmpty((float[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyFloatArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyFloatArray));

        final boolean[] emptyBooleanArray = new boolean[] {};
        final boolean[] notEmptyBooleanArray = new boolean[] { true };
        Assert.assertFalse(ArrayUtils.isNotEmpty((boolean[])null));
        Assert.assertFalse(ArrayUtils.isNotEmpty(emptyBooleanArray));
        Assert.assertTrue(ArrayUtils.isNotEmpty(notEmptyBooleanArray));
    }
    // ------------------------------------------------------------------------
    @Test
    public void testGetLength() {
        Assert.assertEquals(0, ArrayUtils.getLength(null));
        
        final Object[] emptyObjectArray = new Object[0];
        final Object[] notEmptyObjectArray = new Object[] {"aValue"};
        Assert.assertEquals(0, ArrayUtils.getLength((Object[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyObjectArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyObjectArray));
 
        final int[] emptyIntArray = new int[] {};
        final int[] notEmptyIntArray = new int[] { 1 };
        Assert.assertEquals(0, ArrayUtils.getLength((int[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyIntArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyIntArray));

        final short[] emptyShortArray = new short[] {};
        final short[] notEmptyShortArray = new short[] { 1 };
        Assert.assertEquals(0, ArrayUtils.getLength((short[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyShortArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyShortArray));

        final char[] emptyCharArray = new char[] {};
        final char[] notEmptyCharArray = new char[] { 1 };
        Assert.assertEquals(0, ArrayUtils.getLength((char[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyCharArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyCharArray));

        final byte[] emptyByteArray = new byte[] {};
        final byte[] notEmptyByteArray = new byte[] { 1 };
        Assert.assertEquals(0, ArrayUtils.getLength((byte[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyByteArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyByteArray));

        final double[] emptyDoubleArray = new double[] {};
        final double[] notEmptyDoubleArray = new double[] { 1.0 };
        Assert.assertEquals(0, ArrayUtils.getLength((double[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyDoubleArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyDoubleArray));

        final float[] emptyFloatArray = new float[] {};
        final float[] notEmptyFloatArray = new float[] { 1.0F };
        Assert.assertEquals(0, ArrayUtils.getLength((float[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyFloatArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyFloatArray));

        final boolean[] emptyBooleanArray = new boolean[] {};
        final boolean[] notEmptyBooleanArray = new boolean[] { true };
        Assert.assertEquals(0, ArrayUtils.getLength((boolean[]) null));
        Assert.assertEquals(0, ArrayUtils.getLength(emptyBooleanArray));
        Assert.assertEquals(1, ArrayUtils.getLength(notEmptyBooleanArray));
        
        try {
            ArrayUtils.getLength("notAnArray");
            Assert.fail("IllegalArgumentException should have been thrown");
        } catch (final IllegalArgumentException e) {}
    }

    @Test
    public void testIsSorted() {
        Integer[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new Integer[]{1};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new Integer[]{1,2,3};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new Integer[]{1,3,2};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedComparator() {
        Comparator<Integer> c = new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }
            };

        Integer[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array, c));

        array = new Integer[]{1};
        Assert.assertTrue(ArrayUtils.isSorted(array, c));

        array = new Integer[]{3,2,1};
        Assert.assertTrue(ArrayUtils.isSorted(array, c));

        array = new Integer[]{1,3,2};
        Assert.assertFalse(ArrayUtils.isSorted(array, c));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsSortedNullComparator() throws Exception {
        ArrayUtils.isSorted(null, null);
    }

    @Test
    public void testIsSortedInt() {
        int[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new int[]{1};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new int[]{1,2,3};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new int[]{1,3,2};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedFloat() {
        float[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new float[]{0f};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new float[]{-1f, 0f, 0.1f, 0.2f};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new float[]{-1f, 0.2f, 0.1f, 0f};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedLong() {
        long[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new long[]{0L};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new long[]{-1L, 0L, 1L};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new long[]{-1L, 1L, 0L};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedDouble() {
        double[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new double[]{0.0};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new double[]{-1.0, 0.0, 0.1, 0.2};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new double[]{-1.0, 0.2, 0.1, 0.0};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedChar() {
        char[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new char[]{'a'};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new char[]{'a', 'b', 'c'};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new char[]{'a', 'c', 'b'};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedByte() {
        byte[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new byte[]{0x10};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new byte[]{0x10, 0x20, 0x30};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new byte[]{0x10, 0x30, 0x20};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedShort() {
        short[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new short[]{0};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new short[]{-1, 0, 1};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new short[]{-1, 1, 0};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testIsSortedBool() {
        boolean[] array = null;
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new boolean[]{true};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new boolean[]{false, true};
        Assert.assertTrue(ArrayUtils.isSorted(array));

        array = new boolean[]{true, false};
        Assert.assertFalse(ArrayUtils.isSorted(array));
    }

    @Test
    public void testCreatePrimitiveArray() {
        Assert.assertNull(ArrayUtils.toPrimitive((Object[])null));
        Assert.assertArrayEquals(new int[]{}, (int[]) ArrayUtils.toPrimitive(new Integer[]{}));
        Assert.assertArrayEquals(new short[]{2}, (short[]) ArrayUtils.toPrimitive(new Short[]{2}));
        Assert.assertArrayEquals(new long[]{2, 3}, (long[]) ArrayUtils.toPrimitive(new Long[]{2L, 3L}));
        Assert.assertArrayEquals(new float[]{3.14f}, (float[]) ArrayUtils.toPrimitive(new Float[]{3.14f}), 0.1f);
        Assert.assertArrayEquals(new double[]{2.718}, (double[]) ArrayUtils.toPrimitive(new Double[]{2.718}), 0.1);
    }

}
