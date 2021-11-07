package com.admin;
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
import com.course.Course;
import com.course.CourseActions;

public class DeleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		String courseId = request.getParameter("cid");
		
		//database Connector class
		DatabaseConnector db = new DatabaseConnector();
		Connection connection=null;
		PreparedStatement pstat = null;
		
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		boolean deleteRegCode=false;
		boolean deleteStudentStatus=false;
		boolean deleteFacultyStatus=false;
		boolean deleteFileStatus=false;
		boolean deleteVideoStatus=false;
		boolean deleteCourseStatus=false;
		int result=0;
		String sql=null;
		try {
			//1
			connection = db.connect();
			sql="delete from registration_code_course where code=?";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, courseId);			
			
			result = pstat.executeUpdate();
			if(result>0) {
				deleteRegCode=true;
			} 
			
			//2
			sql="delete from course_student where cid=?";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, courseId);			
			
			result = pstat.executeUpdate();
			if(result>0) {
				deleteStudentStatus=true;
			} 
			
			//3
			sql="delete from course_faculty where cid=?";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, courseId);			
			
			result = pstat.executeUpdate();
			if(result>0) {
				deleteFacultyStatus=true;
			} 
			
			//4
			sql="delete from COURSE_FILES where COURSEID=?";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, courseId);			
			
			result = pstat.executeUpdate();
			if(result>0) {
				deleteFileStatus=true;
			} 
			
			//5
			sql="delete from COURSE_VIDEOS where COURSEID=?";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, courseId);			
			
			result = pstat.executeUpdate();
			if(result>0) {
				deleteVideoStatus=true;
			} 
			
			//6
			sql="delete from course where course_id=?";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, courseId);			
			
			result = pstat.executeUpdate();
			if(result>0) {
				deleteCourseStatus=true;
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
		
		if(deleteRegCode && deleteStudentStatus && deleteFacultyStatus && deleteFileStatus && deleteVideoStatus && deleteCourseStatus) {
			CourseActions courseAction = new CourseActions();
			sql="select * from course";
			ArrayList<Course> courseList = courseAction.getCoursesList(sql);
			sess.setAttribute("courseList", courseList);
			response.sendRedirect("deancoursemanagement.jsp");
		} else {
			response.sendRedirect("deancoursemanagement.jsp?msg=failed to delete course, try later.");			
		}
		
	}

}
