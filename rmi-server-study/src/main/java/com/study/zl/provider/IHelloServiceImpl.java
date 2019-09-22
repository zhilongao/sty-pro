package com.study.zl.provider;


import com.study.zl.api.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author long
 * @Date 2019/7/21 16:00
 */
public class IHelloServiceImpl extends UnicastRemoteObject implements IHelloService {

    protected IHelloServiceImpl() throws RemoteException {
        super();
    }


    @Override
    public void sayHello() {
        System.out.println("hello!");
    }
}
