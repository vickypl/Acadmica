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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.student.Student;
import com.student.StudentAction;

public class StudentDetailEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regid = request.getParameter("id");
		StudentAction studentAction = new StudentAction();
		String sql="select * from student where id='"+regid+"'";
		Student student=studentAction.getSingleStudent(sql);
		request.setAttribute("student", student);
		RequestDispatcher rd = request.getRequestDispatcher("facultyeditstudentdetail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//servlet name for log file
		String servletname = this.getClass().getName();
		
		//session
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		String regid = request.getParameter("regid");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String category = request.getParameter("category");
		String fatherName = request.getParameter("faname");
		String motherName = request.getParameter("maname");
		String standard = request.getParameter("standard");
		//String course = request.getParameter("course");
		String school = request.getParameter("school");
		String college = request.getParameter("college");
		String degree = request.getParameter("degree");
		String subject = request.getParameter("subject");
		String marks = request.getParameter("marks");
		//String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String fatherMobile = request.getParameter("gaurdmobile");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String pinCode = request.getParameter("pincode");
		//String userName = request.getParameter("username");
		//String password = request.getParameter("password");
		//String cpassword = request.getParameter("confirmp"); //extra
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection=null;
		PreparedStatement pstat = null;
	
		boolean updateStatus=false;
		try {
			connection=db.connect();
			String sql="update student set firstname=?, lastname=?, gender=?, dob=?, category=?, fathername=?, mothername=?, standard=?, school=?, college=?, degree=?, marks=?, mobile=?, fmobile=?, address1=?, address2=?, city=?, state=?, pincode=?, subject=? where id=?";
		
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, firstName);
			pstat.setString(2, lastName);
			pstat.setString(3, gender);
			pstat.setString(4, dob);
			pstat.setString(5, category);
			pstat.setString(6, fatherName);
			pstat.setString(7, motherName);
			pstat.setString(8, standard);
			pstat.setString(9, school);
			pstat.setString(10, college);
			pstat.setString(11, degree);
			pstat.setString(12, marks);
			pstat.setString(13, mobile);
			pstat.setString(14, fatherMobile);
			pstat.setString(15, address1);
			pstat.setString(16, address2);
			pstat.setString(17, city);
			pstat.setString(18, state);
			pstat.setString(19, pinCode);
			pstat.setString(20, subject);
			pstat.setString(21, regid);
			
			int result = pstat.executeUpdate();
			
			if(result>0) {
				updateStatus=true;
			} else {
				updateStatus=false;
			}
			
		} catch(ClassNotFoundException e) {
			db.logInFile(e, servletname);
		} catch(SQLException e) {
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
		
		if(updateStatus) {
			response.sendRedirect("studentdetailview?studentid="+regid);
		} else {
			response.sendRedirect("editstudent?id="+regid+"&?msg=Student Updated failed..");
		}
		
	}
	
}
