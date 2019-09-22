package com.gupao.spring.cloud.feign.api.hystrix;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.service.PersonService;

import java.util.Collection;
import java.util.Collections;

/**
 * @Author long
 * @Date 2019/8/1 22:38
 * PersonService服务熔断处理类
 */
public class PersonServiceFallback implements PersonService {
    @Override
    public boolean save(Person person) {
        return false;
    }

    @Override
    public Collection<Person> findAll() {
        return Collections.emptyList();
    }
}
