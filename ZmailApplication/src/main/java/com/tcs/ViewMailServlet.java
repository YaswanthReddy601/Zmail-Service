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

@WebServlet("/ViewMailServlet")
public class ViewMailServlet extends HttpServlet {
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			req.getRequestDispatcher("header.html").include(req, res);
			
			HttpSession hs = req.getSession(false);
			if(hs==null) {
				res.sendRedirect("Login.html");
			}
			else {
				String zmail = (String)hs.getAttribute("zmail");
//				pw.println("<h3 style='text-align:center'>Hi "+zmail+"</h3>");
				req.getRequestDispatcher("link.html").include(req, res);
				
				int id = Integer.parseInt(req.getParameter("id"));
				
				try {
					Connection con = DBConnection.getconnection();
					PreparedStatement ps = con.prepareStatement("select * from composemessage where id=?");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						pw.print("<h3> Subject:: "+rs.getString("subject")+"</h3>");
						pw.print("<h3> Message:: "+rs.getString("message")+"<br> By:: "+rs.getString("sender")+"</h3>");
						pw.print("<h2><a href='DeleteMailServlet?id="+rs.getString(1)+"'>Delete Mail</a></h2>");
						}
					else {
						pw.print("<h1>No mails with id "+id+"</h1>");
					}
					}
				catch(Exception e) {
					pw.println(e);
				}
				req.getRequestDispatcher("footer.html").include(req, res);
				
			}
				
			
		}
	
	
	
}
