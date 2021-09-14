package com.wxj.springboot.jta.dbAop;

import java.lang.annotation.*;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DataSource.java
 * @Description TODO
 * @createTime 2021年09月14日 21:03:00
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    String value() default DataSourceNames.ONE;
}
