package com.aopdemo.aspect;

import java.util.List;

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
	
	@AfterReturning(pointcut = "execution(* com.aopdemo.dao.AccountDAO.findAccounts(..))", returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {
		System.out.println("======> @After Returning Advice");
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " +methodSig);
		
		Object [] args = theJoinPoint.getArgs();
		
		for(Object temps :args) {
			System.out.println(temps);
		}
		
		// print out result in method call
		System.out.println("\n===>>> Result is: " +result);
		
		// post-process i.e modify data
		
		//convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		//print modified result
		System.out.println("\n===>>> Result is: " +result);
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through accounts
		for (Account tempAccount : result) {
			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			
			//update the name on the account
			tempAccount.setName(theUpperName);
		}
		
		
		
		
	}

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
	
	
}
