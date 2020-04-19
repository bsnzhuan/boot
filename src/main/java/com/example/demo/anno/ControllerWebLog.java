package com.example.demo.anno;

import java.lang.annotation.*;

/**
 * 控制层注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControllerWebLog {
    String name();
    boolean intoDb() default false;
}
