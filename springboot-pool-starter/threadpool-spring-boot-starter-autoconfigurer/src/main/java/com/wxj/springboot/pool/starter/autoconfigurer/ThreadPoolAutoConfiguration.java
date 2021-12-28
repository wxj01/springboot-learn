package com.wxj.springboot.pool.starter.autoconfigurer;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/27 0027 14:43
 */
@EnableAsync //
@Configuration
@ConditionalOnProperty(
        prefix = "threa.pool",
        name = "isOpen",
        havingValue = "true",
        matchIfMissing = true
)
@EnableConfigurationProperties(ThreadPoolProperties.class)
@Slf4j
public class ThreadPoolAutoConfiguration {

    @Autowired
    private ThreadPoolProperties threadPoolProperties;

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 动态生成多个线程池
     * 这个方法返回Runnable只是一个幌子，最重要的是执行方法里面的代码
     * @return
     * @throws Exception
     */
    @Bean
    public Runnable dynamicConfiguration(){
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) this.applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        List<ThreadPoolEntity> poolEntityList = threadPoolProperties.getList();
        poolEntityList.forEach((poolEntity) ->{
            //开始注册异步线程池
            log.info("Start the registration of asynchronous thread pool===>"+ JSONUtil.toJsonStr(poolEntity));

            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(ThreadPoolTaskExecutor.class);
            beanDefinitionBuilder.addPropertyValue("corePoolSize",poolEntity.getCorePoolSize());
            beanDefinitionBuilder.addPropertyValue("maxPoolSize",poolEntity.getMaxPoolSize());
            beanDefinitionBuilder.addPropertyValue("queueCapacity",poolEntity.getQueueCapacity());
            beanDefinitionBuilder.addPropertyValue("keepAliveSeconds",poolEntity.getKeepAliveSeconds());
            beanDefinitionBuilder.addPropertyValue("threadNamePrefix",poolEntity.getThreadName());
            //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
            beanDefinitionBuilder.addPropertyValue("waitForTasksToCompleteOnShutdown",true);
            beanDefinitionBuilder.addPropertyValue("awaitTerminationSeconds",poolEntity.getAwaitTerminationSeconds());
            /** 程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
             *  该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
             */
            beanDefinitionBuilder.addPropertyValue("rejectedExecutionHandler",new ThreadPoolExecutor.CallerRunsPolicy());
            //  注册到spring容器中
            beanFactory.registerBeanDefinition(poolEntity.getThreadName(),beanDefinitionBuilder.getBeanDefinition());

            log.info("End the registration of asynchronous thread pool===>"+poolEntity.getThreadName());
        });
        return null;
    }
}