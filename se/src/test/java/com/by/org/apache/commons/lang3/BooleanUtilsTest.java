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

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.BooleanUtils}.
 */
public class BooleanUtilsTest {

    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new BooleanUtils());
        final Constructor<?>[] cons = BooleanUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(BooleanUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(BooleanUtils.class.getModifiers()));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void test_negate_Boolean() {
        Assert.assertSame(null, BooleanUtils.negate(null));
        Assert.assertSame(Boolean.TRUE, BooleanUtils.negate(Boolean.FALSE));
        Assert.assertSame(Boolean.FALSE, BooleanUtils.negate(Boolean.TRUE));
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_isTrue_Boolean() {
        Assert.assertTrue(BooleanUtils.isTrue(Boolean.TRUE));
        Assert.assertFalse(BooleanUtils.isTrue(Boolean.FALSE));
        Assert.assertFalse(BooleanUtils.isTrue((Boolean) null));
    }

    @Test
    public void test_isNotTrue_Boolean() {
        Assert.assertFalse(BooleanUtils.isNotTrue(Boolean.TRUE));
        Assert.assertTrue(BooleanUtils.isNotTrue(Boolean.FALSE));
        Assert.assertTrue(BooleanUtils.isNotTrue((Boolean) null));
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_isFalse_Boolean() {
        Assert.assertFalse(BooleanUtils.isFalse(Boolean.TRUE));
        Assert.assertTrue(BooleanUtils.isFalse(Boolean.FALSE));
        Assert.assertFalse(BooleanUtils.isFalse((Boolean) null));
    }

    @Test
    public void test_isNotFalse_Boolean() {
        Assert.assertTrue(BooleanUtils.isNotFalse(Boolean.TRUE));
        Assert.assertFalse(BooleanUtils.isNotFalse(Boolean.FALSE));
        Assert.assertTrue(BooleanUtils.isNotFalse((Boolean) null));
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_toBoolean_Boolean() {
        Assert.assertTrue(BooleanUtils.toBoolean(Boolean.TRUE));
        Assert.assertFalse(BooleanUtils.toBoolean(Boolean.FALSE));
        Assert.assertFalse(BooleanUtils.toBoolean((Boolean) null));
    }

    @Test
    public void test_toBooleanDefaultIfNull_Boolean_boolean() {
        Assert.assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, true));
        Assert.assertTrue(BooleanUtils.toBooleanDefaultIfNull(Boolean.TRUE, false));
        Assert.assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, true));
        Assert.assertFalse(BooleanUtils.toBooleanDefaultIfNull(Boolean.FALSE, false));
        Assert.assertTrue(BooleanUtils.toBooleanDefaultIfNull((Boolean) null, true));
        Assert.assertFalse(BooleanUtils.toBooleanDefaultIfNull((Boolean) null, false));
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void test_toBoolean_int() {
        Assert.assertTrue(BooleanUtils.toBoolean(1));
        Assert.assertTrue(BooleanUtils.toBoolean(-1));
        Assert.assertFalse(BooleanUtils.toBoolean(0));
    }
    
    @Test
    public void test_toBooleanObject_int() {
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(1));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(-1));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(0));
    }
    
    @Test
    public void test_toBooleanObject_Integer() {
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(1)));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(-1)));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(0)));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject((Integer) null));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void test_toBoolean_int_int_int() {
        Assert.assertTrue(BooleanUtils.toBoolean(6, 6, 7));
        Assert.assertFalse(BooleanUtils.toBoolean(7, 6, 7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBoolean_int_int_int_noMatch() {
        BooleanUtils.toBoolean(8, 6, 7);
    }
    
    @Test
    public void test_toBoolean_Integer_Integer_Integer() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);

        Assert.assertTrue(BooleanUtils.toBoolean((Integer) null, null, seven));
        Assert.assertFalse(BooleanUtils.toBoolean((Integer) null, six, null));

        Assert.assertTrue(BooleanUtils.toBoolean(Integer.valueOf(6), six, seven));
        Assert.assertFalse(BooleanUtils.toBoolean(Integer.valueOf(7), six, seven));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBoolean_Integer_Integer_Integer_nullValue() {
        BooleanUtils.toBoolean(null, Integer.valueOf(6), Integer.valueOf(7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBoolean_Integer_Integer_Integer_noMatch() {
        BooleanUtils.toBoolean(Integer.valueOf(8), Integer.valueOf(6), Integer.valueOf(7));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void test_toBooleanObject_int_int_int() {
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(6, 6, 7, 8));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(7, 6, 7, 8));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject(8, 6, 7, 8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBooleanObject_int_int_int_noMatch() {
        BooleanUtils.toBooleanObject(9, 6, 7, 8);
    }
    
    @Test
    public void test_toBooleanObject_Integer_Integer_Integer_Integer() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);

        Assert.assertSame(Boolean.TRUE, BooleanUtils.toBooleanObject((Integer) null, null, seven, eight));
        Assert.assertSame(Boolean.FALSE, BooleanUtils.toBooleanObject((Integer) null, six, null, eight));
        Assert.assertSame(null, BooleanUtils.toBooleanObject((Integer) null, six, seven, null));

        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject(Integer.valueOf(6), six, seven, eight));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject(Integer.valueOf(7), six, seven, eight));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject(Integer.valueOf(8), six, seven, eight));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_nullValue() {
        BooleanUtils.toBooleanObject(null, Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBooleanObject_Integer_Integer_Integer_Integer_noMatch() {
        BooleanUtils.toBooleanObject(Integer.valueOf(9), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8));
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_toInteger_boolean() {
        Assert.assertEquals(1, BooleanUtils.toInteger(true));
        Assert.assertEquals(0, BooleanUtils.toInteger(false));
    }
    
    @Test
    public void test_toIntegerObject_boolean() {
        Assert.assertEquals(Integer.valueOf(1), BooleanUtils.toIntegerObject(true));
        Assert.assertEquals(Integer.valueOf(0), BooleanUtils.toIntegerObject(false));
    }
    
    @Test
    public void test_toIntegerObject_Boolean() {
        Assert.assertEquals(Integer.valueOf(1), BooleanUtils.toIntegerObject(Boolean.TRUE));
        Assert.assertEquals(Integer.valueOf(0), BooleanUtils.toIntegerObject(Boolean.FALSE));
        Assert.assertEquals(null, BooleanUtils.toIntegerObject((Boolean) null));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void test_toInteger_boolean_int_int() {
        Assert.assertEquals(6, BooleanUtils.toInteger(true, 6, 7));
        Assert.assertEquals(7, BooleanUtils.toInteger(false, 6, 7));
    }
    
    @Test
    public void test_toInteger_Boolean_int_int_int() {
        Assert.assertEquals(6, BooleanUtils.toInteger(Boolean.TRUE, 6, 7, 8));
        Assert.assertEquals(7, BooleanUtils.toInteger(Boolean.FALSE, 6, 7, 8));
        Assert.assertEquals(8, BooleanUtils.toInteger(null, 6, 7, 8));
    }
    
    @Test
    public void test_toIntegerObject_boolean_Integer_Integer() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        Assert.assertEquals(six, BooleanUtils.toIntegerObject(true, six, seven));
        Assert.assertEquals(seven, BooleanUtils.toIntegerObject(false, six, seven));
    }
    
    @Test
    public void test_toIntegerObject_Boolean_Integer_Integer_Integer() {
        final Integer six = Integer.valueOf(6);
        final Integer seven = Integer.valueOf(7);
        final Integer eight = Integer.valueOf(8);
        Assert.assertEquals(six, BooleanUtils.toIntegerObject(Boolean.TRUE, six, seven, eight));
        Assert.assertEquals(seven, BooleanUtils.toIntegerObject(Boolean.FALSE, six, seven, eight));
        Assert.assertEquals(eight, BooleanUtils.toIntegerObject((Boolean) null, six, seven, eight));
        Assert.assertEquals(null, BooleanUtils.toIntegerObject((Boolean) null, six, seven, null));
    }
    
    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void test_toBooleanObject_String() {
        Assert.assertEquals(null, BooleanUtils.toBooleanObject((String) null));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject(""));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("false"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("no"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("off"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("FALSE"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("NO"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("OFF"));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject("oof"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("true"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("yes"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("on"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TRUE"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("ON"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("YES"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TruE"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("TruE"));

        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("y")); // yes
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("Y"));
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("t")); // true
        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("T"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("f")); // false
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("F"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("n")); // No
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("N"));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject("z"));

        Assert.assertEquals(null, BooleanUtils.toBooleanObject("ab"));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject("yoo"));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject("true "));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject("ono"));
    }
    
    @Test
    public void test_toBooleanObject_String_String_String_String() {
        Assert.assertSame(Boolean.TRUE, BooleanUtils.toBooleanObject((String) null, null, "N", "U"));
        Assert.assertSame(Boolean.FALSE, BooleanUtils.toBooleanObject((String) null, "Y", null, "U"));
        Assert.assertSame(null, BooleanUtils.toBooleanObject((String) null, "Y", "N", null));

        Assert.assertEquals(Boolean.TRUE, BooleanUtils.toBooleanObject("Y", "Y", "N", "U"));
        Assert.assertEquals(Boolean.FALSE, BooleanUtils.toBooleanObject("N", "Y", "N", "U"));
        Assert.assertEquals(null, BooleanUtils.toBooleanObject("U", "Y", "N", "U"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBooleanObject_String_String_String_String_nullValue() {
        BooleanUtils.toBooleanObject((String) null, "Y", "N", "U");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBooleanObject_String_String_String_String_noMatch() {
        BooleanUtils.toBooleanObject("X", "Y", "N", "U");
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_toBoolean_String() {
        Assert.assertFalse(BooleanUtils.toBoolean((String) null));
        Assert.assertFalse(BooleanUtils.toBoolean(""));
        Assert.assertFalse(BooleanUtils.toBoolean("off"));
        Assert.assertFalse(BooleanUtils.toBoolean("oof"));
        Assert.assertFalse(BooleanUtils.toBoolean("yep"));
        Assert.assertFalse(BooleanUtils.toBoolean("trux"));
        Assert.assertFalse(BooleanUtils.toBoolean("false"));
        Assert.assertFalse(BooleanUtils.toBoolean("a"));
        Assert.assertTrue(BooleanUtils.toBoolean("true")); // interned handled differently
        Assert.assertTrue(BooleanUtils.toBoolean(new StringBuilder("tr").append("ue").toString()));
        Assert.assertTrue(BooleanUtils.toBoolean("truE"));
        Assert.assertTrue(BooleanUtils.toBoolean("trUe"));
        Assert.assertTrue(BooleanUtils.toBoolean("trUE"));
        Assert.assertTrue(BooleanUtils.toBoolean("tRue"));
        Assert.assertTrue(BooleanUtils.toBoolean("tRuE"));
        Assert.assertTrue(BooleanUtils.toBoolean("tRUe"));
        Assert.assertTrue(BooleanUtils.toBoolean("tRUE"));
        Assert.assertTrue(BooleanUtils.toBoolean("TRUE"));
        Assert.assertTrue(BooleanUtils.toBoolean("TRUe"));
        Assert.assertTrue(BooleanUtils.toBoolean("TRuE"));
        Assert.assertTrue(BooleanUtils.toBoolean("TRue"));
        Assert.assertTrue(BooleanUtils.toBoolean("TrUE"));
        Assert.assertTrue(BooleanUtils.toBoolean("TrUe"));
        Assert.assertTrue(BooleanUtils.toBoolean("TruE"));
        Assert.assertTrue(BooleanUtils.toBoolean("True"));
        Assert.assertTrue(BooleanUtils.toBoolean("on"));
        Assert.assertTrue(BooleanUtils.toBoolean("oN"));
        Assert.assertTrue(BooleanUtils.toBoolean("On"));
        Assert.assertTrue(BooleanUtils.toBoolean("ON"));
        Assert.assertTrue(BooleanUtils.toBoolean("yes"));
        Assert.assertTrue(BooleanUtils.toBoolean("yeS"));
        Assert.assertTrue(BooleanUtils.toBoolean("yEs"));
        Assert.assertTrue(BooleanUtils.toBoolean("yES"));
        Assert.assertTrue(BooleanUtils.toBoolean("Yes"));
        Assert.assertTrue(BooleanUtils.toBoolean("YeS"));
        Assert.assertTrue(BooleanUtils.toBoolean("YEs"));
        Assert.assertTrue(BooleanUtils.toBoolean("YES"));
        Assert.assertFalse(BooleanUtils.toBoolean("yes?"));
        Assert.assertFalse(BooleanUtils.toBoolean("tru"));

        Assert.assertFalse(BooleanUtils.toBoolean("no"));
        Assert.assertFalse(BooleanUtils.toBoolean("off"));
        Assert.assertFalse(BooleanUtils.toBoolean("yoo"));
    }

    @Test
    public void test_toBoolean_String_String_String() {
        Assert.assertTrue(BooleanUtils.toBoolean((String) null, null, "N"));
        Assert.assertFalse(BooleanUtils.toBoolean((String) null, "Y", null));
        Assert.assertTrue(BooleanUtils.toBoolean("Y", "Y", "N"));
        Assert.assertTrue(BooleanUtils.toBoolean("Y", new String("Y"), new String("N")));
        Assert.assertFalse(BooleanUtils.toBoolean("N", "Y", "N"));
        Assert.assertFalse(BooleanUtils.toBoolean("N", new String("Y"), new String("N")));
        Assert.assertTrue(BooleanUtils.toBoolean((String) null, null, null));
        Assert.assertTrue(BooleanUtils.toBoolean("Y", "Y", "Y"));
        Assert.assertTrue(BooleanUtils.toBoolean("Y", new String("Y"), new String("Y")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBoolean_String_String_String_nullValue() {
        BooleanUtils.toBoolean(null, "Y", "N");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_toBoolean_String_String_String_noMatch() {
        BooleanUtils.toBoolean("X", "Y", "N");
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_toStringTrueFalse_Boolean() {
        Assert.assertEquals(null, BooleanUtils.toStringTrueFalse((Boolean) null));
        Assert.assertEquals("true", BooleanUtils.toStringTrueFalse(Boolean.TRUE));
        Assert.assertEquals("false", BooleanUtils.toStringTrueFalse(Boolean.FALSE));
    }
    
    @Test
    public void test_toStringOnOff_Boolean() {
        Assert.assertEquals(null, BooleanUtils.toStringOnOff((Boolean) null));
        Assert.assertEquals("on", BooleanUtils.toStringOnOff(Boolean.TRUE));
        Assert.assertEquals("off", BooleanUtils.toStringOnOff(Boolean.FALSE));
    }
    
    @Test
    public void test_toStringYesNo_Boolean() {
        Assert.assertEquals(null, BooleanUtils.toStringYesNo((Boolean) null));
        Assert.assertEquals("yes", BooleanUtils.toStringYesNo(Boolean.TRUE));
        Assert.assertEquals("no", BooleanUtils.toStringYesNo(Boolean.FALSE));
    }
    
    @Test
    public void test_toString_Boolean_String_String_String() {
        Assert.assertEquals("U", BooleanUtils.toString((Boolean) null, "Y", "N", "U"));
        Assert.assertEquals("Y", BooleanUtils.toString(Boolean.TRUE, "Y", "N", "U"));
        Assert.assertEquals("N", BooleanUtils.toString(Boolean.FALSE, "Y", "N", "U"));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void test_toStringTrueFalse_boolean() {
        Assert.assertEquals("true", BooleanUtils.toStringTrueFalse(true));
        Assert.assertEquals("false", BooleanUtils.toStringTrueFalse(false));
    }
    
    @Test
    public void test_toStringOnOff_boolean() {
        Assert.assertEquals("on", BooleanUtils.toStringOnOff(true));
        Assert.assertEquals("off", BooleanUtils.toStringOnOff(false));
    }
    
    @Test
    public void test_toStringYesNo_boolean() {
        Assert.assertEquals("yes", BooleanUtils.toStringYesNo(true));
        Assert.assertEquals("no", BooleanUtils.toStringYesNo(false));
    }
    
    @Test
    public void test_toString_boolean_String_String_String() {
        Assert.assertEquals("Y", BooleanUtils.toString(true, "Y", "N"));
        Assert.assertEquals("N", BooleanUtils.toString(false, "Y", "N"));
    }
    
    //  testXor
    //  -----------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testXor_primitive_nullInput() {
        BooleanUtils.xor((boolean[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testXor_primitive_emptyInput() {
        BooleanUtils.xor(new boolean[] {});
    }

    @Test
    public void testXor_primitive_validInput_2items() {
        Assert.assertEquals(
            "true ^ true",
            true ^ true ,
            BooleanUtils.xor(new boolean[] { true, true }));

        Assert.assertEquals(
            "false ^ false",
            false ^ false,
            BooleanUtils.xor(new boolean[] { false, false }));

        Assert.assertEquals(
            "true ^ false",
            true ^ false,
            BooleanUtils.xor(new boolean[] { true, false }));

        Assert.assertEquals(
            "false ^ true",
            false ^ true,
            BooleanUtils.xor(new boolean[] { false, true }));
    }

    @Test
    public void testXor_primitive_validInput_3items() {
        Assert.assertEquals(
            "false ^ false ^ false",
            false ^ false ^ false,
            BooleanUtils.xor(new boolean[] { false, false, false }));

        Assert.assertEquals(
            "false ^ false ^ true",
            false ^ false ^ true,
            BooleanUtils.xor(new boolean[] { false, false, true }));

        Assert.assertEquals(
            "false ^ true ^ false",
            false ^ true ^ false,
            BooleanUtils.xor(new boolean[] { false, true, false }));

        Assert.assertEquals(
            "false ^ true ^ true",
            false ^ true ^ true,
            BooleanUtils.xor(new boolean[] { false, true, true }));

        Assert.assertEquals(
            "true ^ false ^ false",
            true ^ false ^ false,
            BooleanUtils.xor(new boolean[] { true, false, false }));

        Assert.assertEquals(
            "true ^ false ^ true",
            true ^ false ^ true,
            BooleanUtils.xor(new boolean[] { true, false, true }));

        Assert.assertEquals(
            "true ^ true ^ false",
            true ^ true ^ false,
            BooleanUtils.xor(new boolean[] { true, true, false }));

        Assert.assertEquals(
            "true ^ true ^ true",
            true ^ true ^ true,
            BooleanUtils.xor(new boolean[] { true, true, true }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testXor_object_nullInput() {
        BooleanUtils.xor((Boolean[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testXor_object_emptyInput() {
        BooleanUtils.xor(new Boolean[] {});
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testXor_object_nullElementInput() {
        BooleanUtils.xor(new Boolean[] {null});
    }

    @Test
    public void testXor_object_validInput_2items() {
        Assert.assertEquals(
            "false ^ false",
            false ^ false,
            BooleanUtils
                .xor(new Boolean[] { Boolean.FALSE, Boolean.FALSE })
                .booleanValue());

        Assert.assertEquals(
            "false ^ true",
            false ^ true,
            BooleanUtils
                .xor(new Boolean[] { Boolean.FALSE, Boolean.TRUE })
                .booleanValue());

        Assert.assertEquals(
            "true ^ false",
            true ^ false,
            BooleanUtils
                .xor(new Boolean[] { Boolean.TRUE, Boolean.FALSE })
                .booleanValue());

        Assert.assertEquals(
            "true ^ true",
            true ^ true,
            BooleanUtils
                .xor(new Boolean[] { Boolean.TRUE, Boolean.TRUE })
                .booleanValue());
    }

    @Test
    public void testXor_object_validInput_3items() {
        Assert.assertEquals(
                "false ^ false ^ false",
                false ^ false ^ false,
                BooleanUtils.xor(
                        new Boolean[] {
                                Boolean.FALSE,
                                Boolean.FALSE,
                                Boolean.FALSE })
                        .booleanValue());

        Assert.assertEquals(
            "false ^ false ^ true",
            false ^ false ^ true,
            BooleanUtils
                .xor(
                    new Boolean[] {
                        Boolean.FALSE,
                        Boolean.FALSE,
                        Boolean.TRUE })
                .booleanValue());

        Assert.assertEquals(
            "false ^ true ^ false",
            false ^ true ^ false,
            BooleanUtils
                .xor(
                    new Boolean[] {
                        Boolean.FALSE,
                        Boolean.TRUE,
                        Boolean.FALSE })
                .booleanValue());

        Assert.assertEquals(
            "true ^ false ^ false",
            true ^ false ^ false,
            BooleanUtils
                .xor(
                    new Boolean[] {
                        Boolean.TRUE,
                        Boolean.FALSE,
                        Boolean.FALSE })
                .booleanValue());

        Assert.assertEquals(
                "true ^ false ^ true",
                true ^ false ^ true,
                BooleanUtils.xor(
                        new Boolean[] {
                                Boolean.TRUE,
                                Boolean.FALSE,
                                Boolean.TRUE })
                        .booleanValue());

        Assert.assertEquals(
            "true ^ true ^ false",
            true ^ true ^ false,
            BooleanUtils.xor(
                    new Boolean[] {
                        Boolean.TRUE,
                        Boolean.TRUE,
                        Boolean.FALSE })
                .booleanValue());

        Assert.assertEquals(
            "false ^ true ^ true",
            false ^ true ^ true,
            BooleanUtils.xor(
                    new Boolean[] {
                        Boolean.FALSE,
                        Boolean.TRUE,
                        Boolean.TRUE })
                .booleanValue());

        Assert.assertEquals(
                "true ^ true ^ true",
                true ^ true ^ true,
                BooleanUtils
                        .xor(new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE })
                        .booleanValue());
    }

    //  testAnd
    //  -----------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testAnd_primitive_nullInput() {
        BooleanUtils.and((boolean[]) null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAnd_primitive_emptyInput() {
        BooleanUtils.and(new boolean[] {});
    }
    
    @Test
    public void testAnd_primitive_validInput_2items() {
        Assert.assertTrue(
            "False result for (true, true)",
            BooleanUtils.and(new boolean[] { true, true }));
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils.and(new boolean[] { false, false }));
        
        Assert.assertTrue(
            "True result for (true, false)",
            ! BooleanUtils.and(new boolean[] { true, false }));
        
        Assert.assertTrue(
            "True result for (false, true)",
            ! BooleanUtils.and(new boolean[] { false, true }));
    }
    
    @Test
    public void testAnd_primitive_validInput_3items() {
        Assert.assertTrue(
            "True result for (false, false, true)",
            ! BooleanUtils.and(new boolean[] { false, false, true }));
        
        Assert.assertTrue(
            "True result for (false, true, false)",
            ! BooleanUtils.and(new boolean[] { false, true, false }));
        
        Assert.assertTrue(
            "True result for (true, false, false)",
            ! BooleanUtils.and(new boolean[] { true, false, false }));
        
        Assert.assertTrue(
            "False result for (true, true, true)",
            BooleanUtils.and(new boolean[] { true, true, true }));
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils.and(new boolean[] { false, false, false }));
        
        Assert.assertTrue(
            "True result for (true, true, false)",
            ! BooleanUtils.and(new boolean[] { true, true, false }));
        
        Assert.assertTrue(
            "True result for (true, false, true)",
            ! BooleanUtils.and(new boolean[] { true, false, true }));
        
        Assert.assertTrue(
            "True result for (false, true, true)",
            ! BooleanUtils.and(new boolean[] { false, true, true }));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAnd_object_nullInput() {
        BooleanUtils.and((Boolean[]) null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAnd_object_emptyInput() {
        BooleanUtils.and(new Boolean[] {});
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAnd_object_nullElementInput() {
        BooleanUtils.and(new Boolean[] {null});
    }
    
    @Test
    public void testAnd_object_validInput_2items() {
        Assert.assertTrue(
            "False result for (true, true)",
            BooleanUtils
            .and(new Boolean[] { Boolean.TRUE, Boolean.TRUE })
            .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils
            .and(new Boolean[] { Boolean.FALSE, Boolean.FALSE })
            .booleanValue());
        
        Assert.assertTrue(
            "True result for (true, false)",
            ! BooleanUtils
            .and(new Boolean[] { Boolean.TRUE, Boolean.FALSE })
            .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, true)",
            ! BooleanUtils
            .and(new Boolean[] { Boolean.FALSE, Boolean.TRUE })
            .booleanValue());
    }
    
    @Test
    public void testAnd_object_validInput_3items() {
        Assert.assertTrue(
            "True result for (false, false, true)",
            ! BooleanUtils
            .and(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.FALSE,
                    Boolean.TRUE })
                    .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, true, false)",
            ! BooleanUtils
            .and(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.TRUE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "True result for (true, false, false)",
            ! BooleanUtils
            .and(
                new Boolean[] {
                    Boolean.TRUE,
                    Boolean.FALSE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (true, true, true)",
            BooleanUtils
            .and(new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE })
            .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils.and(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.FALSE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "True result for (true, true, false)",
            ! BooleanUtils.and(
                new Boolean[] {
                    Boolean.TRUE,
                    Boolean.TRUE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "True result for (true, false, true)",
            ! BooleanUtils.and(
                new Boolean[] {
                    Boolean.TRUE,
                    Boolean.FALSE,
                    Boolean.TRUE })
                    .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, true, true)",
            ! BooleanUtils.and(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.TRUE,
                    Boolean.TRUE })
                    .booleanValue());
    }
    
    //  testOr
    //  -----------------------------------------------------------------------
    @Test(expected = IllegalArgumentException.class)
    public void testOr_primitive_nullInput() {
        BooleanUtils.or((boolean[]) null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOr_primitive_emptyInput() {
        BooleanUtils.or(new boolean[] {});
    }
    
    @Test
    public void testOr_primitive_validInput_2items() {
        Assert.assertTrue(
            "False result for (true, true)",
            BooleanUtils.or(new boolean[] { true, true }));
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils.or(new boolean[] { false, false }));
        
        Assert.assertTrue(
            "False result for (true, false)",
            BooleanUtils.or(new boolean[] { true, false }));
        
        Assert.assertTrue(
            "False result for (false, true)",
            BooleanUtils.or(new boolean[] { false, true }));
    }
    
    @Test
    public void testOr_primitive_validInput_3items() {
        Assert.assertTrue(
            "False result for (false, false, true)",
            BooleanUtils.or(new boolean[] { false, false, true }));
        
        Assert.assertTrue(
            "False result for (false, true, false)",
            BooleanUtils.or(new boolean[] { false, true, false }));
        
        Assert.assertTrue(
            "False result for (true, false, false)",
            BooleanUtils.or(new boolean[] { true, false, false }));
        
        Assert.assertTrue(
            "False result for (true, true, true)",
            BooleanUtils.or(new boolean[] { true, true, true }));
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils.or(new boolean[] { false, false, false }));
        
        Assert.assertTrue(
            "False result for (true, true, false)",
            BooleanUtils.or(new boolean[] { true, true, false }));
        
        Assert.assertTrue(
            "False result for (true, false, true)",
            BooleanUtils.or(new boolean[] { true, false, true }));
        
        Assert.assertTrue(
            "False result for (false, true, true)",
            BooleanUtils.or(new boolean[] { false, true, true }));
    
    }
    @Test(expected = IllegalArgumentException.class)
    public void testOr_object_nullInput() {
        BooleanUtils.or((Boolean[]) null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOr_object_emptyInput() {
        BooleanUtils.or(new Boolean[] {});
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOr_object_nullElementInput() {
        BooleanUtils.or(new Boolean[] {null});
    }
    
    @Test
    public void testOr_object_validInput_2items() {
        Assert.assertTrue(
            "False result for (true, true)",
            BooleanUtils
            .or(new Boolean[] { Boolean.TRUE, Boolean.TRUE })
            .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils
            .or(new Boolean[] { Boolean.FALSE, Boolean.FALSE })
            .booleanValue());
        
        Assert.assertTrue(
            "False result for (true, false)",
            BooleanUtils
            .or(new Boolean[] { Boolean.TRUE, Boolean.FALSE })
            .booleanValue());
        
        Assert.assertTrue(
            "False result for (false, true)",
            BooleanUtils
            .or(new Boolean[] { Boolean.FALSE, Boolean.TRUE })
            .booleanValue());
    }
    
    @Test
    public void testOr_object_validInput_3items() {
        Assert.assertTrue(
            "False result for (false, false, true)",
            BooleanUtils
            .or(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.FALSE,
                    Boolean.TRUE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (false, true, false)",
            BooleanUtils
            .or(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.TRUE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (true, false, false)",
            BooleanUtils
            .or(
                new Boolean[] {
                    Boolean.TRUE,
                    Boolean.FALSE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (true, true, true)",
            BooleanUtils
            .or(new Boolean[] { Boolean.TRUE, Boolean.TRUE, Boolean.TRUE })
            .booleanValue());
        
        Assert.assertTrue(
            "True result for (false, false)",
            ! BooleanUtils.or(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.FALSE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (true, true, false)",
            BooleanUtils.or(
                new Boolean[] {
                    Boolean.TRUE,
                    Boolean.TRUE,
                    Boolean.FALSE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (true, false, true)",
            BooleanUtils.or(
                new Boolean[] {
                    Boolean.TRUE,
                    Boolean.FALSE,
                    Boolean.TRUE })
                    .booleanValue());
        
        Assert.assertTrue(
            "False result for (false, true, true)",
            BooleanUtils.or(
                new Boolean[] {
                    Boolean.FALSE,
                    Boolean.TRUE,
                    Boolean.TRUE })
                    .booleanValue());
    }

    @Test
    public void testCompare(){
        Assert.assertTrue(BooleanUtils.compare(true, false) > 0);
        Assert.assertTrue(BooleanUtils.compare(true, true) == 0);
        Assert.assertTrue(BooleanUtils.compare(false, false) == 0);
        Assert.assertTrue(BooleanUtils.compare(false, true) < 0);
    }
    
}
