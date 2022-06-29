package com.wxj.springboot.mybatisplus.pojo.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName BaseEntity.java
 * @Description TODO
 * @createTime 2022年06月29日 23:05:00
 */
@Getter
@Setter
public abstract class BaseEntity {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) // 插入自动填充
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE) // 插入或更新时自动填充
    private LocalDateTime updateTime;

    /**
     * 删除标记（0未删除，1已删除）
     */
    //    @TableLogic // 此注解表示该字段是逻辑删除字段（这里注掉是因为现用的mp版本是3.4.2，从3.3.0版本后就可以省略该注解）
    private Integer delFlag;
}
