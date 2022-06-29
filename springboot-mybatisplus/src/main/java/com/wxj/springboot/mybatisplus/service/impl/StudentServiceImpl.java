package com.wxj.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxj.springboot.mybatisplus.mapper.StudentMapper;
import com.wxj.springboot.mybatisplus.pojo.Student;
import com.wxj.springboot.mybatisplus.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName StudentServiceImpl.java
 * @Description TODO
 * @createTime 2022年06月29日 23:18:00
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService{
}
