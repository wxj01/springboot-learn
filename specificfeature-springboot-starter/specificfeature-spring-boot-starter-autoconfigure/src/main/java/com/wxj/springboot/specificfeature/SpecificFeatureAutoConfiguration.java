package com.wxj.springboot.specificfeature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "specific.branch")
@EnableConfigurationProperties(SpecificFeatureProperties.class)
public class SpecificFeatureAutoConfiguration {

    @Autowired
    private SpecificFeatureProperties specificFeatureProperties;


}
