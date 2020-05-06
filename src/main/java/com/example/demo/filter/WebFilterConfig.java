package com.example.demo.filter;

import com.example.demo.filter.MyFilter;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器基于Servlet实现
 */
//@Configuration
public class WebFilterConfig {
    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    /**
     * 全局攔截
     * @return
     */
    @Bean
    public FilterRegistrationBean<MyFilter> testFilterRegistration(){
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<MyFilter>();
        registrationBean.setFilter(new MyFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("paramName","paramValue");
        registrationBean.setName("MyFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
