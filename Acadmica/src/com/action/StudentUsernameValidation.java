package com.action;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.Student;
import com.student.StudentAction;

public class StudentUsernameValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usr = request.getParameter("username");
		
		StudentAction action = new StudentAction();
		
		Student student = null;
		String sql="select * from student from username='"+usr+"'";
			student = action.getSingleStudent(sql);
		
			response.setContentType("text/html");
			
			if(student!=null) {
				String msg = "<span class='text-danger'>Is Not Available</span>";
				response.getWriter().print(msg);
			} else {
				String msg = "<span class='text-success'>Is Available</span>";
				response.getWriter().print(msg);
			}
	}

}
