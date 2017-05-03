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
package com.by.jdbc.dbutils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.dbutils.QueryLoader;

/**
 * QueryLoaderTest
 */
public class QueryLoaderTest {

    private static final String QUERIES =
        "/org/apache/commons/dbutils/TestQueries.properties";

    public void testLoad() throws IOException {
        try {
            QueryLoader loader = QueryLoader.instance();
            Map<String,String> q = loader.load(QUERIES);
            Map<String,String> q2 = loader.load(QUERIES);

            loader.unload(QUERIES);
            Map<String,String> q3 = loader.load(QUERIES);

        } catch (IllegalArgumentException e) {
            // TODO Figure out why the Maven build can't find the properties
            // file.  The tests run fine in Eclipse so just catch this
            // exception for now.
        }
    }

}
