package com.example.filter;

import org.springframework.boot.autoconfigure.AutoConfigurationImportEvent;
import org.springframework.boot.autoconfigure.AutoConfigurationImportListener;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
import java.util.Set;

/**
 * @Author long
 * @Date 2019/8/21 11:24
 */
public class DefaultAutoConfigurationImportListener implements AutoConfigurationImportListener {

    @Override
    public void onAutoConfigurationImportEvent(AutoConfigurationImportEvent event) {
        // 获取当前ClassLoader
        ClassLoader classLoader = event.getClass().getClassLoader();
        // 获取自动装配class名单
        List<String>  candidates = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, classLoader);
        // 实际的自动装配class名单
        List<String> configurations = event.getCandidateConfigurations();
        // 排除的自动装配class名单
        Set<String> exclusions = event.getExclusions();
        // 输出各自的数量
        System.out.printf("自动装配Class名单-->候选数量%d,实际数量:%d,排除数量:%s\n", candidates.size(), configurations.size(), exclusions.size());
        // 输出实际和排除的自动装配class名单
        System.out.println("实际的自动装配class名单");
        event.getCandidateConfigurations().forEach(System.out::println);
        System.out.println("排除的自动装配class名单");
        event.getExclusions().forEach(System.out::println);
    }
}
