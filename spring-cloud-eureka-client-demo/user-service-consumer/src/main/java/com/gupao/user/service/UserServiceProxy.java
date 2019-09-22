package com.gupao.user.service;

import com.gupao.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

/**
 * {@link UserService} Proxy实现
 *
 * @Author long
 * @Date 2019/7/28 10:55
 */
@Service
public class UserServiceProxy implements UserService {

    /**
     * 所调用服务的应用名称
     */
    private static final String PROVIDER_SERVER_URL_PREFIX = "http://user-service-provider";

    /**
     * 通过Rest api代理到服务提供者
     */
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public boolean save(User user) {
        User returnValue =
                restTemplate.postForObject(PROVIDER_SERVER_URL_PREFIX + "/user/save", user, User.class);
        return returnValue != null;
    }

    @Override
    public Collection<User> findAll() {
        return restTemplate.getForObject(PROVIDER_SERVER_URL_PREFIX + "/user/list", Collection.class);
    }
}
