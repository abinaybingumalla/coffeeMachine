package com.dunzo.coffee.util;

import java.util.Map;

public class MapUtil {
    public static void print(Map<String, Integer> map) {
        System.out.print("==========");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.print("==========");
    }
}
