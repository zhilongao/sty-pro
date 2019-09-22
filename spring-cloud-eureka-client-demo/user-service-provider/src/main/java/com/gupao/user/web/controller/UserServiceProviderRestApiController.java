package com.gupao.user.web.controller;

import com.gupao.user.domain.User;
import com.gupao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * {@link UserService 用户服务} 提供方 REST API {@link RestController}
 *
 * @Author long
 * @Date 2019/7/28 10:40
 */
@RestController
public class UserServiceProviderRestApiController {

    @Autowired
    private UserService userService;

    /**
     * @param user
     * @return 如果保存成功的话返回{@link User},否则返回<code>null</code>
     */
    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user) {
        if (userService.save(user)) {
            System.out.println("UserService 用户服务方保存用户成功!" + user);
            return user;
        }
        return null;
    }

    /**
     * 罗列所有用户数据
     * @return 所有用户数据
     */
    @GetMapping("/user/list")
    public Collection<User> list() {
        return userService.findAll();
    }

}
