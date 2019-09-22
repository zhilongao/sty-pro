package com.gupao.spring.cloud.feign.person.service.provider.web.controller;

import com.gupao.spring.cloud.feign.api.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author long
 * @Date 2019/8/1 17:03
 * 服务端对外提供服务
 */
@RestController
public class PersonServiceProviderController {

    /**
     * 数据仓库,存储数据
     */
    private final Map<Long, Person> persons = new ConcurrentHashMap<>();

    /**
     * 保存person
     * @param person
     * @return
     */
    @PostMapping(value = "/person/save")
    public boolean savePerson(@RequestBody Person person) {
        return persons.put(person.getId(), person) == null;
    }

    /**
     * 获取person列表
     * @return
     */
    @GetMapping(value = "/person/find/all")
    public Collection<Person> findAllPersons() {
        return persons.values();
    }
}
