package com.wxj.springboot.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName MyMetaObjectHandler.java
 * @Description 自定义MyBatis-Plus填充处理器
 * @createTime 2022年06月29日 23:00:00
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 默认策略：如果属性有值则不覆盖，如果填充值为null则不填充
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    /**
     * 做insert插入时自动填充的值（这里一般是create_time和update_time）
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter(CREATE_TIME) && metaObject.hasGetter(UPDATE_TIME)) { // 实体类有get方法，就是有这个字段
            LocalDateTime localDateTime = LocalDateTime.now();
            log.info("【insertFill】localDateTime={}", localDateTime.toString());
            this.strictInsertFill(metaObject, CREATE_TIME, () -> localDateTime, LocalDateTime.class); // 起始版本 3.3.3(推荐)
            this.strictInsertFill(metaObject, UPDATE_TIME, () -> localDateTime, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        }
    }

    /**
     * 做update更新时自动填充的值（更新就只针对update_time）
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter(UPDATE_TIME)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            log.info("【updateFill】localDateTime={}", localDateTime.toString());
            this.strictUpdateFill(metaObject, UPDATE_TIME, () -> localDateTime, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        }
    }
}
