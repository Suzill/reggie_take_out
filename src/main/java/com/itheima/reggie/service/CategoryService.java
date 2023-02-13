package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author A
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2023-02-08 17:18:31
*/
public interface CategoryService extends IService<Category> {

    R<String> saveCategory(Category category);

    R<Page> pageCategory(int page, int pageSize);

    R<String> deleteByIds(int ids);
}
