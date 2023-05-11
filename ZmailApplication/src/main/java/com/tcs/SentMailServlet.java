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

@WebServlet("/mailsenderservlet")
public class SentMailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 PrintWriter pw = resp.getWriter();
		 resp.setContentType("text/html");
		 req.getRequestDispatcher("header.html").include(req, resp);
		 
		 HttpSession hs = req.getSession(false);
		 if(hs==null)
			 resp.sendRedirect("Login.html");
		 else {
			 String zmail = (String)hs.getAttribute("zmail");
			 pw.print("<h1 style='text-align:center'>hi "+zmail+"</h1>");
			 req.getRequestDispatcher("link.html").include(req, resp);
			 pw.print("<h1 style='text-align:center'>these are the sent mails</h1>");
			
			 try {
				 Connection con = DBConnection.getconnection();
				PreparedStatement ps = con.prepareStatement("select * from composemessage where sender=? and trash=? order by id desc");
				 ps.setString(1, zmail);
				 ps.setString(2, "no");
				 ResultSet rs = ps.executeQuery();
				 pw.print("<center><table border='2'>");
				 pw.print("<tr><td>To</td><td>Subject</td></tr>");
				 while(rs.next()) {
					 pw.print("<tr><td>"+rs.getString("receiver")+"</td><td><a href='ViewMailServlet?id="+rs.getString("id")+"'>"+rs.getString("subject")+"</a></td></tr>");
				 }
				 pw.print("</table></center>");
				}
			 catch(Exception e) {
				 pw.print(e);
			 }
		 	}
		 req.getRequestDispatcher("footer.html").include(req, resp);
		 
	} 
	
	
}
