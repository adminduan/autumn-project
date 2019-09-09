//package com.white.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//
//@Configuration
//public class MvcInterceptorConfig extends WebMvcConfigurationSupport {
//
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/")
//                .excludePathPatterns("/index")
//                .excludePathPatterns("/*/page/**")
////                .excludePathPatterns("/static/**")
//                .excludePathPatterns("/pub/**")
//        ;
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
////                .addResourceLocations("classpath:/META-INF/resources/")
////                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/img/")
//                .addResourceLocations("classpath:/static/**")
//                .addResourceLocations("classpath:/public/");
//        super.addResourceHandlers(registry);
//    }
//}
