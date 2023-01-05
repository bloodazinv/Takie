/**
 * FileName: EmplyeeController
 * Author: jane
 * Date: 2022/7/26 20:40
 * Description:
 * Version:
 */

package com.takie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takie.common.R;
import com.takie.entity.Employee;
import com.takie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmplyeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        //1.将页面提交的密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据页面提交的用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //3.没有查询到 -> 返回失败结果
        if(emp == null){
            return R.error("Login Failed!");
        }
        //4.密码对比 -> 不一致, 返回失败结果
        if(!emp.getPassword().equals(password)){
            return R.error("Login Failed!");
        }
        //5.查看员工状态
        if(emp.getStatus() == 0){
            return R.error("Account has been blocked");
        }
        //6.登陆成功, 将员工id存入session并返回登陆成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的当前登陆员工的id
        request.getSession().removeAttribute("employee");
        return R.success("Logout Successfully");
    }

    /**
     * add new employee account
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,
                          @RequestBody Employee employee){
        //set initial password
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //set time
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //get the id of the current user
        //Long empId = (Long) request.getSession().getAttribute("employee");
        //set create user
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("new user added");

    }

    /**
     * employee information query by pages
     * @param page: page index
     * @param pageSize how mane employees are shown
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){//Page是mybatisplus提供的分页工具
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);
        //分页构造器
        Page pageinfo = new Page(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Employee> querywrapper = new LambdaQueryWrapper();
        //添加过滤条件
        querywrapper.like(StringUtils.isNotEmpty(name), Employee::getUsername, name);
        //添加排序条件
        querywrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        //mybatisplus 的分页查询方法
        employeeService.page(pageinfo,querywrapper);//会把最终的查询结果封装到pageinfo之中
        return R.success(pageinfo);
    }

    /**
     * update employee information according to empid
     * @param employee
     * @return
     */
    @PutMapping("")
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee){
        //employee.setUpdateTime(LocalDateTime.now());
        //Long currentuser = (Long) request.getSession().getAttribute("employee");
        //employee.setUpdateUser(currentuser);

        employeeService.updateById(employee);
        return R.success("employee information updated!");
    }

    @GetMapping("/{id}")
    public R<Employee> getBtId(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("invalid employ found");
    }
}
