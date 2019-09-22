package com.example.controller;

import com.example.entity.User;
import com.gupaoedu.starter.HelloFormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author long
 * @Date 2019/7/24 17:22
 */
@RestController
public class FormatController {

    @Autowired
    private HelloFormatTemplate helloFormatTemplate;

    @GetMapping("/format")
    public String format() {
        User u = new User("jack", 18);
        return helloFormatTemplate.doFormat(u);
    }
}
