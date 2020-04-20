package com.aopdemo.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	// Pointcut declaration for all methods in the package
	@Pointcut("execution(* com.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	// Pointcut declaration for all getter methods in the package
	@Pointcut("execution(* com.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	// Pointcut declaration for all getter methods in the package
	@Pointcut("execution(* com.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	//Pointcut declaration include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
	
	@Before("forDaoPackageNoGetterSetter()")
	public void beforeAdvice() {
		System.out.println("\n=====>>> Executing BEFORE ADVICE");
	}
	
	@AfterReturning("forDaoPackageNoGetterSetter()")
	public void AfterAdvice() {
		System.out.println("\n=====>>> Executing AFTER ADVICE");
	}
	
}
