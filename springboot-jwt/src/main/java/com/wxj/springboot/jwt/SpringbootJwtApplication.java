package com.wxj.springboot.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SpringbootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJwtApplication.class, args);
    }

}
