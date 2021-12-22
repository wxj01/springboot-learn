package com.wxj.springboot.multidatasource.service;

import com.wxj.springboot.multidatasource.model.Student;
import com.wxj.springboot.multidatasource.model.Student2;

import java.util.List;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 10:42
 */
public interface StudentService {

    List<Student> studentList1();

    List<Student2> studentList2();
}
