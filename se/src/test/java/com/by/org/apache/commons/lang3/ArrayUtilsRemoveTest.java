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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Tests ArrayUtils remove and removeElement methods.
 */
public class ArrayUtilsRemoveTest {

    @Test
    public void testRemoveObjectArray() {
        Object[] array;
        array = ArrayUtils.remove(new Object[] {"a"}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 0);
        Assert.assertTrue(Arrays.equals(new Object[] {"b"}, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.remove(new Object[] {"a", "b"}, 1);
        Assert.assertTrue(Arrays.equals(new Object[] {"a"}, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.remove(new Object[] {"a", "b", "c"}, 1);
        Assert.assertTrue(Arrays.equals(new Object[] {"a", "c"}, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new Object[] {"a", "b"}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new Object[] {"a", "b"}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((Object[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }

    @Test
    public void testRemoveNumberArray(){
        final Number[] inarray = {Integer.valueOf(1),Long.valueOf(2),Byte.valueOf((byte) 3)};
        Assert.assertEquals(3, inarray.length);
        Number[] outarray;
        outarray = ArrayUtils.remove(inarray, 1);
        Assert.assertEquals(2, outarray.length);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
        outarray = ArrayUtils.remove(outarray, 1);
        Assert.assertEquals(1, outarray.length);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
        outarray = ArrayUtils.remove(outarray, 0);
        Assert.assertEquals(0, outarray.length);
        Assert.assertEquals(Number.class, outarray.getClass().getComponentType());
    }

    @Test
    public void testRemoveBooleanArray() {
        boolean[] array;
        array = ArrayUtils.remove(new boolean[] {true}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new boolean[] {true, false}, 0);
        Assert.assertTrue(Arrays.equals(new boolean[] {false}, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new boolean[] {true, false}, 1);
        Assert.assertTrue(Arrays.equals(new boolean[] {true}, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new boolean[] {true, false, true}, 1);
        Assert.assertTrue(Arrays.equals(new boolean[] {true, true}, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new boolean[] {true, false}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new boolean[] {true, false}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((boolean[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveByteArray() {
        byte[] array;
        array = ArrayUtils.remove(new byte[] {1}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new byte[] {1, 2}, 0);
        Assert.assertTrue(Arrays.equals(new byte[] {2}, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new byte[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new byte[] {1}, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new byte[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new byte[] {1, 1}, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new byte[] {1, 2}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new byte[] {1, 2}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((byte[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveCharArray() {
        char[] array;
        array = ArrayUtils.remove(new char[] {'a'}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 0);
        Assert.assertTrue(Arrays.equals(new char[] {'b'}, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new char[] {'a', 'b'}, 1);
        Assert.assertTrue(Arrays.equals(new char[] {'a'}, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new char[] {'a', 'b', 'c'}, 1);
        Assert.assertTrue(Arrays.equals(new char[] {'a', 'c'}, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new char[] {'a', 'b'}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new char[] {'a', 'b'}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((char[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveDoubleArray() {
        double[] array;
        array = ArrayUtils.remove(new double[] {1}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new double[] {1, 2}, 0);
        Assert.assertTrue(Arrays.equals(new double[] {2}, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new double[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new double[] {1}, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new double[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new double[] {1, 1}, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new double[] {1, 2}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new double[] {1, 2}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((double[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveFloatArray() {
        float[] array;
        array = ArrayUtils.remove(new float[] {1}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new float[] {1, 2}, 0);
        Assert.assertTrue(Arrays.equals(new float[] {2}, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new float[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new float[] {1}, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new float[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new float[] {1, 1}, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new float[] {1, 2}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new float[] {1, 2}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((float[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveIntArray() {
        int[] array;
        array = ArrayUtils.remove(new int[] {1}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new int[] {1, 2}, 0);
        Assert.assertTrue(Arrays.equals(new int[] {2}, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new int[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new int[] {1}, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new int[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new int[] {1, 1}, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new int[] {1, 2}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new int[] {1, 2}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((int[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveLongArray() {
        long[] array;
        array = ArrayUtils.remove(new long[] {1}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new long[] {1, 2}, 0);
        Assert.assertTrue(Arrays.equals(new long[] {2}, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new long[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new long[] {1}, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new long[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new long[] {1, 1}, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new long[] {1, 2}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new long[] {1, 2}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((long[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveShortArray() {
        short[] array;
        array = ArrayUtils.remove(new short[] {1}, 0);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new short[] {1, 2}, 0);
        Assert.assertTrue(Arrays.equals(new short[] {2}, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new short[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new short[] {1}, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.remove(new short[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new short[] {1, 1}, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        try {
            ArrayUtils.remove(new short[] {1, 2}, -1);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove(new short[] {1, 2}, 2);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
        try {
            ArrayUtils.remove((short[]) null, 0);
            Assert.fail("IndexOutOfBoundsException expected");
        } catch (final IndexOutOfBoundsException e) {}
    }
    
    @Test
    public void testRemoveElementObjectArray() {
        Object[] array;
        array = ArrayUtils.removeElement((Object[]) null, "a");
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_OBJECT_ARRAY, "a");
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new Object[] {"a"}, "a");
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_OBJECT_ARRAY, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new Object[] {"a", "b"}, "a");
        Assert.assertTrue(Arrays.equals(new Object[] {"b"}, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new Object[] {"a", "b", "a"}, "a");
        Assert.assertTrue(Arrays.equals(new Object[] {"b", "a"}, array));
        Assert.assertEquals(Object.class, array.getClass().getComponentType());
    }
    
    @Test
    public void testRemoveElementBooleanArray() {
        boolean[] array;
        array = ArrayUtils.removeElement((boolean[]) null, true);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BOOLEAN_ARRAY, true);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new boolean[] {true}, true);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new boolean[] {true, false}, true);
        Assert.assertTrue(Arrays.equals(new boolean[] {false}, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new boolean[] {true, false, true}, true);
        Assert.assertTrue(Arrays.equals(new boolean[] {false, true}, array));
        Assert.assertEquals(Boolean.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    public void testRemoveElementByteArray() {
        byte[] array;
        array = ArrayUtils.removeElement((byte[]) null, (byte) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_BYTE_ARRAY, (byte) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new byte[] {1}, (byte) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new byte[] {1, 2}, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] {2}, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new byte[] {1, 2, 1}, (byte) 1);
        Assert.assertTrue(Arrays.equals(new byte[] {2, 1}, array));
        Assert.assertEquals(Byte.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    public void testRemoveElementCharArray() {
        char[] array;
        array = ArrayUtils.removeElement((char[]) null, 'a');
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_CHAR_ARRAY, 'a');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new char[] {'a'}, 'a');
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new char[] {'a', 'b'}, 'a');
        Assert.assertTrue(Arrays.equals(new char[] {'b'}, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new char[] {'a', 'b', 'a'}, 'a');
        Assert.assertTrue(Arrays.equals(new char[] {'b', 'a'}, array));
        Assert.assertEquals(Character.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementDoubleArray() {
        double[] array;
        array = ArrayUtils.removeElement((double[]) null, (double) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_DOUBLE_ARRAY, (double) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new double[] {1}, (double) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new double[] {1, 2}, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] {2}, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new double[] {1, 2, 1}, (double) 1);
        Assert.assertTrue(Arrays.equals(new double[] {2, 1}, array));
        Assert.assertEquals(Double.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementFloatArray() {
        float[] array;
        array = ArrayUtils.removeElement((float[]) null, (float) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_FLOAT_ARRAY, (float) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new float[] {1}, (float) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new float[] {1, 2}, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] {2}, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new float[] {1, 2, 1}, (float) 1);
        Assert.assertTrue(Arrays.equals(new float[] {2, 1}, array));
        Assert.assertEquals(Float.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    public void testRemoveElementIntArray() {
        int[] array;
        array = ArrayUtils.removeElement((int[]) null, 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_INT_ARRAY, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new int[] {1}, 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new int[] {1, 2}, 1);
        Assert.assertTrue(Arrays.equals(new int[] {2}, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new int[] {1, 2, 1}, 1);
        Assert.assertTrue(Arrays.equals(new int[] {2, 1}, array));
        Assert.assertEquals(Integer.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    @SuppressWarnings("cast")
    public void testRemoveElementLongArray() {
        long[] array;
        array = ArrayUtils.removeElement((long[]) null, (long) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_LONG_ARRAY, (long) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new long[] {1}, (long) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new long[] {1, 2}, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] {2}, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new long[] {1, 2, 1}, (long) 1);
        Assert.assertTrue(Arrays.equals(new long[] {2, 1}, array));
        Assert.assertEquals(Long.TYPE, array.getClass().getComponentType());
    }
    
    @Test
    public void testRemoveElementShortArray() {
        short[] array;
        array = ArrayUtils.removeElement((short[]) null, (short) 1);
        Assert.assertNull(array);
        array = ArrayUtils.removeElement(ArrayUtils.EMPTY_SHORT_ARRAY, (short) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new short[] {1}, (short) 1);
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new short[] {1, 2}, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] {2}, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
        array = ArrayUtils.removeElement(new short[] {1, 2, 1}, (short) 1);
        Assert.assertTrue(Arrays.equals(new short[] {2, 1}, array));
        Assert.assertEquals(Short.TYPE, array.getClass().getComponentType());
    }
    

    @Test
    public void testRemoveAllBooleanOccurences() {
        boolean[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, true));

        a = new boolean[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurences(a, true)));

        a = new boolean[] { true };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurences(a, true)));

        a = new boolean[] { true, true };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BOOLEAN_ARRAY, ArrayUtils.removeAllOccurences(a, true)));

        a = new boolean[] { false, true, true, false, true };
        Assert.assertTrue(Arrays.equals(new boolean[] { false, false }, ArrayUtils.removeAllOccurences(a, true)));

        a = new boolean[] { false, true, true, false, true };
        Assert.assertTrue(Arrays.equals(new boolean[] { true, true, true }, ArrayUtils.removeAllOccurences(a, false)));
    }

    @Test
    public void testRemoveAllCharOccurences() {
        char[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, '2'));

        a = new char[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurences(a, '2')));

        a = new char[] { '2' };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurences(a, '2')));

        a = new char[] { '2', '2' };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_CHAR_ARRAY, ArrayUtils.removeAllOccurences(a, '2')));

        a = new char[] { '1', '2', '2', '3', '2' };
        Assert.assertTrue(Arrays.equals(new char[] { '1', '3' }, ArrayUtils.removeAllOccurences(a, '2')));

        a = new char[] { '1', '2', '2', '3', '2' };
        Assert.assertTrue(Arrays.equals(new char[] { '1', '2', '2', '3', '2' }, ArrayUtils.removeAllOccurences(a, '4')));
    }
    
    @Test
    public void testRemoveAllByteOccurences() {
        byte[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, (byte) 2));

        a = new byte[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurences(a, (byte) 2)));

        a = new byte[] { 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurences(a, (byte) 2)));

        a = new byte[] { 2, 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_BYTE_ARRAY, ArrayUtils.removeAllOccurences(a, (byte) 2)));

        a = new byte[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new byte[] { 1, 3 }, ArrayUtils.removeAllOccurences(a, (byte) 2)));

        a = new byte[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new byte[] { 1, 2, 2, 3, 2 }, ArrayUtils.removeAllOccurences(a, (byte) 4)));
    }

    @Test
    public void testRemoveAllShortOccurences() {
        short[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, (short) 2));

        a = new short[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurences(a, (short) 2)));

        a = new short[] { 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurences(a, (short) 2)));

        a = new short[] { 2, 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_SHORT_ARRAY, ArrayUtils.removeAllOccurences(a, (short) 2)));

        a = new short[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new short[] { 1, 3 }, ArrayUtils.removeAllOccurences(a, (short) 2)));

        a = new short[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new short[] { 1, 2, 2, 3, 2 }, ArrayUtils.removeAllOccurences(a, (short) 4)));
    }

    @Test
    public void testRemoveAllIntOccurences() {        
        int[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, 2));

        a = new int[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new int[] { 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new int[] { 2, 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_INT_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new int[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new int[] { 1, 3 }, ArrayUtils.removeAllOccurences(a, 2)));

        a = new int[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new int[] { 1, 2, 2, 3, 2 }, ArrayUtils.removeAllOccurences(a, 4)));
    }    
    
    @Test
    public void testRemoveAllLongOccurences() {        
        long[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, 2));

        a = new long[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new long[] { 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new long[] { 2, 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_LONG_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new long[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new long[] { 1, 3 }, ArrayUtils.removeAllOccurences(a, 2)));

        a = new long[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new long[] { 1, 2, 2, 3, 2 }, ArrayUtils.removeAllOccurences(a, 4)));
    }

    @Test
    public void testRemoveAllFloatOccurences() {    
        float[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, 2));

        a = new float[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new float[] { 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new float[] { 2, 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_FLOAT_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new float[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new float[] { 1, 3 }, ArrayUtils.removeAllOccurences(a, 2)));

        a = new float[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new float[] { 1, 2, 2, 3, 2 }, ArrayUtils.removeAllOccurences(a, 4)));
    }

    @Test
    public void testRemoveAllDoubleOccurences() {    
        double[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, 2));

        a = new double[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new double[] { 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new double[] { 2, 2 };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_DOUBLE_ARRAY, ArrayUtils.removeAllOccurences(a, 2)));

        a = new double[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new double[] { 1, 3 }, ArrayUtils.removeAllOccurences(a, 2)));

        a = new double[] { 1, 2, 2, 3, 2 };
        Assert.assertTrue(Arrays.equals(new double[] { 1, 2, 2, 3, 2 }, ArrayUtils.removeAllOccurences(a, 4)));
    }

    @Test
    public void testRemoveAllObjectOccurences() {    
        String[] a = null;
        Assert.assertNull(ArrayUtils.removeAllOccurences(a, "2"));

        a = new String[0];
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurences(a, "2")));

        a = new String[] { "2" };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurences(a, "2")));

        a = new String[] { "2", "2" };
        Assert.assertTrue(Arrays.equals(ArrayUtils.EMPTY_STRING_ARRAY, ArrayUtils.removeAllOccurences(a, "2")));

        a = new String[] { "1", "2", "2", "3", "2" };
        Assert.assertTrue(Arrays.equals(new String[] { "1", "3" }, ArrayUtils.removeAllOccurences(a, "2")));

        a = new String[] { "1", "2", "2", "3", "2" };
        Assert.assertTrue(Arrays.equals(new String[] { "1", "2", "2", "3", "2" }, ArrayUtils.removeAllOccurences(a, "4")));
    }
}
