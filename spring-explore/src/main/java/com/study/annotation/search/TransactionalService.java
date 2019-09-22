package com.study.annotation.search;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * @Author long
 * @Date 2019/9/22 16:16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Transactional
public @interface TransactionalService {
    String name() default "";
}
