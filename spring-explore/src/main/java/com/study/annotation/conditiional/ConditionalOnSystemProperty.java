package com.study.annotation.conditiional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @Author long
 * @Date 2019/9/29 17:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionalOnSystemProperty {

    String name();

    String value();
}
