package com.study.zl.pattern.delegate.spring;

/**
 * @Author long
 * @Date 2019/3/9 10:00
 */
public interface HandlerAdapter {

    boolean supports(Object handler);

    //该方法用以返回经由handler处理封装好的ModelAndView
    ModelAndView handle(HttpRequest request, HttpResponse response, Object handler) throws Exception;

    long getLastModified(HttpRequest request, Object handle);
}
