package com.wxj.springboot.webflux.dao;

import com.wxj.springboot.webflux.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserRepository.java
 * @Description TODO
 * @createTime 2021年09月16日 22:53:00
 */
@Repository
public class UserRepository {

    private ConcurrentHashMap<Long,User> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long save(User user){
        Long id = idGenerator.incrementAndGet();
        user.setId(id);
        repository.put(id,user);
        return  id;
    }

    public Collection<User> findAll() {
        return repository.values();
    }

    public User findUserById(Long id) {
        return repository.get(id);
    }

    public Long updateUser(User user) {
        repository.put(user.getId(), user);
        return user.getId();
    }

    public Long deleteUser(Long id) {
        repository.remove(id);
        return id;
    }
}
