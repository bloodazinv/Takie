/**
 * FileName: CategoryController
 * Author: jane
 * Date: 2022/7/27 19:12
 * Description:
 * Version:
 */

package com.takie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.takie.common.R;
import com.takie.entity.Category;
import com.takie.entity.Employee;
import com.takie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * new category
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category){
        categoryService.save(category);
        return R.success(" new category added");
    }

    /**
     * query all categories by pages
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){//Page是mybatisplus提供的分页工具
        log.info("page = {}, pageSize = {}", page, pageSize);
        //分页构造器
        Page pageinfo = new Page(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Category> querywrapper = new LambdaQueryWrapper();
        //添加过滤条件
        //添加排序条件
        querywrapper.orderByAsc(Category::getSort);
        //执行查询
        //mybatisplus 的分页查询方法
        categoryService.page(pageinfo,querywrapper);//会把最终的查询结果封装到pageinfo之中
        return R.success(pageinfo);
    }

    @DeleteMapping
    public R<String> delete(Long ids){
        categoryService.remove(ids);
        return R.success("category deleted");
    }

    /**
     * update employee information according to empid
     * @param
     * @return
     */
    @PutMapping("")
    public R<String> update(HttpServletRequest request, @RequestBody Category category){

        categoryService.updateById(category);
        return R.success("employee information updated!");
    }

    /**
     * query categories with conditions
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
