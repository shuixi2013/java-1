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
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.CharSetUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharSetUtils}.
 */
public class CharSetUtilsTest  {

    //-----------------------------------------------------------------------
    @Test
    public void testConstructor() {
        Assert.assertNotNull(new CharSetUtils());
        final Constructor<?>[] cons = CharSetUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(CharSetUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(CharSetUtils.class.getModifiers()));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testSqueeze_StringString() {
        Assert.assertEquals(null, CharSetUtils.squeeze(null, (String) null));
        Assert.assertEquals(null, CharSetUtils.squeeze(null, ""));
        
        Assert.assertEquals("", CharSetUtils.squeeze("", (String) null));
        Assert.assertEquals("", CharSetUtils.squeeze("", ""));
        Assert.assertEquals("", CharSetUtils.squeeze("", "a-e"));
        
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", (String) null));
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", ""));
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", "a-e"));
        Assert.assertEquals("helo", CharSetUtils.squeeze("hello", "l-p"));
        Assert.assertEquals("heloo", CharSetUtils.squeeze("helloo", "l"));
        Assert.assertEquals("hello", CharSetUtils.squeeze("helloo", "^l"));
    }
    
    @Test
    public void testSqueeze_StringStringarray() {
        Assert.assertEquals(null, CharSetUtils.squeeze(null, (String[]) null));
        Assert.assertEquals(null, CharSetUtils.squeeze(null, new String[0]));
        Assert.assertEquals(null, CharSetUtils.squeeze(null, new String[] {null}));
        Assert.assertEquals(null, CharSetUtils.squeeze(null, new String[] {"el"}));
        
        Assert.assertEquals("", CharSetUtils.squeeze("", (String[]) null));
        Assert.assertEquals("", CharSetUtils.squeeze("", new String[0]));
        Assert.assertEquals("", CharSetUtils.squeeze("", new String[] {null}));
        Assert.assertEquals("", CharSetUtils.squeeze("", new String[] {"a-e"}));
        
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", (String[]) null));
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", new String[0]));
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", new String[] {null}));
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", new String[] {"a-e"}));
        
        Assert.assertEquals("helo", CharSetUtils.squeeze("hello", new String[] { "el" }));
        Assert.assertEquals("hello", CharSetUtils.squeeze("hello", new String[] { "e" }));
        Assert.assertEquals("fofof", CharSetUtils.squeeze("fooffooff", new String[] { "of" }));
        Assert.assertEquals("fof", CharSetUtils.squeeze("fooooff", new String[] { "fo" }));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testContainsAny_StringString() {
        Assert.assertFalse(CharSetUtils.containsAny(null, (String) null));
        Assert.assertFalse(CharSetUtils.containsAny(null, ""));
        
        Assert.assertFalse(CharSetUtils.containsAny("", (String) null));
        Assert.assertFalse(CharSetUtils.containsAny("", ""));
        Assert.assertFalse(CharSetUtils.containsAny("", "a-e"));
        
        Assert.assertFalse(CharSetUtils.containsAny("hello", (String) null));
        Assert.assertFalse(CharSetUtils.containsAny("hello", ""));
        Assert.assertTrue(CharSetUtils.containsAny("hello", "a-e"));
        Assert.assertTrue(CharSetUtils.containsAny("hello", "l-p"));
    }
    
    @Test
    public void testContainsAny_StringStringarray() {
        Assert.assertFalse(CharSetUtils.containsAny(null, (String[]) null));
        Assert.assertFalse(CharSetUtils.containsAny(null, new String[0]));
        Assert.assertFalse(CharSetUtils.containsAny(null, new String[] {null}));
        Assert.assertFalse(CharSetUtils.containsAny(null, new String[] {"a-e"}));
        
        Assert.assertFalse(CharSetUtils.containsAny("", (String[]) null));
        Assert.assertFalse(CharSetUtils.containsAny("", new String[0]));
        Assert.assertFalse(CharSetUtils.containsAny("", new String[] {null}));
        Assert.assertFalse(CharSetUtils.containsAny("", new String[] {"a-e"}));
        
        Assert.assertFalse(CharSetUtils.containsAny("hello", (String[]) null));
        Assert.assertFalse(CharSetUtils.containsAny("hello", new String[0]));
        Assert.assertFalse(CharSetUtils.containsAny("hello", new String[] {null}));
        Assert.assertTrue(CharSetUtils.containsAny("hello", new String[] {"a-e"}));
        
        Assert.assertTrue(CharSetUtils.containsAny("hello", new String[] { "el" }));
        Assert.assertFalse(CharSetUtils.containsAny("hello", new String[] { "x" }));
        Assert.assertTrue(CharSetUtils.containsAny("hello", new String[] { "e-i" }));
        Assert.assertTrue(CharSetUtils.containsAny("hello", new String[] { "a-z" }));
        Assert.assertFalse(CharSetUtils.containsAny("hello", new String[] { "" }));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testCount_StringString() {
        Assert.assertEquals(0, CharSetUtils.count(null, (String) null));
        Assert.assertEquals(0, CharSetUtils.count(null, ""));
        
        Assert.assertEquals(0, CharSetUtils.count("", (String) null));
        Assert.assertEquals(0, CharSetUtils.count("", ""));
        Assert.assertEquals(0, CharSetUtils.count("", "a-e"));
        
        Assert.assertEquals(0, CharSetUtils.count("hello", (String) null));
        Assert.assertEquals(0, CharSetUtils.count("hello", ""));
        Assert.assertEquals(1, CharSetUtils.count("hello", "a-e"));
        Assert.assertEquals(3, CharSetUtils.count("hello", "l-p"));
    }
    
    @Test
    public void testCount_StringStringarray() {
        Assert.assertEquals(0, CharSetUtils.count(null, (String[]) null));
        Assert.assertEquals(0, CharSetUtils.count(null, new String[0]));
        Assert.assertEquals(0, CharSetUtils.count(null, new String[] {null}));
        Assert.assertEquals(0, CharSetUtils.count(null, new String[] {"a-e"}));
        
        Assert.assertEquals(0, CharSetUtils.count("", (String[]) null));
        Assert.assertEquals(0, CharSetUtils.count("", new String[0]));
        Assert.assertEquals(0, CharSetUtils.count("", new String[] {null}));
        Assert.assertEquals(0, CharSetUtils.count("", new String[] {"a-e"}));
        
        Assert.assertEquals(0, CharSetUtils.count("hello", (String[]) null));
        Assert.assertEquals(0, CharSetUtils.count("hello", new String[0]));
        Assert.assertEquals(0, CharSetUtils.count("hello", new String[] {null}));
        Assert.assertEquals(1, CharSetUtils.count("hello", new String[] {"a-e"}));
        
        Assert.assertEquals(3, CharSetUtils.count("hello", new String[] { "el" }));
        Assert.assertEquals(0, CharSetUtils.count("hello", new String[] { "x" }));
        Assert.assertEquals(2, CharSetUtils.count("hello", new String[] { "e-i" }));
        Assert.assertEquals(5, CharSetUtils.count("hello", new String[] { "a-z" }));
        Assert.assertEquals(0, CharSetUtils.count("hello", new String[] { "" }));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testKeep_StringString() {
        Assert.assertEquals(null, CharSetUtils.keep(null, (String) null));
        Assert.assertEquals(null, CharSetUtils.keep(null, ""));
        
        Assert.assertEquals("", CharSetUtils.keep("", (String) null));
        Assert.assertEquals("", CharSetUtils.keep("", ""));
        Assert.assertEquals("", CharSetUtils.keep("", "a-e"));
        
        Assert.assertEquals("", CharSetUtils.keep("hello", (String) null));
        Assert.assertEquals("", CharSetUtils.keep("hello", ""));
        Assert.assertEquals("", CharSetUtils.keep("hello", "xyz"));
        Assert.assertEquals("hello", CharSetUtils.keep("hello", "a-z"));
        Assert.assertEquals("hello", CharSetUtils.keep("hello", "oleh"));
        Assert.assertEquals("ell", CharSetUtils.keep("hello", "el"));
    }
    
    @Test
    public void testKeep_StringStringarray() {
        Assert.assertEquals(null, CharSetUtils.keep(null, (String[]) null));
        Assert.assertEquals(null, CharSetUtils.keep(null, new String[0]));
        Assert.assertEquals(null, CharSetUtils.keep(null, new String[] {null}));
        Assert.assertEquals(null, CharSetUtils.keep(null, new String[] {"a-e"}));
        
        Assert.assertEquals("", CharSetUtils.keep("", (String[]) null));
        Assert.assertEquals("", CharSetUtils.keep("", new String[0]));
        Assert.assertEquals("", CharSetUtils.keep("", new String[] {null}));
        Assert.assertEquals("", CharSetUtils.keep("", new String[] {"a-e"}));
        
        Assert.assertEquals("", CharSetUtils.keep("hello", (String[]) null));
        Assert.assertEquals("", CharSetUtils.keep("hello", new String[0]));
        Assert.assertEquals("", CharSetUtils.keep("hello", new String[] {null}));
        Assert.assertEquals("e", CharSetUtils.keep("hello", new String[] {"a-e"}));
        
        Assert.assertEquals("e", CharSetUtils.keep("hello", new String[] { "a-e" }));
        Assert.assertEquals("ell", CharSetUtils.keep("hello", new String[] { "el" }));
        Assert.assertEquals("hello", CharSetUtils.keep("hello", new String[] { "elho" }));
        Assert.assertEquals("hello", CharSetUtils.keep("hello", new String[] { "a-z" }));
        Assert.assertEquals("----", CharSetUtils.keep("----", new String[] { "-" }));
        Assert.assertEquals("ll", CharSetUtils.keep("hello", new String[] { "l" }));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testDelete_StringString() {
        Assert.assertEquals(null, CharSetUtils.delete(null, (String) null));
        Assert.assertEquals(null, CharSetUtils.delete(null, ""));
        
        Assert.assertEquals("", CharSetUtils.delete("", (String) null));
        Assert.assertEquals("", CharSetUtils.delete("", ""));
        Assert.assertEquals("", CharSetUtils.delete("", "a-e"));
        
        Assert.assertEquals("hello", CharSetUtils.delete("hello", (String) null));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", ""));
        Assert.assertEquals("hllo", CharSetUtils.delete("hello", "a-e"));
        Assert.assertEquals("he", CharSetUtils.delete("hello", "l-p"));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", "z"));
    }
    
    @Test
    public void testDelete_StringStringarray() {
        Assert.assertEquals(null, CharSetUtils.delete(null, (String[]) null));
        Assert.assertEquals(null, CharSetUtils.delete(null, new String[0]));
        Assert.assertEquals(null, CharSetUtils.delete(null, new String[] {null}));
        Assert.assertEquals(null, CharSetUtils.delete(null, new String[] {"el"}));
        
        Assert.assertEquals("", CharSetUtils.delete("", (String[]) null));
        Assert.assertEquals("", CharSetUtils.delete("", new String[0]));
        Assert.assertEquals("", CharSetUtils.delete("", new String[] {null}));
        Assert.assertEquals("", CharSetUtils.delete("", new String[] {"a-e"}));
        
        Assert.assertEquals("hello", CharSetUtils.delete("hello", (String[]) null));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", new String[0]));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", new String[] {null}));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", new String[] {"xyz"}));

        Assert.assertEquals("ho", CharSetUtils.delete("hello", new String[] { "el" }));
        Assert.assertEquals("", CharSetUtils.delete("hello", new String[] { "elho" }));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", new String[] { "" }));
        Assert.assertEquals("hello", CharSetUtils.delete("hello", ""));
        Assert.assertEquals("", CharSetUtils.delete("hello", new String[] { "a-z" }));
        Assert.assertEquals("", CharSetUtils.delete("----", new String[] { "-" }));
        Assert.assertEquals("heo", CharSetUtils.delete("hello", new String[] { "l" }));
    }
    
}
