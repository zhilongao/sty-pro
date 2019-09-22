package com.gupao.service.mock;

import com.gupao.study.order.domain.Order;
import com.gupao.study.order.service.OrderService;

import java.util.Collection;

/**
 * @Author long
 * @Date 2019/8/7 14:18
 */
public class OrderServiceImplMock implements OrderService {
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
        return "系统繁忙,请稍后重试!";
    }
}
