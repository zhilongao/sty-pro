package com.study.zl.io.bio.handle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author long
 * @Date 2019/8/24 11:48
 */
public class ReadHandle implements RequestHandle {


    @Override
    public void handle(Socket socket) {
        // 处理输入
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null && s.length() > 0) {
                System.out.println(s);
                s = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
