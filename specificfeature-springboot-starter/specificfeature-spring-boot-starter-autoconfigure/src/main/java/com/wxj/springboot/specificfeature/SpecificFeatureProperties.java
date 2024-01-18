package com.wxj.springboot.specificfeature;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("specific.branch")
public class SpecificFeatureProperties {
    /**
     * 分支名称
     */
    private String name;
    /**
     *  正则
     */
    private String rule;

    /**
     * 是否开启自动配置
     */
    private boolean enabled;
}
