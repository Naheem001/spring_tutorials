package com.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aopdemo.Account;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
	//With fully qualified class name to Aop Expressions
	@Before("com.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n=====>>> Performing @Before Advice");
	
		//display method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " +methodSig);
		
		//display method arguments
		
			// get args
		Object[] args = theJoinPoint.getArgs();
		
			// loop thru args
		for (Object tempArg : args) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				System.out.println("Account Name: "+theAccount.getName());
				System.out.println("Account Level: "+theAccount.getLevel());
			}
		}
		
	}
	
	@AfterReturning(pointcut = "execution(* com.aopdemo.dao.AccountDAO.findAccounts(..))", returning="results")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("======> @After Returning Advice");
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " +methodSig);
		
		Object [] args = theJoinPoint.getArgs();
		
		for(Object temps :args) {
			System.out.println(temps);
		}
	}
	
	@AfterThrowing(pointcut = "execution(* com.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("======> Executing @After Throwing Advice on method: " +method);
		
		// logging the exception
		System.out.println("\n====> The exception is: " +theExc);
	} 
	
}
