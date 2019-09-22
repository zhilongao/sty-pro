package com.gupao.service;

import com.alibaba.dubbo.container.Main;

/**
 * @Author long
 * @Date 2019/8/5 7:56
 */
public class ServerApp {

    public static void main(String[] args) {
        // 默认情况下会使用Spring容器来启动服务
        System.setProperty("dubbo.provider.com.alibaba.dubbo.config.ProviderConfig.port", "9999");
        System.setProperty("dubbo.provider.com.alibaba.dubbo.config.ProviderConfig.host", "127.0.0.1");
        System.setProperty("dubbo.registry.address", "127.0.0.0:2181|127.0.0.0:2182");

        Main.main(new String[]{"spring","log4j"});
    }
}
