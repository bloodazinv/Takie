/**
 * FileName: AddressBookServiceImpl
 * Author: jane
 * Date: 2022/7/30 8:47
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.entity.AddressBook;
import com.takie.mapper.AddressBookMapper;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
