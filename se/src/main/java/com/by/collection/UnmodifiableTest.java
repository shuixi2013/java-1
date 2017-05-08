package com.by.collection;


import java.util.*;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 *
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class UnmodifiableTest {
    public static void main(String[] args) {
        List unmodifiableList = Collections.emptyList(); //空集合
        Set unmodifiableSet = Collections.singleton("Java"); //一个元素不可更改的集合
        unmodifiableSet.add("test1");
        Map scores = new HashMap();
        scores.put("test", 80);
        scores.put("Java", 82);
        Map unmodifiableMap = Collections.unmodifiableMap(scores); //不可更改的map
        unmodifiableList.add("test");
        unmodifiableMap.put("test", 90);
    }
}

