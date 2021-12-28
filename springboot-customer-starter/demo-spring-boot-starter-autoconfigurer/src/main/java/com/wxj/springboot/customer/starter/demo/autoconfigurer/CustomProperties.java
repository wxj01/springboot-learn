package com.wxj.springboot.customer.starter.demo.autoconfigurer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO 属性配置文件
 * @date 2021/12/23 0023 19:30
 */
@ConfigurationProperties("auto.custom")
@Data
public class CustomProperties {
    private String name;

}