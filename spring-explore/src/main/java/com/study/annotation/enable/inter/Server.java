package com.study.annotation.enable.inter;

/**
 * @Author long
 * @Date 2019/9/28 17:19
 */
public interface Server {
    /**
     * 启动服务
     */
    void start();

    /**
     * 关闭服务
     */
    void stop();

    /**
     * 服务器类型
     */
    enum Type {
        HTTP, // HTTP服务器
        FTP // FTP服务器
    }
}
