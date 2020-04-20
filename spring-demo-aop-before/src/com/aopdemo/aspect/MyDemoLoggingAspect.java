package com.aopdemo.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	@Before("execution(* addAccount(com.aopdemo.Account))")
	public void beforeAddAccountAdvice3() {
		System.out.println("\n=====>>> Executing @Before advice on addAccount with argument as class name");
	}
	//@Before Advice for all classes and all methods in com.aopdemo package
	@Before("execution(* com.aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice4() {
		System.out.println("\n=====>>> Executing @Before advice for all classes and all methods in com.aopdemo package");
	}
	
	@Before("execution(void addAccount(*))")
	public void beforeAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on addAccount with argument");
	}
	
	@Before("execution(public void addSillyAccount(..))")
	public void beforeAddSillyAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on addSillyAccount with more than one argument");
	}
	
	@Before("execution(* add*(..))")
	public void beforeAddAccountAdvice2() {
		System.out.println("\n=====>>> Executing @Before advice on addAccount with argument or without argument");
	}
	
	@Before("execution(public void add*())")
	public void afterAddAccountAdvice() {
		System.out.println("\n=====>>> Executing @Before advice on all addAccount");
	}
}
