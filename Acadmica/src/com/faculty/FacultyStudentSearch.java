package com.faculty;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.Student;
import com.student.StudentAction;

public class FacultyStudentSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}	
		
		//fetching student id;
		String studentId = request.getParameter("studentid");
		
		StudentAction action = new StudentAction();
		Student studentFound = null;
		
		String sql="select * from student where id='"+studentId+"'";
			studentFound = action.getSingleStudent(sql);
		

			RequestDispatcher rd = request.getRequestDispatcher("facultystudentlist.jsp");
			request.setAttribute("studentFound", studentFound);
			rd.forward(request, response);
	
	}

}
