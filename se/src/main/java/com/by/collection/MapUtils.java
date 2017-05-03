package com.by.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapUtils {
	private MapUtils() {
	}

	public static int getRightSize(int size) {
		int order = 0;
		int mapSize = 0;
		while (true) {
			mapSize = 2 ^ order;
			if (mapSize * 0.75 > size) {
				break;
			}
			order++;
		}
		return mapSize;
	}

	public static boolean isEmpty(Map m) {
		return m == null || m.isEmpty() ? true : false;
	}

	public static List sortByKeyToList(Map map) {
		return sortByKeyToList(map, null);
	}

	public static List sortByKeyToList(Map map, Comparator comparator) {
		List keyList = sortKey(map, comparator);
		ArrayList valueList = new ArrayList();
		for (int i = keyList.size(); --i >= 0;) {
			valueList.add(map.get(keyList.get(i)));
		}
		return valueList;
	}

	public static List sortKey(Map map, Comparator comparator) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Iterator it = map.keySet().iterator();
		ArrayList keyList = new ArrayList();
		while (it.hasNext()) {
			keyList.add(it.next());
		}
		Collections.sort(keyList, comparator);
		return keyList;
	}
}
