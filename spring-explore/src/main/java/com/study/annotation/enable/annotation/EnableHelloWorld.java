package com.study.annotation.enable.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author long
 * @Date 2019/9/28 17:07
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {


}
