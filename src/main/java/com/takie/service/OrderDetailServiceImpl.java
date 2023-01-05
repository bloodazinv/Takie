/**
 * FileName: OrderDetailServiceImpl
 * Author: jane
 * Date: 2022/7/30 11:15
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.entity.OrderDetail;
import com.takie.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
