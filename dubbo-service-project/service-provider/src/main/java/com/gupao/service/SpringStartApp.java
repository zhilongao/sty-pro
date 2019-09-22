package com.gupao.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author long
 * @Date 2019/7/18 15:53
 */
public class SpringStartApp {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/dubbo-server.xml");
        context.start();
        System.in.read();
    }
}
