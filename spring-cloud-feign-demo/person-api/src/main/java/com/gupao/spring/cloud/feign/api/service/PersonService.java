package com.gupao.spring.cloud.feign.api.service;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.hystrix.PersonServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

/**
 * @Author long
 * @Date 2019/8/1 16:55
 * api 提供person-service的服务,熔断处理类为PersonServiceFallback,该类继承了PersonService类
 */
@FeignClient(value = "person-service", fallback = PersonServiceFallback.class)
public interface PersonService {

    /**
     * 保存person
     * @param person
     * @return
     */
    @PostMapping("/person/save")
    boolean save(@RequestBody Person person);

    /**
     * 获取全部
     * @return
     */
    @GetMapping("/person/find/all")
    Collection<Person> findAll();
}
