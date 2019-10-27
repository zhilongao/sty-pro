package com.study.annotation.enable.inter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author long
 * @Date 2019/9/28 17:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerImportSelector.class)
// @Import(ServerImportBeanDefinitionRegistrar.class)
public @interface EnableServer {

    /**
     * 设置服务器的类型
     * @return
     */
    Server.Type type();
}
