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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.CloneFailedException;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.text.StrBuilder;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.ObjectUtils}.
 */
@SuppressWarnings("deprecation") // deliberate use of deprecated code
public class ObjectUtilsTest {
    private static final String FOO = "foo";
    private static final String BAR = "bar";

    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new ObjectUtils());
        final Constructor<?>[] cons = ObjectUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(ObjectUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(ObjectUtils.class.getModifiers()));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testIsNull() {
        final Object o = FOO;
        final Object dflt = BAR;
        Assert.assertSame("dflt was not returned when o was null", dflt, ObjectUtils.defaultIfNull(null, dflt));
        Assert.assertSame("dflt was returned when o was not null", o, ObjectUtils.defaultIfNull(o, dflt));
    }

    @Test
    public void testFirstNonNull() {
        Assert.assertEquals("", ObjectUtils.firstNonNull(null, ""));
        final String firstNonNullGenerics = ObjectUtils.firstNonNull(null, null, "123", "456");
        Assert.assertEquals("123", firstNonNullGenerics);
        Assert.assertEquals("123", ObjectUtils.firstNonNull("123", null, "456", null));
        Assert.assertSame(Boolean.TRUE, ObjectUtils.firstNonNull(Boolean.TRUE));
        
        // Explicitly pass in an empty array of Object type to ensure compiler doesn't complain of unchecked generic array creation
        Assert.assertNull(ObjectUtils.firstNonNull(new Object[0]));
        
        // Cast to Object in line below ensures compiler doesn't complain of unchecked generic array creation
        Assert.assertNull(ObjectUtils.firstNonNull((Object) null, (Object) null));
        
//        assertSame("123", ObjectUtils.firstNonNull(null, ObjectUtils.NULL, "123", "456"));
//        assertSame("456", ObjectUtils.firstNonNull(ObjectUtils.NULL, "456", "123", null));
//        assertNull(ObjectUtils.firstNonNull(null, null, ObjectUtils.NULL));
        Assert.assertNull(ObjectUtils.firstNonNull((Object) null));
        Assert.assertNull(ObjectUtils.firstNonNull((Object[]) null));
    }

    /**
     * Tests {@link ObjectUtils#anyNotNull(Object...)}.
     */
    @Test
    public void testAnyNotNull() {
        Assert.assertFalse(ObjectUtils.anyNotNull());
        Assert.assertFalse(ObjectUtils.anyNotNull((Object) null));
        Assert.assertFalse(ObjectUtils.anyNotNull((Object[]) null));
        Assert.assertFalse(ObjectUtils.anyNotNull(null, null, null));

        Assert.assertTrue(ObjectUtils.anyNotNull(FOO));
        Assert.assertTrue(ObjectUtils.anyNotNull(null, FOO, null));
        Assert.assertTrue(ObjectUtils.anyNotNull(null, null, null, null, FOO, BAR));
    }

    /**
     * Tests {@link ObjectUtils#allNotNull(Object...)}.
     */
    @Test
    public void testAllNotNull() {
        Assert.assertFalse(ObjectUtils.allNotNull((Object) null));
        Assert.assertFalse(ObjectUtils.allNotNull((Object[]) null));
        Assert.assertFalse(ObjectUtils.allNotNull(null, null, null));
        Assert.assertFalse(ObjectUtils.allNotNull(null, FOO, BAR));
        Assert.assertFalse(ObjectUtils.allNotNull(FOO, BAR, null));
        Assert.assertFalse(ObjectUtils.allNotNull(FOO, BAR, null, FOO, BAR));

        Assert.assertTrue(ObjectUtils.allNotNull());
        Assert.assertTrue(ObjectUtils.allNotNull(FOO));
        Assert.assertTrue(ObjectUtils.allNotNull(FOO, BAR, 1, Boolean.TRUE, new Object(), new Object[]{}));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testEquals() {
        Assert.assertTrue("ObjectUtils.equals(null, null) returned false", ObjectUtils.equals(null, null));
        Assert.assertTrue("ObjectUtils.equals(\"foo\", null) returned true", !ObjectUtils.equals(FOO, null));
        Assert.assertTrue("ObjectUtils.equals(null, \"bar\") returned true", !ObjectUtils.equals(null, BAR));
        Assert.assertTrue("ObjectUtils.equals(\"foo\", \"bar\") returned true", !ObjectUtils.equals(FOO, BAR));
        Assert.assertTrue("ObjectUtils.equals(\"foo\", \"foo\") returned false", ObjectUtils.equals(FOO, FOO));
    }

    @Test
    public void testNotEqual() {
        Assert.assertFalse("ObjectUtils.notEqual(null, null) returned false", ObjectUtils.notEqual(null, null));
        Assert.assertTrue("ObjectUtils.notEqual(\"foo\", null) returned true", ObjectUtils.notEqual(FOO, null));
        Assert.assertTrue("ObjectUtils.notEqual(null, \"bar\") returned true", ObjectUtils.notEqual(null, BAR));
        Assert.assertTrue("ObjectUtils.notEqual(\"foo\", \"bar\") returned true", ObjectUtils.notEqual(FOO, BAR));
        Assert.assertFalse("ObjectUtils.notEqual(\"foo\", \"foo\") returned false", ObjectUtils.notEqual(FOO, FOO));
    }

    @Test
    public void testHashCode() {
        Assert.assertEquals(0, ObjectUtils.hashCode(null));
        Assert.assertEquals("a".hashCode(), ObjectUtils.hashCode("a"));
    }

    @Test
    public void testHashCodeMulti_multiple_emptyArray() {
        final Object[] array = new Object[0];
        Assert.assertEquals(1, ObjectUtils.hashCodeMulti(array));
    }

    @Test
    public void testHashCodeMulti_multiple_nullArray() {
        final Object[] array = null;
        Assert.assertEquals(1, ObjectUtils.hashCodeMulti(array));
    }

    @Test
    public void testHashCodeMulti_multiple_likeList() {
        final List<Object> list0 = new ArrayList<Object>(Arrays.asList(new Object[0]));
        Assert.assertEquals(list0.hashCode(), ObjectUtils.hashCodeMulti());
        
        final List<Object> list1 = new ArrayList<Object>(Arrays.asList("a"));
        Assert.assertEquals(list1.hashCode(), ObjectUtils.hashCodeMulti("a"));
        
        final List<Object> list2 = new ArrayList<Object>(Arrays.asList("a", "b"));
        Assert.assertEquals(list2.hashCode(), ObjectUtils.hashCodeMulti("a", "b"));
        
        final List<Object> list3 = new ArrayList<Object>(Arrays.asList("a", "b", "c"));
        Assert.assertEquals(list3.hashCode(), ObjectUtils.hashCodeMulti("a", "b", "c"));
    }

//    /**
//     * Show that java.util.Date and java.sql.Timestamp are apples and oranges.
//     * Prompted by an email discussion. 
//     * 
//     * The behavior is different b/w Sun Java 1.3.1_10 and 1.4.2_03.
//     */
//    public void testDateEqualsJava() {
//        long now = 1076957313284L; // Feb 16, 2004 10:49... PST
//        java.util.Date date = new java.util.Date(now);
//        java.sql.Timestamp realTimestamp = new java.sql.Timestamp(now);
//        java.util.Date timestamp = realTimestamp;
//        // sanity check 1:
//        assertEquals(284000000, realTimestamp.getNanos());
//        assertEquals(1076957313284L, date.getTime());
//        //
//        // On Sun 1.3.1_10:
//        //junit.framework.AssertionFailedError: expected:<1076957313284> but was:<1076957313000>
//        //
//        //assertEquals(1076957313284L, timestamp.getTime());
//        //
//        //junit.framework.AssertionFailedError: expected:<1076957313284> but was:<1076957313000>
//        //
//        //assertEquals(1076957313284L, realTimestamp.getTime());
//        // sanity check 2:        
//        assertEquals(date.getDay(), realTimestamp.getDay());
//        assertEquals(date.getHours(), realTimestamp.getHours());
//        assertEquals(date.getMinutes(), realTimestamp.getMinutes());
//        assertEquals(date.getMonth(), realTimestamp.getMonth());
//        assertEquals(date.getSeconds(), realTimestamp.getSeconds());
//        assertEquals(date.getTimezoneOffset(), realTimestamp.getTimezoneOffset());
//        assertEquals(date.getYear(), realTimestamp.getYear());
//        //
//        // Time values are == and equals() on Sun 1.4.2_03 but NOT on Sun 1.3.1_10:
//        //
//        //assertFalse("Sanity check failed: date.getTime() == timestamp.getTime()", date.getTime() == timestamp.getTime());
//        //assertFalse("Sanity check failed: timestamp.equals(date)", timestamp.equals(date));
//        //assertFalse("Sanity check failed: date.equals(timestamp)", date.equals(timestamp));
//        // real test:
//        //assertFalse("java.util.Date and java.sql.Timestamp should be equal", ObjectUtils.equals(date, timestamp));
//    }
    
    @Test
    public void testIdentityToStringStringBuffer() {
        final Integer i = Integer.valueOf(45);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StringBuffer buffer = new StringBuffer();
        ObjectUtils.identityToString(buffer, i);
        Assert.assertEquals(expected, buffer.toString());

        try {
            ObjectUtils.identityToString((StringBuffer)null, "tmp");
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        }
        try {
            ObjectUtils.identityToString(new StringBuffer(), null);
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        }
    }
    
    @Test
    public void testIdentityToStringStringBuilder() {
        Assert.assertEquals(null, ObjectUtils.identityToString(null));
        Assert.assertEquals(
            "java.lang.String@" + Integer.toHexString(System.identityHashCode(FOO)),
            ObjectUtils.identityToString(FOO));
        final Integer i = Integer.valueOf(90);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));
        
        Assert.assertEquals(expected, ObjectUtils.identityToString(i));
        
        final StringBuilder builder = new StringBuilder();
        ObjectUtils.identityToString(builder, i);
        Assert.assertEquals(expected, builder.toString());

        try {
            ObjectUtils.identityToString((StringBuilder)null, "tmp");
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        }
        
        try {
            ObjectUtils.identityToString(new StringBuilder(), null);
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        }
    }
    
    @Test
    public void testIdentityToStringStrBuilder() {
        final Integer i = Integer.valueOf(102);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        final StrBuilder builder = new StrBuilder();
        ObjectUtils.identityToString(builder, i);
        Assert.assertEquals(expected, builder.toString());

        try {
            ObjectUtils.identityToString((StrBuilder)null, "tmp");
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        }
        
        try {
            ObjectUtils.identityToString(new StrBuilder(), null);
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        }
    }
    
    @Test
    public void testIdentityToStringAppendable() {
        final Integer i = Integer.valueOf(121);
        final String expected = "java.lang.Integer@" + Integer.toHexString(System.identityHashCode(i));

        try {
            final Appendable appendable = new StringBuilder();
            ObjectUtils.identityToString(appendable, i);
            Assert.assertEquals(expected, appendable.toString());
        } catch(final IOException ex) {
            Assert.fail("IOException unexpected");
        }
        
        try {
            ObjectUtils.identityToString((Appendable)null, "tmp");
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        } catch (final IOException ex) {
        }
        
        try {
            ObjectUtils.identityToString((Appendable)(new StringBuilder()), null);
            Assert.fail("NullPointerException expected");
        } catch(final NullPointerException npe) {
        } catch (final IOException ex) {
        }
    }

    @Test
    public void testToString_Object() {
        Assert.assertEquals("", ObjectUtils.toString((Object) null) );
        Assert.assertEquals(Boolean.TRUE.toString(), ObjectUtils.toString(Boolean.TRUE) );
    }

    @Test
    public void testToString_ObjectString() {
        Assert.assertEquals(BAR, ObjectUtils.toString((Object) null, BAR) );
        Assert.assertEquals(Boolean.TRUE.toString(), ObjectUtils.toString(Boolean.TRUE, BAR) );
    }

    @SuppressWarnings("cast") // 1 OK, because we are checking for code change
    @Test
    public void testNull() {
        Assert.assertNotNull(ObjectUtils.NULL);
        // 1 Check that NULL really is a Null i.e. the definition has not been changed
        Assert.assertTrue(ObjectUtils.NULL instanceof ObjectUtils.Null);
        Assert.assertSame(ObjectUtils.NULL, SerializationUtils.clone(ObjectUtils.NULL));
    }

    @Test
    public void testMax() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullAray = null;
        
        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();
        
        Assert.assertNotSame( nonNullComparable1, nonNullComparable2 );
        
        Assert.assertNull(ObjectUtils.max( (String) null ) );
        Assert.assertNull(ObjectUtils.max( nullAray ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( null, nonNullComparable1 ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( nonNullComparable1, null ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( null, nonNullComparable1, null ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( nonNullComparable1, nonNullComparable2 ) );
        Assert.assertSame( nonNullComparable2, ObjectUtils.max( nonNullComparable2, nonNullComparable1 ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( nonNullComparable1, minComparable ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( minComparable, nonNullComparable1 ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.max( null, minComparable, null, nonNullComparable1 ) );

        Assert.assertNull( ObjectUtils.max((String)null, (String)null) );
    }

    @Test
    public void testMin() {
        final Calendar calendar = Calendar.getInstance();
        final Date nonNullComparable1 = calendar.getTime();
        final Date nonNullComparable2 = calendar.getTime();
        final String[] nullAray = null;
        
        calendar.set( Calendar.YEAR, calendar.get( Calendar.YEAR ) -1 );
        final Date minComparable = calendar.getTime();
        
        Assert.assertNotSame( nonNullComparable1, nonNullComparable2 );
        
        Assert.assertNull(ObjectUtils.min( (String) null ) );
        Assert.assertNull(ObjectUtils.min( nullAray ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.min( null, nonNullComparable1 ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.min( nonNullComparable1, null ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.min( null, nonNullComparable1, null ) );
        Assert.assertSame( nonNullComparable1, ObjectUtils.min( nonNullComparable1, nonNullComparable2 ) );
        Assert.assertSame( nonNullComparable2, ObjectUtils.min( nonNullComparable2, nonNullComparable1 ) );
        Assert.assertSame( minComparable, ObjectUtils.min( nonNullComparable1, minComparable ) );
        Assert.assertSame( minComparable, ObjectUtils.min( minComparable, nonNullComparable1 ) );
        Assert.assertSame( minComparable, ObjectUtils.min( null, nonNullComparable1, null, minComparable ) );

        Assert.assertNull( ObjectUtils.min((String)null, (String)null) );
    }

    /**
     * Tests {@link ObjectUtils#compare(Comparable, Comparable, boolean)}.
     */
    @Test
    public void testCompare() {
        final Integer one = Integer.valueOf(1);
        final Integer two = Integer.valueOf(2);
        final Integer nullValue = null;

        Assert.assertEquals("Null Null false", 0, ObjectUtils.compare(nullValue, nullValue));
        Assert.assertEquals("Null Null true",  0, ObjectUtils.compare(nullValue, nullValue, true));

        Assert.assertEquals("Null one false", -1, ObjectUtils.compare(nullValue, one));
        Assert.assertEquals("Null one true",   1, ObjectUtils.compare(nullValue, one, true));
        
        Assert.assertEquals("one Null false", 1, ObjectUtils.compare(one, nullValue));
        Assert.assertEquals("one Null true", -1, ObjectUtils.compare(one, nullValue, true));

        Assert.assertEquals("one two false", -1, ObjectUtils.compare(one, two));
        Assert.assertEquals("one two true",  -1, ObjectUtils.compare(one, two, true));
    }

    @Test
    public void testMedian() {
        Assert.assertEquals("foo", ObjectUtils.median("foo"));
        Assert.assertEquals("bar", ObjectUtils.median("foo", "bar"));
        Assert.assertEquals("baz", ObjectUtils.median("foo", "bar", "baz"));
        Assert.assertEquals("baz", ObjectUtils.median("foo", "bar", "baz", "blah"));
        Assert.assertEquals("blah", ObjectUtils.median("foo", "bar", "baz", "blah", "wah"));
        Assert.assertEquals(Integer.valueOf(5),
            ObjectUtils.median(Integer.valueOf(1), Integer.valueOf(5), Integer.valueOf(10)));
        Assert.assertEquals(
            Integer.valueOf(7),
            ObjectUtils.median(Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8),
                Integer.valueOf(9)));
        Assert.assertEquals(Integer.valueOf(6),
            ObjectUtils.median(Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8)));
    }

    @Test(expected = NullPointerException.class)
    public void testMedian_nullItems() {
        ObjectUtils.median((String[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMedian_emptyItems() {
        ObjectUtils.<String> median();
    }

    @Test
    public void testComparatorMedian() {
        final CharSequenceComparator cmp = new CharSequenceComparator();
        final NonComparableCharSequence foo = new NonComparableCharSequence("foo");
        final NonComparableCharSequence bar = new NonComparableCharSequence("bar");
        final NonComparableCharSequence baz = new NonComparableCharSequence("baz");
        final NonComparableCharSequence blah = new NonComparableCharSequence("blah");
        final NonComparableCharSequence wah = new NonComparableCharSequence("wah");
        Assert.assertSame(foo, ObjectUtils.median(cmp, foo));
        Assert.assertSame(bar, ObjectUtils.median(cmp, foo, bar));
        Assert.assertSame(baz, ObjectUtils.median(cmp, foo, bar, baz));
        Assert.assertSame(baz, ObjectUtils.median(cmp, foo, bar, baz, blah));
        Assert.assertSame(blah, ObjectUtils.median(cmp, foo, bar, baz, blah, wah));
    }

    @Test(expected = NullPointerException.class)
    public void testComparatorMedian_nullComparator() {
        ObjectUtils.median((Comparator<CharSequence>) null, new NonComparableCharSequence("foo"));
    }

    @Test(expected = NullPointerException.class)
    public void testComparatorMedian_nullItems() {
        ObjectUtils.median(new CharSequenceComparator(), (CharSequence[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComparatorMedian_emptyItems() {
        ObjectUtils.median(new CharSequenceComparator());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMode() {
        Assert.assertNull(ObjectUtils.mode((Object[]) null));
        Assert.assertNull(ObjectUtils.mode());
        Assert.assertNull(ObjectUtils.mode("foo", "bar", "baz"));
        Assert.assertNull(ObjectUtils.mode("foo", "bar", "baz", "foo", "bar"));
        Assert.assertEquals("foo", ObjectUtils.mode("foo", "bar", "baz", "foo"));
        Assert.assertEquals(Integer.valueOf(9),
            ObjectUtils.mode("foo", "bar", "baz", Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(9)));
    }

    /**
     * Tests {@link ObjectUtils#clone(Object)} with a cloneable object.
     */
    @Test
    public void testCloneOfCloneable() {
        final CloneableString string = new CloneableString("apache");
        final CloneableString stringClone = ObjectUtils.clone(string);
        Assert.assertEquals("apache", stringClone.getValue());
    }

    /**
     * Tests {@link ObjectUtils#clone(Object)} with a not cloneable object.
     */
    @Test
    public void testCloneOfNotCloneable() {
        final String string = new String("apache");
        Assert.assertNull(ObjectUtils.clone(string));
    }

    /**
     * Tests {@link ObjectUtils#clone(Object)} with an uncloneable object.
     *
     * @throws java.lang.Throwable because we expect this to fail
     */
    @Test(expected = NoSuchMethodException.class)
    public void testCloneOfUncloneable() throws Throwable {
        final UncloneableString string = new UncloneableString("apache");
        try {
            ObjectUtils.clone(string);
            Assert.fail("Thrown " + CloneFailedException.class.getName() + " expected");
        } catch (final CloneFailedException e) {
            throw e.getCause();
        }
    }

    /**
     * Tests {@link ObjectUtils#clone(Object)} with an object array.
     */
    @Test
    public void testCloneOfStringArray() {
        Assert.assertTrue(Arrays.deepEquals(
            new String[]{"string"}, ObjectUtils.clone(new String[]{"string"})));
    }

    /**
     * Tests {@link ObjectUtils#clone(Object)} with an array of primitives.
     */
    @Test
    public void testCloneOfPrimitiveArray() {
        Assert.assertTrue(Arrays.equals(new int[]{1}, ObjectUtils.clone(new int[]{1})));
    }

    /**
     * Tests {@link ObjectUtils#cloneIfPossible(Object)} with a cloneable object.
     */
    @Test
    public void testPossibleCloneOfCloneable() {
        final CloneableString string = new CloneableString("apache");
        final CloneableString stringClone = ObjectUtils.cloneIfPossible(string);
        Assert.assertEquals("apache", stringClone.getValue());
    }

    /**
     * Tests {@link ObjectUtils#cloneIfPossible(Object)} with a not cloneable object.
     */
    @Test
    public void testPossibleCloneOfNotCloneable() {
        final String string = new String("apache");
        Assert.assertSame(string, ObjectUtils.cloneIfPossible(string));
    }

    /**
     * Tests {@link ObjectUtils#cloneIfPossible(Object)} with an uncloneable object.
     *
     * @throws java.lang.Throwable because we expect this to fail
     */
    @Test(expected = NoSuchMethodException.class)
    public void testPossibleCloneOfUncloneable() throws Throwable {
        final UncloneableString string = new UncloneableString("apache");
        try {
            ObjectUtils.cloneIfPossible(string);
            Assert.fail("Thrown " + CloneFailedException.class.getName() + " expected");
        } catch (final CloneFailedException e) {
            throw e.getCause();
        }
    }

    @Test
    public void testConstMethods() {

        // To truly test the CONST() method, we'd want to look in the
        // bytecode to see if the literals were folded into the
        // class, or if the bytecode kept the method call.

        Assert.assertTrue("CONST(boolean)", ObjectUtils.CONST(true));
        Assert.assertEquals("CONST(byte)", (byte) 3, ObjectUtils.CONST((byte) 3));
        Assert.assertEquals("CONST(char)", (char) 3, ObjectUtils.CONST((char) 3));
        Assert.assertEquals("CONST(short)", (short) 3, ObjectUtils.CONST((short) 3));
        Assert.assertEquals("CONST(int)", 3, ObjectUtils.CONST(3));
        Assert.assertEquals("CONST(long)", 3l, ObjectUtils.CONST(3l));
        Assert.assertEquals("CONST(float)", 3f, ObjectUtils.CONST(3f), 0);
        Assert.assertEquals("CONST(double)", 3.0, ObjectUtils.CONST(3.0), 0);
        Assert.assertEquals("CONST(Object)", "abc", ObjectUtils.CONST("abc"));

        // Make sure documentation examples from Javadoc all work
        // (this fixed a lot of my bugs when I these!)
        //
        // My bugs should be in a software engineering textbook
        // for "Can you screw this up?"  The answer is, yes,
        // you can even screw this up.  (When you == Julius)
        // .
        final boolean MAGIC_FLAG = ObjectUtils.CONST(true);
        final byte MAGIC_BYTE1 = ObjectUtils.CONST((byte) 127);
        final byte MAGIC_BYTE2 = ObjectUtils.CONST_BYTE(127);
        final char MAGIC_CHAR = ObjectUtils.CONST('a');
        final short MAGIC_SHORT1 = ObjectUtils.CONST((short) 123);
        final short MAGIC_SHORT2 = ObjectUtils.CONST_SHORT(127);
        final int MAGIC_INT = ObjectUtils.CONST(123);
        final long MAGIC_LONG1 = ObjectUtils.CONST(123L);
        final long MAGIC_LONG2 = ObjectUtils.CONST(3);
        final float MAGIC_FLOAT = ObjectUtils.CONST(1.0f);
        final double MAGIC_DOUBLE = ObjectUtils.CONST(1.0);
        final String MAGIC_STRING = ObjectUtils.CONST("abc");

        Assert.assertTrue(MAGIC_FLAG);
        Assert.assertEquals(127, MAGIC_BYTE1);
        Assert.assertEquals(127, MAGIC_BYTE2);
        Assert.assertEquals('a', MAGIC_CHAR);
        Assert.assertEquals(123, MAGIC_SHORT1);
        Assert.assertEquals(127, MAGIC_SHORT2);
        Assert.assertEquals(123, MAGIC_INT);
        Assert.assertEquals(123, MAGIC_LONG1);
        Assert.assertEquals(3, MAGIC_LONG2);
        Assert.assertEquals(1.0f, MAGIC_FLOAT, 0.0f);
        Assert.assertEquals(1.0, MAGIC_DOUBLE, 0.0);
        Assert.assertEquals("abc", MAGIC_STRING);

        try {
            ObjectUtils.CONST_BYTE(-129);
            Assert.fail("CONST_BYTE(-129): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }
        try {
            ObjectUtils.CONST_BYTE(128);
            Assert.fail("CONST_BYTE(128): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }
        try {
            ObjectUtils.CONST_SHORT(-32769);
            Assert.fail("CONST_SHORT(-32769): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }
        try {
            ObjectUtils.CONST_BYTE(32768);
            Assert.fail("CONST_SHORT(32768): IllegalArgumentException should have been thrown.");
        } catch (final IllegalArgumentException iae) {

        }

    }

    /**
     * String that is cloneable.
     */
    static final class CloneableString extends MutableObject<String> implements Cloneable {
        private static final long serialVersionUID = 1L;
        CloneableString(final String s) {
            super(s);
        }

        @Override
        public CloneableString clone() throws CloneNotSupportedException {
            return (CloneableString)super.clone();
        }
    }

    /**
     * String that is not cloneable.
     */
    static final class UncloneableString extends MutableObject<String> implements Cloneable {
        private static final long serialVersionUID = 1L;
        UncloneableString(final String s) {
            super(s);
        }
    }

    static final class NonComparableCharSequence implements CharSequence {
        final String value;

        /**
         * Create a new NonComparableCharSequence instance.
         *
         * @param value
         */
        public NonComparableCharSequence(final String value) {
            super();
            Validate.notNull(value);
            this.value = value;
        }

        @Override
        public char charAt(final int arg0) {
            return value.charAt(arg0);
        }

        @Override
        public int length() {
            return value.length();
        }

        @Override
        public CharSequence subSequence(final int arg0, final int arg1) {
            return value.subSequence(arg0, arg1);
        }

        @Override
        public String toString() {
            return value;
        }
    }

    static final class CharSequenceComparator implements Comparator<CharSequence> {

        @Override
        public int compare(final CharSequence o1, final CharSequence o2) {
            return o1.toString().compareTo(o2.toString());
        }

    }
}
