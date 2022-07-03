//package com.wxj.springboot.mybatisplus.controller;
//
//import com.wxj.springboot.mybatisplus.mapper.StudentMapper;
//import com.wxj.springboot.mybatisplus.pojo.Student;
//import com.wxj.springboot.mybatisplus.result.R;
//import com.wxj.springboot.mybatisplus.result.Result;
//import com.wxj.springboot.mybatisplus.service.StudentService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author wxj
// * @version 1.0.0
// * @ClassName StudentController.java
// * @Description
// * @createTime 2022年06月29日 23:21:00
// */
//@Slf4j
//@RestController
//@RequestMapping("/student")
//@RequiredArgsConstructor // lombok注解：可代替@Autowired，但是必须加上final（其实就是通过构造器来注入）
//public class StudentController {
//
//    // 为了演示，我这里把service和mapper都注入了进来，实际开发只需注入一个service，主要是介绍service的mapper对的操作区别
//    // 其实service和mapper在功能上基本上是一样的，service有的功能mapper也有，只是他两用的时候在方法名字上有区别
//    // service里的方法内部再去调用了mapper，可以点进去看下源码就明白了
//    // 实际中，可以在service中自定义一些方法，在serviceImpl中再调用mapper，当明白了mybatisplus的使用后可自由发挥
//
//    private final StudentService studentService;
//    private final StudentMapper studentMapper;
//
//    /**
//     * 新增
//     */
//    @PostMapping("/add")
//    public R add() {
//        // service演示新增
//        Student student = new Student();
//        student.setName("张三");
//        student.setGender(1);
//        student.setMajorId(1);
//        student.setPhone("18300001111");
//        boolean save = studentService.save(student);
//        // mapper演示新增
//        Student student1 = new Student();
//        student1.setName("小芳");
//        student1.setGender(2);
//        student1.setMajorId(1);
//        student1.setPhone("18300002222");
//        int insert = studentMapper.insert(student1); // 上面的save内部也是调用insert，只是使用时名称不一样
//        log.info("【add】save={}, insert={}", save, insert);
//        // 批量插入
//        Student student2 = new Student();
//        student2.setName("小三");
//        student2.setGender(2);
//        student2.setMajorId(2);
//        student2.setPhone("18300003333");
//        Student student3 = new Student();
//        student3.setName("小明");
//        student3.setGender(1);
//        student3.setMajorId(1);
//        student3.setPhone("18300004444");
//        List<Student> studentList = new ArrayList<>();
//        studentList.add(student2);
//        studentList.add(student3);
//        studentService.saveBatch(studentList); // saveBatch，只能用service去调用
//        return Result.ok(save && insert == 1);
//    }
//}
