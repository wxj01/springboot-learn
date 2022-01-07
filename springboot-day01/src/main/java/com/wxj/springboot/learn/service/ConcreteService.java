package com.wxj.springboot.learn.service;


import lombok.extern.slf4j.Slf4j;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/1/7 0007 11:19
 */
@Slf4j
public class ConcreteService {

    public void sayHello(){
        log.info("<*> ~~~~~ ConcreteService say hello!");
    }
}