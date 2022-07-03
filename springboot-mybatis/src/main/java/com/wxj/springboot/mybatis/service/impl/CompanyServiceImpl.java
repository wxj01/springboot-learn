package com.wxj.springboot.mybatis.service.impl;

import com.wxj.springboot.mybatis.convert.CompanyConvertBasic;
import com.wxj.springboot.mybatis.domain.dto.CompanyDTO;
import com.wxj.springboot.mybatis.domain.entity.Company;
import com.wxj.springboot.mybatis.domain.vo.CompanyVO;
import com.wxj.springboot.mybatis.mapper.CompanyMapper;
import com.wxj.springboot.mybatis.result.Result;
import com.wxj.springboot.mybatis.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private CompanyConvertBasic basic;

    @Override
    public CompanyDTO getDetailById(Long id) {
        Company company = companyMapper.getCompanyById(id);
        //将company 转成 companyDTO
        CompanyDTO companyDTO = basic.toCompanyDTO(company);
        return companyDTO;
    }

    @Override
    public List<CompanyDTO> list() {
        List<Company> list = companyMapper.list();
        List<CompanyDTO> companyDTOS = basic.toListCompanyDTO(list);
        return companyDTOS;
    }

    @Override
    public Integer create(CompanyDTO dto) {
        Company company = basic.toCompany(dto);
        Integer result = companyMapper.create(company);
        return result;
    }

    @Override
    public Integer update(CompanyDTO dto) {
        Company company = basic.toCompany(dto);
        Integer result = companyMapper.update(company);
        return result;
    }

    @Override
    public Integer delete(Long id) {
        Integer delete = companyMapper.delete(id);
        return delete;
    }
}
