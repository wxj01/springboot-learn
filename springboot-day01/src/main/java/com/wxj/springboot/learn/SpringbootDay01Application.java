package com.wxj.springboot.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringbootDay01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDay01Application.class, args);
//        context.getBean(ConcreteService.class).sayHello();
    }

}
