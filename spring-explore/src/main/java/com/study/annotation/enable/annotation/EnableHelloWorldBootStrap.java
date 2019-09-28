package com.study.annotation.enable.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author long
 * @Date 2019/9/28 17:10
 */
@EnableHelloWorld
@Configuration
public class EnableHelloWorldBootStrap {

    public static void main(String[] args) {
        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册配置类
        context.register(EnableHelloWorldBootStrap.class);
        // 启动上下文
        context.refresh();
        // 获取名称为'helloWorld'的bean对象
        String helloWorld = context.getBean("helloWorld", String.class);
        // 输出
        System.out.printf("helloWorld = %s \n", helloWorld);
        // 关闭上下文
        context.close();
    }
}
