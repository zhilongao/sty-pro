package com.study.zl.thread.info;

/**
 * @Author long
 * @Date 2019/8/25 11:46
 */
public class Join {

    public static void main(String[] args) {
        Thread prevThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(prevThread), String.valueOf(i));
            thread.start();
            prevThread = thread;
        }

    }


    static class Domino implements Runnable {
        private Thread prev;

        public Domino(Thread prev) {
            this.prev = prev;
        }

        @Override
        public void run() {
            try {
                prev.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is running!");
        }
    }


}
