package com.by.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * description:
 * create       2017/6/20 15:34
 *
 * @author email:baoyang@jd.com,ERP:baoyang3
 * @version 1.0.0
 */
public class BiMapTest {

    public static void main(String args[]) {
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Mahesh"));
    }

}
