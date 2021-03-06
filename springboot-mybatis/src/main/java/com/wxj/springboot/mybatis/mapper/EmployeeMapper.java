package com.wxj.springboot.mybatis.mapper;

import com.wxj.springboot.mybatis.domain.entity.Employee;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName EmployeeMapper.java
 * @Description TODO
 * @createTime 2022年05月27日 22:30:00
 */
public interface EmployeeMapper {

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    public Employee getEmpById(Integer id);
}
