package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
* @author A
* @description 针对表【employee(员工信息)】的数据库操作Service
* @createDate 2023-02-08 17:18:31
*/
public interface EmployeeService extends IService<Employee> {

    R<Employee> login(HttpServletRequest request, Employee employee);

    R<String> add(HttpServletRequest request, Employee employee);

    R<Page> listPage(int page, int pageSize, String name);

    R<String> updateUser(HttpServletRequest request, Employee employee);
}
