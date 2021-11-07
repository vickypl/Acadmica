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
import com.admin.AdminAction;
import com.admin.RegistrationCode;
import com.course.Course;
import com.course.CourseActions;

public class CourseAddView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		String courseCode = request.getParameter("coursecode");
		String coursename = request.getParameter("coursename");
		
		String courseName = coursename.toUpperCase();

		AdminAction adminAction = new AdminAction();
		String sql="select * from REGISTRATION_CODE_COURSE where code='"+courseCode+"'";
		RegistrationCode regCode = null;
		regCode = adminAction.getRegCode(sql);
        
		boolean updateStatus=false;
        boolean courseStatus=false;
        boolean coursefacStatus=false;
		boolean invalidStatus=false;
		if(regCode!=null) {
			
			if(regCode.getStatus().equals("true")) {
				response.sendRedirect("facultycoursedetails.jsp?msg=Your course code is already expired.");
				return;
			}
			
			//database Connector class
			DatabaseConnector db = new DatabaseConnector();
			Connection connection=null;
			PreparedStatement pstat = null;
			
			try {
				connection = db.connect();
				//in case if a faculty adding his/her first subject
				if(faculty.getSubject()==null) {
					sql="update faculty set subject=? where id=?";
					pstat=connection.prepareStatement(sql);
					pstat.setString(1, courseName);
					pstat.setString(2, faculty.getRegistrationId());
					
					int r = pstat.executeUpdate();
					if(r>0) {
						updateStatus=true;
					}
					
				} else {
					updateStatus=true;
				}
				
				//updating the coursename into course table;
				sql="insert into course (id, course_id, course_name) values (course_num.nextval, ?, ?)";
				pstat=connection.prepareStatement(sql);
				pstat.setString(1, regCode.getCode());
				pstat.setString(2, courseName);
				int x=pstat.executeUpdate();
				if(x>0) {
					courseStatus=true;
				}
				
				//updating the course into course_faculty table
				sql="insert into course_faculty (id, regid, cid) values (course_fac_num.nextval, ?, ?)";
				pstat=connection.prepareStatement(sql);
				pstat.setString(1, faculty.getRegistrationId());
				pstat.setString(2, regCode.getCode());
				int t = pstat.executeUpdate();
				
				if(t>0) {
					coursefacStatus=true;
				}
				
				//invalidating the course code after reg
				if(courseStatus && coursefacStatus) {
					sql="update REGISTRATION_CODE_COURSE	set status=? where code=?";
					pstat=connection.prepareStatement(sql);
					pstat.setString(1, "true");
					pstat.setString(2, regCode.getCode());
					int y= pstat.executeUpdate();
					if(y>0) {
						invalidStatus=true;
					}
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
		} else {
			response.sendRedirect("facultycoursedetails.jsp?msg=Invalid Course Code.");	
		}
		
		if(updateStatus && courseStatus && coursefacStatus && invalidStatus) {
			CourseActions action = new CourseActions();
			sql="select * from course c inner join course_faculty cf on cf.cid=c.COURSE_ID and cf.REGID='"+faculty.getRegistrationId()+"'";
			ArrayList<Course> courseList = action.getCoursesList(sql);
			sess.setAttribute("courseList", courseList);
			response.sendRedirect("facultycoursedetails.jsp?msg=Course Successfully Added.");
		} else {
			response.sendRedirect("facultycoursedetails.jsp?msg=Error In Adding Course try again.");			
		}
	}
}