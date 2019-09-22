package com.gupao.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * {@link com.gupao.user.service.UserService 用户服务} 引导类
 *
 * @Author long
 * @Date 2019/7/28 10:48
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceProviderBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderBootstrap.class, args);
    }
}
