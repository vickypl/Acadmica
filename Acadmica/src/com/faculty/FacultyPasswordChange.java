package com.faculty;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.action.EncryptDecrypt;

public class FacultyPasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("facultypasswordchange.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session vlidator
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		//servlet name for log file
		String servletname=this.getClass().getName();
		//fetching details
		String oldPassword = request.getParameter("old");
		String newPassword = request.getParameter("password");
		String cPassword = request.getParameter("cpassword");
		

		//checking
		if(!newPassword.equals(cPassword)) {
			response.sendRedirect("facultypasswordchange.jsp?msg=New/Confirm Password is not same");
			return;
		}
		
		//encrypting old password to validate
		EncryptDecrypt ed = new EncryptDecrypt();
		String oldpass = ed.encryptPassword(oldPassword);
		if(!oldpass.equals(faculty.getPassword())) {
			response.sendRedirect("facultypasswordchange.jsp?msg=Wrong old password");
			return;
		}
		
		//encrypting new password to update
		String pass = ed.encryptPassword(newPassword);
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection = null;
		PreparedStatement pstate =null;
		boolean updateStatus = false;
		try {
			String id = faculty.getRegistrationId();
			connection = db.connect();
			String sql = "update faculty set password=? where id=?";
			pstate = connection.prepareStatement(sql);
			pstate.setString(1, pass);
			pstate.setString(2, id);
			
			int result = pstate.executeUpdate();
			
			if(result>0) {
				updateStatus=true;
			} else {
				updateStatus=false;
			}
			
		} catch(ClassNotFoundException e) {
			db.logInFile(e, servletname);
			response.sendRedirect("errorpage.jsp");
		} catch(SQLException e) {
			db.logInFile(e, servletname);
			response.sendRedirect("errorpage.jsp");
		} catch(Exception e) {
			db.logInFile(e, servletname);
			response.sendRedirect("errorpage.jsp");
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					db.logInFile(e, servletname);
					response.sendRedirect("errorpage.jsp");
				}
			}
		}
		if(updateStatus) {
			response.sendRedirect("facultypasswordchange.jsp?msg=Password Successfully Updated.");
		} else {
			response.sendRedirect("facultypasswordchange.jsp?msg=Failed to Update the password.");			
		}
	}
}
