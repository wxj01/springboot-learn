package com.wxj.springboot.learn;

import com.wxj.springboot.learn.task.Task;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wxj
 * @version 1.0
 * @description: TODO
 * @date 2021/12/27 0027 15:54
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoTests {

    @Autowired
    private Task task;

    @Test
    @SneakyThrows
    public void test() {
        for (int i = 0; i < 100; i++) {
            task.doTaskOne();
            task.doTaskTwo();
            task.doTaskThree();
        }
    }

}