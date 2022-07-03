package com.wxj.springboot.mybatis.controller;

import com.wxj.springboot.mybatis.convert.CompanyConvertBasic;
import com.wxj.springboot.mybatis.domain.dto.CompanyDTO;
import com.wxj.springboot.mybatis.domain.reqparam.CompanyReqParams;
import com.wxj.springboot.mybatis.domain.vo.CompanyVO;
import com.wxj.springboot.mybatis.result.Result;
import com.wxj.springboot.mybatis.service.CompanyService;
import com.wxj.springboot.mybatis.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName CompanyController.java
 * @Description TODO
 * @createTime 2022年07月02日 13:13:00
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @Resource
    private CompanyConvertBasic basic;

    /**
     * 查询列表
     * @return
     */
    @GetMapping("list")
    public Result<List<CompanyVO>> list(){
        List<CompanyDTO> list = companyService.list();
        //将DTO 转成 VO
        List<CompanyVO> companyVOS = basic.toListCompanyVO(list);
        return ResultUtil.success(companyVOS);
    }

    @GetMapping("detail")
    public Result<CompanyVO> detail(@RequestParam("id") Long id){
        CompanyDTO companyDTO = companyService.getDetailById(id);
        //将DTO 转成VO
        CompanyVO companyVO = basic.toCompanyVO(companyDTO);
        return ResultUtil.success(companyVO);
    }

    /**
     * 新增
     * @param params
     * @return
     */
    @PostMapping("create")
    public Result<Integer> create( @RequestBody CompanyReqParams params){
        CompanyDTO companyDTO = basic.toCompanyDTO(params);
        return ResultUtil.success(companyService.create(companyDTO));
    }

    /**
     * 修改
     * @param params
     * @return
     */
    @PostMapping("update")
    public Result<Integer> update(@RequestBody CompanyReqParams params){
        CompanyDTO companyDTO = basic.toCompanyDTO(params);
        return ResultUtil.success(companyService.update(companyDTO));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result<Integer> delete(@RequestParam("id") Long id){
        return ResultUtil.success(companyService.delete(id));
    }
}
