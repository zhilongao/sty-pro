package com.gupao.springcloudhystrixclientdemo.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/7/29 18:17
 */
@RestController
public class HystrixDemoController {


    private final Random random = new Random();
    /*****************采用的第一种方式************************/
    @GetMapping("/hello-world")
    @HystrixCommand(
        fallbackMethod = "errorContent",
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")

        }
    )
    public String hellWorld() throws Exception{
        int value = random.nextInt(200);
        System.out.println("execute program cost time " + value);
        TimeUnit.MILLISECONDS.sleep(value);
        return "hello,world";
    }

    public String errorContent() {
        return "error content!";
    }

    /*****************采用的第二种方式-编程式************************/
    @GetMapping("hello-world-2")
    public String helloWorld2() {
        return new HelloWorldCommand().execute();
    }

    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String> {
        protected HelloWorldCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),
                    100);

        }

        @Override
        protected String run() throws Exception {
            // 如果随机时间 大于 100 ，那么触发容错
            int value = random.nextInt(200);
            System.out.println("helloWorld() costs " + value + " ms.");
            Thread.sleep(value);
            return "Hello,World";
        }

        /**
         * 容错执行
         */
        @Override
        protected String getFallback() {
            return HystrixDemoController.this.errorContent();
        }
    }
}
