package com.wxj.springboot.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wxj.springboot.mybatisplus.pojo.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName Student.java
 * @Description 学生类
 * @createTime 2022年06月29日 23:09:00
 */
@Data
@TableName("t_student") // 指定数据库里对应的表名
public class Student extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3845437225977119201L;

    // @TableId(type = IdType.AUTO)：表主键id注解，AUTO表示id是自增的
    // @TableField(value = "major_id")：表字段注解，value表示对应表里的字段，但只要表字段命名规范，实体类里驼峰命名，就可不加此注解

    /**
     * 学生id
     */
//    @TableId(type = IdType.AUTO) // 如果你的表主键id是自增的，就加上这行注解，我这里的id是非自增，所以不用加@TableId注解
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别：1男，2女，0未知
     */
    private Integer gender;

    /**
     * 专业id
     */
//    @TableField(value = "major_id") // 指定对应表里字段，为了方便，此注解可以不加
    private Integer majorId;

    /**
     * 手机号
     */
    private String phone;

    //======下面是我额外加的字段=========

    /**
     * 所属专业名称
     */
    @TableField(exist = false) // 非表字段（这种就得加上@TableField注解，exist默认为true，为false时表示非数据表字段）
    private String majorName; // 这个字段在mybatis-plus自动查询的时候就不会带上，不过当你在xml里自定义写多表查询sql时可映射此字段

}
