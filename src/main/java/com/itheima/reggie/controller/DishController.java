package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.pojo.Category;
import com.itheima.reggie.pojo.Dish;
import com.itheima.reggie.pojo.DishFlavor;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @param dish 菜品Dto
     * @return return
     */
    @CacheEvict(value = "setmealCache",allEntries = true)
    @PostMapping
    public R<String> save(@RequestBody DishDto dish) {
        dishService.save(dish);
        //清理所有菜品缓存数据
        //Set keys = redisTemplate.keys("dish_*");
        //redisTemplate.delete(keys);

        //清理某个分类下面的菜品缓存数据
        String key="dish_"+dish.getCategoryId()+"_"+dish.getStatus();
        redisTemplate.delete(key);
        return R.success("新增菜成功");
    }

    /**
     * 新增菜品分页
     * @param page 分页参数
     * @param pageSize 分页参数
     * @param name 菜品名称
     * @return 菜品详情
     */
    @GetMapping("/page")
    public R<Page<DishDto>> page(int page, int pageSize, String name) {
        return dishService.pageList(page, pageSize, name);
    }

    /**
     * 根据菜品id获取菜品信息
     * @param id 菜品id
     * @return 菜品信息
     */
    @GetMapping("{id}")
    public R<DishDto> DishGetById(@PathVariable Long id) {
        return R.success(dishService.getByIdWithFlavor(id));
    }

    @CacheEvict(value = "setmealCache",allEntries = true)
    @PutMapping
    public R<String> updateById(@RequestBody DishDto dishDto) {
        dishService.updateWithFlavor(dishDto);
        //清理所有菜品缓存数据
        //Set keys = redisTemplate.keys("dish_*");
        //redisTemplate.delete(keys);

        //清理某个分类下面的菜品缓存数据
        String key="dish_"+dishDto.getCategoryId()+"_"+dishDto.getStatus();
        redisTemplate.delete(key);
        return R.success("修改菜品成功");
    }

    //停售起售菜品
    @PostMapping("/status/{status}")
    public R<String> sale(@PathVariable int status, String[] ids){
        for(String id: ids){
            Dish dish = dishService.getById(id);
            dish.setStatus(status);
            dishService.updateById(dish);
        }
        return R.success("修改成功");
    }

    //删除菜品
    @CacheEvict(value = "setmealCache",allEntries = true)
    @DeleteMapping
    public R<String> delete(String[] ids){
        for (String id:ids) {
            dishService.removeById(id);
        }
        return R.success("删除成功");
    }

    //根据条件查询对应菜品数据
    @Cacheable(value = "setmealCache",key = "#setmeal.categoryId+'_'+#setmeal.status")
    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish) {
        // 改造DishController的list方法，先从Redis中获取菜品数据，如果有则直接返回，无需查询数据库
        List<DishDto> dishDtoList1=null;
        //动态构造Key
        String key="dish_"+dish.getCategoryId()+"_"+dish.getStatus();
        //先从redis中获取缓存数据
        dishDtoList1 = (List<DishDto>) redisTemplate.opsForValue().get(key);
        if(dishDtoList1!=null){
            //如果存在，则直接返回，无需查询数据库
            return R.success(dishDtoList1);
        }

        //构造查询条件
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加条件，查询状态为1的（起售状态）
        lambdaQueryWrapper.eq(Dish::getStatus, 1);
        lambdaQueryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
        //条件排序条件
        lambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(lambdaQueryWrapper);
        //如果不存在，则查询数据库，并且将查询到的菜品数据添加到缓存中
        redisTemplate.opsForValue().set(key,list,60, TimeUnit.MINUTES);

        List<DishDto> dishDtoList = list.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            //根据id查分类对象
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            //当前菜品id
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId, dishId);
            //SQL: select* from dishflavor where dish_id=?;
            List<DishFlavor> dishFlavorlist = dishFlavorService.list(queryWrapper);
            dishDto.setFlavors(dishFlavorlist);
            return dishDto;
        }).collect(Collectors.toList());

        return R.success(dishDtoList);
    }


}
