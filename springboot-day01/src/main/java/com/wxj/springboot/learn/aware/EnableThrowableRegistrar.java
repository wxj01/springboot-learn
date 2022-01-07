package com.wxj.springboot.learn.aware;

import com.wxj.springboot.learn.annotation.EnableThrowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/1/7 0007 11:03
 */
@Slf4j
public class EnableThrowableRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("JAVA_HOME："+ environment.getProperty("JAVA_HOME"));
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        log.info("开始手动注册");
        Map<String, Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(EnableThrowable.class.getCanonicalName());

        Class<?>[] targets = (Class<?>[]) annotationAttributes.get("targets");
        if(null != targets && targets.length > 0){
            Stream.of(targets).forEach(target -> {
                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(target)
                        .getBeanDefinition();
                registry.registerBeanDefinition(beanDefinition.getBeanClassName(),beanDefinition);
            });


        }
    }
}