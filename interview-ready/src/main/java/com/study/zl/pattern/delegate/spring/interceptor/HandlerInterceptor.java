package com.study.zl.pattern.delegate.spring.interceptor;

import com.study.zl.pattern.delegate.spring.HttpRequest;
import com.study.zl.pattern.delegate.spring.HttpResponse;

/**
 * @Author long
 * @Date 2019/3/9 9:48
 * Handler的过滤接口
 */
public interface HandlerInterceptor {

    boolean preHandle(HttpRequest request, HttpResponse response, Object handler) throws Exception;

    void postHandler(HttpRequest request, HttpResponse response, Object handler) throws Exception;

    void afterCompletion(HttpRequest request, HttpResponse response, Object handler) throws Exception;
}
