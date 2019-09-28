package com.study.annotation.parser;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @Author long
 * @Date 2019/9/22 16:16
 * 由 @Service和@Transactional注解组成的复合注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Transactional
public @interface TransactionalService {

    // 为@Service注解   @AliasFor-value 别名
    @AliasFor(attribute = "value", annotation = Service.class)
    String name() default "";

    // 为@Service注解 @AliasFor->value 别名
    @AliasFor(attribute = "value", annotation = Service.class)
    String value() default "";

    // 为@Transactional注解 @AliasFor-transactionManager 别名
    @AliasFor(attribute = "transactionManager", annotation = Transactional.class)
    String manager() default "txManager";
}
