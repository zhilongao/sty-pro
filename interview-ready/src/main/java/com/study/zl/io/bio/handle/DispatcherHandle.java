package com.study.zl.io.bio.handle;

import com.study.zl.io.bio.common.HandleKey;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/24 11:45
 */
public class DispatcherHandle implements RequestHandle {

    private static Map<HandleKey, RequestHandle> handlers = new HashMap<>();

    static {
        handlers.put(HandleKey.Read, new ReadHandle());
        handlers.put(HandleKey.Write, new WriteHandle());
    }

    @Override
    public void handle(Socket socket) {
        // 读取请求
        handlers.get(HandleKey.Read).handle(socket);
        // 返回响应
        handlers.get(HandleKey.Write).handle(socket);
    }
}
