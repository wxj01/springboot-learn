package com.wxj.springboot.learn;

import com.wxj.springboot.learn.service.ConcreteService;
import com.wxj.springboot.specificfeature.config.CustomerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
@RibbonClient(name = "abc",configuration = CustomerConfig.class)
public class SpringbootDay01Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootDay01Application.class, args);
        context.getBean(ConcreteService.class).sayHello();
    }

}
