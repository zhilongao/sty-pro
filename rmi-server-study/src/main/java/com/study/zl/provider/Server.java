package com.study.zl.provider;


import com.study.zl.api.IHelloService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @Author long
 * @Date 2019/7/21 16:00
 */
public class Server {

    public static void main(String[] args) {
        try {
            IHelloService helloService = new IHelloServiceImpl();
            LocateRegistry.createRegistry(1099);
            //注册中心 key - value
            Naming.rebind("rmi://127.0.0.1/Hello",helloService);
            System.out.println("服务启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
