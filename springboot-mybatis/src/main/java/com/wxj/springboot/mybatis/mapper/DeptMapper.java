package com.wxj.springboot.mybatis.mapper;

import com.wxj.springboot.mybatis.domain.entity.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DeptMapper.java
 * @Description TODO
 * @createTime 2022年07月02日 17:41:00
 */
public interface DeptMapper {

    /**
     * 获取部门的信息
     * @param id
     * @return
     */
    Dept getDeptAndEmpById(@Param("id") Long id);
}
