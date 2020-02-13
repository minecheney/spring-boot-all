package com.mine.filter.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: mich
 * @Date: 2020/2/10 21:22
 */

/**
 * 过滤器方式一:
 * @Component注解让TimeFilter成为Spring上下文中的一个Bean，
 * @WebFilter注解的urlPatterns属性配置了哪些请求可以进入该过滤器，/*表示所有请求。
 */
//@Component
//@WebFilter("/*") //表示所有请求都过滤
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器开始执行");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        System.out.println(String.format("执行%sms",end-start));
        System.out.println("过滤器结束执行");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁!");
    }
}
