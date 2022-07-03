package com.wxj.springboot.mybatis.controller;

import com.wxj.springboot.mybatis.domain.entity.Dept;
import com.wxj.springboot.mybatis.result.Result;
import com.wxj.springboot.mybatis.service.DeptService;
import com.wxj.springboot.mybatis.utils.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName DeptController.java
 * @Description TODO
 * @createTime 2022年07月02日 17:51:00
 */
@RestController
@RequestMapping("dept")
public class DeptController {


    @Resource
    private DeptService deptService;

    @GetMapping("detail")
    public Result<Dept> detail(@RequestParam Long id){

        Dept detailById = deptService.getDetailById(id);
        return ResultUtil.success();

    }
}
