/*
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
 */
package by.org.apache.commons.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.Validate}.
 */
public class ValidateTest  {

    //-----------------------------------------------------------------------
    @Test
    public void testIsTrue1() {
        Validate.isTrue(true);
        try {
            Validate.isTrue(false);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated expression is false", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIsTrue2() {
        Validate.isTrue(true, "MSG");
        try {
            Validate.isTrue(false, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIsTrue3() {
        Validate.isTrue(true, "MSG", 6);
        try {
            Validate.isTrue(false, "MSG", 6);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIsTrue4() {
        Validate.isTrue(true, "MSG", 7);
        try {
            Validate.isTrue(false, "MSG", 7);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIsTrue5() {
        Validate.isTrue(true, "MSG", 7.4d);
        try {
            Validate.isTrue(false, "MSG", 7.4d);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @SuppressWarnings("unused")
    @Test
    public void testNotNull1() {
        Validate.notNull(new Object());
        try {
            Validate.notNull(null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated object is null", ex.getMessage());
        }

        final String str = "Hi";
        final String testStr = Validate.notNull(str);
        Assert.assertSame(str, testStr);
    }

    //-----------------------------------------------------------------------
    @SuppressWarnings("unused")
    @Test
    public void testNotNull2() {
        Validate.notNull(new Object(), "MSG");
        try {
            Validate.notNull(null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }

        final String str = "Hi";
        final String testStr = Validate.notNull(str, "Message");
        Assert.assertSame(str, testStr);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyArray1() {
        Validate.notEmpty(new Object[] {null});
        try {
            Validate.notEmpty((Object[]) null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated array is empty", ex.getMessage());
        }
        try {
            Validate.notEmpty(new Object[0]);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated array is empty", ex.getMessage());
        }

        final String[] array = new String[] {"hi"};
        final String[] test = Validate.notEmpty(array);
        Assert.assertSame(array, test);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyArray2() {
        Validate.notEmpty(new Object[] {null}, "MSG");
        try {
            Validate.notEmpty((Object[]) null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        try {
            Validate.notEmpty(new Object[0], "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }

        final String[] array = new String[] {"hi"};
        final String[] test = Validate.notEmpty(array, "Message");
        Assert.assertSame(array, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyCollection1() {
        final Collection<Integer> coll = new ArrayList<Integer>();
        try {
            Validate.notEmpty((Collection<?>) null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated collection is empty", ex.getMessage());
        }
        try {
            Validate.notEmpty(coll);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated collection is empty", ex.getMessage());
        }
        coll.add(Integer.valueOf(8));
        Validate.notEmpty(coll);

        final Collection<Integer> test = Validate.notEmpty(coll);
        Assert.assertSame(coll, test);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyCollection2() {
        final Collection<Integer> coll = new ArrayList<Integer>();
        try {
            Validate.notEmpty((Collection<?>) null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        try {
            Validate.notEmpty(coll, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        coll.add(Integer.valueOf(8));
        Validate.notEmpty(coll, "MSG");

        final Collection<Integer> test = Validate.notEmpty(coll, "Message");
        Assert.assertSame(coll, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyMap1() {
        final Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            Validate.notEmpty((Map<?, ?>) null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated map is empty", ex.getMessage());
        }
        try {
            Validate.notEmpty(map);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated map is empty", ex.getMessage());
        }
        map.put("ll", Integer.valueOf(8));
        Validate.notEmpty(map);

        final Map<String, Integer> test = Validate.notEmpty(map);
        Assert.assertSame(map, test);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyMap2() {
        final Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            Validate.notEmpty((Map<?, ?>) null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        try {
            Validate.notEmpty(map, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        map.put("ll", Integer.valueOf(8));
        Validate.notEmpty(map, "MSG");

        final Map<String, Integer> test = Validate.notEmpty(map, "Message");
        Assert.assertSame(map, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyString1() {
        Validate.notEmpty("hjl");
        try {
            Validate.notEmpty((String) null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated character sequence is empty", ex.getMessage());
        }
        try {
            Validate.notEmpty("");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated character sequence is empty", ex.getMessage());
        }

        final String str = "Hi";
        final String testStr = Validate.notEmpty(str);
        Assert.assertSame(str, testStr);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotEmptyString2() {
        Validate.notEmpty("a", "MSG");
        try {
            Validate.notEmpty((String) null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        try {
            Validate.notEmpty("", "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }

        final String str = "Hi";
        final String testStr = Validate.notEmpty(str, "Message");
        Assert.assertSame(str, testStr);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankNullStringShouldThrow() {
        //given
        final String string = null;

        try {
            //when
            Validate.notBlank(string);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException e) {
            //then
            Assert.assertEquals("The validated character sequence is blank", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgNullStringShouldThrow() {
        //given
        final String string = null;

        try {
            //when
            Validate.notBlank(string, "Message");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException e) {
            //then
            Assert.assertEquals("Message", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankEmptyStringShouldThrow() {
        //given
        final String string = "";

        try {
            //when
            Validate.notBlank(string);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            //then
            Assert.assertEquals("The validated character sequence is blank", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankBlankStringWithWhitespacesShouldThrow() {
        //given
        final String string = "   ";

        try {
            //when
            Validate.notBlank(string);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            //then
            Assert.assertEquals("The validated character sequence is blank", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankBlankStringWithNewlinesShouldThrow() {
        //given
        final String string = " \n \t \r \n ";

        try {
            //when
            Validate.notBlank(string);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            //then
            Assert.assertEquals("The validated character sequence is blank", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgBlankStringShouldThrow() {
        //given
        final String string = " \n \t \r \n ";

        try {
            //when
            Validate.notBlank(string, "Message");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            //then
            Assert.assertEquals("Message", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgBlankStringWithWhitespacesShouldThrow() {
        //given
        final String string = "   ";

        try {
            //when
            Validate.notBlank(string, "Message");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            //then
            Assert.assertEquals("Message", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgEmptyStringShouldThrow() {
        //given
        final String string = "";

        try {
            //when
            Validate.notBlank(string, "Message");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            //then
            Assert.assertEquals("Message", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankNotBlankStringShouldNotThrow() {
        //given
        final String string = "abc";

        //when
        Validate.notBlank(string);

        //then should not throw
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankNotBlankStringWithWhitespacesShouldNotThrow() {
        //given
        final String string = "  abc   ";

        //when
        Validate.notBlank(string);

        //then should not throw
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankNotBlankStringWithNewlinesShouldNotThrow() {
        //given
        final String string = " \n \t abc \r \n ";

        //when
        Validate.notBlank(string);

        //then should not throw
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgNotBlankStringShouldNotThrow() {
        //given
        final String string = "abc";

        //when
        Validate.notBlank(string, "Message");

        //then should not throw
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgNotBlankStringWithWhitespacesShouldNotThrow() {
        //given
        final String string = "  abc   ";

        //when
        Validate.notBlank(string, "Message");

        //then should not throw
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankMsgNotBlankStringWithNewlinesShouldNotThrow() {
        //given
        final String string = " \n \t abc \r \n ";

        //when
        Validate.notBlank(string, "Message");

        //then should not throw
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNotBlankReturnValues1() {
        final String str = "Hi";
        final String test = Validate.notBlank(str);
        Assert.assertSame(str, test);
    }

    @Test
    public void testNotBlankReturnValues2() {
        final String str = "Hi";
        final String test = Validate.notBlank(str, "Message");
        Assert.assertSame(str, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNoNullElementsArray1() {
        String[] array = new String[] {"a", "b"};
        Validate.noNullElements(array);
        try {
            Validate.noNullElements((Object[]) null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated object is null", ex.getMessage());
        }
        array[1] = null;
        try {
            Validate.noNullElements(array);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated array contains null element at index: 1", ex.getMessage());
        }

        array = new String[] {"a", "b"};
        final String[] test = Validate.noNullElements(array);
        Assert.assertSame(array, test);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNoNullElementsArray2() {
        String[] array = new String[] {"a", "b"};
        Validate.noNullElements(array, "MSG");
        try {
            Validate.noNullElements((Object[]) null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated object is null", ex.getMessage());
        }
        array[1] = null;
        try {
            Validate.noNullElements(array, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }

        array = new String[] {"a", "b"};
        final String[] test = Validate.noNullElements(array, "Message");
        Assert.assertSame(array, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testNoNullElementsCollection1() {
        final List<String> coll = new ArrayList<String>();
        coll.add("a");
        coll.add("b");
        Validate.noNullElements(coll);
        try {
            Validate.noNullElements((Collection<?>) null);
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated object is null", ex.getMessage());
        }
        coll.set(1, null);
        try {
            Validate.noNullElements(coll);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated collection contains null element at index: 1", ex.getMessage());
        }

        coll.set(1, "b");
        final List<String> test = Validate.noNullElements(coll);
        Assert.assertSame(coll, test);
    }

    //-----------------------------------------------------------------------
    @Test
    public void testNoNullElementsCollection2() {
        final List<String> coll = new ArrayList<String>();
        coll.add("a");
        coll.add("b");
        Validate.noNullElements(coll, "MSG");
        try {
            Validate.noNullElements((Collection<?>) null, "MSG");
            Assert.fail("Expecting NullPointerException");
        } catch (final NullPointerException ex) {
            Assert.assertEquals("The validated object is null", ex.getMessage());
        }
        coll.set(1, null);
        try {
            Validate.noNullElements(coll, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }

        coll.set(1, "b");
        final List<String> test = Validate.noNullElements(coll, "Message");
        Assert.assertSame(coll, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new Validate());
        final Constructor<?>[] cons = Validate.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(Validate.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(Validate.class.getModifiers()));
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testValidIndex_withMessage_array() {
        final Object[] array = new Object[2];
        Validate.validIndex(array, 0, "Broken: ");
        Validate.validIndex(array, 1, "Broken: ");
        try {
            Validate.validIndex(array, -1, "Broken: ");
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("Broken: ", ex.getMessage());
        }
        try {
            Validate.validIndex(array, 2, "Broken: ");
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("Broken: ", ex.getMessage());
        }

        final String[] strArray = new String[] {"Hi"};
        final String[] test = Validate.noNullElements(strArray, "Message");
        Assert.assertSame(strArray, test);
    }

    @Test
    public void testValidIndex_array() {
        final Object[] array = new Object[2];
        Validate.validIndex(array, 0);
        Validate.validIndex(array, 1);
        try {
            Validate.validIndex(array, -1);
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("The validated array index is invalid: -1", ex.getMessage());
        }
        try {
            Validate.validIndex(array, 2);
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("The validated array index is invalid: 2", ex.getMessage());
        }

        final String[] strArray = new String[] {"Hi"};
        final String[] test = Validate.noNullElements(strArray);
        Assert.assertSame(strArray, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testValidIndex_withMessage_collection() {
        final Collection<String> coll = new ArrayList<String>();
        coll.add(null);
        coll.add(null);
        Validate.validIndex(coll, 0, "Broken: ");
        Validate.validIndex(coll, 1, "Broken: ");
        try {
            Validate.validIndex(coll, -1, "Broken: ");
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("Broken: ", ex.getMessage());
        }
        try {
            Validate.validIndex(coll, 2, "Broken: ");
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("Broken: ", ex.getMessage());
        }

        final List<String> strColl = Arrays.asList(new String[] {"Hi"});
        final List<String> test = Validate.validIndex(strColl, 0, "Message");
        Assert.assertSame(strColl, test);
    }

    @Test
    public void testValidIndex_collection() {
        final Collection<String> coll = new ArrayList<String>();
        coll.add(null);
        coll.add(null);
        Validate.validIndex(coll, 0);
        Validate.validIndex(coll, 1);
        try {
            Validate.validIndex(coll, -1);
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("The validated collection index is invalid: -1", ex.getMessage());
        }
        try {
            Validate.validIndex(coll, 2);
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("The validated collection index is invalid: 2", ex.getMessage());
        }

        final List<String> strColl = Arrays.asList(new String[] {"Hi"});
        final List<String> test = Validate.validIndex(strColl, 0);
        Assert.assertSame(strColl, test);
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------
    @Test
    public void testValidIndex_withMessage_charSequence() {
        final CharSequence str = "Hi";
        Validate.validIndex(str, 0, "Broken: ");
        Validate.validIndex(str, 1, "Broken: ");
        try {
            Validate.validIndex(str, -1, "Broken: ");
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("Broken: ", ex.getMessage());
        }
        try {
            Validate.validIndex(str, 2, "Broken: ");
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("Broken: ", ex.getMessage());
        }

        final String input = "Hi";
        final String test = Validate.validIndex(input, 0, "Message");
        Assert.assertSame(input, test);
    }

    @Test
    public void testValidIndex_charSequence() {
        final CharSequence str = "Hi";
        Validate.validIndex(str, 0);
        Validate.validIndex(str, 1);
        try {
            Validate.validIndex(str, -1);
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("The validated character sequence index is invalid: -1", ex.getMessage());
        }
        try {
            Validate.validIndex(str, 2);
            Assert.fail("Expecting IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException ex) {
            Assert.assertEquals("The validated character sequence index is invalid: 2", ex.getMessage());
        }

        final String input = "Hi";
        final String test = Validate.validIndex(input, 0);
        Assert.assertSame(input, test);
    }

    @Test
    public void testMatchesPattern()
    {
        final CharSequence str = "hi";
        Validate.matchesPattern(str, "[a-z]*");
        try
        {
            Validate.matchesPattern(str, "[0-9]*");
            Assert.fail("Expecting IllegalArgumentException");
        }
        catch (final IllegalArgumentException e)
        {
            Assert.assertEquals("The string hi does not match the pattern [0-9]*", e.getMessage());
        }
    }

    @Test
    public void testMatchesPattern_withMessage()
    {
        final CharSequence str = "hi";
        Validate.matchesPattern(str, "[a-z]*", "Does not match");
        try
        {
            Validate.matchesPattern(str, "[0-9]*", "Does not match");
            Assert.fail("Expecting IllegalArgumentException");
        }
        catch (final IllegalArgumentException e)
        {
            Assert.assertEquals("Does not match", e.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    @Test
    public void testNotNaN1() {
        Validate.notNaN(0.0);
        Validate.notNaN(Double.POSITIVE_INFINITY);
        Validate.notNaN(Double.NEGATIVE_INFINITY);
        try {
            Validate.notNaN(Double.NaN);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The validated value is not a number", ex.getMessage());
        }
    }

    @Test
    public void testNotNaN2() {
        Validate.notNaN(0.0, "MSG");
        Validate.notNaN(Double.POSITIVE_INFINITY, "MSG");
        Validate.notNaN(Double.NEGATIVE_INFINITY, "MSG");
        try {
            Validate.notNaN(Double.NaN, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    @Test
    public void testFinite1() {
        Validate.finite(0.0);
        try {
            Validate.finite(Double.POSITIVE_INFINITY);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The value is invalid: Infinity", ex.getMessage());
        }
        try {
            Validate.finite(Double.NEGATIVE_INFINITY);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The value is invalid: -Infinity", ex.getMessage());
        }
        try {
            Validate.finite(Double.NaN);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("The value is invalid: NaN", ex.getMessage());
        }
    }

    @Test
    public void testFinite2() {
        Validate.finite(0.0, "MSG");
        try {
            Validate.finite(Double.POSITIVE_INFINITY, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        try {
            Validate.finite(Double.NEGATIVE_INFINITY, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
        try {
            Validate.finite(Double.NaN, "MSG");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException ex) {
            Assert.assertEquals("MSG", ex.getMessage());
        }
    }

    //-----------------------------------------------------------------------
    //-----------------------------------------------------------------------

    @Test
    public void testInclusiveBetween()
    {
        Validate.inclusiveBetween("a", "c", "b");
        try {
            Validate.inclusiveBetween("0", "5", "6");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 6 is not in the specified inclusive range of 0 to 5", e.getMessage());
        }
    }

    @Test
    public void testInclusiveBetween_withMessage()
    {
        Validate.inclusiveBetween("a", "c", "b", "Error");
        try {
            Validate.inclusiveBetween("0", "5", "6", "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testInclusiveBetweenLong()
    {
        Validate.inclusiveBetween(0, 2, 1);
        Validate.inclusiveBetween(0, 2, 2);
        try {
            Validate.inclusiveBetween(0, 5, 6);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 6 is not in the specified inclusive range of 0 to 5", e.getMessage());
        }
    }

    @Test
    public void testInclusiveBetweenLong_withMessage()
    {
        Validate.inclusiveBetween(0, 2, 1, "Error");
        Validate.inclusiveBetween(0, 2, 2, "Error");
        try {
            Validate.inclusiveBetween(0, 5, 6, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testInclusiveBetweenDouble()
    {
        Validate.inclusiveBetween(0.1, 2.1, 1.1);
        Validate.inclusiveBetween(0.1, 2.1, 2.1);
        try {
            Validate.inclusiveBetween(0.1, 5.1, 6.1);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 6.1 is not in the specified inclusive range of 0.1 to 5.1", e.getMessage());
        }
    }

    @Test
    public void testInclusiveBetweenDouble_withMessage()
    {
        Validate.inclusiveBetween(0.1, 2.1, 1.1, "Error");
        Validate.inclusiveBetween(0.1, 2.1, 2.1, "Error");
        try {
            Validate.inclusiveBetween(0.1, 5.1, 6.1, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testExclusiveBetween()
    {
        Validate.exclusiveBetween("a", "c", "b");
        try {
            Validate.exclusiveBetween("0", "5", "6");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 6 is not in the specified exclusive range of 0 to 5", e.getMessage());
        }
        try {
            Validate.exclusiveBetween("0", "5", "5");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 5 is not in the specified exclusive range of 0 to 5", e.getMessage());
        }
    }

    @Test
    public void testExclusiveBetween_withMessage()
    {
        Validate.exclusiveBetween("a", "c", "b", "Error");
        try {
            Validate.exclusiveBetween("0", "5", "6", "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
        try {
            Validate.exclusiveBetween("0", "5", "5", "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testExclusiveBetweenLong()
    {
        Validate.exclusiveBetween(0, 2, 1);
        try {
            Validate.exclusiveBetween(0, 5, 6);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 6 is not in the specified exclusive range of 0 to 5", e.getMessage());
        }
        try {
            Validate.exclusiveBetween(0, 5, 5);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 5 is not in the specified exclusive range of 0 to 5", e.getMessage());
        }
    }

    @Test
    public void testExclusiveBetweenLong_withMessage()
    {
        Validate.exclusiveBetween(0, 2, 1, "Error");
        try {
            Validate.exclusiveBetween(0, 5, 6, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
        try {
            Validate.exclusiveBetween(0, 5, 5, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testExclusiveBetweenDouble()
    {
        Validate.exclusiveBetween(0.1, 2.1, 1.1);
        try {
            Validate.exclusiveBetween(0.1, 5.1, 6.1);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 6.1 is not in the specified exclusive range of 0.1 to 5.1", e.getMessage());
        }
        try {
            Validate.exclusiveBetween(0.1, 5.1, 5.1);
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("The value 5.1 is not in the specified exclusive range of 0.1 to 5.1", e.getMessage());
        }
    }

    @Test
    public void testExclusiveBetweenDouble_withMessage()
    {
        Validate.exclusiveBetween(0.1, 2.1, 1.1, "Error");
        try {
            Validate.exclusiveBetween(0.1, 5.1, 6.1, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
        try {
            Validate.exclusiveBetween(0.1, 5.1, 5.1, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testIsInstanceOf() {
        Validate.isInstanceOf(String.class, "hi");
        Validate.isInstanceOf(Integer.class, 1);
    }

    @Test
    public void testIsInstanceOfExceptionMessage() {
        try {
            Validate.isInstanceOf(List.class, "hi");
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Expected type: java.util.List, actual: java.lang.String", e.getMessage());
        }
    }

    @Test
    public void testIsInstanceOf_withMessage() {
        Validate.isInstanceOf(String.class, "hi", "Error");
        Validate.isInstanceOf(Integer.class, 1, "Error");
        try {
            Validate.isInstanceOf(List.class, "hi", "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

    @Test
    public void testIsInstanceOf_withMessageArgs() {
        Validate.isInstanceOf(String.class, "hi", "Error %s=%s", "Name", "Value");
        Validate.isInstanceOf(Integer.class, 1, "Error %s=%s", "Name", "Value");
        try {
            Validate.isInstanceOf(List.class, "hi", "Error %s=%s", "Name", "Value");
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Error Name=Value", e.getMessage());
        }
        try {
            Validate.isInstanceOf(List.class, "hi", "Error %s=%s", List.class, "Value");
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Error interface java.util.List=Value", e.getMessage());
        }
        try {
            Validate.isInstanceOf(List.class, "hi", "Error %s=%s", List.class, null);
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Error interface java.util.List=null", e.getMessage());
        }
    }

    @Test
    public void testIsAssignable() {
        Validate.isAssignableFrom(CharSequence.class, String.class);
        Validate.isAssignableFrom(AbstractList.class, ArrayList.class);
    }

    @Test
    public void testIsAssignableExceptionMessage() {
        try {
            Validate.isAssignableFrom(List.class, String.class);
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Cannot assign a java.lang.String to a java.util.List", e.getMessage());
        }
    }

    @Test
    public void testIsAssignable_withMessage() {
        Validate.isAssignableFrom(CharSequence.class, String.class, "Error");
        Validate.isAssignableFrom(AbstractList.class, ArrayList.class, "Error");
        try {
            Validate.isAssignableFrom(List.class, String.class, "Error");
            Assert.fail("Expecting IllegalArgumentException");
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("Error", e.getMessage());
        }
    }

}