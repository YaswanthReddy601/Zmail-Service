package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/inboxservlet")
public class InboxServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		
		req.getRequestDispatcher("header.html").include(req, resp);
		                                
		HttpSession hs = req.getSession(false);
		if(hs ==null) {
			resp.sendRedirect("Login.html");
		}
		else {
			String zmail = (String) hs.getAttribute("zmail");
			pw.println("<h1 style='text-align:center; color:green'>hi "+zmail+"</h1>");
			req.getRequestDispatcher("link.html").include(req, resp);
			pw.println("<h3 style='text-align:center;background-color:red;color:white'>Inbox</h3>");
			
			String message = (String)req.getAttribute("message");
			if(message!=null)
				pw.print("<h3>"+message+"</h3>");
			
			try {
				Connection con = DBConnection.getconnection();
				PreparedStatement ps = con.prepareStatement("select * from composemessage where sender=? and trash='no' order by id desc");
				ps.setString(1, zmail);
				ResultSet rs = ps.executeQuery();
				pw.print("<center><table style='background-color:pink'><td>receiver</td><td>subject</td></tr>");
				while(rs.next()) {
				pw.print("<tr><td>"+ rs.getString("receiver")+"</td><td><a href='ViewMailServlet?id="+rs.getString(1)+"'>"+rs.getString("subject")+"</a></td></tr>");
				}
				pw.print("</table></center>");
			}
			catch(Exception e) {
				pw.print(e);
			}
			
		}
			
		
		
		
	}
	
}
