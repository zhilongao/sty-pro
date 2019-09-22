package com.gupao.spring.cloud.feign.client.web.controller;

import com.gupao.spring.cloud.feign.api.domain.Person;
import com.gupao.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @Author long
 * @Date 2019/8/1 17:15
 * 对外提供服务
 */
@RestController
public class PersonClientController implements PersonService {

    @Autowired
    private PersonService personService;

    @Override
    public boolean save(@RequestBody Person person) {
        return personService.save(person);
    }

    @Override
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
