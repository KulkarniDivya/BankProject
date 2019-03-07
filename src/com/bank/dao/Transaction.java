package com.bank.dao;

import com.bank.model.User;

public interface Transaction {
	public void deposit(int d, double balance,long ano);
	public void withdraw(int w, double balance,long ano);
	public void option(User u,int n);
	public void updateBalance(double d,long ano);
}
