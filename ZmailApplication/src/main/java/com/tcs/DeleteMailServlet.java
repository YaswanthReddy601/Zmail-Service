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

@WebServlet("/DeleteMailServlet")
public class DeleteMailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("header.html").include(req, res);
		HttpSession hs = req.getSession();
		String zmail = (String)hs.getAttribute("zmail");
		pw.print("<h1 style='text-align:center'>Hi "+zmail+"</h1>");
		req.getRequestDispatcher("link.html").include(req, res);
		
		if(hs==null) {
			req.getRequestDispatcher("Login.html");
		}
		else {
			
			int id=Integer.parseInt(req.getParameter("id"));
			try {
				Connection con = DBConnection.getconnection();
				PreparedStatement pss = con.prepareStatement("select * from composemessage where trash=? and id=?");
				pss.setString(1, "no");
				pss.setInt(2, id);
				ResultSet rs = pss.executeQuery();
				if(rs.next()) {
				}
				else{
					req.setAttribute("message", "there is no mail with the given id or the mail is already sent to trash");
					req.getRequestDispatcher("inboxservlet").forward(req, res);
				}
				
				PreparedStatement ps = con.prepareStatement("update composemessage set trash=? where id=?");
				ps.setString(1, "yes");
				ps.setInt(2, id);
				int i = ps.executeUpdate();
				if(i>0) {
					req.setAttribute("message", "Mail deleted sucessfully");
					req.getRequestDispatcher("inboxservlet").forward(req, res);
				}
				
				con.close();				
			}
			catch(Exception e) {
				pw.print(e);
			}
			
			req.getRequestDispatcher("footer.html").include(req, res);
			pw.close();
			
			
		}

	}
	
	
}
