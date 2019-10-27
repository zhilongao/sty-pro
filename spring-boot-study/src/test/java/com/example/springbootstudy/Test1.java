package com.example.springbootstudy;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @Author long
 * @Date 2019/10/13 10:17
 */
public class Test1 {

    public static void main(String[] args) {
        String name = EnableAutoConfiguration.class.getName();
        System.out.println(name);
    }
}
