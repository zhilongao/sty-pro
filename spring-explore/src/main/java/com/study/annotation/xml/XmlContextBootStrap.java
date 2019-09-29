package com.study.annotation.xml;


import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author long
 * @Date 2019/9/29 10:42
 */
public class XmlContextBootStrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(XMLConfiguration.class);
        // context.register(XMLConfiguration.class);
        // context.refresh();
        System.out.println("----");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName + ":" + context.getBean(definitionName));
        }
        System.out.println("----");
        Object bean = context.getBean(AnnotationConfigUtils.CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME);
        System.out.println(bean);
        context.close();
    }
}

@Configuration
@ImportResource("beans1.xml")
class XMLConfiguration {

}