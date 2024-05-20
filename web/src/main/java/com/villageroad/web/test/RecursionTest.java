package com.villageroad.web.test;

import java.util.*;

/**
 * 递归问题之考古学家
 */
public class RecursionTest {
    private static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        int n = 3;
        String str = "abc";
        combine(str, "");
        Set<String> strings = new LinkedHashSet<>(res);
        System.out.println(strings);
    }

    public static void combine(String str, String cur) {
        if (str.length() == 0) {
            res.add(cur);
            return;
        } else {
            int len = str.length();
            for (int i = 0; i < len; i++) {
                String a = str.charAt(i) + "";
                String b = str.substring(0, i) + str.substring(i + 1);
                combine(b, cur + a);
            }
            return;
        }
    }
}
