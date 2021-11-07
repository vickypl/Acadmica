package com.faculty;
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

public class FacultyPasswordRecovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("facultypasswordrecovery.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailFetched=request.getParameter("email");
		
		FacultyAction action = new FacultyAction();
		String sql="select * from faculty where email='"+emailFetched+"'";
		Faculty faculty = null;
		faculty = action.getSingleFaculty(sql);
		
		if(faculty!=null) {
			
			//decrypting password to send
			EncryptDecrypt ed = new EncryptDecrypt();
			String password = ed.decryptPassword(faculty.getPassword());
			
			Email email = new Email();
			email.setRecipient(faculty.getEmail());
			email.setSubject("Acadmica Password Recovery");
			email.setMessage("Your Recovered Password is "+password+".");
			boolean sentStatus=false;
			sentStatus=email.sendMail();
			if(sentStatus) {				
				response.sendRedirect("facultypasswordrecovery.jsp?msg=Password is sent to your registered email.");
			} else {
				response.sendRedirect("facultypasswordrecovery.jsp?msg=Failed to send the password.");				
			}
			
		} else {
			response.sendRedirect("facultypasswordrecovery.jsp?msg=Email Not Registered. Please Enter a valid email.");
		}
	}

}
