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
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.action.DatabaseConnector;
import com.action.EncryptDecrypt;
import com.course.CourseActions;
import com. course.Course;
import com.faculty.Faculty;
import com.faculty.FacultyAction;

public class StudentSignupForm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CourseActions action = new CourseActions();
		ArrayList<Course> courseList = new ArrayList<Course>();
		courseList = action.getCoursesList("select * from course"); 
		request.setAttribute("courseList", courseList);
		
		//fetching error msg
		String msg = null;
		if(request.getParameter("msg")!=null) {
			msg = request.getParameter("msg");
			request.setAttribute("msg", msg);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("studentsingup.jsp");
		rd.forward(request, response);
		//response.sendRedirect("studentsingup.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//servlet name for error log file
		String servletname = this.getClass().getName();
		
		//fetching data from studentsignup.jsp 24 items
		String regid = request.getParameter("regid");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String category = request.getParameter("category");
		String fatherName = request.getParameter("faname");
		String motherName = request.getParameter("maname");
		String standard = request.getParameter("standard");
		String course = request.getParameter("course");
		String school = request.getParameter("school");
		String college = request.getParameter("college");
		String degree = request.getParameter("degree");
		String subject = request.getParameter("subject");
		String marks = request.getParameter("marks");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String fatherMobile = request.getParameter("gaurdmobile");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pinCode = request.getParameter("pincode");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("confirmp"); //extra
		
		
		//checking if user name is already exists or  not
		StudentAction studentAction = new StudentAction();
		Student stu = null;
		String q ="select * from student where username='"+userName+"' or email='"+email+"'";
		stu = studentAction.getSingleStudent(q);
		
		if(stu!=null) {
			response.sendRedirect("studentsignup?msg=Username/Email Already Exists..");
			return;
		}
 		
		
		//temp logic for now 
		if(!password.equals(cpassword)) {
			response.sendRedirect("errorpage.jsp");
		}
		
		//separating course name and id from course value
		String[] courseNameId = course.split(" ");
		String courseName=courseNameId[0];
		String courseId=courseNameId[1];
	
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection = null;
		PreparedStatement pstat = null;
		boolean registrationStatus = false;
		boolean courseStatus=false;
		boolean propicStatus=false;
		try {
			connection=db.connect();
			
			//it can be null after the addition of new logic
			//fetching faculty name of the course selected by the student to submit in faculty column
			FacultyAction action = new FacultyAction();
			String fetcher = "select * from faculty f inner join course_faculty cf on f.id=cf.regid and cf.cid='"+courseId+"'";
			ArrayList<Faculty> facultyList = action.getFaculty(fetcher);
			Faculty techer = facultyList.get(0);
			String facultyName=techer.getFirstName();

			
			//to encrypt the password
			EncryptDecrypt encryptor = new EncryptDecrypt();
			String encryptedPassword = encryptor.encryptPassword(password);
			
			
			String sql="insert into student(id, firstname, lastname, gender, dob, category, fathername, mothername, standard, course, school, college, degree, marks, email, mobile, fmobile, address1, address2, city, state, pincode, subject, username, password, regdate, lastlogin, faculty, role) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, 'student')";
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, regid);
			pstat.setString(2, firstName);
			pstat.setString(3, lastName);
			pstat.setString(4, gender);
			pstat.setString(5, dob);
			pstat.setString(6, category);
			pstat.setString(7, fatherName);
			pstat.setString(8, motherName);
			pstat.setString(9, standard);
			pstat.setString(10, courseName);
			pstat.setString(11, school);
			pstat.setString(12, college);
			pstat.setString(13, degree);
			pstat.setString(14, marks);
			pstat.setString(15, email);
			pstat.setString(16, mobile);
			pstat.setString(17, fatherMobile);
			pstat.setString(18, address1);
			pstat.setString(19, address2);
			pstat.setString(20, city);
			pstat.setString(21, state);
			pstat.setString(22, pinCode);
			pstat.setString(23, subject);
			pstat.setString(24, userName);
			pstat.setString(25, encryptedPassword);
			pstat.setString(26, facultyName);
			
			int result = pstat.executeUpdate();
		
			if(result>0) {
				registrationStatus=true;
			} else {
				registrationStatus=false;
			}
			
			//updating the student id into course_student table as student has chosen one						
			sql="insert into course_student (id, regid, cid) values (course_stu_num.nextval, ?, ?)";
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, regid);
			pstat.setString(2, courseId);
			int r = pstat.executeUpdate();
			if(r>0) {
				courseStatus=true;
			} else {
				courseStatus=false;
			}
			
			//filling profile pic column
			sql = "insert into studentprofilepic(id, regid, url) values (fac_pic_num.nextval, ?, ?)";			
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
				String updateCodeStatus="update registration_code_student set status = 'true' where code=?";
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
