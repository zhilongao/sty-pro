package com.study.zl.thread.context;

import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/27 9:25
 */
public class VariablesChange {
    public static void main(String[] args) {
        Resource commonResource = new Resource();
        Thread writeThread = new WriteThread(commonResource);
        Thread readThread = new ReadThread(commonResource);
        writeThread.start();
        readThread.start();
    }
}

class WriteThread extends Thread {
    private Resource resource;
    public WriteThread(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        try {
            resource.writer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ReadThread extends Thread {
    private Resource resource;
    public ReadThread(Resource resource) {
        this.resource = resource;
    }
    @Override
    public void run() {
        resource.reader();
    }
}


class Resource {
    int a = 0;
    boolean flag = false;

    public void writer() throws InterruptedException {
        a = 1;
        TimeUnit.MILLISECONDS.sleep(20);
        flag = true;
    }

    public void reader() {
        if (!flag) {
            int i = a * a;
            System.out.println("i=" + i);
        }
    }
}