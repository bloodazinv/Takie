/**
 * FileName: OrdersController
 * Author: jane
 * Date: 2022/7/30 11:16
 * Description:
 * Version:
 */

package com.takie.controller;

import com.takie.common.R;
import com.takie.entity.Orders;
import com.takie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    //user order
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        ordersService.submit(orders);
        return R.success("user submitted");
    }
}
