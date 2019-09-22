package com.study.zl.t2;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @Author long
 * @Date 2019/8/8 15:50
 */
public class PCApp {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock =  new ReentrantLock();
        lock.lock();
        lock.tryLock();
        lock.tryLock(1000, TimeUnit.MILLISECONDS);

        lock.unlock();



        String host = "www.baidu.com";
        try {
            InetAddress[] allByName = InetAddress.getAllByName(host);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
