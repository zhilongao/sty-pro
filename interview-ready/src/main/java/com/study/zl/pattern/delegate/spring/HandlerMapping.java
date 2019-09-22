package com.study.zl.pattern.delegate.spring;

/**
 * @Author long
 * @Date 2019/3/9 8:28
 * HandlerMapping接口
 */
public interface HandlerMapping {
    // 通过一个HttpRequest获取一个HandlerExecutionChain
    HandlerExecutionChain getHandler(HttpRequest request) throws Exception;
}
