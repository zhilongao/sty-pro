package com.gupao.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * {@link com.gupao.user.service.UserService 用户服务} 消费引导类
 *
 * @Author long
 * @Date 2019/7/28 11:08
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceConsumerBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceConsumerBootstrap.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
