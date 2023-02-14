package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.SetmealDto;
import com.itheima.reggie.pojo.Setmeal;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author author
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 套餐分页
     * @param page 页数
     * @param pageSize 大小
     * @param name 套餐名称
     * @return 套餐列表
     */
    @GetMapping("/page")
    public R<Page<SetmealDto>> page(int page, int pageSize, String name) {
        return R.success(setmealService.pageList(page, pageSize, name));
    }


    /**
     * 新增套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
    }

    /**
     * 删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(String[] ids){
        int index=0;
        for(String id:ids) {
            Setmeal setmeal = setmealService.getById(id);
            if(setmeal.getStatus()!=1){
                setmealService.removeById(id);
            }else {
                index++;
            }
        }
        if (index>0&&index==ids.length){
            return R.error("选中的套餐均为启售状态，不能删除");
        }else {
            return R.success("删除成功");
        }
    }

    /**
     * 套餐修改状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> sale(@PathVariable int status,String[] ids){
        for (String id:ids){
            Setmeal setmeal = setmealService.getById(id);
            setmeal.setStatus(status);
            setmealService.updateById(setmeal);
        }
        return R.success("修改成功");
    }

    //根据Id查询套餐信息
    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id){
        SetmealDto setmealDto=setmealService.getByIdWithDish(id);

        return R.success(setmealDto);
    }

    //修改套餐
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto){
        setmealService.updateWithDish(setmealDto);
        return R.success("修改成功");
    }





}
