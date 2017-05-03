package com.by.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class collectionUtils {
	
	public static <T> T copy(Collection<? super T> dest, Collection<T> src) {
		T last = null;
		for (T ele : src) {
			last = ele;
			dest.add(ele);
		}
		return last;
	}

	public static void main(String[] args) {
		List<Number> ln = new ArrayList<>();
		List<Integer> li = new ArrayList<>();
		li.add(5);
		Integer last = copy(ln, li);
		System.out.println(last);
	}
}
