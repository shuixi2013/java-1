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
package com.by.org.apache.commons.lang3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.CharRange;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.CharRange}.
 */
public class CharRangeTest  {

    //-----------------------------------------------------------------------
    @Test
    public void testClass() {
        // class changed to non-public in 3.0
        Assert.assertFalse(Modifier.isPublic(CharRange.class.getModifiers()));
        Assert.assertTrue(Modifier.isFinal(CharRange.class.getModifiers()));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testConstructorAccessors_is() {
        final CharRange rangea = CharRange.is('a');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('a', rangea.getEnd());
        Assert.assertFalse(rangea.isNegated());
        Assert.assertEquals("a", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isNot() {
        final CharRange rangea = CharRange.isNot('a');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('a', rangea.getEnd());
        Assert.assertTrue(rangea.isNegated());
        Assert.assertEquals("^a", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isIn_Same() {
        final CharRange rangea = CharRange.isIn('a', 'a');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('a', rangea.getEnd());
        Assert.assertFalse(rangea.isNegated());
        Assert.assertEquals("a", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isIn_Normal() {
        final CharRange rangea = CharRange.isIn('a', 'e');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('e', rangea.getEnd());
        Assert.assertFalse(rangea.isNegated());
        Assert.assertEquals("a-e", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isIn_Reversed() {
        final CharRange rangea = CharRange.isIn('e', 'a');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('e', rangea.getEnd());
        Assert.assertFalse(rangea.isNegated());
        Assert.assertEquals("a-e", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isNotIn_Same() {
        final CharRange rangea = CharRange.isNotIn('a', 'a');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('a', rangea.getEnd());
        Assert.assertTrue(rangea.isNegated());
        Assert.assertEquals("^a", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isNotIn_Normal() {
        final CharRange rangea = CharRange.isNotIn('a', 'e');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('e', rangea.getEnd());
        Assert.assertTrue(rangea.isNegated());
        Assert.assertEquals("^a-e", rangea.toString());
    }

    @Test
    public void testConstructorAccessors_isNotIn_Reversed() {
        final CharRange rangea = CharRange.isNotIn('e', 'a');
        Assert.assertEquals('a', rangea.getStart());
        Assert.assertEquals('e', rangea.getEnd());
        Assert.assertTrue(rangea.isNegated());
        Assert.assertEquals("^a-e", rangea.toString());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testEquals_Object() {
        final CharRange rangea = CharRange.is('a');
        final CharRange rangeae = CharRange.isIn('a', 'e');
        final CharRange rangenotbf = CharRange.isIn('b', 'f');

        Assert.assertFalse(rangea.equals(null));

        Assert.assertTrue(rangea.equals(rangea));
        Assert.assertTrue(rangea.equals(CharRange.is('a')));
        Assert.assertTrue(rangeae.equals(rangeae));
        Assert.assertTrue(rangeae.equals(CharRange.isIn('a', 'e')));
        Assert.assertTrue(rangenotbf.equals(rangenotbf));
        Assert.assertTrue(rangenotbf.equals(CharRange.isIn('b', 'f')));

        Assert.assertFalse(rangea.equals(rangeae));
        Assert.assertFalse(rangea.equals(rangenotbf));
        Assert.assertFalse(rangeae.equals(rangea));
        Assert.assertFalse(rangeae.equals(rangenotbf));
        Assert.assertFalse(rangenotbf.equals(rangea));
        Assert.assertFalse(rangenotbf.equals(rangeae));
    }

    @Test
    public void testHashCode() {
        final CharRange rangea = CharRange.is('a');
        final CharRange rangeae = CharRange.isIn('a', 'e');
        final CharRange rangenotbf = CharRange.isIn('b', 'f');

        Assert.assertTrue(rangea.hashCode() == rangea.hashCode());
        Assert.assertTrue(rangea.hashCode() == CharRange.is('a').hashCode());
        Assert.assertTrue(rangeae.hashCode() == rangeae.hashCode());
        Assert.assertTrue(rangeae.hashCode() == CharRange.isIn('a', 'e').hashCode());
        Assert.assertTrue(rangenotbf.hashCode() == rangenotbf.hashCode());
        Assert.assertTrue(rangenotbf.hashCode() == CharRange.isIn('b', 'f').hashCode());

        Assert.assertFalse(rangea.hashCode() == rangeae.hashCode());
        Assert.assertFalse(rangea.hashCode() == rangenotbf.hashCode());
        Assert.assertFalse(rangeae.hashCode() == rangea.hashCode());
        Assert.assertFalse(rangeae.hashCode() == rangenotbf.hashCode());
        Assert.assertFalse(rangenotbf.hashCode() == rangea.hashCode());
        Assert.assertFalse(rangenotbf.hashCode() == rangeae.hashCode());
    }

    //-----------------------------------------------------------------------
    @Test
    public void testContains_Char() {
        CharRange range = CharRange.is('c');
        Assert.assertFalse(range.contains('b'));
        Assert.assertTrue(range.contains('c'));
        Assert.assertFalse(range.contains('d'));
        Assert.assertFalse(range.contains('e'));

        range = CharRange.isIn('c', 'd');
        Assert.assertFalse(range.contains('b'));
        Assert.assertTrue(range.contains('c'));
        Assert.assertTrue(range.contains('d'));
        Assert.assertFalse(range.contains('e'));

        range = CharRange.isIn('d', 'c');
        Assert.assertFalse(range.contains('b'));
        Assert.assertTrue(range.contains('c'));
        Assert.assertTrue(range.contains('d'));
        Assert.assertFalse(range.contains('e'));

        range = CharRange.isNotIn('c', 'd');
        Assert.assertTrue(range.contains('b'));
        Assert.assertFalse(range.contains('c'));
        Assert.assertFalse(range.contains('d'));
        Assert.assertTrue(range.contains('e'));
        Assert.assertTrue(range.contains((char) 0));
        Assert.assertTrue(range.contains(Character.MAX_VALUE));
    }

    //-----------------------------------------------------------------------
    @Test
    public void testContains_Charrange() {
        final CharRange a = CharRange.is('a');
        final CharRange b = CharRange.is('b');
        final CharRange c = CharRange.is('c');
        final CharRange c2 = CharRange.is('c');
        final CharRange d = CharRange.is('d');
        final CharRange e = CharRange.is('e');
        final CharRange cd = CharRange.isIn('c', 'd');
        final CharRange bd = CharRange.isIn('b', 'd');
        final CharRange bc = CharRange.isIn('b', 'c');
        final CharRange ab = CharRange.isIn('a', 'b');
        final CharRange de = CharRange.isIn('d', 'e');
        final CharRange ef = CharRange.isIn('e', 'f');
        final CharRange ae = CharRange.isIn('a', 'e');

        // normal/normal
        Assert.assertFalse(c.contains(b));
        Assert.assertTrue(c.contains(c));
        Assert.assertTrue(c.contains(c2));
        Assert.assertFalse(c.contains(d));

        Assert.assertFalse(c.contains(cd));
        Assert.assertFalse(c.contains(bd));
        Assert.assertFalse(c.contains(bc));
        Assert.assertFalse(c.contains(ab));
        Assert.assertFalse(c.contains(de));

        Assert.assertTrue(cd.contains(c));
        Assert.assertTrue(bd.contains(c));
        Assert.assertTrue(bc.contains(c));
        Assert.assertFalse(ab.contains(c));
        Assert.assertFalse(de.contains(c));

        Assert.assertTrue(ae.contains(b));
        Assert.assertTrue(ae.contains(ab));
        Assert.assertTrue(ae.contains(bc));
        Assert.assertTrue(ae.contains(cd));
        Assert.assertTrue(ae.contains(de));

        final CharRange notb = CharRange.isNot('b');
        final CharRange notc = CharRange.isNot('c');
        final CharRange notd = CharRange.isNot('d');
        final CharRange notab = CharRange.isNotIn('a', 'b');
        final CharRange notbc = CharRange.isNotIn('b', 'c');
        final CharRange notbd = CharRange.isNotIn('b', 'd');
        final CharRange notcd = CharRange.isNotIn('c', 'd');
        final CharRange notde = CharRange.isNotIn('d', 'e');
        final CharRange notae = CharRange.isNotIn('a', 'e');
        final CharRange all = CharRange.isIn((char) 0, Character.MAX_VALUE);
        final CharRange allbutfirst = CharRange.isIn((char) 1, Character.MAX_VALUE);

        // normal/negated
        Assert.assertFalse(c.contains(notc));
        Assert.assertFalse(c.contains(notbd));
        Assert.assertTrue(all.contains(notc));
        Assert.assertTrue(all.contains(notbd));
        Assert.assertFalse(allbutfirst.contains(notc));
        Assert.assertFalse(allbutfirst.contains(notbd));

        // negated/normal
        Assert.assertTrue(notc.contains(a));
        Assert.assertTrue(notc.contains(b));
        Assert.assertFalse(notc.contains(c));
        Assert.assertTrue(notc.contains(d));
        Assert.assertTrue(notc.contains(e));

        Assert.assertTrue(notc.contains(ab));
        Assert.assertFalse(notc.contains(bc));
        Assert.assertFalse(notc.contains(bd));
        Assert.assertFalse(notc.contains(cd));
        Assert.assertTrue(notc.contains(de));
        Assert.assertFalse(notc.contains(ae));
        Assert.assertFalse(notc.contains(all));
        Assert.assertFalse(notc.contains(allbutfirst));

        Assert.assertTrue(notbd.contains(a));
        Assert.assertFalse(notbd.contains(b));
        Assert.assertFalse(notbd.contains(c));
        Assert.assertFalse(notbd.contains(d));
        Assert.assertTrue(notbd.contains(e));

        Assert.assertTrue(notcd.contains(ab));
        Assert.assertFalse(notcd.contains(bc));
        Assert.assertFalse(notcd.contains(bd));
        Assert.assertFalse(notcd.contains(cd));
        Assert.assertFalse(notcd.contains(de));
        Assert.assertFalse(notcd.contains(ae));
        Assert.assertTrue(notcd.contains(ef));
        Assert.assertFalse(notcd.contains(all));
        Assert.assertFalse(notcd.contains(allbutfirst));

        // negated/negated
        Assert.assertFalse(notc.contains(notb));
        Assert.assertTrue(notc.contains(notc));
        Assert.assertFalse(notc.contains(notd));

        Assert.assertFalse(notc.contains(notab));
        Assert.assertTrue(notc.contains(notbc));
        Assert.assertTrue(notc.contains(notbd));
        Assert.assertTrue(notc.contains(notcd));
        Assert.assertFalse(notc.contains(notde));

        Assert.assertFalse(notbd.contains(notb));
        Assert.assertFalse(notbd.contains(notc));
        Assert.assertFalse(notbd.contains(notd));

        Assert.assertFalse(notbd.contains(notab));
        Assert.assertFalse(notbd.contains(notbc));
        Assert.assertTrue(notbd.contains(notbd));
        Assert.assertFalse(notbd.contains(notcd));
        Assert.assertFalse(notbd.contains(notde));
        Assert.assertTrue(notbd.contains(notae));
    }

    @Test
    public void testContainsNullArg() {
        final CharRange range = CharRange.is('a');
        try {
            @SuppressWarnings("unused")
            final
            boolean contains = range.contains(null);
        } catch(final IllegalArgumentException e) {
            Assert.assertEquals("The Range must not be null", e.getMessage());
        }
    }

    @Test
    public void testIterator() {
        final CharRange a = CharRange.is('a');
        final CharRange ad = CharRange.isIn('a', 'd');
        final CharRange nota = CharRange.isNot('a');
        final CharRange emptySet = CharRange.isNotIn((char) 0, Character.MAX_VALUE);
        final CharRange notFirst = CharRange.isNotIn((char) 1, Character.MAX_VALUE);
        final CharRange notLast = CharRange.isNotIn((char) 0, (char) (Character.MAX_VALUE - 1));

        final Iterator<Character> aIt = a.iterator();
        Assert.assertNotNull(aIt);
        Assert.assertTrue(aIt.hasNext());
        Assert.assertEquals(Character.valueOf('a'), aIt.next());
        Assert.assertFalse(aIt.hasNext());

        final Iterator<Character> adIt = ad.iterator();
        Assert.assertNotNull(adIt);
        Assert.assertTrue(adIt.hasNext());
        Assert.assertEquals(Character.valueOf('a'), adIt.next());
        Assert.assertEquals(Character.valueOf('b'), adIt.next());
        Assert.assertEquals(Character.valueOf('c'), adIt.next());
        Assert.assertEquals(Character.valueOf('d'), adIt.next());
        Assert.assertFalse(adIt.hasNext());

        final Iterator<Character> notaIt = nota.iterator();
        Assert.assertNotNull(notaIt);
        Assert.assertTrue(notaIt.hasNext());
        while (notaIt.hasNext()) {
            final Character c = notaIt.next();
            Assert.assertFalse('a' == c.charValue());
        }

        final Iterator<Character> emptySetIt = emptySet.iterator();
        Assert.assertNotNull(emptySetIt);
        Assert.assertFalse(emptySetIt.hasNext());
        try {
            emptySetIt.next();
            Assert.fail("Should throw NoSuchElementException");
        } catch (final NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        final Iterator<Character> notFirstIt = notFirst.iterator();
        Assert.assertNotNull(notFirstIt);
        Assert.assertTrue(notFirstIt.hasNext());
        Assert.assertEquals(Character.valueOf((char) 0), notFirstIt.next());
        Assert.assertFalse(notFirstIt.hasNext());
        try {
            notFirstIt.next();
            Assert.fail("Should throw NoSuchElementException");
        } catch (final NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        final Iterator<Character> notLastIt = notLast.iterator();
        Assert.assertNotNull(notLastIt);
        Assert.assertTrue(notLastIt.hasNext());
        Assert.assertEquals(Character.valueOf(Character.MAX_VALUE), notLastIt.next());
        Assert.assertFalse(notLastIt.hasNext());
        try {
            notLastIt.next();
            Assert.fail("Should throw NoSuchElementException");
        } catch (final NoSuchElementException e) {
            Assert.assertTrue(true);
        }
    }

    //-----------------------------------------------------------------------
    @Test
    public void testSerialization() {
        CharRange range = CharRange.is('a');
        Assert.assertEquals(range, SerializationUtils.clone(range));
        range = CharRange.isIn('a', 'e');
        Assert.assertEquals(range, SerializationUtils.clone(range));
        range = CharRange.isNotIn('a', 'e');
        Assert.assertEquals(range, SerializationUtils.clone(range));
    }

}
