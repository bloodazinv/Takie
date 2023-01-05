/**
 * FileName: AddressBookMapper
 * Author: jane
 * Date: 2022/7/30 8:45
 * Description:
 * Version:
 */
package com.takie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.takie.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
