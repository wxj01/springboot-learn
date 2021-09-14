package com.wxj.springboot.jta.service;

import com.wxj.springboot.jta.pojo.User;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestJtaService.java
 * @Description TODO
 * @createTime 2021年09月14日 21:43:00
 */
public interface TestJtaService {

    void testInsertUser(User user);

    void testInsertUser2(User user);

}
