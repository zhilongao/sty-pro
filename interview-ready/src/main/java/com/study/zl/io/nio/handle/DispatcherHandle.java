package com.study.zl.io.nio.handle;

import com.study.zl.io.nio.common.HandleKey;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/24 11:29
 */
public class DispatcherHandle implements SelectionKeyHandle {

    // 处理selector轮询事件
    private static Map<HandleKey, SelectionKeyHandle> handles = new HashMap<>();

    // 初始化时注册该handlers
    static {
        handles.put(HandleKey.Accept, new AcceptKeyHandle());
        handles.put(HandleKey.Write, new WriteKeyHandle());
        handles.put(HandleKey.Read, new ReadKeyHandle());
    }

    /**
     * 处理SelectionKey的逻辑,将key将给相应的处理器处理
     * @param key
     * @throws IOException
     */
    @Override
    public void handle(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            handles.get(HandleKey.Accept).handle(key);
        } else if (key.isReadable()) {
            handles.get(HandleKey.Read).handle(key);
        } else if (key.isWritable()) {
            handles.get(HandleKey.Write).handle(key);
        }
    }
}
