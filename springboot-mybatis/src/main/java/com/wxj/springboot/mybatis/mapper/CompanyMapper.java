package com.wxj.springboot.mybatis.mapper;

import com.wxj.springboot.mybatis.domain.entity.Company;
import com.wxj.springboot.mybatis.result.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyMapper.java
 * @Description TODO
 * @createTime 2022年07月02日 14:13:00
 */

public interface CompanyMapper {

    /**
     * 根据id查公司
     * @param id
     * @return
     */
    Company getCompanyById(@Param("id") Long id);


    /**
     * 查询所有公司
     * @return
     */
    List<Company> list();

    /**
     * 新增公司
     * @param company
     * @return
     */
    Integer create(@Param("company") Company company);

    /**
     * 根据id 修改公司
     * @param company
     * @return
     */
    Integer update(@Param("company") Company company);

    /**
     * 根据id 删除
     * @param id
     * @return
     */
    Integer delete(@Param("id") Long id);
}
