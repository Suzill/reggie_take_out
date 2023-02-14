package com.itheima.reggie.service;

import com.itheima.reggie.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author A
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2023-02-08 17:18:31
*/
public interface OrdersService extends IService<Orders> {

    void submit(Orders orders);
}
