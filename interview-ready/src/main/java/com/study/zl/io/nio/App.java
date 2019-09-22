package com.study.zl.io.nio;

import com.study.zl.io.nio.server.NIOServer;

import java.io.IOException;

/**
 * @Author long
 * @Date 2019/8/24 11:05
 */
public class App {

    public static void main(String[] args) {
        NIOServer nioServer = new NIOServer(8070);
        try {
            nioServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
