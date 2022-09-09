package com.wxj.springboot.mongodb;

import com.wxj.springboot.mongodb.dao.UserRepository;
import com.wxj.springboot.mongodb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = new User();
        user.setName("test");
        user.setAge(20);
        user.setEmail("ertong@qq.com");
        User save = userRepository.save(user);
        System.out.println("save = " + save);
    }

    @Test
    public void findAll() {
        List<User> all = userRepository.findAll();
        System.out.println("all = " + all);
    }

    @Test
    public void findById() {
        User user = userRepository.findById("6319641ccb0b5e5d937323ce").get();
        System.out.println("user = " + user);
    }

    @Test
    public void findUserList() {
        //创建匹配器、如何使用查询条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                // 改变默认字符串匹配方式；模糊查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true); // 改变默认大小写忽略方式。忽略大小写
        User user = new User();
        user.setName("ertong");
        user.setAge(20);

        Example<User> example = Example.of(user);
        List<User> all = userRepository.findAll(example);
        System.out.println("all = " + all);


    }

    @Test
    public void findPageUserAll() {

        /**
         *  设置分页参数
         *  0 是 第一页
         */
        PageRequest pageRequest = PageRequest.of(0, 3);

        // 查询条件
        User user = new User();
        user.setName("test");
        Example<User> userExample = Example.of(user);
        Page<User> all = userRepository.findAll(userExample, pageRequest);
        System.out.println("all.getContent() = " + all.getContent());
    }

    @Test
    public void delete() {
        userRepository.deleteById("6319b6e46ddd4b434e20c7b7");
    }
}
