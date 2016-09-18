package com.pcms.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArrayUtil {

    public static List<?> getArray(Object... items) {
        List<Object> containers = new ArrayList();
        containers.addAll(Arrays.asList(items));
        return containers;
    }

    public static Map<String, String> getItemByValue(List<Map<String, String>> containers, String value) {
        for (int i = 0; i < containers.size(); i++) {
            Map item = containers.get(i);
            if (item.containsKey(value)) {
                return item;
            }
        }
        return null;
    }
}
