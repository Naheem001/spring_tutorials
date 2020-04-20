package com.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aopdemo.dao.AccountDAO;
import com.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//creating an account object
		Account myAccount = new Account();
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		
		
		//call the business method
		theAccountDAO.addAccount("john");
		theAccountDAO.addAccount(myAccount);
		System.out.println();
		System.out.println();
		System.out.println();
		theAccountDAO.addSillyAccount("john", 12, 013654);
		System.out.println();
		System.out.println();
		System.out.println();
		theMembershipDAO.addAccount();
		theMembershipDAO.addBubbleAccount();
		
		// close the context
		context.close();
	}

}
