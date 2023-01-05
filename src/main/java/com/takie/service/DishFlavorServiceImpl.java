/**
 * FileName: DishFlavorServiceImpl
 * Author: jane
 * Date: 2022/7/28 18:50
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.entity.DishFlavor;
import com.takie.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
