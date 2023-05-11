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

@WebServlet("/trashservlet")
public class TrashServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		req.getRequestDispatcher("header.html").include(req, res);
		
		HttpSession hs= req.getSession(false);
		if(hs==null) {
			res.sendRedirect("Login.html");
		}
		else {
			String zmail = (String)hs.getAttribute("zmail");
			pw.print("<h1 style='text-align:center'>hi "+zmail+"</h1>");
			req.getRequestDispatcher("link.html").include(req, res);
			pw.print("<h1 style='text-align:center'><b>Trash</b></h1>");
			
			String message = (String)req.getAttribute("message");
			if(message!=null)
				pw.print("<h1>"+message+"</h1>");
			
			try {
				Connection con = DBConnection.getconnection();
				PreparedStatement ps = con.prepareStatement("select * from composemessage where sender=? and trash=?");
				ps.setString(1, zmail);
//				ps.setString(2, zmail);
				ps.setString(2, "yes");
				
				ResultSet rs = ps.executeQuery();
				pw.print("<center><table border='2'>");
				pw.print("<tr style='background-color:green;color:white'><td>receiver</td><td>subject</td><td>view</td><td>permanent delete</td></tr>");
				while(rs.next()){
					pw.print("<tr><td>"+rs.getString("receiver")+"</td><td>"+rs.getString("subject")+"</td><td><a href='ViewMailServlet?id="+rs.getString("id")+"'>View</a></td><td><a href='permanentdelete?id="+rs.getInt("id")+"'> Delete permanently </a></td></tr>");
				}
				pw.print("</table></center>");
			}
			catch(Exception e) {
				pw.print(e);
			}
		}
		req.getRequestDispatcher("footer.html").include(req, res);
	}
	
}
