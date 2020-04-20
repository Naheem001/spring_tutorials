package com.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.aopdemo.Account;
import com.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
	private static Logger myLogger = Logger.getLogger(MyDemoLoggingAspect.class.getName());
	
	
	@Around("execution(* com.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		//print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n===> Executing @Around Advice on method: " +method);
		
		// get begin time stamp
		long begin = System.currentTimeMillis();
		
		//now, let's execute the method
		Object result = null;
				
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
			//rethrow  exception
			throw e;
			// give user a custom message
//			result = "Major accident! But no worries, your private AOP helicopter is on the way!";
		}
		
		//get end timestamp
		long end = System.currentTimeMillis();
		
		//compute duration and display
		long duration = end - begin;
		myLogger.info("\n===> Duration: " +duration/1000.0 +" seconds");
		
		return result;
	}
	
	// After advice executes if method succeeds or fails due to exception
	@After("execution(* com.aopdemo.dao.AccountDAO.findAccounts(..))")
		public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		// print method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("======> Executing @After (finally) Advice on method: " +method);
				
	}
	
	@AfterThrowing(pointcut = "execution(* com.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="theExc")
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("======> Executing @After Throwing Advice on method: " +method);
		
		// logging the exception
		myLogger.info("\n====> The exception is: " +theExc);
	} 
	
	@AfterReturning(pointcut = "execution(* com.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
		myLogger.info("======> @After Returning Advice");
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: " +methodSig);
		
		Object [] args = theJoinPoint.getArgs();
		
		for(Object temps :args) {
			myLogger.info(temps.toString());
		}
		
		// print out result in method call
		myLogger.info("\n===>>> Result is: " +result);
	}
	
	
	//With fully qualified class name to Aop Expressions
	@Before("com.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n=====>>> Performing @Before Advice");
	
		//display method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("Method: " +methodSig);
		
		//display method arguments
		
			// get args
		Object[] args = theJoinPoint.getArgs();
		
			// loop thru args
		for (Object tempArg : args) {
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				myLogger.info("Account Name: "+theAccount.getName());
				myLogger.info("Account Level: "+theAccount.getLevel());
			}
		}
		
	}
}
