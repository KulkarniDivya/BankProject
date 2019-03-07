package com.bank.driver;

import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Register;
import com.bank.dao.Transaction;
import com.bank.daoimpl.LoginImpl;
import com.bank.daoimpl.RegisterImpl;
import com.bank.daoimpl.TransactionImpl;
import com.bank.model.User;


public class MainClass {
	 static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			System.out.println("Enter '0' to Register and '1' to Login");
			int n = scan.nextInt();
			switch(n)
			{
			case 0: Register r = new RegisterImpl();
				r.register();
				break;
			case 1: Login l = new LoginImpl();
					options(l.login());
				break;
			}
		}

	private static void options(User u) {
		// TODO Auto-generated method stub
		Transaction o = new TransactionImpl();
		int n=0;
		while(n!=3)
		{
			System.out.println("Enter '0' to Withdraw , '1' to Deposit and '2' to check balance. Press '3' to exit");
			n = scan.nextInt();
			o.option(u,n);
		}
		
	}
}
