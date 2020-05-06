package com.example.demo.config;

import com.example.demo.interceptor.JwtWithPassTokenInterceptor;
import com.example.demo.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(jwtWithPassTokenInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Bean
    public JwtWithPassTokenInterceptor jwtWithPassTokenInterceptor(){
        return new JwtWithPassTokenInterceptor();
    }
}
