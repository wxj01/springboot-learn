package com.wxj.springboot.pool.starter.autoconfigurer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/27 0027 14:39
 */
@ConfigurationProperties(prefix = "thread.pool")
@Data
public class ThreadPoolProperties {

    List<ThreadPoolEntity> list = new ArrayList<>();
}