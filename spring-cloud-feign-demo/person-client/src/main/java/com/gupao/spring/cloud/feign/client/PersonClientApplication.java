package com.gupao.spring.cloud.feign.client;

import com.gupao.spring.cloud.feign.api.service.PersonService;
import com.gupao.spring.cloud.feign.client.ribbon.FirstServerForeverRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * @Author long
 * @Date 2019/8/1 17:12
 * 服务发现 Feign 熔断处理
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = {PersonService.class})
@EnableHystrix
public class PersonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplication.class, args);
    }

    /**
     * 装载自定义负载均衡实现类
     * @return
     */
    @Bean
    public FirstServerForeverRule firstServerForeverRule() {
        return new FirstServerForeverRule();
    }

}
