package com.common.util.convert;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author long
 * @Date 2019/11/2 9:50
 * 自定义媒体类型转换类，支持 text/javascript类型
 */
public class SelfMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public SelfMappingJackson2HttpMessageConverter() {
        List<MediaType> mediaTypeList = new ArrayList<>();
        // 添加test/javascript支持，自动转换为json
        mediaTypeList.add(new MediaType("text", "javascript"));
        setSupportedMediaTypes(mediaTypeList);
    }
}
