package com.testvue.testvue.interceptor;

import com.testvue.testvue.Utils.JwtUtils;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.menu.CodeMessageMenu;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import java.time.LocalTime;


@Component
public class MyIntercepyor implements HandlerInterceptor {


    JwtUtils jwtUtils=new JwtUtils();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equals(request.getMethod())) {
            return true;  // 直接通过，不进行拦截
        }

        System.out.print("开始拦截"+LocalTime.now());




        String token = request.getHeader("authorization");


        if (token == null) {
            throw new AccountNoExistException(CodeMessageMenu.USER_NOT_LEGALLY);
        }
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);  // 从第8个字符开始截取，即去掉 "Bearer " 前缀
        }
     try{
           Claims claims = jwtUtils.decoding(token);
           Object userIdObj = claims.get("userId");

           BaseCont.set((Integer) userIdObj);


         return true;
     }

     catch(Exception e)
        {
          System.out.print("------------------------------------------");
        }


     return true;
    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      // System.out.print("开始处理拦截器"+ LocalTime.now());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
     //   System.out.print("拦截完后的逻辑"+LocalTime.now());
    }
}
