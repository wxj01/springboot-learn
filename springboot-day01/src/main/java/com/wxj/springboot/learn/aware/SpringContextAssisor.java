package com.wxj.springboot.learn.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO 实行 ApplicationContextAware
 * @date 2022/1/7 0007 10:40
 */
@Component
public class SpringContextAssisor implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextAssisor.applicationContext = applicationContext;
    }

    public static Object getBeanDefinition(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBeanDefinition(String name,Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }
}