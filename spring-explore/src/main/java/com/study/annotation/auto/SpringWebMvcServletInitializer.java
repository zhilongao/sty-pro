package com.study.annotation.auto;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @Author long
 * @Date 2019/9/29 14:32
 */
public class SpringWebMvcServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // DispatcherServlet 配置 bean
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return of(SpringWebMvcConfiguration.class);
    }

    // DispatcherServlet URL Pattern映射
    @Override
    protected String[] getServletMappings() {
        return of("/*");
    }

    private static <T> T[] of (T... values) {
        return values;
    }
}
