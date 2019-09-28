package com.study.annotation.enable.inter;

import org.springframework.stereotype.Component;

/**
 * @Author long
 * @Date 2019/9/28 17:21
 */
@Component
public class FtpServer implements Server {
    @Override
    public void start() {
        System.out.println("FTP服务器启动中...");
    }

    @Override
    public void stop() {
        System.out.println("FTP服务器关闭中...");
    }
}
