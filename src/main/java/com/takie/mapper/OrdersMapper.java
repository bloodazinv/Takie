/**
 * FileName: OrdersMapper
 * Author: jane
 * Date: 2022/7/30 11:12
 * Description:
 * Version:
 */
package com.takie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
