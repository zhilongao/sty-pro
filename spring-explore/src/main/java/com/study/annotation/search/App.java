package com.study.annotation.search;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Annotated;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author long
 * @Date 2019/9/22 15:25
 */
public class App {

    public static void main(String[] args) {
        // getMetadataByJdk();
        getMetadataByJdkWithSpring();
    }


    public static void reBuildCode() {
        AnnotatedElement annotatedElement = JdkService.class;
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);
        Set<Annotation> allMetaAnnotations = getAllMetaAnnotations(transactionalService);

        allMetaAnnotations.forEach(App::printAnnotationAttribute);
    }

    public static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {
        // 获取所有注解
        Annotation[] annotations = annotation.annotationType().getAnnotations();
        if (ObjectUtils.isEmpty(annotations)) {
            return Collections.emptySet();
        }
        Set<Annotation> metaAnnotationSet =
                Stream.of(annotations).filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage())).collect(Collectors.toSet());
        // 递归查找元注解的元注解集合
        Set<Annotation> metaMetaAnnotationSet = metaAnnotationSet.stream().map(App::getAllMetaAnnotations).collect(HashSet::new, Set::addAll, Set::addAll);
        // 添加递归结果
        metaAnnotationSet.addAll(metaMetaAnnotationSet);
        return metaAnnotationSet;
    }

    public static void printAnnotationAttribute(Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        ReflectionUtils.doWithMethods(
                annotationType,
                method -> System.out.printf("@%s.%s() = %s\n", annotationType.getSimpleName(), method.getName(), ReflectionUtils.invokeMethod(method, annotation)),
                method -> method.getParameterCount() == 0);
    }




    public static void getMetadataByJdk() {
        // 由于 Class 类实现了 AnnotatedElement接口
        AnnotatedElement annotatedElement = JdkService.class;
        // 从 AnnotatedElement中获取@TransactionalService注解
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);
        // 调用该注解的方法 TransactionalService#name()
        String name = transactionalService.name();
        System.out.println("TransactionalService.name() = " + name);
    }

    public static void getMetadataByJdkWithSpring() {
        // 由于 Class 类实现了 AnnotatedElement接口
        AnnotatedElement annotatedElement = JdkService.class;
        // 从 AnnotatedElement中获取@TransactionalService注解
        TransactionalService transactionalService = annotatedElement.getAnnotation(TransactionalService.class);

        /*ReflectionUtils.doWithMethods(
                TransactionalService.class,
                method -> System.out.printf("@TransactionalService.%s() = %s\n", method.getName(), ReflectionUtils.invokeMethod(method, transactionalService)),
                // 选择无参方法
                method -> method.getParameterCount() == 0
        );*/

        // 排除父类的方法
        ReflectionUtils.doWithMethods(
            TransactionalService.class,
            method -> System.out.printf("@TransactionalService.%s() = %s\n", method.getName(), ReflectionUtils.invokeMethod(method, transactionalService)),
            // 选择无参方法
            method -> !method.getDeclaringClass().equals(Annotation.class)
        );
    }


    /**
     * spring
     */
    public static void getMetadataBySpring() {
        String className = LoanService.class.getName();
        // 创建 MetadataReaderFactory对象
        MetadataReaderFactory factory = new CachingMetadataReaderFactory();
        try {
            // 构建MetadataReader对象
            MetadataReader metadataReader = factory.getMetadataReader(className);
            // 获取 AnnotationMetadata信息
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            // 遍历AnnotationMetadata信息
            annotationMetadata.getAnnotationTypes().forEach(annotationType -> {
                // 获取注解 annotationType注解的注解
                Set<String> metaAnnotationTypes = annotationMetadata.getMetaAnnotationTypes(annotationType);
                // 将这些注解循环输出
                metaAnnotationTypes.forEach(metaAnnotationType -> {
                    System.out.printf("注解 @%s 元标注 @%s\n", annotationType, metaAnnotationType
                    );
                });
                System.out.println("-----------------------");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
