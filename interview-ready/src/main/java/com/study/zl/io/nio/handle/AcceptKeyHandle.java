package com.study.zl.io.nio.handle;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author long
 * @Date 2019/8/24 11:10
 * 接收请求处理逻辑
 */
public class AcceptKeyHandle extends AbstractKeyHandle {

    /**
     * @param key
     * @throws IOException
     * 处理逻辑: 接收一个SocketChannel连接，将该SocketChannel连接设置为非阻塞，并将其注册到key对应的Selector上，
     * 订阅OP_READ事件
     */
    @Override
    public void doHandle(SelectionKey key) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ);
    }
}
