package com.example.controller;

import com.example.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author long
 * @Date 2019/7/26 9:59
 */

@RestController
public class UserInfoController {

    @PostMapping("/user/{age}")
    public User createUserInfo(@PathVariable int age, @RequestParam(name = "name", required = false) String name) {
        User user = new User(name, age);
        return user;
    }

    /**
     * json转properties
     * @param user
     * @return
     */
    @RequestMapping(
            value = "/user/json/to/properties",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, // consumes对应Content-Type    application/json;charset=UTF-8
            produces = "application/properties+user"         // produces对应Accept
    )
    public User jsonToProperties(@RequestBody User user) {
        // @RequestBody的内容是json
        // 响应的内容是Properties
        return user;
    }

    /**
     * properties转json
     * @param user
     * @return
     */
    @RequestMapping(
            value = "/user/properties/to/json",
            consumes = "application/properties+user",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public User propertiesToJson(@RequestBody User user) {
        return user;
    }
}
