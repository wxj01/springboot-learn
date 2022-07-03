package com.wxj.springboot.mybatis.convert;


import com.wxj.springboot.mybatis.domain.dto.CompanyDTO;
import com.wxj.springboot.mybatis.domain.entity.Company;
import com.wxj.springboot.mybatis.domain.reqparam.CompanyReqParams;
import com.wxj.springboot.mybatis.domain.vo.CompanyVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyConvertBasic.java
 * @Description company转化
 * @createTime 2022年07月02日 18:45:00
 */
@Mapper(componentModel = "spring")
public interface CompanyConvertBasic {
    CompanyConvertBasic INSTANCE = Mappers.getMapper(CompanyConvertBasic.class);

    /**
     * 将Company 转成 CompanyDTO
     * @param company
     * @return
     */
    CompanyDTO toCompanyDTO(Company company);


    /**
     * 将companyDTO 转成 companyVO
     * @param companyDTO
     * @return
     */
    CompanyVO toCompanyVO(CompanyDTO companyDTO);


    /**
     * 将页面请求的参数 转成 DTO
     * @param params
     * @return
     */
    CompanyDTO toCompanyDTO(CompanyReqParams params);


    /**
     * 将dto 转成 实体
     * @param companyDTO
     * @return
     */
    Company toCompany(CompanyDTO companyDTO);


    /**
     * 将 List<CompanyDTO> 转成 List<CompanyVO>
     * @param dtoList
     * @return
     */
    List<CompanyVO> toListCompanyVO(List<CompanyDTO> dtoList);


    /**
     * 将  List<Company> 转成  List<CompanyDTO>
     * @param list
     * @return
     */
    List<CompanyDTO> toListCompanyDTO(List<Company> list);
}
