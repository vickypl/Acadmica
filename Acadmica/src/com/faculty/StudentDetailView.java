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
import com.student.StudentProfilePic;



public class StudentDetailView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//deanteacherfulldetail.jsp		
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//fetching student's id
		String regid  = request.getParameter("studentid");
		
		String sql = "";
		StudentAction studentAction = new StudentAction();
		sql="select * from studentprofilepic where regid='"+regid+"'";
		StudentProfilePic spp = studentAction.getStudentProfilePic(sql);
		
		sql="select * from student where id='"+regid+"'";
		Student student=studentAction.getSingleStudent(sql);
		
		request.setAttribute("studentPic", spp);
		request.setAttribute("student", student);
		
		RequestDispatcher rd = request.getRequestDispatcher("facultystudentfulldetail.jsp");
		rd.forward(request, response);
	}
}
