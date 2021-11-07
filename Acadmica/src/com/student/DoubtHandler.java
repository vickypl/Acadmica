package com.student;
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

public class DoubtHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		response.sendRedirect("studentdoubt.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//class name for log
		String servletname = this.getClass().getName();
		
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//fetching parameters
		String facultyId = request.getParameter("facultyId");
		String doubt = request.getParameter("doubt");
		
		//fetching student details
		Student student = (Student)sess.getAttribute("student");
		
		//database connection
		DatabaseConnector db = new DatabaseConnector();
	
		Connection connection = null;
		PreparedStatement pstate = null;
		Boolean doubtStatus = false;
		try {
			connection = db.connect();
			String sql="insert into doubts (id, doubt, studentid, facultyid, postedby, postedon) values (doubts_id.nextval, ?, ?, ?, ?, SYSDATE)";
			pstate = connection.prepareStatement(sql);
			pstate.setString(1, doubt);
			pstate.setString(2, student.getRegistrationId());
			pstate.setString(3, facultyId);
			pstate.setString(4, student.getFirstName());
			
			int result = pstate.executeUpdate();
			
			if(result>0) {
				doubtStatus=true;
			} else {
				doubtStatus=false;				
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
		if(doubtStatus) {
			response.sendRedirect("studentdoubt.jsp?msg=Doubt Posted Succefully...");
		}
	}
}
