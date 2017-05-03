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

import static org.apache.commons.lang3.JavaVersion.JAVA_1_1;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_2;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_3;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_4;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_5;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_6;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_7;
import static org.apache.commons.lang3.JavaVersion.JAVA_1_8;
import static org.apache.commons.lang3.JavaVersion.JAVA_9;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Locale;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.SystemUtils}.
 * 
 * Only limited testing can be performed.
 */
public class SystemUtilsTest {

    @Test
    public void testConstructor() {
        Assert.assertNotNull(new SystemUtils());
        final Constructor<?>[] cons = SystemUtils.class.getDeclaredConstructors();
        Assert.assertEquals(1, cons.length);
        Assert.assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        Assert.assertTrue(Modifier.isPublic(SystemUtils.class.getModifiers()));
        Assert.assertFalse(Modifier.isFinal(SystemUtils.class.getModifiers()));
    }

    /**
     * Assums no security manager exists.
     */
    @Test
    public void testGetJavaHome() {
        final File dir = SystemUtils.getJavaHome();
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
    }

    /**
     * Assums no security manager exists.
     */
    @Test
    public void testGetJavaIoTmpDir() {
        final File dir = SystemUtils.getJavaIoTmpDir();
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
    }

    /**
     * Assums no security manager exists.
     */
    @Test
    public void testGetUserDir() {
        final File dir = SystemUtils.getUserDir();
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
    }

    /**
     * Assums no security manager exists.
     */
    @Test
    public void testGetUserHome() {
        final File dir = SystemUtils.getUserHome();
        Assert.assertNotNull(dir);
        Assert.assertTrue(dir.exists());
    }

    @Test
    @SuppressWarnings("deprecation")
    public void testIS_JAVA() {
        final String javaVersion = SystemUtils.JAVA_VERSION;
        if (javaVersion == null) {
            Assert.assertFalse(SystemUtils.IS_JAVA_1_1);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_2);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_3);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_4);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_5);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_6);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_7);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_8);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_9);
            Assert.assertFalse(SystemUtils.IS_JAVA_9);
        } else if (javaVersion.startsWith("1.6")) {
            Assert.assertFalse(SystemUtils.IS_JAVA_1_1);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_2);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_3);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_4);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_5);
            Assert.assertTrue(SystemUtils.IS_JAVA_1_6);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_7);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_8);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_9);
            Assert.assertFalse(SystemUtils.IS_JAVA_9);
        } else if (javaVersion.startsWith("1.7")) {
            Assert.assertFalse(SystemUtils.IS_JAVA_1_1);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_2);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_3);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_4);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_5);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_6);
            Assert.assertTrue(SystemUtils.IS_JAVA_1_7);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_8);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_9);
            Assert.assertFalse(SystemUtils.IS_JAVA_9);
        } else if (javaVersion.startsWith("1.8")) {
            Assert.assertFalse(SystemUtils.IS_JAVA_1_1);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_2);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_3);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_4);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_5);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_6);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_7);
            Assert.assertTrue(SystemUtils.IS_JAVA_1_8);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_9);
            Assert.assertFalse(SystemUtils.IS_JAVA_9);
        } else if (javaVersion.startsWith("9")) {
            Assert.assertFalse(SystemUtils.IS_JAVA_1_1);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_2);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_3);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_4);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_5);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_6);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_7);
            Assert.assertFalse(SystemUtils.IS_JAVA_1_8);
            Assert.assertTrue(SystemUtils.IS_JAVA_1_9);
            Assert.assertTrue(SystemUtils.IS_JAVA_9);
        } else {
            System.out.println("Can't test IS_JAVA value: "+javaVersion);
        }
    }

    @Test
    public void testIS_OS() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
            Assert.assertFalse(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_SOLARIS);
            Assert.assertFalse(SystemUtils.IS_OS_LINUX);
            Assert.assertFalse(SystemUtils.IS_OS_MAC_OSX);
        } else if (osName.startsWith("Windows")) {
            Assert.assertFalse(SystemUtils.IS_OS_UNIX);
            Assert.assertTrue(SystemUtils.IS_OS_WINDOWS);
        } else if (osName.startsWith("Solaris")) {
            Assert.assertTrue(SystemUtils.IS_OS_SOLARIS);
            Assert.assertTrue(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
        } else if (osName.toLowerCase(Locale.ENGLISH).startsWith("linux")) {
            Assert.assertTrue(SystemUtils.IS_OS_LINUX);
            Assert.assertTrue(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
        } else if (osName.startsWith("Mac OS X")) {
            Assert.assertTrue(SystemUtils.IS_OS_MAC_OSX);
            Assert.assertTrue(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
        } else if (osName.startsWith("OS/2")) {
            Assert.assertTrue(SystemUtils.IS_OS_OS2);
            Assert.assertFalse(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
        } else if (osName.startsWith("SunOS")) {
            Assert.assertTrue(SystemUtils.IS_OS_SUN_OS);
            Assert.assertTrue(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
        } else if (osName.startsWith("FreeBSD")) {
            Assert.assertTrue(SystemUtils.IS_OS_FREE_BSD);
            Assert.assertTrue(SystemUtils.IS_OS_UNIX);
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
        } else {
            System.out.println("Can't test IS_OS value: " + osName);
        }
    }

    @Test
    public void testIS_zOS() {
        final String osName = System.getProperty("os.name");
        if (osName == null) {
            Assert.assertFalse(SystemUtils.IS_OS_ZOS);
        } else if (osName.contains("z/OS")) {
            Assert.assertFalse(SystemUtils.IS_OS_WINDOWS);
            Assert.assertTrue(SystemUtils.IS_OS_ZOS);
        }
    }

    @Test
    public void testJavaVersionMatches() {
        String javaVersion = null;
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.0";
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.1";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.2";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.3.0";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.3.1";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.4.0";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.4.1";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.4.2";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.5.0";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.6.0";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.7.0";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "1.8.0";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
        javaVersion = "9";
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.0"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.1"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.2"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.3"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.4"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.5"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.6"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.7"));
        Assert.assertFalse(SystemUtils.isJavaVersionMatch(javaVersion, "1.8"));
        Assert.assertTrue(SystemUtils.isJavaVersionMatch(javaVersion, "9"));
    }

    @Test
    public void testIsJavaVersionAtLeat() throws Exception {
        if (SystemUtils.IS_JAVA_1_6) {
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
            Assert.assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
            Assert.assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
            Assert.assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_9));
        } else if (SystemUtils.IS_JAVA_1_7) {
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
            Assert.assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
            Assert.assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_9));
        } else if (SystemUtils.IS_JAVA_1_8) {
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
            Assert.assertFalse(SystemUtils.isJavaVersionAtLeast(JAVA_9));
        } else if (SystemUtils.IS_JAVA_9) {
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_1));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_2));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_3));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_4));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_5));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_6));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_7));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_1_8));
            Assert.assertTrue(SystemUtils.isJavaVersionAtLeast(JAVA_9));
        }
    }

    @Test
    public void testOSMatchesName() {
        String osName = null;
        Assert.assertFalse(SystemUtils.isOSNameMatch(osName, "Windows"));
        osName = "";
        Assert.assertFalse(SystemUtils.isOSNameMatch(osName, "Windows"));
        osName = "Windows 95";
        Assert.assertTrue(SystemUtils.isOSNameMatch(osName, "Windows"));
        osName = "Windows NT";
        Assert.assertTrue(SystemUtils.isOSNameMatch(osName, "Windows"));
        osName = "OS/2";
        Assert.assertFalse(SystemUtils.isOSNameMatch(osName, "Windows"));
    }

    @Test
    public void testOSMatchesNameAndVersion() {
        String osName = null;
        String osVersion = null;
        Assert.assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
        osName = "";
        osVersion = "";
        Assert.assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
        osName = "Windows 95";
        osVersion = "4.0";
        Assert.assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
        osName = "Windows 95";
        osVersion = "4.1";
        Assert.assertTrue(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
        osName = "Windows 98";
        osVersion = "4.1";
        Assert.assertTrue(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
        osName = "Windows NT";
        osVersion = "4.0";
        Assert.assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
        osName = "OS/2";
        osVersion = "4.0";
        Assert.assertFalse(SystemUtils.isOSMatch(osName, osVersion, "Windows 9", "4.1"));
    }

    @Test
    public void testOsVersionMatches() throws Exception {
        String osVersion = null;
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));

        osVersion = "";
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));

        osVersion = "10";
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));

        osVersion = "10.1";
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));

        osVersion = "10.1.1";
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));

        osVersion = "10.10";
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));

        osVersion = "10.10.1";
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1"));
        Assert.assertFalse(SystemUtils.isOSVersionMatch(osVersion, "10.1.1"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10"));
        Assert.assertTrue(SystemUtils.isOSVersionMatch(osVersion, "10.10.1"));
    }

    @Test
    public void testJavaAwtHeadless() {
        final String expectedStringValue = System.getProperty("java.awt.headless");
        final String expectedStringValueWithDefault = System.getProperty("java.awt.headless", "false");
        Assert.assertNotNull(expectedStringValueWithDefault);
        final boolean expectedValue = Boolean.valueOf(expectedStringValue).booleanValue();
        if (expectedStringValue != null) {
            Assert.assertEquals(expectedStringValue, SystemUtils.JAVA_AWT_HEADLESS);
        }
        Assert.assertEquals(expectedValue, SystemUtils.isJavaAwtHeadless());
        Assert.assertEquals(expectedStringValueWithDefault, "" + SystemUtils.isJavaAwtHeadless());
    }
}
