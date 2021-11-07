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

import mail.Email;

import com.action.EncryptDecrypt;


public class AdminPasswordRecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("deanpasswordrecovery.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailFetched=request.getParameter("email");
		
		AdminAction action = new AdminAction();
		String sql="select * from admin where email='"+emailFetched+"'";
		Admin admin = null;
		admin = action.getAdmin(sql);
		
		if(admin!=null) {
			
			//decrypting password to send
			EncryptDecrypt ed = new EncryptDecrypt();
			String password = ed.decryptPassword(admin.getPassword());
			
			Email email = new Email();
			email.setRecipient(admin.getEmail());
			email.setSubject("Acadmica Password Recovery");
			email.setMessage("Your Recovered Password is "+password+".");
			boolean sentStatus=false;
			sentStatus=email.sendMail();
			if(sentStatus) {				
				response.sendRedirect("deanpasswordrecovery.jsp?msg=Password is sent to your registered email.");
			} else {
				response.sendRedirect("deanpasswordrecovery.jsp?msg=Failed to send the password.");				
			}
			
		} else {
			response.sendRedirect("deanpasswordrecovery.jsp?msg=Email Not Registered. Please Enter a valid email.");
		}
	}

}
