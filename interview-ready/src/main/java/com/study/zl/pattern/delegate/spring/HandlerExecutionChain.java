package com.study.zl.pattern.delegate.spring;


import com.study.zl.pattern.delegate.spring.interceptor.HandlerInterceptor;

/**
 * @Author long
 * @Date 2019/3/9 9:34
 * DispatcherServlet对Http请求的第一个响应
 */
public class HandlerExecutionChain {
    //handler
    private final Object handler;
    //注册到这里的拦截器
    private HandlerInterceptor[] interceptors;

    public HandlerExecutionChain(Object handler){
        this(handler, null);
    }

    public HandlerExecutionChain(Object handler, HandlerInterceptor[] interceptors){
        this.handler = handler;
        this.interceptors = interceptors;
    }
}
