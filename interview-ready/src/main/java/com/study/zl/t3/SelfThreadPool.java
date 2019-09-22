package com.study.zl.t3;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author long
 * @Date 2019/8/8 22:34
 * 自定义线程池
 */
public class SelfThreadPool {

    private int poolSize; // 核心线程数
    private int maxSize; // 最大线程数
    private Set<Work> workers = new HashSet<>();// 用于存储工作线程
    private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10); // 用于存储任务列表
    private final DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory();



    // 构造方法，定义核心线程和工作线程
    public SelfThreadPool(int poolSize, int maxSize) {
        this.poolSize = poolSize;
        this.maxSize = maxSize;
    }

    // 线程池执行的方法
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }
        // 若是工作线程数小于核心线程数，则新建一个核心线程
        if (workers.size() < poolSize) {
            if (addWork(command)) {
                return;
            }
        }
        // 若是工作线程数已经大于或是等于核心线程数，将其放入到队列
        if (!queue.add(command)) {
            // 添加一个非核心线程

        }
    }

    public boolean addWork(Runnable command) {
        Work work = new Work(command);
        final Thread thread = work.thread;
        boolean add = workers.add(work);
        if (add) {
            thread.start();
            return true;
        }
        return false;
    }

    /**
     * 从队列中获取任务
     * @return
     */
    public Runnable getTask() {
        try {
            // 使用take方法，当线程从queue中获取不到任务时，会阻塞
            return queue.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Work线程的执行
     * @param work
     */
    public void runWork(Work work) {
        // 创建一个任务
        Runnable task = null;
        // task的初始值从work.firstTask取值,若是为null,从队列中取值，取完值后要将任务置空
        for (; (task = work.firstTask) != null || (task = getTask()) != null; work.firstTask = null) {
            task.run();
        }
    }


    /**
     * 定义工作线程类
     */
    class Work implements Runnable{
        private Runnable firstTask;// 任务
        private Thread thread;// 线程

        // 构造函数初始化firstTask和thread属性
        public Work(Runnable task) {
            this.firstTask = task;
            this.thread = defaultThreadFactory.newThread(this);
        }

        // 线程调用方法,调用线程池的runWork方法
        @Override
        public void run() {
            runWork(this);
        }
    }

    /**
     * 线程工厂类
     */
    static class DefaultThreadFactory {
        private final AtomicInteger pollNum = new AtomicInteger(1);
        private final AtomicInteger threadNum = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix ="poll-" + pollNum.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(group, runnable, namePrefix + threadNum.getAndIncrement(), 0);
        }
    }
}
