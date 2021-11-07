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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.action.DatabaseConnector;
import com.faculty.Faculty;
import com.faculty.FacultyAction;

public class AdminEditFaculty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regid = request.getParameter("id");
		FacultyAction facultyAction = new FacultyAction();
		String sql="select * from faculty where id='"+regid+"'";
		Faculty faculty=facultyAction.getSingleFaculty(sql);
		request.setAttribute("faculty", faculty);
		RequestDispatcher rd = request.getRequestDispatcher("deaneditfacultydetail.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		HttpSession sess = request.getSession(false);
		if (sess == null
				|| (sess != null && sess.getAttribute("admin") == null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
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
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		//String cpassword = request.getParameter("cpassword");
		
		
		DatabaseConnector db = new DatabaseConnector();
		Connection connection=null;
		PreparedStatement pstat = null;
	
		boolean updateStatus=false;
		try {
			connection=db.connect();
			String sql="update faculty set firstname=?, lastname=?, gender=?, dob=?, mstatus=?, gardian=?, email=?, mobile=?, address1=?, address2=?, city=?, state=?, pincode=?, subject=? where id=?";
			
			pstat=connection.prepareStatement(sql);
			pstat.setString(1, firstName);
			pstat.setString(2, lastName);
			pstat.setString(3, gender);
			pstat.setString(4, dob);
			pstat.setString(5, mstatus);
			pstat.setString(6, gaurdian);
			pstat.setString(7, email);
			pstat.setString(8, mobile);
			pstat.setString(9, address1);
			pstat.setString(10, address2);
			pstat.setString(11, city);
			pstat.setString(12, state);
			pstat.setString(13, pincode);
			pstat.setString(14, subject);
			pstat.setString(15, regid);
			
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
			response.sendRedirect("adminfacultydetailview?facultyid="+regid);
		} else {
			response.sendRedirect("admineditfaculty?id="+regid+"&?msg=Faculty Updated failed..");
		}
	}
}
