package com.mine.filter.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mich
 * @Date: 2020/2/10 21:53
 */

/**
 * 过滤器方式二: 通过FilterRegistrationBean来注册过滤器
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<TestFilter> timeFilter() {
        FilterRegistrationBean<TestFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        TestFilter timeFilter = new TestFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }
}
