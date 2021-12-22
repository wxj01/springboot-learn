package com.wxj.springboot.multidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/21 0021 20:53
 */
@Configuration
// 指定从数据库扫描对应的Mapper文件，生成代理对象
@MapperScan(value = "com.wxj.springboot.multidatasource.mapper2",sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {
    //mapper.xml所在地址
    private static final String MAPPER_LOCATION = "classpath*:mapper2/*.xml";

    /**
     * @description: 数据源
     * @author wangxinjian
     * @date 2021/12/22 0022 9:25
     * @version 1.0
     */
    @Bean(name = "slaveDataSource")
    // 读取spring.datasource.slave前缀的配置文件映射成对应的配置对象
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dataSource(){
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    /**
     * @description: 事务管理
     * @author wangxinjian
     * @date 2021/12/22 0022 9:27
     * @version 1.0
     */
    @Bean(name = "slaveTranscationManager")
    public PlatformTransactionManager dataSourceTransactionManager(
            @Qualifier("slaveDataSource") DataSource dataSource){
            return new DataSourceTransactionManager(dataSource);
    }


    /**
     * @description: TODO session 工厂
     * @author wangxinjian
     * @date 2021/12/22 0022 9:29
     * @version 1.0
     */
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(
                SlaveDataSourceConfig.MAPPER_LOCATION
        ));

        return sqlSessionFactoryBean.getObject();
    }
}