package com.wxj.springboot.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.wxj.springboot.mongodb.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setAge(20);
        user.setName("test");
        user.setEmail("123@qq.com");
        User insert = mongoTemplate.insert(user);
        System.out.println(insert);
    }

    @Test
    public void findAll() {
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(all);
    }

    @Test
    public void findById() {
        User byId = mongoTemplate.findById("63195b2f23a8410eecbd933e", User.class);
        System.out.println(byId);
    }

    @Test
    public void findUserList() {
        Query query = new Query(Criteria.where("name").is("test").and("age").is(20));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);

    }

    @Test
    public void findLikeUserList() {
        //        name like test
        String name = "te";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        /*1、在使用Pattern.compile函数时，可以加入控制正则表达式的匹配行为的参数：
        Pattern Pattern.compile(String regex, int flag)
        2、regex设置匹配规则
        3、Pattern.CASE_INSENSITIVE,这个标志能让表达式忽略大小写进行匹配。*/
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        //创建一个query对象（用来封装所有条件对象)，再创建一个criteria对象（用来构建条件）
        Query query = new Query(//构建查询条件
                Criteria.where("name").regex(pattern));
        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);
    }

    @Test
    public void pageLikeUserList() {
        int pageNo = 1;
        int pageSize = 3;

        //条件构造
        String name = "est";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        // 创建查询条件
        Query name1 = new Query(Criteria.where("name").regex(pattern));

        //分页构建
        // 查询数来集合中的总记录数
        long count = mongoTemplate.count(name1, User.class);
        List<User> users = mongoTemplate.find(
                name1.skip((pageNo - 1) * pageSize).limit(pageSize), User.class
        );
        System.out.println(users);
        System.out.println(count);
    }

    @Test
    public void updateUser() {
        User user = mongoTemplate.findById("63195b2f23a8410eecbd933e", User.class);

        user.setName("张三");
        user.setAge(20);
        user.setEmail("zhangsan@qq.com");

        //调用方法实现修改
        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("name", user.getName());
        update.set("age", user.getAge());
        update.set("email", user.getEmail());

        // 调用mongodb 的修改方法
        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
        long matchedCount = upsert.getMatchedCount();// 获取到受到影响的行数
        System.out.println("matchedCount = " + matchedCount);

    }

    @Test
    public void deleteUser() {
        Query query = new Query(Criteria.where("_id").is("63195b2f23a8410eecbd933e"));

        DeleteResult remove = mongoTemplate.remove(query, User.class);
        long deletedCount = remove.getDeletedCount();
        System.out.println("deletedCount = " + deletedCount);
    }
}
