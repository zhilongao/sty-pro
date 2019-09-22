package com.study.zl.consumer;



import com.study.zl.api.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Author long
 * @Date 2019/7/21 16:00
 */
public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IHelloService service = (IHelloService)Naming.lookup("rmi://127.0.0.1/Hello");
        service.sayHello();
    }
}
