package com.white.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MvcInterceptorConfigCopy extends WebMvcConfigurationSupport {


    @Autowired
    private LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/layui/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/menu/**")
                .excludePathPatterns("/role/**")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/index")
                .excludePathPatterns("/*/page/**")
                .excludePathPatterns("/pub/**");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
