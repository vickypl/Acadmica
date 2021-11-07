package com.admin;
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

import com.faculty.Faculty;
import com.faculty.FacultyAction;
import com.faculty.FacutlyProfilePic;

public class AdminFacultyDetailView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("admin")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		
		//fetching student's id
		String regid  = request.getParameter("facultyid");
		
		String sql = "";
		FacultyAction facultyAction = new FacultyAction();
		sql="select * from facultyprofilepic where regid='"+regid+"'";
		FacutlyProfilePic facultyPic = facultyAction.getFacultyProfilePic(sql);
		
		sql="select * from faculty where id='"+regid+"'";
		Faculty faculty=facultyAction.getSingleFaculty(sql);
		
		request.setAttribute("facultyPic", facultyPic);
		request.setAttribute("faculty", faculty);
		
		RequestDispatcher rd = request.getRequestDispatcher("deanteacherfulldetail.jsp");
		rd.forward(request, response);
		
	}
}
