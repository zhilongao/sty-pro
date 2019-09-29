package com.study.annotation.auto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author long
 * @Date 2019/9/29 14:30
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = SpringWebMvcConfiguration.class)
public class SpringWebMvcConfiguration {
}
