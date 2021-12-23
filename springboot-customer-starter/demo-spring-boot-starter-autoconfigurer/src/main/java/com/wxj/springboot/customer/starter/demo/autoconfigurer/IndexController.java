package com.wxj.springboot.customer.starter.demo.autoconfigurer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/23 0023 19:31
 */
@RestController
@AllArgsConstructor
public class IndexController {

    private CustomProperties customProperties;

    @RequestMapping("/")
    public String index(){
        return customProperties.getName();
    }
}