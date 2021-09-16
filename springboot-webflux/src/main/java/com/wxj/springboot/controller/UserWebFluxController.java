package com.wxj.springboot.controller;

import com.wxj.springboot.webflux.handler.UserHandler;
import com.wxj.springboot.webflux.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserWebFluxController.java
 * @Description TODO
 * @createTime 2021年09月16日 23:03:00
 */
@RestController
@RequestMapping(value = "/user")
public class UserWebFluxController {

    @Autowired
    private UserHandler userHandler;

    /**
     * 根据ID查询某个User
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Mono<User> findUserById(@PathVariable("id") Long id) {
        return userHandler.findUserById(id);
    }

    /**
     * 查找所有User
     * @return
     */
    @GetMapping()
    public Flux<User> findAllUser() {
        return userHandler.findAllUser();
    }

    /**
     * 插入User
     * @param user
     * @return
     */
    @PostMapping()
    public Mono<Long> saveUser(@RequestBody User user) {
        return userHandler.save(user);
    }

    /**
     * 修改User
     * @param user
     * @return
     */
    @PutMapping()
    public Mono<Long> modifyUser(@RequestBody User user) {
        return userHandler.modifyUser(user);
    }

    /**
     * 根据ID删除User
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteUser(@PathVariable("id") Long id) {
        return userHandler.deleteUser(id);
    }
}
