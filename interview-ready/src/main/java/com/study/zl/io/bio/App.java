package com.study.zl.io.bio;

import com.study.zl.io.bio.server.IOServer;

/**
 * @Author long
 * @Date 2019/8/24 11:43
 */
public class App {

    public static void main(String[] args) {
        IOServer.createServer(8090);
    }
}
