package com.study.annotation.component;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author long
 * @Date 2019/9/21 16:24
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocation("classpath:beans1.xml");
        context.refresh();
        StringService stringService = (StringService)context.getBean("stringService");
        System.out.printf("stringService.findAll() = %s \n", stringService.findAll());
    }
}
