package com.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	public void addAccount() {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP");
	}
	
	public void addBubbleAccount() {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING A BUBBLE ACCOUNT");
	}
}