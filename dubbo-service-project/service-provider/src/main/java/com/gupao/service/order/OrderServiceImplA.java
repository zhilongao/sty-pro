package com.gupao.service.order;

import com.gupao.study.order.domain.Order;
import com.gupao.study.order.service.OrderService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author long
 * @Date 2019/8/5 7:38
 */
public class OrderServiceImplA implements OrderService {

    private static final Map<Long, Order> orderRepository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerate = new AtomicLong(0);


    public String orderUser;
    public String orderNum;
    private String orderN;



    @Override
    public boolean save(Order order) {
        return orderRepository.put(idGenerate.getAndIncrement(), order) == null;
    }

    @Override
    public Collection<Order> findAll() {
        return orderRepository.values();
    }

    @Override
    public String saveHello(String name) {
        System.out.println("execute say hello operate");
        return "hello," + name;
    }


    public String callBackMethod() {
        System.out.println("dubbo-回调函数");
        return "dubbo-method 回调";
    }
}
