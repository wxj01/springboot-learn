package com.wxj.springboot.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName ElasticSearchRestClientConfig.java
 * @Description TODO
 * @createTime 2021年09月18日 21:24:00
 */
@Configuration
public class ElasticSearchRestClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.15.129:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
