package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Register;
import com.bank.model.User;
import com.bank.utility.Database;


public class RegisterImpl implements Register {
	User u = new User();
	@Override
	public void register() {
		// TODO Auto-generated method stub
	
		setInfo();
		try {
			Database db = new Database();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into user values(?,?,?,?,?)");
			
			ps.setString(1, u.getName());
			ps.setLong(2, u.getAccountNo());
			ps.setLong(3, u.getAadharNo());
			ps.setString(4, u.getPassword());
			ps.setDouble(5, u.getBalance());
			int i = 0;
			try {
				i = ps.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			if (i == 1) {
				System.out.println("Registered Successfully");
				System.out.println("Your Account No:"+u.getAccountNo());
				Login l = new LoginImpl();
				l.login();
			} else {
				System.out.println("Error in registering");
				register();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void setInfo() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		long n = 10000 + new Random().nextInt(90000);
		System.out.println("Enter Name" );
		u.setName(sc.next());
		System.out.println("Enter Aadhar No" );
		u.setAadharNo(sc.nextLong());
		u.setAccountNo(n);
		u.setBalance(0);
		System.out.println("Enter Password" );
		u.setPassword(sc.next());
	}

}
