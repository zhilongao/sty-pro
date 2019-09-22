package com.gupao.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author long
 * @Date 2019/7/27 16:19
 */
@RestController
@RefreshScope
public class EchoController {

    @Value("${my.name}")
    private String myName = "jack-1";

    @GetMapping("/myName")
    public String getMyName() {
        return myName;
    }

}
