package com.arunesh.irctc.irctc_backend.config;

import com.arunesh.irctc.irctc_backend.interceptors.MyCustomInterceptors;
import com.arunesh.irctc.irctc_backend.interceptors.TimeLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProjectConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyCustomInterceptors myCustomInterceptors;

    @Autowired
    private TimeLoggerInterceptor timeLoggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    registry.addInterceptor(timeLoggerInterceptor).addPathPatterns("/trains/**","/stations/**")
            .excludePathPatterns("/trains/list");
    }
}
