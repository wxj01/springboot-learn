package com.wxj.springboot.learn.aware;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2022/1/7 0007 11:02
 */
@Component
public class ConcreteBean implements InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {
        System.out.println("释放资源");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化资源");
    }
}