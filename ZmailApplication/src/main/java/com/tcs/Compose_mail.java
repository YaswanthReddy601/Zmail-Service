package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/composemail")
public class Compose_mail extends HttpServlet {
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		resp.setContentType("text/html");
		
		req.getRequestDispatcher("header.html").include(req, resp);
		req.getRequestDispatcher("link.html").include(req, resp);
		
		HttpSession hs = req.getSession(false);
		if(hs==null)
			resp.sendRedirect("Login.html");
		else {
			String message =  (String) hs.getAttribute("msg");
			if(message!=null) {
				pw.print("<h1>"+message+"</h1>");
			}
				req.getRequestDispatcher("Compose.html").include(req, resp);
		}
		req.getRequestDispatcher("footer.html").include(req, resp);
	}
	
	
}
