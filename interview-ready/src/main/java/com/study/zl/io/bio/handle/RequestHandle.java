package com.study.zl.io.bio.handle;

import java.net.Socket;

/**
 * @Author long
 * @Date 2019/8/24 11:42
 */
public interface RequestHandle {

    void handle(Socket socket);
}
