package com.study.annotation.conditiional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @Author long
 * @Date 2019/9/29 17:37
 */
public class ConditionalOnSystemPropertyBootStrap {


    static {
        System.setProperty("language", "Chinese");
        // System.setProperty("language", "English");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConditionalMessageConfiguration.class);
        context.refresh();
        String message = context.getBean("message", String.class);
        System.out.println(message);
    }

}
