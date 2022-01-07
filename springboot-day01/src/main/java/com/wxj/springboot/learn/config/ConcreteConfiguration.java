package com.wxj.springboot.learn.config;

import com.wxj.springboot.learn.annotation.EnableThrowable;
import com.wxj.springboot.learn.service.ConcreteService;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/1/7 0007 11:18
 */
@Configuration
@EnableThrowable(targets = {ConcreteService.class})
public class ConcreteConfiguration {
}