/**
 * FileName: LoginCheckFilter
 * Author: jane
 * Date: 2022/7/27 7:53
 * Description: 检查用户是否已经完成登录
 * Version:
 */

package com.takie.filter;

import com.alibaba.druid.pool.ha.selector.StickyRandomDataSourceSelector;
import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import com.takie.common.BaseContext;
import com.takie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*") //过滤器注解
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取本次请求的url
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{//不需要检查的，直接放行的路径
              "/employee/login",
              "/employee/logout",
              "/backend/**",
              "/front/**",
                "/common/**",
                "/user/sendMsg",//mobile app send message
                "/user/login"//mobile app login
        };
        log.info("**********intercepted request: {}" + requestURI);
        //2.判断本次请求是否需要处理
        boolean check = check(urls,requestURI);
        //3.如果不需要处理，直接放行
        if(check){
            filterChain.doFilter(request, response);
            return;
        }
        //4-1.判断employee登陆状态，登录 -》 放行
        if(request.getSession().getAttribute("employee") != null){
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);
            return;
        }
        //4-2.判断user登陆状态，登录 -》 放行
        if(request.getSession().getAttribute("user") != null){
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }
        //5.未登录， 返回结果,通过输出流方式向客户端响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("**********failed intercepted request: {}" + requestURI);




    }

    public boolean check(String[] urls, String requestURL){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,requestURL);
            if(match){
                return true;
            }
        }
        return false;
    }


}
