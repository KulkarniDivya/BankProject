package com.bank.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Transaction;
import com.bank.model.User;
import com.bank.utility.Database;



public class LoginImpl implements Login{

	@Override
	public User login() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Account no and Password");
		long Ano = scan.nextLong();
		String password = scan.next();	User u = null;
		try {
			Database db = new Database();
			Connection con = db.getConnection();
			int c=0;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from user");
			while(rs.next())
			{
				if(rs.getLong(2) == Ano && rs.getString(4).equals(password))
				{
					c++;
					Transaction o = new TransactionImpl();
					u = new User();
					u.setName(rs.getString(1));
					u.setAadharNo(rs.getLong(3));
					u.setAccountNo(rs.getLong(2));
					u.setPassword(rs.getString(4));
					u.setBalance(rs.getDouble(5));
				}
			}
			if(c == 0)
			{
				System.out.println("Wrong Credentials");
				Login l = new LoginImpl();
				l.login();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

}
