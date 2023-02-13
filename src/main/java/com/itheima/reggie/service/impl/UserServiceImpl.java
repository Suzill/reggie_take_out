package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.pojo.User;
import com.itheima.reggie.service.UserService;
import com.itheima.reggie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




