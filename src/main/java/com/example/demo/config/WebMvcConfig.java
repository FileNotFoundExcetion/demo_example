package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    HandlerInterceptor1 handlerInterceptor1;

    @Resource
    HandlerInterceptor2 handlerInterceptor2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor1).addPathPatterns("/**");
        registry.addInterceptor(handlerInterceptor2).addPathPatterns("/**");
    }

}
