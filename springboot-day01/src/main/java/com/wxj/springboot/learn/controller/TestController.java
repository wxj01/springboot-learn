package com.wxj.springboot.learn.controller;

import com.wxj.springboot.learn.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {


    @Resource
    private TestService testService;

    @GetMapping("testService")
    public String test() {
        testService.test1();
        return "bbb";
    }
}
