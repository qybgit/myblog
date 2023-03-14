package com.example.config;

import com.example.util.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebmvcConfig implements WebMvcConfigurer {
    @Resource
    LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置跨域请求
        registry.addMapping("/**")//允许跨域请求地址
                .allowedOriginPatterns("*");//允许跨域请求的域名
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/article/add")
                .addPathPatterns("/comment/add");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
