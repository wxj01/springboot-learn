package com.wxj.springboot.multidatasource.service.impl;

import com.wxj.springboot.multidatasource.mapper.StudentMapper;
import com.wxj.springboot.multidatasource.mapper2.StudentMapper2;
import com.wxj.springboot.multidatasource.model.Student;
import com.wxj.springboot.multidatasource.model.Student2;
import com.wxj.springboot.multidatasource.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 10:43
 */
@Repository
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentMapper2 studentMapper2;

    @Override
    public List<Student> studentList1() {
        return studentMapper.list();
    }

    @Override
    public List<Student2> studentList2() {
        return studentMapper2.list();
    }
}