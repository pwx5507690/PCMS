package com.pcms.core.util;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    public static List<?> getArray(Object... items) {
        List<Object> containers = new ArrayList();
        for (Object item : items) {
            containers.add(item);
        }
        return containers;
    }
}
