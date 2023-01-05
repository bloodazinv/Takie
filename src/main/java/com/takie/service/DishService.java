/**
 * FileName: DishService
 * Author: jane
 * Date: 2022/7/27 19:36
 * Description:
 * Version:
 */
package com.takie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.takie.config.dto.DishDto;
import com.takie.entity.Dish;

public interface DishService extends IService<Dish> {
    //add new dish with "dish" table and "dish_flavor" table
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
