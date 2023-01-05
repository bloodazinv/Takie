/**
 * FileName: UserServiceImpl
 * Author: jane
 * Date: 2022/7/29 17:23
 * Description:
 * Version:
 */

package com.takie.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.takie.entity.User;
import com.takie.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
}
