/**
 * FileName: GlobalExceptionHandler
 * Author: jane
 * Date: 2022/7/27 9:09
 * Description:
 * Version:
 */

package com.takie.common;

import com.takie.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})//添加了restcontroller注解的controller类就可以被拦截到
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//表示这个handler负责处理传入的异常
    public R<String> exceptionHandle(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String[] s = ex.getMessage().split(" ");
            String msg = s[2] + "has existed";
            return R.error(msg);
        }
        return R.error("unknown exception!");
    }

    /**
     * handle custom exception
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)//表示这个handler负责处理传入的异常
    public R<String> exceptionHandle(CustomException ex){
        log.error(ex.getMessage());

        return R.error(ex.getMessage());
    }
}
