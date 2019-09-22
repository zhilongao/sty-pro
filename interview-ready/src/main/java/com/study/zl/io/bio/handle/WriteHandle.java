package com.study.zl.io.bio.handle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author long
 * @Date 2019/8/24 11:49
 */
public class WriteHandle implements RequestHandle {
    @Override
    public void handle(Socket socket) {
        // 处理输出 \r\n 回车换行
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            os.write("HTTP/1.1 200 OK\r\n".getBytes());
            os.write("Content-Type:application/json\n".getBytes());
            os.write("\r\n".getBytes());
            os.write("hello,world".getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
