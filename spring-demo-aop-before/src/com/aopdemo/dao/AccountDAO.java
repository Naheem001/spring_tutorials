package com.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.aopdemo.Account;

@Component
public class AccountDAO {
	
	public void addAccount(Account theAccount) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT FROM THE ACCOUNT CLASS");
	}

	public void addAccount(String name) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public void addSillyAccount(String name, int id, long accountNo) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING A SILLY ACCOUNT");
	}
}
