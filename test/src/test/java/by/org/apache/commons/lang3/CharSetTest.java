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
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharRange;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharSet}.
 */
public class CharSetTest  {

    //-----------------------------------------------------------------------
    @Test
    public void testClass() {
        Assert.assertTrue(Modifier.isPublic(CharSet.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(CharSet.class.getModifiers()));
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testGetInstance() {
        Assert.assertSame(CharSet.EMPTY, CharSet.getInstance( (String) null));
        Assert.assertSame(CharSet.EMPTY, CharSet.getInstance(""));
        Assert.assertSame(CharSet.ASCII_ALPHA, CharSet.getInstance("a-zA-Z"));
        Assert.assertSame(CharSet.ASCII_ALPHA, CharSet.getInstance("A-Za-z"));
        Assert.assertSame(CharSet.ASCII_ALPHA_LOWER, CharSet.getInstance("a-z"));
        Assert.assertSame(CharSet.ASCII_ALPHA_UPPER, CharSet.getInstance("A-Z"));
        Assert.assertSame(CharSet.ASCII_NUMERIC, CharSet.getInstance("0-9"));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testGetInstance_Stringarray() {
        Assert.assertEquals(null, CharSet.getInstance((String[]) null));
        Assert.assertEquals("[]", CharSet.getInstance(new String[0]).toString());
        Assert.assertEquals("[]", CharSet.getInstance(new String[] {null}).toString());
        Assert.assertEquals("[a-e]", CharSet.getInstance(new String[] {"a-e"}).toString());
    }
    
    //-----------------------------------------------------------------------
    @Test
    public void testConstructor_String_simple() {
        CharSet set;
        CharRange[] array;
        
        set = CharSet.getInstance((String) null);
        array = set.getCharRanges();
        Assert.assertEquals("[]", set.toString());
        Assert.assertEquals(0, array.length);
        
        set = CharSet.getInstance("");
        array = set.getCharRanges();
        Assert.assertEquals("[]", set.toString());
        Assert.assertEquals(0, array.length);
        
        set = CharSet.getInstance("a");
        array = set.getCharRanges();
        Assert.assertEquals("[a]", set.toString());
        Assert.assertEquals(1, array.length);
        Assert.assertEquals("a", array[0].toString());
        
        set = CharSet.getInstance("^a");
        array = set.getCharRanges();
        Assert.assertEquals("[^a]", set.toString());
        Assert.assertEquals(1, array.length);
        Assert.assertEquals("^a", array[0].toString());
        
        set = CharSet.getInstance("a-e");
        array = set.getCharRanges();
        Assert.assertEquals("[a-e]", set.toString());
        Assert.assertEquals(1, array.length);
        Assert.assertEquals("a-e", array[0].toString());
        
        set = CharSet.getInstance("^a-e");
        array = set.getCharRanges();
        Assert.assertEquals("[^a-e]", set.toString());
        Assert.assertEquals(1, array.length);
        Assert.assertEquals("^a-e", array[0].toString());
    }
    
    @Test
    public void testConstructor_String_combo() {
        CharSet set;
        CharRange[] array;
        
        set = CharSet.getInstance("abc");
        array = set.getCharRanges();
        Assert.assertEquals(3, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
        
        set = CharSet.getInstance("a-ce-f");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', 'c')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
        
        set = CharSet.getInstance("ae-f");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
        
        set = CharSet.getInstance("e-fa");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
        
        set = CharSet.getInstance("ae-fm-pz");
        array = set.getCharRanges();
        Assert.assertEquals(4, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('e', 'f')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('m', 'p')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('z')));
    }
    
    @Test
    public void testConstructor_String_comboNegated() {
        CharSet set;
        CharRange[] array;
        
        set = CharSet.getInstance("^abc");
        array = set.getCharRanges();
        Assert.assertEquals(3, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
        
        set = CharSet.getInstance("b^ac");
        array = set.getCharRanges();
        Assert.assertEquals(3, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
        
        set = CharSet.getInstance("db^ac");
        array = set.getCharRanges();
        Assert.assertEquals(4, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('d')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('c')));
        
        set = CharSet.getInstance("^b^a");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('b')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('a')));
        
        set = CharSet.getInstance("b^a-c^z");
        array = set.getCharRanges();
        Assert.assertEquals(3, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('a', 'c')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('z')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('b')));
    }

    @Test
    public void testConstructor_String_oddDash() {
        CharSet set;
        CharRange[] array;
        
        set = CharSet.getInstance("-");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
        
        set = CharSet.getInstance("--");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
        
        set = CharSet.getInstance("---");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
        
        set = CharSet.getInstance("----");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
        
        set = CharSet.getInstance("-a");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
        
        set = CharSet.getInstance("a-");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-')));
        
        set = CharSet.getInstance("a--");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', '-')));
        
        set = CharSet.getInstance("--a");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('-', 'a')));
    }
    
    @Test
    public void testConstructor_String_oddNegate() {
        CharSet set;
        CharRange[] array;
        set = CharSet.getInstance("^");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('^'))); // "^"
        
        set = CharSet.getInstance("^^");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('^'))); // "^^"
        
        set = CharSet.getInstance("^^^");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('^'))); // "^^"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('^'))); // "^"
        
        set = CharSet.getInstance("^^^^");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('^'))); // "^^" x2
        
        set = CharSet.getInstance("a^");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('a'))); // "a"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('^'))); // "^"
        
        set = CharSet.getInstance("^a-");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('a'))); // "^a"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-'))); // "-"
        
        set = CharSet.getInstance("^^-c");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('^', 'c'))); // "^^-c"
        
        set = CharSet.getInstance("^c-^");
        array = set.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('c', '^'))); // "^c-^"
        
        set = CharSet.getInstance("^c-^d");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('c', '^'))); // "^c-^"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('d'))); // "d"
        
        set = CharSet.getInstance("^^-");
        array = set.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNot('^'))); // "^^"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('-'))); // "-"
    }
    
    @Test
    public void testConstructor_String_oddCombinations() {
        CharSet set;
        CharRange[] array = null;
        
        set = CharSet.getInstance("a-^c");
        array = set.getCharRanges();
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', '^'))); // "a-^"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('c'))); // "c"
        Assert.assertFalse(set.contains('b'));
        Assert.assertTrue(set.contains('^'));
        Assert.assertTrue(set.contains('_')); // between ^ and a
        Assert.assertTrue(set.contains('c'));
        
        set = CharSet.getInstance("^a-^c");
        array = set.getCharRanges();
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('a', '^'))); // "^a-^"
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.is('c'))); // "c"
        Assert.assertTrue(set.contains('b'));
        Assert.assertFalse(set.contains('^'));
        Assert.assertFalse(set.contains('_')); // between ^ and a
        
        set = CharSet.getInstance("a- ^-- "); //contains everything
        array = set.getCharRanges();
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', ' '))); // "a- "
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isNotIn('-', ' '))); // "^-- "
        Assert.assertTrue(set.contains('#'));
        Assert.assertTrue(set.contains('^'));
        Assert.assertTrue(set.contains('a'));
        Assert.assertTrue(set.contains('*'));
        Assert.assertTrue(set.contains('A'));
        
        set = CharSet.getInstance("^-b");
        array = set.getCharRanges();
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('^','b'))); // "^-b"
        Assert.assertTrue(set.contains('b'));
        Assert.assertTrue(set.contains('_')); // between ^ and a
        Assert.assertFalse(set.contains('A'));
        Assert.assertTrue(set.contains('^'));
        
        set = CharSet.getInstance("b-^");
        array = set.getCharRanges();
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('^','b'))); // "b-^"
        Assert.assertTrue(set.contains('b'));
        Assert.assertTrue(set.contains('^'));
        Assert.assertTrue(set.contains('a')); // between ^ and b
        Assert.assertFalse(set.contains('c'));
    }
        
    //-----------------------------------------------------------------------    
    @Test
    public void testEquals_Object() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");
        
        Assert.assertFalse(abc.equals(null));
        
        Assert.assertTrue(abc.equals(abc));
        Assert.assertTrue(abc.equals(abc2));
        Assert.assertFalse(abc.equals(atoc));
        Assert.assertFalse(abc.equals(notatoc));
        
        Assert.assertFalse(atoc.equals(abc));
        Assert.assertTrue(atoc.equals(atoc));
        Assert.assertTrue(atoc.equals(atoc2));
        Assert.assertFalse(atoc.equals(notatoc));
        
        Assert.assertFalse(notatoc.equals(abc));
        Assert.assertFalse(notatoc.equals(atoc));
        Assert.assertTrue(notatoc.equals(notatoc));
        Assert.assertTrue(notatoc.equals(notatoc2));
    }
            
    @Test
    public void testHashCode() {
        final CharSet abc = CharSet.getInstance("abc");
        final CharSet abc2 = CharSet.getInstance("abc");
        final CharSet atoc = CharSet.getInstance("a-c");
        final CharSet atoc2 = CharSet.getInstance("a-c");
        final CharSet notatoc = CharSet.getInstance("^a-c");
        final CharSet notatoc2 = CharSet.getInstance("^a-c");
        
        Assert.assertEquals(abc.hashCode(), abc.hashCode());
        Assert.assertEquals(abc.hashCode(), abc2.hashCode());
        Assert.assertEquals(atoc.hashCode(), atoc.hashCode());
        Assert.assertEquals(atoc.hashCode(), atoc2.hashCode());
        Assert.assertEquals(notatoc.hashCode(), notatoc.hashCode());
        Assert.assertEquals(notatoc.hashCode(), notatoc2.hashCode());
    }
    
    //-----------------------------------------------------------------------    
    @Test
    public void testContains_Char() {
        final CharSet btod = CharSet.getInstance("b-d");
        final CharSet dtob = CharSet.getInstance("d-b");
        final CharSet bcd = CharSet.getInstance("bcd");
        final CharSet bd = CharSet.getInstance("bd");
        final CharSet notbtod = CharSet.getInstance("^b-d");
        
        Assert.assertFalse(btod.contains('a'));
        Assert.assertTrue(btod.contains('b'));
        Assert.assertTrue(btod.contains('c'));
        Assert.assertTrue(btod.contains('d'));
        Assert.assertFalse(btod.contains('e'));
        
        Assert.assertFalse(bcd.contains('a'));
        Assert.assertTrue(bcd.contains('b'));
        Assert.assertTrue(bcd.contains('c'));
        Assert.assertTrue(bcd.contains('d'));
        Assert.assertFalse(bcd.contains('e'));
        
        Assert.assertFalse(bd.contains('a'));
        Assert.assertTrue(bd.contains('b'));
        Assert.assertFalse(bd.contains('c'));
        Assert.assertTrue(bd.contains('d'));
        Assert.assertFalse(bd.contains('e'));
        
        Assert.assertTrue(notbtod.contains('a'));
        Assert.assertFalse(notbtod.contains('b'));
        Assert.assertFalse(notbtod.contains('c'));
        Assert.assertFalse(notbtod.contains('d'));
        Assert.assertTrue(notbtod.contains('e'));
        
        Assert.assertFalse(dtob.contains('a'));
        Assert.assertTrue(dtob.contains('b'));
        Assert.assertTrue(dtob.contains('c'));
        Assert.assertTrue(dtob.contains('d'));
        Assert.assertFalse(dtob.contains('e'));
      
        final CharRange[] array = dtob.getCharRanges();
        Assert.assertEquals("[b-d]", dtob.toString());
        Assert.assertEquals(1, array.length);
    }
    
    //-----------------------------------------------------------------------    
    @Test
    public void testSerialization() {
        CharSet set = CharSet.getInstance("a");
        Assert.assertEquals(set, SerializationUtils.clone(set));
        set = CharSet.getInstance("a-e");
        Assert.assertEquals(set, SerializationUtils.clone(set));
        set = CharSet.getInstance("be-f^a-z");
        Assert.assertEquals(set, SerializationUtils.clone(set));
    }
    
    //-----------------------------------------------------------------------    
    @Test
    public void testStatics() {
        CharRange[] array;
        
        array = CharSet.EMPTY.getCharRanges();
        Assert.assertEquals(0, array.length);
        
        array = CharSet.ASCII_ALPHA.getCharRanges();
        Assert.assertEquals(2, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', 'z')));
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('A', 'Z')));
        
        array = CharSet.ASCII_ALPHA_LOWER.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('a', 'z')));
        
        array = CharSet.ASCII_ALPHA_UPPER.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('A', 'Z')));
        
        array = CharSet.ASCII_NUMERIC.getCharRanges();
        Assert.assertEquals(1, array.length);
        Assert.assertTrue(ArrayUtils.contains(array, CharRange.isIn('0', '9')));
    }
    
    @Test
    public void testJavadocExamples() throws Exception {
        Assert.assertFalse(CharSet.getInstance("^a-c").contains('a'));
        Assert.assertTrue(CharSet.getInstance("^a-c").contains('d'));
        Assert.assertTrue(CharSet.getInstance("^^a-c").contains('a'));
        Assert.assertFalse(CharSet.getInstance("^^a-c").contains('^'));
        Assert.assertTrue(CharSet.getInstance("^a-cd-f").contains('d'));
        Assert.assertTrue(CharSet.getInstance("a-c^").contains('^'));
        Assert.assertTrue(CharSet.getInstance("^", "a-c").contains('^'));
    }
}
