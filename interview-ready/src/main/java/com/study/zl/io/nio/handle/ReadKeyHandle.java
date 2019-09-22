package com.study.zl.io.nio.handle;

import com.study.zl.io.nio.entity.SelfRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author long
 * @Date 2019/8/24 11:12
 * 读取请求处理逻辑
 */
public class ReadKeyHandle extends AbstractKeyHandle {

    /**
     * @param key
     * @throws IOException
     * 处理逻辑: 通过key获取到该channel，将该channel上的数据读取到缓冲区，然后将该channel注册到selector上面，
     *  订阅OP_WRITE事件，并将请求参数封装好传递给该key。
     */
    @Override
    public void doHandle(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        // 将请求信息读取到缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.clear();
        sc.read(byteBuffer);
        byteBuffer.flip();
        // 将请求信息解析成为字符串
        byte[] content = new byte[byteBuffer.limit()];
        byteBuffer.get(content);
        String requestParam = new String(content);
        // 对读取到的数据进行处理->相关协议解析
        SelfRequest request = dealRequestParam(requestParam);
        sc.register(key.selector(), SelectionKey.OP_WRITE, request);
    }



    private SelfRequest dealRequestParam(String requestParam) {
        // System.out.println("deal request param");
        System.out.println(requestParam);
        String[] paramsArray = requestParam.split("\r\n");
        SelfRequest request = null;
        int paramsLength = paramsArray.length;
        if (paramsLength > 0) {
            String reqLine = paramsArray[0];
            String[] reqLineArray = reqLine.split(" ");
            String methodName = reqLineArray[0];
            String protocolName = reqLineArray[2].split("\\/")[0];
            String protocolVersion = reqLineArray[2].split("\\/")[1];
            request = new SelfRequest(methodName, protocolName, protocolVersion, null, null);
            System.out.printf("methodName:%s,protocolName:%s,protocolVersion:%s\n", methodName, protocolName, protocolVersion);
        }
        Map<String, String> paramsMap = new HashMap<>();
        if (paramsLength > 1) {
            for (int i = 1; i < paramsLength; i++) {
                String param = paramsArray[i];
                if (param.equals("")) {
                    break;
                }
                String[] split = param.split(": ");
                paramsMap.put(split[0], split[1]);
            }
        }
        if (paramsMap.size() > 0) {
            if (paramsMap.containsKey("Accept-Language")) {
                String acceptLanguage = paramsMap.get("Accept-Language");
                request.setAcceptLanguage(acceptLanguage != null ? acceptLanguage : request.getAcceptLanguage());

            }
            if (paramsMap.containsKey("Content-Type")) {
                String contentType = paramsMap.get("Content-Type");
                request.setContentType(contentType != null ? contentType : request.getContentType());
            }
        }
        return request;
    }
}
