package com.admin;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.EncryptDecrypt;

public class AdminProfileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null || (session!=null && session.getAttribute("admin")==null)) {
			response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
			return;
		}
		response.sendRedirect("deanprofilemanager.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session==null || (session!=null && session.getAttribute("admin")==null)) {
			response.sendRedirect("deanlogin.jsp?msg=Authentication Failed.");
			return;
		}
		
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String userName = request.getParameter("username");
		String email= request.getParameter("email");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		if(!password.equals(cpassword)) {
			response.sendRedirect("deanprofilemanager.jsp?msg=password/cpassword is not same.");
			return;
		}
		
		EncryptDecrypt ed = new EncryptDecrypt();
		String pass=ed.encryptPassword(password);
		
		AdminAction action = new AdminAction();
		boolean updateStatus = action.updateAdminDetails(firstName, lastName, userName, email, pass);
		
		if(updateStatus) {
			Admin admin = action.getAdmin("select * from admin");
			session.setAttribute("admin", admin);
			response.sendRedirect("deanprofilemanager.jsp?msg=Details Successfully Updated");			
		} else {
			response.sendRedirect("deanprofilemanager.jsp?msg=Failed to Update the Details.");						
		}
		
	}

}
