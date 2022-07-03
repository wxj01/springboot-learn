package com.wxj.springboot.mybatis.service;

import com.wxj.springboot.mybatis.domain.dto.CompanyDTO;
import com.wxj.springboot.mybatis.domain.entity.Dept;
import com.wxj.springboot.mybatis.result.Result;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyService.java
 * @Description 部门接口类
 * @createTime 2022年07月02日 14:07:00
 */
public interface DeptService {

    /**
     * 根据id 查询部门详情
     * @param id
     * @return
     */
    Dept getDetailById(Long id);




}
