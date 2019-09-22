package com.gupao.user.repository;

import com.gupao.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link User 用户} 仓储
 *
 * @Author long
 * @Date 2019/7/28 10:30
 */
@Repository
public class UserRepository {

    private ConcurrentHashMap<Long, User> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Collection<User> findAll() {
        return repository.values();
    }

    public boolean save(User user) {
        long id = idGenerator.getAndIncrement();
        user.setId(id);
        return repository.putIfAbsent(user.getId(), user) == null;
    }
}
