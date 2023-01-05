/**
 * FileName: SetmealService
 * Author: jane
 * Date: 2022/7/27 19:38
 * Description:
 * Version:
 */
package com.takie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.takie.config.dto.SetmealDto;
import com.takie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    public SetmealDto getByIdWithDish(Long id);

    public void saveWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);

    public void updateWithDish(SetmealDto setmealDto);
}
