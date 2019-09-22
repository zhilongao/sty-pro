package com.study.zl.algorithm.tree;

import com.study.zl.algorithm.SortUtil;

/**
 * @Author long
 * @Date 2019/8/30 9:18
 */
public class TreeApp {

    public static void main(String[] args) {

        int[] params2 = new int[]{1, 5, 7,11,2,3,55};
        SortUtil.bubbleSort(params2);
        for (int i = 0; i < params2.length; i++) {
            System.out.print(params2[i] + "\t");
        }
        System.out.println("\n");

        int[] params1 = new int[]{1, 3, 5, 7, 11, 25, 90};
        int value = 3;
        int search = BinaryTreeUtil.searchByWhile(params1, value);
        System.out.println(search);

        int i = BinaryTreeUtil.searchByRecursion(value, params1, 0, params1.length - 1);
        System.out.println(i);
    }
}
