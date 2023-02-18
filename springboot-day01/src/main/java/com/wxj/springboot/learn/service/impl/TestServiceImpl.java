package com.wxj.springboot.learn.service.impl;

import com.wxj.springboot.learn.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestService testService;

    @Override
    public void test1() {
        System.out.println(this.getClass());
        System.out.println(testService.getClass());
    }

    @Override
    public void test2() {
        System.out.println("aaa");
    }
}
