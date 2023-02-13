package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.pojo.Orders;
import com.itheima.reggie.service.OrdersService;
import com.itheima.reggie.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




