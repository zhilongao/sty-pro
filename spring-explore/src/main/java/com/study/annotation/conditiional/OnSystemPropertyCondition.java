package com.study.annotation.conditiional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @Author long
 * @Date 2019/9/29 17:28
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取到@ConditionalOnSystemProperty注解的所有的属性和方法
        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(ConditionalOnSystemProperty.class.getName());

        String propertyName = (String)attributes.getFirst("name");
        String propertyValue = (String)attributes.getFirst("value");
        // 获取系统属性里面的该值
        String systemPropertyValue = System.getProperty(propertyName);
        // 比较系统属性里面的该值是否与给定的值相等
        if (Objects.equals(systemPropertyValue, propertyValue)) {
            System.out.printf("系统属性[名称:%s] 找到匹配值: %s\n", propertyName, propertyValue);
            return true;
        }
        return false;
    }
}
