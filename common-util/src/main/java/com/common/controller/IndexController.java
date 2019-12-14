package com.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author long
 * @Date 2019/12/1 15:30
 */
@Controller
@RequestMapping
@ApiIgnore
public class IndexController {

    @RequestMapping("/index")
    public String jobList() {
        return "index";
    }
}
