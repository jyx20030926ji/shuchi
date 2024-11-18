package com.testvue.testvue.config;

import com.testvue.testvue.interceptor.MyIntercepyor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration

public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyIntercepyor myIntercepyor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercepyor).excludePathPatterns("/user/users/login","/admin/users/login","/user/books/page","/user/books/categories","/admin/users/upload","/admin/mails","/user/users/emailregister","/user/users/register").addPathPatterns("/**");
    }

}
