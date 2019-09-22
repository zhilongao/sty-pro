package com.study.zl.thread.info;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Author long
 * @Date 2019/8/25 11:27
 */
public class Piped {

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        // 将输入流和输出流进行连接
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();

        // 从控制台输入字符,通过PipedWriter传入到PipedReader,然后printThread会将读取到的字符打印出来
        int receive = 0;
        try {
            while ((receive = System.in.read()) != -1) {
                out.write(receive);
            }
        }finally {
            out.close();
        }
    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.print((char)receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
