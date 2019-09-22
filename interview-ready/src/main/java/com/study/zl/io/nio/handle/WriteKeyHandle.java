package com.study.zl.io.nio.handle;

import com.study.zl.io.nio.entity.SelfRequest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @Author long
 * @Date 2019/8/24 11:13
 * 写入逻辑
 */
public class WriteKeyHandle extends AbstractKeyHandle {

    /**
     * @param key
     * @throws IOException
     * 处理逻辑: 获取key上面的参数，针对参数做处理，处理完成后将响应信息写入到channel,最后关闭channel
     */
    @Override
    public void doHandle(SelectionKey key) throws IOException {
        SelfRequest request = (SelfRequest) key.attachment();
        if (request != null) {
            System.out.println(request);
        }
        // 获取响应实体
        String responseEntity = createResponseEntity(request);
        ByteBuffer wrap = ByteBuffer.wrap(responseEntity.getBytes());
        SocketChannel channel = (SocketChannel) key.channel();
        // 将响应输出并关闭channel
        channel.write(wrap);
        channel.close();
    }


    /**
     * 创建响应实体
     *
     * @param request
     * @return
     */
    private String createResponseEntity(SelfRequest request) {
        String acceptLanguage = request.getAcceptLanguage();
        // 创建响应实体
        StringBuffer result = new StringBuffer();
        // 报文首部
        result.append("HTTP/1.1 200 OK\r\n");
        // 内容协商->根据客户端的acceptLanguage,响应xml或者是json数据格式
        if (acceptLanguage.equals("application/xml")) {
            result.append("Content-Type:application/xml\n\r\n");
            String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<note>\n" +
                    "  <to>Tove</to>\n" +
                    "  <from>Jani</from>\n" +
                    "  <heading>Reminder</heading>\n" +
                    "  <body>Don't forget me this weekend!</body>\n" +
                    "</note>";
            result.append(content);
        } else {
            result.append("Content-Type:application/xml\n\r\n");
            // 报文主体
            // result.append("server response to client!");
            String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<note>\n" +
                    "  <to>Tove</to>\n" +
                    "  <from>Jani</from>\n" +
                    "  <heading>Reminder</heading>\n" +
                    "  <body>Don't forget me this weekend!</body>\n" +
                    "</note>";
            result.append(content);
        }
        return result.toString();
    }

}
