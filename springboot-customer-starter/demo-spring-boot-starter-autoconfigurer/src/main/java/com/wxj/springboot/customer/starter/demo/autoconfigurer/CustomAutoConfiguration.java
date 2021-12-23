package com.wxj.springboot.customer.starter.demo.autoconfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO 自动配置类
 * @date 2021/12/23 0023 19:37
 */
@Configuration
@ConditionalOnProperty(value = "auto.custom.name")
@EnableConfigurationProperties(CustomProperties.class)
public class CustomAutoConfiguration {

    @Autowired
    CustomProperties customProperties;

    @Bean
    public IndexController indexController(){
        return  new IndexController(customProperties);
    }
}