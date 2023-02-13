package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.pojo.DishFlavor;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




