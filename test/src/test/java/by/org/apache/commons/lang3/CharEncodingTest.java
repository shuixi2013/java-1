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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.CharEncoding;
import org.junit.Test;

/**
 * Tests CharEncoding.
 * 
 * @see CharEncoding
 */
public class CharEncodingTest  {

    private void assertSupportedEncoding(final String name) {
        Assert.assertTrue("Encoding should be supported: " + name, CharEncoding.isSupported(name));
    }

    /**
     * The class can be instantiated.
     */
    @Test
    public void testConstructor() {
        new CharEncoding();
    }

    @Test
    public void testMustBeSupportedJava1_3_1_and_above() {
        this.assertSupportedEncoding(CharEncoding.ISO_8859_1);
        this.assertSupportedEncoding(CharEncoding.US_ASCII);
        this.assertSupportedEncoding(CharEncoding.UTF_16);
        this.assertSupportedEncoding(CharEncoding.UTF_16BE);
        this.assertSupportedEncoding(CharEncoding.UTF_16LE);
        this.assertSupportedEncoding(CharEncoding.UTF_8);
    }

    @Test
    public void testSupported() {
        Assert.assertTrue(CharEncoding.isSupported("UTF8"));
        Assert.assertTrue(CharEncoding.isSupported("UTF-8"));
        Assert.assertTrue(CharEncoding.isSupported("ASCII"));
    }

    @Test
    public void testNotSupported() {
        Assert.assertFalse(CharEncoding.isSupported(null));
        Assert.assertFalse(CharEncoding.isSupported(""));
        Assert.assertFalse(CharEncoding.isSupported(" "));
        Assert.assertFalse(CharEncoding.isSupported("\t\r\n"));
        Assert.assertFalse(CharEncoding.isSupported("DOESNOTEXIST"));
        Assert.assertFalse(CharEncoding.isSupported("this is not a valid encoding name"));
    }
}
