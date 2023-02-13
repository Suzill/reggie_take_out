package com.itheima.reggie.service;

import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
* @author A
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2023-02-08 17:18:31
*/
public interface DishService extends IService<Dish> {


    @Transactional
    void saveWithFlavor(DishDto dishDto);
}
