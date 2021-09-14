package com.wxj.springboot.jta.service.impl;

import com.wxj.springboot.jta.dbAop.DataSource;
import com.wxj.springboot.jta.dbAop.DataSourceNames;
import com.wxj.springboot.jta.mapper.UserMapper;
import com.wxj.springboot.jta.pojo.User;
import com.wxj.springboot.jta.service.TestJtaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName TestJtaServiceImpl.java
 * @Description TODO
 * @createTime 2021年09月14日 21:44:00
 */

@Service
public class TestJtaServiceImpl implements TestJtaService {

    @Resource
    UserMapper userMapper;

    public void testInsertUser(User user) {
        int insert = userMapper.insert(user);
        System.out.println("插入成功，条数："+insert);
    }

    @DataSource(DataSourceNames.TWO)
    public void testInsertUser2(User user) {
        int insert = userMapper.insert(user);
        System.out.println("插入成功，条数："+insert);
    }
}
