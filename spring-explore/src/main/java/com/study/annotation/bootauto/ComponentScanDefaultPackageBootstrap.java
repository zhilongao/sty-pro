package com.study.annotation.bootauto;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

/**
 * @Author long
 * @Date 2019/10/13 9:27
 */
// @ComponentScan(basePackages = "")
// @ComponentScan(basePackageClasses = ComponentScanDefaultPackageBootstrap.class )
public class ComponentScanDefaultPackageBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanDefaultPackageBootstrap.class);
        System.out.println("当前spring上下文中所有注册的Bean名称:");
        Stream.of(context.getBeanDefinitionNames())
                .map(name -> "\t" + name)
                .forEach(System.out::println);
        context.close();
    }
}
