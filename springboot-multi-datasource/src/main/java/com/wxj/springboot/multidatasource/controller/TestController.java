package com.wxj.springboot.multidatasource.controller;

import com.wxj.springboot.multidatasource.model.Student;
import com.wxj.springboot.multidatasource.model.Student2;
import com.wxj.springboot.multidatasource.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/22 0022 10:53
 */
@RestController
public class TestController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student/list")
    public void showStudent(){
        List<Student> students = studentService.studentList1();
        System.out.println("主数据库学生数据");
        students.stream().forEach(System.out::println);

        List<Student2> student2s = studentService.studentList2();
        System.out.println("副数据库学生数据");
        student2s.stream().forEach(System.out::println);
    }
}