package com.gupao.service.order;

import com.gupao.study.order.domain.Order;
import com.gupao.study.order.service.OrderService;

import java.util.Collection;

/**
 * @Author long
 * @Date 2019/8/6 14:25
 */
public class OrderServiceImplB implements OrderService {
    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public Collection<Order> findAll() {
        return null;
    }

    @Override
    public String saveHello(String name) {
        return "版本B";
    }
}
