/**
 * FileName: CategoryServiceImpl
 * Author: jane
 * Date: 2022/7/27 19:11
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.common.exception.CustomException;
import com.takie.entity.Category;
import com.takie.entity.Dish;
import com.takie.entity.Setmeal;
import com.takie.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * before deleting category according to id, check if this category was connecting to any dishes or meal sets
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        //添加查询条件---分类id
        dishWrapper.eq(Dish::getCategoryId, id);
        setmealWrapper.eq(Setmeal::getCategoryId, id);
        //查询当前分类是否关联了菜品，如果已经关联，抛出异常
        int dishCount = dishService.count(dishWrapper);
        if (dishCount > 0){
            throw new CustomException("Current category is connected to existed dishes!");
        }
        //查询当前分类是否关联了套餐
        int setmealCount = setmealService.count(setmealWrapper);
        if (setmealCount > 0){
            throw new CustomException("Current category is connected to existed setmeals!");
        }
        //正常删除分类
        super.removeById(id);
    }
}
