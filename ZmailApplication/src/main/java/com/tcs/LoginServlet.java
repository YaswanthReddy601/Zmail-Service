package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		req.getRequestDispatcher("header.html").include(req, res);
		req.getRequestDispatcher("link.html").include(req, res);
		
		HttpSession hs= req.getSession(false);
		if(hs==null) {
			req.getRequestDispatcher("Login.html");
		}
		else {
			pw.print("Please logout from this account to login another account");
		}
		
		String zmail = req.getParameter("zmail");
		String password = req.getParameter("password");
		
		try {
			if(LoginVerf.validate(zmail,password)) {
				req.getSession().setAttribute("zmail", zmail);
				req.getSession().setAttribute("login", "true");
				res.sendRedirect("inboxservlet");
				
			}
			else {
				pw.print("<h1 style='text-align:center'>username or password is invalied</h1>");
				req.getRequestDispatcher("Login.html").include(req, res);
			}
			
			req.getRequestDispatcher("footer.html").include(req, res);
			
		} catch (Exception e) {
			pw.print(e);
		
		}
		
	}	
	
}
