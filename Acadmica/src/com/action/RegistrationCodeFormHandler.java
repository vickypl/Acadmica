package com.action;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCodeFormHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		//fetching data
		String code = request.getParameter("code");
		
		//checking the string is numeric or alpha numeric (if numeric than its student and if alpha numeric than its teacher)
		boolean isNumeric =  isOnlyNumeric(code);
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection = null;
		PreparedStatement pstat = null;
		ResultSet resultSet = null;
		boolean isCodeFound= false;
		String regCode=null;
		String regCodeStatus=null;
		try {
			connection = db.connect();
			String sql= null;
			
			if(isNumeric) {
				sql="select * from registration_code_student where code=?";
			} else {
				sql="select * from registration_code_faculty where code=?";		
			}
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, code);
			resultSet = pstat.executeQuery(); 
			if(resultSet.next()) {
				isCodeFound=true;
				regCode=resultSet.getString(2);
				regCodeStatus=resultSet.getString(3);
			} else {
				response.sendRedirect("home.jsp?codeResult=InValid Registration Code");
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			db.logInFile(e, servletname);
		} catch (SQLException e) {
			db.logInFile(e, servletname);
		} catch(Exception e) {
			db.logInFile(e, servletname);
		}

		
		Cookie cookie = new Cookie("regcode", regCode);
		response.addCookie(cookie);
		
		//response.getWriter().print(regCode);
		
		//redirecting to respective Signup Forms
		if(isNumeric && isCodeFound && regCodeStatus.equals("false")) {
			response.sendRedirect("studentsignup");
		} else if(!isNumeric && isCodeFound && regCodeStatus.equals("false")) {
			response.sendRedirect("facultysignup");
		} else if((isNumeric || !isNumeric) && isCodeFound && regCodeStatus.equals("true")) {
			response.sendRedirect("home.jsp?msg=Registration Code Already Registered.");
		}
	}
	
	public  boolean isOnlyNumeric(String s) {
		return s.matches("[0-9]+"); //if only number than it will return true
    }
}