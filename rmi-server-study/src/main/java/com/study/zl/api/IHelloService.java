package com.study.zl.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author long
 * @Date 2019/7/21 15:59
 */
public interface IHelloService  extends Remote {
    /**
     * sayHello
     */
    void sayHello() throws RemoteException;
}
