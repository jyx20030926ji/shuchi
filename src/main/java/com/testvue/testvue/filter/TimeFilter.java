package com.testvue.testvue.filter;

import jakarta.servlet.*;

import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.time.LocalTime;


@Slf4j
@WebFilter("/*")
@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LocalTime start=LocalTime.now();
    //    log.info("过滤开始{}",start);
        filterChain.doFilter(servletRequest,servletResponse);

        LocalTime end=LocalTime.now();
      //  log.info("过滤结束{}",end);


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
