/**
 * FileName: DishMapper
 * Author: jane
 * Date: 2022/7/27 19:35
 * Description:
 * Version:
 */
package com.takie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
