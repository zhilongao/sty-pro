package com.example.config;

import com.example.http.message.PropertiesPersonHttpMessageConvert;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author long
 * @Date 2019/7/26 11:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 将消息转换器放到第一个,默认会转换为xml
        converters.set(0, new MappingJackson2XmlHttpMessageConverter());
        // 自定义消息转换器
        converters.set(1, new PropertiesPersonHttpMessageConvert());
    }
}
