package com.study.annotation.component;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/***
 * 该注解派生自@Repository注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface StringRepository {
    /**
     * 属性方法名称必须与@Component#value()一致
     * @return
     */
    String value() default "";
}
