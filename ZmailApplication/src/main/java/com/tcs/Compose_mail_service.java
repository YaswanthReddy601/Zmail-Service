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

@WebServlet("/composemailservice")
public class Compose_mail_service extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("header.html");
		
		String to = req.getParameter("to");
		String subject = req.getParameter("subject");
		String message = req.getParameter("message");
		String from = (String)req.getSession(false).getAttribute("zmail");
		
		int a = 0;
		
		try {
		Connection con = DBConnection.getconnection();
		PreparedStatement ps = con.prepareStatement("insert into composemessage(sender,receiver,subject,message,trash) values(?,?,?,?,?)");
		ps.setString(1, from);
		ps.setString(2, to);
		ps.setString(3, subject);
		ps.setString(4, message);
		ps.setString(5, "no");

		a = ps.executeUpdate();
		}
		catch(Exception e) {
			pw.print(e);
		}
		if(a>0) {
			req.getSession().setAttribute("msg", "mail successfully sent");
			req.getRequestDispatcher("composemail").forward(req, res);
		}
		
		req.getRequestDispatcher("footer.html").include(req, res);
		pw.close();
		}
	
}
