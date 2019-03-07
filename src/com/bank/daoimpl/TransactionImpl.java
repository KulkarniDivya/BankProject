package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.dao.Transaction;
import com.bank.model.User;
import com.bank.utility.Database;

public class TransactionImpl implements Transaction{
	Database db = new Database();
	Connection con = db.getConnection();
	@Override
	public void deposit(int depositAmt, double balance, long ano) {
		// TODO Auto-generated method stub
		balance = balance + depositAmt;
		updateBalance(balance,ano);
        System.out.println("Your Money has been successfully deposited");
        System.out.println("Ur balance:"+balance);
        
	}

	@Override
	public void withdraw(int withdrawAmt, double balance, long ano) {
		// TODO Auto-generated method stub
		 if(balance >= withdrawAmt)
         {
             balance = balance - withdrawAmt;
             updateBalance(balance,ano);
             System.out.println("Please collect your money");
             System.out.println("Ur balance:"+balance);
         }
         else
         {
             System.out.println("Insufficient Balance");
         }
	}

	@Override
	public void option(User s,int n) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		switch(n)
		{
			case 0:System.out.println("Enter the amount u want to withdraw");
					int withdrawAmt = scan.nextInt();
					double bal1 = 0;
					try {
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from user where accountNo="+s.getAccountNo());
						while(rs.next())
						 bal1 = rs.getDouble(5);
						withdraw(withdrawAmt,bal1,s.getAccountNo());
					} catch (SQLException e) {
				// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
			case 1:System.out.println("Enter the amount u want to deposit");
					int depositAmt = scan.nextInt();
					double bal = 0;
					try {
							Statement st = con.createStatement();
							ResultSet rs = st.executeQuery("select * from user where accountNo="+s.getAccountNo());
							while(rs.next())
							 bal = rs.getDouble(5);
							deposit(depositAmt,bal,s.getAccountNo());
						} catch (SQLException e) {
					// TODO Auto-generated catch block
							e.printStackTrace();
						}
					break;
			case 2: try {
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from user where accountNo="+s.getAccountNo());
						while(rs.next())
							System.out.println("Ur balance:"+rs.getDouble(5));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				break;
			case 3: System.out.println("Thank You");
		}
	}


	@Override
	public void updateBalance(double balance, long accountNo) {
		// TODO Auto-generated method stub
		

			PreparedStatement ps;
			try {
				ps = con.prepareStatement("update user set balance = ? where accountNo = ?");
				ps.setDouble(1, balance);
				ps.setLong(2, accountNo);
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
