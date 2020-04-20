package com.aopdemo.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	@Pointcut("execution(* com.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	
	@Before("forDaoPackage()")
	public void beforeAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on addAccount with argument or without argument");
	}
	
	@AfterReturning("forDaoPackage()")
	public void AfterAdvice() {
		System.out.println("\n=====>>> Executing @AfterReturning advice on addAccount with argument or without argument");
	}
	
}
