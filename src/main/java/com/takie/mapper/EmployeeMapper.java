/**
 * FileName: EmployeeMapper
 * Author: jane
 * Date: 2022/7/26 20:26
 * Description:
 * Version:
 */
package com.takie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
