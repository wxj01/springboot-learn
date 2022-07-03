package com.wxj.springboot.mybatis.service.impl;

import com.wxj.springboot.mybatis.domain.dto.CompanyDTO;
import com.wxj.springboot.mybatis.domain.entity.Company;
import com.wxj.springboot.mybatis.domain.entity.Dept;
import com.wxj.springboot.mybatis.mapper.CompanyMapper;
import com.wxj.springboot.mybatis.mapper.DeptMapper;
import com.wxj.springboot.mybatis.result.Result;
import com.wxj.springboot.mybatis.service.CompanyService;
import com.wxj.springboot.mybatis.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyServiceImpl.java
 * @Description TODO
 * @createTime 2022年07月02日 14:12:00
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept getDetailById(Long id) {
        Dept dept = deptMapper.getDeptAndEmpById(id);
        //将company 转成 companyDTO
        CompanyDTO companyDTO = new CompanyDTO();
        System.out.println(dept);

        return null;
    }

}
