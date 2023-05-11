package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("header.html").include(req, res);
		req.getRequestDispatcher("link.html").include(req, res);
		
		String name= req.getParameter("name");
		String zmail = req.getParameter("zmail");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String date = req.getParameter("dob");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String contact = req.getParameter("contact");
		
		
		int i = 0;
		try {
			Connection con = DBConnection.getconnection();
			PreparedStatement ps = con.prepareStatement("insert into zmail_register values(?,?,?,?,?,?,?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, zmail);
			ps.setString(3, password);
			ps.setString(4, gender);
			ps.setString(5, date);
			ps.setString(6, address);
			ps.setString(7, city);
			ps.setString(8, state);
			ps.setString(9, country);
			ps.setString(10, contact);
			
			i = ps.executeUpdate();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		if(i>0) {
			pw.print("<h1> you are sucessfully registered</h1>");
			req.getRequestDispatcher("Login.html").include(req, res);
		}
		
		req.getRequestDispatcher("footer.html").include(req, res);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
