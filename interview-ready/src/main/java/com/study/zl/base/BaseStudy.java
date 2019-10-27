package com.study.zl.base;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/29 16:56
 */
public class BaseStudy {

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":完成最后任务!");
            }
        });
        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }

    static class TaskThread extends Thread {
        CyclicBarrier barrier;
        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + " 到达栅栏 A");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 A");
                Thread.sleep(2000);
                System.out.println(getName() + " 到达栅栏 B");
                barrier.await();
                System.out.println(getName() + " 冲破栅栏 B");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
