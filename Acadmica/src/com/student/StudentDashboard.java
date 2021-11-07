package com.student;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session==null || (session!=null && session.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		String msg = "Login Successfull..";
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("studentloggedin.jsp");
		rd.forward(request, response);
	}

}
