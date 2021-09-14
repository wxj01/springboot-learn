package com.wxj.springboot.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootJtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJtaApplication.class, args);
    }

}
