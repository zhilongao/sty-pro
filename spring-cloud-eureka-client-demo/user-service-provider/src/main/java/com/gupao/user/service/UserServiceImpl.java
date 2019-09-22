package com.gupao.user.service;

import com.gupao.user.domain.User;
import com.gupao.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * {@link UserService 用户服务} 提供者实现
 *
 * @Author long
 * @Date 2019/7/28 10:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }
}
