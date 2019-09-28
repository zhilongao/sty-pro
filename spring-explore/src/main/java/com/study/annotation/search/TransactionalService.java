package com.study.annotation.search;

import org.springframework.stereotype.Component;
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
@Service(value = "transactionService")
@Transactional
public @interface TransactionalService {

    String name() default "";

    String transactionManager() default "txManager";

}
