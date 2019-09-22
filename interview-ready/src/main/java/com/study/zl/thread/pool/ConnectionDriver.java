package com.study.zl.thread.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 13:15
 * 连接驱动类
 */
public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(
                ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{ Connection.class },
                new ConnectionHandler());
    }

}
