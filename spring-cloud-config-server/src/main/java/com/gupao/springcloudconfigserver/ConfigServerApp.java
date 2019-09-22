package com.gupao.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.cloud.config.server.EnableConfigServer;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan
// 激活配置管理服务器
@EnableConfigServer
public class ConfigServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp.class, args);
    }

    /**
     * 自定义配置实现,绕过git实现
     * @return
     */
    /*@Bean
    public EnvironmentRepository environmentRepository() {
        return (String application, String profile, String label) -> {
            Environment environment = new Environment("default", profile);
            List<PropertySource> propertySources = environment.getPropertySources();
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("name", "微服务->小马哥");
            PropertySource propertySource = new PropertySource("map", source);
            propertySources.add(propertySource);
            return environment;
        };
    }*/

}
