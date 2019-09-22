package com.example.s1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author long
 * @Date 2019/8/18 10:12
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "hello,world";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
        // SpringBootApplication相关注解
    }
}
