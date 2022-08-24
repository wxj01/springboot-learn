package com.wxj.springboot.specificfeature;

import com.wxj.springboot.specificfeature.config.WebMvcConfig;
import com.wxj.springboot.specificfeature.interceptor.GlobalInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@ConditionalOnProperty(value = "specific.branch.enabled", matchIfMissing = true)
@EnableConfigurationProperties(SpecificFeatureProperties.class)
public class SpecificFeatureAutoConfiguration {

    @Autowired
    private SpecificFeatureProperties specificFeatureProperties;

    @Bean
    @ConditionalOnExpression("'${specific.branch.rule}' != null and '${specific.branch.rule}' != '' ")
    public GlobalInterceptor globalInterceptor() {
        System.out.println("aaa");
        return new GlobalInterceptor(specificFeatureProperties.getName(), specificFeatureProperties.getRule());
    }

    @Bean
    @DependsOn("globalInterceptor")
    @ConditionalOnBean(GlobalInterceptor.class)
    public WebMvcConfig webMvcConfig() {
        System.out.println("bbb");
        return new WebMvcConfig();
    }
}
