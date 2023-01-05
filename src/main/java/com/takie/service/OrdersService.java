/**
 * FileName: OrdersService
 * Author: jane
 * Date: 2022/7/30 11:13
 * Description:
 * Version:
 */
package com.takie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.takie.entity.Orders;

public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
