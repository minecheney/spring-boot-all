package com.mine.filter.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器测试
 *
 *   要使拦截器在Spring Boot中生效，还需要如下两步配置：
 *
 * 1.在拦截器类上加入@Component注解；
 *
 * 2.在WebConfig中通过InterceptorRegistry注册过滤器:
 *
 * @Author: mich
 * @Date: 2020/2/10 21:58
 */
@Component
public class TestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截之前");
        request.setAttribute("start", System.currentTimeMillis());
        System.out.println(handler.getClass().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("开始处理拦截");
        Long start = (Long) request.getAttribute("start");
        System.out.println("【拦截器】耗时 " + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理拦截之后");
        Long start = (Long) request.getAttribute("start");
        System.out.println("【拦截器】耗时 " + (System.currentTimeMillis() - start));
        System.out.println("异常信息 " + ex);
    }
}
