package com.study.zl.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author long
 * @Date 2019/8/11 16:39
 * 主要是CountDownLatch Semaphore CyclicBarrier关键字的使用
 */
public class JucUtil {

    public static void main(String[] args) {
        countDownLatchDemo();
        // semaphoreDemo();
        // cyclicBarrierDemo();
        Executors.newFixedThreadPool(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Future<?> submit = service.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    public static void countDownLatchDemo() {
        ExecutorService service = Executors.newFixedThreadPool(5);
        Set<String> priIds = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            priIds.add("JXJ000000" + i);
        }
        CountDownLatch latch = new CountDownLatch(priIds.size());
        for (String prcId : priIds) {
            service.execute(new SyncData(prcId, latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
        new DataAnalysis().start();
    }

    public static void semaphoreDemo() {
        // 限流 permits->令牌  最大支持多少个线程 公平和非公平-->可以做限流的操作
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i ++) {
            new ParkCar(i+1, semaphore).start();
        }
    }

    public static void cyclicBarrierDemo() {
        // 栅栏->循环屏障，可以使得一组线程达到同步点前阻塞
        CyclicBarrier barrier = new CyclicBarrier(3, new DataAnalysis());
        new DataImport("file1", barrier).start();
        new DataImport("file2", barrier).start();
        new DataImport("file3", barrier).start();

    }
}

/**
 * CountDownLatch关键字使用场景
 */
class SyncData implements Runnable {
    private String prcId;
    private CountDownLatch latch;

    public SyncData(String prcId, CountDownLatch latch) {
        this.prcId = prcId;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.printf("客户%s信息同步中\n", prcId);
            TimeUnit.MILLISECONDS.sleep(3000);
            System.out.printf("客户%s信息同步完成\n", prcId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}



/**
 * CyclicBarrier关键字的使用场景
 */
class DataImport extends Thread {
    private CyclicBarrier barrier;
    private String path;

    public DataImport(String path, CyclicBarrier barrier) {
        this.path = path;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.printf("开始导入:%s 数据\n", path);
        try {
            barrier.await();//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class DataAnalysis extends Thread {
    @Override
    public void run() {
        System.out.println("开始进行数据分析");
    }
}


/**
 * Semaphore的使用场景
 */
class ParkCar extends Thread {
    private int num;
    private Semaphore semaphore;

    public ParkCar(int num, Semaphore semaphore) {
        this.num = num;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.printf("第%s辆车开进来挺好了\n", num);
            TimeUnit.MILLISECONDS.sleep(3000);
            System.out.printf("第%s两车开走了\n", num);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
