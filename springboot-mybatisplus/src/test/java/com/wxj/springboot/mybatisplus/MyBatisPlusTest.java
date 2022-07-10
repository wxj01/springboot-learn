package com.wxj.springboot.mybatisplus;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxj.springboot.mybatisplus.enums.SexEnum;
import com.wxj.springboot.mybatisplus.mapper.ProductMapper;
import com.wxj.springboot.mybatisplus.mapper.UserMapper;
import com.wxj.springboot.mybatisplus.pojo.Product;
import com.wxj.springboot.mybatisplus.pojo.User;
import com.wxj.springboot.mybatisplus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
@Slf4j
public class MyBatisPlusTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private UserService userService;

    @Test
    public void test() {
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("Vz");
        user.setAge(21);
        user.setEmail("vz@oz6.cn");

        int result = userMapper.insert(user);
        System.out.println(result > 0 ? "添加成功！" : "添加失败！");
        System.out.println("受影响的行数为：" + result);
        //1527206783590903810（当前 id 为雪花算法自动生成的id）
        System.out.println("id自动获取" + user.getUid());
    }

    @Test
    public void testDeleteById() {
        int result = userMapper.deleteById(1545741120175349761L);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);

    }

    @Test
    public void testDeleteBatchIds() {
        List<Long> ids = Arrays.asList(6L, 7L, 8L);
        int result = userMapper.deleteBatchIds(ids);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void deleteByMap() {
        //当前演示为根据name和age删除数据
        //执行SQL为：DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("username", "Vz");
        map.put("age", 21);
        int result = userMapper.deleteByMap(map);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void testUpdateById() {
        //执行SQL为： UPDATE user SET name=?, age=?, email=? WHERE id=?
        User user = new User();
        user.setUid(6L);
        user.setName("VzUpdate");
        user.setAge(18);
        user.setEmail("Vz@sina.com");
        int result = userMapper.updateById(user);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        //执行SQL为：SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> longs = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(longs);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        //执行SQL为：SELECT id,name,age,email FROM user WHERE age = ?
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testGetCount() {
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

    @Test
    public void testSaveBatch() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("wxj" + i);
            user.setAge(19 + i);

            users.add(user);
        }

        boolean b = userService.saveBatch(users);
        System.out.println(b ? "添加成功！" : "添加失败！");

    }

    @Test
    public void test01() {
        //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
//        SELECT uid AS id, username AS name, age, email, is_deleted FROM t_user WHERE is_deleted = 0
//        AND(username LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", "a").between("age", 20, 30).isNotNull("email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test02() {
//        SELECT uid AS id, username AS name, age, email, is_deleted FROM t_user WHERE is_deleted = 0 ORDER BY
//        age DESC, id ASC
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByDesc("age").orderByAsc("uid");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);

    }

    @Test
    public void test03() {
//        UPDATE t_user SET is_deleted=1 WHERE is_deleted=0 AND (email IS NULL)
        //删除邮箱地址为null的用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.isNotNull("email");
        int result = userMapper.delete(userQueryWrapper);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void test04() {
//        UPDATE t_user SET user_name =?,email =?WHERE is_deleted = 0 AND(age > ? AND user_name LIKE ? OR email IS NULL)
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        UpdateWrapper<User> userQueryWrapper = new UpdateWrapper<>();
        userQueryWrapper.gt("age", 20).like("username", "a").or().isNull("email");

        User user = new User();
        user.setName("Oz");
        user.setEmail("test@oz6.com");

        int result = userMapper.update(user, userQueryWrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void test05() {
//        UPDATE t_user SET username=?, email=? WHERE is_deleted=0 AND (username LIKE ? AND (age > ? OR email IS NULL))
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("username", "a").and(i -> i.gt("age", 20).or().isNull("email"));

        User user = new User();
        user.setName("Vz7797");
        user.setEmail("test@ss8o.com");

        int result = userMapper.update(user, userUpdateWrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void test06() {
//        SELECT username,age,email FROM t_user WHERE is_deleted=0
        //查询用户的用户名、年龄、邮箱信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.select("username", "age", "email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test07() {
        //查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "select uid from t_user where uid <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08() {
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("username", "a").and(i -> i.gt("age", 20).or().isNull("email")).set("email", "wxj@qq.com");
        int result = userMapper.update(null, userUpdateWrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);

    }

    @Test
    public void test09() {
//        SELECT uid AS id, user_name AS name, age, email, is_deleted FROM t_user WHERE is_deleted = 0
//        AND(user_name LIKE ? AND age <= ?)
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //isNotBlank判断某个字符创是否不为空字符串、不为null、不为空白符
        userQueryWrapper.like(StringUtils.isNoneBlank(username), "username", username);

        if (ageBegin != null) {
            userQueryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            userQueryWrapper.le("age", ageEnd);
        }

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
        
    }

    @Test
    public void test10() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(StringUtils.isNoneBlank(username), "username", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test11(){
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

        userLambdaQueryWrapper.eq(StringUtils.isNoneBlank(username),User::getName,username)
                .ge(ageBegin != null,User::getAge,ageBegin)
                .le(ageEnd != null,User::getAge,ageEnd);

        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test12(){
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "小黑").set(User::getEmail,"abc@atguigu.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result："+result);
    }

    @Test
    public void testPage(){
        //new Page()中的两个参数分别是当前页码，每页显示数量
        Page<User> userPage = userMapper.selectPage(new Page<User>(1, 2), null);
        List<User> records = userPage.getRecords();
//        records.forEach(System.out::println);
        records.forEach(System.out::println);
    }

    @Test
    public void testCustomPage(){
        Page<User> userPage = userMapper.selectPageVo(new Page<User>(1, 2), null);
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }
    
    @Test
    public void testProduct01(){
        //1.小李获取商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productLi.getPrice());

        //2.小王获取商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productWang.getPrice());

        //3.小李修改商品价格+50
        productLi.setPrice(productLi.getPrice()+50);
        productMapper.updateById(productLi);

        //4.小王修改商品价格-30
        productWang.setPrice(productWang.getPrice()-30);
        productMapper.updateById(productWang);

        //5.老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板获取的商品价格为：" + productBoss.getPrice());
    }

    @Test
    public void testProduct02(){
        //1.小李获取商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productLi.getPrice());

        //2.小王获取商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小李获取的商品价格为：" + productWang.getPrice());

        //3.小李修改商品价格+50
        productLi.setPrice(productLi.getPrice()+50);
        productMapper.updateById(productLi);

        //4.小王修改商品价格-30
        productWang.setPrice(productWang.getPrice()-30);
        int result = productMapper.updateById(productWang);
        if(result == 0){
            //操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice()-30);
            productMapper.updateById(productNew);
        }

        //5.老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板获取的商品价格为：" + productBoss.getPrice());
    }


    @Test
    public void testEnum(){
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
    }


}




