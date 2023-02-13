package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Category;
import com.itheima.reggie.pojo.Employee;
import com.itheima.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R<String> deleteByIds(@PathVariable int ids) {
        return categoryService.deleteByIds(ids);
    }

    @PutMapping
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("修改分类成功");
    }


}
