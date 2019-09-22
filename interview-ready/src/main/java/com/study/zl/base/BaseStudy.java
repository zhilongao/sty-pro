package com.study.zl.base;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/29 16:56
 */
public class BaseStudy {

    public static void main(String[] args) {
        int[] params = {1,3,5,4,7};
        int lengthOfLCIS = findLengthOfLCIS(params);
        System.out.println(lengthOfLCIS);
    }

    // 给定一个未经过排序的数组，找到最长且连续的递增序列
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int curr = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > nums[i - 1]) {
                curr ++;
            } else {
                max = Math.max(max, curr);
                curr = 1;
            }
        }
        return Math.max(curr, max);
    }



    private static void doSomething() {
        int j = 0;
        while (j < 2) {
            try {
                int a = 0 / 0;
                break;
            } catch (Exception ex) {
                // 若是重试了一次数据还未同步成功，抛出异常
                if (j == 1) {
                    throw ex;
                } else {

                }
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            j++;
        }
        System.out.println("---------");
    }

}
