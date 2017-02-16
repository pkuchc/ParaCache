package com.shch.paracache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) //定义的注解只对属性起作用
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParaCache {
	String key() default ""; //该注解只有一个参数key,且可以默认为空
   
}
