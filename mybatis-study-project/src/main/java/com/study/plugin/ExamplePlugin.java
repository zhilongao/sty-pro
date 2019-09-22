package com.study.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @Author long
 * @Date 2019/4/5 16:42
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class ExamplePlugin implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept method");
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {

    }
}
