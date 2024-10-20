package com.testvue.testvue.annotation;

import com.testvue.testvue.menu.AopLogMenu;

import java.lang.annotation.*;
import java.util.SplittableRandom;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String operation() default "";

    AopLogMenu aopLogMenu() default AopLogMenu.OTHER;

}
