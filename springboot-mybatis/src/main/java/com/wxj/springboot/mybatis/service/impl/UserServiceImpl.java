package com.wxj.springboot.mybatis.service.impl;

import com.wxj.springboot.mybatis.domain.entity.User;
import com.wxj.springboot.mybatis.mapper.UserMapper;
import com.wxj.springboot.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName UserSerciceImpl.java
 * @Description 用户接口类
 * @createTime 2021年09月15日 20:08:00
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertUser(User user) {

        checkSevice();
        if (user.getUserName() != null && !"".equals(user.getUserName())) {
            try {
                // 对mysql 数据库增加操作后，影响的行数effecteNum
                int effectNum = userMapper.insertUser(user);
                if (effectNum > 0) {
                    //如果影响行数大于0，那么就是增加成功
                    System.out.println("增加成功，主键为：" + user.getId());
                    return true;
                } else {
                    throw new RuntimeException("插入信息失败,插入行数有误");
                }
            } catch (Exception e) {
                throw new RuntimeException("插入信息失败了:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("信息不能为空！！！！");
        }
    }

    private void checkSevice() {
        int i = 1 / 0;
    }

    /**
     * 按id查找
     *
     * @param id
     * @return
     */
    @Override
    public User getUserInfoById(int id) {
        return userMapper.getUserInfoById(id);

    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<User> queryUserInfo() {
        return userMapper.queryUserInfo();
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserInfoById(User user) {
        if (user.getId() != null && !"".equals(user.getId())) {
            try {
                //对mysql数据库做更新操作后，影响的行数effecteNum
                int effectNum = userMapper.updateUserInfoById(user);
                if (effectNum > 0) {
                    //如果影响行数大于0，那么就是更新成功
                    System.out.println("更新成功，主键为：" + user.getId());
                    return true;
                } else {
                    throw new RuntimeException("更新信息失败,插入行数有误");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新信息失败了:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("信息不能为空！！！！");
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUserInfoById(int id) {

        try {
            //对mysql数据库做删除操作后，影响的行数effecteNum
            int effectNum = userMapper.deleteUserInfoById(id);

            if (effectNum > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
