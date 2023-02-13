package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.pojo.Dish;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.mapper.DishMapper;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

}




