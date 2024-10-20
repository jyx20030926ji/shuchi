package com.testvue.testvue.annotation;

import com.testvue.testvue.menu.AopLogMenu;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeFiledAnnotation {

    AopLogMenu value();

}
