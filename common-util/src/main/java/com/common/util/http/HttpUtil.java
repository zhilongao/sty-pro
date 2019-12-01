package com.common.util.http;

import com.common.util.convert.SelfMappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author long
 * @Date 2019/11/2 9:17
 */
public class HttpUtil {

    /*public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("query","银行");
        map.put("location", "39.915,116.404");
        map.put("radius", "2000");
        map.put("output", "json");
        map.put("ak", "ikwPHuyWxMw1PpQFzN5a6MqsSLdsKNbA");
        String url = "http://api.map.baidu.com/place/v2/search";
        AddressSearchResultDTO resultDTO = new AddressSearchResultDTO();
        Object o = get(url, map, resultDTO);
        AddressSearchResultDTO o1 = (AddressSearchResultDTO) o;
        System.out.println(o1);
    }*/

    /**
     * RestTemplate发送get请求
     * @param url
     * @param params
     * @param response
     * @return
     */
    public static Object get(String url, Map<String, Object> params, Object response) {
        String reqUrl = paramsAppend(url, params);
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        messageConverters.add(new SelfMappingJackson2HttpMessageConverter());
        Object forObject = restTemplate.getForObject(reqUrl, response.getClass());
        return forObject;
    }

    /**
     * url参数拼接
     * @param url
     * @param params
     * @return
     */
    private static String paramsAppend(String url, Map<String, Object> params) {
        StringBuffer buf = new StringBuffer();
        if (!CollectionUtils.isEmpty(params)) {
            Set<String> keys = params.keySet();
            int index = 0;
            int size = keys.size();
            for (String key : keys) {
                if (index != size - 1) {
                    buf.append(key + "=" + params.get(key) + "&");
                } else {
                    buf.append(key + "=" + params.get(key));
                }
                index ++;
            }
        }
        String reqUrl = url + "?" + buf.toString();
        return reqUrl;
    }
}
