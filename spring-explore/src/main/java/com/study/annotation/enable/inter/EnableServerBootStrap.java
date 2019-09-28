package com.study.annotation.enable.inter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author long
 * @Date 2019/9/28 17:31
 */
@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootStrap {

    public static void main(String[] args) {
        // 构建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册配置类
        context.register(EnableServerBootStrap.class);
        // 启动
        context.refresh();
        // 获取Server对象
        Server server = context.getBean(Server.class);
        // 启动服务
        server.start();
        // 关闭服务
        server.stop();
        // 关闭上下文
        context.close();
    }
}
