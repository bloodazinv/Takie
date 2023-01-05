/**
 * FileName: takieApplication
 * Author: jane
 * Date: 2022/7/15 15:11
 * Description:
 * Version:
 */

package com.takie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.swing.*;

@Slf4j //lombok service
@SpringBootApplication
@ServletComponentScan //使过滤器生效
@EnableTransactionManagement
public class takieApplication {
    public static void main(String[] args) {
        SpringApplication.run(takieApplication.class, args);
        log.info("project runs successfully"); //the log service provided by lombok
    }
}
