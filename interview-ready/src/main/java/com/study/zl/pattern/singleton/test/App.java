package com.study.zl.pattern.singleton.test;


import com.study.zl.pattern.singleton.lazy.LazyComplexSingleton;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author long
 * @Date 2019/3/10 16:07
 */
public class App {

    public static void main(String[] args) {
        safetyCheck(1000);
        reflectCheck(LazyComplexSingleton.class);
    }

    //CountDownLatch和CyclicBarrier的区别
    //CountdownLatch等待前n个线程执行完毕以后在继续执行
    //使用方式:
    //CountdownLatch latch = new CountdownLatch(5);
    //latch.await();
    //latch.countdown();

    //CyclicBarrier等待某n个线程同时执行到某一个状态时在一起执行
    //使用方式:
    //CyclicBarrier barrier = new CyclicBarrier(5);
    // barrier.await();
    
    private static void safetyCheck(int num){
        final CountDownLatch latch = new CountDownLatch(num);
        final CyclicBarrier barrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //等待所有线程就绪状态时运行
                    try{
                        System.out.println("开始执行创建任务");
                        latch.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    LazyComplexSingleton instance = LazyComplexSingleton.getInstance();
                    System.out.println(System.currentTimeMillis() + ":" + instance);
                    try {
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("所有任务检查的工作已经完成");
                }
            }).start();
            System.out.println("创建并启动线程i= " + i);
            latch.countDown();
        }
        System.out.println("==========check is over============");
    }

    private static void reflectCheck(Class cls) {
        try {
            Constructor constructor = cls.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            Object o = constructor.newInstance();
            System.out.println("not set reflect prevent, create a object:" + o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


