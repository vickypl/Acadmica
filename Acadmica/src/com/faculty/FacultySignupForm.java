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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.DatabaseConnector;
import com.action.EncryptDecrypt;
import com.course.Course;
import com.course.CourseActions;


public class FacultySignupForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourseActions action = new CourseActions();
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = action.getCoursesList("select * from course"); 
		
		//fetching error msg
		String msg = null;
		if(request.getParameter("msg")!=null) {
			msg = request.getParameter("msg");
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("courseList", courseList);
		RequestDispatcher rd = request.getRequestDispatcher("facultysignup.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//servlet name for logfile
		String servletname = this.getClass().getName();
		
		//fetching parameters
		String regid = request.getParameter("id");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String mstatus = request.getParameter("mstatus");
		String gaurdian = request.getParameter("faname");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pincode = request.getParameter("pincode");
		String subject = request.getParameter("subject");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");

		//temp logic for now 
		if(!password.equals(cpassword)) {
			response.sendRedirect("errorpage.jsp?msg=password/Confirm Password not matched..");
		}
		
		//checking for unique username and email 
		FacultyAction facultyAction = new FacultyAction();
		Faculty fac = null;
		String q ="select * from faculty where username='"+username+"' or email='"+email+"'";
		fac = facultyAction.getSingleFaculty(q);
				
		if(fac!=null) {
			response.sendRedirect("facultysignup?msg=Username/Email Already Exists..");
			return;
		}
		
		
		//separating course name and id from course value
		String[] courseNameId=null;
		String courseName=null;
		//String courseId=null;
		if(!subject.equals("null")) {
			courseNameId = subject.split(" ");
			courseName=courseNameId[0];
			//courseId=courseNameId[1];
		}
		
		//db code
		DatabaseConnector db = new DatabaseConnector();
		Connection connection = null;
		PreparedStatement pstat = null;
		boolean registrationStatus = false;
		boolean courseStatus=true;
		boolean propicStatus=false;
		try {
			connection=db.connect();
			
			
			//to encrypt the password
			EncryptDecrypt encryptor = new EncryptDecrypt();
			String encryptedPassword = encryptor.encryptPassword(password);
			
			
			String sql="insert into faculty(id, firstname, lastname, gender, dob, mstatus, gardian, email, mobile, address1, address2, city, state, pincode, subject, username, password, regdate, lastlogin, role) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, 'faculty')";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, regid);
			pstat.setString(2, firstName);
			pstat.setString(3, lastName);
			pstat.setString(4, gender);
			pstat.setString(5, dob);
			pstat.setString(6, mstatus);
			pstat.setString(7, gaurdian);
			pstat.setString(8, email);
			pstat.setString(9, mobile);
			pstat.setString(10, address1);
			pstat.setString(11, address2);
			pstat.setString(12, city);
			pstat.setString(13, state);
			pstat.setString(14, pincode);
			pstat.setString(15, courseName);
			pstat.setString(16, username);
			pstat.setString(17, encryptedPassword);
			
			int result = pstat.executeUpdate();
		
			if(result>0) {
				registrationStatus=true;
			} else {
				registrationStatus=false;
			}
			
			//updating the faculty id into course_student table as student has chosen one						
/*			if(!subject.equals("null")) {
				sql="insert into course_faculty (id, regid, cid) values (course_fac_num.nextval, ?, ?)";
				pstat = connection.prepareStatement(sql);
				pstat.setString(1, regid);
				pstat.setString(2, courseId);
				int r = pstat.executeUpdate();
				if(r>0) {
					courseStatus=true;
				} else {
					courseStatus=false;
				}
			} else {
				courseStatus=true;
			}*/
			//filling profile pic column
			sql = "insert into facultyprofilepic(id, regid, url) values (stu_pic_num.nextval, ?, ?)";			
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, regid);
			pstat.setString(2, null);
			
			int pro = pstat.executeUpdate();
			if(pro>0) {
				propicStatus=true;
			} else {
				propicStatus=false;				
			}
			
			
			//invalidating the registration in code in student_registrasion code table to avoid multi signup
			if(registrationStatus) {
				String updateCodeStatus="update registration_code_faculty set status = 'true' where code=?";
				pstat=connection.prepareStatement(updateCodeStatus);
				pstat.setString(1, regid);
				int status=pstat.executeUpdate();
				if(status>0) {
					registrationStatus=true;
				} else {
					registrationStatus=false;					
				}
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
			if (connection!=null) {
				try {
					connection.close();
				} catch(SQLException e) {
					db.logInFile(e, servletname);
					response.sendRedirect("errorpage.jsp");
				}
			}
		}
		if(registrationStatus && courseStatus && propicStatus) {
			response.sendRedirect("home.jsp?msg=Registration successful.....SignIn Now");
		}
	}
}