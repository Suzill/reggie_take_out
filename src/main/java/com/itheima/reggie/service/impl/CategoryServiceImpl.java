package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.R;
import com.itheima.reggie.exceptions.CustomException;
import com.itheima.reggie.pojo.Category;
import com.itheima.reggie.pojo.Dish;
import com.itheima.reggie.pojo.Setmeal;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public R<String> saveCategory(Category category) {
        this.save(category);
        return R.success("新增分类成功");
    }

    @Override
    public R<Page> pageCategory(int page, int pageSize) {
        Page<Category> page1 = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper();
        wrapper.orderByAsc(Category::getSort);
        Page<Category> page2 = this.page(page1, wrapper);
        return R.success(page2);
    }

    @Override
    public R<String> deleteByIds(int ids) {
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据分类id进行查询
        queryWrapper.eq(Dish::getCategory_id, ids);
        long count = dishService.count(queryWrapper);
        // 查询当前分类是否关联菜品,如果已经关联，抛出业务异常
        if (count > 0) {
            // 已经关联菜品，抛出业务异常
            throw new CustomException("已经关联菜品，不能删除");
        }
        // 查询当前分类是否关联了套餐，如果已经关联，抛出业务异常
        // 添加查询条件，根据分类id进行查询
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Setmeal::getCategory_id, ids);
        long count1 = setmealService.count(wrapper);
        if (count1 > 0){
            // 已经关联套餐，抛出业务异常
            throw new CustomException("已经关联菜品，不能删除");
        }
        // 正常删除分类
        this.removeById(ids);
        return R.success("删除分类成功");
    }
}




