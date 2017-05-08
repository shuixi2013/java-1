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

import static org.apache.commons.lang3.JavaVersion.JAVA_1_5;
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
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ClassUtils.Interfaces;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.reflect.testbed.GenericConsumer;
import org.apache.commons.lang3.reflect.testbed.GenericParent;
import org.apache.commons.lang3.reflect.testbed.StringParameterizedChild;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.ClassUtils}.
 */
@SuppressWarnings("boxing") // JUnit4 does not support primitive equality testing apart from long
public class ClassUtilsTest  {

    private static class Inner {
        private class DeeplyNested{}
    }

    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new ClassUtils());
        final Constructor<?>[] cons = ClassUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(ClassUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(ClassUtils.class.getModifiers()));
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_getShortClassName_Object() {
        Assert.assertEquals("ClassUtils", ClassUtils.getShortClassName(new ClassUtils(), "<null>"));
        Assert.assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(new Inner(), "<null>"));
        Assert.assertEquals("String", ClassUtils.getShortClassName("hello", "<null>"));
        Assert.assertEquals("<null>", ClassUtils.getShortClassName(null, "<null>"));

        // Inner types
        class Named {}
        Assert.assertEquals("ClassUtilsTest.1", ClassUtils.getShortClassName(new Object(){}, "<null>"));
        Assert.assertEquals("ClassUtilsTest.1Named", ClassUtils.getShortClassName(new Named(), "<null>"));
        Assert.assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(new Inner(), "<null>"));
    }

    @Test
    public void test_getShortClassName_Class() {
        Assert.assertEquals("ClassUtils", ClassUtils.getShortClassName(ClassUtils.class));
        Assert.assertEquals("Map.Entry", ClassUtils.getShortClassName(Map.Entry.class));
        Assert.assertEquals("", ClassUtils.getShortClassName((Class<?>) null));

        // LANG-535
        Assert.assertEquals("String[]", ClassUtils.getShortClassName(String[].class));
        Assert.assertEquals("Map.Entry[]", ClassUtils.getShortClassName(Map.Entry[].class));

        // Primitives
        Assert.assertEquals("boolean", ClassUtils.getShortClassName(boolean.class));
        Assert.assertEquals("byte", ClassUtils.getShortClassName(byte.class));
        Assert.assertEquals("char", ClassUtils.getShortClassName(char.class));
        Assert.assertEquals("short", ClassUtils.getShortClassName(short.class));
        Assert.assertEquals("int", ClassUtils.getShortClassName(int.class));
        Assert.assertEquals("long", ClassUtils.getShortClassName(long.class));
        Assert.assertEquals("float", ClassUtils.getShortClassName(float.class));
        Assert.assertEquals("double", ClassUtils.getShortClassName(double.class));

        // Primitive Arrays
        Assert.assertEquals("boolean[]", ClassUtils.getShortClassName(boolean[].class));
        Assert.assertEquals("byte[]", ClassUtils.getShortClassName(byte[].class));
        Assert.assertEquals("char[]", ClassUtils.getShortClassName(char[].class));
        Assert.assertEquals("short[]", ClassUtils.getShortClassName(short[].class));
        Assert.assertEquals("int[]", ClassUtils.getShortClassName(int[].class));
        Assert.assertEquals("long[]", ClassUtils.getShortClassName(long[].class));
        Assert.assertEquals("float[]", ClassUtils.getShortClassName(float[].class));
        Assert.assertEquals("double[]", ClassUtils.getShortClassName(double[].class));

        // Arrays of arrays of ...
        Assert.assertEquals("String[][]", ClassUtils.getShortClassName(String[][].class));
        Assert.assertEquals("String[][][]", ClassUtils.getShortClassName(String[][][].class));
        Assert.assertEquals("String[][][][]", ClassUtils.getShortClassName(String[][][][].class));
        
        // Inner types
        class Named {}
        Assert.assertEquals("ClassUtilsTest.2", ClassUtils.getShortClassName(new Object(){}.getClass()));
        Assert.assertEquals("ClassUtilsTest.2Named", ClassUtils.getShortClassName(Named.class));
        Assert.assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortClassName(Inner.class));
    }



    @Test
    public void test_getShortClassName_String() {
        Assert.assertEquals("ClassUtils", ClassUtils.getShortClassName(ClassUtils.class.getName()));
        Assert.assertEquals("Map.Entry", ClassUtils.getShortClassName(Map.Entry.class.getName()));
        Assert.assertEquals("", ClassUtils.getShortClassName((String) null));
        Assert.assertEquals("", ClassUtils.getShortClassName(""));
    }

    @Test
    public void test_getSimpleName_Class() {
        Assert.assertEquals("ClassUtils", ClassUtils.getSimpleName(ClassUtils.class));
        Assert.assertEquals("Entry", ClassUtils.getSimpleName(Map.Entry.class));
        Assert.assertEquals("", ClassUtils.getSimpleName((Class<?>) null));

        // LANG-535
        Assert.assertEquals("String[]", ClassUtils.getSimpleName(String[].class));
        Assert.assertEquals("Entry[]", ClassUtils.getSimpleName(Map.Entry[].class));

        // Primitives
        Assert.assertEquals("boolean", ClassUtils.getSimpleName(boolean.class));
        Assert.assertEquals("byte", ClassUtils.getSimpleName(byte.class));
        Assert.assertEquals("char", ClassUtils.getSimpleName(char.class));
        Assert.assertEquals("short", ClassUtils.getSimpleName(short.class));
        Assert.assertEquals("int", ClassUtils.getSimpleName(int.class));
        Assert.assertEquals("long", ClassUtils.getSimpleName(long.class));
        Assert.assertEquals("float", ClassUtils.getSimpleName(float.class));
        Assert.assertEquals("double", ClassUtils.getSimpleName(double.class));

        // Primitive Arrays
        Assert.assertEquals("boolean[]", ClassUtils.getSimpleName(boolean[].class));
        Assert.assertEquals("byte[]", ClassUtils.getSimpleName(byte[].class));
        Assert.assertEquals("char[]", ClassUtils.getSimpleName(char[].class));
        Assert.assertEquals("short[]", ClassUtils.getSimpleName(short[].class));
        Assert.assertEquals("int[]", ClassUtils.getSimpleName(int[].class));
        Assert.assertEquals("long[]", ClassUtils.getSimpleName(long[].class));
        Assert.assertEquals("float[]", ClassUtils.getSimpleName(float[].class));
        Assert.assertEquals("double[]", ClassUtils.getSimpleName(double[].class));

        // Arrays of arrays of ...
        Assert.assertEquals("String[][]", ClassUtils.getSimpleName(String[][].class));
        Assert.assertEquals("String[][][]", ClassUtils.getSimpleName(String[][][].class));
        Assert.assertEquals("String[][][][]", ClassUtils.getSimpleName(String[][][][].class));
        
        // On-the-fly types
        class Named {}
        Assert.assertEquals("", ClassUtils.getSimpleName(new Object(){}.getClass()));
        Assert.assertEquals("Named", ClassUtils.getSimpleName(Named.class));
    }

    @Test
    public void test_getSimpleName_Object() {
        Assert.assertEquals("ClassUtils", ClassUtils.getSimpleName(new ClassUtils(), "<null>"));
        Assert.assertEquals("Inner", ClassUtils.getSimpleName(new Inner(), "<null>"));
        Assert.assertEquals("String", ClassUtils.getSimpleName("hello", "<null>"));
        Assert.assertEquals("<null>", ClassUtils.getSimpleName(null, "<null>"));
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_getPackageName_Object() {
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(new ClassUtils(), "<null>"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(new Inner(), "<null>"));
        Assert.assertEquals("<null>", ClassUtils.getPackageName(null, "<null>"));
    }

    @Test
    public void test_getPackageName_Class() {
        Assert.assertEquals("java.lang", ClassUtils.getPackageName(String.class));
        Assert.assertEquals("java.util", ClassUtils.getPackageName(Map.Entry.class));
        Assert.assertEquals("", ClassUtils.getPackageName((Class<?>) null));

        // LANG-535
        Assert.assertEquals("java.lang", ClassUtils.getPackageName(String[].class));

        // Primitive Arrays
        Assert.assertEquals("", ClassUtils.getPackageName(boolean[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(byte[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(char[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(short[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(int[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(long[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(float[].class));
        Assert.assertEquals("", ClassUtils.getPackageName(double[].class));

        // Arrays of arrays of ...
        Assert.assertEquals("java.lang", ClassUtils.getPackageName(String[][].class));
        Assert.assertEquals("java.lang", ClassUtils.getPackageName(String[][][].class));
        Assert.assertEquals("java.lang", ClassUtils.getPackageName(String[][][][].class));
        
        // On-the-fly types
        class Named {}
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(new Object() {
        }.getClass()));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(Named.class));
    }

    @Test
    public void test_getPackageName_String() {
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageName(ClassUtils.class.getName()));
        Assert.assertEquals("java.util", ClassUtils.getPackageName(Map.Entry.class.getName()));
        Assert.assertEquals("", ClassUtils.getPackageName((String) null));
        Assert.assertEquals("", ClassUtils.getPackageName(""));
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_getAbbreviatedName_Class() {
        Assert.assertEquals("", ClassUtils.getAbbreviatedName((Class<?>)null, 1));
        Assert.assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 1));
        Assert.assertEquals("j.l.String", ClassUtils.getAbbreviatedName(String.class, 5));
        Assert.assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 13));
        Assert.assertEquals("j.lang.String", ClassUtils.getAbbreviatedName(String.class, 15));
        Assert.assertEquals("java.lang.String", ClassUtils.getAbbreviatedName(String.class, 20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getAbbreviatedName_Class_ZeroLen() {
        ClassUtils.getAbbreviatedName(String.class, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getAbbreviatedName_Class_NegativeLen() {
        ClassUtils.getAbbreviatedName(String.class, -10);
    }

    @Test
    public void test_getAbbreviatedName_String() {
        Assert.assertEquals("", ClassUtils.getAbbreviatedName((String) null, 1));
        Assert.assertEquals("WithoutPackage", ClassUtils.getAbbreviatedName("WithoutPackage", 1));
        Assert.assertEquals("j.l.String", ClassUtils.getAbbreviatedName("java.lang.String", 1));
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_getAllSuperclasses_Class() {
        final List<?> list = ClassUtils.getAllSuperclasses(CY.class);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(CX.class, list.get(0));
        Assert.assertEquals(Object.class, list.get(1));

        Assert.assertEquals(null, ClassUtils.getAllSuperclasses(null));
    }

    @Test
    public void test_getAllInterfaces_Class() {
        final List<?> list = ClassUtils.getAllInterfaces(CY.class);
        Assert.assertEquals(6, list.size());
        Assert.assertEquals(IB.class, list.get(0));
        Assert.assertEquals(IC.class, list.get(1));
        Assert.assertEquals(ID.class, list.get(2));
        Assert.assertEquals(IE.class, list.get(3));
        Assert.assertEquals(IF.class, list.get(4));
        Assert.assertEquals(IA.class, list.get(5));

        Assert.assertEquals(null, ClassUtils.getAllInterfaces(null));
    }

    private static interface IA {
    }
    private static interface IB {
    }
    private static interface IC extends ID, IE {
    }
    private static interface ID {
    }
    private static interface IE extends IF {
    }
    private static interface IF {
    }
    private static class CX implements IB, IA, IE {
    }
    private static class CY extends CX implements IB, IC {
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_convertClassNamesToClasses_List() {
        final List<String> list = new ArrayList<String>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        Assert.assertEquals(0, result.size());

        list.add("java.lang.String");
        list.add("java.lang.xxx");
        list.add("java.lang.Object");
        result = ClassUtils.convertClassNamesToClasses(list);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals(String.class, result.get(0));
        Assert.assertEquals(null, result.get(1));
        Assert.assertEquals(Object.class, result.get(2));

        @SuppressWarnings("unchecked") // test what happens when non-generic code adds wrong type of element
        final
        List<Object> olist = (List<Object>)(List<?>)list;
        olist.add(new Object());
        try {
            ClassUtils.convertClassNamesToClasses(list);
            Assert.fail("Should not have been able to convert list");
        } catch (final ClassCastException expected) {}
        Assert.assertEquals(null, ClassUtils.convertClassNamesToClasses(null));
    }

    @Test
    public void test_convertClassesToClassNames_List() {
        final List<Class<?>> list = new ArrayList<Class<?>>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        Assert.assertEquals(0, result.size());

        list.add(String.class);
        list.add(null);
        list.add(Object.class);
        result = ClassUtils.convertClassesToClassNames(list);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("java.lang.String", result.get(0));
        Assert.assertEquals(null, result.get(1));
        Assert.assertEquals("java.lang.Object", result.get(2));

        @SuppressWarnings("unchecked") // test what happens when non-generic code adds wrong type of element
        final
        List<Object> olist = (List<Object>)(List<?>)list;
        olist.add(new Object());
        try {
            ClassUtils.convertClassesToClassNames(list);
            Assert.fail("Should not have been able to convert list");
        } catch (final ClassCastException expected) {}
        Assert.assertEquals(null, ClassUtils.convertClassesToClassNames(null));
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_isInnerClass_Class() {
        Assert.assertTrue(ClassUtils.isInnerClass(Inner.class));
        Assert.assertTrue(ClassUtils.isInnerClass(Map.Entry.class));
        Assert.assertTrue(ClassUtils.isInnerClass(new Cloneable() {
        }.getClass()));
        Assert.assertFalse(ClassUtils.isInnerClass(this.getClass()));
        Assert.assertFalse(ClassUtils.isInnerClass(String.class));
        Assert.assertFalse(ClassUtils.isInnerClass(null));
    }

    // -------------------------------------------------------------------------
    @Test
    public void test_isAssignable_ClassArray_ClassArray() throws Exception {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        Assert.assertFalse(ClassUtils.isAssignable(array1, array2));
        Assert.assertFalse(ClassUtils.isAssignable(null, array2));
        Assert.assertTrue(ClassUtils.isAssignable(null, array0));
        Assert.assertTrue(ClassUtils.isAssignable(array0, array0));
//        assertTrue(ClassUtils.isAssignable(array0, null)); 
        Assert.assertTrue(ClassUtils.isAssignable(array0, (Class<?>[]) null)); // explicit cast to avoid warning
        Assert.assertTrue(ClassUtils.isAssignable((Class[]) null, (Class[]) null));

        Assert.assertFalse(ClassUtils.isAssignable(array1, array1s));
        Assert.assertTrue(ClassUtils.isAssignable(array1s, array1s));
        Assert.assertTrue(ClassUtils.isAssignable(array1s, array1));

        final boolean autoboxing = SystemUtils.isJavaVersionAtLeast(JAVA_1_5);

        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(arrayPrimitives, arrayWrappers));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(arrayWrappers, arrayPrimitives));
        Assert.assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1));
        Assert.assertFalse(ClassUtils.isAssignable(arrayWrappers, array1));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(arrayPrimitives, array2));
        Assert.assertTrue(ClassUtils.isAssignable(arrayWrappers, array2));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_Autoboxing() throws Exception {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        Assert.assertFalse(ClassUtils.isAssignable(array1, array2, true));
        Assert.assertFalse(ClassUtils.isAssignable(null, array2, true));
        Assert.assertTrue(ClassUtils.isAssignable(null, array0, true));
        Assert.assertTrue(ClassUtils.isAssignable(array0, array0, true));
        Assert.assertTrue(ClassUtils.isAssignable(array0, null, true));
        Assert.assertTrue(ClassUtils.isAssignable((Class[]) null, (Class[]) null, true));

        Assert.assertFalse(ClassUtils.isAssignable(array1, array1s, true));
        Assert.assertTrue(ClassUtils.isAssignable(array1s, array1s, true));
        Assert.assertTrue(ClassUtils.isAssignable(array1s, array1, true));

        Assert.assertTrue(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers, true));
        Assert.assertTrue(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives, true));
        Assert.assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1, true));
        Assert.assertFalse(ClassUtils.isAssignable(arrayWrappers, array1, true));
        Assert.assertTrue(ClassUtils.isAssignable(arrayPrimitives, array2, true));
        Assert.assertTrue(ClassUtils.isAssignable(arrayWrappers, array2, true));
    }

    @Test
    public void test_isAssignable_ClassArray_ClassArray_NoAutoboxing() throws Exception {
        final Class<?>[] array2 = new Class[] {Object.class, Object.class};
        final Class<?>[] array1 = new Class[] {Object.class};
        final Class<?>[] array1s = new Class[] {String.class};
        final Class<?>[] array0 = new Class[] {};
        final Class<?>[] arrayPrimitives = { Integer.TYPE, Boolean.TYPE };
        final Class<?>[] arrayWrappers = { Integer.class, Boolean.class };

        Assert.assertFalse(ClassUtils.isAssignable(array1, array2, false));
        Assert.assertFalse(ClassUtils.isAssignable(null, array2, false));
        Assert.assertTrue(ClassUtils.isAssignable(null, array0, false));
        Assert.assertTrue(ClassUtils.isAssignable(array0, array0, false));
        Assert.assertTrue(ClassUtils.isAssignable(array0, null, false));
        Assert.assertTrue(ClassUtils.isAssignable((Class[]) null, (Class[]) null, false));

        Assert.assertFalse(ClassUtils.isAssignable(array1, array1s, false));
        Assert.assertTrue(ClassUtils.isAssignable(array1s, array1s, false));
        Assert.assertTrue(ClassUtils.isAssignable(array1s, array1, false));

        Assert.assertFalse(ClassUtils.isAssignable(arrayPrimitives, arrayWrappers, false));
        Assert.assertFalse(ClassUtils.isAssignable(arrayWrappers, arrayPrimitives, false));
        Assert.assertFalse(ClassUtils.isAssignable(arrayPrimitives, array1, false));
        Assert.assertFalse(ClassUtils.isAssignable(arrayWrappers, array1, false));
        Assert.assertTrue(ClassUtils.isAssignable(arrayWrappers, array2, false));
        Assert.assertFalse(ClassUtils.isAssignable(arrayPrimitives, array2, false));
    }

    @Test
    public void test_isAssignable() throws Exception {
        Assert.assertFalse(ClassUtils.isAssignable((Class<?>) null, null));
        Assert.assertFalse(ClassUtils.isAssignable(String.class, null));

        Assert.assertTrue(ClassUtils.isAssignable(null, Object.class));
        Assert.assertTrue(ClassUtils.isAssignable(null, Integer.class));
        Assert.assertFalse(ClassUtils.isAssignable(null, Integer.TYPE));
        Assert.assertTrue(ClassUtils.isAssignable(String.class, Object.class));
        Assert.assertTrue(ClassUtils.isAssignable(String.class, String.class));
        Assert.assertFalse(ClassUtils.isAssignable(Object.class, String.class));

        final boolean autoboxing = SystemUtils.isJavaVersionAtLeast(JAVA_1_5);

        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Integer.TYPE, Integer.class));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Integer.TYPE, Object.class));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Integer.class, Integer.TYPE));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Integer.class, Object.class));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Boolean.TYPE, Boolean.class));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Boolean.TYPE, Object.class));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Boolean.class, Boolean.TYPE));
        Assert.assertEquals(autoboxing, ClassUtils.isAssignable(Boolean.class, Object.class));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class));
    }

    @Test
    public void test_isAssignable_Autoboxing() throws Exception {
        Assert.assertFalse(ClassUtils.isAssignable((Class<?>) null, null, true));
        Assert.assertFalse(ClassUtils.isAssignable(String.class, null, true));

        Assert.assertTrue(ClassUtils.isAssignable(null, Object.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(null, Integer.class, true));
        Assert.assertFalse(ClassUtils.isAssignable(null, Integer.TYPE, true));
        Assert.assertTrue(ClassUtils.isAssignable(String.class, Object.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(String.class, String.class, true));
        Assert.assertFalse(ClassUtils.isAssignable(Object.class, String.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.TYPE, Object.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.class, Integer.TYPE, true));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.class, Object.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE, true));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, true));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.class, Object.class, true));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE, true));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class, true));
    }

    @Test
    public void test_isAssignable_NoAutoboxing() throws Exception {
        Assert.assertFalse(ClassUtils.isAssignable((Class<?>) null, null, false));
        Assert.assertFalse(ClassUtils.isAssignable(String.class, null, false));

        Assert.assertTrue(ClassUtils.isAssignable(null, Object.class, false));
        Assert.assertTrue(ClassUtils.isAssignable(null, Integer.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(null, Integer.TYPE, false));
        Assert.assertTrue(ClassUtils.isAssignable(String.class, Object.class, false));
        Assert.assertTrue(ClassUtils.isAssignable(String.class, String.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Object.class, String.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Integer.TYPE, Integer.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Integer.TYPE, Object.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Integer.class, Integer.TYPE, false));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE, false));
        Assert.assertTrue(ClassUtils.isAssignable(Integer.class, Integer.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Boolean.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Boolean.TYPE, Object.class, false));
        Assert.assertFalse(ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, false));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.class, Object.class, false));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE, false));
        Assert.assertTrue(ClassUtils.isAssignable(Boolean.class, Boolean.class, false));
    }

    @Test
    public void test_isAssignable_Widening() throws Exception {
        // test byte conversions
        Assert.assertFalse("byte -> char", ClassUtils.isAssignable(Byte.TYPE, Character.TYPE));
        Assert.assertTrue("byte -> byte", ClassUtils.isAssignable(Byte.TYPE, Byte.TYPE));
        Assert.assertTrue("byte -> short", ClassUtils.isAssignable(Byte.TYPE, Short.TYPE));
        Assert.assertTrue("byte -> int", ClassUtils.isAssignable(Byte.TYPE, Integer.TYPE));
        Assert.assertTrue("byte -> long", ClassUtils.isAssignable(Byte.TYPE, Long.TYPE));
        Assert.assertTrue("byte -> float", ClassUtils.isAssignable(Byte.TYPE, Float.TYPE));
        Assert.assertTrue("byte -> double", ClassUtils.isAssignable(Byte.TYPE, Double.TYPE));
        Assert.assertFalse("byte -> boolean", ClassUtils.isAssignable(Byte.TYPE, Boolean.TYPE));

        // test short conversions
        Assert.assertFalse("short -> char", ClassUtils.isAssignable(Short.TYPE, Character.TYPE));
        Assert.assertFalse("short -> byte", ClassUtils.isAssignable(Short.TYPE, Byte.TYPE));
        Assert.assertTrue("short -> short", ClassUtils.isAssignable(Short.TYPE, Short.TYPE));
        Assert.assertTrue("short -> int", ClassUtils.isAssignable(Short.TYPE, Integer.TYPE));
        Assert.assertTrue("short -> long", ClassUtils.isAssignable(Short.TYPE, Long.TYPE));
        Assert.assertTrue("short -> float", ClassUtils.isAssignable(Short.TYPE, Float.TYPE));
        Assert.assertTrue("short -> double", ClassUtils.isAssignable(Short.TYPE, Double.TYPE));
        Assert.assertFalse("short -> boolean", ClassUtils.isAssignable(Short.TYPE, Boolean.TYPE));

        // test char conversions
        Assert.assertTrue("char -> char", ClassUtils.isAssignable(Character.TYPE, Character.TYPE));
        Assert.assertFalse("char -> byte", ClassUtils.isAssignable(Character.TYPE, Byte.TYPE));
        Assert.assertFalse("char -> short", ClassUtils.isAssignable(Character.TYPE, Short.TYPE));
        Assert.assertTrue("char -> int", ClassUtils.isAssignable(Character.TYPE, Integer.TYPE));
        Assert.assertTrue("char -> long", ClassUtils.isAssignable(Character.TYPE, Long.TYPE));
        Assert.assertTrue("char -> float", ClassUtils.isAssignable(Character.TYPE, Float.TYPE));
        Assert.assertTrue("char -> double", ClassUtils.isAssignable(Character.TYPE, Double.TYPE));
        Assert.assertFalse("char -> boolean", ClassUtils.isAssignable(Character.TYPE, Boolean.TYPE));

        // test int conversions
        Assert.assertFalse("int -> char", ClassUtils.isAssignable(Integer.TYPE, Character.TYPE));
        Assert.assertFalse("int -> byte", ClassUtils.isAssignable(Integer.TYPE, Byte.TYPE));
        Assert.assertFalse("int -> short", ClassUtils.isAssignable(Integer.TYPE, Short.TYPE));
        Assert.assertTrue("int -> int", ClassUtils.isAssignable(Integer.TYPE, Integer.TYPE));
        Assert.assertTrue("int -> long", ClassUtils.isAssignable(Integer.TYPE, Long.TYPE));
        Assert.assertTrue("int -> float", ClassUtils.isAssignable(Integer.TYPE, Float.TYPE));
        Assert.assertTrue("int -> double", ClassUtils.isAssignable(Integer.TYPE, Double.TYPE));
        Assert.assertFalse("int -> boolean", ClassUtils.isAssignable(Integer.TYPE, Boolean.TYPE));

        // test long conversions
        Assert.assertFalse("long -> char", ClassUtils.isAssignable(Long.TYPE, Character.TYPE));
        Assert.assertFalse("long -> byte", ClassUtils.isAssignable(Long.TYPE, Byte.TYPE));
        Assert.assertFalse("long -> short", ClassUtils.isAssignable(Long.TYPE, Short.TYPE));
        Assert.assertFalse("long -> int", ClassUtils.isAssignable(Long.TYPE, Integer.TYPE));
        Assert.assertTrue("long -> long", ClassUtils.isAssignable(Long.TYPE, Long.TYPE));
        Assert.assertTrue("long -> float", ClassUtils.isAssignable(Long.TYPE, Float.TYPE));
        Assert.assertTrue("long -> double", ClassUtils.isAssignable(Long.TYPE, Double.TYPE));
        Assert.assertFalse("long -> boolean", ClassUtils.isAssignable(Long.TYPE, Boolean.TYPE));

        // test float conversions
        Assert.assertFalse("float -> char", ClassUtils.isAssignable(Float.TYPE, Character.TYPE));
        Assert.assertFalse("float -> byte", ClassUtils.isAssignable(Float.TYPE, Byte.TYPE));
        Assert.assertFalse("float -> short", ClassUtils.isAssignable(Float.TYPE, Short.TYPE));
        Assert.assertFalse("float -> int", ClassUtils.isAssignable(Float.TYPE, Integer.TYPE));
        Assert.assertFalse("float -> long", ClassUtils.isAssignable(Float.TYPE, Long.TYPE));
        Assert.assertTrue("float -> float", ClassUtils.isAssignable(Float.TYPE, Float.TYPE));
        Assert.assertTrue("float -> double", ClassUtils.isAssignable(Float.TYPE, Double.TYPE));
        Assert.assertFalse("float -> boolean", ClassUtils.isAssignable(Float.TYPE, Boolean.TYPE));

        // test double conversions
        Assert.assertFalse("double -> char", ClassUtils.isAssignable(Double.TYPE, Character.TYPE));
        Assert.assertFalse("double -> byte", ClassUtils.isAssignable(Double.TYPE, Byte.TYPE));
        Assert.assertFalse("double -> short", ClassUtils.isAssignable(Double.TYPE, Short.TYPE));
        Assert.assertFalse("double -> int", ClassUtils.isAssignable(Double.TYPE, Integer.TYPE));
        Assert.assertFalse("double -> long", ClassUtils.isAssignable(Double.TYPE, Long.TYPE));
        Assert.assertFalse("double -> float", ClassUtils.isAssignable(Double.TYPE, Float.TYPE));
        Assert.assertTrue("double -> double", ClassUtils.isAssignable(Double.TYPE, Double.TYPE));
        Assert.assertFalse("double -> boolean", ClassUtils.isAssignable(Double.TYPE, Boolean.TYPE));

        // test boolean conversions
        Assert.assertFalse("boolean -> char", ClassUtils.isAssignable(Boolean.TYPE, Character.TYPE));
        Assert.assertFalse("boolean -> byte", ClassUtils.isAssignable(Boolean.TYPE, Byte.TYPE));
        Assert.assertFalse("boolean -> short", ClassUtils.isAssignable(Boolean.TYPE, Short.TYPE));
        Assert.assertFalse("boolean -> int", ClassUtils.isAssignable(Boolean.TYPE, Integer.TYPE));
        Assert.assertFalse("boolean -> long", ClassUtils.isAssignable(Boolean.TYPE, Long.TYPE));
        Assert.assertFalse("boolean -> float", ClassUtils.isAssignable(Boolean.TYPE, Float.TYPE));
        Assert.assertFalse("boolean -> double", ClassUtils.isAssignable(Boolean.TYPE, Double.TYPE));
        Assert.assertTrue("boolean -> boolean", ClassUtils.isAssignable(Boolean.TYPE, Boolean.TYPE));
    }

    @Test
    public void test_isAssignable_DefaultUnboxing_Widening() throws Exception {
        final boolean autoboxing = SystemUtils.isJavaVersionAtLeast(JAVA_1_5);

        // test byte conversions
        Assert.assertFalse("byte -> char", ClassUtils.isAssignable(Byte.class, Character.TYPE));
        Assert.assertEquals("byte -> byte", autoboxing, ClassUtils.isAssignable(Byte.class, Byte.TYPE));
        Assert.assertEquals("byte -> short", autoboxing, ClassUtils.isAssignable(Byte.class, Short.TYPE));
        Assert.assertEquals("byte -> int", autoboxing, ClassUtils.isAssignable(Byte.class, Integer.TYPE));
        Assert.assertEquals("byte -> long", autoboxing, ClassUtils.isAssignable(Byte.class, Long.TYPE));
        Assert.assertEquals("byte -> float", autoboxing, ClassUtils.isAssignable(Byte.class, Float.TYPE));
        Assert.assertEquals("byte -> double", autoboxing, ClassUtils.isAssignable(Byte.class, Double.TYPE));
        Assert.assertFalse("byte -> boolean", ClassUtils.isAssignable(Byte.class, Boolean.TYPE));

        // test short conversions
        Assert.assertFalse("short -> char", ClassUtils.isAssignable(Short.class, Character.TYPE));
        Assert.assertFalse("short -> byte", ClassUtils.isAssignable(Short.class, Byte.TYPE));
        Assert.assertEquals("short -> short", autoboxing, ClassUtils.isAssignable(Short.class, Short.TYPE));
        Assert.assertEquals("short -> int", autoboxing, ClassUtils.isAssignable(Short.class, Integer.TYPE));
        Assert.assertEquals("short -> long", autoboxing, ClassUtils.isAssignable(Short.class, Long.TYPE));
        Assert.assertEquals("short -> float", autoboxing, ClassUtils.isAssignable(Short.class, Float.TYPE));
        Assert.assertEquals("short -> double", autoboxing, ClassUtils.isAssignable(Short.class, Double.TYPE));
        Assert.assertFalse("short -> boolean", ClassUtils.isAssignable(Short.class, Boolean.TYPE));

        // test char conversions
        Assert.assertEquals("char -> char", autoboxing, ClassUtils.isAssignable(Character.class, Character.TYPE));
        Assert.assertFalse("char -> byte", ClassUtils.isAssignable(Character.class, Byte.TYPE));
        Assert.assertFalse("char -> short", ClassUtils.isAssignable(Character.class, Short.TYPE));
        Assert.assertEquals("char -> int", autoboxing, ClassUtils.isAssignable(Character.class, Integer.TYPE));
        Assert.assertEquals("char -> long", autoboxing, ClassUtils.isAssignable(Character.class, Long.TYPE));
        Assert.assertEquals("char -> float", autoboxing, ClassUtils.isAssignable(Character.class, Float.TYPE));
        Assert.assertEquals("char -> double", autoboxing, ClassUtils.isAssignable(Character.class, Double.TYPE));
        Assert.assertFalse("char -> boolean", ClassUtils.isAssignable(Character.class, Boolean.TYPE));

        // test int conversions
        Assert.assertFalse("int -> char", ClassUtils.isAssignable(Integer.class, Character.TYPE));
        Assert.assertFalse("int -> byte", ClassUtils.isAssignable(Integer.class, Byte.TYPE));
        Assert.assertFalse("int -> short", ClassUtils.isAssignable(Integer.class, Short.TYPE));
        Assert.assertEquals("int -> int", autoboxing, ClassUtils.isAssignable(Integer.class, Integer.TYPE));
        Assert.assertEquals("int -> long", autoboxing, ClassUtils.isAssignable(Integer.class, Long.TYPE));
        Assert.assertEquals("int -> float", autoboxing, ClassUtils.isAssignable(Integer.class, Float.TYPE));
        Assert.assertEquals("int -> double", autoboxing, ClassUtils.isAssignable(Integer.class, Double.TYPE));
        Assert.assertFalse("int -> boolean", ClassUtils.isAssignable(Integer.class, Boolean.TYPE));

        // test long conversions
        Assert.assertFalse("long -> char", ClassUtils.isAssignable(Long.class, Character.TYPE));
        Assert.assertFalse("long -> byte", ClassUtils.isAssignable(Long.class, Byte.TYPE));
        Assert.assertFalse("long -> short", ClassUtils.isAssignable(Long.class, Short.TYPE));
        Assert.assertFalse("long -> int", ClassUtils.isAssignable(Long.class, Integer.TYPE));
        Assert.assertEquals("long -> long", autoboxing, ClassUtils.isAssignable(Long.class, Long.TYPE));
        Assert.assertEquals("long -> float", autoboxing, ClassUtils.isAssignable(Long.class, Float.TYPE));
        Assert.assertEquals("long -> double", autoboxing, ClassUtils.isAssignable(Long.class, Double.TYPE));
        Assert.assertFalse("long -> boolean", ClassUtils.isAssignable(Long.class, Boolean.TYPE));

        // test float conversions
        Assert.assertFalse("float -> char", ClassUtils.isAssignable(Float.class, Character.TYPE));
        Assert.assertFalse("float -> byte", ClassUtils.isAssignable(Float.class, Byte.TYPE));
        Assert.assertFalse("float -> short", ClassUtils.isAssignable(Float.class, Short.TYPE));
        Assert.assertFalse("float -> int", ClassUtils.isAssignable(Float.class, Integer.TYPE));
        Assert.assertFalse("float -> long", ClassUtils.isAssignable(Float.class, Long.TYPE));
        Assert.assertEquals("float -> float", autoboxing, ClassUtils.isAssignable(Float.class, Float.TYPE));
        Assert.assertEquals("float -> double", autoboxing, ClassUtils.isAssignable(Float.class, Double.TYPE));
        Assert.assertFalse("float -> boolean", ClassUtils.isAssignable(Float.class, Boolean.TYPE));

        // test double conversions
        Assert.assertFalse("double -> char", ClassUtils.isAssignable(Double.class, Character.TYPE));
        Assert.assertFalse("double -> byte", ClassUtils.isAssignable(Double.class, Byte.TYPE));
        Assert.assertFalse("double -> short", ClassUtils.isAssignable(Double.class, Short.TYPE));
        Assert.assertFalse("double -> int", ClassUtils.isAssignable(Double.class, Integer.TYPE));
        Assert.assertFalse("double -> long", ClassUtils.isAssignable(Double.class, Long.TYPE));
        Assert.assertFalse("double -> float", ClassUtils.isAssignable(Double.class, Float.TYPE));
        Assert.assertEquals("double -> double", autoboxing, ClassUtils.isAssignable(Double.class, Double.TYPE));
        Assert.assertFalse("double -> boolean", ClassUtils.isAssignable(Double.class, Boolean.TYPE));

        // test boolean conversions
        Assert.assertFalse("boolean -> char", ClassUtils.isAssignable(Boolean.class, Character.TYPE));
        Assert.assertFalse("boolean -> byte", ClassUtils.isAssignable(Boolean.class, Byte.TYPE));
        Assert.assertFalse("boolean -> short", ClassUtils.isAssignable(Boolean.class, Short.TYPE));
        Assert.assertFalse("boolean -> int", ClassUtils.isAssignable(Boolean.class, Integer.TYPE));
        Assert.assertFalse("boolean -> long", ClassUtils.isAssignable(Boolean.class, Long.TYPE));
        Assert.assertFalse("boolean -> float", ClassUtils.isAssignable(Boolean.class, Float.TYPE));
        Assert.assertFalse("boolean -> double", ClassUtils.isAssignable(Boolean.class, Double.TYPE));
        Assert.assertEquals("boolean -> boolean", autoboxing, ClassUtils.isAssignable(Boolean.class, Boolean.TYPE));
    }

    @Test
    public void test_isAssignable_Unboxing_Widening() throws Exception {
        // test byte conversions
        Assert.assertFalse("byte -> char", ClassUtils.isAssignable(Byte.class, Character.TYPE, true));
        Assert.assertTrue("byte -> byte", ClassUtils.isAssignable(Byte.class, Byte.TYPE, true));
        Assert.assertTrue("byte -> short", ClassUtils.isAssignable(Byte.class, Short.TYPE, true));
        Assert.assertTrue("byte -> int", ClassUtils.isAssignable(Byte.class, Integer.TYPE, true));
        Assert.assertTrue("byte -> long", ClassUtils.isAssignable(Byte.class, Long.TYPE, true));
        Assert.assertTrue("byte -> float", ClassUtils.isAssignable(Byte.class, Float.TYPE, true));
        Assert.assertTrue("byte -> double", ClassUtils.isAssignable(Byte.class, Double.TYPE, true));
        Assert.assertFalse("byte -> boolean", ClassUtils.isAssignable(Byte.class, Boolean.TYPE, true));

        // test short conversions
        Assert.assertFalse("short -> char", ClassUtils.isAssignable(Short.class, Character.TYPE, true));
        Assert.assertFalse("short -> byte", ClassUtils.isAssignable(Short.class, Byte.TYPE, true));
        Assert.assertTrue("short -> short", ClassUtils.isAssignable(Short.class, Short.TYPE, true));
        Assert.assertTrue("short -> int", ClassUtils.isAssignable(Short.class, Integer.TYPE, true));
        Assert.assertTrue("short -> long", ClassUtils.isAssignable(Short.class, Long.TYPE, true));
        Assert.assertTrue("short -> float", ClassUtils.isAssignable(Short.class, Float.TYPE, true));
        Assert.assertTrue("short -> double", ClassUtils.isAssignable(Short.class, Double.TYPE, true));
        Assert.assertFalse("short -> boolean", ClassUtils.isAssignable(Short.class, Boolean.TYPE, true));

        // test char conversions
        Assert.assertTrue("char -> char", ClassUtils.isAssignable(Character.class, Character.TYPE, true));
        Assert.assertFalse("char -> byte", ClassUtils.isAssignable(Character.class, Byte.TYPE, true));
        Assert.assertFalse("char -> short", ClassUtils.isAssignable(Character.class, Short.TYPE, true));
        Assert.assertTrue("char -> int", ClassUtils.isAssignable(Character.class, Integer.TYPE, true));
        Assert.assertTrue("char -> long", ClassUtils.isAssignable(Character.class, Long.TYPE, true));
        Assert.assertTrue("char -> float", ClassUtils.isAssignable(Character.class, Float.TYPE, true));
        Assert.assertTrue("char -> double", ClassUtils.isAssignable(Character.class, Double.TYPE, true));
        Assert.assertFalse("char -> boolean", ClassUtils.isAssignable(Character.class, Boolean.TYPE, true));

        // test int conversions
        Assert.assertFalse("int -> char", ClassUtils.isAssignable(Integer.class, Character.TYPE, true));
        Assert.assertFalse("int -> byte", ClassUtils.isAssignable(Integer.class, Byte.TYPE, true));
        Assert.assertFalse("int -> short", ClassUtils.isAssignable(Integer.class, Short.TYPE, true));
        Assert.assertTrue("int -> int", ClassUtils.isAssignable(Integer.class, Integer.TYPE, true));
        Assert.assertTrue("int -> long", ClassUtils.isAssignable(Integer.class, Long.TYPE, true));
        Assert.assertTrue("int -> float", ClassUtils.isAssignable(Integer.class, Float.TYPE, true));
        Assert.assertTrue("int -> double", ClassUtils.isAssignable(Integer.class, Double.TYPE, true));
        Assert.assertFalse("int -> boolean", ClassUtils.isAssignable(Integer.class, Boolean.TYPE, true));

        // test long conversions
        Assert.assertFalse("long -> char", ClassUtils.isAssignable(Long.class, Character.TYPE, true));
        Assert.assertFalse("long -> byte", ClassUtils.isAssignable(Long.class, Byte.TYPE, true));
        Assert.assertFalse("long -> short", ClassUtils.isAssignable(Long.class, Short.TYPE, true));
        Assert.assertFalse("long -> int", ClassUtils.isAssignable(Long.class, Integer.TYPE, true));
        Assert.assertTrue("long -> long", ClassUtils.isAssignable(Long.class, Long.TYPE, true));
        Assert.assertTrue("long -> float", ClassUtils.isAssignable(Long.class, Float.TYPE, true));
        Assert.assertTrue("long -> double", ClassUtils.isAssignable(Long.class, Double.TYPE, true));
        Assert.assertFalse("long -> boolean", ClassUtils.isAssignable(Long.class, Boolean.TYPE, true));

        // test float conversions
        Assert.assertFalse("float -> char", ClassUtils.isAssignable(Float.class, Character.TYPE, true));
        Assert.assertFalse("float -> byte", ClassUtils.isAssignable(Float.class, Byte.TYPE, true));
        Assert.assertFalse("float -> short", ClassUtils.isAssignable(Float.class, Short.TYPE, true));
        Assert.assertFalse("float -> int", ClassUtils.isAssignable(Float.class, Integer.TYPE, true));
        Assert.assertFalse("float -> long", ClassUtils.isAssignable(Float.class, Long.TYPE, true));
        Assert.assertTrue("float -> float", ClassUtils.isAssignable(Float.class, Float.TYPE, true));
        Assert.assertTrue("float -> double", ClassUtils.isAssignable(Float.class, Double.TYPE, true));
        Assert.assertFalse("float -> boolean", ClassUtils.isAssignable(Float.class, Boolean.TYPE, true));

        // test double conversions
        Assert.assertFalse("double -> char", ClassUtils.isAssignable(Double.class, Character.TYPE, true));
        Assert.assertFalse("double -> byte", ClassUtils.isAssignable(Double.class, Byte.TYPE, true));
        Assert.assertFalse("double -> short", ClassUtils.isAssignable(Double.class, Short.TYPE, true));
        Assert.assertFalse("double -> int", ClassUtils.isAssignable(Double.class, Integer.TYPE, true));
        Assert.assertFalse("double -> long", ClassUtils.isAssignable(Double.class, Long.TYPE, true));
        Assert.assertFalse("double -> float", ClassUtils.isAssignable(Double.class, Float.TYPE, true));
        Assert.assertTrue("double -> double", ClassUtils.isAssignable(Double.class, Double.TYPE, true));
        Assert.assertFalse("double -> boolean", ClassUtils.isAssignable(Double.class, Boolean.TYPE, true));

        // test boolean conversions
        Assert.assertFalse("boolean -> char", ClassUtils.isAssignable(Boolean.class, Character.TYPE, true));
        Assert.assertFalse("boolean -> byte", ClassUtils.isAssignable(Boolean.class, Byte.TYPE, true));
        Assert.assertFalse("boolean -> short", ClassUtils.isAssignable(Boolean.class, Short.TYPE, true));
        Assert.assertFalse("boolean -> int", ClassUtils.isAssignable(Boolean.class, Integer.TYPE, true));
        Assert.assertFalse("boolean -> long", ClassUtils.isAssignable(Boolean.class, Long.TYPE, true));
        Assert.assertFalse("boolean -> float", ClassUtils.isAssignable(Boolean.class, Float.TYPE, true));
        Assert.assertFalse("boolean -> double", ClassUtils.isAssignable(Boolean.class, Double.TYPE, true));
        Assert.assertTrue("boolean -> boolean", ClassUtils.isAssignable(Boolean.class, Boolean.TYPE, true));
    }

    @Test
    public void testIsPrimitiveOrWrapper() {

        // test primitive wrapper classes
        Assert.assertTrue("Boolean.class", ClassUtils.isPrimitiveOrWrapper(Boolean.class));
        Assert.assertTrue("Byte.class", ClassUtils.isPrimitiveOrWrapper(Byte.class));
        Assert.assertTrue("Character.class", ClassUtils.isPrimitiveOrWrapper(Character.class));
        Assert.assertTrue("Short.class", ClassUtils.isPrimitiveOrWrapper(Short.class));
        Assert.assertTrue("Integer.class", ClassUtils.isPrimitiveOrWrapper(Integer.class));
        Assert.assertTrue("Long.class", ClassUtils.isPrimitiveOrWrapper(Long.class));
        Assert.assertTrue("Double.class", ClassUtils.isPrimitiveOrWrapper(Double.class));
        Assert.assertTrue("Float.class", ClassUtils.isPrimitiveOrWrapper(Float.class));
        
        // test primitive classes
        Assert.assertTrue("boolean", ClassUtils.isPrimitiveOrWrapper(Boolean.TYPE));
        Assert.assertTrue("byte", ClassUtils.isPrimitiveOrWrapper(Byte.TYPE));
        Assert.assertTrue("char", ClassUtils.isPrimitiveOrWrapper(Character.TYPE));
        Assert.assertTrue("short", ClassUtils.isPrimitiveOrWrapper(Short.TYPE));
        Assert.assertTrue("int", ClassUtils.isPrimitiveOrWrapper(Integer.TYPE));
        Assert.assertTrue("long", ClassUtils.isPrimitiveOrWrapper(Long.TYPE));
        Assert.assertTrue("double", ClassUtils.isPrimitiveOrWrapper(Double.TYPE));
        Assert.assertTrue("float", ClassUtils.isPrimitiveOrWrapper(Float.TYPE));
        Assert.assertTrue("Void.TYPE", ClassUtils.isPrimitiveOrWrapper(Void.TYPE));
        
        // others
        Assert.assertFalse("null", ClassUtils.isPrimitiveOrWrapper(null));
        Assert.assertFalse("Void.class", ClassUtils.isPrimitiveOrWrapper(Void.class));
        Assert.assertFalse("String.class", ClassUtils.isPrimitiveOrWrapper(String.class));
        Assert.assertFalse("this.getClass()", ClassUtils.isPrimitiveOrWrapper(this.getClass()));
    }
    
    @Test
    public void testIsPrimitiveWrapper() {

        // test primitive wrapper classes
        Assert.assertTrue("Boolean.class", ClassUtils.isPrimitiveWrapper(Boolean.class));
        Assert.assertTrue("Byte.class", ClassUtils.isPrimitiveWrapper(Byte.class));
        Assert.assertTrue("Character.class", ClassUtils.isPrimitiveWrapper(Character.class));
        Assert.assertTrue("Short.class", ClassUtils.isPrimitiveWrapper(Short.class));
        Assert.assertTrue("Integer.class", ClassUtils.isPrimitiveWrapper(Integer.class));
        Assert.assertTrue("Long.class", ClassUtils.isPrimitiveWrapper(Long.class));
        Assert.assertTrue("Double.class", ClassUtils.isPrimitiveWrapper(Double.class));
        Assert.assertTrue("Float.class", ClassUtils.isPrimitiveWrapper(Float.class));
        
        // test primitive classes
        Assert.assertFalse("boolean", ClassUtils.isPrimitiveWrapper(Boolean.TYPE));
        Assert.assertFalse("byte", ClassUtils.isPrimitiveWrapper(Byte.TYPE));
        Assert.assertFalse("char", ClassUtils.isPrimitiveWrapper(Character.TYPE));
        Assert.assertFalse("short", ClassUtils.isPrimitiveWrapper(Short.TYPE));
        Assert.assertFalse("int", ClassUtils.isPrimitiveWrapper(Integer.TYPE));
        Assert.assertFalse("long", ClassUtils.isPrimitiveWrapper(Long.TYPE));
        Assert.assertFalse("double", ClassUtils.isPrimitiveWrapper(Double.TYPE));
        Assert.assertFalse("float", ClassUtils.isPrimitiveWrapper(Float.TYPE));
        
        // others
        Assert.assertFalse("null", ClassUtils.isPrimitiveWrapper(null));
        Assert.assertFalse("Void.class", ClassUtils.isPrimitiveWrapper(Void.class));
        Assert.assertFalse("Void.TYPE", ClassUtils.isPrimitiveWrapper(Void.TYPE));
        Assert.assertFalse("String.class", ClassUtils.isPrimitiveWrapper(String.class));
        Assert.assertFalse("this.getClass()", ClassUtils.isPrimitiveWrapper(this.getClass()));
    }
    
    @Test
    public void testPrimitiveToWrapper() {

        // test primitive classes
        Assert.assertEquals("boolean -> Boolean.class",
            Boolean.class, ClassUtils.primitiveToWrapper(Boolean.TYPE));
        Assert.assertEquals("byte -> Byte.class",
            Byte.class, ClassUtils.primitiveToWrapper(Byte.TYPE));
        Assert.assertEquals("char -> Character.class",
            Character.class, ClassUtils.primitiveToWrapper(Character.TYPE));
        Assert.assertEquals("short -> Short.class",
            Short.class, ClassUtils.primitiveToWrapper(Short.TYPE));
        Assert.assertEquals("int -> Integer.class",
            Integer.class, ClassUtils.primitiveToWrapper(Integer.TYPE));
        Assert.assertEquals("long -> Long.class",
            Long.class, ClassUtils.primitiveToWrapper(Long.TYPE));
        Assert.assertEquals("double -> Double.class",
            Double.class, ClassUtils.primitiveToWrapper(Double.TYPE));
        Assert.assertEquals("float -> Float.class",
            Float.class, ClassUtils.primitiveToWrapper(Float.TYPE));

        // test a few other classes
        Assert.assertEquals("String.class -> String.class",
            String.class, ClassUtils.primitiveToWrapper(String.class));
        Assert.assertEquals("ClassUtils.class -> ClassUtils.class",
            org.apache.commons.lang3.ClassUtils.class,
            ClassUtils.primitiveToWrapper(org.apache.commons.lang3.ClassUtils.class));
        Assert.assertEquals("Void.TYPE -> Void.TYPE",
            Void.TYPE, ClassUtils.primitiveToWrapper(Void.TYPE));

        // test null
        Assert.assertNull("null -> null",
            ClassUtils.primitiveToWrapper(null));
    }

    @Test
    public void testPrimitivesToWrappers() {
        // test null
//        assertNull("null -> null", ClassUtils.primitivesToWrappers(null)); // generates warning
        Assert.assertNull("null -> null", ClassUtils.primitivesToWrappers((Class<?>[]) null)); // equivalent cast to avoid warning
        // Other possible casts for null
        Assert.assertTrue("empty -> empty", Arrays.equals(ArrayUtils.EMPTY_CLASS_ARRAY, ClassUtils.primitivesToWrappers()));
        final Class<?>[] castNull = ClassUtils.primitivesToWrappers((Class<?>)null); // == new Class<?>[]{null}
        Assert.assertTrue("(Class<?>)null -> [null]", Arrays.equals(new Class<?>[]{null}, castNull));
        // test empty array is returned unchanged
        Assert.assertArrayEquals("empty -> empty",
                ArrayUtils.EMPTY_CLASS_ARRAY, ClassUtils.primitivesToWrappers(ArrayUtils.EMPTY_CLASS_ARRAY));

        // test an array of various classes
        final Class<?>[] primitives = new Class[] {
                Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE,
                Integer.TYPE, Long.TYPE, Double.TYPE, Float.TYPE,
                String.class, ClassUtils.class
        };
        final Class<?>[] wrappers= ClassUtils.primitivesToWrappers(primitives);

        for (int i=0; i < primitives.length; i++) {
            // test each returned wrapper
            final Class<?> primitive = primitives[i];
            final Class<?> expectedWrapper = ClassUtils.primitiveToWrapper(primitive);

            Assert.assertEquals(primitive + " -> " + expectedWrapper, expectedWrapper, wrappers[i]);
        }

        // test an array of no primitive classes
        final Class<?>[] noPrimitives = new Class[] {
                String.class, ClassUtils.class, Void.TYPE
        };
        // This used to return the exact same array, but no longer does.
        Assert.assertNotSame("unmodified", noPrimitives, ClassUtils.primitivesToWrappers(noPrimitives));
    }

    @Test
    public void testWrapperToPrimitive() {
        // an array with classes to convert
        final Class<?>[] primitives = {
                Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE,
                Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE
        };
        for (final Class<?> primitive : primitives) {
            final Class<?> wrapperCls = ClassUtils.primitiveToWrapper(primitive);
            Assert.assertFalse("Still primitive", wrapperCls.isPrimitive());
            Assert.assertEquals(wrapperCls + " -> " + primitive, primitive,
                    ClassUtils.wrapperToPrimitive(wrapperCls));
        }
    }

    @Test
    public void testWrapperToPrimitiveNoWrapper() {
        Assert.assertNull("Wrong result for non wrapper class", ClassUtils.wrapperToPrimitive(String.class));
    }

    @Test
    public void testWrapperToPrimitiveNull() {
        Assert.assertNull("Wrong result for null class", ClassUtils.wrapperToPrimitive(null));
    }

    @Test
    public void testWrappersToPrimitives() {
        // an array with classes to test
        final Class<?>[] classes = {
                Boolean.class, Byte.class, Character.class, Short.class,
                Integer.class, Long.class, Float.class, Double.class,
                String.class, ClassUtils.class, null
        };

        final Class<?>[] primitives = ClassUtils.wrappersToPrimitives(classes);
        // now test the result
        Assert.assertEquals("Wrong length of result array", classes.length, primitives.length);
        for (int i = 0; i < classes.length; i++) {
            final Class<?> expectedPrimitive = ClassUtils.wrapperToPrimitive(classes[i]);
            Assert.assertEquals(classes[i] + " -> " + expectedPrimitive, expectedPrimitive,
                    primitives[i]);
        }
    }

    @Test
    public void testWrappersToPrimitivesNull() {
//        assertNull("Wrong result for null input", ClassUtils.wrappersToPrimitives(null)); // generates warning
        Assert.assertNull("Wrong result for null input", ClassUtils.wrappersToPrimitives((Class<?>[]) null)); // equivalent cast
        // Other possible casts for null
        Assert.assertTrue("empty -> empty", Arrays.equals(ArrayUtils.EMPTY_CLASS_ARRAY, ClassUtils.wrappersToPrimitives()));
        final Class<?>[] castNull = ClassUtils.wrappersToPrimitives((Class<?>)null); // == new Class<?>[]{null}
        Assert.assertTrue("(Class<?>)null -> [null]", Arrays.equals(new Class<?>[]{null}, castNull));
}

    @Test
    public void testWrappersToPrimitivesEmpty() {
        final Class<?>[] empty = new Class[0];
        Assert.assertArrayEquals("Wrong result for empty input", empty, ClassUtils.wrappersToPrimitives(empty));
    }

    @Test
    public void testGetClassClassNotFound() throws Exception {
        assertGetClassThrowsClassNotFound( "bool" );
        assertGetClassThrowsClassNotFound( "bool[]" );
        assertGetClassThrowsClassNotFound( "integer[]" );
    }

    @Test
    public void testGetClassInvalidArguments() throws Exception {
        assertGetClassThrowsNullPointerException( null );
        assertGetClassThrowsClassNotFound( "[][][]" );
        assertGetClassThrowsClassNotFound( "[[]" );
        assertGetClassThrowsClassNotFound( "[" );
        assertGetClassThrowsClassNotFound( "java.lang.String][" );
        assertGetClassThrowsClassNotFound( ".hello.world" );
        assertGetClassThrowsClassNotFound( "hello..world" );
    }

    @Test
    public void testWithInterleavingWhitespace() throws ClassNotFoundException {
        Assert.assertEquals( int[].class, ClassUtils.getClass( " int [ ] " ) );
        Assert.assertEquals( long[].class, ClassUtils.getClass( "\rlong\t[\n]\r" ) );
        Assert.assertEquals( short[].class, ClassUtils.getClass( "\tshort                \t\t[]" ) );
        Assert.assertEquals( byte[].class, ClassUtils.getClass( "byte[\t\t\n\r]   " ) );
    }

    @Test
    public void testGetInnerClass() throws ClassNotFoundException {
        Assert.assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest.Inner.DeeplyNested" ) );
        Assert.assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest.Inner$DeeplyNested" ) );
        Assert.assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest$Inner$DeeplyNested" ) );
        Assert.assertEquals( Inner.DeeplyNested.class, ClassUtils.getClass( "org.apache.commons.lang3.ClassUtilsTest$Inner.DeeplyNested" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays() throws ClassNotFoundException {
        Assert.assertEquals( int[].class, ClassUtils.getClass( "int[]" ) );
        Assert.assertEquals( long[].class, ClassUtils.getClass( "long[]" ) );
        Assert.assertEquals( short[].class, ClassUtils.getClass( "short[]" ) );
        Assert.assertEquals( byte[].class, ClassUtils.getClass( "byte[]" ) );
        Assert.assertEquals( char[].class, ClassUtils.getClass( "char[]" ) );
        Assert.assertEquals( float[].class, ClassUtils.getClass( "float[]" ) );
        Assert.assertEquals( double[].class, ClassUtils.getClass( "double[]" ) );
        Assert.assertEquals( boolean[].class, ClassUtils.getClass( "boolean[]" ) );
        Assert.assertEquals( String[].class, ClassUtils.getClass( "java.lang.String[]" ) );
        Assert.assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "java.util.Map.Entry[]" ) );
        Assert.assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "java.util.Map$Entry[]" ) );
        Assert.assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "[Ljava.util.Map.Entry;" ) );
        Assert.assertEquals( java.util.Map.Entry[].class, ClassUtils.getClass( "[Ljava.util.Map$Entry;" ) );
    }

    @Test
    public void testGetClassByNormalNameArrays2D() throws ClassNotFoundException {
        Assert.assertEquals( int[][].class, ClassUtils.getClass( "int[][]" ) );
        Assert.assertEquals( long[][].class, ClassUtils.getClass( "long[][]" ) );
        Assert.assertEquals( short[][].class, ClassUtils.getClass( "short[][]" ) );
        Assert.assertEquals( byte[][].class, ClassUtils.getClass( "byte[][]" ) );
        Assert.assertEquals( char[][].class, ClassUtils.getClass( "char[][]" ) );
        Assert.assertEquals( float[][].class, ClassUtils.getClass( "float[][]" ) );
        Assert.assertEquals( double[][].class, ClassUtils.getClass( "double[][]" ) );
        Assert.assertEquals( boolean[][].class, ClassUtils.getClass( "boolean[][]" ) );
        Assert.assertEquals( String[][].class, ClassUtils.getClass( "java.lang.String[][]" ) );
    }

    @Test
    public void testGetClassWithArrayClasses2D() throws Exception {
        assertGetClassReturnsClass( String[][].class );
        assertGetClassReturnsClass( int[][].class );
        assertGetClassReturnsClass( long[][].class );
        assertGetClassReturnsClass( short[][].class );
        assertGetClassReturnsClass( byte[][].class );
        assertGetClassReturnsClass( char[][].class );
        assertGetClassReturnsClass( float[][].class );
        assertGetClassReturnsClass( double[][].class );
        assertGetClassReturnsClass( boolean[][].class );
    }

    @Test
    public void testGetClassWithArrayClasses() throws Exception {
        assertGetClassReturnsClass( String[].class );
        assertGetClassReturnsClass( int[].class );
        assertGetClassReturnsClass( long[].class );
        assertGetClassReturnsClass( short[].class );
        assertGetClassReturnsClass( byte[].class );
        assertGetClassReturnsClass( char[].class );
        assertGetClassReturnsClass( float[].class );
        assertGetClassReturnsClass( double[].class );
        assertGetClassReturnsClass( boolean[].class );
    }

    @Test
    public void testGetClassRawPrimitives() throws ClassNotFoundException {
        Assert.assertEquals( int.class, ClassUtils.getClass( "int" ) );
        Assert.assertEquals( long.class, ClassUtils.getClass( "long" ) );
        Assert.assertEquals( short.class, ClassUtils.getClass( "short" ) );
        Assert.assertEquals( byte.class, ClassUtils.getClass( "byte" ) );
        Assert.assertEquals( char.class, ClassUtils.getClass( "char" ) );
        Assert.assertEquals( float.class, ClassUtils.getClass( "float" ) );
        Assert.assertEquals( double.class, ClassUtils.getClass( "double" ) );
        Assert.assertEquals( boolean.class, ClassUtils.getClass( "boolean" ) );
        Assert.assertEquals( void.class, ClassUtils.getClass( "void" ) );
    }

    private void assertGetClassReturnsClass( final Class<?> c ) throws Exception {
        Assert.assertEquals( c, ClassUtils.getClass( c.getName() ) );
    }

    private void assertGetClassThrowsException( final String className, final Class<?> exceptionType ) throws Exception {
        try {
            ClassUtils.getClass( className );
            Assert.fail( "ClassUtils.getClass() should fail with an exception of type " + exceptionType.getName() + " when given class name \"" + className + "\"." );
        }
        catch( final Exception e ) {
            Assert.assertTrue( exceptionType.isAssignableFrom( e.getClass() ) );
        }
    }

    private void assertGetClassThrowsNullPointerException( final String className ) throws Exception {
        assertGetClassThrowsException( className, NullPointerException.class );
    }

    private void assertGetClassThrowsClassNotFound( final String className ) throws Exception {
        assertGetClassThrowsException( className, ClassNotFoundException.class );
    }

    // Show the Java bug: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4071957
    // We may have to delete this if a JDK fixes the bug.
    @Test
    public void testShowJavaBug() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<Object>());
        final Method isEmptyMethod = set.getClass().getMethod("isEmpty",  new Class[0]);
        try {
            isEmptyMethod.invoke(set, new Object[0]);
            Assert.fail("Failed to throw IllegalAccessException as expected");
        } catch(final IllegalAccessException iae) {
            // expected
        }
    }

    @Test
    public void testGetPublicMethod() throws Exception {
        // Tests with Collections$UnmodifiableSet
        final Set<?> set = Collections.unmodifiableSet(new HashSet<Object>());
        final Method isEmptyMethod = ClassUtils.getPublicMethod(set.getClass(), "isEmpty",  new Class[0]);
            Assert.assertTrue(Modifier.isPublic(isEmptyMethod.getDeclaringClass().getModifiers()));

        try {
            isEmptyMethod.invoke(set, new Object[0]);
        } catch(final java.lang.IllegalAccessException iae) {
            Assert.fail("Should not have thrown IllegalAccessException");
        }

        // Tests with a public Class
        final Method toStringMethod = ClassUtils.getPublicMethod(Object.class, "toString",  new Class[0]);
            Assert.assertEquals(Object.class.getMethod("toString", new Class[0]), toStringMethod);
    }

    @Test
    public void testToClass_object() {
//        assertNull(ClassUtils.toClass(null)); // generates warning
        Assert.assertNull(ClassUtils.toClass((Object[]) null)); // equivalent explicit cast
        
        // Additional varargs tests
        Assert.assertTrue("empty -> empty", Arrays.equals(ArrayUtils.EMPTY_CLASS_ARRAY, ClassUtils.toClass()));
        final Class<?>[] castNull = ClassUtils.toClass((Object) null); // == new Object[]{null}
        Assert.assertTrue("(Object)null -> [null]", Arrays.equals(new Object[]{null}, castNull));

        Assert.assertSame(ArrayUtils.EMPTY_CLASS_ARRAY, ClassUtils.toClass(ArrayUtils.EMPTY_OBJECT_ARRAY));

        Assert.assertTrue(Arrays.equals(new Class[] { String.class, Integer.class, Double.class },
                ClassUtils.toClass(new Object[] { "Test", Integer.valueOf(1), Double.valueOf(99d) })));

        Assert.assertTrue(Arrays.equals(new Class[] { String.class, null, Double.class },
                ClassUtils.toClass(new Object[] { "Test", null, Double.valueOf(99d) })));
    }

    @Test
    public void test_getShortCanonicalName_Object() {
        Assert.assertEquals("<null>", ClassUtils.getShortCanonicalName(null, "<null>"));
        Assert.assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(new ClassUtils(), "<null>"));
        Assert.assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(new ClassUtils[0], "<null>"));
        Assert.assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName(new ClassUtils[0][0], "<null>"));
        Assert.assertEquals("int[]", ClassUtils.getShortCanonicalName(new int[0], "<null>"));
        Assert.assertEquals("int[][]", ClassUtils.getShortCanonicalName(new int[0][0], "<null>"));

        // Inner types
        class Named {}
        Assert.assertEquals("ClassUtilsTest.6", ClassUtils.getShortCanonicalName(new Object(){}, "<null>"));
        Assert.assertEquals("ClassUtilsTest.5Named", ClassUtils.getShortCanonicalName(new Named(), "<null>"));
        Assert.assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName(new Inner(), "<null>"));
    }

    @Test
    public void test_getShortCanonicalName_Class() {
        Assert.assertEquals("ClassUtils", ClassUtils.getShortCanonicalName(ClassUtils.class));
        Assert.assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName(ClassUtils[].class));
        Assert.assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName(ClassUtils[][].class));
        Assert.assertEquals("int[]", ClassUtils.getShortCanonicalName(int[].class));
        Assert.assertEquals("int[][]", ClassUtils.getShortCanonicalName(int[][].class));
        
        // Inner types
        class Named {}
        Assert.assertEquals("ClassUtilsTest.7", ClassUtils.getShortCanonicalName(new Object(){}.getClass()));
        Assert.assertEquals("ClassUtilsTest.6Named", ClassUtils.getShortCanonicalName(Named.class));
        Assert.assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName(Inner.class));
    }

    @Test
    public void test_getShortCanonicalName_String() {
        Assert.assertEquals("ClassUtils", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils"));
        Assert.assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName("[Lorg.apache.commons.lang3.ClassUtils;"));
        Assert.assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName("[[Lorg.apache.commons.lang3.ClassUtils;"));
        Assert.assertEquals("ClassUtils[]", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils[]"));
        Assert.assertEquals("ClassUtils[][]", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtils[][]"));
        Assert.assertEquals("int[]", ClassUtils.getShortCanonicalName("[I"));
        Assert.assertEquals("int[][]", ClassUtils.getShortCanonicalName("[[I"));
        Assert.assertEquals("int[]", ClassUtils.getShortCanonicalName("int[]"));
        Assert.assertEquals("int[][]", ClassUtils.getShortCanonicalName("int[][]"));
        
        // Inner types
        Assert.assertEquals("ClassUtilsTest.6", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$6"));
        Assert.assertEquals("ClassUtilsTest.5Named", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$5Named"));
        Assert.assertEquals("ClassUtilsTest.Inner", ClassUtils.getShortCanonicalName("org.apache.commons.lang3.ClassUtilsTest$Inner"));
    }

    @Test
    public void test_getPackageCanonicalName_Object() {
        Assert.assertEquals("<null>", ClassUtils.getPackageCanonicalName(null, "<null>"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new ClassUtils(), "<null>"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new ClassUtils[0], "<null>"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new ClassUtils[0][0], "<null>"));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName(new int[0], "<null>"));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName(new int[0][0], "<null>"));
        
        // Inner types
        class Named {}
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new Object(){}, "<null>"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new Named(), "<null>"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new Inner(), "<null>"));
    }

    @Test
    public void test_getPackageCanonicalName_Class() {
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils.class));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils[].class));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(ClassUtils[][].class));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName(int[].class));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName(int[][].class));
        
        // Inner types
        class Named {}
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(new Object(){}.getClass()));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(Named.class));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName(Inner.class));
    }

    @Test
    public void test_getPackageCanonicalName_String() {
        Assert.assertEquals("org.apache.commons.lang3",
            ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils"));
        Assert.assertEquals("org.apache.commons.lang3",
            ClassUtils.getPackageCanonicalName("[Lorg.apache.commons.lang3.ClassUtils;"));
        Assert.assertEquals("org.apache.commons.lang3",
            ClassUtils.getPackageCanonicalName("[[Lorg.apache.commons.lang3.ClassUtils;"));
        Assert.assertEquals("org.apache.commons.lang3",
            ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils[]"));
        Assert.assertEquals("org.apache.commons.lang3",
            ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtils[][]"));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName("[I"));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName("[[I"));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName("int[]"));
        Assert.assertEquals("", ClassUtils.getPackageCanonicalName("int[][]"));
        
        // Inner types
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$6"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$5Named"));
        Assert.assertEquals("org.apache.commons.lang3", ClassUtils.getPackageCanonicalName("org.apache.commons.lang3.ClassUtilsTest$Inner"));
    }

    @Test
    public void testHierarchyIncludingInterfaces() {
        final Iterator<Class<?>> iter =
            ClassUtils.hierarchy(StringParameterizedChild.class, Interfaces.INCLUDE).iterator();
        Assert.assertEquals(StringParameterizedChild.class, iter.next());
        Assert.assertEquals(GenericParent.class, iter.next());
        Assert.assertEquals(GenericConsumer.class, iter.next());
        Assert.assertEquals(Object.class, iter.next());
        Assert.assertFalse(iter.hasNext());
    }

    @Test
    public void testHierarchyExcludingInterfaces() {
        final Iterator<Class<?>> iter = ClassUtils.hierarchy(StringParameterizedChild.class).iterator();
        Assert.assertEquals(StringParameterizedChild.class, iter.next());
        Assert.assertEquals(GenericParent.class, iter.next());
        Assert.assertEquals(Object.class, iter.next());
        Assert.assertFalse(iter.hasNext());
    }
}
