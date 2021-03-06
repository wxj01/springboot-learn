package com.wxj.springboot.jta.dbAop;

import com.wxj.springboot.jta.dbconfig.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DynamicDataSourceAspect.java
 * @Description TODO
 * @createTime 2021年09月14日 21:08:00
 */

@Aspect
@Component
public class DynamicDataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 切点: 所有配置 DataSource 注解的方法
     */
    @Pointcut("@annotation(com.wxj.springboot.jta.dbAop.DataSource)")
    public void dataSourcePointCut() {}


    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource ds = null;

        MethodSignature  signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

        // 获取自定义注解
        ds = method.getAnnotation(DataSource.class);
        if(ds == null){
            //如果监测到自定义注解不存在,那么默认切换到数据源 mydbone
            DataSourceContextHolder.setDataSourceKey(DataSourceNames.ONE);
            logger.info("set default datasource is " + DataSourceNames.ONE);
        }else {
            //自定义存在,则按照注解的值去切换数据源
            DataSourceContextHolder.setDataSourceKey(ds.value());
            logger.info("set datasource is " + ds.value());
        }

        return point.proceed();
    }

    @After(value = "dataSourcePointCut()")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.clearDataSourceKey();
        logger.info("clean datasource");
    }
}
