package com.by.collection;

import java.util.*;

public class MapUtils {
    private MapUtils() {
    }

    public static List sortByKeyToList(Map map) {
        return sortByKeyToList(map, null);
    }

    public static List sortByKeyToList(Map map, Comparator comparator) {
        List keyList = sortKey(map, comparator);
        ArrayList valueList = new ArrayList();
        for (int i = keyList.size(); --i >= 0; ) {
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
