package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.pojo.ShoppingCart;
import com.itheima.reggie.service.ShoppingCartService;
import com.itheima.reggie.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




