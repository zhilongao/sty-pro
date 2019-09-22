package com.study.zl.io.nio.server;

import com.study.zl.io.nio.handle.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;

/**
 * @Author long
 * @Date 2019/8/24 11:05
 * NIO Server
 */
public class NIOServer {
    // Selector->注册channel
    Selector selector = null;
    // ServerSocketChannel->服务端channel,类似ServerSocket
    ServerSocketChannel serverSocketChannel;
    // NIO服务关闭标识
    private volatile boolean stop = false;
    // DispatcherHandle
    private static SelectionKeyHandle dispatcherHandle;

    static {
        // 初始化handler
        dispatcherHandle = new DispatcherHandle();
    }


    public NIOServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            // 设置channel为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 为channel绑定端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            // 将channel注册到selector上，监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.printf("server start on port:%s\n", port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动服务
     * @throws IOException
     */
    public void start() throws IOException {
        while (!stop) {
            // 获取到等待处理的IO事件数量
            int readyChannels = selector.select();
            // 若是等待处理的IO事件数量为0,不处理
            if (readyChannels == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 处理这些SelectionKey
            while (iterator.hasNext()) {
                // 获取到该key
                SelectionKey key = iterator.next();
                // 移除该key
                iterator.remove();
                // 将SelectionKey交给各自不同的Handle来处理
                dispatcherHandle.handle(key);
            }
        }
    }

    /**
     * 停止服务
     */
    public void stop() {
        this.stop = true;

    }
}

