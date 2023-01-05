/**
 * FileName: SetmealDishServiceImpl
 * Author: jane
 * Date: 2022/7/28 20:38
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.entity.Setmeal;
import com.takie.entity.SetmealDish;
import com.takie.mapper.SetmealDishMapper;
import org.springframework.stereotype.Service;

@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
