package com.study.annotation.parser;

import com.study.annotation.parser.bean.TransactionalServiceBean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author long
 * @Date 2019/9/28 14:30
 */
public class AnnotationParserUtil {

    public static void main(String[] args) {
        // parserAttribute();
        // parserAttributeByUtil();
        // loopParser();
        parserBySpring();
        // parseAttributes();
        // parserAttributesByCls();
    }




    /************************理解AnnotationAttributes******************************/
    public static void parserAttributesByCls() {
        AnnotatedElement annotatedElement = TransactionalServiceBean.class;
        AnnotationAttributes serviceAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);
        AnnotationAttributes transactionalAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);
        // 输出
        printAnnotationAttr(serviceAttributes);
        System.out.println("================");
        printAnnotationAttr(transactionalAttributes);
    }


    public static void parseAttributes() {
        AnnotatedElement annotatedElement = TransactionalService.class;
        // 获取@Service注解属性独享
        AnnotationAttributes serviceAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Service.class);
        // 获取@Transactional注解属性独享
        AnnotationAttributes transactionalAttributes = AnnotatedElementUtils.getMergedAnnotationAttributes(annotatedElement, Transactional.class);
        // 输出
        printAnnotationAttr(serviceAttributes);
        System.out.println("=============================");
        printAnnotationAttr(transactionalAttributes);
    }

    public static void printAnnotationAttr(AnnotationAttributes annotationAttributes) {
        System.out.printf("注解 @%s 属性集合：\n", annotationAttributes.annotationType().getName());
        annotationAttributes.forEach((name, value)->{
            System.out.printf("\t属性 %s : %s \n", name, value);
        });
    }




    /***********************************获取某个指定类的单个注解*********************************************/
    // 返回指定类的指定注解
    public static Annotation getAnnotation(Class original, Class annotationCls) {
        // jdk中 Class 类实现了AnnotatedElement接口
        AnnotatedElement annotatedElement = original;
        // 通过AnnotatedElement#getAnnotation(Class)方法返回指定类的注解对象Class
        Annotation annotation = annotatedElement.getAnnotation(annotationCls);
        return annotation;
    }

    // 调用@TransactionalService注解的name方法，获取其name属性值
    public static void parserAttribute() {
        // 获取指定类JdkService.class的@TransactionanlService注解对象
        TransactionalService transactionalService = (TransactionalService)getAnnotation(JdkService.class, TransactionalService.class);
        // 获取@TransactionalService注解的name()属性
        String name = transactionalService.name();
        System.out.println("name=" + name);
    }

    // 利用spring的工具类ReflectionUtils调用注解的方法
    public static void parserAttributeByUtil() {
        TransactionalService transactionalService = (TransactionalService)getAnnotation(JdkService.class, TransactionalService.class);
        // 非lambda写法
        /*
        ReflectionUtils.doWithMethods(
                TransactionalService.class,
                new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        System.out.printf("@TransactionalService.%s() = %s\n", method.getName(), ReflectionUtils.invokeMethod(method, transactionalService));
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        // return method.getParameterCount() == 0;
                        return !method.getDeclaringClass().equals(Annotation.class);
                    }
                }
        );
        */
        // lambda写法
        ReflectionUtils.doWithMethods(
                TransactionalService.class,
                method -> System.out.printf("@TransactionalService.%s() = %s\n", method.getName(), ReflectionUtils.invokeMethod(method, transactionalService)),
                // 遍历方法参数个数为0的方法
                // method -> method.getParameterCount() == 0
                // 不调用注解的父注解Annotation的方法
                method -> !method.getDeclaringClass().equals(Annotation.class)
        );
    }

    /**************************************循环获取注解的元注解*****************************************************/
    public static void loopParser() {
        TransactionalService transactionalService = (TransactionalService)getAnnotation(JdkService.class, TransactionalService.class);
        Set<Annotation> allMetaAnnotations = getAllMetaAnnotations(transactionalService);
        // 遍历这些注解的属性
        allMetaAnnotations.forEach(AnnotationParserUtil::printAnnotationAttribute);
    }

    // 获取某个注解的所有元注解
    public static Set<Annotation> getAllMetaAnnotations(Annotation annotation) {
        // 首先获取给定Annotation的类型(Class)，然后获取该类型上的所有元注解
        Annotation[] metaAnnotations = annotation.annotationType().getAnnotations();
        if  (ObjectUtils.isEmpty(metaAnnotations)) {
            return Collections.emptySet();
        }
        Set<Annotation> metaAnnotationSet = Stream.of(metaAnnotations)
                // 排除java标准注解 如@Target @Retention @Documented 等，他们相互依赖，将会导致递归不断
                // 通过 java.lang.annotation包名排除(排除掉所有与Target注解在同一个包下的注解)
                .filter(metaAnnotation -> !Target.class.getPackage().equals(metaAnnotation.annotationType().getPackage()))
                .collect(Collectors.toSet());
        // 递归查找元注解的元注解集合--> 循环遍历每一个获取到的Annotation-> 将其转化为Set<Annotation>返回
        Set<Annotation> metaMetaAnnotationsSet = metaAnnotationSet.stream().map(AnnotationParserUtil::getAllMetaAnnotations).collect(HashSet::new, Set::addAll, Set::addAll);
        // 添加递归结果
        metaAnnotationSet.addAll(metaMetaAnnotationsSet);
        return metaAnnotationSet;
    }


    public static void printAnnotationAttribute(Annotation annotation) {
        Class<? extends Annotation> annotationType = annotation.annotationType();
        // 完全Java反射实现(ReflectionUtils为Spring反射工具类)
        // 非lambda写法
        ReflectionUtils.doWithMethods(
                annotationType,
                new ReflectionUtils.MethodCallback() {
                    @Override
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        System.out.printf("@%s.%s() = %s\n", annotationType.getSimpleName(), method.getName(), ReflectionUtils.invokeMethod(method, annotation));
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    @Override
                    public boolean matches(Method method) {
                        return !method.getDeclaringClass().equals(Annotation.class);
                    }
                }
        );
        // java8 lambda写法
        /*ReflectionUtils.doWithMethods(
                annotationType,
                method -> System.out.printf("@%s.%s() = %s\n", annotationType.getSimpleName(), method.getName(), ReflectionUtils.invokeMethod(method, annotation)),
                // method -> method.getParameterCount() == 0
                method -> !method.getDeclaringClass().equals(Annotation.class)
        );*/
    }

    /************************************基于spring*****************************************/
    public static void parserBySpring() {
        // 标准的基于jdk实现的AnnotationMetadata-->StandardAnnotationMetadata
        AnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(JdkService.class);
        // 获取所有的元注解类型(全类名)集合 @Service @Transactional @Component
        Set<String> metaAnnotationTypes = annotationMetadata.getAnnotationTypes()
                .stream()
                // 读取单注解的元注解类型集合
                .map(annotationMetadata::getMetaAnnotationTypes)
                // 合并元注解类型(全类名)集合
                .collect(LinkedHashSet::new, Set::addAll, Set::addAll);

        metaAnnotationTypes.forEach(metaAnnotation -> {// 读取所有元注解类型
            // 读取元注解属性信息
            Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(metaAnnotation);
            if (!CollectionUtils.isEmpty(annotationAttributes)) {
                annotationAttributes.forEach((name, value) -> System.out.printf("注解@%s 属性 %s = %s\n", ClassUtils.getShortName(metaAnnotation), name, value));
            }
        });
    }

}
