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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.SerializationUtils}.
 */
public class SerializationUtilsTest {

  static final String CLASS_NOT_FOUND_MESSAGE = "ClassNotFoundSerialization.readObject fake exception";
    protected static final String SERIALIZE_IO_EXCEPTION_MESSAGE = "Anonymous OutputStream I/O exception";
  
    private String iString;
    private Integer iInteger;
    private HashMap<Object, Object> iMap;

    @Before
    public void setUp() {
        iString = "foo";
        iInteger = Integer.valueOf(7);
        iMap = new HashMap<Object, Object>();
        iMap.put("FOO", iString);
        iMap.put("BAR", iInteger);
    }

    //-----------------------------------------------------------------------

    @Test
    public void testConstructor() {
        Assert.assertNotNull(new SerializationUtils());
        final Constructor<?>[] cons = SerializationUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(SerializationUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(SerializationUtils.class.getModifiers()));
    }
    
    @Test
    public void testException() {
        SerializationException serEx;
        final Exception ex = new Exception();
        
        serEx = new SerializationException();
        Assert.assertSame(null, serEx.getMessage());
        Assert.assertSame(null, serEx.getCause());
        
        serEx = new SerializationException("Message");
        Assert.assertSame("Message", serEx.getMessage());
        Assert.assertSame(null, serEx.getCause());
        
        serEx = new SerializationException(ex);
        Assert.assertEquals("java.lang.Exception", serEx.getMessage());
        Assert.assertSame(ex, serEx.getCause());
        
        serEx = new SerializationException("Message", ex);
        Assert.assertSame("Message", serEx.getMessage());
        Assert.assertSame(ex, serEx.getCause());
    }
    
    //-----------------------------------------------------------------------

    @Test
    public void testSerializeStream() throws Exception {
        final ByteArrayOutputStream streamTest = new ByteArrayOutputStream();
        SerializationUtils.serialize(iMap, streamTest);

        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(iMap);
        oos.flush();
        oos.close();

        final byte[] testBytes = streamTest.toByteArray();
        final byte[] realBytes = streamReal.toByteArray();
        Assert.assertEquals(testBytes.length, realBytes.length);
        for (int i = 0; i < realBytes.length; i++) {
            Assert.assertEquals(realBytes[i], testBytes[i]);
        }
    }

    @Test
    public void testSerializeStreamUnserializable() throws Exception {
        final ByteArrayOutputStream streamTest = new ByteArrayOutputStream();
        try {
            iMap.put(new Object(), new Object());
            SerializationUtils.serialize(iMap, streamTest);
        } catch (final SerializationException ex) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testSerializeStreamNullObj() throws Exception {
        final ByteArrayOutputStream streamTest = new ByteArrayOutputStream();
        SerializationUtils.serialize(null, streamTest);

        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(null);
        oos.flush();
        oos.close();

        final byte[] testBytes = streamTest.toByteArray();
        final byte[] realBytes = streamReal.toByteArray();
        Assert.assertEquals(testBytes.length, realBytes.length);
        for (int i = 0; i < realBytes.length; i++) {
            Assert.assertEquals(realBytes[i], testBytes[i]);
        }
    }

    @Test
    public void testSerializeStreamObjNull() throws Exception {
        try {
            SerializationUtils.serialize(iMap, null);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testSerializeStreamNullNull() throws Exception {
        try {
            SerializationUtils.serialize(null, null);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        Assert.fail();
    }
    
    @Test
    public void testSerializeIOException() throws Exception {
        // forces an IOException when the ObjectOutputStream is created, to test not closing the stream
        // in the finally block
        final OutputStream streamTest = new OutputStream() {
            @Override
            public void write(final int arg0) throws IOException {
                throw new IOException(SERIALIZE_IO_EXCEPTION_MESSAGE);
            }
        };
        try {
            SerializationUtils.serialize(iMap, streamTest);
        }
        catch(final SerializationException e) {
            Assert.assertEquals("java.io.IOException: " + SERIALIZE_IO_EXCEPTION_MESSAGE, e.getMessage());
        }
    }

    //-----------------------------------------------------------------------

    @Test
    public void testDeserializeStream() throws Exception {
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(iMap);
        oos.flush();
        oos.close();

        final ByteArrayInputStream inTest = new ByteArrayInputStream(streamReal.toByteArray());
        final Object test = SerializationUtils.deserialize(inTest);
        Assert.assertNotNull(test);
        Assert.assertTrue(test instanceof HashMap<?, ?>);
        Assert.assertTrue(test != iMap);
        final HashMap<?, ?> testMap = (HashMap<?, ?>) test;
        Assert.assertEquals(iString, testMap.get("FOO"));
        Assert.assertTrue(iString != testMap.get("FOO"));
        Assert.assertEquals(iInteger, testMap.get("BAR"));
        Assert.assertTrue(iInteger != testMap.get("BAR"));
        Assert.assertEquals(iMap, testMap);
    }

    @Test(expected=ClassCastException.class)
    public void testDeserializeClassCastException() {
        final String value = "Hello";
        final byte[] serialized = SerializationUtils.serialize(value);
        Assert.assertEquals(value, SerializationUtils.deserialize(serialized));
        // Causes ClassCastException in call site, not in SerializationUtils.deserialize
        @SuppressWarnings("unused") // needed to cause Exception
        final Integer i = SerializationUtils.deserialize(serialized);
    }

    @Test
    public void testDeserializeStreamOfNull() throws Exception {
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(null);
        oos.flush();
        oos.close();

        final ByteArrayInputStream inTest = new ByteArrayInputStream(streamReal.toByteArray());
        final Object test = SerializationUtils.deserialize(inTest);
        Assert.assertNull(test);
    }

    @Test
    public void testDeserializeStreamNull() throws Exception {
        try {
            SerializationUtils.deserialize((InputStream) null);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testDeserializeStreamBadStream() throws Exception {
        try {
            SerializationUtils.deserialize(new ByteArrayInputStream(new byte[0]));
        } catch (final SerializationException ex) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testDeserializeStreamClassNotFound() throws Exception {
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(new ClassNotFoundSerialization());
        oos.flush();
        oos.close();

        final ByteArrayInputStream inTest = new ByteArrayInputStream(streamReal.toByteArray());
        try {
            @SuppressWarnings("unused")
            final
            Object test = SerializationUtils.deserialize(inTest);
        } catch(final SerializationException se) {
            Assert.assertEquals("java.lang.ClassNotFoundException: " + CLASS_NOT_FOUND_MESSAGE, se.getMessage());
        }
    }
    
    @Test 
    public void testRoundtrip() {
        final HashMap<Object, Object> newMap = SerializationUtils.roundtrip(iMap);
        Assert.assertEquals(iMap, newMap);
    }
    
    //-----------------------------------------------------------------------

    @Test
    public void testSerializeBytes() throws Exception {
        final byte[] testBytes = SerializationUtils.serialize(iMap);

        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(iMap);
        oos.flush();
        oos.close();

        final byte[] realBytes = streamReal.toByteArray();
        Assert.assertEquals(testBytes.length, realBytes.length);
        for (int i = 0; i < realBytes.length; i++) {
            Assert.assertEquals(realBytes[i], testBytes[i]);
        }
    }

    @Test
    public void testSerializeBytesUnserializable() throws Exception {
        try {
            iMap.put(new Object(), new Object());
            SerializationUtils.serialize(iMap);
        } catch (final SerializationException ex) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testSerializeBytesNull() throws Exception {
        final byte[] testBytes = SerializationUtils.serialize(null);

        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(null);
        oos.flush();
        oos.close();

        final byte[] realBytes = streamReal.toByteArray();
        Assert.assertEquals(testBytes.length, realBytes.length);
        for (int i = 0; i < realBytes.length; i++) {
            Assert.assertEquals(realBytes[i], testBytes[i]);
        }
    }

    //-----------------------------------------------------------------------

    @Test
    public void testDeserializeBytes() throws Exception {
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(iMap);
        oos.flush();
        oos.close();

        final Object test = SerializationUtils.deserialize(streamReal.toByteArray());
        Assert.assertNotNull(test);
        Assert.assertTrue(test instanceof HashMap<?, ?>);
        Assert.assertTrue(test != iMap);
        final HashMap<?, ?> testMap = (HashMap<?, ?>) test;
        Assert.assertEquals(iString, testMap.get("FOO"));
        Assert.assertTrue(iString != testMap.get("FOO"));
        Assert.assertEquals(iInteger, testMap.get("BAR"));
        Assert.assertTrue(iInteger != testMap.get("BAR"));
        Assert.assertEquals(iMap, testMap);
    }

    @Test
    public void testDeserializeBytesOfNull() throws Exception {
        final ByteArrayOutputStream streamReal = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(streamReal);
        oos.writeObject(null);
        oos.flush();
        oos.close();

        final Object test = SerializationUtils.deserialize(streamReal.toByteArray());
        Assert.assertNull(test);
    }

    @Test
    public void testDeserializeBytesNull() throws Exception {
        try {
            SerializationUtils.deserialize((byte[]) null);
        } catch (final IllegalArgumentException ex) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void testDeserializeBytesBadStream() throws Exception {
        try {
            SerializationUtils.deserialize(new byte[0]);
        } catch (final SerializationException ex) {
            return;
        }
        Assert.fail();
    }

    //-----------------------------------------------------------------------

    @Test
    public void testClone() throws Exception {
        final Object test = SerializationUtils.clone(iMap);
        Assert.assertNotNull(test);
        Assert.assertTrue(test instanceof HashMap<?,?>);
        Assert.assertTrue(test != iMap);
        final HashMap<?, ?> testMap = (HashMap<?, ?>) test;
        Assert.assertEquals(iString, testMap.get("FOO"));
        Assert.assertTrue(iString != testMap.get("FOO"));
        Assert.assertEquals(iInteger, testMap.get("BAR"));
        Assert.assertTrue(iInteger != testMap.get("BAR"));
        Assert.assertEquals(iMap, testMap);
    }

    @Test
    public void testCloneNull() throws Exception {
        final Object test = SerializationUtils.clone(null);
        Assert.assertNull(test);
    }

    @Test
    public void testCloneUnserializable() throws Exception {
        try {
            iMap.put(new Object(), new Object());
            SerializationUtils.clone(iMap);
        } catch (final SerializationException ex) {
            return;
        }
        Assert.fail();
    }
    
    @Test
    public void testPrimitiveTypeClassSerialization() {
        final Class<?>[] primitiveTypes = { byte.class, short.class, int.class, long.class, float.class, double.class,
                boolean.class, char.class, void.class };

        for (final Class<?> primitiveType : primitiveTypes) {
            final Class<?> clone = SerializationUtils.clone(primitiveType);
            Assert.assertEquals(primitiveType, clone);
        }
    }

}

class ClassNotFoundSerialization implements Serializable
{

    private static final long serialVersionUID = 1L;

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException    {
        throw new ClassNotFoundException(SerializationUtilsTest.CLASS_NOT_FOUND_MESSAGE);
    }
}
