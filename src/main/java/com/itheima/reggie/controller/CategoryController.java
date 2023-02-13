package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Category;
import com.itheima.reggie.pojo.Employee;
import com.itheima.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category pojo
     * @return string
     */
    @PostMapping
    @Transactional
    public R<String> save(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    /**
     * 分类列表
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        return categoryService.pageCategory(page, pageSize);
    }

    /**
     * 删除分类
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    @Transactional
    public R<String> deleteByIds(@PathVariable int ids) {
        return categoryService.deleteByIds(ids);
    }

    /**
     * 更新分类
     * @param category 菜品分类
     * @return return
     */
    @PutMapping
    @Transactional
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("修改分类成功");
    }

    /**
     * //根据条件查询分类数据
     * @param category 菜品分类
     * @return 分类下拉
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        //条件构造器
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //添加条件
        lambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        //添加排序条件
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByAsc(Category::getUpdateTime);
        List<Category> list = categoryService.list(lambdaQueryWrapper);
        return R.success(list);
    }


}
