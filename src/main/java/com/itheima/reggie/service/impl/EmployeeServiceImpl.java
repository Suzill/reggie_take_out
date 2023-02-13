package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.Enums.EmployeeEnums;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Employee;
import com.itheima.reggie.service.EmployeeService;
import com.itheima.reggie.mapper.EmployeeMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Objects;

/**
* @author A
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2023-02-08 17:18:31
*/
@Service
@Slf4j
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

    @Override
    public R<Employee> login(HttpServletRequest request, Employee employee) {
        // 1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 2、根据页面提交的用户名username查询数据库
        Employee employee1 = this.getOne(new LambdaQueryWrapper<Employee>().eq(Employee::getUsername, employee.getUsername()));

        // 3、如果没有查询到则返回登录失败结果
        if (employee1 == null){
            return R.error("账号或者密码错误");
        }

        // 4、密码比对，如果不一致则返回登录失败结果
        if (!Objects.equals(employee1.getPassword(), password)){
            return R.error("登录失败");
        }

        // 5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (Objects.equals(employee1.getStatus(), EmployeeEnums.DISABLE.getType())){
            return R.error("账号已禁用");
        }

        // 6、登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", employee1.getId());
        return R.success(employee1);
    }

    @Override
    public R<String> add(HttpServletRequest request, Employee employee) {
        log.info("新增员工，员工信息：{}",employee.toString());
        //设置初始密码，需要进行md5加密处理
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        // 获取创建人的id
        Long emp = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(emp);
        employee.setCreateUser(emp);
        this.save(employee);
        return R.success("新增员工成功");
    }

    @Override
    public R<Page> listPage(int page, int pageSize, String name) {
        //构造分页构造器
        Page pageInfo = new Page<>(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        wrapper.like(!ObjectUtils.isEmpty(name), Employee::getName, name);
        //添加排序条件
        wrapper.orderByDesc(Employee::getCreateTime);
        //执行查询
        return R.success(this.page(pageInfo, wrapper));
    }

    @Override
    public R<String> updateUser(HttpServletRequest request, Employee employee) {
        Long id = Thread.currentThread().threadId();
        log.info("线程id: {}", id);
        // 修改人
        Long employee1 = (Long) request.getSession().getAttribute("employee");
        employee.setUpdateUser(employee1);
        employee.setUpdateTime(new Date());
        this.updateById(employee);
        return R.success("员工信息修改成功");
    }
}




