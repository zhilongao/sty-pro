package com.gupao.study.order.service;

import com.gupao.study.order.domain.Order;

import java.util.Collection;

/**
 * @Author long
 * @Date 2019/8/5 7:36
 */
public interface OrderService {

    boolean save(Order order);

    Collection<Order> findAll();

    String saveHello(String name);

}
