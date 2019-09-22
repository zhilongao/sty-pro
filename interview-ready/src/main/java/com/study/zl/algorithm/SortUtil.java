package com.study.zl.algorithm;

/**
 * @Author long
 * @Date 2019/8/30 10:05
 */
public class SortUtil {

    /**
     * 使用冒泡排序算法对数组排序
     * @param array
     */
    public static void bubbleSort(int array[]) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array [j] > array [j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 使用快速排序算法对数组进行排序
     * @param array
     * @param low
     * @param high
     */
    public static void fastSort (int array[], int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int temp = array[low];
        while (i < j) {
            // 从右向左查找到第一个小于temp的元素，保存其下标值
            while (array[j] > temp && i < j) {
                j --;
            }
            // 从左向右查找到第一个大于temp的元素， 保存其下标值
            while (array[i] <= temp && i < j) {
                i ++;
            }
            // 交换这两个元素
            int swap = array[i];
            array[i] = array[j];
            array[j] = swap;
        }
        // 将temp存放到中间
        array[low] = array[i];
        array[i] = temp;
        // 快速排序左边的元素
        fastSort(array, low, j - 1);
        // 快速排序右边的元素
        fastSort(array, j + 1, high);
    }



    // 归并排序算法
    public static  void sortMerge(int[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(int array[], int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (R + L) / 2;
        // 排序左半部分
        sort(array, L, mid);
        // 排序右半部分
        sort(array, mid + 1, R);
        // 合并
        merge(array, L, mid, R);
    }
    public static void merge(int[] array, int L, int mid, int R) {
        int[] temp = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        // 将数组排序后放入到临时数组temp中
        while (p1 <= mid && p2 <= R) {
            if (array[p1] < array[p2]) {
                temp[i++] = array[p1++];
            } else {
                temp[i++] = array[p2++];
            }
        }
        while(p1 <= mid) {
            temp[i++] = array[p1++];
        }
        while(p2 <= R) {
            temp[i++] = array[p2++];
        }
        // 将临时数组中的元素移动到array中
        for (int k = 0; k < temp.length; k ++) {
            array[L + k] = temp[k];
        }
    }


    // 普通选择排序
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    // 插入排序
    public static void insertSort(int[] array) {
        int i,j;
        for (i = 1; i < array.length; i ++) {
            int temp = array[i];
            for (j = i - 1; j >=0 && array[j] > temp; j --) {
                // 将数组中的元素后移一位
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
    }








    public static void main(String[] args){
        int[] array1 = {6,6,1,2,7,9,3,4,5,10,8};
        //fastSort(array1, 0, 9);
        insertSort(array1);
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i] + "\t");
        }
    }
}
