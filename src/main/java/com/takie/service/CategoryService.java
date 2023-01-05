/**
 * FileName: CategoryService
 * Author: jane
 * Date: 2022/7/27 19:11
 * Description:
 * Version:
 */
package com.takie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.takie.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
