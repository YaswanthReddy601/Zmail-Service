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
import javax.servlet.http.HttpSession;

@WebServlet("/permanentdelete")
public class PermanentDelete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("header.html").include(req, res);
		req.getRequestDispatcher("link.html").include(req, res);
		
		HttpSession hs = req.getSession(false);
		if(hs==null) {
			res.sendRedirect("Login.html");
		}
		
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		int a=0;
		try {
			Connection con  = DBConnection.getconnection();
			PreparedStatement ps = con.prepareStatement("Delete from composemessage where id = ?");
			ps.setInt(1, id);
			a = ps.executeUpdate();
			if(a>0) {
				req.setAttribute("message", "zmail delated sucessfully");
				req.getRequestDispatcher("trashservlet").forward(req, res);
			}
			else{
				pw.print("<h3 style='text-align:center'>there is no mail with the given id or the mail is already deleted</h3>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("footer.html").include(req, res);
		
	}
}
