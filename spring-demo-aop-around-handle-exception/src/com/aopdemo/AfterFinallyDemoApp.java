package com.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {
	public static void main (String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
			
		// call method to find the accounts
		List<Account> theAccounts;
		
		try {
			// after advice runs if findAccount is true or false
			theAccounts = theAccountDAO.findAccounts(false);
		}catch(Exception ex) {
			System.out.println("\n\nMain Program... caught exception: " +ex);
		}
		// display the accounts
		System.out.println("\n\nMain Program: After Throwing DemoApp");
		System.out.println("----");
		
//		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		// close the context
		context.close();
	}
}
