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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Tests ArrayUtils remove and removeElement methods.
 */
public class ArrayUtilsRemoveMultipleTest {

    @Test
    public void testRemoveAllObjectArray() {
        Object[] array;
        array = ArrayUtils.removeAll(new Object[] { "a" }, 0);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b" }, 0, 1);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c" }, 1, 2);
        Assert.assertArrayEquals(new Object[] { "a" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 1, 2);
        Assert.assertArrayEquals(new Object[] { "a", "d" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 3);
        Assert.assertArrayEquals(new Object[] { "b", "c" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3);
        Assert.assertArrayEquals(new Object[] { "c" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 1, 3);
        Assert.assertArrayEquals(new Object[] { "c", "e" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d", "e" }, 0, 2, 4);
        Assert.assertArrayEquals(new Object[] { "b", "d" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 0, 1, 3, 0, 1, 3);
        Assert.assertArrayEquals(new Object[] { "c" }, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 1, 0, 3);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new Object[] { "a", "b", "c", "d" }, 2, 0, 1, 3, 0, 2, 1, 3);
        Assert.assertArrayEquals(ArrayUtils.EMPTY_OBJECT_ARRAY, array);
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllObjectArrayRemoveNone() {
        final Object[] array1 = new Object[] { "foo", "bar", "baz" };
        final Object[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertArrayEquals(array1, array2);
        Assert.assertEquals(Object.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllObjectArrayNegativeIndex() {
        ArrayUtils.removeAll(new Object[] { "a", "b" }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllObjectArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new Object[] { "a", "b" }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullObjectArray() {
        ArrayUtils.remove((Object[]) null, 0);
    }

    @Test
    public void testRemoveAllNumberArray() {
        final Number[] inarray = { Integer.valueOf(1), Long.valueOf(2L), Byte.valueOf((byte) 3) };
        Assert.assertEquals(3, inarray.length);
        Number[] outarray;
        outarray = ArrayUtils.removeAll(inarray, 1);
        Assert.assertArrayEquals(new Number[] { Integer.valueOf(1), Byte.valueOf((byte) 3) }, outarray);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
        outarray = ArrayUtils.removeAll(outarray, 1);
        Assert.assertArrayEquals(new Number[] { Integer.valueOf(1) }, outarray);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
        outarray = ArrayUtils.removeAll(outarray, 0);
        Assert.assertEquals(0, outarray.length);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());

        outarray = ArrayUtils.removeAll(inarray, 0, 1);
        Assert.assertArrayEquals(new Number[] { Byte.valueOf((byte) 3) }, outarray);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
        outarray = ArrayUtils.removeAll(inarray, 0, 2);
        Assert.assertArrayEquals(new Number[] { Long.valueOf(2L) }, outarray);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
        outarray = ArrayUtils.removeAll(inarray, 1, 2);
        Assert.assertArrayEquals(new Number[] { Integer.valueOf(1) }, outarray);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArray() {
        boolean[] array;
        array = ArrayUtils.removeAll(new boolean[] { true }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false }, 1);
        Assert.assertTrue(Arrays.equals(new boolean[] { true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true }, 1);
        Assert.assertTrue(Arrays.equals(new boolean[] { true, true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new boolean[] { true, false }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 1);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 0, 2);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, false }, 1, 2);
        Assert.assertTrue(Arrays.equals(new boolean[] { true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new boolean[] { false, false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3);
        Assert.assertTrue(Arrays.equals(new boolean[] { true, true, true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true }, 1, 3, 4);
        Assert.assertTrue(Arrays.equals(new boolean[] { true, true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new boolean[] { false, false, false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new boolean[] { true, true, true, true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new boolean[] { true, false, true, false, true, false, true }, 0, 1, 2);
        Assert.assertTrue(Arrays.equals(new boolean[] { false, true, false, true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllBooleanArrayRemoveNone() {
        final boolean[] array1 = new boolean[] { true, false };
        final boolean[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertTrue(Arrays.equals(array1, array2));
        Assert.assertEquals(boolean.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllBooleanArrayNegativeIndex() {
        ArrayUtils.removeAll(new boolean[] { true, false }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllBooleanArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new boolean[] { true, false }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullBooleanArray() {
        ArrayUtils.removeAll((boolean[]) null, 0);
    }

    @Test
    public void testRemoveAllByteArray() {
        byte[] array;
        array = ArrayUtils.removeAll(new byte[] { 1 }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0);
        Assert.assertTrue(Arrays.equals(new byte[] { 2 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 1 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 1, 1 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new byte[] { 1, 2 }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 3 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new byte[] { 1 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3 }, 0, 2);
        Assert.assertTrue(Arrays.equals(new byte[] { 2 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 1, 3);
        Assert.assertTrue(Arrays.equals(new byte[] { 1, 3, 5 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new byte[] { 2, 4 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new byte[] { 1, 3, 5, 7 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new byte[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new byte[] { 2, 4, 6 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllByteArrayRemoveNone() {
        final byte[] array1 = new byte[] { 1, 2 };
        final byte[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertArrayEquals(array1, array2);
        Assert.assertEquals(byte.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllByteArrayNegativeIndex() {
        ArrayUtils.removeAll(new byte[] { 1, 2 }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllByteArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new byte[] { 1, 2 }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullByteArray() {
        ArrayUtils.removeAll((byte[]) null, 0);
    }

    @Test
    public void testRemoveAllCharArray() {
        char[] array;
        array = ArrayUtils.removeAll(new char[] { 'a' }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0);
        Assert.assertTrue(Arrays.equals(new char[] { 'b' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 1);
        Assert.assertTrue(Arrays.equals(new char[] { 'a' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1);
        Assert.assertTrue(Arrays.equals(new char[] { 'a', 'c' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new char[] { 'a', 'b' }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 1);
        Assert.assertTrue(Arrays.equals(new char[] { 'c' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 1, 2);
        Assert.assertTrue(Arrays.equals(new char[] { 'a' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c' }, 0, 2);
        Assert.assertTrue(Arrays.equals(new char[] { 'b' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 1, 3);
        Assert.assertTrue(Arrays.equals(new char[] { 'a', 'c', 'e' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e' }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new char[] { 'b', 'd' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new char[] { 'a', 'c', 'e', 'g' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g' }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new char[] { 'b', 'd', 'f' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllCharArrayRemoveNone() {
        final char[] array1 = new char[] { 'a', 'b' };
        final char[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertArrayEquals(array1, array2);
        Assert.assertEquals(char.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllCharArrayNegativeIndex() {
        ArrayUtils.removeAll(new char[] { 'a', 'b' }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllCharArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new char[] { 'a', 'b' }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullCharArray() {
        ArrayUtils.removeAll((char[]) null, 0);
    }

    @Test
    public void testRemoveAllDoubleArray() {
        double[] array;
        array = ArrayUtils.removeAll(new double[] { 1 }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0);
        Assert.assertTrue(Arrays.equals(new double[] { 2 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new double[] { 1 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new double[] { 1, 1 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new double[] { 1, 2 }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 1);
        Assert.assertTrue(Arrays.equals(new double[] { 3 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new double[] { 1 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3 }, 0, 2);
        Assert.assertTrue(Arrays.equals(new double[] { 2 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 1, 3);
        Assert.assertTrue(Arrays.equals(new double[] { 1, 3, 5 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new double[] { 2, 4 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new double[] { 1, 3, 5, 7 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new double[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new double[] { 2, 4, 6 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllDoubleArrayRemoveNone() {
        final double[] array1 = new double[] { 1, 2 };
        final double[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertTrue(Arrays.equals(array1, array2));
        Assert.assertEquals(double.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllDoubleArrayNegativeIndex() {
        ArrayUtils.removeAll(new double[] { 1, 2 }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllDoubleArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new double[] { 1, 2 }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullDoubleArray() {
        ArrayUtils.removeAll((double[]) null, 0);
    }

    @Test
    public void testRemoveAllFloatArray() {
        float[] array;
        array = ArrayUtils.removeAll(new float[] { 1 }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0);
        Assert.assertTrue(Arrays.equals(new float[] { 2 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new float[] { 1 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new float[] { 1, 1 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new float[] { 1, 2 }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 1);
        Assert.assertTrue(Arrays.equals(new float[] { 3 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new float[] { 1 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3 }, 0, 2);
        Assert.assertTrue(Arrays.equals(new float[] { 2 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 1, 3);
        Assert.assertTrue(Arrays.equals(new float[] { 1, 3, 5 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new float[] { 2, 4 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new float[] { 1, 3, 5, 7 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new float[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new float[] { 2, 4, 6 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllFloatArrayRemoveNone() {
        final float[] array1 = new float[] { 1, 2 };
        final float[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertTrue(Arrays.equals(array1, array2));
        Assert.assertEquals(float.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllFloatArrayNegativeIndex() {
        ArrayUtils.removeAll(new float[] { 1, 2 }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllFloatArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new float[] { 1, 2 }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullFloatArray() {
        ArrayUtils.removeAll((float[]) null, 0);
    }

    @Test
    public void testRemoveAllIntArray() {
        int[] array;
        array = ArrayUtils.removeAll(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.EMPTY_INT_ARRAY);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        array = ArrayUtils.removeAll(new int[] { 1 }, ArrayUtils.EMPTY_INT_ARRAY);
        Assert.assertTrue(Arrays.equals(new int[] { 1 }, array));
        array = ArrayUtils.removeAll(new int[] { 1 }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0);
        Assert.assertTrue(Arrays.equals(new int[] { 2 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 1 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 1, 1 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new int[] { 1, 2 }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 3 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new int[] { 1 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3 }, 0, 2);
        Assert.assertTrue(Arrays.equals(new int[] { 2 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 1, 3);
        Assert.assertTrue(Arrays.equals(new int[] { 1, 3, 5 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new int[] { 2, 4 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new int[] { 1, 3, 5, 7 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new int[] { 2, 4, 6 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllIntArrayRemoveNone() {
        final int[] array1 = new int[] { 1, 2 };
        final int[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertArrayEquals(array1, array2);
        Assert.assertEquals(int.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllIntArrayNegativeIndex() {
        ArrayUtils.removeAll(new int[] { 1, 2 }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllIntArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new int[] { 1, 2 }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullIntArray() {
        ArrayUtils.removeAll((int[]) null, 0);
    }

    @Test
    public void testRemoveAllLongArray() {
        long[] array;
        array = ArrayUtils.removeAll(new long[] { 1 }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0);
        Assert.assertTrue(Arrays.equals(new long[] { 2 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new long[] { 1 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new long[] { 1, 1 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new long[] { 1, 2 }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 1);
        Assert.assertTrue(Arrays.equals(new long[] { 3 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new long[] { 1 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3 }, 0, 2);
        Assert.assertTrue(Arrays.equals(new long[] { 2 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 1, 3);
        Assert.assertTrue(Arrays.equals(new long[] { 1, 3, 5 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new long[] { 2, 4 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new long[] { 1, 3, 5, 7 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new long[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new long[] { 2, 4, 6 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllLongArrayRemoveNone() {
        final long[] array1 = new long[] { 1, 2 };
        final long[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertArrayEquals(array1, array2);
        Assert.assertEquals(long.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllLongArrayNegativeIndex() {
        ArrayUtils.removeAll(new long[] { 1, 2 }, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllLongArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new long[] { 1, 2 }, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullLongArray() {
        ArrayUtils.removeAll((long[]) null, 0);
    }

    @Test
    public void testRemoveAllShortArray() {
        short[] array;
        array = ArrayUtils.removeAll(new short[] { 1 }, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0);
        Assert.assertTrue(Arrays.equals(new short[] { 2 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new short[] { 1 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new short[] { 1, 1 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeAll(new short[] { 1, 2 }, 0, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 1);
        Assert.assertTrue(Arrays.equals(new short[] { 3 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new short[] { 1 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3 }, 0, 2);
        Assert.assertTrue(Arrays.equals(new short[] { 2 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 1, 3);
        Assert.assertTrue(Arrays.equals(new short[] { 1, 3, 5 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5 }, 0, 2, 4);
        Assert.assertTrue(Arrays.equals(new short[] { 2, 4 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 1, 3, 5);
        Assert.assertTrue(Arrays.equals(new short[] { 1, 3, 5, 7 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeAll(new short[] { 1, 2, 3, 4, 5, 6, 7 }, 0, 2, 4, 6);
        Assert.assertTrue(Arrays.equals(new short[] { 2, 4, 6 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveAllShortArrayRemoveNone() {
        final short[] array1 = new short[] { 1, 2 };
        final short[] array2 = ArrayUtils.removeAll(array1);
        Assert.assertNotSame(array1, array2);
        Assert.assertArrayEquals(array1, array2);
        Assert.assertEquals(short.class, array2.getClass().getComponentType());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllShortArrayNegativeIndex() {
        ArrayUtils.removeAll(new short[] { 1, 2 }, -1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllShortArrayOutOfBoundsIndex() {
        ArrayUtils.removeAll(new short[] { 1, 2 }, 2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAllNullShortArray() {
        ArrayUtils.removeAll((short[]) null, 0);
    }

    @Test
    public void testRemoveElementsObjectArray() {
        Object[] array;
        array = ArrayUtils.removeElements((Object[]) null, "a");
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a" }, "a");
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a");
        Assert.assertTrue(Arrays.equals(new Object[] { "b" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        Assert.assertTrue(Arrays.equals(new Object[] { "b", "a" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((Object[]) null, "a", "b");
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_OBJECT_ARRAY, "a", "b");
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a" }, "a", "b");
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b" }, "a", "c");
        Assert.assertTrue(Arrays.equals(new Object[] { "b" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a");
        Assert.assertTrue(Arrays.equals(new Object[] { "b", "a" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "b");
        Assert.assertTrue(Arrays.equals(new Object[] { "a" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a");
        Assert.assertTrue(Arrays.equals(new Object[] { "b" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new Object[] { "a", "b", "a" }, "a", "a", "a", "a");
        Assert.assertTrue(Arrays.equals(new Object[] { "b" }, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementBooleanArray() {
        boolean[] array;
        array = ArrayUtils.removeElements((boolean[]) null, true);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true }, true);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false }, true);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true);
        Assert.assertTrue(Arrays.equals(new boolean[] { false, true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((boolean[]) null, true, false);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true, false);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true }, true, false);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, false);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false }, true, true);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, false);
        Assert.assertTrue(Arrays.equals(new boolean[] { true }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new boolean[] { true, false, true }, true, true, true, true);
        Assert.assertTrue(Arrays.equals(new boolean[] { false }, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementByteArray() {
        byte[] array;
        array = ArrayUtils.removeElements((byte[]) null, (byte) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 2 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 2, 1 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((byte[]) null, (byte) 1, (byte) 2);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1, (byte) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1 }, (byte) 1, (byte) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2 }, (byte) 1, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 2 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 2);
        Assert.assertTrue(Arrays.equals(new byte[] { 1 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 2 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new byte[] { 1, 2, 1 }, (byte) 1, (byte) 1, (byte) 1, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] { 2 }, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementCharArray() {
        char[] array;
        array = ArrayUtils.removeElements((char[]) null, 'a');
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a');
        Assert.assertTrue(Arrays.equals(new char[] { 'b' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a');
        Assert.assertTrue(Arrays.equals(new char[] { 'b', 'a' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((char[]) null, 'a', 'b');
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_CHAR_ARRAY, 'a', 'b');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a' }, 'a', 'b');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'b');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b' }, 'a', 'a');
        Assert.assertTrue(Arrays.equals(new char[] { 'b' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'b');
        Assert.assertTrue(Arrays.equals(new char[] { 'a' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a');
        Assert.assertTrue(Arrays.equals(new char[] { 'b' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new char[] { 'a', 'b', 'a' }, 'a', 'a', 'a', 'a');
        Assert.assertTrue(Arrays.equals(new char[] { 'b' }, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray() {
        double[] array;
        array = ArrayUtils.removeElements((double[]) null, (double) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] { 2 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] { 2, 1 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((double[]) null, (double) 1, (double) 2);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1, (double) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1 }, (double) 1, (double) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2 }, (double) 1, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] { 2 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 2);
        Assert.assertTrue(Arrays.equals(new double[] { 1 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] { 2 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new double[] { 1, 2, 1 }, (double) 1, (double) 1, (double) 1, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] { 2 }, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray() {
        float[] array;
        array = ArrayUtils.removeElements((float[]) null, (float) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] { 2 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] { 2, 1 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((float[]) null, (float) 1, (float) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1, (float) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1 }, (float) 1, (float) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2 }, (float) 1, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] { 2 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] { 2 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 2);
        Assert.assertTrue(Arrays.equals(new float[] { 1 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new float[] { 1, 2, 1 }, (float) 1, (float) 1, (float) 1, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] { 2 }, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementIntArray() {
        int[] array;
        array = ArrayUtils.removeElements((int[]) null, 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1 }, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 2 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 2, 1 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((int[]) null, 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_INT_ARRAY, 1, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1 }, 1, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2 }, 1, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 2 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 2);
        Assert.assertTrue(Arrays.equals(new int[] { 1 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 2 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new int[] { 1, 2, 1 }, 1, 1, 1, 1);
        Assert.assertTrue(Arrays.equals(new int[] { 2 }, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }

    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray() {
        long[] array;
        array = ArrayUtils.removeElements((long[]) null, (long) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, (long) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1 }, (long) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2 }, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] { 2 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] { 2, 1 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((long[]) null, (long) 1, (long) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_LONG_ARRAY, (long) 1, (long) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1 }, (long) 1, (long) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2 }, (long) 1, (long) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2 }, (long) 1, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] { 2 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, (long) 1, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] { 2 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, (long) 1, (long) 2);
        Assert.assertTrue(Arrays.equals(new long[] { 1 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new long[] { 1, 2, 1 }, (long) 1, (long) 1, (long) 1, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] { 2 }, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
    }

    @Test
    public void testRemoveElementShortArray() {
        short[] array;
        array = ArrayUtils.removeElements((short[]) null, (short) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] { 2 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] { 2, 1 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());

        array = ArrayUtils.removeElements((short[]) null, (short) 1, (short) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElements(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1, (short) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1 }, (short) 1, (short) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 2);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2 }, (short) 1, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] { 2 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] { 2 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 2);
        Assert.assertTrue(Arrays.equals(new short[] { 1 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElements(new short[] { 1, 2, 1 }, (short) 1, (short) 1, (short) 1, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] { 2 }, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
    }

}
