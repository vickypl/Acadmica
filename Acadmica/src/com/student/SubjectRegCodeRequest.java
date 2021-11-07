package com.student;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.faculty.Faculty;
import com.faculty.FacultyAction;

public class SubjectRegCodeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session
		HttpSession session = request.getSession(false);
		if(session==null || (session!=null && session.getAttribute("student")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Student student = (Student)session.getAttribute("student");
		
		//name of servlets for db file 
		String servletname = this.getClass().getName();
		
		//fetching parameter
		String courseNameCode = request.getParameter("course");
		
		//separating course name and id from course value
		String[] courseNameId = courseNameCode.split(" ");
		String courseName=courseNameId[0];
		String courseId=courseNameId[1];
		
		//fetching faculty of the course selected by a student;
		FacultyAction facultyAction = new FacultyAction();
		Faculty faculty = null;
		String sql ="select * from faculty inner join course_faculty cf on cf.cid='"+courseId+"' and faculty.id=cf.regid";
				faculty = facultyAction.getSingleFaculty(sql);
		
		
		//db connection
		DatabaseConnector db = new DatabaseConnector();
		
		java.sql.Connection connection = null;
		PreparedStatement pstat = null;
		
		boolean requestStatus=false;
		try {
			connection = db.connect();
			sql = "insert into subject_reg_code_request (id, faculty_id, cid, course_name, request, regid, student_name) values(subject_code_req_id.nextval, ?, ?, ?, ?, ?, ?)";
			pstat= connection.prepareStatement(sql);
			pstat.setString(1, faculty.getRegistrationId());
			pstat.setString(2, courseId);
			pstat.setString(3, courseName);
			pstat.setString(4, "Request for new course code provide asap.");
			pstat.setString(5, student.getRegistrationId());
			pstat.setString(6, student.getFirstName());
			
			int result = pstat.executeUpdate();
			if(result>0) {
				requestStatus=true;
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
		
		if(requestStatus) {
			response.sendRedirect("studentsubjectlist.jsp?msg=Code Successfully Requested..");
		} else {
			response.sendRedirect("studentsubjectlist.jsp?msg=Failed to Request the code..");
		}		
		
	}

}
