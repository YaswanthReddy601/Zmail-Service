package com.tcs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getconnection(){
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/me", "root", "root");
		}
		catch(Exception e) {
		System.out.println(e);
		}
		return con;
	}
	
}
