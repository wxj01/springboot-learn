package com.wxj.springboot.webflux.handler;

import com.wxj.springboot.webflux.dao.UserRepository;
import com.wxj.springboot.webflux.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserHandler.java
 * @Description TODO
 * @createTime 2021年09月16日 22:58:00
 */
@Component
public class UserHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<Long> save(User user) {
        return Mono.create(userMonoSink -> userMonoSink.success(userRepository.save(user)));
    }

    public Mono<User> findUserById(Long id) {
        return Mono.justOrEmpty(userRepository.findUserById(id));
    }

    public Flux<User> findAllUser() {
        return Flux.fromIterable(userRepository.findAll());
    }

    public Mono<Long> modifyUser(User user) {
        return Mono.create(userMonoSink -> userMonoSink.success(userRepository.updateUser(user)));
    }

    public Mono<Long> deleteUser(Long id) {
        return Mono.create(userMonoSink -> userMonoSink.success(userRepository.deleteUser(id)));
    }



}
