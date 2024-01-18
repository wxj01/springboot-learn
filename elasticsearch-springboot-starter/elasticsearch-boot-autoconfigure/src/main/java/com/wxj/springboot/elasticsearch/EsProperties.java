package com.wxj.springboot.elasticsearch;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("es")
public class EsProperties {

    private String name;
    
}
