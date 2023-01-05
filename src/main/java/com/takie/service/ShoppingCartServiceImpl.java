/**
 * FileName: ShoppingCartServiceImpl
 * Author: jane
 * Date: 2022/7/30 10:29
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.entity.ShoppingCart;
import com.takie.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
