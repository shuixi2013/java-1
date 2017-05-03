package com.by.collection;

import java.util.*;

/**
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class PerformanceTest {
	public static void main(String[] args) {
		String[] tst1 = new String[900000];
		for (int i = 0; i < 900000; i++) {
			tst1[i] = String.valueOf(i);
		}
		ArrayList al = new ArrayList();
		for (int i = 0; i < 900000; i++) {
			al.add(tst1[i]);
		}
		LinkedList ll = new LinkedList();
		for (int i = 0; i < 900000; i++) {
			ll.add(tst1[i]);
		}
		long start = System.currentTimeMillis();
		for (Iterator it = al.iterator(); it.hasNext();) {
			it.next();
		}
		System.out.println((System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		for (Iterator<String> it = ll.iterator(); it.hasNext();) {
			it.next();
		}
		System.out.println((System.currentTimeMillis() - start));
	}
}
