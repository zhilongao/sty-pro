package com.study.zl.thread.info;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Author long
 * @Date 2019/8/25 9:27
 */
public class MultiThread {

    public static void main(String[] args) {
        // 获取java线程管理ThreadMXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
