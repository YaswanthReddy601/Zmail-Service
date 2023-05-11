package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutservlet")
public class LogoutServlet extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			req.getRequestDispatcher("header.html").include(req, res);
			req.getRequestDispatcher("link.html").include(req, res);

			HttpSession hs = req.getSession(false);
			if(hs==null)
				res.sendRedirect("Login.html");
			else {
				pw.print("<center>");
				pw.print("<form action='logoutservletservice' method='get'>");
				pw.print("<input type='submit' value='Logout' >");
				pw.print("</form>");
				pw.print("</center>");
			}
			req.getRequestDispatcher("footer.html").include(req, res);
	}
	
}
