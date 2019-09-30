package com.study.annotation.parser;

import com.study.annotation.parser.bean.TransactionalServiceBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.SimpleTransactionStatus;

import java.util.Map;

/**
 * @Author long
 * @Date 2019/9/28 16:05
 * 实现引导类, 关于注解属性的覆盖
 *
 */
@Configuration
@ComponentScan(basePackageClasses = TransactionalServiceBean.class) // 扫描包路径
@EnableTransactionManagement // 激活事务
public class AttributeCoverSearch {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AttributeCoverSearch.class);
        Map<String, TransactionalServiceBean> beansMap = context.getBeansOfType(TransactionalServiceBean.class);
        beansMap.forEach((beanName, bean) -> {
            System.out.printf("Bean 名称 : %s, 对象 : %s\n", beanName, bean);
            bean.save();
        });
        context.close();
    }

    // 配置第一个事务管理器
    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager: 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
                System.out.println("txManager: 事务回滚...");
            }
        };
    }
    // 配置第二个事务管理器
    @Bean("txManager2")
    public PlatformTransactionManager txManager2() {
        return new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                return new SimpleTransactionStatus();
            }

            @Override
            public void commit(TransactionStatus status) throws TransactionException {
                System.out.println("txManager2: 事务提交...");
            }

            @Override
            public void rollback(TransactionStatus status) throws TransactionException {
                System.out.println("txManager2: 事务回滚...");
            }
        };
    }
}
