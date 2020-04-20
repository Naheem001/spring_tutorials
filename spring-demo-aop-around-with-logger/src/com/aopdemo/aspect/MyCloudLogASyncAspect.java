package com.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyCloudLogASyncAspect {
	//With fully qualified class name to Aop Expressions
	@Before("com.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void logToCloudASync() {
		System.out.println("\n=====>>> Logging to Cloud in async fashion");
	}
}
