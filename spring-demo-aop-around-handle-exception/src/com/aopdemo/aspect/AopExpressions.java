package com.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

	// Pointcut declaration for all methods in the package
		@Pointcut("execution(* com.aopdemo.dao.*.*(..))")
		public void forDaoPackage() {}
		
		// Pointcut declaration for all getter methods in the package
		@Pointcut("execution(* com.aopdemo.dao.*.get*(..))")
		public void getter() {}
		
		// Pointcut declaration for all getter methods in the package
		@Pointcut("execution(* com.aopdemo.dao.*.set*(..))")
		public void setter() {}
		
		//Pointcut declaration include package ... exclude getter/setter
		@Pointcut("forDaoPackage() && !(getter() || setter())")
		public void forDaoPackageNoGetterSetter() {}
}
