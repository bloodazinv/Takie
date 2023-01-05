/**
 * FileName: ShoppingCartMapper
 * Author: jane
 * Date: 2022/7/30 10:28
 * Description:
 * Version:
 */
package com.takie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
