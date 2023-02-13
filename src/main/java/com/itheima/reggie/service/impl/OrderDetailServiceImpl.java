package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.pojo.OrderDetail;
import com.itheima.reggie.service.OrderDetailService;
import com.itheima.reggie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




