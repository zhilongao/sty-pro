package com.study.zl.algorithm.tree;

/**
 * @Author long
 * @Date 2019/8/30 9:17
 */
public class BinaryTreeUtil {

    /**
     * 给定一个排序数组，查找该数组中某一个值在该数组中的位置, 内部通过while循环实现
     * @param array
     * @param value
     * @return
     */
    public static int searchByWhile(int[] array, int value) {
        int result = -1;

        int left = 0;
        int right = array.length - 1;
        int middle = (left + right) / 2;
        while (left <= right) {
            if (array[middle] == value) {
                result = middle;
                break;
            }
            if (array[middle] > value) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            middle = (left + right) /2;
        }
        return result;
    }

    /**
     * 二分查找一个有序数组中的某个值，通过递归方式实现
     * @param key
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int searchByRecursion(int key, int[] array, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (array[middle] > key) {
            return searchByRecursion(key, array, left, middle - 1);
        } else if (array[middle] < key) {
            return searchByRecursion(key, array, middle + 1, right);
        } else {
            return middle;
        }
    }
}
