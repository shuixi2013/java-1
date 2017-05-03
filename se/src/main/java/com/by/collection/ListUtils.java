package com.by.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ListUtils {
	private ListUtils() {
	}

	public static boolean isEmpty(List<?> list) {
		return list == null || list.isEmpty() ? true : false;
	}

	/**
	 * 
	 * @param src
	 * @param target
	 * @return
	 */
	public static <T> List<T> copy(List<T> src, List<T> target) {
		ArrayList<T> list = new ArrayList<>();
		for (int i = src.size(); --i >= 0;) {
			list.add(src.get(i));
		}
		for (int i = target.size(); --i >= 0;) {
			list.add(target.get(i));
		}
		return list;
	}

	public static <T> List<T> addList(List<T> src, List<T> target) {
		for (int i = 0, k = src.size(); i < k; i++) {
			target.add(src.get(i));
		}
		return target;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> convertObjectToList(Object objs) {
		ArrayList<T> list = new ArrayList<T>();
		if (objs != null && objs instanceof Object[]) {
			Object[] objects = (Object[]) objs;
			for (int i = objects.length; --i >= 0; list.add((T) objects[i]))
				;
		}
		return list;
	}

	public static <T> List<T> toSingleElement(List<T> list) {
		ArrayList<T> temp = new ArrayList<T>(list.size());
		HashSet<T> tempSet = new HashSet<T>();
		for (int i = 0, k = list.size(); i < k; i++) {
			tempSet.add(list.get(i));
		}
		Iterator<T> it = tempSet.iterator();
		while (it.hasNext()) {
			temp.add(it.next());
		}
		return temp;
	}

}
