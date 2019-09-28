package com.study.annotation.enable.inter;

import org.springframework.stereotype.Component;

/**
 * @Author long
 * @Date 2019/9/28 17:20
 */
@Component
public class HttpServer implements Server {
    @Override
    public void start() {
        System.out.println("HTTP服务器启动中...");
    }

    @Override
    public void stop() {
        System.out.println("HTTP服务器关闭中...");
    }
}
