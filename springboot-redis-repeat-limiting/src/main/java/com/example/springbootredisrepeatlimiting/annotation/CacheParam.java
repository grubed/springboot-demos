package com.example.springbootredisrepeatlimiting.annotation;

import java.lang.annotation.*;

/**
 * 锁的参数
 *
 * @author Una Ma
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";

}
