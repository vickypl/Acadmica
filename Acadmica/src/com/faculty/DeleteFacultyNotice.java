package com.faculty;
/**
author: Vicky pl
email: vicky542011@gmail.com
mobile: 7828789845
**/
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteFacultyNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session check
		HttpSession sess = request.getSession(false);
		if(sess==null || (sess!=null && sess.getAttribute("faculty")==null)) {
			response.sendRedirect("home.jsp?msg=Login Required");
			return;
		}
		Faculty faculty = (Faculty)sess.getAttribute("faculty");
		
		String noticeId = request.getParameter("id");
		
		FacultyAction action = new FacultyAction();
		
		boolean isDeleted = action.deleteFacultyNotice(noticeId);
		
		if(isDeleted) {
			String sql="select * from faculty_notice where facultyid='"+faculty.getRegistrationId()+"'";
			ArrayList<FacultyNotice> noticeList = action.getFacultyNotice(sql);
			sess.setAttribute("noticeList", noticeList);
			response.sendRedirect("facultynoticehistory.jsp?msg=Notice Deleted.");
		} else {
			response.sendRedirect("facultynoticehistory.jsp?msg=Failed to Deleted.");	
		}
		
	}

}
