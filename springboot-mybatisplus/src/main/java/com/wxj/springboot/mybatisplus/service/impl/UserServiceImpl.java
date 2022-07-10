package com.wxj.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxj.springboot.mybatisplus.mapper.UserMapper;
import com.wxj.springboot.mybatisplus.pojo.User;
import com.wxj.springboot.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
