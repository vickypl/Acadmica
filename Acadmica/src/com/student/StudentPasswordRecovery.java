package com.student;
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

public class StudentPasswordRecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("studentpasswordrecovery.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailFetched=request.getParameter("email");
		
		StudentAction action = new StudentAction();
		String sql="select * from student where email='"+emailFetched+"'";
		Student student = null;
					  student = action.getSingleStudent(sql);
		
		if(student!=null) {
			
			//decrypting password to send
			EncryptDecrypt ed = new EncryptDecrypt();
			String password = ed.decryptPassword(student.getPassword());
			
			Email email = new Email();
			email.setRecipient(student.getEmail());
			email.setSubject("Acadmica Password Recovery");
			email.setMessage("Your Recovered Password is "+password+".");
			boolean sentStatus=false;
			sentStatus=email.sendMail();
			if(sentStatus) {				
				response.sendRedirect("studentpasswordrecovery.jsp?msg=Password is sent to your registered email.");
			} else {
				response.sendRedirect("studentpasswordrecovery.jsp?msg=Failed to send the password.");				
			}
			
		} else {
			response.sendRedirect("studentpasswordrecovery.jsp?msg=Email Not Registered. Please Enter a valid email.");
		}
	}
}
