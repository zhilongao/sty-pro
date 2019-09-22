package com.gupao.spring.cloud.feign.person.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author long
 * @Date 2019/8/1 17:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PersonServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProviderApplication.class, args);
    }
}
