package com.wxj.springboot.mybatis.service;

import com.wxj.springboot.mybatis.domain.dto.CompanyDTO;
import com.wxj.springboot.mybatis.domain.vo.CompanyVO;
import com.wxj.springboot.mybatis.result.Result;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyService.java
 * @Description 公司接口类
 * @createTime 2022年07月02日 14:07:00
 */
public interface CompanyService {

    /**
     * 根据id 查询公司详情
     * @param id
     * @return
     */
    CompanyDTO getDetailById(Long id);


    /**
     * 查询公司列表
     * @return
     */
    List<CompanyDTO> list();


    /**
     * 新增公司
     * @param dto
     * @return
     */
    Integer create(CompanyDTO dto);

    /**
     * 更新公司
     * @param dto
     * @return
     */
    Integer update(CompanyDTO dto);

    /**
     * 删除公司
     * @param id
     * @return
     */
    Integer delete(Long id);


}
