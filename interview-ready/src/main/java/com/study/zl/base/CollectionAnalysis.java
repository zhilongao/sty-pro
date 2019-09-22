package com.study.zl.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/15 14:17
 */
public class CollectionAnalysis {

    public static void main(String[] args) {
        // Integer的最大值
        System.out.println(Integer.MAX_VALUE + "");
        // HashMap的最大数组容量
        final int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY + "");

        ArrayList<String> list = new ArrayList<>();
        list.add("");


        HashMap<String, String> map = new HashMap<>();
        map.put("", "");
    }

}
