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
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.student.StudentAction;


public class AssignmentPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		String facultyName = faculty.getFirstName()+" "+faculty.getLastName();
		
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		//fetching values
		String course = request.getParameter("course");
		String assignment = request.getParameter("assignment");
		String subDate = request.getParameter("subDate");
		
		DatabaseConnector db = new DatabaseConnector();
		
		Connection connection = null;
		PreparedStatement pstat = null;
		boolean assignStatus = false;
		try {
			connection = db.connect();
			String sql="insert into assingment(id, course, regid, assingment, assinged_by, ass_date, sub_date) values (ass_id.nextval, ?, ?, ?, ?, SYSDATE, ?)";

			pstat = connection.prepareStatement(sql);
			pstat.setString(1, course);
			pstat.setString(2, faculty.getRegistrationId());
			pstat.setString(3, assignment);
			pstat.setString(4, facultyName);
			pstat.setString(5, subDate);
			
			int result = pstat.executeUpdate();
			if(result>0) {
				assignStatus=true;
			} else {
				assignStatus=false;
			}

		} catch (ClassNotFoundException e) {
			db.logInFile(e, servletname);
		} catch (SQLException e) {
			db.logInFile(e, servletname);
		} catch (Exception e) {
			db.logInFile(e, servletname);
		} finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					db.logInFile(e, servletname);
				}
			}
		}
		if(assignStatus) {
			StudentAction studentAction = new StudentAction();
			String sql="select * from assingment where regid='"+faculty.getRegistrationId()+"'";
			ArrayList<Assignment> assignmentList = studentAction.getStudentAssingments(sql);
			sess.setAttribute("assignmentList", assignmentList);
			response.sendRedirect("facultyassignment.jsp?msg=Assigned Successfully..");
		} else {
			response.sendRedirect("facultyassignment.jsp?msg=Failed to Assigned..");			
		}
	}

}
