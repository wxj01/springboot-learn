package com.wxj.springboot.redis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName RedissonLockAnnotation.java
 * @Description TODO
 * @createTime 2021年09月17日 19:59:00
 */
@Target(ElementType.METHOD) //注解在方法
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLockAnnotation {

    /**
     * 指定组成分布式锁的key
     */
    String lockRedisKey();
}
