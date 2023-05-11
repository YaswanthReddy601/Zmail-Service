package com.tcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginVerf {

	public static boolean validate(String zmail,String password) throws SQLException, ClassNotFoundException {
		
		boolean boo = false;
		Connection con = DBConnection.getconnection();
		PreparedStatement ps = con.prepareStatement("select * from zmail_register where zmail=? and password=?");
		ps.setString(1, zmail);
		ps.setString(2, password);
		 
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			boo=true;
		}
		
		return boo;
	}
	
}
