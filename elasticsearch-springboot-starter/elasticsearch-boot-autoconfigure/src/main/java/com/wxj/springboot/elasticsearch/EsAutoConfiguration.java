package com.wxj.springboot.elasticsearch;


import com.wxj.springboot.elasticsearch.service.EsSearchService;
import com.wxj.springboot.elasticsearch.service.impl.EsSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean
@EnableConfigurationProperties(EsProperties.class)
public class EsAutoConfiguration {

    @Autowired
    private EsProperties esProperties;

    @Bean
    @ConditionalOnMissingBean(name = "esService")
    public EsSearchService esService() {
        return new EsSearchServiceImpl();
    }
}
