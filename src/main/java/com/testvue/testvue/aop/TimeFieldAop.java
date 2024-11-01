package com.testvue.testvue.aop;


import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.menu.AopLogMenu;
import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;


@Aspect
@Component
@Order(1)
public class TimeFieldAop {
    @Pointcut("@annotation(com.testvue.testvue.annotation.TimeFiledAnnotation)")
    public void po()
    {}





    @Before(value = "po()")

    public void beforeCreateTimeField(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        TimeFiledAnnotation annotation = method.getAnnotation(TimeFiledAnnotation.class);





        if (args.length > 0 && args[0]!=null){

            Object obj= args[0];
            LocalDateTime createTime=LocalDateTime.now();

            if(annotation.value()== AopLogMenu.UPDATE)
            {
                invokeOriginalMethod(obj,createTime,"setUpdateTime");
            }
            if(annotation.value()==AopLogMenu.INSERT)
            {
                invokeOriginalMethod(obj,createTime,"setCreateTime");
                invokeOriginalMethod(obj,createTime,"setUpdateTime");
            }
            if(annotation.value()==AopLogMenu.DELETE)
            {
                invokeOriginalMethod(obj,createTime,"setUpdateTime");
            }




        }




    }

    private void invokeOriginalMethod(Object object, LocalDateTime time, String methodName)
    {
        try {

            Method method = object.getClass().getDeclaredMethod(methodName, LocalDateTime.class);
            method.invoke(object,time);
        }
        catch(NoSuchMethodException  | IllegalAccessException  | InvocationTargetException e ) {

            System.out.println(object+":::::::::::::::::::::::::::::好像没有这个方法::::::::::::::::::");
    }
    }



}
