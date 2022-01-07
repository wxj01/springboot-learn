package com.wxj.springboot.learn.annotation;

import com.wxj.springboot.learn.aware.EnableThrowableRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/1/7 0007 11:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(EnableThrowableRegistrar.class)
public @interface EnableThrowable {
    Class<?>[] targets() default {};
}
