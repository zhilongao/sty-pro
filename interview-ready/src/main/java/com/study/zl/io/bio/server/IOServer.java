package com.study.zl.io.bio.server;

import com.study.zl.io.bio.handle.DispatcherHandle;
import com.study.zl.io.bio.handle.RequestHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author long
 * @Date 2019/8/24 11:43
 */
public class IOServer {

    private static volatile boolean stop = false;

    private static RequestHandle handle;

    static {
        handle = new DispatcherHandle();
    }


    /**
     * 开启服务
     */
    public static void createServer(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.printf("服务器在端口%s上启动。。。\n", port);
            while (!stop) {
                try {
                    Socket socket = serverSocket.accept();
                    handle.handle(socket);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket !=  null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 关闭服务
     */
    public static void stopServer() {
        stop = true;
    }

}
