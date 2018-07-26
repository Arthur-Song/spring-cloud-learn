package com.arthursong.demo.config;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 自定义的注解
 *
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface MyUrl {

	// 定义url与method属性
	String url();
	String method();
}
