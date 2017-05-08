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

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.StringUtils} - Empty/Blank methods
 */
public class StringUtilsEmptyBlankTest  {

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertFalse(StringUtils.isEmpty(" "));
        Assert.assertFalse(StringUtils.isEmpty("foo"));
        Assert.assertFalse(StringUtils.isEmpty("  foo  "));
    }

    @Test
    public void testIsNotEmpty() {
        Assert.assertFalse(StringUtils.isNotEmpty(null));
        Assert.assertFalse(StringUtils.isNotEmpty(""));
        Assert.assertTrue(StringUtils.isNotEmpty(" "));
        Assert.assertTrue(StringUtils.isNotEmpty("foo"));
        Assert.assertTrue(StringUtils.isNotEmpty("  foo  "));
    }

    @Test
    public void testIsAnyEmpty() {
        Assert.assertTrue(StringUtils.isAnyEmpty((String) null));
        Assert.assertTrue(StringUtils.isAnyEmpty((String[]) null));
        Assert.assertTrue(StringUtils.isAnyEmpty(null, "foo"));
        Assert.assertTrue(StringUtils.isAnyEmpty("", "bar"));
        Assert.assertTrue(StringUtils.isAnyEmpty("bob", ""));
        Assert.assertTrue(StringUtils.isAnyEmpty("  bob  ", null));
        Assert.assertFalse(StringUtils.isAnyEmpty(" ", "bar"));
        Assert.assertFalse(StringUtils.isAnyEmpty("foo", "bar"));
    }

    @Test
    public void testIsNoneEmpty() {
        Assert.assertFalse(StringUtils.isNoneEmpty((String) null));
        Assert.assertFalse(StringUtils.isNoneEmpty((String[]) null));
        Assert.assertFalse(StringUtils.isNoneEmpty(null, "foo"));
        Assert.assertFalse(StringUtils.isNoneEmpty("", "bar"));
        Assert.assertFalse(StringUtils.isNoneEmpty("bob", ""));
        Assert.assertFalse(StringUtils.isNoneEmpty("  bob  ", null));
        Assert.assertTrue(StringUtils.isNoneEmpty(" ", "bar"));
        Assert.assertTrue(StringUtils.isNoneEmpty("foo", "bar"));
    }

    @Test
    public void testIsBlank() {
        Assert.assertTrue(StringUtils.isBlank(null));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(StringUtilsTest.WHITESPACE));
        Assert.assertFalse(StringUtils.isBlank("foo"));
        Assert.assertFalse(StringUtils.isBlank("  foo  "));
    }

    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(StringUtils.isNotBlank(null));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(StringUtilsTest.WHITESPACE));
        Assert.assertTrue(StringUtils.isNotBlank("foo"));
        Assert.assertTrue(StringUtils.isNotBlank("  foo  "));
    }

    @Test
    public void testIsAnyBlank() {
        Assert.assertTrue(StringUtils.isAnyBlank((String) null));
        Assert.assertTrue(StringUtils.isAnyBlank((String[]) null));
        Assert.assertTrue(StringUtils.isAnyBlank(null, "foo"));
        Assert.assertTrue(StringUtils.isAnyBlank(null, null));
        Assert.assertTrue(StringUtils.isAnyBlank("", "bar"));
        Assert.assertTrue(StringUtils.isAnyBlank("bob", ""));
        Assert.assertTrue(StringUtils.isAnyBlank("  bob  ", null));
        Assert.assertTrue(StringUtils.isAnyBlank(" ", "bar"));
        Assert.assertFalse(StringUtils.isAnyBlank("foo", "bar"));
    }

    @Test
    public void testIsNoneBlank() {
        Assert.assertFalse(StringUtils.isNoneBlank((String) null));
        Assert.assertFalse(StringUtils.isNoneBlank((String[]) null));
        Assert.assertFalse(StringUtils.isNoneBlank(null, "foo"));
        Assert.assertFalse(StringUtils.isNoneBlank(null, null));
        Assert.assertFalse(StringUtils.isNoneBlank("", "bar"));
        Assert.assertFalse(StringUtils.isNoneBlank("bob", ""));
        Assert.assertFalse(StringUtils.isNoneBlank("  bob  ", null));
        Assert.assertFalse(StringUtils.isNoneBlank(" ", "bar"));
        Assert.assertTrue(StringUtils.isNoneBlank("foo", "bar"));
    }
}
