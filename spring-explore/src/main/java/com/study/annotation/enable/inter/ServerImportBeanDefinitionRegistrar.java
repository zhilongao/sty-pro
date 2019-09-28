package com.study.annotation.enable.inter;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @Author long
 * @Date 2019/9/28 17:35
 */
public class ServerImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 可复用ServerImportSelector
        ImportSelector importSelector = new ServerImportSelector();
        // 调用 selectImports筛选
        String[] selectedClassNames = importSelector.selectImports(importingClassMetadata);
        // 创建 bean 定义
        Stream.of(selectedClassNames)
                // 转化为 BeanDefinitionBuilder对象
                .map(BeanDefinitionBuilder :: genericBeanDefinition)
                // 转化为 BeanDefinition对象
                .map(BeanDefinitionBuilder::getBeanDefinition)
                .forEach(beanDefinition -> {
                    // 注册BeanDefinition到BeanDefinitionRegistry
                    BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition, registry);
                });
    }
}
