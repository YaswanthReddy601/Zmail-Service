package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("header.html").include(req, res);
		
		HttpSession hs = req.getSession(false);
		if(hs==null)
			res.sendRedirect("Login.html");
		else {
			req.getRequestDispatcher("link.html").include(req, res);
			
			String name = (String)hs.getAttribute("name");
			String zmail = (String)hs.getAttribute("zmail");
			String gender = (String)hs.getAttribute("gender");
			String date = (String)hs.getAttribute("date");
			String city = (String)hs.getAttribute("city");
			String state = (String)hs.getAttribute("state");
			String country = (String)hs.getAttribute("country");
			String contact = (String)hs.getAttribute("contact");
			
			pw.print("<center>");
			pw.print("name::<h2 text-align:center>"+name+"</h2>");
			pw.print("zmail::<h2 text-align:center>"+zmail+"</h2>");
			pw.print("gender::<h2 text-align:center>"+gender+"</h2>");
			pw.print("date::<h2 text-align:center>"+date+"</h2>");
			pw.print("city::<h2 text-align:center>"+city+"</h2>");
			pw.print("state::<h2 text-align:center>"+state+"</h2>");
			pw.print("country::<h2 text-align:center>"+country+"</h2>");
			pw.print("contact::<h2 text-align:center>"+contact+"</h2>");
			pw.print("</center>");
			
		}
	}
}
