package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
* @author A
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2023-02-08 17:18:31
*/
public interface SetmealService extends IService<Setmeal> {

    Page<SetmealDto> pageList(int page, int pageSize, String name);

    //新增套餐，同时要保持与菜品的关联关系
    @Transactional
    void saveWithDish(SetmealDto setmealDto);

    SetmealDto getByIdWithDish(Long id);

    void updateWithDish(SetmealDto setmealDto);
}
