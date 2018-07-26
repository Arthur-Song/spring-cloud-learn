package com.arthursong.demo.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.cloud.netflix.feign.support.SpringMvcContract;

import feign.MethodMetadata;

/**
 * Created by Administrator on 2018/7/25.
 */
public class MyContract extends SpringMvcContract {

	/**
	 * 用于处理方法级的注解
	 */
	protected void processAnnotationOnMethod(MethodMetadata data,
			Annotation annotation, Method method) {
		// 调用父类的方法，吗时支持 @RequestMapping 注解
		super.processAnnotationOnMethod(data, annotation, method);	
		// 是MyUrl注解才进行处理
		if(MyUrl.class.isInstance(annotation)) {
			// 获取注解的实例
			MyUrl myUrlAnn = method.getAnnotation(MyUrl.class);
			// 获取配置的HTTP方法
			String httpMethod = myUrlAnn.method();
			// 获取服务的url
			String url = myUrlAnn.url();
			// 将值设置到模板中
			data.template().method(httpMethod);
			data.template().append(url);
		}
	}
}
