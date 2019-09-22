package com.gupao.study.service;

import com.gupao.study.order.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author long
 * @Date 2019/8/5 8:04
 */
public class ConsumerApp {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("dubbo-client.xml");

        OrderService orderService = (OrderService) context.getBean("orderService");

        System.out.println(orderService.saveHello("jack"));

        System.in.read();
    }
}
