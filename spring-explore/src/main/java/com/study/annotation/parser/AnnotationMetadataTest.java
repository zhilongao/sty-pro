package com.study.annotation.parser;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;

import java.io.IOException;

/**
 * @Author long
 * @Date 2019/9/30 17:01
 * 基于jdk和基于asm性能测试
 */
public class AnnotationMetadataTest {

    public static void main(String[] args) {
        try {
            performanceTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void performanceTest() throws IOException {
        // 反射实现
        AnnotationMetadata standardAnnotationMetadata = new StandardAnnotationMetadata(TransactionalService.class);

        // 获取  MetadataReaderFactory
        SimpleMetadataReaderFactory factory = new SimpleMetadataReaderFactory();
        // 获取 MetadataReader
        MetadataReader metadataReader = factory.getMetadataReader(TransactionalService.class.getName());
        // asm实现
        AnnotationMetadata visitorAnnotationMetadata = metadataReader.getAnnotationMetadata();
        // 10万次
        int times = 10 * 10000;
        test(standardAnnotationMetadata, times);
        test(visitorAnnotationMetadata, times);

        // 100万次
        times = 100 * 10000;
        test(standardAnnotationMetadata, times);
        test(visitorAnnotationMetadata, times);

        // 1000万次
        times = 1000 * 10000;
        test(standardAnnotationMetadata, times);
        test(visitorAnnotationMetadata, times);

        // 1亿次
        times = 10000 * 10000;
        test(standardAnnotationMetadata, times);
        test(visitorAnnotationMetadata, times);

    }

    private static void test(AnnotationMetadata annotationMetadata, int times) {
        long startTimes = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            annotationMetadata.getAnnotationTypes();
        }
        long costTime = System.currentTimeMillis() - startTimes;
        System.out.printf("%d 次 %s.getAnnotationTypes() 方法执行消耗 %s ms\n", times, annotationMetadata.getClass().getSimpleName(), costTime);
    }

}
