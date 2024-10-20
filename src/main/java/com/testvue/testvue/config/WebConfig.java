package com.testvue.testvue.config;


import com.testvue.testvue.interceptor.MyIntercepyor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepyor()).addPathPatterns("/**").excludePathPatterns("/admin/register","/admin/login");
    }
}
