package com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 20:50
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "customerEntityManagerFactory",
        transactionManagerRef = "customerTransactionManager",
        basePackages = {"com.wxj.springboot.multi.jap.datasource.multi.jpa.datasource.customer.dao"})
public class CustomerConfig {

    @Primary
    @Bean(name = "customerDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.customer")
    public DataSource customerDataSource(){
        return DataSourceBuilder.create().build();
    }

//    @Primary
//    @Bean(name = "customerEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
//            EntityManagerFactoryBuilder builder, @Qualifier("customerDataSource") DataSource dataSource
//    ){
//
//    }
}
