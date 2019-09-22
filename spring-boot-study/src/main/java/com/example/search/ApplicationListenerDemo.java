package com.example.search;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author long
 * @Date 2019/7/26 15:42
 * Spring-Boot中的事件机制
 */
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        // 基于注解驱动的上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册监听器
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            /**
             * 监听器得到事件
             * @param event
             */
            @Override
            public void onApplicationEvent(MyApplicationEvent event) {
                System.out.println("接收到事件：" + event.getSource() +" @ "+event.getApplicationContext());
            }
        });
        context.refresh();
        // 发布事件
        context.publishEvent(new MyApplicationEvent(context,"Hello,World"));
        context.publishEvent(new MyApplicationEvent(context,1));
        context.publishEvent(new MyApplicationEvent(context,new Integer(100)));
    }

    /**
     * 自定义事件
     */
    private static class MyApplicationEvent extends ApplicationEvent{
        private final ApplicationContext context;

        public MyApplicationEvent(ApplicationContext applicationContext, Object source) {
            super(source);
            this.context = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return context;
        }
    }

}
