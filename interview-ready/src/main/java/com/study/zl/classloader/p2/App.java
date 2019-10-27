package com.study.zl.classloader.p2;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @Author long
 * @Date 2019/10/13 14:26
 */
public class App {
    public static void main(String[] args) {
        Class<App> appClass = App.class;
        URL resource1 = appClass.getResource("/application.properties");
        URL resource2 = appClass.getClassLoader().getResource("application.properties");
        System.out.println(resource1.toString());
        System.out.println(resource2.toString());
        Properties properties = new Properties();
        try {
            Enumeration<URL> urls = appClass.getClassLoader().getResources("application.properties");
            while (urls.hasMoreElements()) {
                properties.putAll(PropertiesLoaderUtils.loadProperties(new UrlResource(urls.nextElement())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties);
    }
}
