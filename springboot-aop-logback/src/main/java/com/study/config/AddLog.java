package com.study.config;

import java.lang.annotation.*;

/**
 * 添加日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface AddLog {

	String desc() default "";

	//表达式获取接口参数
	String interfaceParam() default "";
}