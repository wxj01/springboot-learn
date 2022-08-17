package com.wxj.springboot.specificfeature;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("specific.branch")
@Data
public class SpecificFeatureProperties {
    private String name;
}
