/**
 * FileName: UserController
 * Author: jane
 * Date: 2022/7/29 17:28
 * Description:
 * Version:
 */

package com.takie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.takie.common.R;
import com.takie.service.UserService;
import com.takie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.takie.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //*****obtain phone number
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            //*****create random 4digit code
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}", code);
            //*****use message send api
            //SMSUtils.sendMessage("takie","",phone,code);
            //*****save code in session
            session.setAttribute(phone,code);
            return R.success("phone validation code sent!");
        }
        return R.error("fail to send validation code!");

    }

    @PostMapping("/login")
    public R<User> sendMsg(@RequestBody Map map, HttpSession session){
        //obtain phone number
        String phone = map.get("phone").toString();
        //obtain validation code
        String code = map.get("code").toString();
        //the validation code saved in session
        String oriCode = (String)session.getAttribute(phone);
        //check code
        if(oriCode != null && oriCode.equals(code)){
            //check matches

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null){
                //new user can automatically register
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user", user.getId());
            return R.success(user);
        }
        return R.error("login failed!");
    }
}
