package com.study.zl.base;

import java.sql.Time;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author long
 * @Date 2019/7/28 16:26
 */
public class CollectionIssue {

    private static final Random random = new Random();

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();
        map1.put("", "");
        map1.get("");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("", "");
    }

}
